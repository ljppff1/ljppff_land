package com.compass.hk.login_register;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.Tab1Activity;
import com.compass.hk.compass.OwnerActivity;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.hot.Frame_hot1;
import com.compass.hk.util.Bean;
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
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends FragmentActivity implements OnClickListener {
	private TextView mTv_Summit;
	private EditText mEt_Email;
	private EditText mEt_PWD;
	private RadioButton mRadiobtn_Type1;
	private RadioButton mRadiobtn_Type2;
	private String string_MemberType;
	private CheckBox mCb_password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_login);
		initUI();
		SharedPreferences sp = getSharedPreferences("password", MODE_PRIVATE);
		String email = sp.getString("email", "null");
		if ("null".equals(email)) {
			
		}
		else {
			mCb_password.setChecked(true);
			 mEt_Email.setText(email);
			 mEt_PWD.setText(sp.getString("password", "null"));
		}
        
        mTv_Summit.setOnClickListener(this);
	}
	private void initUI() {
		mRadiobtn_Type1 = (RadioButton) findViewById(R.id.RadioButton_login_type1);
        mRadiobtn_Type2 = (RadioButton) findViewById(R.id.RadioButton_login_type2);
        mRadiobtn_Type2 = (RadioButton) findViewById(R.id.RadioButton_login_type2);
        mCb_password = (CheckBox) findViewById(R.id.checkBox_password);
        
        mTv_Summit = (TextView) findViewById(R.id.textView_login_summit);
        mEt_Email = (EditText) findViewById(R.id.EditText_login_email);
        mEt_PWD = (EditText) findViewById(R.id.EditText_login_pwd);
	}
	private void summit() {
        String email = mEt_Email.getText().toString();
        String pwd = mEt_PWD.getText().toString();
        if ("".equals(email)||email==null) {
  		  new CustomerAlertDialog(LoginActivity.this, "Ոݔ�������]���]��").show();
  		}    
        else {
     	   if (isEmail(email)) {
				
     		   if ("".equals(pwd)||email==null) {
     			   new CustomerAlertDialog(LoginActivity.this, "Ոݔ���ܴa").show();
     		   }
     		   else {
     			   updown(email,pwd);
     		   }
			}
     	   else {
     		   new CustomerAlertDialog(LoginActivity.this, "�]���ַ�����_").show(); 
			}
		}
}
	public static boolean isEmail(String email){     
	     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	        Pattern p = Pattern.compile(str);     
	        Matcher m = p.matcher(email);     
	        return m.matches();     
	    }
private void updown(String email, String pwd) {
	SharedPreferences sp = getSharedPreferences("password", MODE_PRIVATE);
		   if (mCb_password.isChecked()) {
			sp.edit().putString("email", email).putString("password", pwd).commit();
		}
		   else {
			   sp.edit().putString("email", "null").commit();
		}
		if (mRadiobtn_Type1.isChecked()) {
			string_MemberType ="1";
		}
		if (mRadiobtn_Type2.isChecked()) {
			string_MemberType ="2";
		}

		// TODO Auto-generated method stub
		 RequestParams params = new RequestParams();
         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
         nameValuePairs.add(new BasicNameValuePair("MemberType", string_MemberType));
         nameValuePairs.add(new BasicNameValuePair("MemberEmail", email));
         nameValuePairs.add(new BasicNameValuePair("MemberPwd", pwd));
			
         params.addBodyParameter(nameValuePairs);
         HttpUtils http = new HttpUtils();
         http.send(HttpRequest.HttpMethod.POST,
                 "http://www.hk-compass.com/json/login.php",
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
							 JSONObject detail = result.getJSONObject("data");
								String telephone = detail.getString("MemberTel");
								String email = detail.getString("MemberEmail");
								String member_ID= detail.getString("MemberID");
								String  member_Name = detail.getString("MemberName");
								String  member_Type= detail.getString("MemberType");
								
								Log.e("setLoginTag", "333");
								Log.e("setLoginTag", email);
								Log.e("setLoginTag", member_ID);
								Log.e("setLoginTag", member_Name);
								Log.e("setLoginTag", member_Type);
								
							  String code = result.getString("code");
							  String msg = result.getString("msg");
							  String data = result.getString("data");
							  Log.e("code:", code);
							  Log.e("msg:", msg);
							  Log.e("data:", data);
							  if ("1".equals(code)) {
								  
									//�����䛠�B
									Bean.setLogined(true);
									Bean.setMember_Email(email);
									Bean.setMember_Tel(telephone);
									Bean.setMember_Id(member_ID);
									Bean.setMember_Name(member_Name);
									Bean.setMember_Type(member_Type);
								  
								  Toast.makeText(LoginActivity.this, "�ɹ����", Toast.LENGTH_SHORT).show();
								  Intent intent=new Intent(LoginActivity.this,Tab1Activity.class);
			                		intent.putExtra("tab_id", 2);
			                		startActivity(intent);
			                        LoginActivity.this.finish();
							}
							//  08-05 17:26:43.594: E/msg:(30505): Email does not exist

							  else {
								if ("Account has not been activated".equals(msg)) {
									Dialog dialog = new AlertDialog.Builder(LoginActivity.this).setTitle("��ʾ")  
							                .setMessage("ԓ������δ���Ո�����]���M�м��")  
							                .setPositiveButton("�_��", null  ).create();  
							                 dialog.show();  
								}
                                else if ("Error password".equals(msg)) {
                                	Dialog dialog = new AlertDialog.Builder(LoginActivity.this).setTitle("��ʾ")  
							                .setMessage("�ܴa����`��")  
							                .setPositiveButton("�_��", null  ).create();  
							                 dialog.show(); 
									}
                                else if ("Email does not exist".equals(msg)) {
                                	Dialog dialog = new AlertDialog.Builder(LoginActivity.this).setTitle("��ʾ")  
							                .setMessage("�Ñ������ڣ�")  
							                .setPositiveButton("�_��", null  ).create();  
							                 dialog.show(); 
								}
								else {
									Dialog dialog = new AlertDialog.Builder(LoginActivity.this).setTitle("��ʾ")  
							                .setMessage("������`��Ո���µ��")  
							                .setPositiveButton("�_��", null  ).create();  
							                 dialog.show(); 
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                     }
                     @Override
                     public void onFailure(HttpException error, String msg) {
                     //	Toast.makeText(ForgetPassword.this, "δ�l�ͳɹ���", Toast.LENGTH_SHORT).show();  
                    	 Log.e("error:", msg);
                    	 new Dialog_noInternet(LoginActivity.this).show();
                     }
                 });
	}
@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.textView_login_summit:
		  //summit  
        summit();
		break;

	default:
		break;
	}
	// TODO Auto-generated method stub
	
}
	
}
