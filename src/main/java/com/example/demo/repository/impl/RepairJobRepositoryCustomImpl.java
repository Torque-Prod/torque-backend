package com.example.demo.repository.impl;

import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedRepairJobResponse;
import com.example.demo.dto.RepairJobDto;
import com.example.demo.repository.RepairJobRepositoryCustom;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepairJobRepositoryCustomImpl implements RepairJobRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PagedRepairJobResponse searchJobs(GridSearchDto dto) throws ParseException {

        // Base native SQL — joins via plain FK columns (no JPA associations)
        String baseFrom = """
                FROM repair_jobs rp
                LEFT JOIN customers cus ON cus.id = rp.customer_id
                LEFT JOIN technicians tech ON tech.id = rp.technician_id
                LEFT JOIN repair_job_parts rjp ON rjp.repair_job_id = rp.id
                WHERE 1=1
                """;

        StringBuilder where = new StringBuilder();
        List<Object> params = new ArrayList<>();
        int paramIndex = 1;

        // Global filter (searches job_number, customer name, item_name, model_number, serial_number)
        if (dto.getGlobalFilter() != null && !dto.getGlobalFilter().isBlank()) {
            where.append(" AND (rp.job_number LIKE ? OR cus.name LIKE ? OR rp.item_name LIKE ? OR rp.model_number LIKE ? OR rp.serial_number LIKE ?)");
            String pattern = "%" + dto.getGlobalFilter().trim() + "%";
            params.add(pattern);
            params.add(pattern);
            params.add(pattern);
            params.add(pattern);
            params.add(pattern);
        }

        // Column-level filters
        if (dto.getFilters() != null) {
            var f = dto.getFilters();

            if (hasValue(f.get("rp.jobNumber"))) {
                where.append(" AND rp.job_number LIKE ?");
                params.add("%" + f.get("rp.jobNumber").getValue() + "%");
            }
            if (hasValue(f.get("customerName"))) {
                where.append(" AND cus.name LIKE ?");
                params.add("%" + f.get("customerName").getValue() + "%");
            }
            if (hasValue(f.get("itemName"))) {
                where.append(" AND rp.item_name LIKE ?");
                params.add("%" + f.get("itemName").getValue() + "%");
            }
            if (hasValue(f.get("modelNumber"))) {
                where.append(" AND rp.model_number LIKE ?");
                params.add("%" + f.get("modelNumber").getValue() + "%");
            }
            if (hasValue(f.get("serialNumber"))) {
                where.append(" AND rp.serial_number LIKE ?");
                params.add("%" + f.get("serialNumber").getValue() + "%");
            }
            if (hasValue(f.get("technicianName"))) {
                where.append(" AND tech.name LIKE ?");
                params.add("%" + f.get("technicianName").getValue() + "%");
            }
            if (hasValue(f.get("status"))) {
                where.append(" AND rp.status = ?");
                params.add(f.get("status").getValue());
            }
            if (f.get("paid") != null && f.get("paid").getValue() != null) {
                where.append(" AND rp.is_paid = ?");
                params.add(f.get("paid").getValue());
            }
        }

        // Count query
        String countSql = "SELECT COUNT(DISTINCT rp.id) " + baseFrom + where;
        Query countQuery = entityManager.createNativeQuery(countSql);
        setNativeParams(countQuery, params);
        long totalRecords = ((Number) countQuery.getSingleResult()).longValue();

        // Sort
        String sortClause = " ORDER BY rp.created_at DESC";
        if (dto.getSortField() != null && !dto.getSortField().isBlank()) {
            String col = mapSortField(dto.getSortField());
            String dir = (dto.getSortOrder() != null && dto.getSortOrder() == -1) ? "DESC" : "ASC";
            sortClause = " ORDER BY " + col + " " + dir;
        }

        // Data query — select all needed columns in one shot
        String dataSql = """
                SELECT DISTINCT
                    rp.id,
                    rp.job_number,
                    rp.customer_id,
                    cus.name        AS customer_name,
                    cus.phone       AS customer_phone,
                    rp.technician_id,
                    tech.name       AS technician_name,
                    rp.item_name,
                    rp.model_number,
                    rp.serial_number,
                    rp.problem_description,
                    rp.received_date,
                    rp.expected_date,
                    rp.status,
                    rp.service_charge,
                    rp.is_paid,
                    rp.created_at,
                    COALESCE(SUM(rjp.quantity * rjp.unit_price), 0) AS parts_total
                """ + baseFrom + where +
                " GROUP BY rp.id, rp.job_number, rp.customer_id, cus.name, cus.phone, rp.technician_id, tech.name, rp.item_name, rp.model_number, rp.serial_number, rp.problem_description, rp.received_date, rp.expected_date, rp.status, rp.service_charge, rp.is_paid, rp.created_at"
                + sortClause;

        Query dataQuery = entityManager.createNativeQuery(dataSql);
        setNativeParams(dataQuery, params);

        // Pagination
        int first = (dto.getFirst() != null && dto.getFirst() >= 0) ? dto.getFirst() : 0;
        int rows  = (dto.getRows()  != null && dto.getRows()  > 0)  ? dto.getRows()  : 25;
        dataQuery.setFirstResult(first);
        dataQuery.setMaxResults(rows);

        List<Object[]> rows_result = dataQuery.getResultList();
        List<RepairJobDto> data = new ArrayList<>();

        for (Object[] row : rows_result) {
            // row indices after adding model_number (8) and serial_number (9):
            // 0=id, 1=job_number, 2=customer_id, 3=customer_name, 4=customer_phone,
            // 5=technician_id, 6=technician_name, 7=item_name,
            // 8=model_number, 9=serial_number, 10=problem_description,
            // 11=received_date, 12=expected_date, 13=status,
            // 14=service_charge, 15=is_paid, 16=created_at, 17=parts_total
            double serviceCharge = row[14] != null ? ((Number) row[14]).doubleValue() : 0.0;
            double partsTotal    = row[17] != null ? ((Number) row[17]).doubleValue() : 0.0;

            RepairJobDto job = RepairJobDto.builder()
                    .id(((Number) row[0]).longValue())
                    .jobNumber((String) row[1])
                    .customerId(((Number) row[2]).longValue())
                    .customerName((String) row[3])
                    .customerPhone((String) row[4])
                    .technicianId(row[5] != null ? ((Number) row[5]).longValue() : null)
                    .technicianName(row[6] != null ? (String) row[6] : "Unassigned")
                    .itemName((String) row[7])
                    .modelNumber((String) row[8])
                    .serialNumber((String) row[9])
                    .problemDescription((String) row[10])
                    .receivedDate(toLocalDate(row[11]))
                    .expectedDate(toLocalDate(row[12]))
                    .status((String) row[13])
                    .serviceCharge(serviceCharge)
                    .paid(toBoolean(row[15]))
                    .partsTotal(partsTotal)
                    .netTotal(serviceCharge + partsTotal)
                    .usedParts(new ArrayList<>())
                    .build();

            data.add(job);
        }

        return PagedRepairJobResponse.builder()
                .data(data)
                .totalRecords(totalRecords)
                .build();
    }

    // ── helpers ──────────────────────────────────────────────

    private LocalDate toLocalDate(Object value) {
        if (value == null) return null;
        if (value instanceof LocalDate ld) return ld;
        if (value instanceof java.sql.Date d) return d.toLocalDate();
        return null;
    }

    private boolean toBoolean(Object value) {
        if (value == null)                return false;
        if (value instanceof Boolean b)   return b;
        if (value instanceof Number n)    return n.intValue() == 1;
        return false;
    }

    private boolean hasValue(com.example.demo.dto.Filter<?> f) {
        return f != null && f.getValue() != null && !f.getValue().toString().isBlank();
    }

    private void setNativeParams(Query query, List<Object> params) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }
    }

    private String mapSortField(String field) {
        return switch (field) {
            case "jobNumber"      -> "rp.job_number";
            case "customerName"   -> "cus.name";
            case "technicianName" -> "tech.name";
            case "itemName"       -> "rp.item_name";
            case "status"         -> "rp.status";
            case "netTotal"       -> "(rp.service_charge + COALESCE(parts_total,0))";
            case "receivedDate"   -> "rp.received_date";
            default               -> "rp.created_at";
        };
    }
}
