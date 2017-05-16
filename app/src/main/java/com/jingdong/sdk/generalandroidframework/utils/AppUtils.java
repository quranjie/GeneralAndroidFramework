package com.jingdong.sdk.generalandroidframework.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * Created by quzhiyong on 2017/5/15.
 */
public class AppUtils {
    /**
     * 获取app的版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        String version = "0";
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 得到软件显示版本信息
     * @param context
     * @return  当前版本信息
     */
    public  static  String  getVerionName(Context context) {
        String verName = "";
        try {
            String packageName = context.getPackageName();
            verName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 获取Imei号码
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        String deviceId;
        TelephonyManager telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        deviceId = telephony.getDeviceId();
        return deviceId;
    }

    /**
     * 安装apk
     * @param context
     * @param file  文件APK文件
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 安装apk
     * @param context
     * @param file  apk文件的uri
     */
    public static void installApk(Context context, Uri file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(file, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 卸载apk
     * @param context
     * @param packageName   包名
     */
    public static void uninstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri uri = Uri.parse("package:" + packageName);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * 检测服务是否运行
     * @param context
     * @param className 类名
     * @return  是否运行的状态
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning  = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo si : servicesList) {
            if (className.equalsIgnoreCase(si.service.getClassName())) {
                isRunning = true;
            }
        }

        return isRunning;
    }

    /**
     * 停止运行服务
     * @param context
     * @param className 类名
     * @return  是否执行成功
     */
    public static boolean stopRunningService(Context context, String className) {
        Intent intent_service = null;
        boolean ret = false;
        try {
            intent_service = new Intent(context, Class.forName(className));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (intent_service != null) {
            ret = context.stopService(intent_service);
        }

        return ret;
    }

    /**
     * 应用程序是否在后台
     * <ul>
     *     <li>需要在Manifest.xml中使用权限android.permission.GET_TASKS </li>
     * </ul>
     * @param context
     * @return  如果应用程序在后台返回true，否则返回
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取设备的可用内存大小
     * @param context
     * @return  当前内存大小
     */
    public  static int getDeviceUsableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return (int) (mi.availMem / (1024 * 1024));
    }

    /**
     * 获取手机系统SDK版本
     * @return
     */
    @TargetApi(Build.VERSION_CODES.DONUT)
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备唯一标识
     * @param context
     * @return
     */
    public static String getUUID(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELECOM_SERVICE);

        final String tmDevice, tmSerial, tmPhone, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();

        return uniqueId;
    }

    /**
     * 是否是主线程
     * @param context
     * @return
     */
    public static boolean isMainProcess(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测是否安装了某个客户端
     * @param context
     * @param packageName   包名
     * @return  true说明安装了，false说明没安装
     */
    public static boolean isAvailible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();

        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);

        for (int i = 0; i < pinfo.size(); i++) {
            if (pinfo.get(i).packageName.equalsIgnoreCase(packageName)) {
                return true;
            }
        }
        return false;
    }
}
