package com.example.mydaily;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class QueryResult extends BaseActivity{
	 private List<item> itemLists = new ArrayList<item>();
	 private String key;
     private String type;
     private Button set;
     private int count=0;
     private TextView result;
     private ListView listView;
	
	private MyDatabaseHelper dbHelper;
	
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setTitle("查询结果");
	        setContentView(R.layout.query_result);
	        set=(Button)findViewById(R.id.bt_set);
	        set.setVisibility(View.GONE);
	        result=(TextView)findViewById(R.id.result);
	        listView=(ListView) findViewById(R.id.list_view);
	        result.setVisibility(View.GONE);
	        //获取从queryitem传过来的关键字和类型
	        Intent intent=getIntent();
	        key=intent.getStringExtra("key");
	        type=intent.getStringExtra("type");
	        update();
	        
	    }
	
	public void update() {
        itemLists.clear();
    	dbHelper=new MyDatabaseHelper(this,"Item.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //如果类型是日期的话就按以什么什么开头查询
        if(type.equals("date")){
        	//Cursor cursor = db.query("contact", new String[]{"data"}, "data"+"  LIKE ? ",new String[] { "%" + key + "%" }, null, null, null);
        	
        	Cursor cursor=db.query("contact", null, null, null,null, null,null );	
        	if(cursor.moveToFirst()){
            	do{
            		
            		if(cursor.getString(cursor.getColumnIndex("date")).startsWith(key)){
            			item a;
            			if(cursor.getString(cursor.getColumnIndex("content")).length()>15){
                			a=new item(cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("content")),
                    				cursor.getString(cursor.getColumnIndex("content")).substring(0,15)+"...");
                		}else{//如果类型是内容的话就按关键字搜索
                			a=new item(cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("content")),
                    				cursor.getString(cursor.getColumnIndex("content")));
                		}
                		itemLists.add(a);
                		count++;
                    }
            	}while(cursor.moveToNext());
            	
            }
        	cursor.close();
        	//Log.d("x"," "+count);
        	if(count==0){
        		listView.setVisibility(View.GONE);
        		result.setVisibility(View.VISIBLE);
        		
        	}
        	
        }else if(type.equals("content")){
        	//Cursor cursor = db.query("contact", new String[]{"content"},"date"+"  like '%" + key + "%'", null, null, null, null);
        	//String current_sql_sel = "select * from "+"contact" +"where "+"content"+" like '%"+key+"%'";
        	//Cursor cursor = db.rawQuery(current_sql_sel, null);
        	//Cursor cursor=db.rawQuery("SELECT _id, date,content FROM contact WHERE content LIKE '%" + key +"%'",null); 
        	Uri uri =Uri.parse("content://com.example.mydaily.provider/contact");
        	
          	Cursor cursor = getContentResolver().query(uri,null,"content like ?",new String[]{"%"+key+"%"},null);
        	if(cursor.moveToFirst()){
            	do{
            		item a;
            		if(cursor.getString(cursor.getColumnIndex("content")).length()>15){
            			a=new item(cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("content")),
                				cursor.getString(cursor.getColumnIndex("content")).substring(0,15)+"...");
            		}else{
            			a=new item(cursor.getString(cursor.getColumnIndex("date")),cursor.getString(cursor.getColumnIndex("content")),
                				cursor.getString(cursor.getColumnIndex("content")));
            		}
            		itemLists.add(a);
            		count++;
            	}while(cursor.moveToNext());
            }
        	cursor.close();
        	
        	if(count==0){
        		listView.setVisibility(View.GONE);
        		result.setVisibility(View.VISIBLE);
        	}
        	
        }
        
        
        itemAdapter adapter=new itemAdapter(QueryResult.this,R.layout.items,itemLists);
        
        listView.setAdapter(adapter);
        //点击listview到readme界面
        listView.setOnItemClickListener(new OnItemClickListener(){
        	
        	@Override
        	public void onItemClick(AdapterView<?> parent,View view,
        			int position,long id){
        		item i=itemLists.get(position);
        		Intent intent=new Intent();
        		    intent.setClass(QueryResult.this,ReadMe.class);
        		    intent.putExtra("readdate", i.getDate());
        		    intent.putExtra("readcontent", i.getContent());
        		    startActivity(intent);
        		    //finish();
        		
        	}
        });
    }
	//点击返回键回到主界面
	public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
        	Intent intent = new Intent(QueryResult.this,MainActivity.class);
            startActivity(intent);
            finish();
  
        }  
          
        return false;  
          
    } 
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	} 
}
