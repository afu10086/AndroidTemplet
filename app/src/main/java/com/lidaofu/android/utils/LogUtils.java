package com.lidaofu.android.utils;

import android.util.Log;

import com.lidaofu.android.config.BuildConfig;


public class LogUtils {

	
	private static final String TAG="QinHuTouZi";

	public static void i(String msg){
		if(BuildConfig.DEBUG){
			Log.i(TAG, msg);
		}
	}
	
	public static void i(String tag,String msg){
		if(BuildConfig.DEBUG){
			Log.i(tag, msg);
		}
	}
	
	
	public static void v(String msg){
		if(BuildConfig.DEBUG){
			Log.v(TAG, msg);
		}
	}
	
	public static void v(String tag,String msg){
		if(BuildConfig.DEBUG){
			Log.v(tag, msg);
		}
	}
	
	public static void e(String msg){
		if(BuildConfig.DEBUG){
			Log.e(TAG, msg);
		}
	}
	
	public static void e(String tag,String msg){
		if(BuildConfig.DEBUG){
			Log.e(tag, msg);
		}
	}
	
	
}
