package com.compass.hk.hot;

import com.webdesign688.compass.R;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * ljppffΩ÷æ∞µÿÕº
 * @author liujun
 *
 */
public class HotDetail4Activity extends Activity {
  
	private String mString_ID;
	private WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hot_detail4);
		 mWebView = (WebView)findViewById(R.id.webView1);
         getID();
         WebSettings setting = mWebView.getSettings();
 		setSettings(setting);
 		mWebView.setWebChromeClient(new WebChromeClient());
 		mWebView.setWebViewClient(new WebViewClient());
 		
 		mWebView.loadUrl(Content.URL_YISHOU_JIEJING+mString_ID);
	}
	@SuppressLint("NewApi")
	private void setSettings(WebSettings setting) {
		setting.setJavaScriptEnabled(true);
		setting.setBuiltInZoomControls(true);
		setting.setDisplayZoomControls(true);
		setting.setSupportZoom(true);
		setting.setDomStorageEnabled(true);
		setting.setDatabaseEnabled(true);
		// »´∆¡œ‘ æ
		setting.setLoadWithOverviewMode(true);
		setting.setUseWideViewPort(true);
	}
	private void getID() {
		// TODO Auto-generated method stub
		UILApplication application = (UILApplication)getApplication();
		mString_ID = application.getHotDetail_ID();
		Log.e("getID", mString_ID);
	}
}
