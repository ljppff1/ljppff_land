package com.compass.hk.frame;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;






public class Frame_viewpage_home extends Fragment 
{  
	protected ImageLoader imageLoader = ImageLoader.getInstance();

    private List<String> list =new ArrayList<String>();
	private  int[] mPicList=new int[]{R.drawable.icon_seafront,R.drawable.homebanner02};
	private int mPosition;
	private DisplayImageOptions options;

	private ImageView imageView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		    
                             View layout = inflater.inflate(R.layout.frame_viewpage_home, null);
                              imageView= (ImageView) layout.findViewById(R.id.image_viewpage_home);
                            imageView.setBackgroundResource(mPicList[mPosition]);
                       //     downLoad(); 
		return layout ; 
	}
	public void setPosition(int arg0,List<String> list) {
		// TODO Auto-generated method stub
	//	this.list =list;
		this.mPosition=arg0;
		//initImageLoaderOptions();
		//imageLoader.displayImage(list.get(arg0),imageView, options);

	}
	
	private void initImageLoaderOptions() {
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.cacheInMemory()
				.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}
}
