package ru.nikolnikova.MaxN;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.PriorityQueue;


public class ExcelReader {
    public PriorityQueue<Integer> read(String filePath) throws IOException {
        Workbook workbook = loadWorkbook(filePath);
        Sheet sheet = workbook.sheetIterator().next();

        return processSheet(sheet);
    }

    private Workbook loadWorkbook(String filePath) throws IOException {
        var extension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
        var file = new FileInputStream(filePath);

        return switch (extension) {
            case "xls" -> new HSSFWorkbook(file);
            case "xlsx" -> new XSSFWorkbook(file);
            default -> throw new RuntimeException("Unknown Excel file extension: " + extension);
        };
    }

    private PriorityQueue<Integer> processSheet(Sheet sheet) {
        PriorityQueue<Integer> data = new PriorityQueue<>();
        var iterator = sheet.rowIterator();

        while (iterator.hasNext()) {
            var row = iterator.next();
            processCell(row.getCell(0), data);
        }

        return data;
    }

    private void processCell(Cell cell, PriorityQueue<Integer> dataRow) {
        if (Objects.requireNonNull(cell.getCellType()) == CellType.NUMERIC && !DateUtil.isCellDateFormatted(cell)) {
            dataRow.add((int) cell.getNumericCellValue());
        }
    }
}