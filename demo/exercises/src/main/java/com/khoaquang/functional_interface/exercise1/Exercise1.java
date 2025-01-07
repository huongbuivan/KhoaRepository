package com.khoaquang.functional_interface.exercise1;

public class Exercise1 {
    public static void main(String[] args) {
        Calculator addOperator = (a, b) -> a + b;
        Calculator subOperator = (a, b) -> a - b;
        Calculator multipleOperator = (a, b) -> a * b;

        double a = 1000;
        double b = 900;
        System.out.println("Addition result: " + addOperator.operator(a, b));
        System.out.println("Subtraction result: " + subOperator.operator(a, b));
        System.out.println("Multiplication result: " + multipleOperator.operator(a, b));
    }
}