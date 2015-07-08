package com.compass.hk.badfile;

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

public class XiongZhaiListDetailActivity extends Activity {

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
	private String TITLE;
	private String DATE;
	private String CONTENT;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_content1);
		Intent intent = getIntent();
		TITLE = intent.getStringExtra("TITLE");
		DATE = intent.getStringExtra("DATE");
		CONTENT = intent.getStringExtra("CONTENT");
		
		initUI();
		
	}
	
	
	private void initUI() {
		
		mTv_Title = (TextView) findViewById(R.id.tv_newdetail_title);
		mTv_Content = (TextView) findViewById(R.id.tv_newdetail_content);
		mTv_Date = (TextView) findViewById(R.id.tv_newdetail_date);
		 Spanned sp = Html.fromHtml(CONTENT, new Html.ImageGetter() {  
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
		mTv_Title.setText(TITLE);
		mTv_Date.setText(DATE);
		
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
