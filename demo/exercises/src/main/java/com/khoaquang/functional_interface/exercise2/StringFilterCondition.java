package com.khoaquang.functional_interface.exercise2;

@FunctionalInterface
public interface StringFilterCondition {
    boolean filter(String string);
}
