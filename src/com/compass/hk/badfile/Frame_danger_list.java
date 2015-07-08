package com.compass.hk.badfile;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.compass.hk.xinwen.NewContent3Activity;
import com.compass.hk.xinwen.NewContentActivity;
import com.compass.hk.xinwen.News3Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


import com.webdesign688.compass.R;




public class Frame_danger_list extends Fragment 
{  

	private View mLayout;
	private ListView mListView;
	private ArrayList<Data> mDataList=new ArrayList<Frame_danger_list.Data>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.activity_news1, null);	
                             downLoad();
		return mLayout ; 
	}

	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_BADFILE_NEWLISTS);
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
					mLayout.findViewById(R.id.progressBar_new).setVisibility(View.GONE);
					new AlertInfoDialog(getActivity()).show();
				}
			} catch (JSONException e) {
				          mLayout.findViewById(R.id.progressBar_new).setVisibility(View.GONE);
				new Dialog_noInternet(getActivity()).show();
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

	       mLayout.findViewById(R.id.progressBar_new).setVisibility(View.GONE);
	       mListView = (ListView) mLayout.findViewById(R.id.listView_new);
	       mListView.setVisibility(View.VISIBLE);
	       mListView.setAdapter(new Myadapter());
	       mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {
	                 Intent intent = new Intent(getActivity(),NewContentActivity.class);
	                 intent.putExtra("ID", mDataList.get(position).ID);
					startActivity(intent);								
				}
			});
		
		// TODO Auto-generated method stub
		
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder  viewHolder=null;
			          if (convertView==null) {
			        	  convertView = getActivity().getLayoutInflater().inflate(R.layout.item_listview_new1, null);
			        	  viewHolder=new ViewHolder();
			        	  viewHolder.tv_Title= (TextView) convertView.findViewById(R.id.tv_listview_new_title);
			        	  viewHolder.tv_Date= (TextView) convertView.findViewById(R.id.tv_listview_new_date);
			        	 convertView.setTag(viewHolder);
					}
			          else {
			        	 viewHolder = (ViewHolder) convertView.getTag();
					}
			          viewHolder.tv_Title.setText(mDataList.get(position).Title);
			         
			          if (mDataList.get(position).Year.equals("null")||mDataList.get(position).Year==null) {
							
						} else {
							viewHolder.tv_Date.setText(  mDataList.get(position).Year+"."+mDataList.get(position).Month+"."+mDataList.get(position).Day);
						}
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
	class ViewHolder{
		TextView   tv_Title;
		TextView   tv_Date;
	}

}
