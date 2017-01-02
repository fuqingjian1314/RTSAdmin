package com.sjg.sys.utils;

import sun.misc.BASE64Decoder;
public class Base64 {

	/**
	 * 对参数进行base64编码
	 * @param s
	 * @return
	 */
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	/**
	 * 对产生进行base64解码
	 * @param s
	 * @return
	 */
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
}
