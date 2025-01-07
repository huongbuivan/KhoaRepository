package com.khoaquang.controllers;

import com.khoaquang.dtos.FilterRequest;
import com.khoaquang.dtos.FilterType;
import com.khoaquang.services.StringFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("string-filter")
public class StringFilterController {
    @Autowired
    private StringFilterService stringFilterService;

    private static final Logger log = LoggerFactory.getLogger(StringFilterController.class);

    @PostMapping("/start-with")
    public ResponseEntity<List<String>> filterStringStartWith(@RequestBody(required = false) FilterRequest filterRequest) {
        log.info("Handle filter-string-start-with request.");
        validateRequestPayload(filterRequest);
        return ResponseEntity.ok(stringFilterService.filter(FilterType.START_WITH, filterRequest.getStringList(), filterRequest.getFilterValue()));
    }

    @PostMapping("/contain")
    public ResponseEntity<List<String>> filterStringContain(@RequestBody(required = false) FilterRequest filterRequest) {
        log.info("Handle filter-string-contain request.");
        validateRequestPayload(filterRequest);
        return ResponseEntity.ok(stringFilterService.filter(FilterType.CONTAIN, filterRequest.getStringList(), filterRequest.getFilterValue()));
    }

    private void validateRequestPayload(FilterRequest filterRequest) {
        log.info("Start validating filter request payload.");
        if (filterRequest == null) {
            throw new IllegalArgumentException("Filter request payload cannot be null.");
        }

        if (filterRequest.getStringList() == null || filterRequest.getStringList().isEmpty()) {
            throw new IllegalArgumentException("The string list cannot be null/empty.");
        }

        if (!StringUtils.hasText(filterRequest.getFilterValue())) {
            throw new IllegalArgumentException("The filter value cannot be null/empty.");
        }
    }
}
