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
        //获得当前的状态，对应设置按钮上的字
		typevalue=getSkinValue();
		if(typevalue==0){
			cbSetting.setText("启用");
		}else{
			cbSetting.setText("停用");
		}
		cbSetting.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				if (cbSetting.getText().equals("启用")) {
					// 夜间模式
					saveSkinValue(1);
					setTheme(R.style.AppTheme_Night);//防止getTheme()取出来的值是创建时候得值
					initView();
					init();
				} else {
					// 白天模式
					saveSkinValue(0);
					setTheme(R.style.AppTheme_Light);//防止getTheme()取出来的值是创建时候得值
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
	//改变模式后手动修改UI,不然出现闪屏
	private void initView(){
		TypedValue typedValue = new TypedValue();
		Theme theme = getTheme();
		theme.resolveAttribute(R.attr.gray_3_double, typedValue, true);   
		tv_title.setTextColor(getResources().getColor(typedValue.resourceId));
		theme.resolveAttribute(R.attr.source_bg, typedValue, true);   
		llNight.setBackgroundDrawable(getResources().getDrawable(typedValue.resourceId));  
	}
	//点击返回键返回主页
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
