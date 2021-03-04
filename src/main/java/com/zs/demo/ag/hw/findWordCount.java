package com.zs.demo.ag.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class findWordCount {
    /**
     * 写出一个程序，接受一个由字母、数字和空格组成的字符串，
     * 和一个字母，然后输出输入字符串中该字母的出现次数。不区分大小写。
     */
    /*public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1=br.readLine().toLowerCase();
        char c=br.readLine().toLowerCase().charAt(0);
        char[] chars = s1.toCharArray();
        int times =0;
        for (char ch : chars) {
            if (c==ch){
                times++;
            }
        }
        System.out.println(times);
    }*/
    public static void main(String[] args) {
        String a = "A000B0000";

        System.out.println(removeZeros(a, 4));
        /*Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().toUpperCase();
        char target = scanner.nextLine().toUpperCase().toCharArray()[0];
        int n = getCount(str, target);
        System.out.println(n);*/
    }

    private static int getCount(String str, char target) {
        // TODO Auto-generated method stub

        int count = 0;
        if (str != null && str.length() > 0) {
            for (int i = 0; i < str.length(); i++) {
                if (target == str.charAt(i)) {//遍历string里面的字符就可以找出来
                    count++;
                }
            }
        } else {
            count = 0;
        }
        return count;
    }


    private static String removeZeros(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        char[] chas = str.toCharArray();
        int count = 0;
        int start = -1;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            } else {
                count = 0;
                start = -1;

            }
            if (count == k) {
                while (count-- != 0) {
                    chas[start++] = 0;
                }
            }

        }
        return String.valueOf(chas);
    }
}
