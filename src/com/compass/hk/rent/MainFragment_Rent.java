package com.compass.hk.rent;

import java.util.ArrayList;

import com.webdesign688.compass.R;
import com.compass.hk.yishou.Frame_Yishou1;
import com.compass.hk.yishou.Frame_Yishou2;
import com.compass.hk.yishou.Frame_Yishou3;
import com.compass.hk.yishou.Frame_Yishou4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;


public class MainFragment_Rent extends Fragment
{

	private int mColorRes = -1;
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	private int m_Id;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		
		return initTabHost(inflater, savedInstanceState);
	}

	private View initTabHost(LayoutInflater inflater, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_tabs_pager, null);
		mTabHost = (TabHost) layout.findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) layout.findViewById(R.id.pager_tabs);

		mTabsAdapter = new TabsAdapter(getActivity(), mTabHost, mViewPager);
		View view1=getActivity().getLayoutInflater().inflate(R.layout.item_rent_tab1, null);
		TabSpec setIndicator1 = mTabHost.newTabSpec("jingxuan").setIndicator(view1);
		mTabsAdapter.addTab(
				setIndicator1,
				Frame_Rent1.class, null);
		
		View view2=getActivity().getLayoutInflater().inflate(R.layout.item_rent_tab2, null);
		
		TabSpec setIndicator2 = mTabHost.newTabSpec("allfourm")
				.setIndicator(view2);
		mTabsAdapter.addTab(setIndicator2, Frame_Rent1.class, null);
		
		View view3=getActivity().getLayoutInflater().inflate(R.layout.item_rent_tab3, null);
		TabSpec setIndicator3 = mTabHost.newTabSpec("allfourm")
				.setIndicator(view3);
		mTabsAdapter.addTab(setIndicator3, Frame_Rent1.class, null);
		
		View view4=getActivity().getLayoutInflater().inflate(R.layout.item_rent_tab4, null);
		TabSpec setIndicator4 = mTabHost.newTabSpec("allfourm")
				.setIndicator(view4);
		mTabsAdapter.addTab(setIndicator4, Frame_Rent1.class, null);
		
		View view5=getActivity().getLayoutInflater().inflate(R.layout.item_rent_tab5, null);
		TabSpec setIndicator5 = mTabHost.newTabSpec("allfourm")
				.setIndicator(view5);
		mTabsAdapter.addTab(setIndicator5, Frame_Rent1.class, null);

		if (savedInstanceState != null)
		{
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
		mViewPager.setCurrentItem(m_Id);
		return layout;
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt("mColorRes", mColorRes);
	}

	public class TabsAdapter extends FragmentStatePagerAdapter implements
			TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener
	{
		private final Context mContext;
		private final TabHost mTabHost;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		final class TabInfo
		{
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(String _tag, Class<?> _class, Bundle _args)
			{
				tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		class DummyTabFactory implements TabHost.TabContentFactory
		{
			private final Context mContext;

			public DummyTabFactory(Context context)
			{
				mContext = context;
			}

			@Override
			public View createTabContent(String tag)
			{
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
		}

		public TabsAdapter(FragmentActivity activity, TabHost tabHost,
				ViewPager pager)
		{
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mTabHost = tabHost;
			mViewPager = pager;
			mTabHost.setOnTabChangedListener(this);
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args)
		{
			tabSpec.setContent(new DummyTabFactory(mContext));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);
			mTabs.add(info);
			mTabHost.addTab(tabSpec);
			notifyDataSetChanged();
		}

		@Override
		public int getCount()
		{
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position)
		{
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		@Override
		public void onTabChanged(String tabId)
		{
			int position = mTabHost.getCurrentTab();
			mViewPager.setCurrentItem(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels)
		{
		}

		@Override
		public void onPageSelected(int position)
		{
			TabWidget widget = mTabHost.getTabWidget();
			int oldFocusability = widget.getDescendantFocusability();
			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			mTabHost.setCurrentTab(position);
			widget.setDescendantFocusability(oldFocusability);

		}

		@Override
		public void onPageScrollStateChanged(int state)
		{
		}
	}

	public void setId(int m_Id) {
		this.m_Id=m_Id;
	}

}
