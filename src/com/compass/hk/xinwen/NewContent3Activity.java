package com.compass.hk.xinwen;

import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.rent.RentDetailActivity;
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
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewContent3Activity extends Activity {

	private String mString_ID;
	private TextView mTv_Title;
	private TextView mTv_Content;
	private TextView mTv_Date;
	private ImageView mImageView1;
	private ImageView mImageView2;
	private ImageView mImageView3;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private String mString_URL_ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_content);
		Intent intent = getIntent();
		mString_ID = intent.getStringExtra("ID");
		mString_URL_ID = intent.getStringExtra("url_id");
		
		initUI();
		downLoad();
		
	}
	private void downLoad() {
		// TODO Auto-generated method stub
			new DownLoadAsyTask().execute(Content.URL_BADFILENEWDETAIL+mString_ID);	
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
					 JSONObject data = jsonObject.getJSONObject("data");
					 String Title = data.getString("Title");
					 String Year = data.getString("Year");
					 String Month = data.getString("Month");
					 String Day = data.getString("Day");
					 String Content = data.getString("Content");
					 String Photo1 = data.getString("Photo1");
					 String Photo2 = data.getString("Photo2");
					 String Photo3 = data.getString("Photo3");

					 //settext
					 mTv_Title.setText(Title);
					 if ("null".equals(Year)||Year==null) {
						}
						 else{
							 mTv_Date.setText(Year+"."+Month+"."+Day);
						 }
					 mTv_Content.setText(Html.fromHtml(Content));
					 
					  initImageLoaderOptions();
					 if ("".equals(Photo1)||Photo1==null) {
					}
					 else {
						 imageLoader.displayImage(Photo1,
								 mImageView1, options);
					}
					 if ("".equals(Photo2)||Photo2==null) {
						}
						 else {
							 imageLoader.displayImage(Photo2,
									 mImageView2, options);
						}
					 if ("".equals(Photo3)||Photo3==null) {
						}
						 else {
							 imageLoader.displayImage(Photo3,
									 mImageView3, options);
						}

					 
				 }
				 else {
					// layout.findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
					new AlertInfoDialog(NewContent3Activity.this).show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(NewContent3Activity.this).show();
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	private void initUI() {
		
		mTv_Title = (TextView) findViewById(R.id.tv_newdetail_title);
		mTv_Content = (TextView) findViewById(R.id.tv_newdetail_content);
		mTv_Date = (TextView) findViewById(R.id.tv_newdetail_date);
		mImageView1 = (ImageView) findViewById(R.id.iv_newdetail_iv1);
		mImageView2 = (ImageView) findViewById(R.id.iv_newdetail_iv2);
		mImageView3 = (ImageView) findViewById(R.id.iv_newdetail_iv3);
		
	}
	public  void btn_back(View v) {
		// TODO Auto-generated method stub
       finish();
	}
	public  void btn_home(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,MainActivity.class));
	}

}
