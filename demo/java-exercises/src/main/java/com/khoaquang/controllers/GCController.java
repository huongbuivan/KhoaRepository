package com.khoaquang.controllers;

import com.khoaquang.services.GCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gc")
public class GCController {
    private static final Logger log = LoggerFactory.getLogger(GCController.class);

    @Autowired
    private GCService gcService;

    @GetMapping("/garbage-collection")
    public ResponseEntity<String> garbageCollectionSimulation() {
        log.info("Handle simulate garbage collection request.");
        gcService.simulateGarbageCollection();
        return ResponseEntity.ok("Finished simulating garbage collection.");
    }
}
