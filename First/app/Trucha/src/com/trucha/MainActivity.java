package com.trucha;

import java.util.ArrayList;

import com.trucha.utils.DatabaseHandler;
//import com.trucha.utils.DatabaseHandler;
import com.trucha.utils.HostContract.Host;
import com.trucha.utils.URLConstants;
import com.trucha.utils.HostHelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		setContentView(R.layout.activity_settings);		
		//new GetCategories().execute();
	}

	public void saveHost(View view){
		Log.i("i","Entered saveHost method");
		EditText edt_txt_host = (EditText) this.findViewById(R.id.edt_txt_host); 
		String hostAddress = edt_txt_host.getText().toString();
		Log.d("d", hostAddress);
		HostHelper dbHostHelper = new HostHelper(this);
		ArrayList<Object> elements = new ArrayList<Object>();
		//elements[0].0 
		elements.add(hostAddress);
		//elements[0].1
		elements.add(dbHostHelper);
		//new GetCategories().execute(hostAddress);
		new SaveHost().execute(elements);
	}
	
	private class SaveHost extends AsyncTask<ArrayList<Object>,Void,Void>{
		
		@Override
		protected Void doInBackground(ArrayList<Object>... elements){
			Log.i("i","Entered asynctask for saving host address");
			SQLiteDatabase db = ((HostHelper) elements[0].get(1)).getWritableDatabase();
			((HostHelper) elements[0].get(1)).onUpgrade(db, 1, 2);
			ContentValues values = new ContentValues();
			values.put(Host._ID, 1);
			values.put(Host.COLUMN_NAME_ADDRESS,(String) elements[0].get(0));
			long response = DatabaseHandler.add(db, Host.TABLE_NAME, values);
			if(response != -1){
				Log.i("i","Insert complete");
			}
			/*SQLiteDatabase db = ((HostHelper) elements[0].get(1)).getReadableDatabase();
			Log.i("i","Starting to read from db");
			Cursor cursor = DatabaseHandler.read(db, Host.TABLE_NAME, 1);
			if(cursor.getCount() != 0){
				Log.d("i","Cursor: "+cursor);
				Log.d("d","_ID: "+cursor.getLong(0));
				Log.d("d","Address: "+cursor.getString(1));
			}*/
			/*Log.i("i","Check if db is empty");
			SQLiteDatabase db = ((HostHelper) elements[0].get(1)).getReadableDatabase();
			String query = "SELECT * FROM host WHERE _ID = 1";
			Cursor cursor = db.rawQuery(query, null);
			cursor.moveToFirst();
			if(cursor.getCount() == 0){
				Log.i("i","DB is empty");
				Log.i("i","Create db");
				db = ((HostHelper) elements[0].get(1)).getWritableDatabase();
				Log.i("i","Done");
				Log.i("i","Creating values");
				ContentValues values = new ContentValues();
				values.put(Host._ID, 1);
				values.put(Host.COLUMN_NAME_ADDRESS,(String) elements[0].get(0));
				Log.i("i","Values created: "+values);
				Log.i("i","Making insert to db");
				Long rowId;
				rowId = db.insert(Host.TABLE_NAME,null,values);
				Log.i("i","Finished insert,primary key: "+rowId);
			}
			else{
				db = ((HostHelper) elements[0].get(1)).getWritableDatabase();
				Log.i("i","Update value of the host address");
				query = "UPDATE "+Host.TABLE_NAME+" SET "+Host.COLUMN_NAME_ADDRESS+" = \""+(String) elements[0].get(0)+
						"\" WHERE "+Host._ID+" = 1";
				Log.d("d","Update query: "+query);
				db.rawQuery(query, null);
				query = "SELECT * FROM host WHERE _ID = 1";
				Cursor c = db.rawQuery(query, null);
				Log.d("d","Cursor: "+c);
				c.moveToFirst();
				Log.d("d","Value of _ID of the cursor: "+c.getLong(0));
				Log.d("d","Value of the host address: "+c.getString(1));
			}*/
			return null;
		}
		
	}
	
	private class GetCategories extends AsyncTask<String,Void,Void>{
		
		@Override
		protected Void doInBackground(String... hostAddress){
			Log.i("i","Entered asynctask");
			ServiceHandler sh = new ServiceHandler();
			String url = hostAddress[0] + URLConstants.URL_CATEGORY_INDEX;
			Log.d("d","Valor de url: "+url);
			String jsonString = sh.makeGetServiceCall(url);
			//Log.d("d","Value url: "+"http://api.androidhive.info/contacts/");
			//String jsonString = sh.makeGetServiceCall("http://api.androidhive.info/contacts/");
			//Log.d("d","Value url: "+URLConstants.URL_CATEGORY_INDEX);
			//String jsonString = sh.makeGetServiceCall(URLConstants.URL_CATEGORY_INDEX);
			Log.d("d","Value json: "+jsonString);
			return null;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
