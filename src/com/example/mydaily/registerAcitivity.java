package com.example.mydaily;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerAcitivity extends Activity{
    private EditText accountEdit;

    private EditText passwordEdit;

    private Button reg;
    
    private MyDatabaseHelperuser dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        accountEdit = (EditText) findViewById(R.id.account_r);
        passwordEdit = (EditText) findViewById(R.id.password_r);
        reg = (Button) findViewById(R.id.register_bt);
        dbHelper = new MyDatabaseHelperuser(this, "User.db", null, 2);
        reg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                int flag=0;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("users", null, null, null, null, null, null);
                //�ж��˺������Ƿ�Ϊ��
                if(account.equals("")||password.equals("")){
                	Toast.makeText(registerAcitivity.this,"�˺Ż����벻��Ϊ��",
                            Toast.LENGTH_SHORT).show();
                }else{//��Ϊ���ж��˺��Ƿ����
	                if (cursor != null) {
	                    while (cursor.moveToNext()) {
	                        String a = cursor.getString(cursor. getColumnIndex("account"));
	                        String p = cursor.getString(cursor. getColumnIndex("password"));
	                        if(a.equals(account)){
		                        	flag=1;
	                        }
	                    }
	                    cursor.close();
	                }
	               if(flag==0){//�����ھʹ������ݿ���ת����¼����
	            	   ContentValues values=new  ContentValues();
	            	   values.put("account",account);
	                   values.put("password",password);
	                   db.insert("users", null , values);
	            	   Intent intent = new Intent(registerAcitivity.this, LoginActivity.class);
	                    startActivity(intent);
	                    finish();
	               }else{//�Ѵ��ھ���ʾ��ʾ
	            	   Toast.makeText(registerAcitivity.this, "�˺��Ѵ��ڣ�������һ��",
	                            Toast.LENGTH_SHORT).show();
	               }
                }
            } 
            
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
        	Intent intent=new Intent();
		    intent.setClass(registerAcitivity.this,LoginActivity.class);
		    startActivity(intent);
		    finish();
  
        }  
          
        return false;  
          
    }  

}
