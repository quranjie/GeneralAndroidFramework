package com.jingdong.sdk.generalandroidframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by quzhiyong on 2017/5/15.
 */
public class SDCardUtils {
    /**
     * Check the SD card
     *
     * @return 是否存在SDCard
     */
    public static boolean checkSDCardAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * Check if the file is exists
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return 是否存在文件
     */
    public static boolean isFileExistsInSDCard(String filePath, String fileName) {
        boolean flag = false;
        if (checkSDCardAvailable()) {
            File file = new File(filePath, fileName);
            if (file.exists()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Write file to SD card
     *
     * @param filePath 文件路径
     * @param filename 文件名
     * @param content  内容
     * @return 是否保存成功
     * @throws Exception
     */
    public static boolean saveFileToSDCard(String filePath, String filename,
                                           String content) throws Exception {
        boolean flag = false;
        if (checkSDCardAvailable()) {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(filePath, filename);
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();
            flag = true;
        }
        return flag;
    }

    /**
     * Read file as stream from SD card
     *
     * @param fileName String PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dirName";
     * @return Byte数组
     */
    public static byte[] readFileFromSDCard(String filePath, String fileName) {
        byte[] buffer = null;
        try {
            if (checkSDCardAvailable()) {
                String filePaht = filePath + "/" + fileName;
                FileInputStream fin = new FileInputStream(filePaht);
                int length = fin.available();
                buffer = new byte[length];
                fin.read(buffer);
                fin.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Delete file
     *
     * @param filePath 文件路径
     * @param fileName filePath = android.os.Environment.getExternalStorageDirectory().getPath()
     * @return 是否删除成功
     */
    public static boolean deleteSDFile(String filePath, String fileName) {
        File file = new File(filePath + "/" + fileName);
        if (!file.exists() || file.isDirectory())
            return false;
        return file.delete();
    }

    /**
     * 获取应用运行的最大内存
     *
     * @return 最大内存
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory() / 1024;
    }
}
