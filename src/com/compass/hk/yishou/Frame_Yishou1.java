package com.compass.hk.yishou;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.HotActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_agence;
import com.compass.hk.hot.Frame_viewpage_Hot;
import com.compass.hk.hot.HotDetail2Activity;
import com.compass.hk.hot.HotDetailActivity;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;
import com.compass.hk.util.getJson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * ljppff一手楼盘
 * @author liujun
 *
 */
public class Frame_Yishou1 extends Fragment 
{  

	
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	
	private View layout;
	private ListView mListView;
	ArrayList<Data>  mDataList=new ArrayList<Frame_Yishou1.Data>();
	private ViewPager viewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             layout = inflater.inflate(R.layout.frame_menu1, null);	
                            //download
                            // initViewPage();
                     		downLoad();
		return layout ; 
	}
	private void initViewPage() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager) layout.findViewById(R.id.pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 10000;
		}
		@Override
		public Fragment getItem(int arg0) {
	
		Frame_viewpage_Hot myFrame = new Frame_viewpage_Hot();
			return myFrame ;
		}
	});
	}
	//UIL
			private void initImageLoaderOptions() {
				options = new DisplayImageOptions.Builder()
						.showImageForEmptyUri(R.drawable.ic_empty)
						.cacheInMemory()
						.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
						.bitmapConfig(Bitmap.Config.RGB_565).build();
			}
	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_FIRST_HAND+"1");
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
						 data.string_ID= jsonObject2.getString("ID");
						 data.string_Name= jsonObject2.getString("Name");
						 data.string_DistrictName = jsonObject2.getString("DistrictName");
						 data.string_Developer=jsonObject2.getString("Developer");
						 data.string_SaleAddress=jsonObject2.getString("PropertyAddress");
						 data.string_CoverPic=jsonObject2.getString("CoverPic");
						 mDataList.add(data);
                          data.toString();						 
					}
					  initListView();
				}
				 else {
					 layout.findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
					new AlertInfoDialog(getActivity()).show();
				}
			} catch (JSONException e) {
			 if(mDataList.isEmpty())
				new Dialog_noInternet(getActivity()).show();
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	private void initListView() {
                  		layout.findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
                  		mListView = (ListView) layout.findViewById(R.id.listView_tab_rent);
                  		mListView.setVisibility(View.VISIBLE);
                  		mListView.setAdapter(new Myadapter());
                  		mListView.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								UILApplication application = (UILApplication) getActivity().getApplication();
								   application.setHotDetail_ID(mDataList.get(position).string_ID);
								   application.setHotDetail_Name(mDataList.get(position).string_Name);
								   
                                Intent intent =new Intent(getActivity(),HotDetailActivity.class);
                                intent.putExtra("ID", mDataList.get(position).string_ID);
                                startActivity(intent);
                                
							}
						});
	}
	class Data{
		String   string_ID;
		String   string_Name;
		String   string_DistrictName;
		String   string_Developer;
		String   string_SaleAddress;
		String   string_CoverPic;
		@Override
		public String toString() {
			return "Data [string_ID=" + string_ID + ", string_Name="
					+ string_Name + ", string_DistrictName="
					+ string_DistrictName + ", string_Developer="
					+ string_Developer + ", string_SaleAddress="
					+ string_SaleAddress + ", string_CoverPic="
					+ string_CoverPic + "]";
		}
		
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			           View layout = getActivity().getLayoutInflater().inflate(R.layout.item_frame_yishou, null);
			           TextView mTv_Name= (TextView) layout.findViewById(R.id.tv_listview_firsthand_name);
			           TextView mTv_StrickName= (TextView) layout.findViewById(R.id.tv_listview_firsthand_strickname);
			           TextView mTv_SellAdress= (TextView) layout.findViewById(R.id.tv_listview_firsthand_selladress);
			           TextView mTv_Developer= (TextView) layout.findViewById(R.id.tv_listview_firsthand_developer);
			           
			           ImageView imageView= (ImageView) layout.findViewById(R.id.iv_listview_firsthand_pic);
			           //UIL
			           initImageLoaderOptions();
						imageLoader.displayImage(mDataList.get(position).string_CoverPic,
								imageView, options);
			           //settext
			           mTv_Name.setText(mDataList.get(position).string_Name);
			           mTv_StrickName.setText("區域:"+mDataList.get(position).string_DistrictName);
			           mTv_SellAdress.setText("物業地址:"+mDataList.get(position).string_SaleAddress);
			           mTv_Developer.setText("發展商:"+mDataList.get(position).string_Developer);
			           
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
