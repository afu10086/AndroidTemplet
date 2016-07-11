/*
**        DroidPlugin Project
**
** Copyright(c) 2015 Andy Zhang <zhangyong232@gmail.com>
**
** This file is part of DroidPlugin.
**
** DroidPlugin is free software: you can redistribute it and/or
** modify it under the terms of the GNU Lesser General Public
** License as published by the Free Software Foundation, either
** version 3 of the License, or (at your option) any later version.
**
** DroidPlugin is distributed in the hope that it will be useful,
** but WITHOUT ANY WARRANTY; without even the implied warranty of
** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
** Lesser General Public License for more details.
**
** You should have received a copy of the GNU Lesser General Public
** License along with DroidPlugin.  If not, see <http://www.gnu.org/licenses/lgpl.txt>
**
**/

package com.lidaofu.android.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;

import com.lidaofu.android.config.BuildConfig;
import com.lidaofu.android.mode.SingleArray;

import java.io.File;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyCrashHandler implements UncaughtExceptionHandler {

    private static final String TAG = "MyCrashHandler";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final MyCrashHandler sMyCrashHandler = new MyCrashHandler();


    private UncaughtExceptionHandler mOldHandler;

    private Context mContext;


    public static MyCrashHandler getInstance() {
        return sMyCrashHandler;
    }

    public void register(Context context) {
        if (context != null) {
            mOldHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (mOldHandler != this) {
                Thread.setDefaultUncaughtExceptionHandler(this);
            }
            mContext = context;
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (BuildConfig.DEBUG) {//debug时采用默认处理方式
            mOldHandler.uncaughtException(thread, ex);
        } else {
            saveLogToFile(ex);
        }
    }

    /**
     * 出现未知异常时退出程序,不要出现默认的提示黑框
     *
     * @return
     */
    private boolean handlerException() {
        SingleArray singleActivityList =SingleArray.singleArrayInstance();
        if (singleActivityList != null && singleActivityList.size() != 0) {
            for (int i = 0; i < singleActivityList.size(); i++) {
                Activity activity = (Activity) singleActivityList.get(i);
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        return true;
    }


    private void saveLogToFile(Throwable ex) {
        PrintWriter writer = null;
        try {
            Date date = new Date();
            String dateStr = SIMPLE_DATE_FORMAT1.format(date);
            File file = new File(Environment.getExternalStorageDirectory(), String.format("QinHuDai/CrashLog/CrashLog_%s_%s.log", dateStr, android.os.Process.myPid()));
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.exists()) {
                file.delete();
            }

            writer = new PrintWriter(file);

            writer.println("Date:" + SIMPLE_DATE_FORMAT.format(date));
            String packageName = mContext.getPackageName();
            writer.println("AppPkgName:" + packageName);
            try {
                PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(packageName, 0);
                writer.println("VersionCode:" + packageInfo.versionCode);
                writer.println("VersionName:" + packageInfo.versionName);
                writer.println("Debug:" + (0 != (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE)));
            } catch (Exception e) {
                writer.println("VersionCode:-1");
                writer.println("VersionName:null");
                writer.println("Debug:Unkown");
            }
            try {
                writer.println("deviceInfo-->" + getDeviceInfo());
            } catch (Exception e) {
            }
            ex.printStackTrace(writer);

            //未知异常时,退出程序
            handlerException();
        } catch (Throwable e) {

        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (Exception e) {
            }
        }
    }


    private String getDeviceInfo() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append('\n').append("DEVICE INFORMATION").append('\n');
            sb.append("Board: ").append(Build.BOARD).append('\n');
            sb.append("BOOTLOADER: ").append(Build.BOOTLOADER).append('\n');
            sb.append("BRAND: ").append(Build.BRAND).append('\n');
            sb.append("DEVICE: ").append(Build.DEVICE).append('\n');
            sb.append("DISPLAY: ").append(Build.DISPLAY).append('\n');
            sb.append("FINGERPRINT: ").append(Build.FINGERPRINT).append('\n');
            sb.append("HARDWARE: ").append(Build.HARDWARE).append('\n');
            sb.append("HOST: ").append(Build.HOST).append('\n');
            sb.append("ID: ").append(Build.ID).append('\n');
            sb.append("MANUFACTURER: ").append(Build.MANUFACTURER).append('\n');
            sb.append("PRODUCT: ").append(Build.PRODUCT).append('\n');
            sb.append("TAGS: ").append(Build.TAGS).append('\n');
            sb.append("TYPE: ").append(Build.TYPE).append('\n');
            sb.append("USER: ").append(Build.USER).append('\n');
        } catch (Exception e) {
            e.toString();
        }
        return sb.toString();
    }


}
