package com.compass.hk.rent;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.hot.GallleryViewActivity;
import com.compass.hk.hot.HotDetail3Activity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;
import com.compass.hk.util.getJson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class Frame_rent_plane extends Fragment 
{  
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	
	private GridView mGridView;
	ArrayList<Data>  mDataList=new ArrayList<Data>();
	private String mString_ID;
	private View mLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{          
                             mLayout = inflater.inflate(R.layout.activity_hot_detail3, null);	
                             getID();
                    		 //download
                            downLoad();
		return mLayout ; 
	}
	//UIL
	private void initImageLoaderOptions() {
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.cacheInMemory()
				.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}
	private void getID() {
		// TODO Auto-generated method stub
		  Bean   bean=new Bean();		
          mString_ID = bean.getRentID();
	}
private void downLoad() {
// TODO Auto-generated method stub
new DownLoadAsyTask().execute(Content.URL_PROPERTYDETAIL_GALLERY+mString_ID);
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
			 JSONArray array = jsonObject.getJSONArray("data");
			  for (int i = 0; i < array.length(); i++) {
				  
				  Data  data=new Data();
				  
				 JSONObject jsonObject2 = array.getJSONObject(i);
				 data.GalleryID= jsonObject2.getString("GalleryID");
				 data.CoverPic= jsonObject2.getString("CoverPic");
				 data.GalleryName = jsonObject2.getString("GalleryName");
				 mDataList.add(data);
			}
			  initGridView();
		}
		 else {
			 mLayout.findViewById(R.id.progressBar_plane).setVisibility(View.GONE);
			new CustomerAlertDialog(getActivity(),"•ºŸoÏàêPÆ½ÃæˆD").show();
		}
	} catch (JSONException e) {
		 if(mDataList.isEmpty())
		new Dialog_noInternet(getActivity()).show();
		e.printStackTrace();
	}
}
	@Override
	protected String doInBackground(String... params) {
		String str=params[0];
		return getJson.getData(str);
	}
	}
private void initGridView() {
          		mLayout.findViewById(R.id.progressBar_plane).setVisibility(View.GONE);
          		mGridView = (GridView)mLayout.findViewById(R.id.gridView_plane);
          		mGridView.setVisibility(View.VISIBLE);
          		mGridView.setAdapter(new Myadapter());
          		mGridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						
					Intent intent=new Intent(getActivity(),GallleryViewActivity.class);
					intent.putExtra("ID", mDataList.get(position).GalleryID);
					
					startActivity(intent);
					//getActivity().finish();
						/*UILApplication application = (UILApplication)getApplication();
						   application.setHotDetail_ID(mDataList.get(position).string_ID);
                          startActivity(new Intent(getActivity(),HotDetailActivity.class));*/								
					}
				});
          		
}
class Data{
String   GalleryID;
String   GalleryName;
String   CoverPic;
@Override
public String toString() {
	return "Data [GalleryID=" + GalleryID + ", GalleryName="
			+ GalleryName + ", CoverPic=" + CoverPic + "]";
}
}
class  Myadapter extends   BaseAdapter{
@Override
public View getView(int position, View convertView, ViewGroup parent) {
	           View layout = getActivity().getLayoutInflater().inflate(R.layout.item_gridview_plane, null);
	           TextView mTv_Name= (TextView) layout.findViewById(R.id.tv_gv_name);
	           
	           ImageView imageView= (ImageView) layout.findViewById(R.id.iv_gridview_pic);
	           //UIL
	           initImageLoaderOptions();
				imageLoader.displayImage(mDataList.get(position).CoverPic,
						imageView, options);
	           //settext
	           mTv_Name.setText(mDataList.get(position).GalleryName);
	           
	return layout;
}
@Override
public int getCount() {
	// TODO Auto-generated method stub
	return mDataList.size();
}

@Override
public Object getItem(int position) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return 0;
}

}

	
}
