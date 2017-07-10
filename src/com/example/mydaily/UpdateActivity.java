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
        //����������Ϊ���ɱ༭
        da.setFocusable(false);
        da.setFocusableInTouchMode(false);
        //�����ݵĹ���Ƶ�ĩβ
        Editable etext = con.getText();
        Selection.setSelection(etext, etext.length());
        
        Editable etextd = da.getText();
        Selection.setSelection(etextd, etextd.length());
        
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	//������������͵绰���µ����ݿ��contact���У�
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
            // �����˳��Ի���  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // ���öԻ������  
            isExit.setTitle("��ʾ");  
            // ���öԻ�����Ϣ  
            isExit.setMessage("��û�б������ڱ༭����ƪ��־����ȷ��Ҫ�˳���");  
            // ���ѡ��ť��ע�����  
            isExit.setButton("ȷ��", listener);  
            isExit.setButton2("ȡ��", listener);  
            // ��ʾ�Ի���  
            isExit.show();  
  
        }  
          
        return false;  
          
    }  
    /**�����Ի��������button����¼�*/  
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����  
            	Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "ȡ��"�ڶ�����ťȡ���Ի���  
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

