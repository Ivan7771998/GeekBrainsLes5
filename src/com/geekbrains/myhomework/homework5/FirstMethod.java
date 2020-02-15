package com.geekbrains.myhomework;

public class FirstMethod implements Runnable {

    private int size;
    private float[] arr;

    public FirstMethod(int size, float[] arr) {
        this.size = size;
        this.arr = arr;
    }

    @Override
    public void run() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время первого метода равно: " + (System.currentTimeMillis() - a) + " миллисекунд");
    }
}
