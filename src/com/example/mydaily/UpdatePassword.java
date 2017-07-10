package com.example.mydaily;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePassword extends Activity{
	 private EditText accountEdit;

	    private EditText passwordEdit;
	    private EditText passwordEditn;

	    private Button sure;
	    
	    private MyDatabaseHelperuser dbHelper;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.update_password);
	        accountEdit = (EditText) findViewById(R.id.account_u);
	        passwordEdit = (EditText) findViewById(R.id.passwordbefore);
	        passwordEditn = (EditText) findViewById(R.id.passwordnow);
	        sure = (Button) findViewById(R.id.besure);
	        dbHelper = new MyDatabaseHelperuser(this, "User.db", null, 2);
	        sure.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                String account = accountEdit.getText().toString();
	                String passwordb = passwordEdit.getText().toString();
	                String passwordn = passwordEditn.getText().toString();
	                int flag=0;
	                SQLiteDatabase db = dbHelper.getWritableDatabase();
	                Cursor cursor = db.query("users", null, null, null, null, null, null);
	                //判断账号密码是否为空
	                if(account.equals("")||passwordb.equals("")||passwordn.equals("")){
	                	Toast.makeText(UpdatePassword.this,"账号或原密码或新密码不能为空",
	                            Toast.LENGTH_SHORT).show();
	                }else{//不为空判断账号是否存在
		                if (cursor != null) {
		                    while (cursor.moveToNext()) {
		                        String a = cursor.getString(cursor. getColumnIndex("account"));
		                        String p = cursor.getString(cursor. getColumnIndex("password"));
		                        if(a.equals(account)){
			                        	flag=1;
			                        	if(p.equals(passwordb))
			                        		flag=2;

		                        }
		                    }
		                    cursor.close();
		                }
		               if(flag==0){//不存在
		            	   Toast.makeText(UpdatePassword.this,"账号不存在！",
		                            Toast.LENGTH_SHORT).show();
		               }else if(flag==1){//存在但密码错误
		            	   Toast.makeText(UpdatePassword.this, "原密码错误",
		                            Toast.LENGTH_SHORT).show();
		               }else{
		            	   
		            	   ContentValues values = new ContentValues();
		                   values.put("password", passwordn);
		                   db.update("users", values, "account = ?", new String[] {account});
		                   Toast.makeText(UpdatePassword.this,"修改成功！",
		                            Toast.LENGTH_SHORT).show();
		                   Intent intent = new Intent(UpdatePassword.this,MainActivity.class);
		                   startActivity(intent);
		                   finish();
		               }
	                }
	            } 
	            
	        });
	    }
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	        	Intent intent = new Intent(UpdatePassword.this,MainActivity.class);
	            startActivity(intent);
	            finish();
	  
	        }  
	          
	        return false;  
	          
	    }  

}
