package org.redrock.set;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wang on 2017/8/6.
 */
public class Parcel8 {
    public static void main(String[] args) throws IOException
    {
//        Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
//        替代文件
//        words.addAll(new TextFile("D:\\JAVAeclipse\\eclipse---space\\second_lesson\\src\\org\\redrock\\set\\text.txt", "\\w+").getData());
//        System.out.println(words);

        //作业：选择一个文件，利用Set容器的特性，计算该文件中有多少个不同的单词
//        Integer num = null;
//        //num = new TextFile("D:\\JAVAeclipse\\eclipse---space\\second_lesson\\src\\org\\redrock\\set\\text.txt", "\\w+").getData();
//        System.out.println(num);
        Parcel8.getWordsNum();
    }

    public static void getWordsNum() throws IOException
    {
        String string = "";
        Set s = new HashSet();
        FileInputStream stream = new FileInputStream("D:\\JAVAeclipse\\eclipse---space\\second_lesson\\src\\org\\redrock\\set\\text.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(stream)
        );
        while ((string = br.readLine()) !=null) {
            String[] st = string.split("\\s+");
            for (String i : st) {
                s.add(i);
            }
        }
        System.out.println(s.size());
    }

}
