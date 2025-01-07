package com.khoaquang.garbagecollector_memoryleaks.exercise2;

import java.util.ArrayList;
import java.util.List;

/**
 * Behavior:
 *   This program creates a lot of Task objects, and only a small number are removed, while the rest stay in memory.
 *   Over time, the heap memory will fill up, leading to OutOfMemoryError in large-scale applications.
 * How to Fix the Memory Leak:
 *   To prevent the memory leak, ensure that objects are properly dereferenced when no longer needed. For example:
 *   taskList.clear(); // Clears all references in the list
 */
public class MemoryLeaks {
    static class Task {
        private final byte[] data = new byte[1024 * 1024]; // 1 MB data
        private final String name;

        Task(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        System.out.println("Adding tasks to the list...");

        for (int i = 1; i <= 1000; i++) {
            Task task = new Task("Task " + i);
            taskList.add(task);
            System.out.println("Added: " + task);

            // Simulate removing tasks but forget to clean up references
            if (i % 100 == 0) {
                taskList.remove(0); // Removing one task
            }
        }

        System.out.println("Tasks added. Suggesting garbage collection...");

        // Suggest GC
        System.gc();

        // Adding a delay for GC to run
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of program.");
    }
}
