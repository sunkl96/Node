package com.example.mydaily;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import com.example.constant.Global.SkinType;

public abstract class BaseActivity extends Activity {
	protected int skin;
	public Context mContext;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setThemeMode(getSkinTypeValue());
		super.onCreate(savedInstanceState);
		init();
	}
	public abstract void init();
	//�����û�ѡ���ģʽ���ò�����ɫ
	protected void setThemeMode(SkinType skinType) {
		switch (skinType) {
		case Light:
			setTheme(R.style.AppTheme_Light);
			break;
		case Night:
			setTheme(R.style.AppTheme_Night);
			break;
		default:
			setTheme(R.style.AppTheme_Light);
			break;
		}
	}
	//��ȡ�û�ѡȡ��ģʽ
	protected SkinType getSkinTypeValue() {
		if (sp == null) {
			sp = getSharedPreferences("AppSkinType", Context.MODE_PRIVATE);
		}
		int i = sp.getInt("AppSkinTypeValue", 0);
		switch (i) {
		case 0:
			return SkinType.Light;
        case 1:
			return SkinType.Night;
		default:
			break;
		}
		return SkinType.Light;
	}
	//�����û���ѡȡ��ģʽ
	public void saveSkinValue(int skin) {
		if (sp == null) {
			sp = getSharedPreferences("AppSkinType", Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putInt("AppSkinTypeValue", skin);
		editor.commit();
	}
	
	public int getSkinValue() {
		return sp.getInt("AppSkinTypeValue", 0);
	}
	
}

