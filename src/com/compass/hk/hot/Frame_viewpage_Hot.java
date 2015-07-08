package com.compass.hk.hot;

import com.webdesign688.compass.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;






public class Frame_viewpage_Hot extends Fragment 
{  

	private String mPicUrl;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             View layout = inflater.inflate(R.layout.frame_viewpage_hot, null);	
		return layout ; 
	}
	//UIL
	private void initImageLoaderOptions() {
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.cacheInMemory()
				.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}
	public void setUrl(String string) {
                this.mPicUrl=string;		
		
	}
	
}
