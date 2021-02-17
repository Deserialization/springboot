package com.zs.demo.ag.hw;

import java.util.Scanner;

public class lastWordLength {


    /**
     * 字符串最后一个单词的长度
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String st = scanner.nextLine();
        String[] arr = st.split("\\s+");

        int n = arr[arr.length - 1].length();
        System.out.println(n);

    }
}

