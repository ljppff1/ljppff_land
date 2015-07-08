package com.compass.hk.login_register;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;

public class Login_Regiester extends TabActivity {

	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login__regiester);
		initTabHost();
	}
	public  void btn_back(View v) {
		// TODO Auto-generated method stub
       finish();
	}
	public  void btn_home(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,MainActivity.class));
		finish();
	}
	private void initTabHost() {

		mTabHost = getTabHost();
		TabSpec tabSpec1=mTabHost.newTabSpec("αιT");
		View  view1=getLayoutInflater().inflate(R.layout.item_login_tab, null);
		tabSpec1.setIndicator(view1);
		Intent intent1 = new Intent(this,LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec1.setContent(intent1);
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2=mTabHost.newTabSpec("Έ½½ό");
		 View  view2=getLayoutInflater().inflate(R.layout.item_regiester_tab, null);
		tabSpec2.setIndicator(view2);
		Intent intent2=new Intent(this,RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec2.setContent(intent2);
		mTabHost.addTab(tabSpec2);
	}
}
