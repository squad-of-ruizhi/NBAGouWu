package com.yc.gw.util;

public class StringUtil {
	public static boolean isNull(String ... args) {
		if (args == null || args.length <= 0) {
			return true;
		}
		
		for (String str : args) {
			if (str == null || "".equals(str)){
				return true;
			}
		}
		
		return false;
	}
}
