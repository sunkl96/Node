package com.example.mydaily;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

//主界面
public class MainActivity extends BaseActivity {

	 private List<item> itemList = new ArrayList<item>();
	 private MyDatabaseHelper dbHelper;
	 private Button add;
	 private Button query;
	 private Button set;
	 private ListView listView;
	 private TextView tip;
	 private int count=0;
	 
	 public static MainActivity instance = null;  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        instance = this;
        
        add=(Button)findViewById(R.id.bt_add);
        query=(Button)findViewById(R.id.bt_query);
        listView=(ListView) findViewById(R.id.list_view);
        set=(Button)findViewById(R.id.bt_set);
        tip=(TextView)findViewById(R.id.tip);
        tip.setVisibility(View.GONE);
        update();
        
        
        //点击新增记事按钮触发的事件——跳转到添加记事页面
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(MainActivity.this,AddItem.class);
                startActivity(intent);
                finish();
            }
        });
        //点击查询按钮触发的事件——跳转到查询页面
        query.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(MainActivity.this,QueryItem.class);
                startActivity(intent);
                finish();
            }
        });
        //点击右上角设置按钮跳转到设置界面
        set.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,Set.class);
                startActivity(intent);
                finish();
			}
        	
        });
        
    }
        ////
    public void update() {
        itemList.clear();
    	dbHelper=new MyDatabaseHelper(this,"Item.db",null,1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        
        Cursor cursor=db.query("contact", null, null, null,null, null,null );	
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
        		itemList.add(a);
        		count++;
        	}while(cursor.moveToNext());
        }
        cursor.close();
        
        if(count==0){
    		listView.setVisibility(View.GONE);
    		tip.setVisibility(View.VISIBLE);
    	}
        
        itemAdapter adapter=new itemAdapter(MainActivity.this,R.layout.items,itemList);
        
        listView.setAdapter(adapter);
        //点击listview跳转到readme页面
        listView.setOnItemClickListener(new OnItemClickListener(){
        	
        	@Override
        	public void onItemClick(AdapterView<?> parent,View view,
        			int position,long id){
        		item i=itemList.get(position);
        		Intent intent=new Intent();
        		    intent.setClass(MainActivity.this,ReadMe.class);
        		    intent.putExtra("readdate", i.getDate());
        		    intent.putExtra("readcontent", i.getContent());
        		    startActivity(intent);
        		    
        	}
        });
    }
		@Override
		public void init() {
			// TODO Auto-generated method stub
			
		}   
    


}
