package com.compass.hk;
import com.compass.hk.compass.OwnerActivity;
import com.compass.hk.more.MoreActivity;
import com.compass.hk.nearby.NearByActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import com.webdesign688.compass.R;

public class Tab1Activity extends TabActivity{
	private TabHost mTabHost;
	private boolean hasPressedBack;
	private Handler mHandler=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		//getID
		Intent intent = getIntent();
		int m_Id = intent.getIntExtra("tab_id", 0);
		initTabHost();
		mTabHost.setCurrentTab(m_Id);
	}
	private void initTabHost() {
		mTabHost = getTabHost();
		TabSpec tabSpec1=mTabHost.newTabSpec("熱門");
		View  view1=getLayoutInflater().inflate(R.layout.item_tab1, null);
		tabSpec1.setIndicator(view1);
		Intent intent1 = new Intent(this,HotActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec1.setContent(intent1);
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2=mTabHost.newTabSpec("附近");
		 View  view2=getLayoutInflater().inflate(R.layout.item_tab2, null);
		tabSpec2.setIndicator(view2);
		Intent intent2=new Intent(this,NearByActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec2.setContent(intent2);
		mTabHost.addTab(tabSpec2);
		
		TabSpec tabSpec3=mTabHost.newTabSpec("我的指南針");
		 View  view3=getLayoutInflater().inflate(R.layout.item_tab3, null);
		tabSpec3.setIndicator(view3);
		Intent intent3 = new Intent(this,OwnerActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec3.setContent(intent3);
		mTabHost.addTab(tabSpec3);
		
		TabSpec tabSpec4=mTabHost.newTabSpec("更多");
		 View  view4=getLayoutInflater().inflate(R.layout.item_tab4, null);
		tabSpec4.setIndicator(view4);
		Intent intent4 = new Intent(this,MoreActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec4.setContent(intent4);
		mTabHost.addTab(tabSpec4);
		
	}
}
