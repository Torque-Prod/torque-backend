package com.example.demo.service;

import com.example.demo.dto.RepairJobDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelExportService {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String[] HEADERS = {
        "Job Number", "Customer", "Phone",
        "Item Name", "Model Number", "Serial Number",
        "Problem Description", "Technician",
        "Received Date", "Expected Date",
        "Status", "Service Charge (Rs.)", "Parts Total (Rs.)",
        "Net Total (Rs.)", "Paid"
    };

    public byte[] exportRepairJobs(List<RepairJobDto> jobs) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Repair Jobs");

            // ── Styles ───────────────────────────────────────────
            CellStyle titleStyle   = createTitleStyle(workbook);
            CellStyle headerStyle  = createHeaderStyle(workbook);
            CellStyle dateStyle    = createDateCellStyle(workbook);
            CellStyle currencyStyle = createCurrencyStyle(workbook);
            CellStyle altRowStyle  = createAltRowStyle(workbook);
            CellStyle normalStyle  = createNormalStyle(workbook);
            CellStyle paidStyle    = createPaidStyle(workbook);
            CellStyle unpaidStyle  = createUnpaidStyle(workbook);

            // ── Title row ────────────────────────────────────────
            Row titleRow = sheet.createRow(0);
            titleRow.setHeightInPoints(28);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Torque — Repair Jobs Export");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, HEADERS.length - 1));

            // ── Subtitle / generated date row ────────────────────
            Row subRow = sheet.createRow(1);
            subRow.setHeightInPoints(16);
            Cell subCell = subRow.createCell(0);
            subCell.setCellValue("Generated: " + LocalDate.now().format(DATE_FMT) + "   |   Total records: " + jobs.size());
            CellStyle subStyle = workbook.createCellStyle();
            Font subFont = workbook.createFont();
            subFont.setItalic(true);
            subFont.setColor(IndexedColors.GREY_50_PERCENT.getIndex());
            subFont.setFontHeightInPoints((short) 10);
            subStyle.setFont(subFont);
            subCell.setCellStyle(subStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, HEADERS.length - 1));

            // ── Blank separator row ──────────────────────────────
            sheet.createRow(2).setHeightInPoints(6);

            // ── Header row ───────────────────────────────────────
            Row headerRow = sheet.createRow(3);
            headerRow.setHeightInPoints(22);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HEADERS[i]);
                cell.setCellStyle(headerStyle);
            }

            // ── Data rows ────────────────────────────────────────
            int rowNum = 4;
            for (int idx = 0; idx < jobs.size(); idx++) {
                RepairJobDto job = jobs.get(idx);
                Row row = sheet.createRow(rowNum++);
                row.setHeightInPoints(18);

                boolean alt = idx % 2 != 0;
                CellStyle base = alt ? altRowStyle : normalStyle;

                writeCell(row, 0, job.getJobNumber(),                                    base);
                writeCell(row, 1, job.getCustomerName(),                                 base);
                writeCell(row, 2, job.getCustomerPhone(),                                base);
                writeCell(row, 3, job.getItemName(),                                     base);
                writeCell(row, 4, job.getModelNumber(),                                  base);
                writeCell(row, 5, job.getSerialNumber(),                                 base);
                writeCell(row, 6, job.getProblemDescription(),                           base);
                writeCell(row, 7, job.getTechnicianName(),                               base);
                writeDateCell(row, 8,  job.getReceivedDate(),  dateStyle);
                writeDateCell(row, 9,  job.getExpectedDate(),  dateStyle);
                writeCell(row, 10, job.getStatus(),                                      base);
                writeNumericCell(row, 11, job.getServiceCharge() != null ? job.getServiceCharge() : 0.0, currencyStyle);
                writeNumericCell(row, 12, job.getPartsTotal()    != null ? job.getPartsTotal()    : 0.0, currencyStyle);
                writeNumericCell(row, 13, job.getNetTotal()      != null ? job.getNetTotal()      : 0.0, currencyStyle);

                // Paid column — styled Yes/No
                Cell paidCell = row.createCell(14);
                boolean paid  = job.isPaid();
                paidCell.setCellValue(paid ? "Yes" : "No");
                paidCell.setCellStyle(paid ? paidStyle : unpaidStyle);
            }

            // ── Freeze header ────────────────────────────────────
            sheet.createFreezePane(0, 4);

            // ── Auto-size columns ────────────────────────────────
            for (int i = 0; i < HEADERS.length; i++) {
                sheet.autoSizeColumn(i);
                // add a little padding
                int currentWidth = sheet.getColumnWidth(i);
                sheet.setColumnWidth(i, Math.min(currentWidth + 1024, 20000));
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }

    // ── Cell writers ─────────────────────────────────────────────

    private void writeCell(Row row, int col, String value, CellStyle style) {
        Cell cell = row.createCell(col);
        cell.setCellValue(value != null ? value : "");
        cell.setCellStyle(style);
    }

    private void writeDateCell(Row row, int col, LocalDate date, CellStyle style) {
        Cell cell = row.createCell(col);
        cell.setCellValue(date != null ? date.format(DATE_FMT) : "");
        cell.setCellStyle(style);
    }

    private void writeNumericCell(Row row, int col, double value, CellStyle style) {
        Cell cell = row.createCell(col);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    // ── Style factories ──────────────────────────────────────────

    private CellStyle createTitleStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setLeftBorderColor(IndexedColors.ROYAL_BLUE.getIndex());
        return style;
    }

    private CellStyle createHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        return style;
    }

    private CellStyle createNormalStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        return style;
    }

    private CellStyle createAltRowStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.WHITE.getIndex());
        return style;
    }

    private CellStyle createDateCellStyle(Workbook wb) {
        CellStyle style = createNormalStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private CellStyle createCurrencyStyle(Workbook wb) {
        CellStyle style = createNormalStyle(wb);
        DataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("#,##0.00"));
        style.setAlignment(HorizontalAlignment.RIGHT);
        return style;
    }

    private CellStyle createPaidStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);
        font.setColor(IndexedColors.DARK_GREEN.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        return style;
    }

    private CellStyle createUnpaidStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 10);
        font.setColor(IndexedColors.RED.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        return style;
    }
}
