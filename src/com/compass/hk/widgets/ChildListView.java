package com.compass.hk.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * listView嵌套listView的时�?..
 * @author qq576507648
 */
public class ChildListView extends ListView{
	public ChildListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public ChildListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public ChildListView(Context context) {
		super(context);
	}
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                MeasureSpec.AT_MOST);  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }
//	@Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//           return true;
//    }
}
