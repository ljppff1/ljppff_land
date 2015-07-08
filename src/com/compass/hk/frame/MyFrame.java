package com.compass.hk.frame;

import com.webdesign688.compass.R;
import com.compass.hk.hot.Gallery_Pic_Activity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;






public class MyFrame extends Fragment 
{  

	private String mPicUrl;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private String mPicName;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             View layout = inflater.inflate(R.layout.frame_viewpage_hot_pic, null);	
                             ImageView mImageView= (ImageView) layout.findViewById(R.id.image_viewpage);
                             mImageView.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Intent intent=new Intent(getActivity(),Gallery_Pic_Activity.class);
									if ("".equals(mPicName)||mPicName==null) {
									}
									else {
										intent.putExtra("pic", mPicName);
										intent.putExtra("url", mPicUrl);
									}
									startActivity(intent);
								}
							});
                             Log.e("MyFrame", mPicUrl);
                             //UIL
      			           initImageLoaderOptions();
      						imageLoader.displayImage(mPicUrl,
      								mImageView, options);
                             
		return layout ; 
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
	public void setText(String string) {
		// TODO Auto-generated method stub
	        this.mPicName=string;	
	}
	
}
