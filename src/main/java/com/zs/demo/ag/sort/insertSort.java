package com.zs.demo.ag.sort;

public class insertSort {
    public static void main(String[] args) {

        int[] array = {3, 4, 1, 5, 2};
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
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
