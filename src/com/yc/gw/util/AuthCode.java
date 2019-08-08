package com.yc.gw.util;

import java.util.Random;

/**
 * 生成动态验证码
 * @author ASUS
 *
 */
public class AuthCode {
	public String getCode() {
		Random rd = new Random();
		int code = rd.nextInt(8999) + 1000;
		return String.valueOf(code);
	}
}
