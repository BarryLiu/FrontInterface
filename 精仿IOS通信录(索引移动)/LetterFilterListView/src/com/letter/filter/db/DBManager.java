package com.letter.filter.db;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.hui.util.log.LogUtils;
import com.letter.filter.R;

/**
 * 
 * ============================================================
 * 
 * project name : 字母索引查找列表
 * 
 * copyright ZENG HUI (c) 2015
 * 
 * author : HUI
 * 
 * QQ : 240336124
 * 
 * version : 1.0
 * 
 * date created : On July, 2015
 * 
 * description : 
 * 
 * revision history :
 * 
 * ============================================================
 *
 */
public class DBManager {
    private final int BUFFER_SIZE = 1024;
    public static final String DB_NAME = "city_cn.s3db";
    public static final String PACKAGE_NAME = "com.letter.filter";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"+ PACKAGE_NAME;
    private SQLiteDatabase database;
    private Context context;
    private File file=null;
    
    public DBManager(Context context) {
        this.context = context;
    }
 
    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
        LogUtils.e("database open");
    }
    public SQLiteDatabase getDatabase(){
    	return this.database;
    }
 
    private SQLiteDatabase openDatabase(String dbfile) {
        try {
        	LogUtils.d("open database fileName : "+dbfile);
        	file = new File(dbfile);
            if (!file.exists()) {
            	InputStream is = context.getResources().openRawResource(R.raw.ffu365);
            	if(is!=null){
            		LogUtils.d("is null");
            	}
            	FileOutputStream fos = new FileOutputStream(dbfile);
            	if(is!=null){
            		LogUtils.d("fosnull");
            	}
            	
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count =is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                	fos.flush();
                }
                fos.close();
                is.close();
            }
            database = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            return database;
        } catch (FileNotFoundException e) {
        	LogUtils.d("File not found");
            e.printStackTrace();
        } catch (IOException e) {
        	LogUtils.d("IO exception");
            e.printStackTrace();
        }
        return null;
    }
    
    public void closeDatabase() {
    	if(this.database!=null)
    		this.database.close();
    	LogUtils.e("database close");
    }
}