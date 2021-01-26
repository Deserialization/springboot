package com.zs.demo.ag;


import java.util.Scanner;

/**
 * <p>
 * 数列的第一项为n，以后各项为前一项的平方根，求数列的前m项的和。
 * 输入描述:
 * 输入数据有多组，每组占一行，由两个整数n（n < 10000）和m(m < 1000)组成，n和m的含义如前所述。
 * <p>
 * <p>
 * 输出描述:
 * 对于每组输入数据，输出该数列的和，每个测试实例占一行，要求精度保留2位小数。
 */
public class Test_NumberSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            double m = scanner.nextDouble();
            double n = scanner.nextDouble();
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum = sum + m;
                m = Math.pow(m, 0.5);
            }
            System.out.println(String.format("%.2f", sum));//保证小数掉过后两位有效数字
        }
    }
}
