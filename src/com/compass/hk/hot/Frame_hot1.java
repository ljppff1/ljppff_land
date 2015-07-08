
package com.compass.hk.hot;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;
import com.compass.hk.util.getJson;
import com.compass.hk.xinwen.NewContentActivity;

/**
 * 物业资料
 * @author liujun
 *
 */
public class Frame_hot1 extends Fragment 
{  

	private View mLayout;
	private TextView mTv_Name;
	private TextView mTv_Predictdate;
	private TextView mTv_Propertyadress;
	private TextView mTv_Propertynum;
	private TextView mTv_Propertyfloor;
	private TextView mTv_Unittotal;
	private TextView mTv_Unitgap;
	private TextView mTv_Facility;
	private TextView mTv_Fheight;
	private TextView mTv_Schoolnet;
	private TextView mTv_Areas;
	private TextView mTv_Developers;
	private TextView mTv_Parking;
	private TextView mTv_SaleAdress;
	private WebView mWb_Latestprice;
	private TextView mTv_Managecompany;
	private String mString_ID;
	
	private ViewPager viewPager;
	ArrayList<String> mNewList_ID=new ArrayList<String>();
	ArrayList<String> mNewList_Title=new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frame_hot1, null);
                             getID();
                             downLoad();
                             downLoadNew();
		return mLayout ; 
	}
	private void downLoadNew() {
		new DownLoadAsyTask_New().execute(Content.URL_YISHOU_NEWLISTS_INNER+mString_ID);		
	}
class DownLoadAsyTask_New extends AsyncTask<String, Void, String> implements OnClickListener{  
		
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
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 
						String  string_ID= jsonObject2.getString("ID");
						String string_Title= jsonObject2.getString("Title");
						Log.e("DownLoadAsyTask_New", string_ID);
						 Log.e("DownLoadAsyTask_New", string_Title);     
						 mNewList_ID.add(string_ID);
						 mNewList_Title.add(string_Title);
					}
					  
					  if (mNewList_ID.size()>0) {
						View rela_1 = getActivity().findViewById(R.id.rela_hot2_new1);
						rela_1.setVisibility(View.VISIBLE);
						TextView textView= (TextView) getActivity().findViewById(R.id.tv_rela_hot2_new1);
						textView.setText(mNewList_Title.get(0));
						rela_1.setOnClickListener(this);
					}
					  if (mNewList_ID.size()>1) {
							View rela_2 = getActivity().findViewById(R.id.rela_hot2_new2);
							rela_2.setVisibility(View.VISIBLE);
							TextView textView= (TextView) getActivity().findViewById(R.id.tv_rela_hot2_new2);
							textView.setText(mNewList_Title.get(1));
							rela_2.setOnClickListener(this);
						}
					  if (mNewList_ID.size()>2) {
							View rela_3 = getActivity().findViewById(R.id.rela_hot2_new3);
							rela_3.setVisibility(View.VISIBLE);
							TextView textView= (TextView)getActivity(). findViewById(R.id.tv_rela_hot2_new3);
							textView.setText(mNewList_Title.get(2));
							rela_3.setOnClickListener(this);
						}
				}
				 else {
				}
			} catch (JSONException e) {
				new Dialog_noInternet(getActivity()).show();
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.rela_hot2_new1:
					 Intent intent = new Intent(getActivity(),NewContentActivity.class);
	                 intent.putExtra("ID", mNewList_ID.get(0));
	                 intent.putExtra("url_id", "1");
					startActivity(intent);
					break;
                case R.id.rela_hot2_new2:
                	
                	 Intent intent2 = new Intent(getActivity(),NewContentActivity.class);
	                 intent2.putExtra("ID", mNewList_ID.get(1));
	                 intent2.putExtra("url_id", "1");
	                 startActivity(intent2);
					break;
                case R.id.rela_hot2_new3:
                	 Intent intent3 = new Intent(getActivity(),NewContentActivity.class);
	                 intent3.putExtra("ID", mNewList_ID.get(2));
	                 intent3.putExtra("url_id", "1");
	                 startActivity(intent3);
	break;
					

				default:
					break;
				}
			}
			}
	private void getID() {
		// TODO Auto-generated method stub
		UILApplication application = (UILApplication) getActivity().getApplication();
		mString_ID = application.getHotDetail_ID();
		Log.e("getID", mString_ID);
	}

	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_FIRST_HAND_DETAIL+mString_ID);
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
					 JSONObject data = jsonObject.getJSONObject("data");
					 String Name = data.getString("Name");
					 String PredictDate = data.getString("PredictDate");
					 String PropertyAddress = data.getString("PropertyAddress");
					 String PropertyNumber = data.getString("PropertyNumber");
					 String PropertyFloor = data.getString("PropertyFloor");
					 String Areas = data.getString("Areas");
					 String Unitgap = data.getString("Unitgap");
					 String Fheight = data.getString("Fheight");
					 String Unittotal = data.getString("Unittotal");
					 String Parking = data.getString("Parking");
					 String SchoolNet = data.getString("SchoolNet");
					 String Facility = data.getString("Facility");
					 String ManageCompany = data.getString("ManageCompany");
					 String Developer = data.getString("Developer");
					 String SaleAddress = data.getString("SaleAddress");
					 String LatestPrice = data.getString("LatestPrice");
					  Log.e("resultss1", LatestPrice);
					 Spanned html_latestprice = Html.fromHtml(LatestPrice);
					 
					 //initUI
				    mLayout.findViewById(R.id.progressBar_hot1).setVisibility(View.GONE);
					 mLayout.findViewById(R.id.scrollview_hot1).setVisibility(View.VISIBLE);
					 
					 
					 initUI();
					 
					 //settext
					 mTv_Name.setText(Name);
					 mTv_Areas.setText(Areas);
					 mTv_Developers.setText(Developer);
					 mTv_Facility.setText(Facility);
					 mTv_Fheight.setText(Fheight);
					 
					 
					 //show content
					 mWb_Latestprice.getSettings().setUseWideViewPort(false);
					 mWb_Latestprice.getSettings().setBuiltInZoomControls(false);
	         			  WebSettings setting = mWb_Latestprice.getSettings();
	         					setting.setJavaScriptEnabled(true);
	         					setting.setBuiltInZoomControls(false);
	         					setting.setSupportZoom(false);

	         					setting.setDomStorageEnabled(true);
	         					setting.setDatabaseEnabled(true);
	         					// 全屏显示
	         				//	setting.setLoadWithOverviewMode(true);
	       			  
	       			    //轉換
	       			  String resultss = LatestPrice.replace("\"", "'");
	       			  String resultss2 = resultss.replace("\\", "");
	       			  Log.e("resultss", resultss);
	       			  Log.e("resultss2", resultss2);
	       		      //顯示數據
	       			mWb_Latestprice.loadDataWithBaseURL(null, resultss2, null, "UTF-8", null);
					 
					 
					 mTv_Managecompany.setText(ManageCompany);
					 mTv_Parking.setText(Parking);
					 mTv_Predictdate.setText(PredictDate);
					 mTv_Propertyadress.setText(PropertyAddress);
					 mTv_Propertyfloor.setText(PropertyFloor);
					 mTv_Propertynum.setText(PropertyNumber);
					 mTv_SaleAdress.setText(SaleAddress);
					 mTv_Schoolnet.setText(SchoolNet);
					 mTv_Unitgap.setText(Unitgap);
					 mTv_Unittotal.setText(Unittotal);
					 
					 
				 }
				 else {
					// layout.findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
					 mLayout.findViewById(R.id.progressBar_hot1).setVisibility(View.GONE);
					new AlertInfoDialog(getActivity()).show();
				}
			} catch (JSONException e) {
				mLayout.findViewById(R.id.progressBar_hot1).setVisibility(View.GONE);
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
	private void initUI() {
		
		mTv_Name = (TextView) mLayout.findViewById(R.id.tv_hot_detail_name);
		mTv_Predictdate = (TextView) mLayout.findViewById(R.id.tv_hot_detail_predictdate);
		mTv_Propertyadress = (TextView) mLayout.findViewById(R.id.tv_hot_detail_propertyadress);
		mTv_Propertynum = (TextView) mLayout.findViewById(R.id.tv_hot_detail_propertynum);
		mTv_Propertyfloor = (TextView) mLayout.findViewById(R.id.tv_hot_detail_propertyfloor);
		mTv_Unittotal = (TextView) mLayout.findViewById(R.id.tv_hot_detail_unittotal);
		mTv_Unitgap = (TextView) mLayout.findViewById(R.id.tv_hot_detail_unitgap);
		mTv_Facility = (TextView) mLayout.findViewById(R.id.tv_hot_detail_facility);
		mTv_Fheight = (TextView) mLayout.findViewById(R.id.tv_hot_detail_fheight);
		mTv_Schoolnet = (TextView) mLayout.findViewById(R.id.tv_hot_detail_schoolnet);
		mTv_Areas = (TextView) mLayout.findViewById(R.id.tv_hot_detail_areas);
		mTv_Developers = (TextView) mLayout.findViewById(R.id.tv_hot_detail_developers);
		mTv_Parking = (TextView) mLayout.findViewById(R.id.tv_hot_detail_parking);
		mTv_SaleAdress = (TextView) mLayout.findViewById(R.id.tv_hot_detail_saleadress);
		mWb_Latestprice = (WebView) mLayout.findViewById(R.id.wv_hot_detail_latestprice);
		mTv_Managecompany = (TextView) mLayout.findViewById(R.id.tv_hot_detail_managecompany);
	}
}
