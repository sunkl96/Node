package com.example.mydaily;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyDatabaseHelperuser extends SQLiteOpenHelper{
	
	
	/////dd///
	public static final String CREATE_ITEM= "create table users ("
			+ "id integer primary key autoincrement, "
			+ "account text, "
			+ "password text)";
	////dd//

    private Context mContext;

    public MyDatabaseHelperuser(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL(CREATE_ITEM);
		ContentValues values=new  ContentValues();
        
//        values.clear();
        values.put("account","sun");
        values.put("password","123");
        db.insert("users", null , values);
        ////d/d///////
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
