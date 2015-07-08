package com.compass.hk.calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.more.ContactActivity;
import com.compass.hk.util.Content;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends FragmentActivity {

	private EditText mEt_mortpercent;
	private EditText mEt_Mortyear;
	private EditText mEt_Mortamount;
	private EditText mEt_Loantotal;
	private EditText mEt_FloorPrice;
	private TextWatcher watcher;
	private TextWatcher watcher1;
	private TextView mTv_totalrate;
	private TextView mTv_totalpay;
	private TextView mTv_monthamount;
	private TextView mTv_firstAmount;
	private TextView mTv_stampfee;
	private TextView mTv_middlefee;
	private TextView mTv_totalfee;
	private String string_Table1;
	private String string_Table2;
	private int price;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("按揭計算器");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		initUi();
	}
	private void initUi() {
		mEt_FloorPrice = (EditText) findViewById(R.id.et_calculator_floorprice);
		mEt_Loantotal = (EditText) findViewById(R.id.et_calculator_loantotal);
		mEt_Mortamount = (EditText) findViewById(R.id.et_calculator_mortamount);
		mEt_Mortyear = (EditText) findViewById(R.id.et_calculator_mortyear);
		mEt_mortpercent = (EditText) findViewById(R.id.et_calculator_mortpercent);
		
		mTv_firstAmount = (TextView) findViewById(R.id.tv_calculator_firstamount);
		mTv_monthamount = (TextView) findViewById(R.id.tv_calculator_monthamount);
		mTv_totalpay = (TextView) findViewById(R.id.tv_calculator_totalpay);
		mTv_totalrate = (TextView) findViewById(R.id.tv_calculator_totalrate);
		mTv_totalfee= (TextView) findViewById(R.id.tv_calculator_totalfee);
		mTv_middlefee = (TextView) findViewById(R.id.tv_calculator_middlefee);
		mTv_stampfee = (TextView) findViewById(R.id.tv_calculator_stampfee);
	        Intent intent = getIntent();
	        if (intent!=null) {
				price = intent.getIntExtra("price", 0);
				mEt_FloorPrice.setText(price+"0000");
				int price2=(int) (price*0.7);
				mEt_Loantotal.setText(price2+"0000");
			}
	        else {
	        	mEt_FloorPrice.setText("5000000");
	        	mEt_Loantotal.setText("3500000");
				
			}
		mEt_mortpercent.setText("70");
		mEt_Mortamount.setText("2.5");
		mEt_Mortyear.setText("20");
		
		
		watcher =new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
				
				// mEt_Loantotal.setText(""+percent*price);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Log.e("afterTextChanged", "enter");
				String string_floorprice = mEt_FloorPrice.getText().toString();
				String string_percent = mEt_mortpercent.getText().toString();
				if ("".equals(string_floorprice)||string_floorprice==null) {
					
					new CustomerAlertDialog(CalculatorActivity.this, "請輸入樓價").show();
					
				} else {
					if ("".equals(string_percent)||string_percent==null) {
						
						new CustomerAlertDialog(CalculatorActivity.this, "請輸入按揭成數").show();
					} else {
						float f1 = Float.valueOf(string_percent);
						if (f1<0.0||f1>100.0) {
							new CustomerAlertDialog(CalculatorActivity.this, "請輸入0到100的數字").show();
						}
						else {
							
							float percent = Float.valueOf(string_percent)/100;
							float price = Float.valueOf(string_floorprice);
							Double a =(double) (percent*price);
							Double  b   =  (double) ((Math.round(a*100))/100);
							mEt_Loantotal.setText(b+"");

							//mEt_Loantotal.setText(percent*price+"");
							
						}
					}
					
				}
				
				
			}
		};
	watcher1 =new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
				
				// mEt_Loantotal.setText(""+percent*price);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Log.e("afterTextChanged", "enter");
				String string_floorprice = mEt_FloorPrice.getText().toString();
				String string_percent = mEt_mortpercent.getText().toString();
				
				if ("".equals(string_floorprice)||string_floorprice==null) {
					
					
				} else {
					if ("".equals(string_percent)||string_percent==null) {
						
					} else {
						float f1 = Float.valueOf(string_percent);
						if (f1<0.0||f1>100.0) {
							new CustomerAlertDialog(CalculatorActivity.this, "請輸入0到100的數字").show();
						}
						else {
							
							float percent = Float.valueOf(string_percent)/100;
							float price = Float.valueOf(string_floorprice);
							Double a =(double) (percent*price);
							Double  b   =  (double) ((Math.round(a*100))/100);
							mEt_Loantotal.setText(b+"");
							
						}
					}
					
				}
			}
		};
		mEt_mortpercent.addTextChangedListener(watcher);
		mEt_FloorPrice.addTextChangedListener(watcher1);
	}
	public	 void btn_calculator(View v) {
		// TODO Auto-generated method stub
		String floorprice = mEt_FloorPrice.getText().toString();
		String loantotal= mEt_Loantotal.getText().toString();
		String mortamount= mEt_Mortamount.getText().toString();
		String mortpercent = mEt_mortpercent.getText().toString();
		String mortyear= mEt_Mortyear.getText().toString();
		if ("".equals(floorprice)||floorprice==null) {
			
			new CustomerAlertDialog(CalculatorActivity.this, "請輸入樓價").show();
			
		} else {
			if ("".equals(mortpercent)||mortpercent==null) {
				
				new CustomerAlertDialog(CalculatorActivity.this, "請輸入按揭成數").show();
			} else {
				float f1 = Float.valueOf(mortpercent);
				if (f1<0.0||f1>100.0) {
					new CustomerAlertDialog(CalculatorActivity.this, "按揭成數請輸入0到100的數字").show();
				}
				else {
					
					if ("".equals(mortamount)||mortamount==null) {
						new CustomerAlertDialog(CalculatorActivity.this, "請輸入年利率").show();
					} 
					else {
						float f2 = Float.valueOf(mortamount);
						if (f2<0.0||f2>100.0) {
							new CustomerAlertDialog(CalculatorActivity.this, "年利率請輸入0到100的數字").show();
						} else {
							if ("".equals(mortyear)||mortyear==null) {
								new CustomerAlertDialog(CalculatorActivity.this, "請輸入按揭年期").show();
							} else {
								summit(floorprice,mortamount,mortpercent,mortyear);
							}
						}
					}
				}
			}
		}
				
	}
	private void summit(String floorprice, String mortamount, String mortpercent, String mortyear) {
		// TODO Auto-generated method stub
		 RequestParams params = new RequestParams();
         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
         nameValuePairs.add(new BasicNameValuePair("TotalAmount", floorprice));
         nameValuePairs.add(new BasicNameValuePair("MortPercent", mortpercent));
         nameValuePairs.add(new BasicNameValuePair("MortAmount", mortamount));
         nameValuePairs.add(new BasicNameValuePair("MortYear", mortyear));
         params.addBodyParameter(nameValuePairs);
         HttpUtils http = new HttpUtils();
         http.send(HttpRequest.HttpMethod.POST,
        		 Content.URL_Calculate,
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
								  //解析json
								  parsing_Json(data);
								  
							}
							  else {
								
							}
							  
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                     }
                     @Override
                     public void onFailure(HttpException error, String msg) {
                     	Toast.makeText(CalculatorActivity.this, "未提交成功", Toast.LENGTH_SHORT).show();  
                     }
                 });

	
	}
	protected void parsing_Json(String data) {
		// TODO Auto-generated method stub
		JSONObject json=null;
		try {
			  json=new JSONObject(data);
			  String string_TotalPay= json.getString("TotalPay");
			  String string_TotalFee= json.getString("TotalFee");
			  String string_MiddleFee= json.getString("MiddleFee");
			  String string_FirstAmount= json.getString("FirstAmount");
			  String string_TotalRate= json.getString("TotalRate");
			  String string_MonthAmount= json.getString("MonthAmount");
			  String string_StampFee= json.getString("StampFee");
			  string_Table1 = json.getString("Table1");
			  string_Table2 = json.getString("Table2");
			  
			  
			  Log.e("parsing_Json", string_TotalFee);
			  Log.e("parsing_Json", string_MiddleFee);
			  Log.e("parsing_Json", string_FirstAmount);
			  Log.e("parsing_Json", string_TotalRate);
			  Log.e("parsing_Json", string_StampFee);
			  Log.e("parsing_Json", string_Table1);
			  Log.e("parsing_Json", string_Table2);
			  Log.e("parsing_Json", string_TotalPay);
			  Log.e("parsing_Json", string_MonthAmount);
			  
			  mTv_firstAmount.setText(string_FirstAmount);
			  mTv_monthamount.setText(string_MonthAmount);
			  mTv_totalpay.setText(string_TotalPay);
			  mTv_totalrate.setText(string_TotalRate);
			  mTv_totalfee.setText(string_TotalFee);
			  mTv_middlefee.setText(string_MiddleFee);
			  mTv_stampfee.setText(string_StampFee);
			  
			  
			  if ("".equals(string_Table1)||string_Table1==null) {
				
			}
			  else {
				findViewById(R.id.btn_table1).setVisibility(View.VISIBLE);
			}
			  if ("".equals(string_Table2)||string_Table2==null) {
					
				}
				  else {
					findViewById(R.id.btn_table2).setVisibility(View.VISIBLE);
				}
			  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void btn_table1(View v) {
		Intent intent=new Intent(this,TableActivity.class);
		intent.putExtra("table", string_Table1);
                 startActivity(intent);
	}
	public void btn_table2(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,TableActivity.class);
		intent.putExtra("table", string_Table2);
                 startActivity(intent);
	}

}
