package com.compass.hk.compass;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.login_register.RegisterActivity;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Listview_Tip_Activity extends FragmentActivity {

	private ListView mListView;
	private String[] mStringLists;
	private int[] mNumLists;
	private int mId;
	private int mDistrictNum;
	private String[] mString_NumLists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		Intent intent = getIntent();
		String string_Title = intent.getStringExtra("title");
		
		mId = intent.getIntExtra("id", 0);
		if (mId==1) {
			mStringLists = getResources().getStringArray(R.array.addtip_housesource);
			initListView();
		}
		else if(mId==2) {
			mStringLists = getResources().getStringArray(R.array.addtip_area);
			initListView();
		}
		else	if (mId==3) {
			String area_id = intent.getStringExtra("area_id");
			if ("1".equals(area_id)) {
				mStringLists = getResources().getStringArray(R.array.addtip_hongkong);
			}
			else if ("2".equals(area_id)) {
				mStringLists = getResources().getStringArray(R.array.addtip_jiulong);
			}
           else if ("3".equals(area_id)) {
        	   mStringLists = getResources().getStringArray(R.array.addtip_xinjie);
			  }
           else {
			
		}
			initListView();
		}
		else if (mId==4) {
			
           mStringLists = getResources().getStringArray(R.array.addtip_housetype1);
			
			initListView();
		}
		else if (mId==5) {
			mStringLists = getResources().getStringArray(R.array.addtip_housetype2);
			
			initListView();
		}
		else if (mId==6) {
			mStringLists = getResources().getStringArray(R.array.addtip_roomnum);
			initListView();
		}
		else if(mId==7) {
			mStringLists = getResources().getStringArray(R.array.addtip_housefloor);
			initListView();
		}
		else {
			
		}
		
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle(string_Title);
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		
	}

	private void initListView() {
		mListView = (ListView) findViewById(R.id.listView_fangfan);
		mListView.setAdapter(new MyAdapter());
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent data=new Intent();
				switch (mId) {
				 case 1:
					 AddTipActivity.setResource(mStringLists[arg2]);
						finish();
					break;
                 case 2:
                	 AddTipActivity.setArea(mStringLists[arg2]);
                	 AddTipActivity.setAreaId(""+arg2);
						finish();
					break;
                 case 3:
                	 AddTipActivity.setDistrict(mStringLists[arg2]);
						finish();
	                break;
	                
                 case 4:
                	 AddTipActivity.setType1(mStringLists[arg2]);
						finish();
 	                break;
                 case 5:
                	 AddTipActivity.setType2(mStringLists[arg2]);
						finish();
 	                break;
                 case 6:
                	 AddTipActivity.setRoomNum(mStringLists[arg2]);
						finish();
  	                break;
                 case 7:
                	 AddTipActivity.setHouseFloor(mStringLists[arg2]);
						finish();
  	                break;
  	                
				default:
					break;
				}
			}
		
		});
	}
 
	class MyAdapter extends  BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = getLayoutInflater().inflate(R.layout.item_listview_post, null);
			TextView  mTextView = (TextView) layout.findViewById(R.id.tv_listview_post_title);
				mTextView.setText(mStringLists[position]);
			return layout;
		}
		@Override
		public int getCount() {
				return mStringLists.length;
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

		}

}
