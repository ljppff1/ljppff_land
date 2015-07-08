package com.compass.hk;


import com.webdesign688.compass.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class SplashActivity extends Activity {
 private Handler handle=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		handle.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(new Intent(SplashActivity.this,MainActivity.class));
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				finish();
			}
		}, 3000);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_DOWN) {
			startActivity(new Intent(SplashActivity.this,MainActivity.class));
			overridePendingTransition(R.anim.push_left_in,
					R.anim.push_left_out);
			finish();
		}
		return super.onTouchEvent(event);
	}
}
