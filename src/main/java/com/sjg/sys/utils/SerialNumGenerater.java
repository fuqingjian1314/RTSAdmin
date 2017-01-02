package com.sjg.sys.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuqingjian on 2016/11/23.
 */
public class SerialNumGenerater {

    private static final String SERIAL_NUMBER = "XXXXXXXX"; // 流水号格式8位
    private static SerialNumGenerater serialNumGenerater = null;

    private SerialNumGenerater() {
    }

    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static SerialNumGenerater getInstance() {
        if (serialNumGenerater == null) {
            synchronized (SerialNumGenerater.class) {
                if (serialNumGenerater == null) {
                    serialNumGenerater = new SerialNumGenerater();
                }
            }
        }
        return serialNumGenerater;
    }

    /**
     * 生成下一个编号
     */
    public synchronized String generaterNextNumber(String sno) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (sno == null) {
            id = formatter.format(date) + "00000001";
        } else {
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("00000000");
            id = formatter.format(date)
                    + df.format(1 + Integer.parseInt(sno.substring(8, 16)));
        }
        return id;
    }

    public static void main(String[] args) {
        SerialNumGenerater serialNumGenerater= SerialNumGenerater.getInstance();
        System.out.println(serialNumGenerater.generaterNextNumber(null));
    }
}
