package com.zs.demo.ag.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class lastWordLength {


    public static void main(String[] args) throws Exception {

        //sub();
        //judgeIp();
        //togetherOne();
        //leftAndRight();
        //findBigChar();
        //replaceProgram();
        //day();
        //pot24();
        //commad();
        //perGC();
        //binaryOne();
        //leastTwo();
        //nextInsert();

        //substringSix();
        //beautyNum();
        //notDeadRabbit();
        //asicSort();


        //prefectReverse();
        //replaceChar();
        //replaceStar();
        //addStar();

        //endsWith();
        //sum();

        //quickSort();

        //dpApple();


        //check7();
        //countAction();
        //rabbit();
        //highMeter();
        //brotherWord();
        //systemSort();


        //password();

        //password1();

        //password2();

        //drink();

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
        //secretPalace();

        //killChild();
}

    /**
     * 输入一个字符串，返回其最长的数字子串，以及其长度。若有多个最长的数字子串，则将它们全部输出（按原字符串的相对位置）
     本题含有多组样例输入
     */
    private static void sub() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            int len = str.length();
            int max = 0;
            int dp[] = new int[len];
            int flag =0;
            //动态规划边界
            if (str.charAt(0)>='0'&&str.charAt(0)<='9') {

                dp[0]=1;
            }
            for (int i = 1; i < len; i++) {
                if (str.charAt(i)>='0'&&str.charAt(i)<='9') {
                    dp[i]=dp[i-1]+1;
                }else {
                    dp[i]=0;
                }
            }
            for (int i = 0; i < dp.length; i++) {
                if (dp[i]>max) {
                    max = dp[i];
                    flag = i;
                }
            }
            String temp = "";
            for (int i = 0; i < dp.length; i++) {
                if (dp[i]==max) {
                    temp+=str.substring(i-max+1,i+1);
                }
            }
            //System.out.println(str.substring(flag-max+1,flag+1)+","+max);
            System.out.println(temp+","+max);
        }
    }


    /**
     * 请计算n*m的棋盘格子（n为横向的格子数，m为竖向的格子数）沿着各自边缘线从左上角走到右下角，总共有多少种走法，
     * 要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
     * @param n
     * @param m
     * @return
     */
    public static int getCount(int n,int m) {
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<n+1;i++) {
            for(int j=0;j<m+1;j++) {
                if(i==0||j==0){
                    // 边上的每一个点的可能性都是1，因为走到边上，就只能顺着边往下走了，可能性只能是1
                    dp[i][j]=1;
                }else{
                    // 往后每一个点，都是后面两个点的可能性之和，因为它可以选择任意一个点来走，可能性就是下两个点的可能性相加
                    dp[i][j]=dp[i][j-1]+dp[i-1][j];
                }
            }
        }
        // 最终加到最后一个点，可能性就是所有的路线数量
        return dp[n][m];
    }
    private static void judgeIp() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while((str = bf.readLine()) != null){

            String[] subIP = str.split("\\.");

            for(int i =0; i < subIP.length; i++){
                Integer intIP = Integer.valueOf(subIP[i]);
                if(intIP >= 0 && intIP<=255){
                    if(i == subIP.length-1){
                        System.out.println("YES");
                    }
                    continue;
                } else{
                    System.out.println("NO");
                    break;
                }
            }
        }
    }

    //
//求一个byte数字对应的二进制数字中1的最大连续数，例如3的二进制为00000011，最大连续2个1
    private static void togetherOne() throws IOException {
        BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
        String value;
        while((value=bf.readLine())!=null){
            int num=Integer.parseInt(value);
            char[] chars=Integer.toBinaryString(num).toCharArray();

            int[] nums=new int[chars.length];
            for(int index=0;index<chars.length;index++){
                char chat=chars[index];
                if(chat=='1'){
                    if(index==0)
                        nums[index]=1;
                    else{
                        nums[index]=nums[index-1]+1;
                        nums[index-1]=0;
                    }
                }
            }

            Arrays.sort(nums);
            System.out.println(nums[nums.length-1]);
        }
    }

    private static void leftAndRight() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;

        while((str = br.readLine())!=null && str.length()!=0){
            char[] chars = str.toCharArray();
            int n = chars.length;
            int max = 1;

            for(int i = 0; i < n-1; i++){//奇
                int left = i - 1;
                int right = i + 1;
                int length = 1;
                while(left>=0 && right<n && chars[left]==chars[right]){
                    length += 2;
                    left--;
                    right++;
                }
                max = max > length ? max : length;
            }
            for(int i = 0; i < n-1; i++){//偶
                int length = 1;
                if(chars[i]==chars[i+1]){
                    length = 2;
                }else
                    continue;

                int left = i - 1;
                int right = i + 2;
                while(left>=0 && right<n && chars[left]==chars[right]){
                    length += 2;
                    left--;
                    right++;
                }
                max = max > length ? max : length;
            }
            System.out.println(max);
        }
    }

    private static void findBigChar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while((str = br.readLine()) != null) {
            char[] chars = str.toCharArray();
            int count = 0;
            for(int i = 0; i < chars.length; i++)
                if(65 <= chars[i] && chars[i] <= 90) count ++;
            System.out.println(count);
        }
    }

    /*验证尼科彻斯定理，即：任何一个整数m的立方都可以写成m个连续奇数之和。

    例如：

            1^3=1

            2^3=3+5

            3^3=7+9+11

            4^3=13+15+17+1*/
    public static String method(int m){
        int temp1 = m*m*m ;
        int temp2 = m*m ;
        StringBuffer sb = new StringBuffer() ;

        for(int i=0;i<m;i++){
            if(m%2!=0){
                sb.append(temp2-2*(m/2)+2*i).append("+") ;
            }else{
                sb.append(temp2-m+1+2*i).append("+") ; ;
            }
        }
        return  sb.substring(0,sb.length()-1).toString() ;
    }
    /**
     * 给定两个只包含小写字母的字符串，计算两个字符串的最大公共子串的长度。
     注：子串的定义指一个字符串删掉其部分前缀和后缀（也可以不删）后形成的字符串。
     * @param c1
     * @param c2
     * @return
     */
    public static int way(char[] c1, char[] c2) {
        int max = 0;
        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                int t1 = i;
                int count = 0;
                int t2 = j;
                while (c1[t1] == c2[t2]) {
                    count++;
                    t1++;
                    t2++;
                    if (count > max) {
                        max = count;
                    }
                    if (t1 == c1.length || t2 == c2.length) {
                        break;
                    }
                }
            }
        }
        return max;
    }
    private static void replaceProgram() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s=br.readLine())!=null){
            char[] chars=s.toCharArray();
            StringBuffer ana=new StringBuffer();
            int flag=0;
            int count=1;
            for(int i=0;i<chars.length;i++){
                if(chars[i]=='\"'){
                    flag++;
                    continue;
                }
                if(chars[i]!=' '){
                    ana.append(chars[i]);
                }
                if(chars[i]==' '&&flag%2!=0){
                    ana.append(chars[i]);
                }
                if(chars[i]==' '&&flag%2==0){
                    ana.append("\n");
                    count++;
                }
            }
            System.out.println(count+"\n"+ana.toString());
        }
    }

    private static void day() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while ((str = br.readLine()) != null) {
            String[] strs = str.split(" ");
            int year = Integer.parseInt(strs[0]);
            int month = Integer.parseInt(strs[1]);
            int day = Integer.parseInt(strs[2]);
            int result = day;
            for (int i = 1; i < month; i++) {
                result += getMonthDays(year, i);
            }
            System.out.println(result);
        }
    }

    public static int getMonthDays(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        if (month == 2) {
            return year % 4 == 0 ? 29 : 28;
        }
        return 30;
    }
    //买鸡
    private static void killChild() {
        int x,y;
        for(int z=0;z<100;z++){
            if(z%3==0){
                if(4*z/3-100>=0 && 200-7*z/3<=100 && 4*z/3-100<=100 && 200-7*z/3>=0){
                    x = 4*z/3-100;
                    y = 200-7*z/3;
                    System.out.println(x+" "+y+" "+z);
                }
            }
        }
    }

    private static void pot24() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while ((str = br.readLine()) != null) {
            String[] arr = str.split(" ");
            int[] num = new int[arr.length];
            boolean[] flags = new boolean[arr.length];
            for (int i = 0; i < num.length; i++) {
                num[i] = Integer.parseInt(arr[i]);
            }
            boolean flag = false;
            for (int i = 0; i < arr.length; i++) {
                flags[i] = true;
                flag = dfs(num, flags, num[i]);
                flags[i] = false;
                if (flag) break;
            }
            System.out.println(flag);


        }
    }

    public static boolean dfs(int[] arr, boolean[] flags, int c) {
        boolean end = true;
        for (boolean b : flags) {
            if (!b) {
                end = false;
                break;
            }
        }
        if (end) {
            if (c == 24) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (!flags[i]) {
                flags[i] = true;
                if (dfs(arr, flags, c + arr[i])) return true;
                if (dfs(arr, flags, c - arr[i])) return true;
                if (dfs(arr, flags, c * arr[i])) return true;
                if (c % arr[i] == 0 && dfs(arr, flags, c / arr[i])) return true;
                flags[i] = false;
            }
        }
        return false;
    }

    /**
     * 多行字符串，每行字符串一条命令
     * @throws IOException
     */
    private static void commad() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, String> HMp = new HashMap<>();
        HMp.put("reset", "reset what");
        HMp.put("reset board", "board fault");
        HMp.put("board add", "where to add");
        HMp.put("board delete", "no board at all");
        HMp.put("reboot backplane", "impossible");
        HMp.put("backplane abort", "install first");

        String input=br.readLine();

        while(null!=input){
            if(HMp.containsKey(input)){
                System.out.println(HMp.get(input));
            }
            else{
                System.out.println("unknown command");
            }

            input=br.readLine();
        }
    }

    //找出GC比例最高的子串,如果有多个输出第一个的子串

    /**
     *
     * 输入 AACTGTGCACGACCTGA
            5
       输出 GCACG
     * @throws IOException
     */
    private static void perGC() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


        String str = null;

        while((str = bf.readLine())!=null){

            int num = Integer.parseInt(bf.readLine());
            int count = 0;
            for(int i = 0;i<str.length();i++){
                char c = str.charAt(i);
                if(c=='G'||c=='C')
                    count++;
            }
            int max = count;
            int left = 0;
            for(int i = 1;i<str.length()-num;i++){
                char pre = str.charAt(i-1);
                char nex = str.charAt(i+num-1);
                if(pre=='G'||pre=='C')
                    count--;
                if(nex=='G'||nex=='C')
                    count++;
                if(count>max){
                    max = count;
                    left = i;
                }
            }

            System.out.println(str.substring(left,left+num));



        }
    }

    //计算整数二进制中1的个数
    private static void binaryOne() {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int n = scan.nextInt();
            int count = 0;
            String str = Integer.toBinaryString(n);
            char[] cha = str.toCharArray();
            for(char c :cha){
                if(c=='1'){
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    //输入n个整数，输出其中最小的k个。
    private static void leastTwo() {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNext()) {
            String s=scanner.nextLine();
            String s1=scanner.nextLine();
            String[] ss=s.split(" ");
            String[] s1s=s1.split(" ");
            int[] nums=new int[Integer.valueOf(ss[0])];
            for (int i = 0; i < s1s.length; i++) {
                nums[i]=Integer.valueOf(s1s[i]);
            }
            Arrays.sort(nums);
            StringBuilder stringBuilder=new StringBuilder();
            for (int i = 0; i < Integer.valueOf(ss[1]); i++) {
                stringBuilder.append(nums[i]).append(" ");
            }
            System.out.println(stringBuilder.toString().trim());
        }
    }

    //输入一个单向链表，输出该链表中倒数第k个结点，链表的倒数第1个结点为链表的尾指针
    private static void nextInsert() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while((str = br.readLine())!=null){
            int num = Integer.parseInt(str);
            String[] numStr = br.readLine().split(" ");

            int num2 = Integer.parseInt(br.readLine().trim());
            if(num2<=0||num2>numStr.length){
                System.out.println(num2);
            }else{
                System.out.println(numStr[numStr.length-num2]);
            }
        }
    }

    //输入一个字符串和一个整数k，截取字符串的前k个字符并输出
    private static void substringSix() throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=null;
        while((str=br.readLine())!=null){
            int k=Integer.parseInt(br.readLine());
            System.out.println(str.substring(0,k));
        }
    }

    private static void beautyNum() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            for(int i=0;i<n;i++){
                String s=br.readLine();
                char[] c=s.toCharArray();
                int[] count=new int[150];
                for(int j=0;j<c.length;j++){
                    count[c[j]]++;
                }

                Arrays.sort(count);
                int a=26;
                int sum=0;
                for(int k=count.length-1;k>=0;k--){
                    if (count[k] == 0) {
                        break;
                    }
                    sum+=count[k]*(a--);
                }
                System.out.println(sum);
            }
        }
    }

    //迷宫问题
    private static void secretPalace() throws IOException {
        BufferedReader bfr  = new BufferedReader(new InputStreamReader(System.in));
        String x;
        while ((x=bfr.readLine())!=null){
            String[] arr = x.split(" ");
            int m = Integer.parseInt(arr[0]);
            int n = Integer.parseInt(arr[1]);
            int[][] a = new int[m][n];
            for(int i=0;i<m;i++){
                String strr = bfr.readLine();
                String[] b = strr.split(" ");
                for(int j=0;j<n;j++){
                    a[i][j] = Integer.parseInt(b[j]);
                }
            }

            int i=0;
            int j=0;
            for(int k=0;k<m+n-1;k++){
                ////打印：当mat[i][j]==0时
                if(a[i][j]==0){
                    System.out.println("(" +i+","+ j+")");
                }
                //只能往下走：索引到最大j==n-1||右边mat[i][j+1]==1时，i++
                if (j==n-1||a[i][j+1]==1){
                    i++;
                    continue;
                }
                //只能往右走：索引到最大i==m-1||下边mat[i][j+1]==1时，j++
                if(i==m-1||a[i][j+1]==0){
                    j++;
                    continue;
                }
                //停止：i==m-1 && j==n-1时，break跳出
                if((i==m-1)&&(j==n-1)){
                    break;
                }
            }


        }
    }


    //数字转换成英语
    public static String parse(int num){
        String[] numStr={"zero","one","two","three","four","five",
                "six","seven","eight","nine","ten","eleven","twelve",
                "thirteen","fourteen","fifteen","sixteen","seventeen",
                "eighteen","ninteen"};
        if(num>=0 && num<20){
            return numStr[num];
        }else if(num>=20 && num<100){
            int a=num%10;
            if(num<30){
                return a !=0?"twenty "+parse(a):"twenty";
            }else if(num<40){
                return a !=0?"thirty "+parse(a):"thirty";
            }else if(num<50){
                return a !=0?"forty "+parse(a):"forty";
            }else if(num<60){
                return a !=0?"fifty "+parse(a):"fifty";
            }else if(num<70){
                return a !=0?"sixty "+parse(a):"sixty";
            }else if(num<80){
                return a !=0?"seventy "+parse(a):"seventy";
            }else if(num<90){
                return a !=0?"eighty "+parse(a):"eighty";
            }else if(num<100){
                return a !=0?"ninety "+parse(a):"ninety";
            }
        }else if(num>=100 && num<1000){
            int x=num/100;
            int y=num%100;
            if(y!=0){
                return parse(x)+" hundred"+ " and "+ parse(y);
            }else{
                return parse(x)+" hundred";
            }
        }
        else if(num>=1000 && num<1000000){
            int x=num/1000;
            int y=num%1000;
            if(y!=0){
                return parse(x)+" thousand "+ parse(y);
            }else{
                return parse(x)+" thousand";
            }
        }else if(num>=1000000 && num<100000000){
            int x=num/1000000;
            int y=num%1000000;
            if(y!=0){
                return parse(x)+" million "+ parse(y);
            }else{
                return parse(x)+" million";
            }
        }
        return "error";
    }
    //不死兔子
    private static void notDeadRabbit() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while((input = bf.readLine())!=null){
            int num = Integer.parseInt(input);
            int a=1;
            int b=1;
            if(num==1 || num==2) System.out.println(num);
            else{
                for(int i=3;i<=num;i++){
                    int temp = b;
                    b = a+b;
                    a = temp;
                }
                System.out.println(b);
            }
        }
    }

    //蛇形矩阵是由1开始的自然数依次排列成的一个矩阵上三角形
    private static void encode(int key) {
        // TODO Auto-generated method stub
        int[][] arr = new int[key][key];
        arr[0][0] = 1;
        System.out.print(arr[0][0]);
        for (int i = 1; i < key; i++) {
            arr[0][i] = arr[0][i-1] + i+1;
            System.out.print(" "+arr[0][i]);
        }
        for (int i = 1; i < key; i++) {
            System.out.println();
            for (int j = 0; j < key-i; j++) {
                arr[i][j] = arr[i-1][j+1] -1;
                if(j==0){
                    System.out.print(arr[i][j]);
                }else {
                    System.out.print(" "+arr[i][j]);
                }
            }
        }
    }

    private static void asicSort() {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            char [] str = s.toCharArray();
            Arrays.sort(str);
            System.out.println(str);
        }
    }

    private static void getTenToIP(String num)
    {
        String sb="";
        //把数转化成二进制
        String binaryString = Long.toBinaryString(Long.parseLong(num));
        /*System.out.println(binaryString);
        System.out.println(binaryString.length());
        System.out.println(addZero32(binaryString));
        System.out.println(addZero32(binaryString).length());*/
        String binnaryNum = addZero32(binaryString);
        //然后没8个一组，进行截取
        for(int i=0;i<binnaryNum.length();i=i+8)
        {
            //System.out.println(binnaryNum.substring(i, i+8));
            String substring = binnaryNum.substring(i, i+8);
            //转化为十进制数
            int parseInt = Integer.parseInt(substring,2);
            sb=sb+parseInt+".";

        }
        System.out.println(sb.substring(0, sb.length()-1));
    }

    private static String addZero32(String binaryString) {
        // TODO Auto-generated method stub

        if(binaryString.length()<32)
        {
            String sb = "";
            int addLength = 32-binaryString.length();
            for(int i=0;i<addLength;i++)
            {
                sb= sb+"0";
            }
            return sb + binaryString;
        }
        return binaryString;
    }

    private static void getTenIp(String ip)
    {
        String sb = "";
        String[] sub = ip.split("\\.");//.需要转义字符
        for(int i=0;i<sub.length;i++)
        {
            //System.out.println(sub[i]);
            //对每一个字符转换成二进制
            String binaryString = Integer.toBinaryString(Integer.parseInt(sub[i]));
            //对每一位进行判断，不够8位补零
            String addZeroIP = addZero(binaryString);
            sb=sb+addZeroIP;
            //System.out.println(addZero(binaryString));
        }
        //System.out.println(sb.length());
        System.out.println(Long.parseLong(sb, 2));//刚开始就是老师用Integer，位数不够
    }

    private static String addZero(String binaryString) {
        // TODO Auto-generated method stub

        if(binaryString.length()<8)
        {
            String sb = "";
            int addLength = 8-binaryString.length();
            for(int i=0;i<addLength;i++)
            {
                sb= sb+"0";
            }
            return sb + binaryString;
        }
        return binaryString;
    }
    //回文串只有两种类型：偶数型为ABBA型，奇数型为ABA型。。
    //依次向后以每个字符为中心，向两侧扩展，判断是偶数型还是奇数型，然后比较偶数型和奇数型回文串的最大长度。。
    public static void Dispose(String str){
        int len = str.length();//字符串长度
        if(len == 1){
            System.out.println(1);
            return;
        }
        int num = 1;//回文串最大长度,最小为1
        for(int i=1;i<len-1;i++){  //从第二个字符开始，依次选为中心字符向向两侧扩展
            if(str.charAt(i)==str.charAt(i+1)){  //偶数对为回文串
                int cur = 0;  //当成为回文串的最大长度,初始化置为0
                int start = i; //左侧起始位
                int end = i+1; //右侧起始位
                while(start >=0 && end <len && str.charAt(start) == str.charAt(end)){ //向两侧扩展
                    start --;//每次扩展一位
                    end ++;
                    cur += 2;//每次最大回文长度增加两位
                }
                if(cur>=num){
                    num = cur;
                }
            }
            if(str.charAt(i-1) == str.charAt(i+1)){ //奇数对为回文串
                int cur = 0;
                int start = i-1; //左侧
                int end = i+1; //右侧
                while(start>=0 && end <len && str.charAt(start) == str.charAt(end)){ //向两侧扩展
                    start --;
                    end ++;
                    cur += 2;
                }
                if(cur>=num){
                    num = cur+1;//注意此处加1是为了算上中心字符；
                }
            }
        }
        System.out.println(num);
    }
    private static void prefectReverse() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String ss = s.replaceAll("[^a-zA-Z]+", " ").trim();
        String str[]=ss.split(" ");
        for(int i=str.length-1;i>0;i--){
            System.out.print(str[i]+" ");
        }
        System.out.println(str[0]);
    }

    private static void drink() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            if (num == 0) break;
            System.out.println(result(num));
        }
    }

    public static int result(int n) {
        int result = 0;
        int total = n;
        while (total > 0) {//不断循环
            if (total == 2) {//只剩下两瓶就+1
                total++;
            }
            total = total - 3;
            if (total >= 0) {//每减三瓶总数加一瓶，结果也加一
                total++;
                result++;
            }
        }
        return result;
    }

    private static void password2() {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            stringBuilder.append(password(c));
        }
        System.out.println(stringBuilder.toString());
        sc.close();
    }

    public static char password(char c) {
        int[] codetable = {2, 2, 2, 3, 3, 3, 4, 4, 4,
                5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
        if (c <= 'Z' && c >= 'A') {
            if (c == 'Z') {
                c = 'a';
            } else {
                c = Character.toLowerCase(c);
                c++;
            }
        } else if (c <= 'z' && c >= 'a') {
            c = (char) (codetable[c - 'a'] + '0');
        }
        return c;
    }

    private static void password1() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        StringBuffer sb = new StringBuffer();

        while (null != (input = reader.readLine())) {

            //设置四种类型数据初始为空即false，有数据了就更改为true
            boolean[] flag = new boolean[4];
            char[] chars = input.toCharArray();

            // 第一个条件
            if (chars.length < 9) {
                sb.append("NG").append("\n");
                continue;
            }

            // 第二个条件
            for (int i = 0; i < chars.length; i++) {
                if ('A' <= chars[i] && chars[i] <= 'Z') {
                    flag[0] = true;
                } else if ('a' <= chars[i] && chars[i] <= 'z') {
                    flag[1] = true;
                } else if ('0' <= chars[i] && chars[i] <= '9') {
                    flag[2] = true;
                } else {
                    flag[3] = true;
                }
            }
            int count = 0;
            for (int i = 0; i < 4; i++) {
                if (flag[i]) {
                    count++;
                }
            }

            // 第三个条件
            boolean sign = true;        //不存在两个大于2的子串相同，即！（i=i+3,i+1=i+4,i+2=i+5）
            for (int i = 0; i < chars.length - 5; i++) {
                for (int j = i + 3; j < chars.length - 2; j++) {
                    if (chars[i] == chars[j] && chars[i + 1] == chars[j + 1] && chars[i + 2] == chars[j + 2]) {
                        sign = false;
                    }
                }
            }

            if (count >= 3 && sign) {
                sb.append("OK").append("\n");
            } else {
                sb.append("NG").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void password() throws IOException {
        BufferedReader buffer =
                new BufferedReader(new InputStreamReader(System.in));
        String str;
        LinkedHashMap<String, Integer> data = new LinkedHashMap<String, Integer>();
        while ((str = buffer.readLine()) != null) {
            int idx1 = str.lastIndexOf(" ");
            int idx2 = str.lastIndexOf("\\");
            String key = (idx1 - idx2) > 16 ? str.substring(idx1 - 16) : str.substring(idx2 + 1);
            data.put(key, data.getOrDefault(key, 0) + 1);
        }
        int count = 0;
        for (String key : data.keySet()) {
            count++;
            if (count > (data.size() - 8)) {
                System.out.println(key + " " + data.get(key));
            }
        }
    }

    private static void replaceChar() {
        String s = "?123233?34";
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                //前面一个字符  如果当前是第0个的话 字符就为‘ ’
                char ahead = i == 0 ? ' ' : chars[i - 1];
                //后面一个字符  如果当前是最后一个的话 字符就为‘ ’
                char behind = i == chars.length - 1 ? ' ' : chars[i + 1];
                //从a开始比较  如果等于前面或者后面的话 就+1
                char temp = 'a';
                while (temp == ahead || temp == behind) {
                    temp++;
                }
                //找到目标字符后 做替换
                chars[i] = temp;
            }
        }
        System.out.println(new String(chars));
    }

    private static void replaceStar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (null != (str = br.readLine())) {
            char[] strs = str.toCharArray();
            StringBuilder result = new StringBuilder();
            int i = 0;
            while (i < strs.length) {
                if ('0' <= strs[i] && strs[i] <= '9') {
                    result.append("*");
                    result.append(strs[i]);
                    while (++i < strs.length && '0' <= strs[i] && strs[i] <= '9') {
                        result.append(strs[i]);
                    }
                    result.append("*");
                } else {
                    result.append(strs[i]);
                    i++;
                }
            }
            System.out.println(result.toString().trim());
        }
    }

    private static void addStar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while ((str = br.readLine()) != null) {
            char[] c = str.toCharArray();
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < c.length) {
                if (c[i] >= '0' && c[i] <= '9') {
                    sb.append("*");
                    sb.append(c[i]);
                    while (++i < c.length && c[i] >= '0' && c[i] <= '9') {
                        sb.append(c[i]);
                    }
                    sb.append("*");
                } else {
                    sb.append(c[i]);
                    i++;
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static void endsWith() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int cnt = 0;
            for (int i = 0; i <= n; i++) {
                long end = i * i;
                if (String.valueOf(end).endsWith(String.valueOf(i))) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    private static void sum() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while ((str = br.readLine()) != null) {
            int n = Integer.parseInt(str);
            System.out.println((3 * n * n + n) / 2);
        }
    }

    private static void quickSort() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputCount;

        while ((inputCount = br.readLine()) != null) {
            int count = Integer.parseInt(inputCount);
            String[] input = br.readLine().split(" ");
            int flag = Integer.parseInt(br.readLine());
            int[] num = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                num[i] = Integer.parseInt(input[i]);
            }
            quickSort(num, 0, num.length - 1);
            StringBuilder sb = new StringBuilder();
            if (flag == 0) {
                for (int j = 0; j < num.length; j++) {
                    sb.append(num[j]).append(" ");
                }
            } else {
                for (int k = num.length - 1; k >= 0; k--) {
                    sb.append(num[k]).append(" ");
                }
            }
            System.out.println(sb.substring(0, sb.length() - 1));
        }
    }

    public static void quickSort(int[] num, int L, int R) {
        if (L >= R) {
            return;
        }
        int p = partition(num, L, R);
        quickSort(num, L, p - 1);
        quickSort(num, p + 1, R);
    }

    public static int partition(int[] num, int L, int R) {
        int key = num[L];
        int pivot = L;

        for (int i = L + 1; i <= R; i++) {
            if (num[i] < key) {
                int temp = num[++pivot];
                num[pivot] = num[i];
                num[i] = temp;
            }
        }
        int tt = num[pivot];
        num[pivot] = num[L];
        num[L] = tt;
        return pivot;
    }

    private static void dpApple() throws IOException {
    /*  解题分析：
    设f(m,n) 为m个苹果，n个盘子的放法数目，则先对n作讨论，
    当n>m：必定有n-m个盘子永远空着，去掉它们对摆放苹果方法数目不产生影响。即if(n>m) f(m,n) = f(m,m)　　
    当n<=m：不同的放法可以分成两类：
    1、有至少一个盘子空着，即相当于f(m,n) = f(m,n-1);
    2、所有盘子都有苹果，相当于可以从每个盘子中拿掉一个苹果，不影响不同放法的数目，即f(m,n) = f(m-n,n).
    而总的放苹果的放法数目等于两者的和，即 f(m,n) =f(m,n-1)+f(m-n,n)
递归出口条件说明：
    当n=1时，所有苹果都必须放在一个盘子里，所以返回１；
    当没有苹果可放时，定义为１种放法；
    递归的两条路，第一条n会逐渐减少，终会到达出口n==1;
    第二条m会逐渐减少，因为n>m时，我们会return f(m,m)　所以终会到达出口m==0．
*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = " ";
        while ((str = br.readLine()) != null) {
            String[] s = str.split(" ");
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            System.out.println(count(m, n));
        }
    }

    public static int count(int m, int n) {
        if (n == 1 || m == 0) return 1;
        else if (n > m) return count(m, m);
        else return count(m, n - 1) + count(m - n, n);
    }

    private static void check7() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while ((input = bufferedReader.readLine()) != null) {
            int count = 0;
            int num = Integer.parseInt(input);
            for (int i = 7; i <= num; i++) {
                if (i % 7 == 0 || contain7(i)) {
                    count++;
                }
            }
            System.out.println(count);
        }
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

    //输入一行字符，分别统计出包含英文字母、空格、数字和其它字符的个数。
    private static void countAction() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
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
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                n[0]++;
            } else if (c == ' ') {
                n[1]++;
            } else if (c >= '0' && c <= '9') {
                n[2]++;
            } else {
                n[3]++;
            }
        }

        return n;
    }

    private static void rabbit() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            //int m = count(n);
            int m = count(n);
            System.out.println(m);
        }
    }

    private static int count(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return count(n - 1) + count(n - 2);
    }

    //自由落体
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

    //加密解密
    private static String en(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] >= 'A' && cs[i] <= 'Z') {
                if (cs[i] == 'Z') {
                    cs[i] = 'a';
                } else
                    cs[i] = (char) (cs[i] + 33);
            } else if (cs[i] >= 'a' && cs[i] <= 'z') {
                if (cs[i] == 'z') {
                    cs[i] = 'A';
                } else
                    cs[i] = (char) (cs[i] - 31);
            } else if (cs[i] >= '0' && cs[i] <= '9') {
                if (cs[i] == '9') {
                    cs[i] = '0';
                } else {
                    cs[i] = (char) (cs[i] + 1);
                }
            }
        }
        return String.valueOf(cs);
    }

    private static String de(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] >= 'A' && cs[i] <= 'Z') {
                if (cs[i] == 'A') {
                    cs[i] = 'z';
                } else
                    cs[i] = (char) (cs[i] + 31);
            } else if (cs[i] >= 'a' && cs[i] <= 'z') {
                if (cs[i] == 'a') {
                    cs[i] = 'Z';
                } else
                    cs[i] = (char) (cs[i] - 33);
            } else if (cs[i] >= '0' && cs[i] <= '9') {
                if (cs[i] == '0') {
                    cs[i] = '9';
                } else {
                    cs[i] = (char) (cs[i] - 1);
                }
            }
        }
        return String.valueOf(cs);
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

