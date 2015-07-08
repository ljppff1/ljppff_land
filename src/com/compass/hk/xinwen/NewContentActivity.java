package com.compass.hk.xinwen;

import java.io.InputStream;
import java.net.URL;

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
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewContentActivity extends Activity {

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
		if ("1".equals(mString_URL_ID)) {
			
			new DownLoadAsyTask().execute(Content.URL_YISHOU_NEWDETAIL+mString_ID);	
		}
		else if ("2".equals(mString_URL_ID)) {
			
			new DownLoadAsyTask().execute(Content.URL_PROPERTYNEWDETAILS+mString_ID);
		}
	    else if ("3".equals(mString_URL_ID)) {
			
			new DownLoadAsyTask().execute(Content.URL_BADFILENEWDETAIL+mString_ID);
		}
        else if ("4".equals(mString_URL_ID)) {
        	
        	new DownLoadAsyTask().execute(Content.URL_AJNEWDETAIL+mString_ID);
		}
        else if ("5".equals(mString_URL_ID)) {
        	
        	new DownLoadAsyTask().execute(Content.URL_COMPANYNEWDETAILS+mString_ID);
      		}
        else {
			
		}
		
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
					 //html
					 //ÞD“Q
					  String resultss = Content.replace("\"", "'");
					  String resultss2 = resultss.replace("\\", "");
					  Log.e("resultss", resultss);
					  Log.e("resultss2", resultss2);
					 Spanned sp = Html.fromHtml(resultss2, new Html.ImageGetter() {  
						    @Override  
						    public Drawable getDrawable(String source) {  
						        InputStream is = null;  
						        try {  
						            is = (InputStream) new URL(source).getContent();  
						            Drawable d = Drawable.createFromStream(is, "src");  
						            d.setBounds(0, 0, d.getIntrinsicWidth(),  
						                    d.getIntrinsicHeight());  
						            is.close();  
						            return d;  
						        } catch (Exception e) {  
						            return null;  
						        }  
						    }  
						}, null);  
					 mTv_Content.setText(sp);  
						    
					 
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
					new AlertInfoDialog(NewContentActivity.this).show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(NewContentActivity.this).show();
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
