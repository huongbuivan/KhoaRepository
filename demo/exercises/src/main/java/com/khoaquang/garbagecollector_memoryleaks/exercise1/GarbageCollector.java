package com.khoaquang.garbagecollector_memoryleaks.exercise1;

public class GarbageCollector {
    // Overriding finalize method to observe garbage collection
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Garbage Collector invoked and object collected: " + this);
    }

    public static void main(String[] args) {
        // Creating objects inside a scope
        System.out.println("Creating objects...");
        for (int i = 1; i <= 5; i++) {
            GarbageCollector obj = new GarbageCollector();
            System.out.println("Created object: " + obj);
        }

        // Suggesting JVM to run Garbage Collection
        System.out.println("\nSuggesting Garbage Collection...");
        System.gc(); // Suggest garbage collection
        // Adding a delay to give GC time to run

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of main method.");
    }
}
