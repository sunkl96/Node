package com.example.mydaily;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydaily.LoginActivity;
import com.example.mydaily.MainActivity;
import com.example.mydaily.R;
//登录界面
public class LoginActivity extends Activity{
    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;
    
    private Button reg;
    
    private MyDatabaseHelperuser dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        reg = (Button) findViewById(R.id.register);
        dbHelper = new MyDatabaseHelperuser(this, "User.db", null, 2);
        //点击登录按钮触发的事件
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                int flag=0;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("users", null, null, null, null, null, null);
                //判断账号或密码是否为空
                if(account.equals("")||password.equals("")){
                	Toast.makeText(LoginActivity.this,"账号或密码不能为空",
                            Toast.LENGTH_SHORT).show();
                }else{//不为空就判断账号和密码是否正确
	                if (cursor != null) {
	                    while (cursor.moveToNext()) {
	                        String a = cursor.getString(cursor. getColumnIndex("account"));
	                        String p = cursor.getString(cursor. getColumnIndex("password"));
	                        if(a.equals(account)){
		                        if(p.equals(password))
		                        	flag=1;
	                        }
	                    }
	                    cursor.close();
	                }
	               if(flag==1){//正确就跳转到主界面
	            	   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
	                    startActivity(intent);
	                    finish();
	               }else{//不正确就提示
	            	   Toast.makeText(LoginActivity.this, "account or password is invalid",
	                            Toast.LENGTH_SHORT).show();
	               }
              } 
            }
            
        });
        //点击注册按钮触发的事件——跳转到注册页面
        reg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(LoginActivity.this, registerAcitivity.class);
                startActivity(intent);
                finish();
              } 
            
        });
    }


}
