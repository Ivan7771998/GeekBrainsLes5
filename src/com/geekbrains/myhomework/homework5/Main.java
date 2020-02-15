package com.geekbrains.myhomework;

public class Main {

    public static void main(String[] args) {

        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        FirstMethod firstMethod = new FirstMethod(size, arr);
        Thread methodFirstThread = new Thread(firstMethod);
        methodFirstThread.start();
        try {
            methodFirstThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SecondMethod secondMethod = new SecondMethod(size, arr, h);
        secondMethod.runSecondMethod();
    }
}
