package com.letterlistdamo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpressDbHelper extends SQLiteOpenHelper{
	public static final String DB_NAME = "express.db";
	public static final int VERSION = 1;
	/**快递公司名字**/
	public static final String TABLE_COMPANY_COMPANY_NAME = "company_name";
	/**快递公司对应code**/
	public static final String TABLE_COMPANY_COMPANY_CODE = "company_code";
	/**公司名字对应的首字母**/
	public static final String TABLE_COMPANY_COMPANY_INITIAL = "initial";
	public ExpressDbHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
