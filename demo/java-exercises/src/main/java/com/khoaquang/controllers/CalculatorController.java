package com.khoaquang.controllers;

import com.khoaquang.services.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    private static final Logger log = LoggerFactory.getLogger(CalculatorController.class);

    @PostMapping("/addition")
    public ResponseEntity<Double> add(@RequestParam double firstNumber, @RequestParam double secondNumber) {
        log.info("Handle addition request.");
        return ResponseEntity.ok(calculatorService.addOperator(firstNumber, secondNumber));
    }

    @PostMapping("/subtraction")
    public ResponseEntity<Double> sub(@RequestParam double firstNumber, @RequestParam double secondNumber) {
        log.info("Handle subtraction request.");
        return ResponseEntity.ok(calculatorService.subOperator(firstNumber, secondNumber));
    }

    @PostMapping("/multiplication")
    public ResponseEntity<Double> mul(@RequestParam double firstNumber, @RequestParam double secondNumber) {
        log.info("Handle multiplication request.");
        return ResponseEntity.ok(calculatorService.mulOperator(firstNumber, secondNumber));
    }

    @PostMapping("/division")
    public ResponseEntity<Double> div(@RequestParam double firstNumber, @RequestParam double secondNumber) {
        log.info("Handle division request.");
        if (secondNumber == 0) {
            throw new IllegalArgumentException("The second number cannot be 0.");
        }

        return ResponseEntity.ok(calculatorService.divOperator(firstNumber, secondNumber));
    }
}
