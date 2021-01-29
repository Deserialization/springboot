package com.zs.demo.ag;

import java.util.Scanner;

/**
 *          5
 *         7
 *        3 8
 *       8 1 0
 *      2 7 4 4
 *     4 5 2 6 5
 */
public class TriangleSum {
    static int n = 0;
    static int[][] array = null;
    static int[][] maxSum = null;
    public static void main(String[] args) {
        while(true){
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
            array = new int[n + 1][n + 1];
            maxSum = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    array[i][j] = scanner.nextInt();
                    maxSum[i][j] = -1;
                }
            }
            System.out.println(maxSum(1,1));
        }
    }

    private static int maxSum(int x, int y) {
        if(maxSum[x][y] != -1) return maxSum[x][y];
        if (x == n)
            return array[x][y];
        else
            return Math.max(maxSum(x + 1,y),maxSum(x + 1,y + 1)) + array[x][y];
    }

}