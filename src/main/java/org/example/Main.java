package org.example;

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future> futureList = new ArrayList<>();


        final ExecutorService threadPool = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            Future newFuture = threadPool.submit(new GenerateRoute());
            futureList.add(newFuture);
            int result = (int) newFuture.get();

            if (sizeToFreq.containsKey(result)) {
                synchronized (sizeToFreq) {
                    int value = sizeToFreq.get(result);
                    sizeToFreq.replace(result, ++value);
                }
            } else {
                synchronized (sizeToFreq) {
                    sizeToFreq.put(result, 1);
                }
            }
        }
        int maxValue = 0;
        int maxKay= 0;

        for (Map.Entry<Integer, Integer> item : sizeToFreq.entrySet()) {
            if (item.getValue() > maxValue) {
                maxValue = item.getValue();
                maxKay = item.getKey();
            }
        }
        System.out.printf("Самое частое количество повторений %d (встретилось %d раз)\n", maxKay, maxValue);

        System.out.println("Другие размеры:");
        for (Map.Entry<Integer, Integer> item : sizeToFreq.entrySet()) {
            if (item.getKey() == maxKay) {
                continue;
            }
            System.out.printf("- %d (%d раз) \n", item.getKey(), item.getValue());
        }

        threadPool.shutdown();
    }
}