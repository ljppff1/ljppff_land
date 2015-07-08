package com.compass.hk.badfile;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.webdesign688.compass.R;
import com.compass.hk.badfile.Frame_danger1.OnFragmentListener;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.CustomerAlertDialog.mOnClickListener;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.login_register.Login_Regiester;
import com.compass.hk.util.Bean;
/**
 * Ð×Õ¬µµ°¸
 * @author liujun
 *
 */
@SuppressLint("NewApi")
public class XiongZhaiActivity extends FragmentActivity implements OnClickListener,mOnClickListener,OnFragmentListener{

	private FragmentTransaction ft;
	private ViewPager mViewPager;
	private TextView mTv_tab1;
	private TextView mTv_tab2;
	private TextView mTv_tab3;
	private TextView mTv_tab4;
	private TextView mTv_tab5;
	private com.compass.hk.badfile.PagerSlidingTabStrip tabs;
	private com.compass.hk.badfile.NoSrollViewPager pager;
	private ArrayList<Fragment> fragments;
	private Frame_danger1 menuFrame1;
	private Frame_danger2 menuFrame2;
	private Frame_danger3 menuFrame3;
	private Frame_danger4 menuFrame4;
	private Frame_danger5 menuFrame5;
	private MyPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiong_zhai);
		FragmentManager sfm = getSupportFragmentManager();
		ft = sfm.beginTransaction();
		
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("Ð×Õ¬™n°¸");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		initUi();
		
		Intent intent = getIntent();
		boolean isBooked = intent.getBooleanExtra("isBooked", false);
		if (isBooked) {
			pager.setCurrentItem(4);
		}
	}

	private void initUi() {
		
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (NoSrollViewPager) findViewById(R.id.pager);
		pager.setScanScroll(true);
		pager.setOffscreenPageLimit(5);
		fragments = new ArrayList<Fragment>();
		menuFrame1 = new Frame_danger1();
		menuFrame2 = new Frame_danger2();
		menuFrame3 = new Frame_danger3();
		menuFrame4 = new Frame_danger4();
		menuFrame5 = new Frame_danger5();
		fragments.add(menuFrame1);
		fragments.add(menuFrame2);
		fragments.add(menuFrame3);
		fragments.add(menuFrame5);
		fragments.add(menuFrame4);
		adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 5, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		pager.setAdapter(adapter);
		tabs.setViewPager(pager);
		tabs.setUnderlineColorResource(R.color.main_tab_underline);
		tabs.setIndicatorColorResource(R.color.main_tab_indicator);

		tabs.setOnPageChangeListener(onPageChangeListener);
                
	}
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final int[] TITLES = { R.string.badfile_sort,
				R.string.badfile_file, R.string.badfile_new,
				R.string.badfile_oder,R.string.badfile_void};
		String[] mStrings=new String[]{"  Ð×Õ¬ÅÅÐÐ°ñ  ","  ºÎÖ^Ð×Õ¬  ","  Ð×Õ¬ÐÂÂ„  ","  Ôõ˜Ó¿ÉÃâÙIÈëÐ×Õ¬  ","  Ð×Õ¬Ó†é†  "};
		private List<Fragment> fragments;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mStrings[position];
		}

		@Override
		public int getCount() {
			return fragments.size();
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

	}
OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(final int position) {
			if (position==4) {
				 //ÅÐ”à•þ†TÊÇ·ñµÇä›
		        boolean logined = Bean.isLogined();
				 if (!logined) {
						
							startActivity(new Intent(XiongZhaiActivity.this,Login_Regiester.class));
							finish();
							
					}
			}
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub

		}
	};

	private void initViewPage() {}
	@Override
	public void onClick(View v) {
switch (v.getId()) {}		
	}

	@Override
	public void onBtnClicklistener() {
		startActivity(new Intent(this,Login_Regiester.class));
		
	}

	@Override
	public void onFragmentAction(boolean intflag) {
		// TODO Auto-generated method stub
		pager.setCurrentItem(4);
	}

}