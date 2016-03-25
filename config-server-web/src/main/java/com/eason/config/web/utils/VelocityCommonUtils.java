package com.eason.config.web.utils;

import org.springframework.util.StringUtils;

public class VelocityCommonUtils {
	
	public static String getOrderPrice(String dbString){
		if(StringUtils.isEmpty(dbString)){
			return "";
		}
		String[] ss = dbString.split(":");
		if(ss.length < 1){
			return "";
		}
		Integer num = Integer.parseInt(ss[0])/100;
		return num.toString();
	}
	
	public static String getReducePrice(String dbString){
		if(StringUtils.isEmpty(dbString)){
			return "";
		}
		String[] ss = dbString.split(":");
		if(ss.length < 2)
			return "";
		Integer num = Integer.parseInt(ss[1])/100;
		return num.toString();
	}

}
