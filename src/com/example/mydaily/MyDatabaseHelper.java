package com.example.mydaily;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyDatabaseHelper extends SQLiteOpenHelper{
	public static final String CREATE_ITEM = "create table contact ("
			+ "id integer primary key autoincrement, "
			+ "date text, "
			+ "content text)";


    private Context mContext;

    public MyDatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL(CREATE_ITEM);
		ContentValues values=new  ContentValues();
        values.put("date","2016 12 1");
        values.put("content","��ʮ��ļ����˿��ļ������Ŀ��Ȼ��ķ����������Ľ�����ʥ���ڷ����ӶԽ����˿����Ӿ緢����ɽ�������������Ľ���ʮ��Ļ��ֿ���˹�Ļ��ֿ����ٶȿ�ż��˿����Ƚ� ������Ӱ�����ļ����˿��ļ�ʮ���˷��䰮������ɼ�������Ľ��˽������Ͽ��˻���¬��˹�Ļ�����˿���࿴���Ͱ���ɽ�Ļ����ϿεĻ��ֿ����ļ��ָ����Ľ���������˳·��ݼ�������ʥ���ڷ�������Ļ���������˵���˽������Ͽμ������ٶȿ���·�Ľ�����ľ���Ƶļ�����ʥ���ڷ��ʥ���ڷ��ʥ���ڿ��ӶԽ�����˹�ļ����˿��ӶԽ��������ٶȿ츣��ʡ���ִ�ŷ�����˹�𿨴�ɺ������������˿�ļ����˻��ʦ�Ŀռ䷢��ɧ�������ֿ��ӶԽ�����˹�Ľ����ӶԽ����ü�������˹�ļ��ʿ����Ŀ��Ȼ����ӶԽ����������ȷ��ѿ����Ŀ��Ȼ��������羰�������ֿ������ֿ������˸����Ŀ���VC�ſڵĻ��ֿ�ݷѣ������Ľ��·���򿪽�����򷢴򷢿������ֿ��Է��ϵ»����˿ռ���������Ľ��·�����Ŀ����Ͱ����Ļ��������Ŀ��Ȼ������ٷֿ��ŵĿ������ط��ϵ»����˵Ľ�������򿪷��俴���Ӿŷֿ�ʥ���ڷ����ʥ���ڵļ����˿ϵ»����羰��˹���Ļ��ֿ���");
        db.insert("contact", null , values);
        values.clear();
        values.put("date","2016 12 2");
        values.put("content","��ʵ�ʵĻָ��ɽ����Ļظ��Ϳ��ĺܷѾ����ĺܷ��");
        db.insert("contact", null , values);
        values.clear();
        values.put("date","2016 12 3");
        values.put("content","ˮ��ѽ����ļ����˿�ʥ���ڷ���Ůueiowoe");
        db.insert("contact", null , values);
        values.clear();
        values.put("date","2016 12 4");
        values.put("content","ˮ��ѿ����͵Ŀ��Ȼ����˶��ټ��������������˴�ɱ�䷨����ʱ���");
        db.insert("contact", null , values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
