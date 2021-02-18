package com.zs.demo.ag.sort;

public class selectSort {
    public static void main(String[] args) {
        int[] array = {3, 4, 1, 5, 2};

        //剩余中找最小的
        for (int i = 0; i < array.length; i++) {
            //只为了找最小的
            int minPos = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minPos]) {
                    minPos = j;
                }
            }
            int temp = array[minPos];
            array[minPos] = array[i];
            array[i] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
