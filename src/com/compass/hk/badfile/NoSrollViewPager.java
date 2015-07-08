package com.compass.hk.badfile;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class NoSrollViewPager extends ViewPager {
	private boolean isCanScroll = true;

	public NoSrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NoSrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScanScroll(boolean isCanScroll) {
		this.isCanScroll = isCanScroll;
	}

	@Override
	public void scrollTo(int x, int y) {
		if (isCanScroll) {
			super.scrollTo(x, y);
		}
	}
}
