package com.trucha.utils;

import com.trucha.utils.HostContract.Host;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*
 * Class for the CRUD operations of a database
 */

public class DatabaseHandler {

	//Create operation
	public static long add(SQLiteDatabase db,String tableName,ContentValues values){
		Log.i("i","Create operation");
		long id = db.insert(tableName, null, values);
		db.close();
		return id;
	}
	
	//Read
	public static Cursor read(SQLiteDatabase db,String tableName,int id){
		Log.i("i","Read operation");
		String[] columns = {Host._ID,Host.COLUMN_NAME_ADDRESS};
		Log.d("d","Columns: "+columns);
		String selection = "_ID = ?";
		Log.d("d","Selection: "+selection);
		String[] selectionArgs = {String.valueOf(id)};
		Log.d("d","SelectionArgs: "+selectionArgs);
		Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null);
		cursor.moveToFirst();
		Log.i("i","Database read");
		db.close();
		return cursor;
	}
	
}
