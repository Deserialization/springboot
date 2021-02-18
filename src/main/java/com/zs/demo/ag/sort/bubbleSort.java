package com.zs.demo.ag.sort;

public class bubbleSort {
    public static void main(String[] args) {
        int[] array = {3, 4, 1, 5, 2};

        for (int i = array.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        print(array);

    }

    private static void swap(int[] array, int i, int minPos) {
        int temp = array[minPos];
        array[minPos] = array[i];
        array[i] = temp;
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
