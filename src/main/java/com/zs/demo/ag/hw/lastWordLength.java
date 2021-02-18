package com.zs.demo.ag.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class lastWordLength {


    public static void main(String[] args) {

        //keyAndValue();
        //addAndSub();
        //divNumber();
        //transNum();
        //splitString();
        //lastWordLength();
    }

    /**
     * 数据表记录包含表索引和数值（int范围的正整数），请对表索引相同的记录进行合并，
     * 即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
     */
    private static void keyAndValue() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int i = Integer.parseInt(in.readLine());
            Map<Integer, Integer> tm = new TreeMap<Integer, Integer>();

            for (int j = 0; j < i; j++) {
                String s = in.readLine();
                String[] str = s.split(" ");
                int key = Integer.parseInt(str[0]);
                int value = tm.containsKey(key) ? Integer.parseInt(str[1]) + tm.get(key) : Integer.parseInt(str[1]);
                tm.put(key, value);
            }
            for (Integer k : tm.keySet()) {
                System.out.println(k + " " + tm.get(k));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addAndSub() {
        /**
         * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。
         * 如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
         */
        Scanner scanner = new Scanner(System.in);
        double number = scanner.nextDouble();
        int i = (int) (number);
        System.out.println((number - i) >= 0.5 ? i + 1 : i);
    }

    /**
     * 输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）
     * （如180的质因子为2 2 3 3 5 ）
     */
    private static void divNumber() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sbBuilder = new StringBuilder();

        long num = scanner.nextLong();

        long t = num;
        for (int i = 2; i <= t; i++) {
            while (num % i == 0) {
                num = num / i;
                sbBuilder.append(i);
                sbBuilder.append(" ");
            }
        }
        System.out.println(sbBuilder.toString());
    }

    /**
     * 16进制转10进制
     */
    private static void transNum() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next().substring(2);
            System.out.println(Integer.parseInt(str, 16));
        }
    }

    private static void splitString() {
        /**
         * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
         •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
         */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String st = scanner.nextLine();
            while (st.length() >= 8) {
                System.out.println(st.substring(0, 8));
                st = st.substring(8);
            }
            if (st.length() < 8 && st.length() > 0) {
                st = st + "00000000";
                System.out.println(st.substring(0, 8));
            }

            System.out.println(st);
        }
    }


    /**
     * 字符串最后一个单词的长度s
     */
    private static void lastWordLength() {
        Scanner scanner = new Scanner(System.in);
        String st = scanner.nextLine();
        String[] arr = st.split("\\s+");

        int n = arr[arr.length - 1].length();
        System.out.println(n);
    }
}

