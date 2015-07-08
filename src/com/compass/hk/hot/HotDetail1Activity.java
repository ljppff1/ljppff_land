package com.compass.hk.hot;

import com.webdesign688.compass.R;
import com.compass.hk.yishou.Frame_Yishou1;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class HotDetail1Activity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hot_detail1);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_hot1 mainFragment = new Frame_hot1();
		ft.add(R.id.frame_hotdetail, mainFragment);
		ft.commit();
	}
}
