package cn.com.nstc.questions;

import java.util.Scanner;

/**
 * @author huanghudong
 * @ClassName QuestionOne.java
 * @Description 查找出字符串str中字符A，替换成字符B，并统计替换的次数。
 * @createTime 2021年04月23日 11:02:00
 */
public class QuestionOne {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder builder = new StringBuilder();
        builder.append(str);
        int count=0;
        for (int i = 0; i < str.length(); i++) {
            if ('A'==builder.charAt(i)) {
                builder.replace(i,i+1,"B");//包前不包后
                count++;
            }
        }
        System.out.println("Result String:"+builder.toString());
        System.out.println("A count:"+count);
    }
}
