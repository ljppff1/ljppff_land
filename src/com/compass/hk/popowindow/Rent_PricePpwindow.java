package com.compass.hk.popowindow;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.webdesign688.compass.R;
import com.compass.hk.popowindow.Rent_ZonePPWindow.Data;
import com.compass.hk.popowindow.Rent_ZonePPWindow.ListAdapter;
import com.compass.hk.popowindow.Rent_ZonePPWindow.onSearchBarItemClickListener;
import com.compass.hk.util.getJson;
public class Rent_PricePpwindow extends BasePopupWindow implements
		OnClickListener,OnCheckedChangeListener {

	private onPriceClickListener mOnPriceClickListener;
	private Context mContext;

	private RadioButton button_price1;

	private RadioButton button_price2;

	private RadioButton button_price3;

	private RadioButton button_price4;

	private int style;

	private RadioButton button_price5;
	private RadioButton button_nolimit;

	public  Rent_PricePpwindow(Context context, int width, int height) {
		super(LayoutInflater.from(context).inflate(
				R.layout.ppwindow_price, null), width, height);
		this.mContext=context;
	}

	@Override
	public void initViews() {
		button_nolimit = (RadioButton) findViewById(R.id.radio_nolimit_price);
		button_price1 = (RadioButton) findViewById(R.id.radio_price1);
		button_price2 = (RadioButton) findViewById(R.id.radio_price2);
		button_price3 = (RadioButton) findViewById(R.id.radio_price3);
		button_price4 = (RadioButton) findViewById(R.id.radio_price4);
		button_price5 = (RadioButton) findViewById(R.id.radio_price5);
		button_nolimit.setOnClickListener(listener);
		button_nolimit.setOnCheckedChangeListener(this); 
		button_price1.setOnCheckedChangeListener(this);
		button_price2.setOnCheckedChangeListener(this);
		button_price3.setOnCheckedChangeListener(this);
		button_price4.setOnCheckedChangeListener(this);
		button_price5.setOnCheckedChangeListener(this);
		findViewById(R.id.view_blank).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		View v_aa = findViewById(R.id.view_aa);
		v_aa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
	}

	@Override
	public void initEvents() {
		//mSearch_Button.setOnClickListener(this);
	} 

	@Override
	public void init() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	
	  default  :
				break;
		}
		//dismiss();
	}

	public void initListView() {
	}

	public void setOnPriceClickListener(
			onPriceClickListener listener) {
		mOnPriceClickListener = listener;
	}

	public interface onPriceClickListener {
		
		void onpricelistener(int style);

	}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.radio_nolimit_price:
				String a ="";
				break;

			default:
				break;
			}
			
		}
	};
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			switch (arg0.getId()) {
			case R.id.radio_price1:
				mOnPriceClickListener.onpricelistener(1);
				dismiss();
				break;
			case R.id.radio_price2:
				mOnPriceClickListener.onpricelistener(2);
				dismiss();
				break;
			case R.id.radio_price3:
				mOnPriceClickListener.onpricelistener(3);
				dismiss();
				break;
			case R.id.radio_price4:
				mOnPriceClickListener.onpricelistener(4);
				dismiss();
				break;
			case R.id.radio_price5:
				mOnPriceClickListener.onpricelistener(5);
				dismiss();
				break;
			case R.id.radio_nolimit_price:
				mOnPriceClickListener.onpricelistener(0);
				dismiss();
				break;
			default:
				break;
			}
		}
	}
}
