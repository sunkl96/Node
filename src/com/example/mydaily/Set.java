package com.example.mydaily;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class Set extends BaseActivity {
	private Button cbSetting;
	private TextView tv_title;
	private View llNight;
	int typevalue;
	private Button set;
	private Button toupdate;
	
	@Override
	public void init() {
		setContentView(R.layout.set_nightmode);
		llNight = findViewById(R.id.ll_night);
		tv_title = (TextView) findViewById(R.id.tv_title);
		cbSetting = (Button) findViewById(R.id.cb_setting);
		toupdate= (Button)findViewById(R.id.toupdate);
		set=(Button)findViewById(R.id.bt_set);
        set.setVisibility(View.GONE);
        //��õ�ǰ��״̬����Ӧ���ð�ť�ϵ���
		typevalue=getSkinValue();
		if(typevalue==0){
			cbSetting.setText("����");
		}else{
			cbSetting.setText("ͣ��");
		}
		cbSetting.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if (cbSetting.getText().equals("����")) {
					// ҹ��ģʽ
					saveSkinValue(1);
					setTheme(R.style.AppTheme_Night);//��ֹgetTheme()ȡ������ֵ�Ǵ���ʱ���ֵ
					initView();
					init();
				} else {
					// ����ģʽ
					saveSkinValue(0);
					setTheme(R.style.AppTheme_Light);//��ֹgetTheme()ȡ������ֵ�Ǵ���ʱ���ֵ
					initView(); 
					init();
				}
			}
		});
		
		toupdate.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(Set.this,UpdatePassword.class);
	            startActivity(intent);
	            finish();
			}
		});
		
		
		
	}
	//�ı�ģʽ���ֶ��޸�UI,��Ȼ��������
	private void initView(){
		TypedValue typedValue = new TypedValue();
		Theme theme = getTheme();
		theme.resolveAttribute(R.attr.gray_3_double, typedValue, true);   
		tv_title.setTextColor(getResources().getColor(typedValue.resourceId));
		theme.resolveAttribute(R.attr.source_bg, typedValue, true);   
		llNight.setBackgroundDrawable(getResources().getDrawable(typedValue.resourceId));  
	}
	//������ؼ�������ҳ
	public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
        	Intent intent = new Intent(Set.this,MainActivity.class);
            startActivity(intent);
            finish();
  
        }  
          
        return false;  
          
    }  
}
