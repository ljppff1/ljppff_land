package com.compass.hk.yishou;

import com.webdesign688.compass.R;
import com.compass.hk.frame.Frame_Title;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class Activity_YiShou4 extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity__yi_shou1);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Yishou4 mainFragment = new Frame_Yishou4();
		ft.add(R.id.frame_yishou, mainFragment);
		ft.commit();
	}

}
