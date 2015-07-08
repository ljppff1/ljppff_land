package com.compass.hk.xinwen;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.yishou.Activity_YiShou1;
import com.compass.hk.yishou.Activity_YiShou2;
import com.compass.hk.yishou.Activity_YiShou3;
import com.compass.hk.yishou.Activity_YiShou4;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
/**
 * 新闻中心
 * @author liujun
 *
 */
public class XinWenActivity extends TabActivity {
	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xin_wen);
		
		initTabHost();

	}
	public  void btn_back(View v) {
		// TODO Auto-generated method stub
       finish();
	}
	public  void btn_home(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,MainActivity.class));
	}

	private void initTabHost() {

		mTabHost = getTabHost();
		TabSpec tabSpec1=mTabHost.newTabSpec("熱門");
		View  view1=getLayoutInflater().inflate(R.layout.item__new_tab1, null);
		tabSpec1.setIndicator(view1);
		Intent intent1 = new Intent(this,News1Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec1.setContent(intent1);
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2=mTabHost.newTabSpec("附近");
		 View  view2=getLayoutInflater().inflate(R.layout.item__new_tab2, null);
		tabSpec2.setIndicator(view2);
		Intent intent2=new Intent(this,News2Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec2.setContent(intent2);
		mTabHost.addTab(tabSpec2);
		
		TabSpec tabSpec3=mTabHost.newTabSpec("我的指南針");
		 View  view3=getLayoutInflater().inflate(R.layout.item__new_tab3, null);
		tabSpec3.setIndicator(view3);
		Intent intent3 = new Intent(this,News3Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec3.setContent(intent3);
		mTabHost.addTab(tabSpec3);
		
		TabSpec tabSpec4=mTabHost.newTabSpec("更多");
		 View  view4=getLayoutInflater().inflate(R.layout.item__new_tab4, null);
		tabSpec4.setIndicator(view4);
		Intent intent4 = new Intent(this,News4Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec4.setContent(intent4);
		mTabHost.addTab(tabSpec4);
		
		TabSpec tabSpec5=mTabHost.newTabSpec("更多");
		 View  view5=getLayoutInflater().inflate(R.layout.item__new_tab5, null);
		tabSpec5.setIndicator(view5);
		Intent intent5 = new Intent(this,News5Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec5.setContent(intent5);
		mTabHost.addTab(tabSpec5);
		
		
	
		// TODO Auto-generated method stub
		
	}


}
