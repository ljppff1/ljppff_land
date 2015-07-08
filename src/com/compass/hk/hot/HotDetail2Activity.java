package com.compass.hk.hot;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.senab.photoview.PhotoView;


import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_viewpage_home;
import com.compass.hk.frame.MyFrame;
import com.compass.hk.hot.Frame_hot1.DownLoadAsyTask;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;
import com.compass.hk.util.getJson;
import com.compass.hk.xinwen.NewContentActivity;
import com.compass.hk.xinwen.News1Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class HotDetail2Activity extends FragmentActivity{

	private ViewPager mViewPager;
	private String mString_ID;
	ArrayList<String>  mPicList=new ArrayList<String>();
	ArrayList<String>  mPicNameList=new ArrayList<String>();
	ArrayList<String> mNewList_ID=new ArrayList<String>();
	ArrayList<String> mNewList_Title=new ArrayList<String>();
	private ViewPager mViewPager_Adv;
	private RadioGroup mRadioGroup;
	private RadioButton mRadionBtn_1;
	private RadioButton mRadionBtn_2;
	private RadioButton mRadionBtn_3;
	private RadioButton mRadionBtn_4;
	private RadioButton mRadionBtn_5;
	private RadioButton mRadionBtn_6;
	private RadioButton mRadionBtn_7;
	private RadioButton mRadionBtn_8;
	private RadioButton mRadionBtn_9;
	private RadioButton mRadionBtn_10;
	private RadioButton mRadionBtn_11;
	private RadioButton mRadionBtn_12;
	private RadioButton mRadionBtn_13;
	private RadioButton mRadionBtn_14;
	private RadioButton mRadionBtn_15;
	private RadioButton mRadionBtn_16;
	private RadioButton mRadionBtn_17;
	private RadioButton mRadionBtn_18;
	private RadioButton mRadionBtn_19;
	private RadioButton mRadionBtn_20;
	private TextView mTv_PicName;
	private ArrayList<Integer> mRadioList=new ArrayList<Integer>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_hot2);
	        mViewPager = (ViewPager) findViewById(R.id.pager_hot_pic);  
		getID();
		 initRadio();
		 download_New();
		download();
	}

	private void download_New() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask_New().execute(Content.URL_YISHOU_NEWLISTS_INNER+mString_ID);
	}

	private void initRadio() {
		// TODO Auto-generated method stub
		
		mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_hot2);
		 mRadionBtn_1 = (RadioButton) findViewById(R.id.hot2_radio_1);
		 mRadionBtn_2 = (RadioButton) findViewById(R.id.hot2_radio_2);
		 mRadionBtn_3 = (RadioButton) findViewById(R.id.hot2_radio_3);
		 mRadionBtn_4= (RadioButton) findViewById(R.id.hot2_radio_4);
		 mRadionBtn_5= (RadioButton) findViewById(R.id.hot2_radio_5);
		 mRadionBtn_6= (RadioButton) findViewById(R.id.hot2_radio_6);
		 mRadionBtn_7= (RadioButton) findViewById(R.id.hot2_radio_7);
		 mRadionBtn_8= (RadioButton) findViewById(R.id.hot2_radio_8);
		 mRadionBtn_9= (RadioButton) findViewById(R.id.hot2_radio_9);
		 mRadionBtn_10= (RadioButton) findViewById(R.id.hot2_radio_10);
		 mRadionBtn_11= (RadioButton) findViewById(R.id.hot2_radio_11);
	     mRadionBtn_12= (RadioButton) findViewById(R.id.hot2_radio_12);
		 mRadionBtn_13= (RadioButton) findViewById(R.id.hot2_radio_13);
		 mRadionBtn_14= (RadioButton) findViewById(R.id.hot2_radio_14);
		 mRadionBtn_15= (RadioButton) findViewById(R.id.hot2_radio_15);
		 mRadionBtn_16= (RadioButton) findViewById(R.id.hot2_radio_16);
		 mRadionBtn_17= (RadioButton) findViewById(R.id.hot2_radio_17);
		 mRadionBtn_18= (RadioButton) findViewById(R.id.hot2_radio_18);
		 mRadionBtn_19= (RadioButton) findViewById(R.id.hot2_radio_19);
		 mRadionBtn_20= (RadioButton) findViewById(R.id.hot2_radio_20);
		 for (int i = 0; i <20; i++) {
			mRadioList.add(R.id.hot2_radio_1);
			mRadioList.add(R.id.hot2_radio_2);
			mRadioList.add(R.id.hot2_radio_3);
			mRadioList.add(R.id.hot2_radio_4);
			mRadioList.add(R.id.hot2_radio_5);
			mRadioList.add(R.id.hot2_radio_6);
			mRadioList.add(R.id.hot2_radio_7);
			mRadioList.add(R.id.hot2_radio_8);
			mRadioList.add(R.id.hot2_radio_9);
			mRadioList.add(R.id.hot2_radio_10);
			mRadioList.add(R.id.hot2_radio_11);
			mRadioList.add(R.id.hot2_radio_12);
			mRadioList.add(R.id.hot2_radio_13);
			mRadioList.add(R.id.hot2_radio_14);
			mRadioList.add(R.id.hot2_radio_15);
			mRadioList.add(R.id.hot2_radio_16);
			mRadioList.add(R.id.hot2_radio_17);
			mRadioList.add(R.id.hot2_radio_18);
			mRadioList.add(R.id.hot2_radio_19);
			mRadioList.add(R.id.hot2_radio_20);
			
		}
		 
	}

	private void getID() {
		// TODO Auto-generated method stub
		UILApplication application = (UILApplication)getApplication();
		mString_ID = application.getHotDetail_ID();
		Log.e("getID", mString_ID);
	}
	private void download() {
		new DownLoadAsyTask().execute(Content.URL_FIRST_HAND_DETAIL_WUYETUPIAN+mString_ID);
	}
	
class DownLoadAsyTask_New extends AsyncTask<String, Void, String> implements OnClickListener{  
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					 JSONArray array = jsonObject.getJSONArray("data");
					  for (int i = 0; i < array.length(); i++) {
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 
						String  string_ID= jsonObject2.getString("ID");
						String string_Title= jsonObject2.getString("Title");
						Log.e("DownLoadAsyTask_New", string_ID);
						 Log.e("DownLoadAsyTask_New", string_Title);     
						 mNewList_ID.add(string_ID);
						 mNewList_Title.add(string_Title);
					}
					  
					  if (mNewList_ID.size()>0) {
						View rela_1 = findViewById(R.id.rela_hot2_new1);
						rela_1.setVisibility(View.VISIBLE);
						TextView textView= (TextView) findViewById(R.id.tv_rela_hot2_new1);
						textView.setText(mNewList_Title.get(0));
						rela_1.setOnClickListener(this);
					}
					  if (mNewList_ID.size()>1) {
							View rela_2 = findViewById(R.id.rela_hot2_new2);
							rela_2.setVisibility(View.VISIBLE);
							TextView textView= (TextView) findViewById(R.id.tv_rela_hot2_new2);
							textView.setText(mNewList_Title.get(1));
							rela_2.setOnClickListener(this);
						}
					  if (mNewList_ID.size()>2) {
							View rela_3 = findViewById(R.id.rela_hot2_new3);
							rela_3.setVisibility(View.VISIBLE);
							TextView textView= (TextView) findViewById(R.id.tv_rela_hot2_new3);
							textView.setText(mNewList_Title.get(2));
							rela_3.setOnClickListener(this);
						}
				}
				 else {
				}
			} catch (JSONException e) {
				new Dialog_noInternet(HotDetail2Activity.this).show();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.rela_hot2_new1:
					 Intent intent = new Intent(HotDetail2Activity.this,NewContentActivity.class);
	                 intent.putExtra("ID", mNewList_ID.get(0));
	                 intent.putExtra("url_id", "1");
					startActivity(intent);
					break;
                case R.id.rela_hot2_new2:
                	
                	 Intent intent2 = new Intent(HotDetail2Activity.this,NewContentActivity.class);
	                 intent2.putExtra("ID", mNewList_ID.get(1));
	                 intent2.putExtra("url_id", "1");
	                 startActivity(intent2);
					break;
                case R.id.rela_hot2_new3:
                	 Intent intent3 = new Intent(HotDetail2Activity.this,NewContentActivity.class);
	                 intent3.putExtra("ID", mNewList_ID.get(2));
	                 intent3.putExtra("url_id", "1");
	                 startActivity(intent3);
	break;
					

				default:
					break;
				}
			}
			}
	class DownLoadAsyTask extends AsyncTask<String, Void, String>{  
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					 JSONArray array = jsonObject.getJSONArray("data");
					  for (int i = 0; i < array.length(); i++) {
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 
						String string_PhotoName = jsonObject2.getString("PhotoName");
						String string_PhotoUr = jsonObject2.getString("PhotoUrl");
						mPicList.add(string_PhotoUr);
						mPicNameList.add(string_PhotoName);
						Log.e("string_PhotoUr", string_PhotoUr);
						 Log.e("initViewPage", ""+mPicList.size());     
						 findViewById(R.id.tv_hot2_referbigpic).setVisibility(View.VISIBLE);
						
					}
					  if (mPicNameList.size()>0) {
						  mTv_PicName = (TextView) findViewById(R.id.tv_hot2_picname);
						  mTv_PicName.setText(mPicNameList.get(0));
					}
					  else {
						  mTv_PicName.setText(" ");
					}
					  for (int j = 0; j < mPicNameList.size(); j++) {
							 int   id = mRadioList.get(j);
								RadioButton radioButton = (RadioButton) findViewById(id);
								radioButton.setVisibility(View.VISIBLE);
							
						}
					  initViewPage();
				}
				 else {
					new CustomerAlertDialog(HotDetail2Activity.this,"•ºŸoÏàêPŸoÎï˜IˆDÆ¬").show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(HotDetail2Activity.this).show();
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	private void initViewPage() {
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPicList.size();
		}
		@Override
		public Fragment getItem(int arg0) {
			Log.e("getItem", ""+arg0);
		MyFrame  frame=new MyFrame();
		frame.setUrl(mPicList.get(arg0));
		frame.setText(mPicNameList.get(arg0));
			return frame ;
		}
	});
	  
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				Log.e("mViewPager", ""+position);
				int   id = mRadioList.get(position);
				RadioButton radioButton = (RadioButton) findViewById(id);
				radioButton.setChecked(true);
				mTv_PicName.setText(mPicNameList.get(position));
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
