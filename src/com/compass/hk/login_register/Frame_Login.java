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
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;






public class Frame_Login extends Fragment implements  OnClickListener
{  

	private TextView mTv_Summit;
	private EditText mEt_Email;
	private EditText mEt_PWD;
	private RadioButton mRadiobtn_Type1;
	private RadioButton mRadiobtn_Type2;
	private String string_MemberType;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
              View layout = inflater.inflate(R.layout.frame_login, null);	
              
              mRadiobtn_Type1 = (RadioButton) layout.findViewById(R.id.RadioButton_login_type1);
              mRadiobtn_Type2 = (RadioButton) layout.findViewById(R.id.RadioButton_login_type2);
              
              mTv_Summit = (TextView) layout.findViewById(R.id.textView_login_summit);
              mEt_Email = (EditText) layout.findViewById(R.id.EditText_login_email);
              mEt_PWD = (EditText) layout.findViewById(R.id.EditText_login_pwd);
              
              mTv_Summit.setOnClickListener(this);
             
		return layout ; 
	}
	private void summit() {
		           String email = mEt_Email.getText().toString();
		           String pwd = mEt_PWD.getText().toString();
		           if ("".equals(email)||email==null) {
		     		  new CustomerAlertDialog(getActivity(), "請輸入您的註冊郵箱").show();
		     		}    
		           else {
		        	   if (isEmail(email)) {
						
		        		   if ("".equals(pwd)||email==null) {
		        			   new CustomerAlertDialog(getActivity(), "請輸入密碼").show();
		        		   }
		        		   else {
		        			   updown(email,pwd);
		        		   }
					}
		        	   else {
		        		   new CustomerAlertDialog(getActivity(), "郵箱地址不正確").show(); 
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
		
		
		startActivity(new Intent(getActivity(),OwnerActivity.class));
		getActivity().finish();
		
		
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
                 Content.URL_LOGIN,
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
                     	JSONObject result;
                     	try {
							 result =new JSONObject(responseInfo.result);
							 JSONObject detail = result.getJSONObject("data");
								String telephone = detail.getString("MemberTel");
								String email = detail.getString("MemberEmail");
								String member_ID= detail.getString("MemberID");
								String  member_Name = detail.getString("MemberName");
								String  member_Type= detail.getString("MemberType");
								
								Log.e("setLoginTag", "333");
							/*	Log.e("setLoginTag", email);
								Log.e("setLoginTag", member_ID);
								Log.e("setLoginTag", member_Name);
								Log.e("setLoginTag", member_Type);*/
								
							  String code = result.getString("code");
							  String msg = result.getString("msg");
							  String data = result.getString("data");
							  Log.e("code:", code);
							  Log.e("msg:", msg);
							  Log.e("data:", data);
							  if ("1".equals(code)) {
									//08-22 16:58:44.922: E/data:(19873): 
									//{"MemberTel":"852&nbsp;47886887","MemberEmail":"531539451@qq.com","MemberID":"22129","MemberName":"333","MemberType":"1"}
										/* JSONObject detail = result.getJSONObject("data");
										String telephone = detail.getString("MemberTel");
										String email = detail.getString("MemberEmail");
										String member_ID= detail.getString("MemberID");
										String  member_Name = detail.getString("MemberName");
										String  member_Type= detail.getString("MemberType");
										
										Log.e("setLoginTag", telephone);
										Log.e("setLoginTag", email);
										Log.e("setLoginTag", member_ID);
										Log.e("setLoginTag", member_Name);
										Log.e("setLoginTag", member_Type);*/
										//保存登錄狀態
										Bean.setLogined(true);
										Bean.setMember_Email(email);
										Bean.setMember_Tel(telephone);
										Bean.setMember_Id(member_ID);
										Bean.setMember_Name(member_Name);
										Bean.setMember_Type(member_Type);
										
								
								  Intent intent=new Intent(getActivity(),Tab1Activity.class);
			                		intent.putExtra("tab_id", 2);
			                		startActivity(intent);
			                		Toast.makeText(getActivity(), "成功登錄", Toast.LENGTH_SHORT).show();
			                        getActivity().finish();
							}
							//  08-05 17:26:43.594: E/msg:(30505): Email does not exist

							  else {
								if ("Account has not been activated".equals(msg)) {
									Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示")  
							                .setMessage("該帳戶尚未激活，請查收郵箱進行激活。")  
							                .setPositiveButton("確定", null  ).create();  
							                 dialog.show();  
								}
                                else if ("Error password".equals(msg)) {
                                	Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示")  
							                .setMessage("密碼填寫有誤。")  
							                .setPositiveButton("確定", null  ).create();  
							                 dialog.show(); 
									}
                                else if ("Email does not exist".equals(msg)) {
                                	Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示")  
							                .setMessage("用戶不存在！")  
							                .setPositiveButton("確定", null  ).create();  
							                 dialog.show(); 
								}
								else {
									Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示")  
							                .setMessage("登錄有誤，請重新登錄")  
							                .setPositiveButton("確定", null  ).create();  
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
                     //	Toast.makeText(ForgetPassword.this, "未發送成功，", Toast.LENGTH_SHORT).show();  
                    	 Log.e("error:", msg);
                    	 new Dialog_noInternet(getActivity()).show();
                     }
                 });
	}
	protected void setLoginTag(String data) {
		
		//08-22 16:58:44.922: E/data:(19873): 
		//{"MemberTel":"852&nbsp;47886887","MemberEmail":"531539451@qq.com","MemberID":"22129","MemberName":"333","MemberType":"1"}
		JSONObject json;
		try {
			json = new JSONObject(data);
			String telephone = json.getString("MemberTel");
			String email = json.getString("MemberEmail");
			String member_ID= json.getString("MemberID");
			String  member_Name = json.getString("MemberName");
			String  member_Type= json.getString("MemberType");
			
			Log.e("setLoginTag", telephone);
			Log.e("setLoginTag", email);
			Log.e("setLoginTag", member_ID);
			Log.e("setLoginTag", member_Name);
			Log.e("setLoginTag", member_Type);
			//保存登錄狀態
			Bean.setLogined(true);
			Bean.setMember_Email(email);
			Bean.setMember_Tel(telephone);
			Bean.setMember_Id(member_ID);
			Bean.setMember_Name(member_Name);
			Bean.setMember_Type(member_Type);
			
		} catch (JSONException e) {
			Log.e("", "jsonerror");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
