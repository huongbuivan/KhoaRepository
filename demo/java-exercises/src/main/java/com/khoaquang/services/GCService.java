package com.khoaquang.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Key Observations
 * 1. Memory Growth:
 * • As objects are created, used memory increases until it approaches the JVM’s allocated heap size.
 * 2. Memory Cleanup:
 * • Removing objects (clear()) frees up memory, but it may not immediately show until garbage collection occurs.
 * 3. Garbage Collection:
 * • After suggesting GC with System.gc(), memory usage drops as the JVM reclaims memory for unreachable objects.
 *
 * Memory leaks' behavior:
 *   This program creates a lot of 'LargeObject' objects, and only a small number are removed, while the rest stay in memory.
 *   Over time, the heap memory will fill up, leading to OutOfMemoryError in large-scale applications.
 * How to fix the memory leak:
 *   To prevent the memory leak, ensure that objects are properly dereferenced when no longer needed. For example:
 *   largeObjectList.clear(); // Clears all references in the list
 */
@Service
public class GCService {
    private static final Logger log = LoggerFactory.getLogger(GCService.class);

    static class LargeObject {
        private final byte[] data = new byte[1024 * 1024]; // 1 MB size for each object
    }

    public void simulateGarbageCollection() {
        List<LargeObject> largeObjectList = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        log.info("Initial Memory Usage:");
        printMemoryUsage(runtime);

        // Creating a large number of objects
        for (int i = 0; i < 1000; i++) {
            largeObjectList.add(new LargeObject());
            if ((i + 1) % 100 == 0) {
                log.info("Created {} objects", i + 1);
                printMemoryUsage(runtime);
            }

            // Simulating memory cleanup
            if ((i + 1) % 200 == 0) {
                largeObjectList.subList(0, 100).clear(); // Remove some objects
                log.info("Removed 100 objects.");
                printMemoryUsage(runtime);
            }
        }

        // Suggesting garbage collection
        log.info("\nSuggesting Garbage Collection...");
        System.gc();

        // Allow some time for GC to execute
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("After Garbage Collection:");
        printMemoryUsage(runtime);
    }

    private static void printMemoryUsage(Runtime runtime) {
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.printf("Total Memory: %d MB, Free Memory: %d MB, Used Memory: %d MB%n",
                totalMemory / (1024 * 1024), freeMemory / (1024 * 1024), usedMemory / (1024 * 1024));
    }
}
