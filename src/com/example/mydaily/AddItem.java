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
//��������ҳ��
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
        
        //�Զ���ȡ�����������ʾ��������һ��
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);//��ȡ���
        int month=ca.get(Calendar.MONTH)+1;//��ȡ�·�
        int day=ca.get(Calendar.DATE);//��ȡ��
        da.setText(year+" "+month+" "+day);
        
       //��������һ���Ĺ���Ƶ�ĩβ
        Editable etext = da.getText();
        Selection.setSelection(etext, etext.length());
        //������水ťʱ�����¼�
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	//������������͵绰���µ����ݿ��contact���У�
                String na = da.getText().toString();
                String ph = con.getText().toString();
                int flag=0;
                SQLiteDatabase db=dbHelper.getWritableDatabase();   
                //�жϻ�ȡ���û�ѡ�������֮ǰ��û��д�������д��flag=1
                Cursor cursor=db.query("contact", null, null, null,null, null,null );	
                if(cursor.moveToFirst()){
                	do{
                		if(cursor.getString(cursor.getColumnIndex("date")).equals(na)){
                			flag=1;
                			s=cursor.getString(cursor.getColumnIndex("content"));
                		}
                	}while(cursor.moveToNext());
                }
                //flag=0��û��д������ô�����ں����ݴ������ݿ���
                if(flag==0){
	        		ContentValues values=new  ContentValues();
	                values.put("date",na);
	                values.put("content",ph);
	                db.insert("contact", null , values);
	                
	                Intent intent = new Intent(AddItem.this,MainActivity.class);
	                startActivity(intent);
	                finish();
                }else{//���д���򵯳��Ի���
                	new AlertDialog.Builder(AddItem.this)   
                	.setTitle("��ʾ")  
                	.setMessage("���Ѿ�д���������־������Ҫ��Ҫȥ�༭��")  
                	.setPositiveButton("ȥ�༭", listen)  
                	.setNegativeButton("������ѡ��������", listen)  
                	.show();  
                	
                }
                

            }
        });
        
        //�ٵ��������һ����ʱ�򵯳��������ٰ��û�ѡ������ڰ�һ����ʽ��ʾ���������
        
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
	//��������ؼ�ʱ�����Ƿ��˳��༭�ĶԻ���
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
    DialogInterface.OnClickListener listen = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// ȥ�༭������ת������ҳ�沢����ǰҳ��д�õ�������ӵ�����ĩβ
            	Intent intents=new Intent();
    		    intents.setClass(AddItem.this,UpdateActivity.class);
    		    intents.putExtra("udate", da.getText().toString());
    		    intents.putExtra("ucontent", s+con.getText().toString());
    		    startActivity(intents);
    		    finish();
                break;  
            case AlertDialog.BUTTON_NEGATIVE://������ѡ��������
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
            case AlertDialog.BUTTON_POSITIVE:// "ȷ��"��ť�˳�����  
            	Intent intent = new Intent(AddItem.this,MainActivity.class);
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

