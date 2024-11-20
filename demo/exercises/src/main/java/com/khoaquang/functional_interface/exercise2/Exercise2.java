package com.khoaquang.functional_interface.exercise2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise2 {
    public static void main(String[] args) {
        List<String> exampleString = Arrays.asList("Test", "Endava", "DEK", "VTD", "MYD", "5years");
        StringFilterCondition length = (s) -> s.length() > 3;
        StringFilterCondition startWith = (s) -> s.startsWith("e");
        StringFilterCondition contain = (s) -> s.contains("d");

        System.out.println("Texts with length > 3: " + stringFilter(exampleString, length));
        System.out.println("Texts start with character 'e': " + stringFilter(exampleString, startWith));
        System.out.println("Texts contain character 'd': " + stringFilter(exampleString, contain));
    }

    public static List<String> stringFilter(List<String> stringList, StringFilterCondition filterCondition) {
        List<String> results = new ArrayList<>();
        stringList.forEach(string -> {
            if (filterCondition.filter(string.toLowerCase())) {
                results.add(string);
            }
        });

        return results;
    }
}
