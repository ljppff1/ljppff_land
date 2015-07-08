 package com.compass.hk.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.ListviewActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.util.Content;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchActivity extends FragmentActivity implements OnClickListener {

	private TextView mTv_Rentsale;
	private TextView mTv_PropertyStyle;
	private TextView mTv_District;
	private TextView mTv_Zone;
	private int mDistrict_Num;
	private int mZone_Num;
	private int mPropertystyle_Num;
	private int mRentsale_Num;
	private TextView mTv_Area;
	private TextView mTv_Jiange;
	private int mJiange_String_Num;
	private Spinner mSpinner_Area1;
	private Spinner mSpinner_Area2;
	private String[] mAreaList1;
	private String[] mAreaList2;
	protected String mSting_area2;
	protected String mString_area1;
	private EditText mEt_Keyword;
	private EditText mEt_PropetyNo;
	private RadioButton mRb_Type1;
	private RadioButton mRb_Type2;
	private RadioButton mRb_Type3;
	private String searchType;
	private String mString_Keyword;
	private String mString_PropetyNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("物業搜尋");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		initUI();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		mTv_Rentsale = (TextView) findViewById(R.id.tv_postbuild_rentsale);
		mTv_PropertyStyle = (TextView) findViewById(R.id.tv_postbuild_propertystyle);
		mTv_District = (TextView) findViewById(R.id.tv_postbuild_district);
		mTv_Zone = (TextView) findViewById(R.id.tv_postbuild_zone);
		mTv_Jiange = (TextView) findViewById(R.id.tv_search_jiange);
		
		mSpinner_Area1 = (Spinner) findViewById(R.id.spinner_search_area1);
		mSpinner_Area2 = (Spinner) findViewById(R.id.spinner_search_area2);
		
		mEt_Keyword = (EditText) findViewById(R.id.et_search_keyword);
		mEt_PropetyNo = (EditText) findViewById(R.id.et_search_PropertyNo);
		
		mRb_Type1 = (RadioButton) findViewById(R.id.RadioButton_SearchType_1);
		mRb_Type2 = (RadioButton) findViewById(R.id.RadioButton_SearchType_2);
		mRb_Type3 = (RadioButton) findViewById(R.id.RadioButton_SearchType_3);
		
		initSpinner();
		
		mTv_Rentsale.setOnClickListener(this);
		mTv_PropertyStyle.setOnClickListener( this);
		mTv_District.setOnClickListener(this);
		mTv_Zone.setOnClickListener(this);
		mTv_Jiange.setOnClickListener(this);
	}

	private void initSpinner() {
		mAreaList1 = new String[]{"0","100","200","300","400","500","600","700","800","900","1000"};
		mAreaList2 = new String[]{"0","100","200","300","400","500","600","700","800","900","1000+"};
		
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, R.layout.item_spinner, mAreaList1);
		ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, R.layout.item_spinner, mAreaList2);
		mSpinner_Area1.setAdapter(adapter1);
		mSpinner_Area2.setAdapter(adapter2);
		mSpinner_Area1.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				mString_area1 = mAreaList1[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mSpinner_Area2.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (arg2==10) {
					mSting_area2="1000000";
				}
				else {
					
					mSting_area2 = mAreaList2[arg2];
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	public	 void btn_reset(View v) {
		// TODO Auto-generated method stub
           startActivity(new Intent(this,SearchActivity.class));
           finish();
	}
	public	 void btn_search(View v) {
		// TODO Auto-generated method stub
		  if (mRb_Type1.isChecked()) {
		 searchType = "1";
		 }
		if (mRb_Type2.isChecked()) {
			searchType = "2";
			}
		if (mRb_Type3.isChecked()) {
			searchType = "0";
			}
		mString_Keyword = mEt_Keyword.getText().toString();
		mString_PropetyNo = mEt_PropetyNo.getText().toString();
		
		summit();
                   
	}
	private void summit() {
		findViewById(R.id.mPBsearch).setVisibility(View.VISIBLE);
		 RequestParams params = new RequestParams();
         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
         nameValuePairs.add(new BasicNameValuePair("SearchType", searchType));
         nameValuePairs.add(new BasicNameValuePair("RentSale", mRentsale_Num+""));
         nameValuePairs.add(new BasicNameValuePair("PropertyType", mPropertystyle_Num+""));
         nameValuePairs.add(new BasicNameValuePair("PropertyLocation", mDistrict_Num+""));
         nameValuePairs.add(new BasicNameValuePair("PropertyDistrict", mZone_Num+""));
         nameValuePairs.add(new BasicNameValuePair("PropertyRoom", mJiange_String_Num+""));
         nameValuePairs.add(new BasicNameValuePair("PropertyAreaFrom", mString_area1));
         nameValuePairs.add(new BasicNameValuePair("PropertyAreaTo", mSting_area2));
			
        	 nameValuePairs.add(new BasicNameValuePair("KeyWord", mString_Keyword));
        	 nameValuePairs.add(new BasicNameValuePair("PropertyNo", mString_PropetyNo));
        	 
         params.addBodyParameter(nameValuePairs);
         HttpUtils http = new HttpUtils();
         http.send(HttpRequest.HttpMethod.POST,
        		Content.URL_PROPERTYSEARCH,
                 params,
                 new RequestCallBack<String>() {   

                     @Override
                     public void onStart() {
                     }
                     @Override
                     public void onLoading(long total, long current, boolean isUploading) {
                     }
                     @Override
                     public void onSuccess(ResponseInfo<String> responseInfo) {
                     	Log.e("onSuccess", "upload response:" + responseInfo.result);
                     	
                     	try {
							JSONObject result =new JSONObject(responseInfo.result);
							  String code = result.getString("code");
							  String msg = result.getString("msg");
							  String data = result.getString("data");
							  
							  Log.e("code:", code);
							  Log.e("msg:", msg);
							  Log.e("data:", data);
								findViewById(R.id.mPBsearch).setVisibility(View.GONE);
							  if ("1".equals(code)) {
								
								  Intent intent=new Intent(SearchActivity.this,SearchResultActivity.class);
								  intent.putExtra("data", data);
								startActivity(intent);
								//finish();
							}
							  else {
								new CustomerAlertDialog(SearchActivity.this, "無相關搜索結果").show();
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                     }
                     @Override
                     public void onFailure(HttpException error, String msg) {
                     //	Toast.makeText(ForgetPassword.this, "未發送成功，", Toast.LENGTH_SHORT).show();  
                     }
                 });
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_postbuild_rentsale:
			Intent intent1=new Intent(SearchActivity.this,ListviewActivity.class);
			intent1.putExtra("title", "租售类型");
			intent1.putExtra("id", 1);
			startActivityForResult(intent1, 0);
			break;
        case R.id.tv_postbuild_propertystyle:
        	Intent intent2=new Intent(SearchActivity.this,ListviewActivity.class);
			intent2.putExtra("title", "物業種類");
			intent2.putExtra("id", 2);
			startActivityForResult(intent2, 0);
			break;
         case R.id.tv_postbuild_district:
        		Intent intent3=new Intent(SearchActivity.this,ListviewActivity.class);
    			intent3.putExtra("title", "區域");
    			intent3.putExtra("id", 3);
    			startActivityForResult(intent3, 0);
	          break;
          case R.id.tv_postbuild_zone:
        	  
        	  if (mDistrict_Num==0) {
				new CustomerAlertDialog(SearchActivity.this, "請選擇區域").show();
			}
        	  else {
        		  Intent intent4=new Intent(SearchActivity.this,ListviewActivity.class);
        		  intent4.putExtra("title", "地區");
        		  intent4.putExtra("id", 4);
        		  intent4.putExtra("districtnum", mDistrict_Num);
        		  startActivityForResult(intent4, 0);
			}
	      break;
          case R.id.tv_search_jiange:
      		Intent intent14=new Intent(SearchActivity.this,ListviewActivity.class);
  			intent14.putExtra("title", "間隔");
  			intent14.putExtra("id", 14);
  			startActivityForResult(intent14, 0);
	          break;

		default:
			break;
		}
	}
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		switch (arg1) {
		case 1:
			mTv_Rentsale.setText(arg2.getStringExtra("name")+"  ");
			mRentsale_Num = arg2.getIntExtra("num", 3);
			Log.e("mRentsale_Num", ""+mRentsale_Num);
			break;
		case 2:
			mTv_PropertyStyle.setText(arg2.getStringExtra("name")+"  ");
			mPropertystyle_Num = arg2.getIntExtra("num", 0);
			Log.e("mPropertystyle_Num", ""+mPropertystyle_Num);
			break;
		case 3:
			mTv_District.setText(arg2.getStringExtra("name")+"  ");
			mDistrict_Num = arg2.getIntExtra("num", 0);
			Log.e("mDistrict_Num", ""+mDistrict_Num);
			
			break;
			
		case 4:
			mTv_Zone.setText(arg2.getStringExtra("name")+"  ");
			mZone_Num = arg2.getIntExtra("num", 0);
			Log.e("mZone_Num", ""+mZone_Num);
			break;
		case 14:
			mTv_Jiange.setText(arg2.getStringExtra("name")+"  ");
			mJiange_String_Num = arg2.getIntExtra("num", 0);
			Log.e("mJiange_String_Num", ""+mJiange_String_Num);
			break;
		default:
			break;
		}
	}
}
