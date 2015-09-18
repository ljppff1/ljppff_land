package com.compass.hk;


import com.webdesign688.compass.R;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.badfile.XiongZhaiActivity;
import com.compass.hk.calculator.CalculatorActivity;
import com.compass.hk.compass.OwnerActivity;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.CustomerAlertDialog.mOnClickListener;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_viewpage_home;
import com.compass.hk.frame.MyFrame;
import com.compass.hk.hot.HotDetailActivity;
import com.compass.hk.login_register.Login_Regiester;
import com.compass.hk.popowindow.HomeSearchBarPopupWindow;
import com.compass.hk.popowindow.HomeSearchBarPopupWindow.onSearchBarItemClickListener;
import com.compass.hk.rent.RentActivity;
import com.compass.hk.rent.RentDetailActivity;
import com.compass.hk.search.SearchActivity;
import com.compass.hk.search.SearchResult1Activity;
import com.compass.hk.search.SearchResultActivity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.compass.hk.widgets.ChildListView;
import com.compass.hk.xinwen.NewContentActivity;
import com.compass.hk.xinwen.XinWenActivity;
import com.compass.hk.yishou.YiShouActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends FragmentActivity implements onSearchBarItemClickListener, mOnClickListener {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private ChildListView mListView;
	private ViewPager viewPager;
	private ViewPager mViewPager;
	private boolean hasPressedBack;
	private Handler  mHandler=new Handler();
	public ArrayList<Data> mDataList=new ArrayList<MainActivity.Data>();
	private HomeSearchBarPopupWindow mBarPopupWindow;
	private View mLayout;
	private RadioButton mRadioButton1;
	private RadioButton mRadioButton2;
    private List<String> list =new ArrayList<String>();
    private List<String> list1 =new ArrayList<String>();
    private int preSelImgIndex = 0;
	private LinearLayout ll_focus_indicator_container;
	private RelativeLayout mRltitle;
	private FrameLayout mFLtitle;

	@Override   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new DefaultLogin(this);
		initUI();
		
	//initViewPage();
		downloadnavigation();
	
		downLoad();
		
	}

	private void downloadnavigation() {
		new DownLoadAsyTask1().execute("http://www.hk-compass.com/json/photolist.php?t=5");
	}
	class DownLoadAsyTask1 extends AsyncTask<String, Void, String>{  
		private com.compass.hk.widgets.MyGallery gallery;
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
							JSONObject jsonObject2 = array.getJSONObject(i);
							String CoverPic=jsonObject2.getString("PhotoPath");
                            String PhotoLink =jsonObject2.getString("PhotoLink");
					   	    list.add(CoverPic);	 
						    list1.add(PhotoLink);
				              	}
						mRltitle.setVisibility(View.GONE);
						mFLtitle .setVisibility(View.VISIBLE);

						ll_focus_indicator_container = (LinearLayout) findViewById(R.id.ll_focus_indicator_container);
						InitFocusIndicatorContainer();
						gallery = (com.compass.hk.widgets.MyGallery) findViewById(R.id.banner_gallery);
						gallery.setAdapter(new ImageAdapter(getApplicationContext()));
						gallery.setFocusable(true);
						gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

						    public void onItemSelected(AdapterView<?> arg0, View arg1,
							    int selIndex, long arg3) {
							//修改上一次选中项的背景
						    	selIndex = selIndex % list.size();
						    	
							ImageView preSelImg = (ImageView) ll_focus_indicator_container.findViewById(preSelImgIndex);
						preSelImg.setImageDrawable(MainActivity.this
							.getResources().getDrawable(R.drawable.ic_focus));
							//修改当前选中项的背景
							ImageView curSelImg = (ImageView) ll_focus_indicator_container
								.findViewById(selIndex);
							curSelImg
								.setImageDrawable(MainActivity.this
									.getResources().getDrawable(
										R.drawable.ic_focus_select));
							preSelImgIndex = selIndex;
						    }

						    public void onNothingSelected(AdapterView<?> arg0) {
						    }
						});
                       gallery.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent it = new Intent( Intent.ACTION_VIEW );
							it.setData( Uri.parse(list1.get(position)) ); //这里面是需要调转的rul
							it = Intent.createChooser( it, null );
							startActivity( it );							
						}
					});
				}
				 else {}
			} catch (JSONException e) {
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	 public class ImageAdapter extends BaseAdapter {
			private Context _context;

			public ImageAdapter(Context context) {
			    _context = context;
			}

			public int getCount() {
			    return list.size();
			}

			public Object getItem(int position) {

			    return position;
			}

			public long getItemId(int position) {
			    return position;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder viewHolder = null;
				if(convertView == null)
				{
					viewHolder = new ViewHolder();
					ImageView imageView = new ImageView(_context);
				    imageView.setAdjustViewBounds(true);
				    imageView.setScaleType(ScaleType.FIT_XY);
				    imageView.setLayoutParams(new Gallery.LayoutParams(
					    LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				    convertView = imageView;
				    viewHolder.imageView = (ImageView)convertView; 
				    convertView.setTag(viewHolder);
					
				}
				else
				{
					viewHolder = (ViewHolder)convertView.getTag();
				}
			  //  viewHolder.imageView.setImageDrawable(imgList.get(position%imgList.size()));
		        initImageLoaderOptions();
					imageLoader.displayImage(list.get(position),
							viewHolder.imageView, options);

			    return convertView;
			}
			
		    }
		    
		    private static class ViewHolder
			{
				ImageView imageView;
			}
    private void InitFocusIndicatorContainer() {
	for (int i = 0; i < list.size(); i++) {
	    ImageView localImageView = new ImageView(this);
	    localImageView.setId(i);
	    ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
	    localImageView.setScaleType(localScaleType);
	    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
		    24, 24);
	    localImageView.setLayoutParams(localLayoutParams);
	    localImageView.setPadding(5, 5, 5, 5);
	    localImageView.setImageResource(R.drawable.ic_focus);
	    this.ll_focus_indicator_container.addView(localImageView);
	}
    }

	private void initUI() {
		mLayout = findViewById(R.id.relativeLayout_main);
		mRltitle =(RelativeLayout)findViewById(R.id.mRltitle);
		mFLtitle =(FrameLayout)findViewById(R.id.mFLtitle);
	//	mRadioButton1 = (RadioButton) findViewById(R.id.radio0_maintop);
	//	mRadioButton2 = (RadioButton) findViewById(R.id.radio1_maintop);

		mBarPopupWindow = new HomeSearchBarPopupWindow(this,
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mBarPopupWindow.setOnSearchBarItemClickListener(this);
	}

	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_PROPERTYLIST+"1");
	}
	//UIL
		private void initImageLoaderOptions() {
			options = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.drawable.ic_empty)
					.cacheInMemory()
					.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
					.bitmapConfig(Bitmap.Config.RGB_565).build();
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
					 findViewById(R.id.progressBar_main).setVisibility(View.GONE);
					new AlertInfoDialog(MainActivity.this).show();
				}
			} catch (JSONException e) {
				 findViewById(R.id.progressBar_main).setVisibility(View.GONE);
				 if(mDataList.isEmpty())
				new Dialog_noInternet(MainActivity.this).show();
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
		// TODO Auto-generated method stub
		mListView = (ChildListView) findViewById(R.id.listView_main);
		findViewById(R.id.progressBar_main).setVisibility(View.GONE);
		mListView.setVisibility(View.VISIBLE);
		initAdapter();
		ScrollView mScrollView= (ScrollView) findViewById(R.id.scrollview_main);
		mScrollView.smoothScrollTo(0, 0);
	}

	private void initViewPage() {
		mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		@Override
		public Fragment getItem(int arg0) {
		Frame_viewpage_home  frame=new Frame_viewpage_home();
		frame.setPosition(arg0,list);
			return frame ;
		}
	});
        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if (arg0==0) {
					mRadioButton1.setChecked(true);
				}
				else {
					mRadioButton2.setChecked(true);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
}

	private void initAdapter() {
         mListView.setAdapter(new Myadapter());
         mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//setid
				Bean.setRentID(mDataList.get(position).ID);
				Log.e("initListView", mDataList.get(position).ID);
				
                Intent intent = new Intent(MainActivity.this,RentDetailActivity.class);
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
	           if ("0".endsWith(mDataList.get(position).SellingPrice)) {
	        	   mTv_Saleprice.setText("售: -");
			}
	           else {
		           mTv_Saleprice.setText("售:"+mDataList.get(position).SellingPrice+"萬");
			}

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
			return 2;
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
	
	public void btn_tab1(View v) {
		Intent intent=new Intent(this,Tab1Activity.class);
		intent.putExtra("tab_id", 0);
		startActivity(intent);
	}
	public void btn_tab2(View v) {
		Intent intent=new Intent(this,Tab1Activity.class);
		intent.putExtra("tab_id", 1);
		startActivity(intent);
	}
	public void btn_tab3(View v) {
		Intent intent=new Intent(this,Tab1Activity.class);
		intent.putExtra("tab_id", 2);
		startActivity(intent);
	}
	public void btn_tab4(View v) {
		Intent intent=new Intent(this,Tab1Activity.class);
		intent.putExtra("tab_id", 3);
		startActivity(intent);
	}
	public  void btn_popowindow(View v) {
		// TODO Auto-generated method stub
		mBarPopupWindow.showAtLocation(mLayout, Gravity.TOP, 0, 100);
	}
	public  void btn_yishou(View v) {
		
                 startActivity(new Intent(this,YiShouActivity.class));
	}
	public  void btn_sale(View v) {
		
        startActivity(new Intent(this,SaleActivity.class));
}
public  void btn_rent(View v) {
		
        startActivity(new Intent(this,RentActivity.class));
}
	public void btn_fangpan(View v) {
		
		if (Bean.isLogined()) {
			
			startActivity(new Intent(MainActivity.this,FangPanActivity.class));
		}
		else {
			CustomerAlertDialog customerAlertDialog = new CustomerAlertDialog(MainActivity.this, "請先登錄");
			customerAlertDialog.show();
			customerAlertDialog.setOnclickListener_on(this);
		}
	}
	//物业搜索
	public  void btn_search(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,SearchActivity.class));
	}
	public  void btn_own(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,XinWenActivity.class));
	}
	public  void btn_calculator(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,CalculatorActivity.class));
	}
	public  void btn_xiongzhai(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,XiongZhaiActivity.class));
	}
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:  
			if (!hasPressedBack)
            {
                // 第一次按
                hasPressedBack = true;
                Toast.makeText(MainActivity.this, "再按一次back鍵退出", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {   
                        hasPressedBack = false;
                    }
                }, 3000);  
                return true;
            }
			break;
		default:
			break;
		}
    	return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onSearchButtonClick(String key,String  style) {
		// TODO Auto-generated method stub
		summit(key,style);
	}

	private void summit(String key, String style) {
		Intent intent =new Intent(getApplicationContext(), SearchResult1Activity.class); 
	   intent.putExtra("KEY", key);
	   intent.putExtra("STYLE", style);
		startActivity(intent);
/*		 RequestParams params = new RequestParams();
         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
         nameValuePairs.add(new BasicNameValuePair("SearchType", style));
        	 nameValuePairs.add(new BasicNameValuePair("KeyWord", key));
        	 
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
							  String data = result.getString("data");
							  
							  Log.e("code:", code);
							  Log.e("msg:", msg);
							  Log.e("data:", data);
							  
							  if ("1".equals(code)) {
								  Intent intent=new Intent(MainActivity.this,SearchResultActivity.class);
								  intent.putExtra("data", data);
								startActivity(intent);
							}
							  else {
								new CustomerAlertDialog(MainActivity.this, "無相關搜索結果").show();
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                     }
                     @Override
                     public void onFailure(HttpException error, String msg) {
                     //	Toast.makeText(ForgetPassword.this, "未發送成功，", Toast.LENGTH_SHORT).show();  
                     }
                 });
*/	}

	@Override
	public void onBtnClicklistener() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,Login_Regiester.class));
	}
	
}