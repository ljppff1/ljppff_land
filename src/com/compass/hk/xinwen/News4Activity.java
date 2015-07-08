package com.compass.hk.xinwen;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.HotActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.hot.HotDetailActivity;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.compass.hk.xinwen.News4Activity.Data;
import com.compass.hk.xinwen.News4Activity.DownLoadAsyTask;
import com.compass.hk.xinwen.News4Activity.Myadapter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class News4Activity extends Activity {

	private ListView mListView;
	public ArrayList<Data> mDataList=new ArrayList<News4Activity.Data>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news1);
		downLoad();
		
	}
	
	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_AJNEWSLIST);
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
						 data.Day = jsonObject2.getString("Day");
						 data.Month=jsonObject2.getString("Month");
						 data.Title=jsonObject2.getString("Title");
						 data.Year=jsonObject2.getString("Year");
						 mDataList.add(data);
                          data.toString();						 
					}
					  initListView();
				}
				 else {
					 findViewById(R.id.progressBar_new).setVisibility(View.GONE);
					new AlertInfoDialog(News4Activity.this).show();
				}
			} catch (JSONException e) {
				          findViewById(R.id.progressBar_new).setVisibility(View.GONE);
				          if(mDataList.isEmpty())
				new Dialog_noInternet(News4Activity.this).show();
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
		String   Title;
		String   Year;
		String   Month;
		String   Day;
		@Override
		public String toString() {
			return "Data [ID=" + ID + ", Title=" + Title + ", Year=" + Year
					+ ", Month=" + Month + ", Day=" + Day + "]";
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
	                 Intent intent = new Intent(News4Activity.this,NewContentActivity.class);
	                 intent.putExtra("ID", mDataList.get(position).ID);
	                 intent.putExtra("url_id", "4");
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
			         
			           if (mDataList.get(position).Year.equals("")||mDataList.get(position).Year==null) {
							
						} else {
							tv_Date.setText(  mDataList.get(position).Year+"."+mDataList.get(position).Month+"."+mDataList.get(position).Day);
						}
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
			return 0;
		}
	}

}
