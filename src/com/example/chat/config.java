package com.example.chat;

import java.io.File;

public class config{
    public static String SDCARD = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    public static File path = new File(SDCARD+"/A/"); //��ݿ��ļ�Ŀ¼   
    
    public static File f = new File(SDCARD+"/A/config.db"); //��ݿ��ļ�  
}