package com.compass.hk;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.rent.RentDetailActivity;
import com.compass.hk.search.SearchActivity;
import com.compass.hk.search.SearchResultActivity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.widgets.ChildListView;
import com.compass.hk.widgets.MyListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.webdesign688.compass.R;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 立即放盘
 * @author liujun
 *
 */
public class FangPanActivity extends FragmentActivity implements OnClickListener {

	private TextView mTv_PropertyStyle;
	private TextView mTv_Rentsale;
	private TextView mTv_District;
	private TextView mTv_Zone;
	private int mDistrict_Num;
	private int mZone_Num;
	private EditText mEt_areagross;
	private EditText mEt_areanet;
	private EditText mEt_developer;
	private EditText mEt_periods;
	private EditText mEt_buildname;
	private EditText mEt_seatno;
	private EditText mEt_streetname;
	private EditText mEt_units;
	private EditText mEt_years;
	private TextView mTv_Floor;
	private int mRentsale_Num=3;
	private int mPropertystyle_Num;
	private TextView mTv_Livingroom;
	private TextView mTv_Dinningroom;
	private TextView mTv_Bedroom;
	private TextView mTv_Flat;
	private TextView mTv_Toilet;
	private TextView mTv_Kitchen;
	private TextView mTv_Sitto;
	private TextView mTv_Schoolnet;
	private String mLivingroom_String_Num;
	private String mBeding_String_Num;
	private String mKitchen_String_Num;
	private String mToilet_String_Num;
	private EditText mEt_Saleprice;
	private EditText mEt_Vavantdate;
	private EditText mEt_ContractPrice;
	private EditText mEt_ContractExpiry;
	private EditText mEt_RentPrice;
	private EditText mEt_Taxdeduction;
	private EditText mEt_Maintenance;
	private CheckBox mCb_isinclude1;
	private CheckBox mCb_isinclude2;
	private CheckBox mCb_isinclude3;
	private CheckBox mCb_isinclude4;
	private CheckBox mCb_isVancant1;
	private CheckBox mCb_isVancant2;
	private CheckBox mCb_post_1;
	private CheckBox mCb_post_2;
	private CheckBox mCb_post_3;
	private CheckBox mCb_post_4;
	private CheckBox mCb_post_5;
	private CheckBox mCb_post_6;
	private CheckBox mCb_post_7;
	private CheckBox mCb_post_8;
	private CheckBox mCb_post_9;
	private CheckBox mCb_post_10;
	private CheckBox mCb_post_11;
	private CheckBox mCb_post_12;
	private CheckBox mCb_post_13;
	private CheckBox mCb_post_14;
	private CheckBox mCb_post_15;
	private CheckBox mCb_post_16;
	private CheckBox mCb_post_17;
	private CheckBox mCb_post_18;
	private CheckBox mCb_post_19;
	private CheckBox mCb_post_20;
	private CheckBox mCb_post_21;
	private CheckBox mCb_post_22;
	private CheckBox mCb_post_23;
	private ChildListView myListView;
	 private  ArrayList<Integer>  mListviewNumList=new ArrayList<Integer>();
	 private  ArrayList<String>  mPicList=new ArrayList<String>();
	 private  ArrayList<Data>  mDataList=new ArrayList<Data>();
	private CheckBox mCb_post_PhotoMark;
	private EditText mEt_Remark;
	private EditText mEt_Video;
	
	private String picPath = null;  
	private File file;
	private MyAdapter mAdapter;
	private String mFloor_Num;
	private String mDinning_Num;
	private String mFlat_Num;
	private String mSitto_Num;
	private String mSchoolNum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fang_pan);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("立即放盤");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		
		initUI();
		initListView();
	}

	private void initListView() {
		mListviewNumList.add(1);
		myListView = (ChildListView) findViewById(R.id.mylistview_postbuild);
		mAdapter = new MyAdapter();
		myListView.setAdapter(mAdapter);
		ScrollView scrollView= (ScrollView) findViewById(R.id.scrollview_fangpan);
		scrollView.smoothScrollTo(0, 0);
	}
	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mListviewNumList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
 
		@Override
		public View getView(final int arg0, View arg1, ViewGroup arg2) {
		View layout = getLayoutInflater().inflate(R.layout.item_listview_postbuild, null);
		Button button_select= (Button) layout.findViewById(R.id.button_llistview_postbuild_selectpic);
		button_select.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/*** 
		         * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 
		         */  
				  Intent i = new Intent(
						                          Intent.ACTION_PICK,
						                          android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						                  startActivityForResult(i, 1);
			
			
			}
		});
		Button button_delete= (Button) layout.findViewById(R.id.button_llistview_postbuild_deletepic);
		button_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mListviewNumList.remove(arg0);
				 mAdapter.notifyDataSetChanged();
			}
		});
		TextView textView_picth = (TextView) layout.findViewById(R.id.tv_postbuild_picth);
		
			return layout;
		}}
	private void initUI() {
		mTv_Rentsale = (TextView) findViewById(R.id.tv_postbuild_rentsale);
		mTv_PropertyStyle = (TextView) findViewById(R.id.tv_postbuild_propertystyle);
		mTv_District = (TextView) findViewById(R.id.tv_postbuild_district);
		mTv_Zone = (TextView) findViewById(R.id.tv_postbuild_zone);
		mTv_Floor = (TextView) findViewById(R.id.tv_postbuild_floor);
		
		//間隔
		mTv_Livingroom = (TextView) findViewById(R.id.tv_postbuild_livingroom);
		mTv_Dinningroom = (TextView) findViewById(R.id.tv_postbuild_diningroom);
		mTv_Bedroom= (TextView) findViewById(R.id.tv_postbuild_bedroom);
		mTv_Flat= (TextView) findViewById(R.id.tv_postbuild_flat);
		mTv_Toilet = (TextView) findViewById(R.id.tv_postbuild_toilet);
		mTv_Kitchen= (TextView) findViewById(R.id.tv_postbuild_kitchen);
		mTv_Sitto= (TextView) findViewById(R.id.tv_postbuild_sitto);
		mTv_Schoolnet = (TextView) findViewById(R.id.tv_postbuild_schoolnet);
		
		mTv_Rentsale.setOnClickListener(this);
		mTv_PropertyStyle.setOnClickListener( this);
		mTv_District.setOnClickListener(this);
		mTv_Zone.setOnClickListener(this);
		mTv_Floor.setOnClickListener(this);
		
		mTv_Livingroom.setOnClickListener(this);
		mTv_Dinningroom.setOnClickListener( this);
		mTv_Bedroom.setOnClickListener(this);
		mTv_Flat.setOnClickListener(this);
		mTv_Toilet.setOnClickListener(this);
		mTv_Sitto.setOnClickListener(this);
		mTv_Schoolnet.setOnClickListener(this);
		mTv_Kitchen.setOnClickListener(this);
		
		
		mEt_areagross = (EditText) findViewById(R.id.et_postbuilder_areagross);
		mEt_areanet = (EditText) findViewById(R.id.et_postbuilder_areanet);
		mEt_buildname = (EditText) findViewById(R.id.et_postbuilder_buildname);
		mEt_developer = (EditText) findViewById(R.id.et_postbuilder_developer);
		mEt_periods = (EditText) findViewById(R.id.et_postbuilder_periods);
		mEt_seatno = (EditText) findViewById(R.id.et_postbuilder_seatno);
		mEt_streetname = (EditText) findViewById(R.id.et_postbuilder_streetname);
		mEt_units = (EditText) findViewById(R.id.et_postbuilder_units);
		mEt_years = (EditText) findViewById(R.id.et_postbuilder_years);
		
		//租賃資料
		mEt_Saleprice = (EditText) findViewById(R.id.et_postbuilder_saleprice);
		mEt_Vavantdate = (EditText) findViewById(R.id.et_postbuilder_Vacantdate);
		mEt_ContractPrice = (EditText) findViewById(R.id.et_postbuilder_ContractPrice);
		mEt_ContractExpiry = (EditText) findViewById(R.id.et_postbuilder_ContractExpiry);
		mEt_RentPrice= (EditText) findViewById(R.id.et_postbuilder_RentPrice);
		mEt_Taxdeduction = (EditText) findViewById(R.id.et_postbuilder_Taxdeduction);
		mEt_Maintenance = (EditText) findViewById(R.id.et_postbuilder_Maintenance);
		
		mCb_isinclude1 = (CheckBox) findViewById(R.id.checkBox_postbuild_isInclude1);
		mCb_isinclude2 = (CheckBox) findViewById(R.id.checkBox_postbuild_isInclude2);
		mCb_isinclude3 = (CheckBox) findViewById(R.id.checkBox_postbuild_isInclude3);
		mCb_isinclude4 = (CheckBox) findViewById(R.id.checkBox_postbuild_isInclude4);
		
		mCb_isVancant1 = (CheckBox) findViewById(R.id.checkBox_postbuild_isVacant1);
		mCb_isVancant2 = (CheckBox) findViewById(R.id.checkBox_postbuild_isVacant2);
		
		mCb_post_1= (CheckBox) findViewById(R.id.checkbox_postbuild_1);
		mCb_post_2 = (CheckBox) findViewById(R.id.checkbox_postbuild_2);
		mCb_post_3 = (CheckBox) findViewById(R.id.checkbox_postbuild_3);
		mCb_post_4 = (CheckBox) findViewById(R.id.checkbox_postbuild_4);
		mCb_post_5 = (CheckBox) findViewById(R.id.checkbox_postbuild_5);
		mCb_post_6 = (CheckBox) findViewById(R.id.checkbox_postbuild_6);
		mCb_post_7 = (CheckBox) findViewById(R.id.checkbox_postbuild_7);
		mCb_post_8 = (CheckBox) findViewById(R.id.checkbox_postbuild_8);
		mCb_post_9 = (CheckBox) findViewById(R.id.checkbox_postbuild_9);
		mCb_post_10 = (CheckBox) findViewById(R.id.checkbox_postbuild_10);
		mCb_post_11 = (CheckBox) findViewById(R.id.checkbox_postbuild_11);
		mCb_post_12= (CheckBox) findViewById(R.id.checkbox_postbuild_12);
		mCb_post_13 = (CheckBox) findViewById(R.id.checkbox_postbuild_13);
		mCb_post_14 = (CheckBox) findViewById(R.id.checkbox_postbuild_14);
		mCb_post_15 = (CheckBox) findViewById(R.id.checkbox_postbuild_15);
		mCb_post_16 = (CheckBox) findViewById(R.id.checkbox_postbuild_16);
		mCb_post_17 = (CheckBox) findViewById(R.id.checkbox_postbuild_17);
		mCb_post_18 = (CheckBox) findViewById(R.id.checkbox_postbuild_18);
		mCb_post_19 = (CheckBox) findViewById(R.id.checkbox_postbuild_19);
		mCb_post_20 = (CheckBox) findViewById(R.id.checkbox_postbuild_20);
		mCb_post_21 = (CheckBox) findViewById(R.id.checkbox_postbuild_21);
		mCb_post_22 = (CheckBox) findViewById(R.id.checkbox_postbuild_22);
		mCb_post_23 = (CheckBox) findViewById(R.id.checkbox_postbuild_23);
		
		//照片，視頻
		
		mCb_post_PhotoMark= (CheckBox) findViewById(R.id.checkBox_postbuild_PhotoMark);
		mEt_Remark = (EditText) findViewById(R.id.et_postbuilder_Remark);
		mEt_Video = (EditText) findViewById(R.id.et_postbuilder_video);
		
	}

	
	class Data{
		String  string_picurl;
		String  string_picname;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_postbuild_rentsale:

			Intent intent1=new Intent(FangPanActivity.this,ListviewActivity.class);
			intent1.putExtra("title", "租售类型");
			intent1.putExtra("id", 1);
			startActivityForResult(intent1, 0);
			break;
        case R.id.tv_postbuild_propertystyle:
        	Intent intent2=new Intent(FangPanActivity.this,ListviewActivity.class);
			intent2.putExtra("title", "物業種類");
			intent2.putExtra("id", 2);
			startActivityForResult(intent2, 0);
			break;
         case R.id.tv_postbuild_district:
        		Intent intent3=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent3.putExtra("title", "區域");
    			intent3.putExtra("id", 3);
    			startActivityForResult(intent3, 0);
	          break;
          case R.id.tv_postbuild_zone:
        	  
        	  if (mDistrict_Num==0) {
				new CustomerAlertDialog(FangPanActivity.this, "請選擇區域").show();
			}
        	  else {
        		  Intent intent4=new Intent(FangPanActivity.this,ListviewActivity.class);
        		  intent4.putExtra("title", "地區");
        		  intent4.putExtra("id", 4);
        		  intent4.putExtra("districtnum", mDistrict_Num);
        		  startActivityForResult(intent4, 0);
			}
	      break;
          case R.id.tv_postbuild_floor:
        		Intent intent5=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent5.putExtra("title", "樓層");
    			intent5.putExtra("id", 5);
    			startActivityForResult(intent5, 0);
  	         break;
          case R.id.tv_postbuild_livingroom:
      		Intent intent6=new Intent(FangPanActivity.this,ListviewActivity.class);
  			intent6.putExtra("title", "客廳");
  			intent6.putExtra("id", 6);
  			startActivityForResult(intent6, 0);
	         break;
          case R.id.tv_postbuild_diningroom:
        		Intent intent7=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent7.putExtra("title", "飯廳");
    			intent7.putExtra("id", 7);
    			startActivityForResult(intent7, 0);
  	         break;
          case R.id.tv_postbuild_bedroom:
        		Intent intent8=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent8.putExtra("title", "房間");
    			intent8.putExtra("id", 8);
    			startActivityForResult(intent8, 0);
  	         break;
          case R.id.tv_postbuild_flat:
        		Intent intent9=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent9.putExtra("title", "套房");
    			intent9.putExtra("id", 9);
    			startActivityForResult(intent9, 0);
  	         break;
          case R.id.tv_postbuild_kitchen:
        		Intent intent10=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent10.putExtra("title", "廚房");
    			intent10.putExtra("id", 10);
    			startActivityForResult(intent10, 0);
  	         break;
          case R.id.tv_postbuild_toilet:
        		Intent intent11=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent11.putExtra("title", "浴室");
    			intent11.putExtra("id", 11);
    			startActivityForResult(intent11, 0);
  	         break;
          case R.id.tv_postbuild_sitto:
        		Intent intent12=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent12.putExtra("title", "坐向");
    			intent12.putExtra("id", 12);
    			startActivityForResult(intent12, 0);
  	         break;
          case R.id.tv_postbuild_schoolnet:
        		Intent intent13=new Intent(FangPanActivity.this,ListviewActivity.class);
    			intent13.putExtra("title", "校網");
    			intent13.putExtra("id", 13);
    			startActivityForResult(intent13, 0);
  	         break;

		default:
			break;
		}
	}
	public  void btn_addpic(View v) {
		// TODO Auto-generated method stub
		mListviewNumList.add(1);
		mAdapter.notifyDataSetChanged();
	}
     //发布楼盘	
	public void btn_postbuild(View v) {
		// TODO Auto-generated method stub
		summit();
	}
	private void summit() {
		 RequestParams params = new RequestParams();
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(41);
        nameValuePairs.add(new BasicNameValuePair("MemberType", Bean.getMember_Type()));
        nameValuePairs.add(new BasicNameValuePair("MemberID",Bean.getMember_Id()));
        nameValuePairs.add(new BasicNameValuePair("RentSale", mRentsale_Num+""));
        nameValuePairs.add(new BasicNameValuePair("PropertyType", mPropertystyle_Num+""));
        nameValuePairs.add(new BasicNameValuePair("PropertyLocation", mDistrict_Num+""));
        nameValuePairs.add(new BasicNameValuePair("PropertyDistrict", mZone_Num+""));
        nameValuePairs.add(new BasicNameValuePair("Floor", mFloor_Num));
        nameValuePairs.add(new BasicNameValuePair("Livingroom", mLivingroom_String_Num));
			
       	 nameValuePairs.add(new BasicNameValuePair("Diningroom", mDinning_Num));
       	 nameValuePairs.add(new BasicNameValuePair("Bedroom", mBeding_String_Num));
       	 nameValuePairs.add(new BasicNameValuePair("Flat", mFlat_Num));
       	 nameValuePairs.add(new BasicNameValuePair("Kitchen", mKitchen_String_Num));
       	 nameValuePairs.add(new BasicNameValuePair("Toilet", mToilet_String_Num));
       	 nameValuePairs.add(new BasicNameValuePair("Sitto", mSitto_Num));
       	 nameValuePairs.add(new BasicNameValuePair("SchoolNet", mSchoolNum));

       	 nameValuePairs.add(new BasicNameValuePair("StreetName", mEt_streetname.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("BuildingName", mEt_buildname.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("SeatNo", mEt_seatno.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("Periods", mEt_periods.getText().toString()));
       	 
       	 nameValuePairs.add(new BasicNameValuePair("Developer", mEt_developer.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("Units", mEt_units.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("AreaGross", mEt_areagross.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("AreaNet", mEt_areanet.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("Years", mEt_years.getText().toString()));

       	 nameValuePairs.add(new BasicNameValuePair("SellingPrice", mEt_Saleprice.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("RentPrice", mEt_RentPrice.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("isVacant", 	(mCb_isVancant1.isChecked())?1+"":0+""));
       	 nameValuePairs.add(new BasicNameValuePair("Vacantdate", mEt_Vavantdate.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("isVacant2", 	(mCb_isVancant2.isChecked())?1+"":0+""));
       	 nameValuePairs.add(new BasicNameValuePair("ContractPrice", mEt_ContractPrice.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("ContractExpiry", mEt_ContractExpiry.getText().toString()));
       
       	 nameValuePairs.add(new BasicNameValuePair("isInclude1", (mCb_isinclude1.isChecked())?1+"":0+""));
       	 nameValuePairs.add(new BasicNameValuePair("isInclude2", (mCb_isinclude2.isChecked())?1+"":0+""));
       	 nameValuePairs.add(new BasicNameValuePair("isInclude3", (mCb_isinclude3.isChecked())?1+"":0+""));
       	 nameValuePairs.add(new BasicNameValuePair("isInclude4", (mCb_isinclude4.isChecked())?1+"":0+""));
       	 nameValuePairs.add(new BasicNameValuePair("Taxdeduction", mEt_Taxdeduction.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("Maintenance", mEt_Maintenance.getText().toString()));
         String view ="";
         String sets ="";
         String special ="";
       	 nameValuePairs.add(new BasicNameValuePair("Views", view));
       	 nameValuePairs.add(new BasicNameValuePair("Sets", sets));
       	 nameValuePairs.add(new BasicNameValuePair("Special", special));
       	 nameValuePairs.add(new BasicNameValuePair("Remark", mEt_Remark.getText().toString()));
       	 nameValuePairs.add(new BasicNameValuePair("Video", mEt_Video.getText().toString()));
  
       	 
        params.addBodyParameter(nameValuePairs);
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
       		Content.URL_POSTBUILD,
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
							  
							  if ("1".equals(code)) {
									Toast.makeText(FangPanActivity.this, "已成功送出放盤", Toast.LENGTH_SHORT).show();
									finish();
								}else if("0".equals(code)){
									Toast.makeText(FangPanActivity.this, msg, Toast.LENGTH_SHORT).show();
								}
							  else {
								new CustomerAlertDialog(FangPanActivity.this, "放盤失敗").show();
							}
							
						} catch (JSONException e) {
							new CustomerAlertDialog(FangPanActivity.this, "放盤失敗").show();	
						}
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                    	new CustomerAlertDialog(FangPanActivity.this, "放盤失敗").show();                    }
                });
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
		case 5:
			mTv_Floor.setText(arg2.getStringExtra("name")+"  ");
			mFloor_Num =arg2.getStringExtra("name");
			break;
		case 6:
			mTv_Livingroom.setText(arg2.getStringExtra("name")+"  ");
			mLivingroom_String_Num = arg2.getStringExtra("string_num");
			Log.e("mLivingroom_String_Num", ""+mLivingroom_String_Num);
			break;
		case 7:
			mTv_Dinningroom.setText(arg2.getStringExtra("name")+"  ");
			mDinning_Num =arg2.getStringExtra("name");
			break;
		case 8:
			mTv_Bedroom.setText(arg2.getStringExtra("name")+"  ");
			mBeding_String_Num = arg2.getStringExtra("string_num");
			Log.e("mBeding_String_Num", ""+mBeding_String_Num);
			break;
		case 9:
			mTv_Flat.setText(arg2.getStringExtra("name")+"  ");
			mFlat_Num =arg2.getStringExtra("name");
			break;
		case 10:
			mTv_Kitchen.setText(arg2.getStringExtra("name")+"  ");
			mKitchen_String_Num = arg2.getStringExtra("string_num");
			Log.e("mKitchen_String_Num", ""+mKitchen_String_Num);
			break;
		case 11:
			mTv_Toilet.setText(arg2.getStringExtra("name")+"  ");
			mToilet_String_Num = arg2.getStringExtra("string_num");
			Log.e("mToilet_String_Num", ""+mToilet_String_Num);
			break;	
		case 12:
			mTv_Sitto.setText(arg2.getStringExtra("name")+"  ");
			mSitto_Num =arg2.getStringExtra("name");
			break;
		case 13:
			mTv_Schoolnet.setText(arg2.getStringExtra("name")+"  ");
			mSchoolNum =arg2.getStringExtra("name");
			break;
		default:
			break;
		}
		 if (arg1 == Activity.RESULT_OK) {  
	            /**  
	             * 当选择的图片不为空的话，在获取到图片的途径  
	             */  
	            Uri uri = arg2.getData();  
	            Log.e("", "uri = " + uri);  
	            try {  
	                String[] pojo = { MediaStore.Images.Media.DATA };  
	                @SuppressWarnings("deprecation")
					Cursor cursor = managedQuery(uri, pojo, null, null, null);  
	                    ContentResolver cr = this.getContentResolver();  
	                    int colunm_index = cursor  
	                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);  
	                    cursor.moveToFirst();  
	                    String path = cursor.getString(colunm_index);  
	                   //添加到
	                    
	                  //  Toast.makeText(this, ""+path, Toast.LENGTH_SHORT).show();
	                    Log.e("path", ""+path);
	                    /*** 
	                     * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了， 
	                     * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以 
	                     */  
	                    if (path.endsWith(".jpg") || path.endsWith(".png")) {  
	                        picPath = path;  
	                        Bitmap bitmap = BitmapFactory.decodeStream(cr  
	                                .openInputStream(uri));  
	                        mPicList.add(picPath);
	                        mAdapter.notifyDataSetChanged();
	                    } else {  
	                        alert();  
	                    }  
	            } catch (Exception e) {  
	            	Log.e("Category", "Exception");
	            }  
	        }  
	}
	private void alert() {  
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")  
                .setMessage("您選擇的不是有效的圖片，")  
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int which) {  
                        picPath = null;  
                    }  
                }).create();  
        dialog.show();  
    }  

}
