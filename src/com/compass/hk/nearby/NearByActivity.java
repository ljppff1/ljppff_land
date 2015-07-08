package com.compass.hk.nearby;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.SaleActivity;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.hot.HotDetailActivity;
import com.compass.hk.login_register.LoginActivity;
import com.compass.hk.login_register.RegisterActivity;
import com.compass.hk.rent.RentActivity;
import com.compass.hk.yishou.Activity_YiShou1;
import com.compass.hk.yishou.YiShouActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
/**
 * 附近屋苑
 * @author liujun
 *
 */
public class NearByActivity extends TabActivity {

	private TabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_near_by);
		initTabHost();
	}
	private void initTabHost() {

		mTabHost = getTabHost();
		TabSpec tabSpec1=mTabHost.newTabSpec("一手");
		View  view1=getLayoutInflater().inflate(R.layout.item_nearby_tab1, null);
		tabSpec1.setIndicator(view1);
		Intent intent1 = new Intent(this,Nearby1Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec1.setContent(intent1);
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2=mTabHost.newTabSpec("出租");
		 View  view2=getLayoutInflater().inflate(R.layout.item_nearby_tab2, null);
		tabSpec2.setIndicator(view2);
		Intent intent2=new Intent(this,Nearby2Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec2.setContent(intent2);
		mTabHost.addTab(tabSpec2);
		
		TabSpec tabSpec3=mTabHost.newTabSpec("出售");
		 View  view3=getLayoutInflater().inflate(R.layout.item_nearby_tab3, null);
		tabSpec3.setIndicator(view3);
		Intent intent3=new Intent(this,Nearby3Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec3.setContent(intent3);
		mTabHost.addTab(tabSpec3);
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
	

}
