package com.compass.hk.badfile;
import com.webdesign688.compass.R;

import java.util.ArrayList;

import org.gradle.wrapper.Download;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DeadLineActivity extends Activity {

	private ListView mListView;
	private ArrayList<Data>  mDataList=new ArrayList<DeadLineActivity.Data>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dead_line);
		downLoad();
	}
	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_BADFILE_TYPE);
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
						 data.ItemID = jsonObject2.getString("ItemID");
						 data.ItemName= jsonObject2.getString("ItemName");
						 data.ItemPrice= jsonObject2.getString("ItemPrice");
						 mDataList.add(data);
					}
					 Log.e("Deadline", mDataList.toString());
					 initListView();
				 }
				 else {
					 findViewById(R.id.progressBar_deadline).setVisibility(View.GONE);
					new CustomerAlertDialog(DeadLineActivity.this,"•ºŸoÏàêPÐÅÏ¢").show();
				}
			} catch (JSONException e) {
				findViewById(R.id.progressBar_deadline).setVisibility(View.GONE);
				if(mDataList==null)
				new Dialog_noInternet(DeadLineActivity.this).show();
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	public void initListView() {
		// TODO Auto-generated method stub
		 findViewById(R.id.progressBar_deadline).setVisibility(View.GONE);
		mListView = (ListView) findViewById(R.id.listView_deadline);
		mListView.setVisibility(View.VISIBLE);
		mListView.setAdapter(new Myadapter());
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			Frame_danger4.setText(mDataList.get(position).ItemName+"(HK$"+mDataList.get(position).ItemPrice+") ");
			Frame_danger4.setID(mDataList.get(position).ItemID);
			Frame_danger4.setTag(true);
			finish();
			}
		});
	}
	
	class Myadapter  extends  BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView==null) {
				convertView = getLayoutInflater().inflate(R.layout.item_listview_deadline, null);
			} 
			TextView textView = (TextView) convertView.findViewById(R.id.tv_listview_deadline);
			textView.setText(mDataList.get(position).ItemName+"(HK$"+mDataList.get(position).ItemPrice+")");
			
			return convertView;
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
	class Data{
		String ItemID;
		String ItemName;
		String ItemPrice;
		@Override
		public String toString() {
			return "Data [ItemID=" + ItemID + ", ItemName=" + ItemName
					+ ", ItemPrice=" + ItemPrice + "]";
		}
	}
}
