package com.example.demo.dto;

import lombok.Data;

import java.util.Map;

@Data
public class GridSearchDto {

    private Integer first;
    private Integer rows;

    private String sortField;
    private Integer sortOrder;

    private String globalFilter;

    private Map<String, Filter> filters;

    private String startDate;
    private String endDate;

    private String invoiceNumber;
    private String customerName;
    private String paymentMethod;
}
