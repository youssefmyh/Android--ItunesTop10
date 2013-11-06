package com.aisteps.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aisteps.constants.ApplicationConstant;

/*
 * DataBaseHelper class is sub of SQLiteOpenHelper
 * used to initalize DataBase and create Tables if it not exisist 
 * under constants package you can find databse constants used in below code like DATABASE_NAME,VERSION ,TABLE Creation
 * */
public class DataBaseHelper extends SQLiteOpenHelper {

	public DataBaseHelper(Context context) {
		super(context, ApplicationConstant.DATABASE_NAME, null,
				ApplicationConstant.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(ApplicationConstant.CREATE_SONG_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
