package com.compass.hk.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.rent.RentActivity;
import com.compass.hk.rent.RentDetailActivity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchResult1Activity extends FragmentActivity {

	private String mData;
	 private ArrayList<Data> mDataList=new ArrayList<SearchResult1Activity.Data>();
	private ListView mListView;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private ProgressBar progressBar_sale;
	private String KEY;
	private String STYLE;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		progressBar_sale =(ProgressBar)this.findViewById(R.id.progressBar_sale);
		progressBar_sale.setVisibility(View.VISIBLE);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("搜尋結果");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();

		Intent intent = getIntent();
		KEY = intent.getStringExtra("KEY");
		STYLE = intent.getStringExtra("STYLE");
		dowhat();
	//	jsondata();
	}
	public void dowhat(){
		 RequestParams params = new RequestParams();
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
        nameValuePairs.add(new BasicNameValuePair("SearchType", STYLE));
       	 nameValuePairs.add(new BasicNameValuePair("KeyWord", KEY));
       	 
        params.addBodyParameter(nameValuePairs);
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
       		Content.URL_PROPERTYSEARCH,
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
                    	
                    	try {
							JSONObject result =new JSONObject(responseInfo.result);
							  String code = result.getString("code");
							  String msg = result.getString("msg");
							  String mData = result.getString("data");
							  
							  if ("1".equals(code)) {
									try {
										JSONArray jsonArray = new JSONArray(mData);
										for (int i = 0; i < jsonArray.length(); i++) {
											  
											  Data  data=new Data();
											 JSONObject jsonObject2 = jsonArray.getJSONObject(i);
											 data.ID= jsonObject2.getString("ID");
											 data.Name= jsonObject2.getString("Name");
											 data.StreetName = jsonObject2.getString("StreetName");
											 data.AreaGross=jsonObject2.getString("AreaGross");
											 data.AreaNet=jsonObject2.getString("AreaNet");
											 data.CoverPic=jsonObject2.getString("CoverPic");
											 data.SellingPrice=jsonObject2.getString("SellingPrice");
											 data.RentPrice=jsonObject2.getString("RentPrice");
											 mDataList.add(data);
											 Log.e("jsondata", ""+mDataList.toString());
										}
										 
									} catch (JSONException e) {
										// TODO Auto-generated catch block
									
									}
									if(mDataList.size()>0)
									 initListView();
									  
							}
							  else {
							Toast.makeText(SearchResult1Activity.this, "無相關搜索結果",0).show();
							
							}
							
						} catch (JSONException e) {
							Toast.makeText(SearchResult1Activity.this, "無相關搜索結果",0).show();
							e.printStackTrace();
						}
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                    //	Toast.makeText(ForgetPassword.this, "未發送成功，", Toast.LENGTH_SHORT).show();  
                    }
                });

	}

	//UIL
		private void initImageLoaderOptions() {
			options = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.drawable.ic_empty)
					.cacheInMemory()
					.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
					.bitmapConfig(Bitmap.Config.RGB_565).build();
		}

	private void jsondata() {
		   JSONArray jsonArray;
		try {
			jsonArray = new JSONArray(mData);
			for (int i = 0; i < jsonArray.length(); i++) {
				  
				  Data  data=new Data();
				 JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				 data.ID= jsonObject2.getString("ID");
				 data.Name= jsonObject2.getString("Name");
				 data.StreetName = jsonObject2.getString("StreetName");
				 data.AreaGross=jsonObject2.getString("AreaGross");
				 data.AreaNet=jsonObject2.getString("AreaNet");
				 data.CoverPic=jsonObject2.getString("CoverPic");
				 data.SellingPrice=jsonObject2.getString("SellingPrice");
				 data.RentPrice=jsonObject2.getString("RentPrice");
				 mDataList.add(data);
				 Log.e("jsondata", ""+mDataList.toString());
			}
			 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		
		}
		if(mDataList.size()>0)
		 initListView();
		  
	}
	private void initListView() {
       mListView = (ListView) findViewById(R.id.listView_sale);
       mListView.setVisibility(View.VISIBLE);
       mListView.setAdapter(new Myadapter());
       progressBar_sale.setVisibility(View.GONE);
       mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				//setid
				Bean.setRentID(mDataList.get(position).ID);
				Log.e("initListView", mDataList.get(position).ID);
				
                Intent intent = new Intent(SearchResult1Activity.this,RentDetailActivity.class);
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
			           mTv_Saleprice.setText("售:"+mDataList.get(position).SellingPrice+"萬");
			           
			           if ("0".endsWith(mDataList.get(position).RentPrice)) {
			        	   mTv_Rentprice.setText("租: -");
					}
			           else {
						
			        	   mTv_Rentprice.setText("租:"+mDataList.get(position).RentPrice);
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
}
