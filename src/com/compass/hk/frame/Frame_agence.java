package com.compass.hk.frame;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.HotActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_Calling;
import com.compass.hk.dialog.Dialog_Calling.mOnClickListener;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.hot.Gallery_Pic_Activity;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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






public class Frame_agence extends Fragment 
{  

	private String mPicUrl;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	private String mPicName;
	private View mLayout;
	private String mString_Call2;
	private String mString_Call1;
	private ArrayList<Data>  mDataList=new ArrayList<Frame_agence.Data>();
	private TextView mTv_CompanyName ,mTv_Licence,mTv_PersonName,mTv_Telephone1,mTv_Telephone2;
	private ImageView mImageView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frame_agence, null);	
                             initUi();
                             downLoad();
                             ImageView mImageView= (ImageView) mLayout.findViewById(R.id.ib_agence_call);
                             mImageView.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									Dialog_Calling dialog_Calling = new Dialog_Calling(getActivity(), "ì‹¥Ú“‘œ¬ƒƒÇÄÎä‘í£ø");
									dialog_Calling.setOnclickListener_on(new mOnClickListener() {
										
										@Override
										public void onCall2_Clicklistener() {
											// TODO Auto-generated method stub
											 if (mString_Call2==null||"null".equals(mString_Call2)) {
													
												 
											}
											 else {
												 Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mString_Call2));
												  startActivity(intent);
											}
										}
										
										@Override
										public void onCall1_Clicklistener() {
											// TODO Auto-generated method stub
											if (mString_Call1==null||"null".equals(mString_Call1)) {
												
											}
											else {
												Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mString_Call1));
												  startActivity(intent);
											}
										}
									});
									dialog_Calling.show();
								}
							});
                           
                             
		return mLayout ; 
	}
	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_Agence);
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
						 data.CompanyName= jsonObject2.getString("CompanyName");
						 data.ContactName= jsonObject2.getString("ContactName");
						 data.ContactTel = jsonObject2.getString("ContactTel");
						 data.ContactPhone = jsonObject2.getString("ContactPhone");
						 data.PersonLicense=jsonObject2.getString("PersonLicense");
						 data.PersonPhoto=jsonObject2.getString("PersonPhoto");
						 mDataList.add(data);
                          data.toString();						 
					}

					  Log.e("agence", mDataList.toString());
					  mTv_CompanyName.setText(mDataList.get(0).CompanyName.toString());
					  mTv_PersonName.setText(mDataList.get(0).ContactName.toString());
					  String replace = mDataList.get(0).ContactPhone.toString();
					  mString_Call1= replace.replace("&nbsp;", "");
					  Log.e("agence33", mString_Call1.replace("&nbsp;", " "));
					  if (mString_Call1!=null||"null".equals(mString_Call1)) {
						
						  mTv_Telephone1.setText(replace.replace("&nbsp;", " "));
					}
					  String replace2 = mDataList.get(0).ContactTel.toString();
					  mString_Call2= replace2.replace("&nbsp;", "");
					  if (mString_Call2!=null||"null".equals(mString_Call2)) {
							
						  mTv_Telephone1.setText(replace2.replace("&nbsp;", " "));
					}
					  mTv_Telephone2.setText(mString_Call2);
					  mTv_Licence.setText(mDataList.get(0).PersonLicense.toString());
					  //UIL
 			           initImageLoaderOptions();
 						imageLoader.displayImage( mDataList.get(0).PersonPhoto.toString(),
 								mImageView, options);
					  
				}
				 else {
					new AlertInfoDialog(getActivity()).show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(getActivity()).show();
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	private void initUi() {
		
		  mTv_CompanyName = (TextView) mLayout.findViewById(R.id.tv_frag_companyname);
		  mTv_Licence= (TextView) mLayout.findViewById(R.id.tv_frag_lience);
		  mTv_PersonName= (TextView) mLayout.findViewById(R.id.tv_frag_personname);
		  mTv_Telephone1= (TextView) mLayout.findViewById(R.id.tv_frag_telephone1);
		  mTv_Telephone2= (TextView) mLayout.findViewById(R.id.tv_frag_telephone2);
		  mImageView= (ImageView) mLayout.findViewById(R.id.iv_frag_personphoto);
		
	}
	class Data{
		String CompanyName;
		String PersonPhoto;
		String PersonLicense;
		String ContactName;
		String ContactPhone;
		String ContactTel;
		@Override
		public String toString() {
			return "Data [CompanyName=" + CompanyName + ", PersonPhoto="
					+ PersonPhoto + ", PersonLicense=" + PersonLicense
					+ ", ContactName=" + ContactName + ", ContactPhone="
					+ ContactPhone + ", ContactTel=" + ContactTel + "]";
		}
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
