package com.compass.hk.login_register;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.ListviewActivity;
import com.webdesign688.compass.R;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.util.Content;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Frame_register extends Fragment implements OnClickListener
{  

	private static String mString_Age;
	private View mLayout;
	private EditText mEt_name;
	private EditText mEt_password;
	private EditText mEt_passwordconfirm;
	private EditText mEt_email;
	private EditText mEt_emailconfirm;
	private EditText mEt_Telephone;
	private EditText mEt_Homecall;
	private RadioButton mRadiobtn_Type1;
	private RadioButton mRadiobtn_Type2;
	private RadioButton mRadiobtn_Man;
	private RadioButton mRadiobtn_Women;
	private static TextView mTv_Age;
	private  static TextView mTv_Slary;
	private static String mString_Slary;
	private static String mString_AgentArea1;
	private TextView mTv_Summit;
	private String string_MemberType;
	private String string_Sex;
	private String string_name;
	private String string_email;
	private String string_emailconfirm;
	private String string_password;
	private String string_passwordconfirm;
	private String string_telephone;
	private String string_homecall;
	private RadioGroup mrGroup;
	private static TextView mTv_AgentArea1;
	private static TextView mTv_AgentArea2;
	private static  TextView mTv_AgentArea3;
	private static String mString_DistrictId_1;
	private static String mString_AgentArea2;
	private static String mString_DistrictId_2;
	private static String mString_AgentArea3;
	private static String mString_DistrictId_3;
	private EditText mEt_CompanyIntro;
	private EditText mEt_CompanyWeb;
	private EditText mEt_CompanyName;
	private EditText mEt_CompanyLicenseNO;
	private EditText mEt_CompanyAdress;
	private EditText mEt_PersonallicenseNo;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frame_regiester, null);
                             initUi();
                        	 mLayout.findViewById(R.id.rela_incule).setVisibility(View.GONE);
                        	 
                             
		return mLayout ; 
	}
	private void initIncludeUi(){
		    mTv_AgentArea1 = (TextView) mLayout.findViewById(R.id.tv_register_AgentArea1);
		    mTv_AgentArea2 = (TextView) mLayout.findViewById(R.id.tv_register_AgentArea2);
		    mTv_AgentArea3 = (TextView) mLayout.findViewById(R.id.tv_register_AgentArea3);
		    mEt_CompanyAdress = (EditText) mLayout.findViewById(R.id.editText_register_CompanyAddress);
		    mEt_CompanyLicenseNO = (EditText) mLayout.findViewById(R.id.editText_register_CompanyLicenseNO);
		    mEt_PersonallicenseNo = (EditText) mLayout.findViewById(R.id.editText_register_PersonalLicenseNO);
		    
		    mEt_CompanyName = (EditText) mLayout.findViewById(R.id.editText_register_CompanyName);
		    mEt_CompanyWeb = (EditText) mLayout.findViewById(R.id.editText_register_CompanyWeb);
		    mEt_CompanyIntro = (EditText) mLayout.findViewById(R.id.editText_register_CompanyIntro);
		    Button button= (Button) mLayout.findViewById(R.id.button_register_selectpic);
		    mTv_AgentArea1.setOnClickListener(this);
		    mTv_AgentArea2.setOnClickListener(this); 
		    mTv_AgentArea3.setOnClickListener(this); 
		    button.setOnClickListener(this);
	}
	private void initUi() {
		// TODO Auto-generated method stub
		 mEt_name = (EditText) mLayout.findViewById(R.id.editText_register_name);	
         mEt_password = (EditText)mLayout.findViewById(R.id.editText_register_password);		
         mEt_passwordconfirm= (EditText)mLayout.findViewById(R.id.editText_register_passwordconfirm);
         mEt_email= (EditText)mLayout.findViewById(R.id.editText_register_email);
         mEt_emailconfirm= (EditText)mLayout.findViewById(R.id.editText_register_confirmemail);
         mEt_Telephone= (EditText)mLayout.findViewById(R.id.editText_telephone);
         mEt_Homecall= (EditText)mLayout.findViewById(R.id.editText_register_homecall);
         mRadiobtn_Type1 = (RadioButton) mLayout.findViewById(R.id.RadioButton_register_type1);
         mRadiobtn_Type2 = (RadioButton) mLayout.findViewById(R.id.RadioButton_register_type2);
         mRadiobtn_Man = (RadioButton) mLayout.findViewById(R.id.RadioButton_register_man);
         mRadiobtn_Women = (RadioButton) mLayout.findViewById(R.id.RadioButton_register_women);
         mrGroup = (RadioGroup) mLayout.findViewById(R.id.RadioGroup08);
         
         mTv_Age = (TextView) mLayout.findViewById(R.id.tv_register_age);
         mTv_Slary = (TextView) mLayout.findViewById(R.id.tv_register_slary);
         mTv_Summit = (TextView) mLayout.findViewById(R.id.tv_register_summit);
         
         
         
         mTv_Age.setOnClickListener(this);
         mTv_Slary.setOnClickListener(this);
         mTv_Summit.setOnClickListener(this);
         
         
         //listen radio
         mrGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { 
             @Override 
             public void onCheckedChanged(RadioGroup group, int checkedId) { 
                 // TODO Auto-generated method stub 
                 if(checkedId==mRadiobtn_Type1.getId()){ 
                	 
                	 mLayout.findViewById(R.id.rela_incule).setVisibility(View.GONE);
                	 
                 }else if(checkedId==mRadiobtn_Type2.getId()){ 
                	 mLayout.findViewById(R.id.rela_incule).setVisibility(View.VISIBLE);
                	 initIncludeUi();
                 } 
             } 
         }); 
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==1) {
			String string_Age = data.getStringExtra("age");
			Log.e("onActivityResult", string_Age);
		}
		
	}
	public static boolean isEmail(String email){     
	     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	        Pattern p = Pattern.compile(str);     
	        Matcher m = p.matcher(email);     
	        return m.matches();     
	    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_register_age:
			Intent intent=new Intent(getActivity(),AgeActivity.class);
			startActivityForResult(intent, 0);
			break;
         case R.id.tv_register_slary:
        	 Intent intent2=new Intent(getActivity(),SlaryActivity.class);
 			startActivityForResult(intent2, 0);
			break;
         case R.id.tv_register_summit:
        	 //summit
        	 isCheck();
			break;
         case R.id.tv_register_AgentArea1:
        	 Intent intent3=new Intent(getActivity(),ListviewActivity.class);
        	 intent3.putExtra("title", "地區");
        	 intent3.putExtra("id", 15);
 			startActivityForResult(intent3, 0);
			break;
         case R.id.tv_register_AgentArea2:
        	 Intent intent4=new Intent(getActivity(),ListviewActivity.class);
        	 intent4.putExtra("title", "地區");
        	 intent4.putExtra("id", 16);
 			startActivityForResult(intent4, 0);
			break;
         case R.id.tv_register_AgentArea3:
        	 Intent intent5=new Intent(getActivity(),ListviewActivity.class);
        	 intent5.putExtra("title", "地區");
        	 intent5.putExtra("id", 17);
 			startActivityForResult(intent5, 0);
			break;
		default:
			break;
		}
		
	}
	private void isCheck() {
		// TODO Auto-generated method stub
		if (mRadiobtn_Type1.isChecked()) {
			string_MemberType ="1";
		}
		if (mRadiobtn_Type2.isChecked()) {
			string_MemberType ="2";
		}
		if (mRadiobtn_Man.isChecked()) {
			string_Sex="男";
		}
		if (mRadiobtn_Women.isChecked()) {
			string_Sex="女";
		}
         gettext();
	}
	private void gettext() {
		string_name = mEt_name.getText().toString();
		string_email = mEt_email.getText().toString();
		string_emailconfirm = mEt_emailconfirm.getText().toString();
		string_password = mEt_password.getText().toString();
		string_passwordconfirm = mEt_passwordconfirm.getText().toString();
		string_telephone = mEt_Telephone.getText().toString();
		string_homecall = mEt_Homecall.getText().toString();
		if ("".equals(string_name)||string_name==null) {
		  new CustomerAlertDialog(getActivity(), "請輸入姓名").show();
		}
		else {
			if ("".equals(string_email)||string_email==null) {
				 new CustomerAlertDialog(getActivity(), "請輸入郵箱").show();
			}
			else {
				boolean isEmail = isEmail(string_email);
				if (!isEmail) {
					new CustomerAlertDialog(getActivity(), "郵箱地址不正確").show();
				}
				else {
					if ("".equals(string_emailconfirm)||string_emailconfirm==null) {
						new CustomerAlertDialog(getActivity(), "請再次輸入郵箱").show();
					}
					else {
						if (string_emailconfirm.equals(string_email)) {
							
							if ("".equals(string_password)||string_password==null) {
								new CustomerAlertDialog(getActivity(), "請輸入密碼").show();
							}
							else {
								if ("".equals(string_passwordconfirm)||string_passwordconfirm==null) {
									new CustomerAlertDialog(getActivity(), "請再次輸入密碼").show();
								}
								else {
									if (string_passwordconfirm.equals(string_password)) {
										if ("".equals(string_telephone)||string_telephone==null) {
											new CustomerAlertDialog(getActivity(), "請輸入聯絡電話").show();
										}
										else {
										//summmit
											summit();
										}
									}
									else {
										new CustomerAlertDialog(getActivity(), "兩次密碼不一樣").show();
									}
								}
							}
						}
						else {
							new CustomerAlertDialog(getActivity(), "兩次郵箱地址不一樣").show();
						}
					}
				}
			}
		}
		
	}
	private void summit() {
		// TODO Auto-generated method stub
		 RequestParams params = new RequestParams();
         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
         nameValuePairs.add(new BasicNameValuePair("MemberName", string_name));
         nameValuePairs.add(new BasicNameValuePair("MemberType", string_MemberType));
         nameValuePairs.add(new BasicNameValuePair("MemberEmail", string_email));
         nameValuePairs.add(new BasicNameValuePair("MemberPwd", string_password));
         nameValuePairs.add(new BasicNameValuePair("MemberTel_a", string_telephone));
         nameValuePairs.add(new BasicNameValuePair("MemberTel", string_telephone));
         nameValuePairs.add(new BasicNameValuePair("MemberPhone_a", string_telephone));
         nameValuePairs.add(new BasicNameValuePair("MemberPhone", string_telephone));
			
        	 nameValuePairs.add(new BasicNameValuePair("PersonalLicenseNO", "111"));
        	 nameValuePairs.add(new BasicNameValuePair("CompanyLicenseNO", "111"));
        	 nameValuePairs.add(new BasicNameValuePair("CompanyName", "111"));
        	 nameValuePairs.add(new BasicNameValuePair("CompanyAddress", "111"));
        	 nameValuePairs.add(new BasicNameValuePair("CompanyWeb", "111.com"));
        	 nameValuePairs.add(new BasicNameValuePair("CompanyIntro", "111"));
        	 
         params.addBodyParameter(nameValuePairs);
         HttpUtils http = new HttpUtils();
         http.send(HttpRequest.HttpMethod.POST,
                 Content.URL_REGISTER,
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
								  new CustomerAlertDialog(getActivity(), "您的登記資料已經成功提交，請返回您的郵箱 :"
							  +string_email+"，確認您的帳戶即可完成註冊。Outlook 與 hotmail 用戶，系統郵件可能被放置於垃圾郵箱，請注意查看。").show();
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
                     //	Toast.makeText(ForgetPassword.this, "未發送成功，", Toast.LENGTH_SHORT).show();  
                     }
                 });

	}
	public static void setAge(String string) {
		// TODO Auto-generated method stub
		mString_Age=string;
		mTv_Age.setText(mString_Age+"   ");
		
	}
	public static void setSlary(String string) {
		// TODO Auto-generated method stub
		mString_Slary=string;
		mTv_Slary.setText(mString_Slary+"   ");
	}
	public static void setAgentArea1(String string) {
		// TODO Auto-generated method stub
		mString_AgentArea1=string;
		mTv_AgentArea1.setText(mString_AgentArea1+"  ");
		
	}
	public static void setDistrictID1(String districtID) {
		// TODO Auto-generated method stub
		mString_DistrictId_1=districtID;
		Log.e("setDistrictID1", mString_DistrictId_1);
	} 
	public static void setAgentArea2(String string) {
		// TODO Auto-generated method stub
		mString_AgentArea2=string;
		mTv_AgentArea2.setText(mString_AgentArea2+"  ");
		
	}
	public static void setDistrictID2(String districtID) {
		// TODO Auto-generated method stub
		mString_DistrictId_2=districtID;
		Log.e("setDistrictID2", mString_DistrictId_2);
	} 
	public static void setAgentArea3(String string) {
		// TODO Auto-generated method stub
		mString_AgentArea3=string;
		mTv_AgentArea3.setText(mString_AgentArea3+"  ");
		
	}
	public static void setDistrictID3(String districtID) {
		// TODO Auto-generated method stub
		mString_DistrictId_3=districtID;
		Log.e("setDistrictID3", mString_DistrictId_3);
	} 
}
