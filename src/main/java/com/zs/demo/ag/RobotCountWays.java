package com.zs.demo.ag;

import java.util.Scanner;

public class RobotCountWays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            System.out.println(countWays1(x, y));
        }
    }

    private static int countWays(int x, int y) {
        int[][] dp = new int[x][y];
        for (int i = 1; i < y; i++)
            dp[0][i] = 1;
        for (int i = 1; i < x; i++)
            dp[i][0] = 1;
        for (int i = 1; i < x; i++)
            for (int j = 1; j < y; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        return dp[x - 1][y - 1];
    }


    public static int countWays1(int x, int y) {
        if (x == 1 || y == 1)
            return 1;
        System.out.println(x);
        System.out.println(y);
        return countWays1(x - 1, y) + countWays1(x, y - 1);
    }
}
