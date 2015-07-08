package com.compass.hk;
import com.webdesign688.compass.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.popowindow.Rent_AreaPpwindow.onAreamainClickListener;
import com.compass.hk.popowindow.Rent_MorePpwindow;
import com.compass.hk.popowindow.Rent_MorePpwindow.onAreaClickListener;
import com.compass.hk.popowindow.Rent_PricePpwindow;
import com.compass.hk.popowindow.Rent_PricePpwindow.onPriceClickListener;
import com.compass.hk.popowindow.Rent_SortPpwindow;
import com.compass.hk.popowindow.Rent_SortPpwindow.onSortlistener;
import com.compass.hk.popowindow.Rent_AreaPpwindow;
import com.compass.hk.popowindow.Rent_ZonePPWindow;
import com.compass.hk.popowindow.Sale_PricePpwindow;
import com.compass.hk.popowindow.Sale_PricePpwindow.onPriceClickListener_sale;
import com.compass.hk.rent.RentActivity;
import com.compass.hk.rent.RentDetailActivity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.UIHelper;
import com.compass.hk.util.getJson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
/**
 * ljppff楼盘出售
 * @author liujun
 *
 */
public  class SaleActivity extends FragmentActivity implements onSortlistener, onAreaClickListener, onPriceClickListener_sale, onAreamainClickListener {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private Rent_ZonePPWindow mPpWindow;
	private Sale_PricePpwindow mPpWindow_price;
	private Rent_MorePpwindow mpMorePpwindow;   
	DisplayImageOptions options;
	private ListView mListView;
	private Button mButton_select1;
	private Button mButton_select2;
	private Button mButton_select3;
	private Button mButton_select4;
	private View mRela_Select;
	private String[] mStringList_zones;
	private ListView mListView_Select;
    private ArrayList<Data> mDataList=new ArrayList<SaleActivity.Data>();
    private ArrayList<Data> mDataList_origin=new ArrayList<SaleActivity.Data>();
    
	private View mLayout;
	private int pptoHeight;
	private SharedPreferences sp;
	private View mLayout2;
	private Rent_SortPpwindow mPpWindow_sort;
	private Myadapter myadapter;
	private Rent_AreaPpwindow mPpWindow_area;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sp = getSharedPreferences("compass", MODE_PRIVATE);
		setContentView(R.layout.activity_sale);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("出售");
		
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		initUi();
		initData();
	/*	try {
			downLoad();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		downloadsearch("0");
	}
	public void downloadsearch(String area11){
		 RequestParams params = new RequestParams();
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
        nameValuePairs.add(new BasicNameValuePair("PropertyLocation", area11));
        nameValuePairs.add(new BasicNameValuePair("RentSale", "1"));
        params.addBodyParameter(nameValuePairs);
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
       		 Content.URL_Search,
                params,
                new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(arg0.result);
							String string_code = jsonObject.getString("code");
							 int  num_code=Integer.valueOf(string_code);
							 if (num_code==1) {
								 //保存到本地
								 mDataList_origin.clear();
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
									 mDataList_origin.add(data);
									 
			                          data.toString();						 
								}
								  mDataList.clear();
								  mDataList.addAll(mDataList_origin);
									if (mDataList_origin.size()==0) {
										findViewById(R.id.tv_noresut).setVisibility(View.VISIBLE);
									}
									else {
										findViewById(R.id.tv_noresut).setVisibility(View.GONE);
									}

								  initListView();
							}
							 else {
								 findViewById(R.id.progressBar_sale).setVisibility(View.GONE);
								new AlertInfoDialog(SaleActivity.this).show();
							}
						} catch (JSONException e) {
							 if(mDataList.isEmpty())
							new Dialog_noInternet(SaleActivity.this).show();
							e.printStackTrace();
						}
							
					
						
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
	
	private void downLoad() throws JSONException {
		// TODO Auto-generated method stub
		  String result = sp.getString("salelist", "nodata");
		  if (!"nodata".equals(result)) {
				JSONObject jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 if (num_code==1) {
					 //保存到本地
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
						 mDataList_origin.add(data);
                          data.toString();						 
					}
					  mDataList.clear();
					  mDataList.addAll(mDataList_origin);
					  initListView();
				}
				 else {
					 findViewById(R.id.progressBar_sale).setVisibility(View.GONE);
					new AlertInfoDialog(SaleActivity.this).show();
				}
		}
		new DownLoadAsyTask().execute(Content.URL_PROPERTYLIST+"2");
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
					 //保存到本地
					 sp.edit().putString("salelist", result).commit();
					 mDataList_origin.clear();
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
						 mDataList_origin.add(data);
                          data.toString();						 
					}
					  mDataList.clear();
					  mDataList.addAll(mDataList_origin);
						if (mDataList_origin.size()==0) {
							findViewById(R.id.tv_noresut).setVisibility(View.VISIBLE);
						}
						else {
							findViewById(R.id.tv_noresut).setVisibility(View.GONE);
						}

					  initListView();
				}
				 else {
					 findViewById(R.id.progressBar_sale).setVisibility(View.GONE);
					new AlertInfoDialog(SaleActivity.this).show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(SaleActivity.this).show();
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
	private void initData() {
		// TODO Auto-generated method stub

		mStringList_zones = new String[]{
				"香港","九龍","新界","離島"
		};
	}
	private void initListView() {
	   findViewById(R.id.progressBar_sale).setVisibility(View.GONE);
       mListView = (ListView) findViewById(R.id.listView_sale);
       mListView.setVisibility(View.VISIBLE);
       myadapter = new Myadapter();
	   mListView.setAdapter(myadapter);
       mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				//setid
				Bean.setRentID(mDataList.get(position).ID);
				Log.e("initListView", mDataList.get(position).ID);
				
                Intent intent = new Intent(SaleActivity.this,RentDetailActivity.class);
                intent.putExtra("name", mDataList.get(position).Name);
				startActivity(intent);	
			}
		});
	}
	private void initUi() {
		  int statusBarHeight = UIHelper.getStatusBarHeight(this);
		  int height = UIHelper.dip2px(this, 41);
		  pptoHeight = statusBarHeight+height+64;
		mButton_select1 = (Button) findViewById(R.id.button_select1);
		mButton_select2 = (Button) findViewById(R.id.button_select2);
		mButton_select3 = (Button) findViewById(R.id.button_select3);
		mButton_select4 = (Button) findViewById(R.id.button_select4);
		mRela_Select = findViewById(R.id.rela_select);
		mPpWindow = new Rent_ZonePPWindow(this,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
				);
		mPpWindow_area =new Rent_AreaPpwindow(this,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
				);
		mPpWindow_price = new Sale_PricePpwindow(this,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
				);
		//0 ，1 ，2 高低
		mPpWindow_sort= new Rent_SortPpwindow(this,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
				);
		//面积
		mpMorePpwindow= new Rent_MorePpwindow(this,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
				);
		mPpWindow_sort.setOnsortListener(this);
		mPpWindow_area.setonAreamainClickListener(this);
		mPpWindow_price.setOnPriceClickListener_sale(this);
		mpMorePpwindow.setAreaClickListener(this);
		 mLayout = findViewById(R.id.button_select1);
		 mLayout2 = findViewById(R.id.button_select2);
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
			           if ("0".endsWith(mDataList.get(position).SellingPrice)) {
			        	   mTv_Saleprice.setText("售: -");
					}
			           else {
				           mTv_Saleprice.setText("售:"+mDataList.get(position).SellingPrice+"萬");
					}
			        /*   if ("0".endsWith(mDataList.get(position).RentPrice)) {
			        	   mTv_Rentprice.setText("租: -");
					}
			           else {
						
			        	   mTv_Rentprice.setText("租:"+mDataList.get(position).RentPrice);
					}*/
			           mTv_Rentprice.setVisibility(View.GONE);
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
	class  Myadapter_Select extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			           View layout = getLayoutInflater().inflate(R.layout.item_listview_select, null);
			           TextView mTv_Select= (TextView) layout.findViewById(R.id.tv_listview_select);
			           mTv_Select.setText(mStringList_zones[position]);
			return layout;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mStringList_zones.length;
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
	public  void btn_select1(View v) {
   //	mPpWindow.showAtLocation(mLayout, Gravity.TOP, 0, 100);
		mPpWindow_area.showAtLocation(mLayout2, Gravity.TOP, 0, 100);
	}
	private void listview_Select_click() {
		// TODO Auto-generated method stub
		mListView_Select.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
			}
		});
		
	}
	public  void btn_select2(View v) {
		mPpWindow_price.showAtLocation(mLayout2, Gravity.TOP, 0, 100);

	}  
	public  void btn_select3(View v) {
		mpMorePpwindow.showAtLocation(mLayout2, Gravity.TOP, 0, 100);
   
	}
	public  void btn_select4(View v) {
		mPpWindow_sort.showAtLocation(mLayout2, Gravity.TOP, 0, 100);
	}
	// 0 , 1  , 2  高低
	@Override
	public void setOnsortListener(int sorttyle) {
		if (sorttyle==0) {
			mDataList.clear();
			mDataList.addAll(mDataList_origin);
			if (mDataList_origin.size()==0) {
				findViewById(R.id.tv_noresut).setVisibility(View.VISIBLE);
			}
			else {
				findViewById(R.id.tv_noresut).setVisibility(View.GONE);
			}
		  myadapter.notifyDataSetChanged();
		}
		else {
			
			List<Data>  mSortDatalist=new ArrayList<SaleActivity.Data>();
			mSortDatalist.clear();
			Vector<Integer> v=new Vector<Integer>();
			for (int i = 0; i < mDataList_origin.size(); i++) {
				Data data = mDataList_origin.get(i);
				String rentPrice = data.SellingPrice;
				if (rentPrice!=null) {
					int price = Integer.valueOf(rentPrice);
					v.add(price);
				}
			}
			Comparator<Integer> cm;
			if (sorttyle==1) {
				cm = new MyComparator_up();
				Collections.sort(v,cm );
			}
			else if (sorttyle==2) {
				cm = new MyComparator_down();
				Collections.sort(v,cm );
			}
			for (Integer integer : v) {
				for (Data data : mDataList_origin) {
					if (Integer.valueOf(data.SellingPrice).equals(integer)) {
						mSortDatalist.add(data);
					}
				}
			}
			mDataList.clear();
			mDataList.addAll(mSortDatalist);
			myadapter.notifyDataSetChanged();
		}
	}
	class MyComparator_up implements Comparator<Integer>{
		public int compare(Integer i1, Integer i2) {
			if (i1 <i2) return -1;
			else if (i1 == i2) return 0;
			else return 1;
		}
	}
	class MyComparator_down implements Comparator<Integer>{
		public int compare(Integer i1, Integer i2) {
			if (i1 > i2) return -1;
			else if (i1 == i2) return 0;
			else return 1;
		}
	}
	
	@Override
	public void onarealistener(int style) {
		if (style==0) {
			mDataList.clear();
			mDataList.addAll(mDataList_origin);
			if (mDataList_origin.size()==0) {
				findViewById(R.id.tv_noresut).setVisibility(View.VISIBLE);
			}
			else {
				findViewById(R.id.tv_noresut).setVisibility(View.GONE);
			}
			myadapter.notifyDataSetChanged();
		}
		else {
			List<Data>  mSortDatalist=new ArrayList<SaleActivity.Data>();
			mSortDatalist.clear();
			for (int i = 0; i < mDataList_origin.size(); i++) {
				Data data = mDataList_origin.get(i);
				String rentPrice = data.AreaNet;
				if (rentPrice!=null) {
					int price = Integer.valueOf(rentPrice);
					switch (style) {
					case  1:
						if (price<100) {
							mSortDatalist.add(data);
						}
						break;
					case 2:
						if (price>=100&&price<300) {
							mSortDatalist.add(data);
						}
						break;
					case 3:
						if (price>=300&&price<600) {
							mSortDatalist.add(data);
						}
						break;
					case 4:
						if (price>=600&&price<900) {
							mSortDatalist.add(data);
						}
						break;
					case 5:
						if (price>=900&&price<1500) {
							mSortDatalist.add(data);
						}
						break;
					case 6:
						if (price>=1500&&price<2000) {
							mSortDatalist.add(data);
						}
						break;
					case 7:
						if (price>2000) {
							mSortDatalist.add(data);
						}
						break;
						
					default:
						break;
					}
					
				}
			}
			if (mSortDatalist.size()==0) {
				findViewById(R.id.tv_noresut).setVisibility(View.VISIBLE);
			}
			else {
				findViewById(R.id.tv_noresut).setVisibility(View.GONE);
			}
			mDataList.clear();
			mDataList.addAll(mSortDatalist);
			myadapter.notifyDataSetChanged();
		}
		}
	@Override
	public void onpricelistener_sale(int style) {
		if (style==0) {
			mDataList.clear();
			mDataList.addAll(mDataList_origin);
			if (mDataList_origin.size()==0) {
				findViewById(R.id.tv_noresut).setVisibility(View.VISIBLE);
			}
			else {
				findViewById(R.id.tv_noresut).setVisibility(View.GONE);
			}
			myadapter.notifyDataSetChanged();
		}
		else {
			List<Data>  mSortDatalist=new ArrayList<SaleActivity.Data>();
			mSortDatalist.clear();
			for (int i = 0; i < mDataList_origin.size(); i++) {
				Data data = mDataList_origin.get(i);
				String rentPrice = data.SellingPrice;
				if (rentPrice!=null) {
					int price = Integer.valueOf(rentPrice);
					switch (style) {
					case  1:
						if (price<300) {
							mSortDatalist.add(data);
						}
						break;
					case 2:
						if (price>=300&&price<500) {
							mSortDatalist.add(data);
						}
						break;
					case 3:
						if (price>=500&&price<1000) {
							mSortDatalist.add(data);
						}
						break;
					case 4:
						if (price>=1000&&price<3000) {
							mSortDatalist.add(data);
						}
						break;
					case 5:
						if (price>=3000&&price<5000) {
							mSortDatalist.add(data);
						}
						break;
					case 6:
						if (price>5000) {
							mSortDatalist.add(data);
						}
						break;
						
					default:
						break;
					}
					
				}
			}
			if (mSortDatalist.size()==0) {
				findViewById(R.id.tv_noresut).setVisibility(View.VISIBLE);
			}
			else {
				findViewById(R.id.tv_noresut).setVisibility(View.GONE);
			}
			mDataList.clear();
			mDataList.addAll(mSortDatalist);
			myadapter.notifyDataSetChanged();
		}
		}
	@Override
	public void onareamainlistener(int style) {
		   findViewById(R.id.progressBar_sale).setVisibility(View.VISIBLE);
		downloadsearch(style+"");
	}
	
}
