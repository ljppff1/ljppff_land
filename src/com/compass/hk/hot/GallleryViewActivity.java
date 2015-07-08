package com.compass.hk.hot;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.MyFrame;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;
import com.compass.hk.util.getJson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GallleryViewActivity extends FragmentActivity {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	
	private View layout;
	private GridView mGridView;
	ArrayList<Data>  mDataList=new ArrayList<GallleryViewActivity.Data>();
	private  ArrayList<Integer>  mRadioList=new ArrayList<Integer>();
	private String mString_ID;
	private String mString_Gallery_ID;
	private ViewPager mViewPager;
	private TextView mTv_Name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_galllery_view);
		mTv_Name = (TextView) findViewById(R.id.tv_gallery_view_name);
		getID();
		initRadio();
		 //download
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
				private void getID() {
					// TODO Auto-generated method stub
					UILApplication application = (UILApplication)getApplication();
					mString_ID = application.getHotDetail_ID();
					Intent intent = getIntent();
					mString_Gallery_ID = intent.getStringExtra("ID");
					
					Log.e("mString_Gallery_ID", mString_Gallery_ID);
				}
		private void downLoad() {
			// TODO Auto-generated method stub
			new DownLoadAsyTask().execute(Content.URL_FIRST_HAND_DETAIL_GALLERYVIEW+mString_ID+"&GalleryID="+mString_Gallery_ID);
		}
		private void initRadio() {
			// TODO Auto-generated method stub
			 for (int i = 0; i <20; i++) {
				mRadioList.add(R.id.gallery_radio_1);
				mRadioList.add(R.id.gallery_radio_2);
				mRadioList.add(R.id.gallery_radio_3);
				mRadioList.add(R.id.gallery_radio_4);
				mRadioList.add(R.id.gallery_radio_5);
				mRadioList.add(R.id.gallery_radio_6);
				mRadioList.add(R.id.gallery_radio_7);
				mRadioList.add(R.id.gallery_radio_8);
				mRadioList.add(R.id.gallery_radio_9);
				mRadioList.add(R.id.gallery_radio_10);
				mRadioList.add(R.id.gallery_radio_11);
				mRadioList.add(R.id.gallery_radio_12);
				mRadioList.add(R.id.gallery_radio_13);
				mRadioList.add(R.id.gallery_radio_14);
				mRadioList.add(R.id.gallery_radio_15);
				mRadioList.add(R.id.gallery_radio_16);
				mRadioList.add(R.id.gallery_radio_17);
				mRadioList.add(R.id.gallery_radio_18);
				mRadioList.add(R.id.gallery_radio_19);
				mRadioList.add(R.id.gallery_radio_20);
			}
		}
		private void initViewPage() {
			mViewPager = (ViewPager) findViewById(R.id.pager1);
	        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mDataList.size();
			}
			@Override
			public Fragment getItem(int arg0) {
				Log.e("getItem", ""+arg0);
		Frame_hot_gallery_pic  frame=new Frame_hot_gallery_pic();
			frame.setUrl(mDataList.get(arg0).PhotoPic);
			frame.setPicName(mDataList.get(arg0).PhotoName);
				return frame ;
			}
		});
	        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
				
				@Override
				public void onPageSelected(int position) {
					mTv_Name.setText(mDataList.get(position).PhotoName);
					Log.e("mViewPager", ""+position);
					int   id = mRadioList.get(position);
					RadioButton radioButton = (RadioButton) findViewById(id);
					radioButton.setChecked(true);
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
							 data.PhotoPic= jsonObject2.getString("PhotoUrl");
							 data.PhotoName= jsonObject2.getString("PhotoName");
							 mDataList.add(data);
						}
						  if (mDataList.size()>0) {
							  mTv_Name.setText(mDataList.get(0).PhotoName);
						}
						  for (int j = 0; j < mDataList.size(); j++) {
								 int   id = mRadioList.get(j);
									RadioButton radioButton = (RadioButton) findViewById(id);
									radioButton.setVisibility(View.VISIBLE);
							}
						  initViewPage();
					}
					 else {
						new AlertInfoDialog(GallleryViewActivity.this).show();
					}
				} catch (JSONException e) {
					 if(mDataList.isEmpty())
					new Dialog_noInternet(GallleryViewActivity.this).show();
				}
			}
				@Override
				protected String doInBackground(String... params) {
					String str=params[0];
					return getJson.getData(str);
				}
				}
		class Data{
			String   PhotoName;
			String   PhotoPic;
			@Override
			public String toString() {
				return "Data [PhotoName=" + PhotoName + ", PhotoPic="
						+ PhotoPic + "]";
			}
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

}
