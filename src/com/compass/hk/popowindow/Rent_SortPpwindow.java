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
public class Rent_SortPpwindow extends BasePopupWindow implements
		OnClickListener,OnCheckedChangeListener {

	private onSortlistener monSortlistener;
	private Context mContext;

	private RadioButton button_sort1;

	private RadioButton button_sort2;


	private int sorttyle;//0為默認，1為高到低，2為低到高

	private RadioButton button_price5;
	private RadioButton button_nolimit;

	public  Rent_SortPpwindow(Context context, int width, int height) {
		super(LayoutInflater.from(context).inflate(
				R.layout.ppwindow_sort, null), width, height);
		this.mContext=context;
	}

	@Override
	public void initViews() {
		button_nolimit = (RadioButton) findViewById(R.id.radio_nolimit_sort);
		button_sort1 = (RadioButton) findViewById(R.id.radio_sort1);
		button_sort2 = (RadioButton) findViewById(R.id.radio_sort2);
		button_nolimit.setOnCheckedChangeListener(this); 
		button_sort1.setOnCheckedChangeListener(this);
		button_sort2.setOnCheckedChangeListener(this);
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

	public void setOnsortListener(
			onSortlistener listener) {
		monSortlistener = listener;
	}
	

	public interface onSortlistener {
		
		void setOnsortListener(int sorttyle);

	}
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			switch (arg0.getId()) {
			case R.id.radio_sort1:
				monSortlistener.setOnsortListener(1);
				
				dismiss();
				break;
			case R.id.radio_sort2:
				if (monSortlistener!=null) {
					monSortlistener.setOnsortListener(2);
				}
				dismiss();
				break;
			case R.id.radio_nolimit_sort:
				if (monSortlistener!=null) {
					sorttyle=0;
					monSortlistener.setOnsortListener(0);
				}
				dismiss();
				break;
				
			default:
				break;
			}
		}
	}
}
