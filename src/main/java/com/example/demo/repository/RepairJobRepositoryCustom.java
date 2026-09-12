package com.example.demo.repository;

import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedRepairJobResponse;

import java.text.ParseException;

public interface RepairJobRepositoryCustom {
    PagedRepairJobResponse searchJobs(GridSearchDto gridSearchDto) throws ParseException;
}
