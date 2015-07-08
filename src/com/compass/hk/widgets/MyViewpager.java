package com.compass.hk.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewpager  extends  ViewPager {

	private boolean isCanScroll=false;

	public MyViewpager(Context context) {
		super(context);
	}
  
    public MyViewpager(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public void setScanScroll(boolean isCanScroll) {  
        this.isCanScroll = isCanScroll;  
    }  
       @Override
    public void setClickable(boolean clickable) {
    	   this.isCanScroll=clickable;
    	// TODO Auto-generated method stub
    }
       public boolean isCanScroll() {
		return isCanScroll;
	}
    @Override  
    public boolean onTouchEvent(MotionEvent arg0) {  
        // TODO Auto-generated method stub  
        return false;//super.onTouchEvent(arg0);  
    }  
    

}
