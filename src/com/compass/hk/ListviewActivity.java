package com.compass.hk;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import com.webdesign688.compass.R;

public class ListviewActivity extends FragmentActivity {

	private ListView mListView;
	private String[] mStringLists;
	private int[] mNumLists;
	private int mId;
	private int mDistrictNum;
	private  ArrayList<Data> mDataList=new ArrayList<ListviewActivity.Data>();
	private String[] mString_NumLists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		
		Intent intent = getIntent();
		String string_Title = intent.getStringExtra("title");
		
		mId = intent.getIntExtra("id", 0);
		if (mId==1) {
			mStringLists = getResources().getStringArray(R.array.postbuild_rentsale);
			mNumLists=new int[]{0,1,2};
			initListView();
		}
		if (mId==2) {
			mStringLists = getResources().getStringArray(R.array.postbuild_propertype);
			mNumLists=new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
			initListView();
		}
		if (mId==3) {
			mStringLists = getResources().getStringArray(R.array.postbuild_district);
			mNumLists=new int[]{1,2,3,4};
			initListView();
		}
		if (mId==4) {
			mDistrictNum = intent.getIntExtra("districtnum", 0);
			Log.e("mDistrictNum", ""+mDistrictNum);
			download();
		}
		if (mId==5) {
			mStringLists = getResources().getStringArray(R.array.postbuild_floor);
			
			initListView();
		}
	
		if (mId==6) {
			mStringLists = getResources().getStringArray(R.array.postbuild_livingroom);
			mString_NumLists=new String[]{"1","2","3","4","5","5+"};
			initListView();
		}
		if (mId==7) {
			mStringLists = getResources().getStringArray(R.array.postbuild_diningroom);
			initListView();
		}
		if (mId==8) {
			mStringLists = getResources().getStringArray(R.array.postbuild_bedroom);
			mString_NumLists=new String[]{"1","2","3","4","5","5+"};
			initListView();
		}
		if (mId==9) {
			mStringLists = getResources().getStringArray(R.array.postbuild_flat);
			initListView();
		}
		if (mId==10) {
			mStringLists = getResources().getStringArray(R.array.postbuild_kitchen);
			mString_NumLists=new String[]{"1","2","3","4","5","5+"};
			initListView();
		}
		if (mId==11) {
			mStringLists = getResources().getStringArray(R.array.postbuild_toilet);
			mString_NumLists=new String[]{"1","2","3","4","5","5+"};
			initListView();
		}
		if (mId==12) {
			mStringLists = getResources().getStringArray(R.array.postbuild_sitto);
			initListView();
		}
		if (mId==13) {
			mStringLists = getResources().getStringArray(R.array.postbuild_schoolnet);
			initListView();
		}
		if (mId==14) {
			mStringLists = getResources().getStringArray(R.array.postbuild_flat);
			mNumLists=new int[]{1,2,3,4,5};
			initListView();
		}
		if (mId==15) {
			download();
		}
		if (mId==16) {
			download();
		}
		if (mId==17) {
			download();
		}
		
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle(string_Title);
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		
	}

	private void download() {
		if (mId==4) {
			new DownLoadAsyTask().execute(Content.URL_DISTRICTLIST+mDistrictNum);
		}
		else {
			new DownLoadAsyTask().execute(Content.URL_DISTRICTLIST_ALL);
			
		}
		// TODO Auto-generated method stub
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
					 JSONArray data_array = jsonObject.getJSONArray("data");
					 for (int i = 0; i < data_array.length(); i++) {
						 Data data=new Data();
						 JSONObject jsonObject2 = data_array.getJSONObject(i);
						 data.DistrictID = jsonObject2.getString("DistrictID");
						 data.DistrictNameHK= jsonObject2.getString("DistrictNameHK");
						 data.DistrictNameEN= jsonObject2.getString("DistrictNameEN");
						 mDataList.add(data);
					}
					 initListView();
				 }
				 else {
					new CustomerAlertDialog(ListviewActivity.this,"暫無相關地區信息").show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(ListviewActivity.this).show();
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	class Data{
		String   DistrictID;
		String   DistrictNameHK;
		String   DistrictNameEN;
		@Override
		public String toString() {
			return "Data [DistrictID=" + DistrictID + ", DistrictNameHK="
					+ DistrictNameHK + ", DistrictNameEN=" + DistrictNameEN
					+ "]";
		}
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
					 data.putExtra("name", mStringLists[arg2]);
					 data.putExtra("num", mNumLists[arg2]);
						setResult(1, data);
						finish();
					break;
                 case 2:
                	 data.putExtra("name", mStringLists[arg2]);
                	 data.putExtra("num", mNumLists[arg2]);
 					setResult(2, data);
 					finish();
					break;
                 case 3:
                	 data.putExtra("name", mStringLists[arg2]);
                	 data.putExtra("num", mNumLists[arg2]);
					setResult(3, data);
					finish();
	                break;
	                
                 case 4:
                	 data.putExtra("name", mDataList.get(arg2).DistrictNameHK+" "+mDataList.get(arg2).DistrictNameEN);
                	 data.putExtra("num",  mDataList.get(arg2).DistrictID);
 					setResult(4, data);
 					finish();
 	                break;
                 case 5:
                	 data.putExtra("name", mStringLists[arg2]);
                
 					setResult(5,data);
 					finish();
 	                break;
                 case 6:
                	 data.putExtra("name", mStringLists[arg2]);
                	 data.putExtra("string_num",  mString_NumLists[arg2]);
  					setResult(6, data);
  					finish();
  	                break;
                 case 7:
                	 data.putExtra("name", mStringLists[arg2]);
  					setResult(7, data);
  					finish();
  	                break;
                 case 8:
                	 data.putExtra("name", mStringLists[arg2]);
                	 data.putExtra("string_num",  mString_NumLists[arg2]);
  					setResult(8, data);
  					finish();
  	                break;
  	                
                 case 9:
  	                	 data.putExtra("name", mStringLists[arg2]);
  	  					setResult(9, data);
  	  				finish();
  	  	                break;
                 case 10:
	                	 data.putExtra("name", mStringLists[arg2]);
  	  	            data.putExtra("string_num",  mString_NumLists[arg2]);
	  					setResult(10, data);
	  					finish();
	  	                break;
                 case 11:
                	 data.putExtra("name", mStringLists[arg2]);
	  	            data.putExtra("string_num",  mString_NumLists[arg2]);
  					setResult(11, data);
  					finish();
  	                break;
                 case 12:
                	 data.putExtra("name", mStringLists[arg2]);
  					setResult(12, data);
  					finish();
  	                break;
                 case 13:
                	 data.putExtra("name", mStringLists[arg2]);
  					setResult(13, data);
  					finish();
  	                break;
                 case 14:
                	 data.putExtra("name", mStringLists[arg2]);
                	 data.putExtra("num", mNumLists[arg2]);
  					setResult(14, data);
  					finish();
  	                break;
                 case 15:
         			Intent data1=new Intent();
        			data1.putExtra("AgentArea1", mDataList.get(arg2).DistrictNameHK+" "+mDataList.get(arg2).DistrictNameEN);
        			data1.putExtra("DistrictID1", mDataList.get(arg2).DistrictID);
        			setResult(1, data1);
        			// TODO Auto-generated method stub
        			finish();

             //   	 RegisterActivity.setAgentArea1(mDataList.get(arg2).DistrictNameHK+" "+mDataList.get(arg2).DistrictNameEN);
               // 	 RegisterActivity.setDistrictID1(mDataList.get(arg2).DistrictID);
 				//	finish();
 	                break;
                 case 16:
          			Intent data11=new Intent();
         			data11.putExtra("AgentArea2", mDataList.get(arg2).DistrictNameHK+" "+mDataList.get(arg2).DistrictNameEN);
         			data11.putExtra("DistrictID2", mDataList.get(arg2).DistrictID);
         			setResult(1, data11);
         			// TODO Auto-generated method stub
         			

                	// RegisterActivity.setAgentArea2(mDataList.get(arg2).DistrictNameHK+" "+mDataList.get(arg2).DistrictNameEN);
                //	 RegisterActivity.setDistrictID2(mDataList.get(arg2).DistrictID);
 					finish();
 	                break;
                 case 17:
           			Intent data111=new Intent();
          			data111.putExtra("AgentArea3", mDataList.get(arg2).DistrictNameHK+" "+mDataList.get(arg2).DistrictNameEN);
          			data111.putExtra("DistrictID3", mDataList.get(arg2).DistrictID);
          			setResult(1, data111);

                	// RegisterActivity.setAgentArea3(mDataList.get(arg2).DistrictNameHK+" "+mDataList.get(arg2).DistrictNameEN);
                //	 RegisterActivity.setDistrictID3(mDataList.get(arg2).DistrictID);
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
			if (mId==4) {
				mTextView.setText(mDataList.get(position).DistrictNameHK+" "+mDataList.get(position).DistrictNameEN);
			}
			else if (mId==15) {
				mTextView.setText(mDataList.get(position).DistrictNameHK+" "+mDataList.get(position).DistrictNameEN);
			}
			else if (mId==16) {
				mTextView.setText(mDataList.get(position).DistrictNameHK+" "+mDataList.get(position).DistrictNameEN);
			}
			else if (mId==17) {
				mTextView.setText(mDataList.get(position).DistrictNameHK+" "+mDataList.get(position).DistrictNameEN);
			}
			
			else {
				mTextView.setText(mStringLists[position]);
			}
			return layout;
		}
		@Override
		public int getCount() {
			if (mId==4) {
				return  mDataList.size();
			}
			else if (mId==15) {
				return  mDataList.size();
			}
			else if (mId==16) {
				return  mDataList.size();
			}
			else if (mId==17) {
				return  mDataList.size();
			}
			else {
				return mStringLists.length;
			}
			// TODO Auto-generated method stub
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
