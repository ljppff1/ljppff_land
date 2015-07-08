package com.compass.hk.yishou;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.HotActivity;
import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.compass.OwnerActivity;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_Calling;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.dialog.Dialog_Calling.mOnClickListener;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.frame.Frame_agence;
import com.compass.hk.more.MoreActivity;
import com.compass.hk.nearby.NearByActivity;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
/**
 * 一手楼盘
 * @author liujun
 *
 */
public class YiShouActivity extends TabActivity {
	private TabHost mTabHost;
	private String mPicUrl;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private String mPicName;
	private View mLayout;
	private String mString_Call2;
	private String mString_Call1;
	private ArrayList<Data>  mDataList=new ArrayList<YiShouActivity.Data>();
	private TextView mTv_CompanyName ,mTv_Licence,mTv_PersonName,mTv_Telephone1,mTv_Telephone2;
	private ImageView mImageView;
	private RelativeLayout rela_left;
	private RelativeLayout rela_right;
	private int POSITION;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yi_shou);
		
		initUi();
		initTabHost();
		downLoad();
        ImageView mImageView= (ImageView) findViewById(R.id.ib_agence_call);
        mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Dialog_Calling dialog_Calling = new Dialog_Calling(YiShouActivity.this, "撥打以下哪個電話？");
				dialog_Calling.setOnclickListener_on(new mOnClickListener() {
					@Override
					public void onCall2_Clicklistener() {
						// TODO Auto-generated method stub
						 if (mString_Call2==null||"null".equals(mString_Call2)) {
						}
						 else {
							 Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mString_Call2));
							  startActivity(intent);
						}
					}
					@Override
					public void onCall1_Clicklistener() {
						// TODO Auto-generated method stub
						if (mString_Call1==null||"null".equals(mString_Call1)) {
						}
						else {
							Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mString_Call1));
							  startActivity(intent);
						}
					}
				});
				dialog_Calling.show();
			}
		});
	}
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
	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_Agence);
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
					  initIndex(POSITION);
					/*  Log.e("agence", mDataList.toString());
					  mTv_CompanyName.setText(mDataList.get(0).CompanyName.toString());
					  mTv_PersonName.setText(mDataList.get(0).ContactName.toString());
					  String replace = mDataList.get(0).ContactPhone.toString();
					  mString_Call1= replace.replace("&nbsp;", "");
					  Log.e("agence33", mString_Call1.replace("&nbsp;", " "));
					  if (mString_Call1!=null||"null".equals(mString_Call1)) {
						
						  mTv_Telephone1.setText(replace.replace("&nbsp;", " "));
					}
					  String replace2 = mDataList.get(0).ContactTel.toString();
					  mString_Call2= replace2.replace("&nbsp;", "");
					  if (mString_Call2!=null||"null".equals(mString_Call2)) {
							
						  mTv_Telephone1.setText(replace2.replace("&nbsp;", " "));
					}
					  mTv_Telephone2.setText(mString_Call2);
					  mTv_Licence.setText(mDataList.get(0).PersonLicense.toString());
					  //UIL
 			           initImageLoaderOptions();
 						imageLoader.displayImage( mDataList.get(0).PersonPhoto.toString(),
 								mImageView, options);*/
					  
				}
				 else {
					new AlertInfoDialog(YiShouActivity.this).show();
				}
			} catch (JSONException e) {
				if(mDataList==null)
				new Dialog_noInternet(YiShouActivity.this).show();
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
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
		View  view1=getLayoutInflater().inflate(R.layout.item_yishou_tab1, null);
		tabSpec1.setIndicator(view1);
		Intent intent1 = new Intent(this,Activity_YiShou1.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec1.setContent(intent1);
		mTabHost.addTab(tabSpec1);
		
		TabSpec tabSpec2=mTabHost.newTabSpec("附近");
		 View  view2=getLayoutInflater().inflate(R.layout.item_yishou_tab2, null);
		tabSpec2.setIndicator(view2);
		Intent intent2=new Intent(this,Activity_YiShou2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec2.setContent(intent2);
		mTabHost.addTab(tabSpec2);
		
		TabSpec tabSpec3=mTabHost.newTabSpec("我的指南針");
		 View  view3=getLayoutInflater().inflate(R.layout.item_yishou_tab3, null);
		tabSpec3.setIndicator(view3);
		Intent intent3 = new Intent(this,Activity_YiShou3.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec3.setContent(intent3);
		mTabHost.addTab(tabSpec3);
		
		TabSpec tabSpec4=mTabHost.newTabSpec("更多");
		 View  view4=getLayoutInflater().inflate(R.layout.item_yishou_tab4, null);
		tabSpec4.setIndicator(view4);
		Intent intent4 = new Intent(this,Activity_YiShou4.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		tabSpec4.setContent(intent4);
		mTabHost.addTab(tabSpec4);
		
	
		// TODO Auto-generated method stub
		
	}


}
