package com.zs.demo.ag.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class lastWordLength {


    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while ((input = bufferedReader.readLine()) != null) {
            int count=0;
            int num=Integer.parseInt(input);
            for(int i=7;i<=num;i++){
                if(i%7==0||contain7(i)){
                    count++;
                }
            }
            System.out.println(count);
        }
        //countAction();
        //rabbit();
        //highMeter();
        //brotherWord();
        //systemSort();

        //deleteLettleChar();
        //wads();
        //countMemory();
        //sortString();
        //reverseSentence();


        //convert();
        //countWords();

        ///reverseSort();
        //keyAndValue();
        //addAndSub();
        //divNumber();
        //transNum();
        //splitString();
        //lastWordLength();
    }

    public static boolean contain7(int n) {
        while (n > 0) {
            if (n % 10 == 7) {
                return true;
            } else {
                n /= 10;
            }
        }
        return false;
    }

    private static void countAction() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String string = scanner.nextLine();
            for (int i = 0; i < 4; i++) {
                System.out.println(getNumbers(string)[i]);
            }
        }
        scanner.close();
    }

    public static int[] getNumbers(String string) {
        int[] n = new int[4];
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if((c >= 'a'&& c <='z')||(c >= 'A'&& c <='Z')){
                n[0]++;
            }else if (c == ' ') {
                n[1]++;
            }else if (c >= '0'&& c <='9') {
                n[2]++;
            }else {
                n[3]++;
            }
        }

        return n;
    }
    private static void rabbit() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int n = sc.nextInt();
            //int m = count(n);
            int m =count(n);
            System.out.println(m);
        }
    }

    private static int count(int n) {
        if(n==1||n==2) {
            return 1;
        }
        return count(n-1)+count(n-2);
    }
    private static void highMeter() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int val = in.nextInt();
            double current = val, sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += current;
                current = current / 2;
                sum += current;
            }
            sum -= current;
            System.out.println(sum);
            System.out.println(current);
        }
    }

    private static double calculate(double high) {
        return high / 2;
    }

    private static void brotherWord() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            char[] arr = str.toCharArray();
            StringBuilder builder = new StringBuilder();
            // 英文字母从 A 到 Z 排列，不区分大小写：26 个
            for (int i = 0; i < 26; i++) {
                char c = (char) ('A' + i);
                // 遍历字符串
                for (int j = 0, length = str.length(); j < length; j++) {
                    // 不区分大小写
                    if (c == arr[j] || c == arr[j] - 'a' + 'A') {
                        builder.append(arr[j]);
                    }
                }
            }
            // 非英文字母的其它字符保持原来的位置
            for (int i = 0, length = str.length(); i < length; i++) {
                if (!((arr[i] >= 'A' && arr[i] <= 'Z') || (arr[i] >= 'a' && arr[i] <= 'z'))) {
                    builder.insert(i, arr[i]);
                }
            }
            System.out.println(builder.toString());
        }
    }

    private static void systemSort() {
        Scanner sca = new Scanner(System.in);
        while (sca.hasNext()) {
            String str = sca.nextLine();
            char[] cha = str.toCharArray();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < 26; i++) {
                char c = (char) (i + 'A');
                for (int j = 0; j < str.length(); j++) {
                    if (cha[j] == c || cha[j] == (char) (c + 32))
                        sb.append(cha[j]);
                }
            }

            for (int k = 0; k < str.length(); k++) {
                if (!(cha[k] >= 'A' && cha[k] <= 'Z' || cha[k] >= 'a' && cha[k] <= 'z'))
                    sb.insert(k, cha[k]);
            }
            System.out.println(sb.toString());
        }
    }

    private static void deleteLettleChar() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String string = sc.nextLine();
            char[] A = string.toCharArray();
            Map<Character, Integer> m = new LinkedHashMap<Character, Integer>();

            for (char c : A) {
                if (!m.containsKey(c)) {
                    m.put(c, 1);
                } else {
                    m.put(c, m.get(c) + 1);
                }
            }
            Collection<Integer> al = m.values();
            int index = Collections.min(al);
            StringBuffer sb = new StringBuffer("");
            for (char c : A) {
                if (m.get(c) != index)
                    sb.append(c);
            }
            System.out.println(sb.toString());

        }
    }

    /**
     * 上下左右移动
     */
    private static void wads() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            int x = 0;
            int y = 0;
            while ((str = reader.readLine()) != null) {
                String[] split = str.split(";");
                for (int i = 0; i < split.length; i++) {
                    char[] chars = split[i].toCharArray();
                    int length = 0;
                    if (chars.length > 3) {
                        continue;
                    }
                    for (int j = 1; j < chars.length; j++) {
                        if (chars[j] >= '0' && chars[j] <= '9') {
                            length = length * 10 + chars[j] - '0';
                        } else {
                            length = 0;
                            break;
                        }
                    }
                    switch (chars[0]) {
                        case 'A':
                            x -= length;
                            break;
                        case 'S':
                            y -= length;
                            break;
                        case 'W':
                            y += length;
                            break;
                        case 'D':
                            x += length;
                    }
                }
                System.out.println(x + "," + y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
     */
    private static void countMemory() {
        Scanner scanner = new Scanner(System.in);
        int j = scanner.nextInt();
        String string = Integer.toBinaryString(j);

        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '1') {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * 输入第一行为一个正整数n(1≤n≤1000),
     * 下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
     */
    private static void sortString() {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> set = new ArrayList<String>();
        int num = scan.hasNextLine() ? Integer.parseInt(scan.nextLine()) : 0;
        while (--num >= 0) {
            set.add(scan.nextLine());
        }
        Collections.sort(set);
        for (String str : set) {
            System.out.println(str);
        }
    }

    /**
     * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
     * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
     */
    private static void reverseSentence() {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();

        String[] s = sentence.split("\\s+");
        StringBuffer buffer = new StringBuffer();
        for (int i = s.length - 1; i >= 0; i--) {
            buffer.append(s[i] + " ");
        }
        System.out.println(buffer.toString());
    }

    /**
     * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
     */
    private static void convert() {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        for (int i = string.length() - 1; i >= 0; i--) {
            System.out.print(string.charAt(i));
        }
    }

    /**
     * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，
     * 换行表示结束符，不算在字符里。不在范围内的不作统计。多个相同的字符只计算一次
     * 例如，对于字符串abaca而言，有a、b、c三种不同的字符，因此输出3。
     */
    private static void countWords() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();

        int count = 0;
        String[] arr = new String[128];
        for (int i = 0; i < string.length(); i++) {
            int ch = string.charAt(i);
            if (string.charAt(i) > 0 && string.charAt(i) < 127 && arr[ch] != "1") {
                //若是ASCII码范围内的字符,arr中1表示已计数过
                count++;
                arr[ch] = "1";
            }
        }
        System.out.println(count);
    }

    private static void reverseSort() {
        /**
         * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
         保证输入的整数最后一位不是0。
         */
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = reader.readLine();
            StringBuilder builder = new StringBuilder();
            int[] count = new int[10];
            for (int i = str.length() - 1; i >= 0; i--) {
                if (count[Integer.parseInt(str.substring(str.length() - 1))] == 0) {
                    builder.append(Integer.parseInt(str.substring(str.length() - 1)));
                    count[Integer.parseInt(str.substring(str.length() - 1))]++;
                }
                str = str.substring(0, str.length() - 1);
            }
            System.out.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据表记录包含表索引和数值（int范围的正整数），请对表索引相同的记录进行合并，
     * 即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
     */
    private static void keyAndValue() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int i = Integer.parseInt(in.readLine());
            Map<Integer, Integer> tm = new TreeMap<>();
            for (int j = 0; j < i; j++) {
                String s = in.readLine();
                String[] str = s.split("\\s+");
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

