package org.example;

import java.util.Map;

public class FindMax extends Thread {
    Map<Integer, Integer> sizeToFreq;

    FindMax(Map<Integer, Integer> sizeToFreq) {
        this.sizeToFreq = sizeToFreq;
    }

    @Override
    public void run() {
        synchronized (sizeToFreq) {

            while (!Thread.interrupted()) {
                try {
                    sizeToFreq.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int maxValue = 0;
                int maxKay = 0;

                for (Map.Entry<Integer, Integer> item : sizeToFreq.entrySet()) {
                    if (item.getValue() > maxValue) {
                        maxValue = item.getValue();
                        maxKay = item.getKey();
                    }
                }
                System.out.println("Максимальное значение в мапе " + maxValue);
            }
        }
    }
}
