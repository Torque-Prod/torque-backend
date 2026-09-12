package com.example.demo.config.tenant;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String host = request.getHeader("Host");
        // For local development on IPs (like 172.x.x.x or localhost), fallback to your main database
        String tenantId = "new-version-electronic"; 

        if (host != null && host.contains(".")) {
            // e.g. shop-abc.torque.lk -> shop-abc
            String possibleTenantId = host.split("\\.")[0];
            
            // Don't treat localhost, 127.x.x.x, or local network IPs (like 192, 172, 10) as tenants
            if (!possibleTenantId.equals("localhost") 
                && !possibleTenantId.matches("^(127|192|172|10)$") 
                && !possibleTenantId.matches("^[0-9]+$")) {
                tenantId = possibleTenantId;
            }
        }

        try {
            TenantContext.set(tenantId);
            filterChain.doFilter(request, response);
        } finally {
            // Always clear the ThreadLocal after the request is processed
            TenantContext.clear();
        }
    }
}
