package com.compass.hk.popowindow;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode.Mode;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

import com.compass.hk.ListviewActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.util.Content;
import com.compass.hk.util.UIHelper;
import com.compass.hk.util.getJson;
public class Rent_ZonePPWindow extends BasePopupWindow implements
		OnClickListener, OnCheckedChangeListener {
	private ArrayList<Data> mDataList=new ArrayList<Rent_ZonePPWindow.Data>();
	private ArrayList<Data> mDataList_hongkong=new ArrayList<Rent_ZonePPWindow.Data>();
	private ArrayList<Data> mDataList_jiulong=new ArrayList<Rent_ZonePPWindow.Data>();
	private ArrayList<Data> mDataList_xinjie=new ArrayList<Rent_ZonePPWindow.Data>();
	private ArrayList<Data> mDataList_lidao=new ArrayList<Rent_ZonePPWindow.Data>();

	private ImageButton mSearch_Button;

	private onSearchBarItemClickListener mOnSearchBarItemClickListener;

	private ListAdapter adapter;

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

	private ListView mListView;

	private RadioButton button_nolimit;

	private RadioButton button_hongkong;

	private RadioButton button_jiulong;

	private RadioButton button_xinjie;

	private RadioButton button_lidao;

	private int style;
//	private SharedPreferences sp;

	public Rent_ZonePPWindow(Context context, int width, int height) {
		super(LayoutInflater.from(context).inflate(
				R.layout.ppwindow_zone, null), width, height);
		this.mContext=context;
	}

	@Override
	public void initViews() {
		
		//sp = mContext.getSharedPreferences("compass", 0);
		button_nolimit = (RadioButton) findViewById(R.id.radio_nolimit);
		button_hongkong = (RadioButton) findViewById(R.id.radio_hongkong);
		button_jiulong = (RadioButton) findViewById(R.id.radio_jiulong);
		button_xinjie = (RadioButton) findViewById(R.id.radio_xinjie);
		button_lidao = (RadioButton) findViewById(R.id.radio_lidao);
		button_nolimit.setOnCheckedChangeListener(this);
		button_hongkong.setOnCheckedChangeListener(this);
		button_jiulong.setOnCheckedChangeListener(this);
		button_xinjie.setOnCheckedChangeListener(this);
		button_lidao.setOnCheckedChangeListener(this);
		mListView = (ListView) findViewById(R.id.listView_ppwindow);
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
		
		
		//下載地區
		new DownLoadAsyTask_all().execute("http://www.hk-compass.com/json/districtlist.php");
		new DownLoadAsyTask_hongkog().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=1");
		new DownLoadAsyTask_jiulong().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=2");
		new DownLoadAsyTask_xinjie().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=3");
		new DownLoadAsyTask_lidao().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=4");
	}

	@Override
	public void initEvents() {
		//mSearch_Button.setOnClickListener(this);
	} 

	@Override
	public void init() {

	}
	class DownLoadAsyTask_all extends AsyncTask<String, Void, String>{  
				@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					// sp.edit().putString("data_all", result).commit();
					 JSONArray data_array = jsonObject.getJSONArray("data");
					 mDataList.clear();
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
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	class DownLoadAsyTask_hongkog extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				int  num_code=Integer.valueOf(string_code);
				if (num_code==1) {
			//		 sp.edit().putString("data_hongkong", result).commit();
					 JSONArray data_array = jsonObject.getJSONArray("data");
					 mDataList_hongkong.clear();
					 for (int i = 0; i < data_array.length(); i++) {
						 Data data=new Data();
						 JSONObject jsonObject2 = data_array.getJSONObject(i);
						 data.DistrictID = jsonObject2.getString("DistrictID");
						 data.DistrictNameHK= jsonObject2.getString("DistrictNameHK");
						 data.DistrictNameEN= jsonObject2.getString("DistrictNameEN");
						 mDataList_hongkong.add(data);
					}
					 initListView();
				 }
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		@Override
		protected String doInBackground(String... params) {
			String str=params[0];
			return getJson.getData(str);
		}
	}
	class DownLoadAsyTask_jiulong extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				int  num_code=Integer.valueOf(string_code);
				if (num_code==1) {
				//	 sp.edit().putString("data_jiulong", result).commit();
					 JSONArray data_array = jsonObject.getJSONArray("data");
					 mDataList_jiulong.clear();
					 for (int i = 0; i < data_array.length(); i++) {
						 Data data=new Data();
						 JSONObject jsonObject2 = data_array.getJSONObject(i);
						 data.DistrictID = jsonObject2.getString("DistrictID");
						 data.DistrictNameHK= jsonObject2.getString("DistrictNameHK");
						 data.DistrictNameEN= jsonObject2.getString("DistrictNameEN");
						 mDataList_jiulong.add(data);
					}
					 initListView();
				 }
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		@Override
		protected String doInBackground(String... params) {
			String str=params[0];
			return getJson.getData(str);
		}
	}
	class DownLoadAsyTask_xinjie extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				int  num_code=Integer.valueOf(string_code);
				if (num_code==1) {
				//	 sp.edit().putString("data_xinjie", result).commit();
					 JSONArray data_array = jsonObject.getJSONArray("data");
					 mDataList_xinjie.clear();
					 for (int i = 0; i < data_array.length(); i++) {
						 Data data=new Data();
						 JSONObject jsonObject2 = data_array.getJSONObject(i);
						 data.DistrictID = jsonObject2.getString("DistrictID");
						 data.DistrictNameHK= jsonObject2.getString("DistrictNameHK");
						 data.DistrictNameEN= jsonObject2.getString("DistrictNameEN");
						 mDataList_xinjie.add(data);
					}
					 initListView();
				 }
			} catch (JSONException e) {
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
	class DownLoadAsyTask_lidao extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				int  num_code=Integer.valueOf(string_code);
				if (num_code==1) {
				///	 sp.edit().putString("data_lidao", result).commit();
					 JSONArray data_array = jsonObject.getJSONArray("data");
					 mDataList_lidao.clear();
					 for (int i = 0; i < data_array.length(); i++) {
						 Data data=new Data();
						 JSONObject jsonObject2 = data_array.getJSONObject(i);
						 data.DistrictID = jsonObject2.getString("DistrictID");
						 data.DistrictNameHK= jsonObject2.getString("DistrictNameHK");
						 data.DistrictNameEN= jsonObject2.getString("DistrictNameEN");
						 mDataList_lidao.add(data);
					}
					 initListView();
				 }
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		@Override
		protected String doInBackground(String... params) {
			String str=params[0];
			return getJson.getData(str);
		}
	}
public class ListAdapter extends BaseAdapter{
	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		   View layout = LayoutInflater.from(mContext).inflate(
				R.layout.item_listview_post, null);
		   Data  data=null;
		   if (style==0) {
			     data = mDataList.get(position);
		}
		   if (style==1) {
			  data = mDataList_hongkong.get(position);
		   }
		   if (style==2) {
			  data = mDataList_jiulong.get(position);
		   }
		   if (style==3) {
			   data = mDataList_xinjie.get(position);
		   }
		   if (style==4) {
			   data = mDataList_lidao.get(position);
		   }
		   
			TextView  mTextView = (TextView) layout.findViewById(R.id.tv_listview_post_title);
			mTextView.setText(data.DistrictNameHK+"  "+data.DistrictNameEN);
		return layout;
	}
	@Override
	public int getCount() {
		  if (style==0) {
			   return mDataList.size();
		}
		   if (style==1) {
			   return mDataList_hongkong.size();
		   }
		   if (style==2) {
			   return mDataList_jiulong.size();
		   }
		   if (style==3) {
			   return mDataList_xinjie.size();
		   }
		   if (style==4) {
			   return mDataList_lidao.size();
		   }
		   else {
			return  0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
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
		      searchType="0";
		      button.setText("業主盤");
		          break;
		case R.id.button_main2:
	          linea_select.setVisibility(View.GONE);
	          button.setVisibility(View.VISIBLE);
	          button.setText("代理盤");
	          searchType="1";
	          break;
		case R.id.button_main3:
	        
	          linea_select.setVisibility(View.GONE);
	          button.setVisibility(View.VISIBLE);
	          button.setText("全部樓盤");
	          searchType="2";
	          break;
	  default  :
				break;
		}
		//dismiss();
	}

	public void initListView() {
		adapter=new ListAdapter();
		mListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
	}

	public void setOnSearchBarItemClickListener(
			onSearchBarItemClickListener listener) {
		mOnSearchBarItemClickListener = listener;
	}

	public interface onSearchBarItemClickListener {
		
		void onSearchButtonClick(String string, String searchType);

	}
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		if (arg1) {
			
			switch (arg0.getId()) {
			case R.id.radio_nolimit:
				style = 0;
				if (mDataList.size()>0) {
					adapter.notifyDataSetChanged();
				} else {
					new DownLoadAsyTask_all().execute("http://www.hk-compass.com/json/districtlist.php");
				}
				break;
			case R.id.radio_hongkong:
				style = 1;
				if (mDataList_hongkong.size()>0) {
					adapter.notifyDataSetChanged();
				} else {
					new DownLoadAsyTask_hongkog().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=1");
				}
				break;
			case R.id.radio_jiulong:
				
				style = 2;
				if (mDataList_jiulong.size()>0) {
					adapter.notifyDataSetChanged();
				} else {
					new DownLoadAsyTask_jiulong().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=2");
				}
				
				
				break;
			case R.id.radio_xinjie:
				
				style = 3;
				if (mDataList_xinjie.size()>0) {
					adapter.notifyDataSetChanged();
				} else {
					new DownLoadAsyTask_xinjie().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=3");
				}
				
				
				break;
			case R.id.radio_lidao:
				
				style = 4;
				if (mDataList_lidao.size()>0) {
					adapter.notifyDataSetChanged();
				} else {
					new DownLoadAsyTask_lidao().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=4");
				}
				
				
				break;
				
			default:
				break;
			}
		}
	}
}
