package com.compass.hk.calculator;



import com.webdesign688.compass.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TableActivity extends Activity {
	private WebView webView;
	private String mUrl_table;
	private WebView mWebView;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table);
		mWebView = (WebView) findViewById(R.id.webView_table);
		
		//obtain video url
		Intent intent = getIntent();
		mUrl_table = intent.getStringExtra("table");
		 //show content
  		mWebView.getSettings().setUseWideViewPort(true);
		  mWebView.getSettings().setBuiltInZoomControls(true);
		  WebSettings setting = mWebView.getSettings();
				setting.setJavaScriptEnabled(true);
				setting.setBuiltInZoomControls(true);
				setting.setDisplayZoomControls(true);
				setting.setSupportZoom(true);

				setting.setDomStorageEnabled(true);
				setting.setDatabaseEnabled(true);
		  
		    //ÞD“Q
		  String resultss = mUrl_table.replace("\"", "'");
		  String resultss2 = resultss.replace("\\", "");
		  Log.e("resultss", resultss);
		  Log.e("resultss2", resultss2);
	      //ï@Ê¾”µ“þ
		  mWebView.loadDataWithBaseURL(null, resultss2, null, "UTF-8", null);
		
	}
}
