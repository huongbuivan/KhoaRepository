package com.khoaquang.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {
    private final CalculatorService calculatorService = new CalculatorService();
    private final double firstNumber = 8;
    private final double secondNumber = 2.5;

    @Test
    public void testAddOperator() {
        assertEquals(10.5, calculatorService.addOperator(firstNumber, secondNumber));
    }

    @Test
    public void testSubOperator() {
        assertEquals(5.5, calculatorService.subOperator(firstNumber, secondNumber));
    }

    @Test
    public void testMulOperator() {
        assertEquals(20, calculatorService.mulOperator(firstNumber, secondNumber));
    }

    @Test
    public void testDivOperator() {
        assertEquals(3.2, calculatorService.divOperator(firstNumber, secondNumber));
    }
}
