package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {
    private Long id;
    private String label;
    private String icon;
    private String routerLink;
    private String requiredPrivilege;
    private String section;
    private String badgeType;
    private Integer orderIndex;
}
