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
public class Rent_MorePpwindow extends BasePopupWindow implements
		OnClickListener,OnCheckedChangeListener {

	private onAreaClickListener mOnAreaClickListener;
	private Context mContext;

	private RadioButton button_area1;

	private RadioButton button_area2;

	private RadioButton button_area3;

	private RadioButton button_area4;

	private int style;

	private RadioButton button_area5;
	private RadioButton button_nolimit;
	private RadioButton button_area6;
	private RadioButton button_area7;

	public  Rent_MorePpwindow(Context context, int width, int height) {
		super(LayoutInflater.from(context).inflate(
				R.layout.ppwindow_more1, null), width, height);
		this.mContext=context;
	}

	@Override
	public void initViews() {
		button_nolimit = (RadioButton) findViewById(R.id.radio_nolimit_area);
		button_area1 = (RadioButton) findViewById(R.id.radio_area1);
		button_area2 = (RadioButton) findViewById(R.id.radio_area2);
		button_area3 = (RadioButton) findViewById(R.id.radio_area3);
		button_area4 = (RadioButton) findViewById(R.id.radio_area4);
		button_area5 = (RadioButton) findViewById(R.id.radio_area5);
		button_area6 = (RadioButton) findViewById(R.id.radio_area6);
		button_area7 = (RadioButton) findViewById(R.id.radio_area7);
		button_nolimit.setOnCheckedChangeListener(this); 
		button_area1.setOnCheckedChangeListener(this);
		button_area2.setOnCheckedChangeListener(this);
		button_area3.setOnCheckedChangeListener(this);
		button_area4.setOnCheckedChangeListener(this);
		button_area5.setOnCheckedChangeListener(this);
		button_area6.setOnCheckedChangeListener(this);
		button_area7.setOnCheckedChangeListener(this);
		findViewById(R.id.view_blank).setOnClickListener(new OnClickListener() {
			
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

	public void setAreaClickListener(
			onAreaClickListener listener) {
		mOnAreaClickListener = listener;
	}

	public interface onAreaClickListener {
		
		void onarealistener(int style);

	}
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			switch (arg0.getId()) {
			case R.id.radio_area1:
				mOnAreaClickListener.onarealistener(1);
				dismiss();
				break;
			case R.id.radio_area2:
				mOnAreaClickListener.onarealistener(2);
				dismiss();
				break;
			case R.id.radio_area3:
				mOnAreaClickListener.onarealistener(3);
				dismiss();
				break;
			case R.id.radio_area4:
				mOnAreaClickListener.onarealistener(4);
				dismiss();
				break;
			case R.id.radio_area5:
				mOnAreaClickListener.onarealistener(5);
				dismiss();
				break;
			case R.id.radio_area6:
				mOnAreaClickListener.onarealistener(6);
				dismiss();
				break;
			case R.id.radio_area7:
				mOnAreaClickListener.onarealistener(7);
				dismiss();
				break;
			case R.id.radio_nolimit_area:
				mOnAreaClickListener.onarealistener(0);
				dismiss();
				break;
			default:
				break;
			}
		}
	}
}
