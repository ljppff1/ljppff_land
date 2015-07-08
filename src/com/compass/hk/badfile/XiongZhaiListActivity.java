package com.compass.hk.badfile;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.webdesign688.compass.R;
import com.compass.hk.badfile.Frame_danger1.OnFragmentListener;
import com.compass.hk.badfile.Frame_danger3.Data;
import com.compass.hk.badfile.Frame_danger3.DownLoadAsyTask;
import com.compass.hk.badfile.Frame_danger3.Myadapter;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.dialog.CustomerAlertDialog.mOnClickListener;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.login_register.Login_Regiester;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.compass.hk.xinwen.NewContentActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
/**
 * Ð×Õ¬µµ°¸
 * @author liujun
 *
 */
@SuppressLint("NewApi")
public class XiongZhaiListActivity extends FragmentActivity {

	private FragmentTransaction ft;
	private String search;
	private ArrayList<Data> mDataList=new ArrayList<XiongZhaiListActivity.Data>();
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xiong_zhai_list);
	   search =	getIntent().getStringExtra("SEARCH");
		FragmentManager sfm = getSupportFragmentManager();
		ft = sfm.beginTransaction();
		
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("Ð×Õ¬™n°¸");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		initUi();
		 //  downLoad();
		postData("", "");
	
	}
	private void postData(String text, String paythod2) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 RequestParams params = new RequestParams();
		         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
		         nameValuePairs.add(new BasicNameValuePair("KeyWords",search));
					
		         params.addBodyParameter(nameValuePairs);
		         HttpUtils http = new HttpUtils();
		         http.send(HttpRequest.HttpMethod.POST,
		        		 Content.URL_BADFILES_LIST,
		                 params,
		                 new RequestCallBack<String>() {   

		                     @Override
		                     public void onStart() {
		                     }
		                     @Override
		                     public void onLoading(long total, long current, boolean isUploading) {
		                     }
		                     @Override
		                     public void onSuccess(ResponseInfo<String> responseInfo) {
		                     	Log.e("onSuccess", "upload response:" + responseInfo.result);
		            			JSONObject jsonObject;
		            			try {
		            				jsonObject = new JSONObject(responseInfo.result);
		            				String string_code = jsonObject.getString("code");
		            				 int  num_code=Integer.valueOf(string_code);
		            				 if (num_code==1) {
		            					 JSONArray array = jsonObject.getJSONArray("data");
		            					  for (int i = 0; i < array.length(); i++) {
		            						  
		            						  Data  data=new Data();
		            						  
		            						 JSONObject jsonObject2 = array.getJSONObject(i);
		            						 data.ID= jsonObject2.getString("ID");
		            						 data.Date = jsonObject2.getString("Date");
		            						 data.Title=jsonObject2.getString("Title");
		            						 data.Content=jsonObject2.getString("Content");
		            						 mDataList.add(data);
		                                      data.toString();						 
		            					}
		            					  initListView();
		            				}
		            				 else {
		            					findViewById(R.id.progressBar_new).setVisibility(View.GONE);
		            					new AlertInfoDialog(XiongZhaiListActivity.this).show();
		            				}
		            			} catch (JSONException e) {
		            				        findViewById(R.id.progressBar_new).setVisibility(View.GONE);
		            				new Dialog_noInternet(XiongZhaiListActivity.this).show();
		            				e.printStackTrace();
		            			}

		                    
		                     }
		                     @Override
		                     public void onFailure(HttpException error, String msg) {
		                     //	Toast.makeText(ForgetPassword.this, "Î´°lËÍ³É¹¦£¬", Toast.LENGTH_SHORT).show();  
		                    	 new Dialog_noInternet(XiongZhaiListActivity.this).show();
		                     }
		                 });
	}
	
	private void downLoad() {
		new DownLoadAsyTask().execute(Content.URL_BADFILES_LIST+"?KeyWords="+"ÜõÍþ»¨ˆ@");
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
						  
						  Data  data=new Data();
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 data.ID= jsonObject2.getString("ID");
						 data.Date = jsonObject2.getString("Date");
						 data.Title=jsonObject2.getString("Title");
						 data.Content=jsonObject2.getString("Content");
						 mDataList.add(data);
                          data.toString();						 
					}
					  initListView();
				}
				 else {
					findViewById(R.id.progressBar_new).setVisibility(View.GONE);
					new AlertInfoDialog(XiongZhaiListActivity.this).show();
				}
			} catch (JSONException e) {
				        findViewById(R.id.progressBar_new).setVisibility(View.GONE);
				new Dialog_noInternet(XiongZhaiListActivity.this).show();
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
		String   ID;
		String   Date;
		String   Title;
		String   Content;
		@Override
		public String toString() {
			return "Data [ID=" + ID + ", Date=" + Date + ", Title=" + Title
					+", Content=" + Content + "]";
		}
	}
	private void initListView() {

	       findViewById(R.id.progressBar_new).setVisibility(View.GONE);
	       mListView = (ListView) findViewById(R.id.listView_new);
	       mListView.setVisibility(View.VISIBLE);
	       mListView.setAdapter(new Myadapter());
	       mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {
	                 Intent intent = new Intent(getApplicationContext(),XiongZhaiListDetailActivity.class);
	                 intent.putExtra("TITLE", mDataList.get(position).Title);
	                 intent.putExtra("CONTENT", mDataList.get(position).Content);
	                 intent.putExtra("DATE", mDataList.get(position).Date);
	                 
					startActivity(intent);								
				}
			});
		
		// TODO Auto-generated method stub
		
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			           View layout = getLayoutInflater().inflate(R.layout.item_listview_new1, null);
			           TextView tv_Title= (TextView) layout.findViewById(R.id.tv_listview_new_title);
			           TextView tv_Date= (TextView) layout.findViewById(R.id.tv_listview_new_date);
			           tv_Title.setText(mDataList.get(position).Title);
                       tv_Date.setText(mDataList.get(position).Date);
			           return layout;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataList.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	}
	private void initUi() {
		   
	}
	

}