package com.sjg.sys.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取项目项目信息
 * (项目名称、项目版本.....)
 * @author Hel
 *
 */
public class ReadItemConfigUtil {

	/**
	 * 注意 此种方式读取的配置文件会有缓存信息
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static Properties getProperties(String name) throws IOException {  
		Properties properties = new Properties();  
        InputStream inputStream = ReadItemConfigUtil.class.getClassLoader().getResourceAsStream(name);  
        try{  
            properties.load(inputStream);  
        }catch (IOException ioE){  
            ioE.printStackTrace();  
        }finally{  
            inputStream.close();  
        }  
        return properties;
    }  
	
	public static String getProperty(String key){
		try {
			return getProperties("itemConfig.properties").getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
