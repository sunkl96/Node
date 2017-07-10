package com.example.mydaily;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends BaseActivity {
	
	private EditText da;
    private EditText con;
    private Button add;
    private MyDatabaseHelper dbHelper;
    String ud;
    String uc;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_add);
        dbHelper=new MyDatabaseHelper(this,"Item.db",null,1);
        da = (EditText) findViewById(R.id.add_date);
        con = (EditText) findViewById(R.id.add_content);
        add = (Button) findViewById(R.id.add);
        
        Intent intent=getIntent();
        ud=intent.getStringExtra("udate");
        uc=intent.getStringExtra("ucontent");
        
        da.setText(ud);
        con.setText(uc);
        //对日期设置为不可编辑
        da.setFocusable(false);
        da.setFocusableInTouchMode(false);
        //将内容的光标移到末尾
        Editable etext = con.getText();
        Selection.setSelection(etext, etext.length());
        
        Editable etextd = da.getText();
        Selection.setSelection(etextd, etextd.length());
        
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	//将输入的姓名和电话更新到数据库的contact表中；
                String na = da.getText().toString();
                String ph = con.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("content", ph);
                db.update("contact", values, "date = ?", new String[] {ud});
                
                MainActivity.instance.finish();
                
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        
 
    }
	
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
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
            	Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
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

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}    
 

}

