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
        values.put("content","几十块的减肥了卡的几分啦的咖啡机的疯狂国考订房的交罚款圣诞节疯狂可视对讲发了看电视剧发卡机山东矿机疯狂拉升的借款方几十块的积分克里斯的积分卡加速度快放假了卡三等奖 宽带连接按付款的减肥了卡的几十块了房间爱看的洛杉矶富利卡的借款方了骄傲的上课了积分卢卡斯的积分拉丝机多看法就阿里山的积分上课的积分卡拉的家乐福卡的健身房垃圾袋顺路快递几分啦快圣诞节疯狂拉升的积分垃圾的说法了骄傲的上课几分啦速度快解放路的交罚款的精神科的减肥了圣诞节疯狂圣诞节疯狂圣诞节可视对讲福克斯的减肥了可视对讲放辣椒速度快福建省快乐大脚菲利克斯金卡戴珊看到几分拉克丝的减肥了会计师的空间发牢骚看到几分可视对讲福克斯的借款方可视对讲看得见啊福克斯的几率卡机的咖啡机可视对讲富利卡极度分裂卡机的咖啡机阿拉丁风景看到几分看到几分卡萨丁了附近的开发VC门口的积分快递费，发生的解放路卡打开交罚款打发打发看到几分看对方肯德基发了空间的所发生的解放路卡机的看法就爱看的积分垃圾的咖啡机考多少分开门的开发及地方肯德基发了的健康房间打开房间看电视九分裤圣诞节疯狂了圣诞节的激发了肯德基看风景奥斯卡的积分卡萨");
        db.insert("contact", null , values);
        values.clear();
        values.put("date","2016 12 2");
        values.put("content","按实际的恢复可骄傲的回复就卡的很费劲卡的很疯狂");
        db.insert("contact", null , values);
        values.clear();
        values.put("date","2016 12 3");
        values.put("content","水电费健康的减肥了卡圣诞节疯狂的女ueiowoe");
        db.insert("contact", null , values);
        values.clear();
        values.put("date","2016 12 4");
        values.put("content","水电费跨世纪的咖啡机考了多少几分啦看到几分了大开杀戒法拉第时间否");
        db.insert("contact", null , values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
