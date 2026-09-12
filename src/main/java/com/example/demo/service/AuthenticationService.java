package com.example.demo.service;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final com.example.demo.repository.RoleRepository roleRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        user.setBranchLocation(request.getBranchLocation());
        repository.save(user);

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().getName());
        if (user.getRole().getPrivileges() != null) {
            java.util.List<String> privileges = user.getRole().getPrivileges().stream()
                    .map(com.example.demo.entity.Privilege::getName)
                    .collect(java.util.stream.Collectors.toList());
            extraClaims.put("privileges", privileges);
        }
        extraClaims.put("tenantId", com.example.demo.config.tenant.TenantContext.get());
        String jwtToken = jwtService.generateToken(extraClaims, user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().getName());
        if (user.getRole().getPrivileges() != null) {
            java.util.List<String> privileges = user.getRole().getPrivileges().stream()
                    .map(com.example.demo.entity.Privilege::getName)
                    .collect(java.util.stream.Collectors.toList());
            extraClaims.put("privileges", privileges);
        }
        extraClaims.put("tenantId", com.example.demo.config.tenant.TenantContext.get());
        String jwtToken = jwtService.generateToken(extraClaims, user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthenticationResponse refreshAccessToken(String token) {
        RefreshToken refreshToken = refreshTokenService.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found. Please login again."));

        // Validates expiry — throws exception if expired
        refreshTokenService.verifyExpiration(refreshToken);

        // Look up user by the stored userId (no more entity navigation)
        User user = repository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found for refresh token."));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().getName());
        if (user.getRole().getPrivileges() != null) {
            java.util.List<String> privileges = user.getRole().getPrivileges().stream()
                    .map(com.example.demo.entity.Privilege::getName)
                    .collect(java.util.stream.Collectors.toList());
            extraClaims.put("privileges", privileges);
        }
        extraClaims.put("tenantId", com.example.demo.config.tenant.TenantContext.get());
        String newAccessToken = jwtService.generateToken(extraClaims, user);

        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(token) // same refresh token reused until it expires
                .build();
    }
}
