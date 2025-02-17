package ru.nikolnikova.MaxN.service;

import org.springframework.stereotype.Service;
import ru.nikolnikova.MaxN.ExcelReader;

import java.io.IOException;
import java.util.PriorityQueue;

@Service
public class NumberService {
    public Integer findMaxNumber(String filePath, int n) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        PriorityQueue<Integer> numbers = excelReader.read(filePath);
        int size = numbers.size();

        if (size < n) {
            throw new RuntimeException("There are less than " + n + " numbers in the file");
        }

        for (int i = 0; i < size - n; i++) {
            numbers.poll();
        }

        return numbers.poll();
    }
}