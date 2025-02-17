package ru.nikolnikova.MaxN.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nikolnikova.MaxN.service.NumberService;

import java.io.IOException;

@RestController
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping("/max-number")
    @Operation(summary = "Поиск максимального N-го числа", description = "Возвращает N-е максимальное число из предоставленного файла Excel (XLSX)")
    public ResponseEntity<Integer> getMaxNumber(@RequestParam String filePath, @RequestParam int n) throws IOException {
        return new ResponseEntity<>(numberService.findMaxNumber(filePath, n), HttpStatus.OK);
    }
}