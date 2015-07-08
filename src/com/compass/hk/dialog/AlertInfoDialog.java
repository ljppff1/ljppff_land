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

public class AlertInfoDialog extends Dialog implements android.view.View.OnClickListener{



	private Context context;
	private CharSequence text;
	private TextView mTextView_Text;
	private TextView mTextView_Title;
	private Button mButton_Negetive;
	private Button mButton_Positive;
	private android.view.View.OnClickListener mClickListener;

	public AlertInfoDialog(Context context) {
		super(context);
		
		this.context=context;
		
	}
@Override
protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setTitle("��ʾ");
	init();
}
private void init() {
		
		setContentView(R.layout.dialog);
		mTextView_Title = (TextView) findViewById(R.id.tv_title);
	   mButton_Negetive = (Button) findViewById(R.id.btn_negetive);
	   mButton_Negetive.setOnClickListener(this);
		
}
	public void setOnclickListener(android.view.View.OnClickListener  clicklistener){
		  this.mClickListener=clicklistener;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_negetive:
			this.dismiss();
			break;

		default:
			break;
		}
	}

}
