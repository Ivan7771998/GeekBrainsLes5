package com.geekbrains.myhomework;

public class SecondMethod {

    private int size;
    private float[] arr;
    private int h;

    public SecondMethod(int size, float[] arr, int h) {
        this.size = size;
        this.arr = arr;
        this.h = h;
    }

    public void runSecondMethod() {

        long a = System.currentTimeMillis();
        float[] arrCopy1 = new float[h];
        float[] arrCopy2 = new float[h];
        System.arraycopy(arr, 0, arrCopy1, 0, h);
        System.arraycopy(arr, h, arrCopy2, 0, h);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = h; i < size; i++) {
                    arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arrCopy1, 0, arr, 0, h);
        System.arraycopy(arrCopy2, 0, arr, h, h);
        System.out.println("Время второго метода равно: " + (System.currentTimeMillis() - a) + " миллисекунд");
    }
}
