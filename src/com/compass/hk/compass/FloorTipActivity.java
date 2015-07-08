package com.compass.hk.compass;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.login_register.LoginActivity;
import com.compass.hk.login_register.RegisterActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class FloorTipActivity extends TabActivity {

	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_floor_tip);
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
		TabSpec tabSpec1=mTabHost.newTabSpec("我的樓盤提示");
		View  view1=getLayoutInflater().inflate(R.layout.item_floortip_tab1, null);
		tabSpec1.setIndicator(view1);
		Intent intent1 = new Intent(this,MyTipActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec1.setContent(intent1);
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2=mTabHost.newTabSpec("新增樓盤");
		 View  view2=getLayoutInflater().inflate(R.layout.item_floortip_tab2, null);
		tabSpec2.setIndicator(view2);
		Intent intent2=new Intent(this,AddTipActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec2.setContent(intent2);
		mTabHost.addTab(tabSpec2);
		
		
	
		// TODO Auto-generated method stub
		
	}
}
