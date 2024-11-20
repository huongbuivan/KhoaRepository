package com.khoaquang.services;

import com.khoaquang.dtos.FilterType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringFilterServiceTest {
    private final StringFilterService stringFilterService = new StringFilterService();

    @Test
    public void testFilterStartWith() {
        List<String> stringList = Arrays.asList("Nam", "Hoa", "Hien", "Hai");
        String filterValue = "h";
        List<String> expectedResult = Arrays.asList("Hoa","Hien","Hai");

        assertEquals(expectedResult, stringFilterService.filter(FilterType.START_WITH, stringList, filterValue));
    }

    @Test
    public void testFilterContain() {
        List<String> stringList = Arrays.asList("Nam", "Tien", "Hien", "Hiep");
        String filterValue = "ie";
        List<String> expectedResult = Arrays.asList("Tien","Hien","Hiep");

        assertEquals(expectedResult, stringFilterService.filter(FilterType.CONTAIN, stringList, filterValue));
    }

    @Test
    public void testUnknownFilterType() {
        List<String> stringList = Arrays.asList("Nam", "Tien", "Hien", "Hiep");
        String filterValue = "ie";

        assertThrows(IllegalArgumentException.class, () -> stringFilterService.filter(FilterType.LENGTH, stringList, filterValue));
    }
}