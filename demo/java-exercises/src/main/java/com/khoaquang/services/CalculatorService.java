package com.khoaquang.services;

import com.khoaquang.interfaces.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private static final Logger log = LoggerFactory.getLogger(CalculatorService.class);

    public double addOperator(double firstNumber, double secondNumber) {
        Calculator add = Double::sum;
        double res = add.operator(firstNumber, secondNumber);

        log.info("Start addition operator with first number {} and second number {}. Result: {}.", firstNumber, secondNumber, res);
        return res;
    }

    public double subOperator(double firstNumber, double secondNumber) {
        Calculator sub = (a, b) -> a - b;
        double res = sub.operator(firstNumber, secondNumber);

        log.info("Start subtraction operator with first number {} and second number {}. Result: {}.", firstNumber, secondNumber, res);
        return res;
    }

    public double mulOperator(double firstNumber, double secondNumber) {
        Calculator mul = (a, b) -> a * b;
        double res = mul.operator(firstNumber, secondNumber);

        log.info("Start multiplication operator with first number {} and second number {}. Result: {}.", firstNumber, secondNumber, res);
        return res;
    }

    public double divOperator(double firstNumber, double secondNumber) {
        Calculator mul = (a, b) -> a / b;
        double res = mul.operator(firstNumber, secondNumber);

        log.info("Start division operator with first number {} and second number {}. Result: {}.", firstNumber, secondNumber, res);
        return res;
    }
}
