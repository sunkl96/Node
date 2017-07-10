package com.example.mydaily;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadMe extends BaseActivity{
	private TextView readdate;
	private TextView readcontent;
	private Button Del;
	private Button Wri;
	private Button before;
	private Button next;
	 String rd;
     String rc;
	 ArrayList<String> stringListdate = new ArrayList<String>(); 
	 ArrayList<String> stringListcontent = new ArrayList<String>(); 
	 private int idnow=0;
	
	private MyDatabaseHelper dbHelper;
	
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //setTitle("��ѯ���");
	        setContentView(R.layout.item_read);
	        
	        readdate=(TextView)findViewById(R.id.read_date);
	        readcontent=(TextView)findViewById(R.id.read_content);
	        Del=(Button)findViewById(R.id.item_delete);
	        Wri=(Button)findViewById(R.id.item_write);
	        before=(Button)findViewById(R.id.item_before);
	        next=(Button)findViewById(R.id.item_next);
	        
	        //ʵ�����ֶ�ʱ���ֹ���
	        readcontent.setMovementMethod(ScrollingMovementMethod.getInstance());
	        
	        //��ȡ��MainActivity�����������ں�����
	        Intent intent=getIntent();
	        rd=intent.getStringExtra("readdate");
	        rc=intent.getStringExtra("readcontent");
	        
	        //��ʾ
	        readdate.setText(rd);
	        readcontent.setText(rc);
	        
	        //������
	        dbHelper=new MyDatabaseHelper(this,"Item.db",null,1);
	        SQLiteDatabase db=dbHelper.getWritableDatabase();
	        Cursor cursor=db.query("contact", null, null, null,null, null,null );	
	        if(cursor.moveToFirst()){
	        	do{
	        		stringListdate.add(cursor.getString(cursor.getColumnIndex("date")));
	        		stringListcontent.add(cursor.getString(cursor.getColumnIndex("content")));
	        	}while(cursor.moveToNext());
	        }
	        cursor.close();
	        //��ȡ��ǰ��id
	        for(idnow=0;idnow<stringListdate.size();++idnow){
	        	if(rd.equals(stringListdate.get(idnow)))
	        		break;
	        }
	        
	        
	        
	        
	        //���ɾ����ť����Ӧ�¼�
	        Del.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	//�����Ի���ȷ���Ƿ�ɾ�������ȷ���ʹ����ݿ���ɾ����ƪ���²��ص�MainActivity
	            	new AlertDialog.Builder(ReadMe.this)
	            	.setTitle("ɾ��")
	            	.setMessage("��ȷ��Ҫɾ����ƪ������")
	            	.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {  
	                    @Override  
	                    public void onClick(DialogInterface dialog,int which) {  
	                        SQLiteDatabase db=dbHelper.getWritableDatabase();
	                    	db.delete("Contact","date=?",new String[]{readdate.getText().toString()});
	                    	MainActivity.instance.finish();
	                    	Intent intent = new Intent(ReadMe.this,MainActivity.class);
	                        startActivity(intent);
	                        finish();
	                    }  
	                })  
	            	.setNegativeButton("ȡ��", null)
	            	.show();
	            }
	        });
	        Wri.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	Intent intent=new Intent();
        		    intent.setClass(ReadMe.this,UpdateActivity.class);
        		    intent.putExtra("udate", readdate.getText().toString());
        		    intent.putExtra("ucontent", readcontent.getText().toString());
        		    startActivity(intent);
        		    finish();
	            }
	        });
	        
	        before.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(idnow==0){
	            		Toast.makeText(ReadMe.this, "���Ѿ��ǵ�һƪ����",
	                            Toast.LENGTH_SHORT).show();
	            	}else{
	            		readdate.setText(stringListdate.get(idnow-1));
	        	        readcontent.setText(stringListcontent.get(idnow-1));
	        	        idnow--;
	            	}
	            	
	            	
	            }
	        });
	        
	        next.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	if(idnow==stringListdate.size()-1){
	            		Toast.makeText(ReadMe.this, "���Ѿ������һƪ����",
	                            Toast.LENGTH_SHORT).show();
	            	}else{
	            		readdate.setText(stringListdate.get(idnow+1));
	        	        readcontent.setText(stringListcontent.get(idnow+1));
	        	        idnow++;
	            	}
	            	
	            	
	            	
	            }
	        });
	    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
