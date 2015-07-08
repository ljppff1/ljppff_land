package com.compass.hk.more;

import com.webdesign688.compass.R;
import com.compass.hk.frame.Frame_Title;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class CompassNetActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compass_net);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("ÖÃ˜IÖ¸ÄÏá˜¾W");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
	}


}
