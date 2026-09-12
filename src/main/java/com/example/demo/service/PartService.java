package com.example.demo.service;

import com.example.demo.config.tenant.TenantContext;
import com.example.demo.dto.CsvImportResultDto;
import com.example.demo.dto.GridSearchDto;
import com.example.demo.dto.PagedPartResponse;
import com.example.demo.dto.PartDto;
import com.example.demo.entity.Part;
import com.example.demo.repository.PartRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PartDto createPart(PartDto request) {
        Part part = Part.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .category(request.getCategory())
                .containerLocation(request.getContainerLocation())
                .build();

        return toDto(partRepository.save(part));
    }

    public List<PartDto> getAllParts() {
        return partRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ─── SEARCH with filters + pagination ──────────────────
    public PagedPartResponse searchParts(GridSearchDto dto) {

        String baseFrom = "FROM parts p WHERE 1=1";
        StringBuilder where = new StringBuilder();
        List<Object> params = new ArrayList<>();

        // Global filter
        if (dto.getGlobalFilter() != null && !dto.getGlobalFilter().isBlank()) {
            where.append(" AND p.name LIKE ?");
            params.add("%" + dto.getGlobalFilter().trim() + "%");
        }

        // Column filters
        if (dto.getFilters() != null) {
            var f = dto.getFilters();
            if (hasValue(f.get("name"))) {
                where.append(" AND p.name LIKE ?");
                params.add("%" + f.get("name").getValue() + "%");
            }
            if (hasValue(f.get("stockStatus"))) {
                String status = f.get("stockStatus").getValue().toString();
                if ("low".equalsIgnoreCase(status)) {
                    where.append(" AND p.stock_quantity <= 5");
                } else if ("available".equalsIgnoreCase(status)) {
                    where.append(" AND p.stock_quantity > 5");
                }
            }
            if (hasValue(f.get("category"))) {
                where.append(" AND p.category LIKE ?");
                params.add("%" + f.get("category").getValue() + "%");
            }
            if (hasValue(f.get("containerLocation"))) {
                where.append(" AND p.container_location LIKE ?");
                params.add("%" + f.get("containerLocation").getValue() + "%");
            }
        }

        // Count
        String countSql = "SELECT COUNT(*) " + baseFrom + where;
        Query countQuery = entityManager.createNativeQuery(countSql);
        setParams(countQuery, params);
        long totalRecords = ((Number) countQuery.getSingleResult()).longValue();

        // Sort
        String sortClause = " ORDER BY p.name ASC";
        if (dto.getSortField() != null && !dto.getSortField().isBlank()) {
            String col = switch (dto.getSortField()) {
                case "name"          -> "p.name";
                case "price"         -> "p.price";
                case "stockQuantity" -> "p.stock_quantity";
                default              -> "p.name";
            };
            String dir = (dto.getSortOrder() != null && dto.getSortOrder() == -1) ? "DESC" : "ASC";
            sortClause = " ORDER BY " + col + " " + dir;
        }

        // Data
        String dataSql = "SELECT p.id, p.name, p.price, p.stock_quantity, p.category, p.container_location, CASE WHEN p.image_data IS NOT NULL THEN 1 ELSE 0 END AS has_image " + baseFrom + where + sortClause;
        Query dataQuery = entityManager.createNativeQuery(dataSql);
        setParams(dataQuery, params);

        int first = (dto.getFirst() != null && dto.getFirst() >= 0) ? dto.getFirst() : 0;
        int rows  = (dto.getRows()  != null && dto.getRows()  > 0)  ? dto.getRows()  : 25;
        dataQuery.setFirstResult(first);
        dataQuery.setMaxResults(rows);

        List<Object[]> results = dataQuery.getResultList();
        List<PartDto> data = results.stream().map(row -> PartDto.builder()
                .id(((Number) row[0]).longValue())
                .name((String) row[1])
                .price(((Number) row[2]).doubleValue())
                .stockQuantity(((Number) row[3]).intValue())
                .category((String) row[4])
                .containerLocation((String) row[5])
                .hasImage(((Number) row[6]).intValue() == 1)
                .build()
        ).collect(Collectors.toList());

        return PagedPartResponse.builder()
                .data(data)
                .totalRecords(totalRecords)
                .build();
    }

    public PartDto getPartById(Long id) {
        return partRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Part not found"));
    }

    public PartDto updatePart(Long id, PartDto request) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
        part.setName(request.getName());
        part.setPrice(request.getPrice());
        part.setStockQuantity(request.getStockQuantity());
        part.setCategory(request.getCategory());
        part.setContainerLocation(request.getContainerLocation());
        return toDto(partRepository.save(part));
    }

    @Transactional
    public void reduceStock(Long id, Integer quantity) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
        
        if (part.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock for: " + part.getName());
        }
        
        part.setStockQuantity(part.getStockQuantity() - quantity);
        partRepository.save(part);
    }

    @Transactional
    public void restoreStock(Long id, Integer quantity) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
        part.setStockQuantity(part.getStockQuantity() + quantity);
        partRepository.save(part);
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    @Transactional
    public void uploadImage(Long id, MultipartFile file) throws IOException {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
        part.setImageData(file.getBytes());
        part.setImageType(file.getContentType());
        partRepository.save(part);
    }

    @Transactional
    public void deleteImage(Long id) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
        part.setImageData(null);
        part.setImageType(null);
        partRepository.save(part);
    }

    public Part getImage(Long id) {
        return partRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Part not found"));
    }

    // ─── CSV BULK IMPORT ────────────────────────────────────
    @Transactional
    public CsvImportResultDto importPartsFromCsv(MultipartFile file) {
        List<CsvImportResultDto.RowError> errors = new ArrayList<>();
        List<Part> toSave = new ArrayList<>();
        int totalRows = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Skip the header row (first line or if it starts with "name" case-insensitive)
                if (lineNumber == 1 && line.toLowerCase().startsWith("name")) {
                    continue;
                }

                // Skip blank lines
                if (line.isBlank()) continue;

                totalRows++;
                String[] cols = line.split(",", -1);

                // Validate column count — require at least 3, allow up to 5
                if (cols.length < 3) {
                    errors.add(CsvImportResultDto.RowError.builder()
                            .rowNumber(lineNumber)
                            .rowData(line)
                            .reason("Expected at least 3 columns (name, price, stockQuantity) but found " + cols.length)
                            .build());
                    continue;
                }

                String name   = cols[0].trim();
                String priceStr  = cols[1].trim();
                String stockStr  = cols[2].trim();

                // Validate name
                if (name.isEmpty()) {
                    errors.add(CsvImportResultDto.RowError.builder()
                            .rowNumber(lineNumber).rowData(line).reason("Part name is required").build());
                    continue;
                }

                // Validate price
                double price;
                try {
                    price = Double.parseDouble(priceStr);
                    if (price < 0) throw new NumberFormatException("negative");
                } catch (NumberFormatException e) {
                    errors.add(CsvImportResultDto.RowError.builder()
                            .rowNumber(lineNumber).rowData(line)
                            .reason("Invalid price: '" + priceStr + "' — must be a non-negative number").build());
                    continue;
                }

                // Validate stock quantity
                int stock;
                try {
                    stock = Integer.parseInt(stockStr);
                    if (stock < 0) throw new NumberFormatException("negative");
                } catch (NumberFormatException e) {
                    errors.add(CsvImportResultDto.RowError.builder()
                            .rowNumber(lineNumber).rowData(line)
                            .reason("Invalid stock quantity: '" + stockStr + "' — must be a non-negative integer").build());
                    continue;
                }

                // Optional columns
                String category          = (cols.length > 3 && !cols[3].trim().isEmpty()) ? cols[3].trim() : null;
                String containerLocation = (cols.length > 4 && !cols[4].trim().isEmpty()) ? cols[4].trim() : null;

                toSave.add(Part.builder()
                        .name(name)
                        .price(price)
                        .stockQuantity(stock)
                        .category(category)
                        .containerLocation(containerLocation)
                        .build());
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage(), e);
        }

        // Batch save all valid parts
        partRepository.saveAll(toSave);

        return CsvImportResultDto.builder()
                .totalRows(totalRows)
                .importedCount(toSave.size())
                .skippedCount(errors.size())
                .errors(errors)
                .build();
    }

    private PartDto toDto(Part part) {
        return PartDto.builder()
                .id(part.getId())
                .name(part.getName())
                .price(part.getPrice())
                .stockQuantity(part.getStockQuantity())
                .category(part.getCategory())
                .containerLocation(part.getContainerLocation())
                .hasImage(part.getImageData() != null)
                .build();
    }

    // ─── Helpers ───────────────────────────────────────────
    @SuppressWarnings("rawtypes")
    private boolean hasValue(com.example.demo.dto.Filter f) {
        return f != null && f.getValue() != null && !f.getValue().toString().isBlank();
    }

    private void setParams(Query query, List<Object> params) {
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }
    }
}
