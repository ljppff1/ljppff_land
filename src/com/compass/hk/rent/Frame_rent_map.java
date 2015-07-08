package com.compass.hk.rent;

import com.webdesign688.compass.R;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioGroup;

public class Frame_rent_map extends Fragment 
{  

	private View mLayout;
	private WebView mWebView;
	private String mString_ID;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{          
                             mLayout = inflater.inflate(R.layout.frament_rent_map, null);
                             mWebView = (WebView) mLayout.findViewById(R.id.webView1);
                             getID();
                             WebSettings setting = mWebView.getSettings();
                     		setSettings(setting);
                     		mWebView.setWebChromeClient(new WebChromeClient());
                     		mWebView.setWebViewClient(new WebViewClient());
                     		
                     		
                     		mWebView.loadUrl(Content.URL_RENT_MAP+mString_ID);
                     		
		return mLayout ; 
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
		  Bean   bean=new Bean();		
          mString_ID = bean.getRentID();
	}
}
