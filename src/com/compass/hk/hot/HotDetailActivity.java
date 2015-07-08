package com.compass.hk.hot;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.badfile.Frame_danger1;
import com.compass.hk.badfile.Frame_danger2;
import com.compass.hk.badfile.Frame_danger3;
import com.compass.hk.badfile.Frame_danger4;
import com.compass.hk.badfile.Frame_danger5;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.rent.MainFragment_Rent;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;
import com.compass.hk.util.getJson;
import com.compass.hk.yishou.Activity_YiShou1;
import com.compass.hk.yishou.Activity_YiShou2;
import com.compass.hk.yishou.Activity_YiShou3;
import com.compass.hk.yishou.Activity_YiShou4;
import com.compass.hk.yishou.YiShouActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
/**
 * ljppff街景地图
 * @author liujun
 *
 */
public class HotDetailActivity extends TabActivity {

	private String mString_Call2;
	private String mString_Call1;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private TabHost mTabHost;
	private int POSITION;
	private ArrayList<Data>  mDataList=new ArrayList<HotDetailActivity.Data>();
	private TextView mTv_CompanyName ,mTv_Licence,mTv_PersonName,mTv_Telephone1,mTv_Telephone2;
	private ImageView mImageView;
	private RelativeLayout rela_left;
	private RelativeLayout rela_right;
	DisplayImageOptions options;
	private String ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hot_detail);
		initUi();
		ID =getIntent().getStringExtra("ID");

		UILApplication application = (UILApplication) getApplication();
		String hotDetail_Name = application.getHotDetail_Name();
		TextView textView_Title= (TextView) findViewById(R.id.tv_hotdetail_title);
		textView_Title.setText(hotDetail_Name);
		initTabHost();
		downLoad();
	}
	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_Agence4+"&&PropertyID="+ID);
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
						 data.CompanyName= jsonObject2.getString("CompanyName");
						 data.ContactName= jsonObject2.getString("ContactName");
						 data.ContactTel = jsonObject2.getString("ContactTel");
						 data.ContactPhone = jsonObject2.getString("ContactPhone");
						 data.PersonLicense=jsonObject2.getString("PersonLicense");
						 data.PersonPhoto=jsonObject2.getString("PersonPhoto");
						 mDataList.add(data);
                          data.toString();						 
					}
					  POSITION =0;
					  hot_detail_bottom1.setVisibility(View.VISIBLE);
					  hot_detail_bottom2.setVisibility(View.GONE);
					  hot_detail_bottom3.setVisibility(View.GONE);
					  initIndex(POSITION);
					  
				}
				 else {
					//new AlertInfoDialog(HotDetailActivity.this).show();
					  hot_detail_bottom1.setVisibility(View.GONE);
					  hot_detail_bottom2.setVisibility(View.VISIBLE);
					  hot_detail_bottom3.setVisibility(View.GONE);

				}
			} catch (JSONException e) {
				new Dialog_noInternet(HotDetailActivity.this).show();
				e.printStackTrace();
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		  switch (v.getId()) {
			case R.id.rela_left:
				if(POSITION>0){
					POSITION =POSITION-1;
				}else{
					POSITION =mDataList.size()-1;
				}
				initIndex(POSITION);
				break;
			case R.id.rela_right:
				if(POSITION<(mDataList.size()-1)){
					POSITION =POSITION+1;
				}else{
					POSITION =0;
				}
				initIndex(POSITION);
				break;

		default:
			break;
		}
		}
	};
	private ProgressBar hot_detail_bottom3;
	private RelativeLayout hot_detail_bottom1;
	private RelativeLayout hot_detail_bottom2;

	private void initUi() {
		
		  mTv_CompanyName = (TextView) findViewById(R.id.tv_frag_companyname);
		  mTv_Licence= (TextView) findViewById(R.id.tv_frag_lience);
		  mTv_PersonName= (TextView) findViewById(R.id.tv_frag_personname);
		  mTv_Telephone1= (TextView) findViewById(R.id.tv_frag_telephone1);
		  mTv_Telephone2= (TextView) findViewById(R.id.tv_frag_telephone2);
		  mImageView= (ImageView) findViewById(R.id.iv_frag_personphoto);
		
		  rela_left =(RelativeLayout)findViewById(R.id.rela_left);
		  rela_right =(RelativeLayout)findViewById(R.id.rela_right);
		  rela_left.setOnClickListener(listener);
		  rela_right.setOnClickListener(listener);
		  hot_detail_bottom3 =(ProgressBar)findViewById(R.id.hot_detail_bottom3);
		  hot_detail_bottom1 =(RelativeLayout)findViewById(R.id.hot_detail_bottom1);
		  hot_detail_bottom2 =(RelativeLayout)findViewById(R.id.hot_detail_bottom2);
		  
	}

	public void initIndex(int position){
		  mTv_CompanyName.setText(mDataList.get(position).CompanyName.toString());
		  mTv_PersonName.setText(mDataList.get(position).ContactName.toString());
		  String replace = mDataList.get(position).ContactPhone.toString();
		  mString_Call1= replace.replace("&nbsp;", "");
		  Log.e("agence33", mString_Call1.replace("&nbsp;", " "));
		  if (mString_Call1!=null||"null".equals(mString_Call1)) {
			
			  mTv_Telephone1.setText(replace.replace("&nbsp;", " "));
		}
		  String replace2 = mDataList.get(position).ContactTel.toString();
		  mString_Call2= replace2.replace("&nbsp;", "");
		  if (mString_Call2!=null||"null".equals(mString_Call2)) {
				
			  mTv_Telephone1.setText(replace2.replace("&nbsp;", " "));
		}
		  mTv_Telephone2.setText(mString_Call2);
		  mTv_Licence.setText(mDataList.get(position).PersonLicense.toString());
		  //UIL
        initImageLoaderOptions();
			imageLoader.displayImage( mDataList.get(position).PersonPhoto.toString(),
					mImageView, options);

	}

	class Data{
		String CompanyName;
		String PersonPhoto;
		String PersonLicense;
		String ContactName;
		String ContactPhone;
		String ContactTel;
		@Override
		public String toString() {
			return "Data [CompanyName=" + CompanyName + ", PersonPhoto="
					+ PersonPhoto + ", PersonLicense=" + PersonLicense
					+ ", ContactName=" + ContactName + ", ContactPhone="
					+ ContactPhone + ", ContactTel=" + ContactTel + "]";
		}
		
		
	}
	//UIL
	private void initImageLoaderOptions() {
		options = new DisplayImageOptions.Builder()
		         .showStubImage(R.drawable.ic_empty)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.cacheInMemory()
				.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	public  void btn_back(View v) {
		// TODO Auto-generated method stub
       finish();
	}
	public  void btn_home(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,MainActivity.class));
		finish();
	}

	private void initTabHost() {

		mTabHost = getTabHost();
		TabSpec tabSpec1=mTabHost.newTabSpec("熱門");
		View  view1=getLayoutInflater().inflate(R.layout.item_hotdetali_tab1, null);
		tabSpec1.setIndicator(view1);
		Intent intent1 = new Intent(this,HotDetail1Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec1.setContent(intent1);
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2=mTabHost.newTabSpec("附近");
		View  view2=getLayoutInflater().inflate(R.layout.item_hotdetali_tab2, null);
		tabSpec2.setIndicator(view2);
		Intent intent2=new Intent(this,HotDetail2Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec2.setContent(intent2);
		mTabHost.addTab(tabSpec2);
		
		TabSpec tabSpec3=mTabHost.newTabSpec("我的指南針");
		 View  view3=getLayoutInflater().inflate(R.layout.item_hotdetali_tab3, null);
		tabSpec3.setIndicator(view3);
		Intent intent3 = new Intent(this,HotDetail3Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec3.setContent(intent3);
		mTabHost.addTab(tabSpec3);
		
		TabSpec tabSpec4=mTabHost.newTabSpec("更多");
		 View  view4=getLayoutInflater().inflate(R.layout.item_hotdetali_tab4, null);
		tabSpec4.setIndicator(view4);
		Intent intent4 = new Intent(this,HotDetail4Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec4.setContent(intent4);
		mTabHost.addTab(tabSpec4);
		
	
		// TODO Auto-generated method stub
		
	}
}
