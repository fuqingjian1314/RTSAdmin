package com.sjg.sys.utils;

import java.io.*;
import java.net.*;

/**
 * Created by Administrator on 2016/11/14.
 */
public class HttpUtils {
    public static String HttpGetRequest(String url){
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            System.setProperty("http.maxRedirects", "100");
            CookieManager manager=new CookieManager(null, CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);
            HttpURLConnection connection =  (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Language", "zh-cn");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Accept-Charset","utf-8");
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(2500);
            connection.setReadTimeout(4500);
//            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println( e);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String HttpPostRequest(String url,String param){
        StringBuffer sb=new StringBuffer();
        try {
            URL realUrl = new URL(url);

            HttpURLConnection httpConn=(HttpURLConnection)realUrl.openConnection();
            //设置参数
            httpConn.setDoOutput(true);   //需要输出
            httpConn.setDoInput(true);   //需要输入
            httpConn.setUseCaches(false);  //不允许缓存
            httpConn.setRequestMethod("POST");   //设置POST方式连接
            //设置请求属性
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");
            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            httpConn.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
            dos.writeBytes(param);
            dos.flush();
            dos.close();
            //获得响应状态
            int resultCode=httpConn.getResponseCode();
            if(HttpURLConnection.HTTP_OK==resultCode){
                String readLine=new String();
                BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
                while((readLine=responseReader.readLine())!=null){
                    sb.append(readLine).append("\n");
                }
                responseReader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

}
