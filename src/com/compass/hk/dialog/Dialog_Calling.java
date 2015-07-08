package com.compass.hk.dialog;


import com.webdesign688.compass.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dialog_Calling extends Dialog implements android.view.View.OnClickListener{



	private Context context;
	private String text;
	private TextView mTextView_Text;
	private TextView mTextView_Title;
	private Button mButton_Call1;
	private Button mButton_Positive;
	private mOnClickListener mClickListener;
	private Button mButton_Call2;

	public Dialog_Calling(Context context,String  content) {
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
		
		setContentView(R.layout.dialog_calling);
		mTextView_Text = (TextView) findViewById(R.id.tv_contents);
		mTextView_Text.setText(text);
	   mButton_Call1 = (Button) findViewById(R.id.btn_call1);
	   mButton_Call1.setOnClickListener(this);
	   
	   mButton_Call2 = (Button) findViewById(R.id.btn_call2);
	   mButton_Call2.setOnClickListener(this);
		
}
	public void setOnclickListener_on(mOnClickListener  clicklistener){
		  mClickListener=clicklistener;
	}
	public interface  mOnClickListener{
		          void onCall1_Clicklistener();
		          void onCall2_Clicklistener();
	} 
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_call1:
			this.dismiss();
			if (mClickListener != null) {
				mClickListener.onCall1_Clicklistener();
			}
			break;
			
		case R.id.btn_call2:
			this.dismiss();
			if (mClickListener != null) {
				mClickListener.onCall2_Clicklistener();
			}
			break;

		default:
			break;
		}
	}

}
