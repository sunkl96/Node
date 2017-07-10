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
//��¼����
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
        //�����¼��ť�������¼�
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                int flag=0;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("users", null, null, null, null, null, null);
                //�ж��˺Ż������Ƿ�Ϊ��
                if(account.equals("")||password.equals("")){
                	Toast.makeText(LoginActivity.this,"�˺Ż����벻��Ϊ��",
                            Toast.LENGTH_SHORT).show();
                }else{//��Ϊ�վ��ж��˺ź������Ƿ���ȷ
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
	               if(flag==1){//��ȷ����ת��������
	            	   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
	                    startActivity(intent);
	                    finish();
	               }else{//����ȷ����ʾ
	            	   Toast.makeText(LoginActivity.this, "account or password is invalid",
	                            Toast.LENGTH_SHORT).show();
	               }
              } 
            }
            
        });
        //���ע�ᰴť�������¼�������ת��ע��ҳ��
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
