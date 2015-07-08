package com.compass.hk.rent;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.calculator.CalculatorActivity;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.CustomerAlertDialog.mOnClickListener;
import com.compass.hk.dialog.Dialog_Calling;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.login_register.Login_Regiester;
import com.compass.hk.rent.RentActivity.Data;
import com.compass.hk.search.SearchActivity;
import com.compass.hk.search.SearchResultActivity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.UILApplication;
import com.compass.hk.util.getJson;
import com.compass.hk.yishou.YiShouActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
/**
 * ljppff楼盘出售详情
 * @author liujun
 *
 */
public class RentDetailActivity extends FragmentActivity implements mOnClickListener {
	private PopupWindow mPop;
	private ViewPager mViewPager;
	private boolean isClick1;
	private boolean isClick2;
	private boolean isClick3;
	private boolean isClick4;
	private String mString_ID;
	public ArrayList<String> mPicList=new ArrayList<String>();
	public ArrayList<String> mPicNameList=new ArrayList<String>();
	public ArrayList<Integer> mRadioList=new ArrayList<Integer>();
	public TextView mTextView_Name;
	private TextView mTv_PicName;
	private String mString_Type;
	private String mString_MemberId;
	private ImageButton mButton_Addfavor;
	private TextView mTv_Saleprice;
	private String sellingPrice;
	private TextView mTv_Rentprice;
	protected ImageLoader imageLoader = ImageLoader.getInstance();

	private RelativeLayout rela_rentdetail_bottom2;
	private RelativeLayout rela_rentdetail_bottom1;
	private TextView tv_frag_companyname;
	private TextView tv_frag_personname;
	private TextView tv_frag_lience;
	private TextView tv_frag_telephone1;
	private TextView tv_frag_telephone2;
	private ImageView ib_agence_call;
	private ImageView iv_frag_personphoto;
	private TextView tv_frag_personname2;
	private TextView tv_frag_telephone1a;
	private TextView tv_frag_telephone2b;
	private ImageView ib_agence_call2;
	private DisplayImageOptions options;
	private ProgressBar progressBar_tab_rent;
	private String ContactTel;
	private String ContactMobile;
	private String FavFlag;
	private Button btn_register_summit21;
	private String string_name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rent_detail);
		
		mTextView_Name= (TextView) findViewById(R.id.tv_rentdetail_picname);
		initUi();
		
		Intent intent = getIntent();
		 string_name = intent.getStringExtra("name");
		TextView tv_Title= (TextView) findViewById(R.id.tv_rentdetail_title);
		tv_Title.setText(string_name);
		 getID();
		 initRadio();
	    downLoad();
	    //downloadpic
	    downLoadPic();
	    initOtherAdd();
	}
	private void initOtherAdd() {
		View view = findViewById(R.id.frame_rent_plane);
		View view1 = findViewById(R.id.frame_rent_map);
		if (view!=null) {
			view.setVisibility(View.GONE);
		}
		if (view1!=null) {
			view1.setVisibility(View.GONE);
		}
		findViewById(R.id.frame_rent_document).setVisibility(View.VISIBLE);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Rent1    frame_Title= new Frame_Rent1();
		ft.add(R.id.frame_rent_document, frame_Title);
		ft.commit();
		
	}
	private void initUi() {
		mButton_Addfavor = (ImageButton) findViewById(R.id.imageButton_rent_addfavor);
		mTv_Saleprice = (TextView) findViewById(R.id.tv_rent1_weight_saleprice); 
		mTv_Rentprice =(TextView)findViewById(R.id.tv_rent1_weight_saleprice1);
		btn_register_summit21 =(Button)findViewById(R.id.btn_register_summit21);
	}
		//加入心水樓盤
		public  void btn_addfavor(View v) {
			// TODO Auto-generated method stub
			if (Bean.isLogined()) {
				if(!TextUtils.isEmpty(FavFlag)&&FavFlag.equals("0")){
				   mString_Type= Bean.getMember_Type();
			       mString_MemberId= Bean.getMember_Id();
			       Log.e("btn_addfavor",	 mString_Type);
			       Log.e("btn_addfavor",	 mString_MemberId);
			       Log.e("btn_addfavor",	 mString_ID);
			       
				 RequestParams params = new RequestParams();
		         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
		         nameValuePairs.add(new BasicNameValuePair("MemberType", mString_Type));
		         nameValuePairs.add(new BasicNameValuePair("MemberID", mString_MemberId));
		         nameValuePairs.add(new BasicNameValuePair("HouseID", mString_ID));
					
		         params.addBodyParameter(nameValuePairs);
		         HttpUtils http = new HttpUtils();
		         http.send(HttpRequest.HttpMethod.POST,
		                 Content.URL_FAVORADD,
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
										Toast.makeText(RentDetailActivity.this, "已添加到心水樓盤", Toast.LENGTH_SHORT).show();
										mButton_Addfavor.setBackgroundResource(R.drawable.icon_heart_add);
									}
									  else {
										  Toast.makeText(RentDetailActivity.this, "未添加成功", Toast.LENGTH_SHORT).show();
									}

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                     }
		                     @Override
		                     public void onFailure(HttpException error, String msg) {
		                    	 Log.e("error:", msg);
		                     }
		                 });
				}else if(!TextUtils.isEmpty(FavFlag)&&FavFlag.equals("1")){
					//删除心
					Toast.makeText(RentDetailActivity.this, "此樓盤已添加到心水樓盤", Toast.LENGTH_SHORT).show();
				}
			} else {
				CustomerAlertDialog customerAlertDialog = new CustomerAlertDialog(RentDetailActivity.this, "請先登錄");
				customerAlertDialog.show();
				customerAlertDialog.setOnclickListener_on(this);
			}

		}
		//发邮件
				public  void btn_sendEmail() {
						 RequestParams params = new RequestParams();
				         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
				         nameValuePairs.add(new BasicNameValuePair("ID", mString_ID));
				         nameValuePairs.add(new BasicNameValuePair("ContactName", mEtName.getText().toString()));
				         nameValuePairs.add(new BasicNameValuePair("ContactTel", mEtTell.getText().toString()));
				         nameValuePairs.add(new BasicNameValuePair("ContactEmail", mEtEamil.getText().toString()));
				         nameValuePairs.add(new BasicNameValuePair("ContactMsg", mEtMsg.getText().toString()));
							
				         params.addBodyParameter(nameValuePairs);
				         HttpUtils http = new HttpUtils();
				         http.send(HttpRequest.HttpMethod.POST,
				                 Content.URL_SENDEMAIL,
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
												Toast.makeText(RentDetailActivity.this, "發送郵件成功", Toast.LENGTH_SHORT).show();
												if (mPop != null && mPop.isShowing()) {
													mPop.dismiss();
													mPop = null;
												}

											}
											  else {
												  Toast.makeText(RentDetailActivity.this, "發送郵件失敗", Toast.LENGTH_SHORT).show();
											}

										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
				                     }
				                     @Override
				                     public void onFailure(HttpException error, String msg) {
				                    	 
				                     }
				                 });
}
	private void downLoadPic() {
		// TODO Auto-generated method stub
		new DownLoadPicTask().execute(Content.URL_PROPERTYPHOTOS+mString_ID);
	}
	private void downLoad() {
  
		
	//	new DownLoadAsyTask().execute(Content.URL_PROPERTYDETAIL+mString_ID);
		summit();
	}
	
	private void summit() {
		 RequestParams params = new RequestParams();
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
        nameValuePairs.add(new BasicNameValuePair("ID", mString_ID));
        nameValuePairs.add(new BasicNameValuePair("MemberType", Bean.getMember_Type()));
        nameValuePairs.add(new BasicNameValuePair("MemberID", Bean.getMember_Id()));
       	 
        params.addBodyParameter(nameValuePairs);
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
       		Content.URL_PROPERTYDETAIL1,
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
							//  String data = result.getString("data");
							  
							  
							  if ("1".equals(code)) {
									 JSONObject data = result.getJSONObject("data");
									 String Name = data.getString("Name");
									 String SeatNo = data.getString("SeatNo");
									 String AreaGross = data.getString("AreaGross");
									 String AreaNet = data.getString("AreaNet");
									 String RentSale = data.getString("RentSale");
									  sellingPrice = data.getString("SellingPrice");
									 String RentPrice = data.getString("RentPrice");
									 String Monthly = data.getString("Monthly");
									 String AddTime = data.getString("AddTime");
									 String UpdateTime = data.getString("UpdateTime");
									 String PropertyType = data.getString("PropertyType");
									 String BuildingNumbers = data.getString("BuildingNumbers");
									 String Region = data.getString("Region");
									 String District = data.getString("District");
									 String StreetName = data.getString("StreetName");
									 String Periods = data.getString("Periods");
									 String Floor = data.getString("Floor");
									 String Units = data.getString("Units");
									 String Diningroom = data.getString("Diningroom");
									 String Bedroom = data.getString("Bedroom");
									 String Flat = data.getString("Flat");
									 String LivingRoom = data.getString("LivingRoom");
									 String Toilet = data.getString("Toilet");
									 String SchoolNet = data.getString("SchoolNet");
									 String Sitto = data.getString("Sitto");
									 String Developer = data.getString("Developer");
									 String isVacant1 = data.getString("isVacant1");
									 String isVacant2 = data.getString("isVacant2");
									 String isInclude1 = data.getString("isInclude1");
									 String isInclude2 = data.getString("isInclude2");
									 String Years = data.getString("Years");
									 String Views = data.getString("Views");
									 String Sets = data.getString("Sets");
									 String Special = data.getString("Special");
									 String Remark = data.getString("Remark");
									 String Video = data.getString("Video");
									// String Contents = data.getString("Contents");
									 //广告，contactType 1 2 0 3种
									 String ContactType = data.getString("ContactType");
									 String ContactName = data.getString("ContactName");
									  ContactTel = data.getString("ContactTel");
									  ContactMobile = data.getString("ContactMobile");
									 String LicenseNO = data.getString("LicenseNO");
									 String CompanyName = data.getString("CompanyName");
									 String CompayLicense = data.getString("CompayLicense");
									 String CompayIntro = data.getString("CompayIntro");
									 String CompayLogo = data.getString("CompayLogo");
									  FavFlag =data.getString("FavFlag");
									  if(!TextUtils.isEmpty(FavFlag)&&FavFlag.equals("1")){
											mButton_Addfavor.setBackgroundResource(R.drawable.icon_heart_add);
									  }
									 initUI();
									 btn_register_summit21.setText(string_name);
									 if(!TextUtils.isEmpty(ContactType)){
										 if(ContactType.equals("2")){
											 progressBar_tab_rent.setVisibility(View.GONE);
											 rela_rentdetail_bottom1.setVisibility(View.VISIBLE);
											 rela_rentdetail_bottom2.setVisibility(View.GONE);
											  tv_frag_companyname.setText(CompanyName);
											  tv_frag_personname.setText(ContactName);
											  tv_frag_lience.setText(CompayLicense);
											  tv_frag_telephone1.setText(ContactTel);
											  tv_frag_telephone2 .setText(ContactMobile);
												//UIL
											   initImageLoaderOptions();
												imageLoader.displayImage(CompayLogo,
														iv_frag_personphoto, options);
												ib_agence_call.setOnClickListener(listener);
										 }else if(ContactType.equals("0")||ContactType.equals("1")){
											 progressBar_tab_rent.setVisibility(View.GONE);
											 rela_rentdetail_bottom1.setVisibility(View.GONE);
											 rela_rentdetail_bottom2.setVisibility(View.VISIBLE);
											  tv_frag_personname2 .setText(ContactName);
											  tv_frag_telephone1a .setText(ContactTel);
											  tv_frag_telephone2b .setText(ContactMobile);
											  ib_agence_call2.setOnClickListener(listener);
										 }
									 }

									 
									 
									 //settext
									 TextView tv_Areagross = (TextView) findViewById(R.id.tv_rent1_weight_areagross);
									 TextView tv_Areanet = (TextView) findViewById(R.id.tv_rent1_weight_areanet);
									 TextView tv_Areanet2 = (TextView) findViewById(R.id.tv_rent1_weight_areanet2);
									 TextView tv_Areanet1 = (TextView) findViewById(R.id.tv_rent1_weight_areanet1);
									 TextView tv_Month = (TextView) findViewById(R.id.tv_rent1_weight_month);
									 TextView tv_room = (TextView) findViewById(R.id.tv_roomtoilat);
									 if(TextUtils.isEmpty(sellingPrice)||sellingPrice.equals("0")){
										 mTv_Saleprice.setText("    :HK$ -萬    ");
									 }else{
									 mTv_Saleprice.setText("    :HK$"+sellingPrice+" 萬    ");
									 }
									 if(TextUtils.isEmpty(RentPrice)||RentPrice.equals("0")){
										 mTv_Rentprice.setText("    :HK$ -萬    ");
									 }else{
										 mTv_Rentprice.setText(":HK$"+RentPrice);
									 }
									 tv_Areagross.setText("建築面積:  "+AreaGross+"(呎)");
									 tv_Areanet.setText("實用面積:  "+AreaNet+"(呎)");
									 tv_Areanet2.setText("   :"+AreaNet+"呎");
									 tv_Areanet1.setText("   :"+AreaGross+"呎");
									 StringBuilder stringBuilder=new StringBuilder();
									 
									 if (Bedroom!=null&&!"".equals(Bedroom)) {
										 stringBuilder.append(Integer.valueOf(Bedroom)+"房");
									}
									 if (LivingRoom!=null&&!"".equals(LivingRoom)) {
										 stringBuilder.append(Integer.valueOf(LivingRoom)+"廳");
									 }
									 if (Toilet!=null&&!"".equals(Toilet)) {
										 stringBuilder.append(Integer.valueOf(Toilet)+"廁");
									 }
									 if(!TextUtils.isEmpty(stringBuilder.toString())){
										 tv_room.setText(stringBuilder.toString());
										 tv_room.setText(":"+Bedroom+"房"+LivingRoom+"廳"+Toilet+"廁");

									 }
									 
									 tv_Month.setText("每月供款:	 "+Html.fromHtml(Monthly));
									 
									
								 }
							  else {
									new AlertInfoDialog(RentDetailActivity.this).show();
							}
							
						} catch (JSONException e) {
							new AlertInfoDialog(RentDetailActivity.this).show();
						}
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                    	new AlertInfoDialog(RentDetailActivity.this).show();
                    }
                });
	}

	
	public  void btn_calculator(View v) {
		// TODO Auto-generated method stub
     
		Intent intent = new Intent(this,CalculatorActivity.class);
		if (sellingPrice!=null) {
			int  price = Integer.valueOf(sellingPrice);
			intent.putExtra("price", price);
		}
		
		startActivity(intent);
		finish();
		
	}
	private void getID() {
        Bean   bean=new Bean();		
        mString_ID = bean.getRentID();
        UILApplication application = (UILApplication)getApplication();
		   application.setHotDetail_ID(mString_ID);
       
}
	private void initRadio() {
		// TODO Auto-generated method stub
		 for (int i = 0; i <20; i++) {
			mRadioList.add(R.id.rent_radiobtn_1);
			mRadioList.add(R.id.rent_radiobtn_2);
			mRadioList.add(R.id.rent_radiobtn_3);
			mRadioList.add(R.id.rent_radiobtn_4);
			mRadioList.add(R.id.rent_radiobtn_5);
			mRadioList.add(R.id.rent_radiobtn_6);
			mRadioList.add(R.id.rent_radiobtn_7);
			mRadioList.add(R.id.rent_radiobtn_8);
			mRadioList.add(R.id.rent_radiobtn_9);
			mRadioList.add(R.id.rent_radiobtn_10);
			mRadioList.add(R.id.rent_radiobtn_11);
			mRadioList.add(R.id.rent_radiobtn_12);
			mRadioList.add(R.id.rent_radiobtn_13);
			mRadioList.add(R.id.rent_radiobtn_14);
			mRadioList.add(R.id.rent_radiobtn_15);
			mRadioList.add(R.id.rent_radiobtn_16);
			mRadioList.add(R.id.rent_radiobtn_17);
			mRadioList.add(R.id.rent_radiobtn_18);
			mRadioList.add(R.id.rent_radiobtn_19);
			mRadioList.add(R.id.rent_radiobtn_20);
		}
		 
	}

	class DownLoadPicTask extends AsyncTask<String, Void, String>{  
		
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
						 
						String string_PhotoName = jsonObject2.getString("PhotoName");
						String string_PhotoUr = jsonObject2.getString("PhotoUrl");
						mPicList.add(string_PhotoUr);
						mPicNameList.add(string_PhotoName);
						Log.e("string_PhotoUr", string_PhotoUr);
					}
					  
					  //显示
					  findViewById(R.id.tv_rent_detail_referpic).setVisibility(View.INVISIBLE);
					  
					  if (mPicNameList.size()>0) {
						mTv_PicName = (TextView) findViewById(R.id.tv_rent_picname);
						mTv_PicName.setText(mPicNameList.get(0));
					}
					  else {
						  mTv_PicName.setText(" ");
					}
					  for (int j = 0; j < mPicNameList.size(); j++) {
							 int   id = mRadioList.get(j);
								RadioButton radioButton = (RadioButton) findViewById(id);
								radioButton.setVisibility(View.VISIBLE);
							
						}
					  initViewPage();
				}
				 else {
					new CustomerAlertDialog(RentDetailActivity.this,"暫無相關物業圖片").show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(RentDetailActivity.this).show();
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	//所有数据详情
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
					 String SeatNo = data.getString("SeatNo");
					 String AreaGross = data.getString("AreaGross");
					 String AreaNet = data.getString("AreaNet");
					 String RentSale = data.getString("RentSale");
					  sellingPrice = data.getString("SellingPrice");
					 String RentPrice = data.getString("RentPrice");
					 String Monthly = data.getString("Monthly");
					 String AddTime = data.getString("AddTime");
					 String UpdateTime = data.getString("UpdateTime");
					 String PropertyType = data.getString("PropertyType");
					 String BuildingNumbers = data.getString("BuildingNumbers");
					 String Region = data.getString("Region");
					 String District = data.getString("District");
					 String StreetName = data.getString("StreetName");
					 String Periods = data.getString("Periods");
					 String Floor = data.getString("Floor");
					 String Units = data.getString("Units");
					 String Diningroom = data.getString("Diningroom");
					 String Bedroom = data.getString("Bedroom");
					 String Flat = data.getString("Flat");
					 String LivingRoom = data.getString("LivingRoom");
					 String Toilet = data.getString("Toilet");
					 String SchoolNet = data.getString("SchoolNet");
					 String Sitto = data.getString("Sitto");
					 String Developer = data.getString("Developer");
					 String isVacant1 = data.getString("isVacant1");
					 String isVacant2 = data.getString("isVacant2");
					 String isInclude1 = data.getString("isInclude1");
					 String isInclude2 = data.getString("isInclude2");
					 String Years = data.getString("Years");
					 String Views = data.getString("Views");
					 String Sets = data.getString("Sets");
					 String Special = data.getString("Special");
					 String Remark = data.getString("Remark");
					 String Video = data.getString("Video");
					// String Contents = data.getString("Contents");
					 //广告，contactType 1 2 0 3种
					 String ContactType = data.getString("ContactType");
					 String ContactName = data.getString("ContactName");
					  ContactTel = data.getString("ContactTel");
					  ContactMobile = data.getString("ContactMobile");
					 String LicenseNO = data.getString("LicenseNO");
					 String CompanyName = data.getString("CompanyName");
					 String CompayLicense = data.getString("CompayLicense");
					 String CompayIntro = data.getString("CompayIntro");
					 String CompayLogo = data.getString("CompayLogo");
					  FavFlag =data.getString("FavFlag");
					  if(!TextUtils.isEmpty(FavFlag)&&FavFlag.equals("1")){
							mButton_Addfavor.setBackgroundResource(R.drawable.icon_heart_add);
					  }
					 initUI();
					 
					 if(!TextUtils.isEmpty(ContactType)){
						 if(ContactType.equals("2")){
							 progressBar_tab_rent.setVisibility(View.GONE);
							 rela_rentdetail_bottom1.setVisibility(View.VISIBLE);
							 rela_rentdetail_bottom2.setVisibility(View.GONE);
							  tv_frag_companyname.setText(CompanyName);
							  tv_frag_personname.setText(ContactName);
							  tv_frag_lience.setText(CompayLicense);
							  tv_frag_telephone1.setText(ContactTel);
							  tv_frag_telephone2 .setText(ContactMobile);
								//UIL
							   initImageLoaderOptions();
								imageLoader.displayImage(CompayLogo,
										iv_frag_personphoto, options);
								ib_agence_call.setOnClickListener(listener);
						 }else if(ContactType.equals("0")||ContactType.equals("1")){
							 progressBar_tab_rent.setVisibility(View.GONE);
							 rela_rentdetail_bottom1.setVisibility(View.GONE);
							 rela_rentdetail_bottom2.setVisibility(View.VISIBLE);
							  tv_frag_personname2 .setText(ContactName);
							  tv_frag_telephone1a .setText(ContactTel);
							  tv_frag_telephone2b .setText(ContactMobile);
							  ib_agence_call2.setOnClickListener(listener);
						 }
					 }

					 
					 
					 //settext
					 TextView tv_Areagross = (TextView) findViewById(R.id.tv_rent1_weight_areagross);
					 TextView tv_Areanet = (TextView) findViewById(R.id.tv_rent1_weight_areanet);
					 TextView tv_Areanet2 = (TextView) findViewById(R.id.tv_rent1_weight_areanet2);
					 TextView tv_Areanet1 = (TextView) findViewById(R.id.tv_rent1_weight_areanet1);
					 TextView tv_Month = (TextView) findViewById(R.id.tv_rent1_weight_month);
					 TextView tv_room = (TextView) findViewById(R.id.tv_roomtoilat);
					 if(TextUtils.isEmpty(sellingPrice)||sellingPrice.equals("0")){
						 mTv_Saleprice.setText("    HK$ -萬    ");
					 }else{
					 mTv_Saleprice.setText("    HK$"+sellingPrice+" 萬    ");
					 }
					 mTv_Rentprice.setText("HK$"+RentPrice);
					 if(TextUtils.isEmpty(RentPrice)||RentPrice.equals("0")){
						 mTv_Rentprice.setText("    HK$ -萬    ");
					 }else{
						 mTv_Rentprice.setText("HK$"+RentPrice);
					 }

					 tv_Areagross.setText("建築面積:  "+AreaGross+"(呎)");
					 tv_Areanet.setText("實用面積:  "+AreaNet+"(呎)");
					 tv_Areanet2.setText(" :"+AreaNet+"呎");
					 tv_Areanet1.setText(" :"+AreaGross+"呎");
					 tv_room.setText("   :"+Bedroom+"房"+LivingRoom+"廳"+Toilet+"廁");

					 tv_Month.setText("每月供款:	 "+Html.fromHtml(Monthly));
					 
					
				 }
				 else {
					// layout.findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
					new AlertInfoDialog(RentDetailActivity.this).show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(RentDetailActivity.this).show();
			//	e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	public  void btn_back(View v) {
		// TODO Auto-generated method stub
       finish();
	}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.ib_agence_call:
				showDialog();
 			break;
			case R.id.ib_agence_call2:
				showDialog();
				break;
			case R.id.mTVw1Id:
				if (mPop != null && mPop.isShowing()) {
					mPop.dismiss();
					mPop = null;
				}
				break;
			case R.id.mBTW1next:
				if(TextUtils.isEmpty(mEtEamil.getText().toString())){
					Toast.makeText(getApplicationContext(), "請填寫電郵", 0).show();
				}else if(TextUtils.isEmpty(mEtMsg.getText().toString())){
					Toast.makeText(getApplicationContext(), "請填寫留言", 0).show();
				}else{
					btn_sendEmail();
				}
				break;
			case R.id.mBTgocancel:
				if (mPop != null && mPop.isShowing()) {
					mPop.dismiss();
					mPop = null;
				}
				break;
			default:
				break;
			}
		}
	};
	

	private View layout;
	private TextView mTVw1Id;
	private EditText mEtName;
	private EditText mEtTell;
	private EditText mEtEamil;
	private EditText mEtMsg;
	private Button mBTgocancel;
	private Button mBTgosure;	
	public void btn_sendEmail(View v){
		showDialog1();
	}
	private void showDialog1() {
			layout = View
					.inflate(RentDetailActivity.this, R.layout.window2, null);
			layout.setFocusable(true); // 这个很重要
			layout.setFocusableInTouchMode(true);

		mPop = new PopupWindow(layout, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT, true);
		mPop.setFocusable(true);

		mPop.showAtLocation(
				RentDetailActivity.this.findViewById(R.id.relativeLayout1rentdetail),
				Gravity.CENTER, 0, 0);// 在屏幕顶部|居右，带偏移
		mEtName =(EditText)layout.findViewById(R.id.mEtName);
		mEtTell =(EditText)layout.findViewById(R.id.mEtTell);
		mEtEamil =(EditText)layout.findViewById(R.id.mEtEamil);
		mEtMsg =(EditText)layout.findViewById(R.id.mEtMsg);
		
		mBTgocancel =(Button)layout.findViewById(R.id.mBTgocancel);
		mBTgosure =(Button)layout.findViewById(R.id.mBTW1next);
		mBTgocancel.setOnClickListener(listener);
		mBTgosure.setOnClickListener(listener);
		mTVw1Id = (TextView) layout.findViewById(R.id.mTVw1Id);
		mTVw1Id.setOnClickListener(listener);

		layout.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				switch (keyCode) {
				case KeyEvent.KEYCODE_BACK:
					if (mPop != null && mPop.isShowing()) {
						mPop.dismiss();
						mPop = null;

						return true;
					}
					break;
				default:
					break;
				}
				return false;
			}
		});
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

	}


	public void showDialog(){
		Dialog_Calling dialog_Calling = new Dialog_Calling(RentDetailActivity.this, "撥打以下哪個電話？");
		dialog_Calling.setOnclickListener_on(new com.compass.hk.dialog.Dialog_Calling.mOnClickListener() {
		@Override
		public void onCall2_Clicklistener() {
			if (ContactMobile==null||"null".equals(ContactMobile)) {
			}
			else {
				Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+ContactMobile));
			    startActivity(intent);
			}
		}
		@Override
		public void onCall1_Clicklistener() {
			 if (ContactTel==null||"null".equals(ContactTel)) {
			      }
			 else {
				 Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+ContactTel));
				  startActivity(intent);
			}
		}
	});
	dialog_Calling.show();
	}
	private void initImageLoaderOptions() {
		options = new DisplayImageOptions.Builder()
		         .showStubImage(R.drawable.ic_empty)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.cacheInMemory()
				.cacheOnDisc().displayer(new FadeInBitmapDisplayer(2000))
				.bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	public void initUI() {
		  rela_rentdetail_bottom1 =(RelativeLayout)findViewById(R.id.rela_rentdetail_bottom1);
		  tv_frag_companyname =(TextView)findViewById(R.id.tv_frag_companyname);
		  tv_frag_personname =(TextView)findViewById(R.id.tv_frag_personname);
		  tv_frag_lience =(TextView)findViewById(R.id.tv_frag_lience);
		  tv_frag_telephone1 =(TextView)findViewById(R.id.tv_frag_telephone1);
		  tv_frag_telephone2 =(TextView)findViewById(R.id.tv_frag_telephone2);
		  ib_agence_call =(ImageView)findViewById(R.id.ib_agence_call);
		  iv_frag_personphoto =(ImageView)findViewById(R.id.iv_frag_personphoto);
		  
		  rela_rentdetail_bottom2 =(RelativeLayout)findViewById(R.id.rela_rentdetail_bottom2);
		  tv_frag_personname2 =(TextView)findViewById(R.id.tv_frag_personname2);
		  tv_frag_telephone1a =(TextView)findViewById(R.id.tv_frag_telephone1a);
		  tv_frag_telephone2b =(TextView)findViewById(R.id.tv_frag_telephone2b);
		  ib_agence_call2 =(ImageView)findViewById(R.id.ib_agence_call2);
		  progressBar_tab_rent =(ProgressBar)findViewById(R.id.progressBar_tab_rent);
	}
	public  void btn_home(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this,MainActivity.class));
		finish();
	}
	//物业资料
	public  void btn_document(View v) {
		// TODO Auto-generated method stub
			if (isClick1) {
			findViewById(R.id.frame_rent_document).setVisibility(View.GONE);
		}
		else {
			View view = findViewById(R.id.frame_rent_plane);
			View view1 = findViewById(R.id.frame_rent_map);
			if (view!=null) {
				view.setVisibility(View.GONE);
			}
			if (view1!=null) {
				view1.setVisibility(View.GONE);
			}
			findViewById(R.id.frame_rent_document).setVisibility(View.VISIBLE);
			FragmentManager sfm = getSupportFragmentManager();
			FragmentTransaction ft = sfm.beginTransaction();
			Frame_Rent1    frame_Title= new Frame_Rent1();
			ft.add(R.id.frame_rent_document, frame_Title);
			ft.commit();
		}
			isClick1=!isClick1;
	}
	
	public  void btn_map(View v) {
		// TODO Auto-generated method stub
		if (isClick3) {
			findViewById(R.id.frame_rent_map).setVisibility(View.GONE);
		}
		else {
			//View view = findViewById(R.id.frame_rent_document);
			View view1 = findViewById(R.id.frame_rent_plane);
		//	if (view!=null) {
		//		view.setVisibility(View.GONE);
		//	}
			if (view1!=null) {
				view1.setVisibility(View.GONE);
			}
			
			findViewById(R.id.frame_rent_map).setVisibility(View.VISIBLE);
			FragmentManager sfm = getSupportFragmentManager();
			FragmentTransaction ft = sfm.beginTransaction();
			Frame_rent_map    frame_Title= new Frame_rent_map();
			ft.add(R.id.frame_rent_map, frame_Title);
			ft.commit();
		}
			isClick3=!isClick3;
	}
	public  void btn_plane(View v) {
		// TODO Auto-generated method stub
					if (isClick2) {
					findViewById(R.id.frame_rent_plane).setVisibility(View.GONE);
				}
				else {
				//	View view = findViewById(R.id.frame_rent_document);
					View view1 = findViewById(R.id.frame_rent_map);
					//if (view!=null) {
				//		view.setVisibility(View.GONE);
				//	}
					if (view1!=null) {
						view1.setVisibility(View.GONE);
					}
					findViewById(R.id.frame_rent_plane).setVisibility(View.VISIBLE);
					FragmentManager sfm = getSupportFragmentManager();
					FragmentTransaction ft = sfm.beginTransaction();
					Frame_rent_plane    frame_Title= new Frame_rent_plane();
					ft.add(R.id.frame_rent_plane, frame_Title);
					ft.commit();
				}
					isClick2=!isClick2;
	}
	// TODO Auto-generated method stub
	private void initViewPage() {
		mViewPager = (ViewPager) findViewById(R.id.pager_rentpic);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPicList.size();
		}
		@Override
		public Fragment getItem(int arg0) {
		Frame_viewpage_rent  frame=new Frame_viewpage_rent();
		frame.setUrl(mPicList.get(arg0));
		frame.setContent(mPicNameList.get(arg0));
			return frame ;
		}
	});
      mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				Log.e("mViewPager", ""+position);
				int   id = mRadioList.get(position);
				RadioButton radioButton = (RadioButton) findViewById(id);
				radioButton.setChecked(true);
				mTv_PicName.setText(mPicNameList.get(position));
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
}
	@Override
	public void onBtnClicklistener() {
		startActivity(new Intent(this,Login_Regiester.class));
		
	}
}

