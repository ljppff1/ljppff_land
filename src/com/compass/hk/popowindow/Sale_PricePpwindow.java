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
public class Sale_PricePpwindow extends BasePopupWindow implements
		OnClickListener,OnCheckedChangeListener {

	private onPriceClickListener_sale mOnPriceClickListener_sale;
	private Context mContext;

	private RadioButton button_price1;

	private RadioButton button_price2;

	private RadioButton button_price3;

	private RadioButton button_price4;

	private int style;

	private RadioButton button_price5;
	private RadioButton button_nolimit;
	private RadioButton button_price6;

	public  Sale_PricePpwindow(Context context, int width, int height) {
		super(LayoutInflater.from(context).inflate(
				R.layout.ppwindow_saleprice, null), width, height);
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
		button_price6 = (RadioButton) findViewById(R.id.radio_price6);
		button_nolimit.setOnCheckedChangeListener(this); 
		button_price1.setOnCheckedChangeListener(this);
		button_price2.setOnCheckedChangeListener(this);
		button_price3.setOnCheckedChangeListener(this);
		button_price4.setOnCheckedChangeListener(this);
		button_price5.setOnCheckedChangeListener(this);
		button_price6.setOnCheckedChangeListener(this);
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

	public void setOnPriceClickListener_sale(
			onPriceClickListener_sale listener) {
		mOnPriceClickListener_sale = listener;
	}

	public interface onPriceClickListener_sale {
		
		void onpricelistener_sale(int style);

	}
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			switch (arg0.getId()) {
			case R.id.radio_price1:
				mOnPriceClickListener_sale.onpricelistener_sale(1);
				dismiss();
				break;
			case R.id.radio_price2:
				mOnPriceClickListener_sale.onpricelistener_sale(2);
				dismiss();
				break;
			case R.id.radio_price3:
				mOnPriceClickListener_sale.onpricelistener_sale(3);
				dismiss();
				break;
			case R.id.radio_price4:
				mOnPriceClickListener_sale.onpricelistener_sale(4);
				dismiss();
				break;
			case R.id.radio_price5:
				mOnPriceClickListener_sale.onpricelistener_sale(5);
				dismiss();
			case R.id.radio_price6:
				mOnPriceClickListener_sale.onpricelistener_sale(6);
				dismiss();
				break;
			case R.id.radio_nolimit_price:
				mOnPriceClickListener_sale.onpricelistener_sale(0);
				dismiss();
				break;
			default:
				break;
			}
		}
	}
}
