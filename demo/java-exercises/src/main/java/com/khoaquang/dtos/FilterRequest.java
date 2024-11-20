package com.khoaquang.dtos;

import java.util.List;

public class FilterRequest {
    private List<String> stringList;
    private String filterValue;

    public List<String> getStringList() {
        return this.stringList;
    }

    public String getFilterValue() {
        return this.filterValue;
    }
}
