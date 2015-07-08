package com.compass.hk.widgets;

import com.webdesign688.compass.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;


	/**
	* 虚拟listview
	*
	* @author JustMe
	*
	*/
	public class MyListView extends LinearLayout {
	private BaseAdapter adapter;
	private MyOnItemClickListener onItemClickListener;
	/**
	* 通知更新listview
	*/
	public void notifyChange() {
	int count = getChildCount();
	LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,
	LayoutParams.WRAP_CONTENT);
	for (int i = count; i < adapter.getCount(); i++) {
	final int index = i;
	final LinearLayout layout = new LinearLayout(getContext());
	layout.setLayoutParams(params);
	layout.setOrientation(VERTICAL);
	View v = adapter.getView(i, null, null);
	v.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
	if (onItemClickListener != null) {
	onItemClickListener.onItemClick(MyListView.this,
	layout, index, adapter.getItem(index));
	}
	}
	});
	// 每个条目下面的线
	ImageView imageView = new ImageView(getContext());
	imageView.setBackgroundResource(R.drawable.icon_greyline);
	imageView.setLayoutParams(params);
	layout.addView(v);
	layout.addView(imageView);
	addView(layout, index);
	}
	}
	public MyListView(Context context) {
	super(context);
	initAttr(null);
	}
	public MyListView(Context context, AttributeSet attrs) {
	super(context, attrs);
	initAttr(attrs);
	}
	/**
	* 设置方向
	*
	* @param attrs
	*/
	public void initAttr(AttributeSet attrs) {
	setOrientation(VERTICAL);
	}
	public BaseAdapter getAdapter() {
	return adapter;
	}
	/**
	* 设置adapter并模拟listview添加数据
	*
	* @param adpater
	*/
	public void setAdapter(BaseAdapter adpater) {
	this.adapter = adpater;
	notifyChange();
	}
	/**
	* 设置条目监听事件
	*
	* @param onClickListener
	*/
	public void setOnItemClickListener(MyOnItemClickListener onClickListener) {
	this.onItemClickListener = onClickListener;
	}
	/**
	* 点击事件监听
	*
	* @author JustMe
	*
	*/
	public static interface MyOnItemClickListener {
	public void onItemClick(ViewGroup parent, View view, int position,
	Object o);
	}
}
