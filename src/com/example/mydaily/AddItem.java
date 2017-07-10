package com.example.mydaily;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
//新增记事页面
public class AddItem extends BaseActivity {
	
	private EditText da;
    private EditText con;
    private Button add;
    private MyDatabaseHelper dbHelper;
    private String s; 
    
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_add);
        dbHelper=new MyDatabaseHelper(this,"Item.db",null,1);
        da = (EditText) findViewById(R.id.add_date);
        con = (EditText) findViewById(R.id.add_content);
        add = (Button) findViewById(R.id.add);
        
        //自动获取当天的日期显示在日期那一栏
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);//获取年份
        int month=ca.get(Calendar.MONTH)+1;//获取月份
        int day=ca.get(Calendar.DATE);//获取日
        da.setText(year+" "+month+" "+day);
        
       //将日期那一栏的光标移到末尾
        Editable etext = da.getText();
        Selection.setSelection(etext, etext.length());
        //点击保存按钮时触发事件
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	//将输入的姓名和电话更新到数据库的contact表中；
                String na = da.getText().toString();
                String ph = con.getText().toString();
                int flag=0;
                SQLiteDatabase db=dbHelper.getWritableDatabase();   
                //判断获取到用户选择的日期之前有没有写过，如果写过flag=1
                Cursor cursor=db.query("contact", null, null, null,null, null,null );	
                if(cursor.moveToFirst()){
                	do{
                		if(cursor.getString(cursor.getColumnIndex("date")).equals(na)){
                			flag=1;
                			s=cursor.getString(cursor.getColumnIndex("content"));
                		}
                	}while(cursor.moveToNext());
                }
                //flag=0即没有写过，那么将日期和内容存入数据库中
                if(flag==0){
	        		ContentValues values=new  ContentValues();
	                values.put("date",na);
	                values.put("content",ph);
	                db.insert("contact", null , values);
	                
	                Intent intent = new Intent(AddItem.this,MainActivity.class);
	                startActivity(intent);
	                finish();
                }else{//如果写过则弹出对话框
                	new AlertDialog.Builder(AddItem.this)   
                	.setTitle("提示")  
                	.setMessage("你已经写过这天的日志了诶，要不要去编辑？")  
                	.setPositiveButton("去编辑", listen)  
                	.setNegativeButton("不，我选错日期了", listen)  
                	.show();  
                	
                }
                

            }
        });
        
        //再点击日期那一栏的时候弹出日历，再把用户选择的日期按一定格式显示在输入框中
        
        da.setOnTouchListener(new OnTouchListener() {  
      	  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {  
                if (event.getAction() == MotionEvent.ACTION_DOWN) {  
                    showDatePickDlg();  
                    return true;  
                }  
                return false;  
            }  
        });  
        da.setOnFocusChangeListener(new OnFocusChangeListener() {  
  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
                if (hasFocus==true) {  
                    showDatePickDlg();  
                }  
            }  
        });  
        
 
    }
	//当点击返回键时弹出是否退出编辑的对话框
	public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // 创建退出对话框  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // 设置对话框标题  
            isExit.setTitle("提示");  
            // 设置对话框消息  
            isExit.setMessage("还没有保存正在编辑的这篇日志诶，确定要退出吗？");  
            // 添加选择按钮并注册监听  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            // 显示对话框  
            isExit.show();  
  
        }  
          
        return false;  
          
    }  
    /**监听对话框里面的button点击事件*/  
    DialogInterface.OnClickListener listen = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// 去编辑，将跳转到更改页面并将当前页面写好的内容添加到内容末尾
            	Intent intents=new Intent();
    		    intents.setClass(AddItem.this,UpdateActivity.class);
    		    intents.putExtra("udate", da.getText().toString());
    		    intents.putExtra("ucontent", s+con.getText().toString());
    		    startActivity(intents);
    		    finish();
                break;  
            case AlertDialog.BUTTON_NEGATIVE://不，我选错日期了
                break;  
            default:  
                break;  
            }  
        }  
    };
    
    
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
            	Intent intent = new Intent(AddItem.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
                break;  
            default:  
                break;  
            }  
        }  
    };

    protected void showDatePickDlg() {  
        Calendar calendar = Calendar.getInstance();  
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddItem.this, new OnDateSetListener() {  
  
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {  
            	AddItem.this.da.setText(year + " " + (monthOfYear+1) + " " + dayOfMonth);  
            }  
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));  
        datePickerDialog.show();  
  
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}    
 

}

