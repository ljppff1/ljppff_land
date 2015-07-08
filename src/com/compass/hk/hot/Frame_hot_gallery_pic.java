package com.compass.hk.hot;

import java.io.IOException;
import java.net.URL;


import com.webdesign688.compass.R;
import com.compass.hk.util.Bean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;






public class Frame_hot_gallery_pic extends Fragment implements OnClickListener
{  

	private String mPicUrl;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private String mPicName;
	 private Drawable mDrawable;
	private View mLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frame_viewpage, null);	
                             ImageView mImageView= (ImageView) mLayout.findViewById(R.id.image_viewpage);
                             mImageView.setOnClickListener(this);
                             
                             Log.e("MyFrame", mPicUrl);
                             //UIL
      			           initImageLoaderOptions();
      						imageLoader.displayImage(mPicUrl,
      								mImageView, options);
      						
      						// new DownloadImageTask().execute(mPicUrl) ;
                             
		            return mLayout ; 
	}
	
	@SuppressLint("NewApi")
	class DownloadImageTask extends AsyncTask<String, Void, Drawable>   
    {  
              
           

			protected Drawable doInBackground(String... urls) {  
                return loadImageFromNetwork(urls[0]);  
            }  
      
            protected void onPostExecute(Drawable result) {  
                   mDrawable=result;
                   mLayout.findViewById(R.id.tv_gallery_referbigpic).setVisibility(View.VISIBLE);
            }  
    }  
	private Drawable loadImageFromNetwork(String imageUrl)  
	{  
	    Drawable drawable = null;  
	    try {  
	        // 可以在这里通过文件名来判断，是否本地有此图片  
	        drawable = Drawable.createFromStream(  
	                new URL(imageUrl).openStream(), "image.jpg");  
	    } catch (IOException e) {  
	        Log.d("test", e.getMessage());  
	    }  
	    if (drawable == null) {  
	        Log.e("test", "null drawable");  
	    } else {  
	        Log.e("test", "not null drawable");  
	    }  
	      
	    return drawable ;  
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
	public void setUrl(String string) {
                this.mPicUrl=string;		
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_viewpage:
				Intent intent=new Intent(getActivity(),Gallery_Pic_Activity.class);
				if ("".equals(mPicName)||mPicName==null) {
				}
				else {
					intent.putExtra("pic", mPicName);
					intent.putExtra("url", mPicUrl);
				}
				startActivity(intent);
			break;

		default:
			break;
		}
	}
	public void setPicName(String photoName) {
		// TODO Auto-generated method stub
		mPicName=photoName;
	}
}
