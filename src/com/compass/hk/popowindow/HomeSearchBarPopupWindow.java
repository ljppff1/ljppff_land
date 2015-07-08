package com.compass.hk.popowindow;


import org.apache.http.entity.ByteArrayEntity;

import com.webdesign688.compass.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * @author Tau.Chen 闄堟稕
 * 
 * @email tauchen1990@gmail.com,1076559197@qq.com
 * 
 * @date 2013骞?鏈?1鏃?
 * 
 * @version V_1.0.0
 * 
 * @description
 * 
 */
public class HomeSearchBarPopupWindow extends BasePopupWindow implements
		OnClickListener {

	private ImageButton mSearch_Button;

	private onSearchBarItemClickListener mOnSearchBarItemClickListener;

	private SpinnerAdapter adapter;

	private String[] mStringDayLists;

	private Context mContext;

	private Button button;

	private Button button1;

	private Button button2;

	private Button button3;

	private View linea_select;
	
	private String searchType="0";
	
	private String mString_Keyword;

	private EditText edittext;

	public HomeSearchBarPopupWindow(Context context, int width, int height) {
		super(LayoutInflater.from(context).inflate(
				R.layout.app_search_toolbar, null), width, height);
		this.mContext=context;
	}

	@Override
	public void initViews() {
		 /* mStringDayLists = new String[]{"業主盤","代理盤","全部樓盤"};
		mSearch_Button = (ImageButton) findViewById(R.id.imageButton_popuwindow);
		Spinner mSpinner= (Spinner) findViewById(R.id.spinner1);
		ListAdapter adapter=new ListAdapter();
		mSpinner.setAdapter(adapter);*/
		mSearch_Button = (ImageButton) findViewById(R.id.imageButton_popuwindow);
		button = (Button) findViewById(R.id.button_main);
		button1 = (Button) findViewById(R.id.button_main1);
		button2 = (Button) findViewById(R.id.button_main2);
		button3 = (Button) findViewById(R.id.button_main3);
		linea_select = findViewById(R.id.linea_select);
		edittext = (EditText) findViewById(R.id.edittext_searchbar);
		button.setOnClickListener(this);
		mSearch_Button.setOnClickListener(this);
	}
	@Override
	public void initEvents() {
		//mSearch_Button.setOnClickListener(this);
	} 

	@Override
	public void init() {

	}
public class ListAdapter extends BaseAdapter implements SpinnerAdapter{

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mStringDayLists.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	                          
		return null;
	}
	
}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButton_popuwindow:
			if (mOnSearchBarItemClickListener != null) {
				String string = edittext.getText().toString();
				if (string==null||"".equals(string)) {
					  Toast.makeText(mContext, "請輸入關鍵字", Toast.LENGTH_SHORT).show();
				}
				else {
					
					mOnSearchBarItemClickListener.onSearchButtonClick(string,searchType);
					dismiss();
				}
			}
			break;
		case R.id.button_main:
			button.setVisibility(View.GONE);
			linea_select.setVisibility(View.VISIBLE);
			button1.setOnClickListener(this);
			button2.setOnClickListener(this);
			button3.setOnClickListener(this);
			break;
		case R.id.button_main1:
		      linea_select.setVisibility(View.GONE);
		      button.setVisibility(View.VISIBLE);
		      searchType="1";
		      button.setText("業主盤");
		          break;
		case R.id.button_main2:
	          linea_select.setVisibility(View.GONE);
	          button.setVisibility(View.VISIBLE);
	          button.setText("代理盤");
	          searchType="2";
	          break;
		case R.id.button_main3:
	        
	          linea_select.setVisibility(View.GONE);
	          button.setVisibility(View.VISIBLE);
	          button.setText("全部樓盤");
	          searchType="0";
	          break;
	  default  :
				break;
		}
		//dismiss();
	}

	public void setOnSearchBarItemClickListener(
			onSearchBarItemClickListener listener) {
		mOnSearchBarItemClickListener = listener;
	}

	public interface onSearchBarItemClickListener {
		
		void onSearchButtonClick(String string, String searchType);

	}
}
