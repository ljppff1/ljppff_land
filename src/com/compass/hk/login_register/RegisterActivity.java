package com.compass.hk.login_register;

import com.webdesign688.compass.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.ListviewActivity;
import com.compass.hk.Tab1Activity;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
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

public class RegisterActivity extends Activity implements OnClickListener{
	private static String mString_Age;
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
	
	private String picPath = null;  
	private File file;
	private TextView mTv_picth;
	private String mString_Picth;
	private boolean isPersonnal=true;
	private String string_companyadress;
	private String string_personallicenseNo;
	private String string_companylicenseNo;
	private String string_companyName;
	private String string_companyWeb;
	private String string_companyIntro;
	private String age1;
	private String slary1;
	private String DistrictID1;
	private String DistrictID2;
	private String DistrictID3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_regiester);
		initUi();
		findViewById(R.id.rela_incule).setVisibility(View.GONE);
	}
	private void initUi() {
		// TODO Auto-generated method stub
		 mEt_name = (EditText) findViewById(R.id.editText_register_name);	
         mEt_password = (EditText)findViewById(R.id.editText_register_password);		
         mEt_passwordconfirm= (EditText)findViewById(R.id.editText_register_passwordconfirm);
         mEt_email= (EditText)findViewById(R.id.editText_register_email);
         mEt_emailconfirm= (EditText)findViewById(R.id.editText_register_confirmemail);
         mEt_Telephone= (EditText)findViewById(R.id.editText_telephone);
         mEt_Homecall= (EditText)findViewById(R.id.editText_register_homecall);
         mRadiobtn_Type1 = (RadioButton) findViewById(R.id.RadioButton_register_type1);
         mRadiobtn_Type2 = (RadioButton) findViewById(R.id.RadioButton_register_type2);
         mRadiobtn_Man = (RadioButton) findViewById(R.id.RadioButton_register_man);
         mRadiobtn_Women = (RadioButton) findViewById(R.id.RadioButton_register_women);
         mrGroup = (RadioGroup) findViewById(R.id.RadioGroup08);
         
         mTv_Age = (TextView) findViewById(R.id.tv_register_age);
         mTv_Slary = (TextView) findViewById(R.id.tv_register_slary);
         mTv_Summit = (TextView) findViewById(R.id.tv_register_summit);
         mTv_picth = (TextView) findViewById(R.id.tv_register_picpath);
         
         mTv_Age.setOnClickListener(this);
         mTv_Slary.setOnClickListener(this);
         mTv_Summit.setOnClickListener(this);
         
         
         //listen radio
         mrGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { 
             @Override 
             public void onCheckedChanged(RadioGroup group, int checkedId) { 
                 // TODO Auto-generated method stub 
                 if(checkedId==mRadiobtn_Type1.getId()){ 
                	 isPersonnal=true;
                	 findViewById(R.id.rela_incule).setVisibility(View.GONE);
                	 
                 }else if(checkedId==mRadiobtn_Type2.getId()){ 
                	 isPersonnal=false;
                	 findViewById(R.id.rela_incule).setVisibility(View.VISIBLE);
                	 initIncludeUi();
                 } 
             } 
         }); 
	}
	private void initIncludeUi(){
	    mTv_AgentArea1 = (TextView) findViewById(R.id.tv_register_AgentArea1);
	    mTv_AgentArea2 = (TextView) findViewById(R.id.tv_register_AgentArea2);
	    mTv_AgentArea3 = (TextView) findViewById(R.id.tv_register_AgentArea3);
	    mEt_CompanyAdress = (EditText) findViewById(R.id.editText_register_CompanyAddress);
	    mEt_CompanyLicenseNO = (EditText) findViewById(R.id.editText_register_CompanyLicenseNO);
	    mEt_PersonallicenseNo = (EditText) findViewById(R.id.editText_register_PersonalLicenseNO);
	    
	    mEt_CompanyName = (EditText) findViewById(R.id.editText_register_CompanyName);
	    mEt_CompanyWeb = (EditText) findViewById(R.id.editText_register_CompanyWeb);
	    mEt_CompanyIntro = (EditText) findViewById(R.id.editText_register_CompanyIntro);
	    Button button= (Button) findViewById(R.id.button_register_selectpic);
	    mTv_AgentArea1.setOnClickListener(this);
	    mTv_AgentArea2.setOnClickListener(this); 
	    mTv_AgentArea3.setOnClickListener(this); 
	    button.setOnClickListener(this);
}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/*if (requestCode==1) {
			String string_Age = data.getStringExtra("age");
			Log.e("onActivityResult", string_Age);
		}*/
		 if (resultCode == Activity.RESULT_OK) {  
	            /**  
	             * 当选择的图片不为空的话，在获取到图片的途径  
	             */  
	            Uri uri = data.getData();  
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
	                  //  Toast.makeText(this, ""+path, Toast.LENGTH_SHORT).show();
	                    Log.e("path", ""+path);
	                    mTv_picth.setText(path);
	                    mString_Picth=path;
	                    /*** 
	                     * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了， 
	                     * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以 
	                     */  
	                    if (path.endsWith(".jpg") || path.endsWith(".png")) {  
	                        picPath = path;  
	                        Bitmap bitmap = BitmapFactory.decodeStream(cr  
	                                .openInputStream(uri));  
	                       // myListView.notifyChange();
	                    } else {  
	                      //  alert();  
	                    }  
	            } catch (Exception e) {  
	            	Log.e("Category", "Exception");
	            }  
	        }
		 if(resultCode==1){
		  switch (requestCode) {
		case 2:
			String  age =	data.getStringExtra("age");
			  age1 =	data.getStringExtra("age1");
			mTv_Age.setText(age);

			break;
		case 3:
			String  slary =	data.getStringExtra("slary");
			  slary1 =	data.getStringExtra("slary1");
			mTv_Slary.setText(slary);

			break;
		case 4:
			String  AgentArea1 =	data.getStringExtra("AgentArea1");
			DistrictID1 =	data.getStringExtra("DistrictID1");
			mTv_AgentArea1.setText(AgentArea1);
			
			break;
		case 5:
			String  AgentArea2 =	data.getStringExtra("AgentArea2");
			DistrictID2 =	data.getStringExtra("DistrictID2");
			mTv_AgentArea2.setText(AgentArea2);
			
			break;
		case 6:
			String  AgentArea3 =	data.getStringExtra("AgentArea3");
			DistrictID3 =	data.getStringExtra("DistrictID3");
			mTv_AgentArea3.setText(AgentArea3);
			
			break;
			
		default:
			break;
		}
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
			Intent intent=new Intent(RegisterActivity.this,AgeActivity.class);
			startActivityForResult(intent, 2);
			break;
         case R.id.tv_register_slary:
        	 Intent intent2=new Intent(RegisterActivity.this,SlaryActivity.class);
 			startActivityForResult(intent2, 3);
			break;
         case R.id.tv_register_summit:
        	 //summit
        	 isCheck();
			break;
         case R.id.tv_register_AgentArea1:
        	 Intent intent3=new Intent(RegisterActivity.this,ListviewActivity.class);
        	 intent3.putExtra("title", "地區");
        	 intent3.putExtra("id", 15);
 			startActivityForResult(intent3, 4);
			break;
         case R.id.tv_register_AgentArea2:
        	 Intent intent4=new Intent(RegisterActivity.this,ListviewActivity.class);
        	 intent4.putExtra("title", "地區");
        	 intent4.putExtra("id", 16);
 			startActivityForResult(intent4, 5);
			break;
         case R.id.tv_register_AgentArea3:
        	 Intent intent5=new Intent(RegisterActivity.this,ListviewActivity.class);
        	 intent5.putExtra("title", "地區");
        	 intent5.putExtra("id", 17);
 			startActivityForResult(intent5, 6);
			break;
         case R.id.button_register_selectpic:
        	 //summit
        	 /*** 
		         * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 
		         */  
				  Intent i = new Intent(
						                          Intent.ACTION_PICK,
						                          android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						                  startActivityForResult(i, 1);
			
			break;
		default:
			break;
		}
		
	}
	private void isCheck() {
		// TODO Auto-generated method stub
		if (mRadiobtn_Type1.isChecked()) {
			string_MemberType ="1";
			Log.e("string_MemberType1", ""+string_MemberType);
		}
		if (mRadiobtn_Type2.isChecked()) {
			string_MemberType ="2";
			Log.e("string_MemberType2", ""+string_MemberType);
		}
		if (mRadiobtn_Man.isChecked()) {
			string_Sex="男";
			Log.e("string_Sex", ""+string_Sex);
		}
		if (mRadiobtn_Women.isChecked()) {
			string_Sex="女";
			Log.e("string_Sex", ""+string_Sex);
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
		  new CustomerAlertDialog(RegisterActivity.this, "請輸入姓名").show();
		}
		else {
			if ("".equals(string_email)||string_email==null) {
				 new CustomerAlertDialog(RegisterActivity.this, "請輸入郵箱").show();
			}
			else {
				boolean isEmail = isEmail(string_email);
				if (!isEmail) {
					new CustomerAlertDialog(RegisterActivity.this, "郵箱地址不正確").show();
				}
				else {
					if ("".equals(string_emailconfirm)||string_emailconfirm==null) {
						new CustomerAlertDialog(RegisterActivity.this, "請再次輸入郵箱").show();
					}
					else {
						if (string_emailconfirm.equals(string_email)) {
							
							if ("".equals(string_password)||string_password==null) {
								new CustomerAlertDialog(RegisterActivity.this, "請輸入密碼").show();
							}
							else {
								if ("".equals(string_passwordconfirm)||string_passwordconfirm==null) {
									new CustomerAlertDialog(RegisterActivity.this, "請再次輸入密碼").show();
								}
								else {
									if (string_passwordconfirm.equals(string_password)) {
										if ("".equals(string_telephone)||string_telephone==null) {
											new CustomerAlertDialog(RegisterActivity.this, "請輸入聯絡電話").show();
										}
										else {
									
											getAgenttext();
										}
									}
									else {
										new CustomerAlertDialog(RegisterActivity.this, "兩次密碼不一樣").show();
									}
								}
							}
						}
						else {
							new CustomerAlertDialog(RegisterActivity.this, "兩次郵箱地址不一樣").show();
						}
					}
				}
			}
		}
		
	}
	private void getAgenttext() {
		if (!isPersonnal) {
			string_companyadress= mEt_CompanyAdress.getText().toString();
			string_personallicenseNo= mEt_PersonallicenseNo.getText().toString();
			string_companylicenseNo= mEt_CompanyLicenseNO.getText().toString();
			string_companyName= mEt_CompanyName.getText().toString();
			string_companyWeb= mEt_CompanyWeb.getText().toString();
			string_companyIntro= mEt_CompanyIntro.getText().toString();
			if ("".equals(string_personallicenseNo)||string_personallicenseNo==null) {
				new CustomerAlertDialog(RegisterActivity.this, "请输入個人代理牌照").show();
			}
			else {
				if ("".equals(string_companylicenseNo)||string_companylicenseNo==null) {
					new CustomerAlertDialog(RegisterActivity.this, "请输入公司經紀牌照").show();
				}
				else {
					if ("".equals(string_companyadress)||string_companyadress==null) {
						new CustomerAlertDialog(RegisterActivity.this, "请输入公司地址").show();
					}
					else {
						if ("".equals(string_companyWeb)||string_companyWeb==null) {
							new CustomerAlertDialog(RegisterActivity.this, "请输入公司網址").show();
						}
						else {
							summit();
						}
					}
				}
			}
		}
		else {
			summit();
		}
	
		// TODO Auto-generated method stub
	
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
		
         nameValuePairs.add(new BasicNameValuePair("MemberAge", age1));
         nameValuePairs.add(new BasicNameValuePair("MemberIncome", slary1));
         nameValuePairs.add(new BasicNameValuePair("AgentArea1", DistrictID1));
         nameValuePairs.add(new BasicNameValuePair("AgentArea2", DistrictID2));
         nameValuePairs.add(new BasicNameValuePair("AgentArea3", DistrictID3));
         
         
        	 nameValuePairs.add(new BasicNameValuePair("PersonalLicenseNO", string_personallicenseNo));
        	 nameValuePairs.add(new BasicNameValuePair("CompanyLicenseNO", string_companylicenseNo));
       	     nameValuePairs.add(new BasicNameValuePair("CompanyName", string_companyName));
        	 nameValuePairs.add(new BasicNameValuePair("CompanyAddress", string_companyadress));
        	  nameValuePairs.add(new BasicNameValuePair("CompanyWeb", string_companyWeb));
              nameValuePairs.add(new BasicNameValuePair("CompanyIntro", string_companyIntro));
        	 if (picPath!=null) {
				params.addBodyParameter("CompanyLogo", picPath);
			}
         params.addBodyParameter(nameValuePairs);
         HttpUtils http = new HttpUtils();
         http.send(HttpRequest.HttpMethod.POST,
                 "http://www.hk-compass.com/json/reg.php",
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
								  Dialog dialog = new AlertDialog.Builder(RegisterActivity.this).setTitle("提示")  
							                .setMessage("您的登記資料已經成功提交，請返回您的郵箱 :"
							  +string_email+"，確認您的帳戶即可完成註冊。" +
							  		"Outlook 與 hotmail 用戶，系統郵件可能被放置於垃圾郵箱，請注意查看。")  
							                .setPositiveButton("確定", new DialogInterface.OnClickListener() {  
							                    public void onClick(DialogInterface dialog, int which) {  
							                    	Intent intent=new Intent(RegisterActivity.this,Tab1Activity.class);
							                		intent.putExtra("tab_id", 2);
							                		startActivity(intent);
							                        finish();
							                    }  
							                }).create();  
							        dialog.show();  
							}
							  else {

								  if ("MemberEmail already exists".endsWith(msg)) {
									  new CustomerAlertDialog(RegisterActivity.this, "電郵已存在").show();
								}
								  else {
									  new CustomerAlertDialog(RegisterActivity.this, "註冊未成功，請重新註冊").show();
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
