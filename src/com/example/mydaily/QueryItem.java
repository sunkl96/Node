package com.example.mydaily;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
//查询界面
public class QueryItem extends BaseActivity{
	private EditText key;
	private RadioButton rdate;
	private RadioButton rcontent;
	private String skey;
	private String stype;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("选择查询条件");
        setContentView(R.layout.query_item);
        
        key = (EditText) findViewById(R.id.key);
        Button queryData = (Button) findViewById(R.id.query);
        rdate=(RadioButton)findViewById(R.id.radioDate);
        rcontent=(RadioButton)findViewById(R.id.radioContent);
        
        
        //弹出日历
        key.setOnTouchListener(new OnTouchListener() {  
        	  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {  
                if (event.getAction() == MotionEvent.ACTION_DOWN&&rdate.isChecked()==true) {  
                    showDatePickDlg();  
                    return true;  
                }  
                return false;  
            }  
        });  
        key.setOnFocusChangeListener(new OnFocusChangeListener() {  
  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
                if (hasFocus&&rdate.isChecked()==true) {  
                    showDatePickDlg();  
                }  
            }  
        });  
        
        
        //点击查询按钮将选择查询的类型和查询的关键字传给查询结果页面
        queryData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	//传递数据
                if(rdate.isChecked()==true){
                	skey=key.getText().toString();
                	stype="date";
	                
                } 
                
                if(rcontent.isChecked()==true){
                	skey=key.getText().toString();
                	stype="content";
	                
                } 
                
                Intent intent=new Intent();
    		    intent.setClass(QueryItem.this,QueryResult.class);
    		    intent.putExtra("key",skey);
    		    intent.putExtra("type",stype);
    		    startActivity(intent);
    		    finish();
            }
        });
 
    }
	
	protected void showDatePickDlg() {  
        Calendar calendar = Calendar.getInstance();  
        DatePickerDialog datePickerDialog = new DatePickerDialog(QueryItem.this, new OnDateSetListener() {  
  
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {  
            	QueryItem.this.key.setText(year + " " + (monthOfYear+1) + " " + dayOfMonth);  
            }  
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));  
        datePickerDialog.show();  
  
    }
	
	public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
        	Intent intent=new Intent();
		    intent.setClass(QueryItem.this,MainActivity.class);
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

