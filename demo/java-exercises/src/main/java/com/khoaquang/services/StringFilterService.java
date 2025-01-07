package com.khoaquang.services;

import com.khoaquang.dtos.FilterType;
import com.khoaquang.interfaces.StringFilterCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StringFilterService {
    private static final Logger log = LoggerFactory.getLogger(StringFilterService.class);

    public List<String> filter(FilterType filterType, List<String> stringList, String filterValue) {
        StringFilterCondition filterCondition;
        switch (filterType) {
            case START_WITH:
                log.info("Filter list \"{}\" start with value \"{}\".", stringList, filterValue);
                filterCondition = (s) -> s.startsWith(filterValue.toLowerCase());
                break;
            case CONTAIN:
                log.info("Filter list \"{}\" contain value \"{}\".", stringList, filterValue);
                filterCondition = (s) -> s.contains(filterValue.toLowerCase());
                break;
            default:
                String errMsg = String.format("Unsupported filter type: %s", filterType);
                log.error(errMsg);
                throw new IllegalArgumentException(errMsg);
        }

        return stringFilter(stringList, filterCondition);
    }

    private static List<String> stringFilter(List<String> stringList, StringFilterCondition filterCondition) {
        List<String> results = new ArrayList<>();
        stringList.forEach(string -> {
            if (filterCondition.filter(string.toLowerCase())) {
                results.add(string);
            }
        });

        log.info("Filter results: \"{}\"", results);
        return results;
    }
}
