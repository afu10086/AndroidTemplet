package com.lidaofu.android.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;

import com.lidaofu.android.app.MyApplication;


/**
 * 网络管理工具
 */
public final class NetManager
{
    // 手机网络类型
    public static final int NETTYPE_NO = 0x00;
    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

//    private static final String USEFUL_TEST_URL  = "http://www.baidu.com";
//    private static final String USEFUL_TEST_HOST = "www.baidu.com";


    public static boolean isNetConnected()
    {
        NetworkInfo info = getActiveNetworkInfo(MyApplication.getSingle());
        if (info != null)
            return info.getState() == NetworkInfo.State.CONNECTED;
        return false;
    }

    public static boolean isNetUseful(int timeout, int tryTimes)
    {
        if (tryTimes <= 0)
            throw new IllegalArgumentException("trying times should be greater than zero.");
        int th = 1;
        while (th <= tryTimes)
        {
//            try
//            {
//                HttpResponseResult result = HttpConnectionManager.doGet(USEFUL_TEST_URL, "gb2312", true, timeout, null);
//                if (result.getResponseCode() == HttpURLConnection.HTTP_OK)
//                {
//                    String host = result.getResponseURL().getHost();
//                    String content = result.getDataString("gb2312");
//                    if (USEFUL_TEST_HOST.equalsIgnoreCase(host) && content.indexOf(USEFUL_TEST_HOST) >= 0)
//                    { // 若能访问到原始站点，证明网络有效
//                        return true;
//                    }
//                }
//            } catch (IOException e)
//            {
//                LogManager.logE(NetManager.class, "the " + th + " time to check net for method of isNetUseful failed.", e);
//            }
            th++;
        }
        return false;
    }

    public static NetworkInfo getActiveNetworkInfo(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    public static NetworkInfo getMobileNetworkInfo(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    }

    public static NetworkInfo getWifiNetworkInfo(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }

    public static NetworkInfo[] getAllNetworkInfo(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getAllNetworkInfo();
    }

    /**
     * <p>是否处于飞行模式
     * 
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
	public static boolean isInAirplaneMode(Context context)
    {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }
    
    

    /**
     * <p>是否存在多个已连接的网络
     * 
     * @param context
     * @return
     */
    public static boolean isAvailableMultiConnectedNets(Context context)
    {
        boolean wifiConnected = false;
        boolean otherConnected = false;
        NetworkInfo[] infos = getAllNetworkInfo(context);
        if (infos != null)
        {
            for (int i = 0; i < infos.length; i++)
            {
                if (infos[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    if (infos[i].getType() == ConnectivityManager.TYPE_WIFI)
                    {
                        wifiConnected = true;
                    } else
                    {
                        otherConnected = true;
                    }
                }
            }
        }
        return wifiConnected && otherConnected;
    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    public static int getNetworkType() {
        int netType = 0;
        NetworkInfo networkInfo = getActiveNetworkInfo(MyApplication.getSingle());
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }
    /**
     * <p>打开系统网络设置的Activity
     * 
     * @param context
     */
    public static void startWirelessSettingsActivity(Context context)
    {
        Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }


}
