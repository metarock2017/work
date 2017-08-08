package org.redrock.set;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wang on 2017/8/6.
 */
public class TextFile {
    private String path;
    private Pattern pattern = null;
    private Set<String> wordsList = null;
    private Integer i;
    TextFile(String path, String pattern) {
        this.path = path;
        this.pattern = Pattern.compile(pattern);
    }

    TextFile(String path) {
        this.path = path;
    }

    //public  Set<String> getData() {
    public Integer getData() {
        this.wordsList = new HashSet<>();
        try {
            FileInputStream fis = new FileInputStream(this.path);
            InputStreamReader isp = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isp);
            String data = null;
            while ((data = br.readLine()) != null) {
                this.parse(data);
            }

            br.close();
            isp.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return this.wordsList;
        return this.i;
    }

    private void parse(String line) {
        Matcher matcher = this.pattern.matcher(line);
        while (matcher.find()) {
            this.wordsList.add(matcher.group());
            this.i++;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        // file(内存)----输入流---->【程序】----输出流---->file(内存)
        TextFile textFile = new TextFile("/Users/wang/wechat/test.php", "[aeiou]");
        textFile.getData();
    }
}
