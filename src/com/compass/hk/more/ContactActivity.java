package com.compass.hk.more;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.CustomerAlertDialog.mOnClickListener;
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
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ContactActivity extends FragmentActivity implements mOnClickListener{

	private EditText mEt_Content;
	private EditText mEt_Email;
	private EditText mEt_Name;
	private EditText mEt_Telephone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("聯絡我們");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		
		initUi();
	}

	private void initUi() {
		mEt_Content = (EditText) findViewById(R.id.et_contact_content);
		mEt_Email = (EditText) findViewById(R.id.et_contact_email);
		mEt_Name = (EditText) findViewById(R.id.et_contact_name);
		mEt_Telephone = (EditText) findViewById(R.id.et_contact_telephone);
	}
	public static boolean isEmail(String email){     
	     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	        Pattern p = Pattern.compile(str);     
	        Matcher m = p.matcher(email);     
	        return m.matches();     
	    }
	public void btn_sent(View v) {
		// TODO Auto-generated method stub
               String  content= mEt_Content.getText().toString();
               String telephone= mEt_Telephone.getText().toString();
               String  name= mEt_Name.getText().toString();
               String email = mEt_Email.getText().toString();
               
               if ("".equals(name)||name==null) {
				new  CustomerAlertDialog(ContactActivity.this, "請輸入姓名").show();
			}else{
				if ("".equals(telephone)||telephone==null) {
					new  CustomerAlertDialog(ContactActivity.this, "請輸入電話").show();
				}
				else {
					if ("".equals(email)||email==null) {
						new  CustomerAlertDialog(ContactActivity.this, "請輸入郵箱").show();
					}
					else {
						if (isEmail(email)) {
							if ("".equals(content)||content==null) {
								
								new  CustomerAlertDialog(ContactActivity.this, "請輸入內容").show();
							}
							else {
								//summit
								summit(content,name,telephone,email);
								
							}
							
						} else {
							new  CustomerAlertDialog(ContactActivity.this, "郵箱格式不正確").show();
						}
					}
				}
			}
               
	}

	private void summit(String content, String name, String telephone,
			String email) {

		// TODO Auto-generated method stub
		 RequestParams params = new RequestParams();
         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
         nameValuePairs.add(new BasicNameValuePair("CName", "ddd"));
         nameValuePairs.add(new BasicNameValuePair("CTel", telephone));
         nameValuePairs.add(new BasicNameValuePair("CEmail", email));
         nameValuePairs.add(new BasicNameValuePair("CMessage", content));
         params.addBodyParameter(nameValuePairs);
         HttpUtils http = new HttpUtils();
         http.send(HttpRequest.HttpMethod.POST,
        		 Content.URL_ContactUs,
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
								  Toast.makeText(ContactActivity.this, "成功送出", Toast.LENGTH_SHORT).show();  
								  finish();
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
                     	Toast.makeText(ContactActivity.this, "未發送成功", Toast.LENGTH_SHORT).show();  
                     }
                 });

	}


	@Override
	public void onBtnClicklistener() {
		// TODO Auto-generated method stub
		finish();
	}
}
