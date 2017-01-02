package com.sjg.sys.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ReadFiles {

    public static void main(String[] args) {
        String fileName = "D:\\idea_workspace\\workspace\\DGGTradeAdmin\\src\\main\\webapp\\css\\Font-Awesome-master\\css\\font-awesome.css";

        List list = readFileByLines(fileName);
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static List readFileByLines(String fileName) {
        List list = new ArrayList();

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(tempString.contains("before")){
                    list.add(tempString.substring(1,tempString.indexOf("before")-1));
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }

}
