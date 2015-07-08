package com.compass.hk.nearby;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.rent.RentActivity;
import com.compass.hk.rent.RentDetailActivity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Nearby2Activity extends Activity {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private ListView mListView;
	private ArrayList<Data> mDataList=new ArrayList<Nearby2Activity.Data>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearby1);
		downLoad();
	}
	//UIL
		private void initImageLoaderOptions() {
			options = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.drawable.ic_empty)
					.cacheInMemory()
					.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
					.bitmapConfig(Bitmap.Config.RGB_565).build();
		}
		
		private void downLoad() {
			// TODO Auto-generated method stub
			new DownLoadAsyTask().execute(Content.URL_PROPERTYLIST+"3");
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
							 data.Name= jsonObject2.getString("Name");
							 data.StreetName = jsonObject2.getString("StreetName");
							 data.AreaGross=jsonObject2.getString("AreaGross");
							 data.AreaNet=jsonObject2.getString("AreaNet");
							 data.CoverPic=jsonObject2.getString("CoverPic");
							 data.SellingPrice=jsonObject2.getString("SellingPrice");
							 data.RentPrice=jsonObject2.getString("RentPrice");
							 mDataList.add(data);
	                          data.toString();						 
						}
						  initListView();
					}
					 else {
						 findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
						new AlertInfoDialog(Nearby2Activity.this).show();
					}
				} catch (JSONException e) {
					findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
					 if(mDataList.isEmpty())
					new Dialog_noInternet(Nearby2Activity.this).show();
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
			String   Name;
			String   StreetName;
			String   AreaGross;
			String   AreaNet;
			String   SellingPrice;
			String   RentPrice;
			String   CoverPic;
			@Override
			public String toString() {
				return "Data [ID=" + ID + ", Name=" + Name + ", StreetName="
						+ StreetName + ", AreaGross=" + AreaGross + ", AreaNet="
						+ AreaNet + ", SellingPrice=" + SellingPrice
						+ ", RentPrice=" + RentPrice + ", CoverPic=" + CoverPic
						+ "]";
			}
			
		}
		private void initListView() {
			findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
	       mListView = (ListView) findViewById(R.id.listView_tab_rent);
	       mListView.setVisibility(View.VISIBLE);
	       mListView.setAdapter(new Myadapter());
	       mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent,
						View view, int position, long id) {
					//setid
					Bean.setRentID(mDataList.get(position).ID);
					Log.e("initListView", mDataList.get(position).ID);
					
	                Intent intent = new Intent(Nearby2Activity.this,RentDetailActivity.class);
	                intent.putExtra("name", mDataList.get(position).Name);
					startActivity(intent);	
				}
			});
		}
		class  Myadapter extends   BaseAdapter{
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				           View layout = getLayoutInflater().inflate(R.layout.item_listview_rent, null);
				           TextView mTv_Name= (TextView) layout.findViewById(R.id.tv_listview_rent_name);
				           TextView mTv_StreetName= (TextView) layout.findViewById(R.id.tv_listview_rent_streetname);
				           TextView mTv_Areagross= (TextView) layout.findViewById(R.id.tv_listview_rent_areagross);
				           TextView mTv_Areanet= (TextView) layout.findViewById(R.id.tv_listview_rent_areanet);
				           TextView mTv_Saleprice= (TextView) layout.findViewById(R.id.tv_listview_rent_saleprice);
				           TextView mTv_Rentprice= (TextView) layout.findViewById(R.id.tv_listview_rent_rentprice);
				           
				           ImageView imageView= (ImageView) layout.findViewById(R.id.iv_listview_rent_pic);
				           
				           //UIL
				           initImageLoaderOptions();
							imageLoader.displayImage(mDataList.get(position).CoverPic,
									imageView, options);
				           //settext
				           mTv_Name.setText(mDataList.get(position).Name);
				           mTv_StreetName.setText(mDataList.get(position).StreetName);
				           mTv_Areagross.setText("建築面積:"+mDataList.get(position).AreaGross+"呎");
				           mTv_Areanet.setText("實用面積:"+mDataList.get(position).AreaNet+"呎");
				        
				           
				           mTv_Rentprice.setVisibility(View.GONE);
				           if ("0".endsWith(mDataList.get(position).RentPrice)) {
				        	   
				        	   mTv_Saleprice.setText("租: -");
						}
				           else {
							
				        	   mTv_Saleprice.setText("租:"+mDataList.get(position).RentPrice);
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
