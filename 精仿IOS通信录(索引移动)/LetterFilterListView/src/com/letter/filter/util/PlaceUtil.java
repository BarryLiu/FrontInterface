package com.letter.filter.util;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.letter.filter.db.DBManager;

/**
 * 
 * ============================================================
 * 
 * project name : TiantianFangFu
 * 
 * copyright ZENG HUI (c) 2015
 * 
 * author : HUI
 * 
 * version : 1.0
 * 
 * date created : On June, 2015
 * 
 * description :
 * 
 * revision history :
 * 
 * ============================================================
 *
 */
public class PlaceUtil {
	private static DBManager dbm;
	private static SQLiteDatabase db;
	
	/**
	 * 从数据库中拿到所有的数据
	 * @param context
	 * @return
	 */
	public static ArrayList<String> getCity(
			Context context) {
		dbm = new DBManager(context.getApplicationContext());
		dbm.openDatabase();
		db = dbm.getDatabase();
		ArrayList<String> list = new ArrayList<String>();
		try {
			String sql = "select * from ffu_area where parentid>0 and parentid<33";
			Cursor cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				list.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (db != null)
			db.close();
		dbm.closeDatabase();
		return list;
	}
}
