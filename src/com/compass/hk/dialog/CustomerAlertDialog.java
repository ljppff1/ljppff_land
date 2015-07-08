package com.compass.hk.dialog;


import com.webdesign688.compass.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerAlertDialog extends Dialog implements android.view.View.OnClickListener{



	private Context context;
	private String text;
	private TextView mTextView_Text;
	private TextView mTextView_Title;
	private Button mButton_Negetive;
	private Button mButton_Positive;
	private mOnClickListener mClickListener;

	public CustomerAlertDialog(Context context,String  content) {
		super(context);
		this.text=content;
		this.context=context;
		
	}
@Override
protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setTitle("ÌבÊ¾");
	init();
}
private void init() {
		
		setContentView(R.layout.alertdialog);
		mTextView_Text = (TextView) findViewById(R.id.tv_contents);
		mTextView_Text.setText(text);
	   mButton_Negetive = (Button) findViewById(R.id.btn_negetive);
	   mButton_Negetive.setOnClickListener(this);
		
}
	public void setOnclickListener_on(mOnClickListener  clicklistener){
		  mClickListener=clicklistener;
	}
	public interface  mOnClickListener{
		          void onBtnClicklistener();
	} 
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_negetive:
			this.dismiss();
			if (mClickListener != null) {
				mClickListener.onBtnClicklistener();
			}
			break;

		default:
			break;
		}
	}
	public void setOnclickListener_on(OnPageChangeListener onPageChangeListener) {
		// TODO Auto-generated method stub
		
	}

}
