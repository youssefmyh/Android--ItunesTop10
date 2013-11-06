package com.aisteps.model;

import com.aisteps.constants.ApplicationConstant;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class DataBaseProvider extends ContentProvider {

	private DataBaseHelper dataBaseHelper = null;

	public static final String AUTHORITY = "com.aisteps.model.DataBaseProvider";
	public static final Uri CONTENT_URI = Uri
			.parse(ApplicationConstant.PROVIDER_CONTENT + AUTHORITY + "/"
					+ ApplicationConstant.SONGS_TABLE_NAME);

	/*
	 * insert Values into Song Table
	 * 
	 * @param uri that the Content_uri of ContentProvider
	 * 
	 * @param values table values need to be updated
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		try {
			// TODO Auto-generated method stub
			if (uri == null) {
				throw new IllegalArgumentException("Uri can't be null");
			}
			if (values == null) {
				throw new IllegalArgumentException(
						"ContentValues can't be null");
			}

			SQLiteDatabase dataBase = dataBaseHelper.getWritableDatabase();
			long id = dataBase.insert(ApplicationConstant.SONGS_TABLE_NAME,
					null, values);
			getContext().getContentResolver().notifyChange(uri, null);
			System.out.println("Insert");
			return Uri.parse(ApplicationConstant.DATABASE_NAME + "/" + id);

		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dataBaseHelper = new DataBaseHelper(getContext());
		return true;
	}

	/*
	 * query from database
	 * 
	 * @param uri provider Uri
	 * 
	 * @param projection its the items you need to select from table
	 */

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(ApplicationConstant.SONGS_TABLE_NAME);
		SQLiteDatabase dataBase = dataBaseHelper.getReadableDatabase();
		Cursor cursor = queryBuilder.query(dataBase, projection, selection,
				selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

}
