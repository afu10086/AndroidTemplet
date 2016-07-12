package com.lidaofu.android.utils;

import android.util.Log;

import com.qinhutouzi.www.config.BuidConfig;

public class LogUtils {

	
	private static final String TAG="QinHuTouZi";

	public static void i(String msg){
		if(BuidConfig.DEBUG){
			Log.i(TAG, msg);
		}
	}
	
	public static void i(String tag,String msg){
		if(BuidConfig.DEBUG){
			Log.i(tag, msg);
		}
	}
	
	
	public static void v(String msg){
		if(BuidConfig.DEBUG){
			Log.v(TAG, msg);
		}
	}
	
	public static void v(String tag,String msg){
		if(BuidConfig.DEBUG){
			Log.v(tag, msg);
		}
	}
	
	public static void e(String msg){
		if(BuidConfig.DEBUG){
			Log.e(TAG, msg);
		}
	}
	
	public static void e(String tag,String msg){
		if(BuidConfig.DEBUG){
			Log.e(tag, msg);
		}
	}
	
	
}
