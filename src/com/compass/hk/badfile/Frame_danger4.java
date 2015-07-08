package com.compass.hk.badfile;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.Tab1Activity;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.dialog.CustomerAlertDialog.mOnClickListener;
import com.compass.hk.login_register.Login_Regiester;
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
import com.webdesign688.compass.R;






public class Frame_danger4 extends Fragment implements OnClickListener, mOnClickListener 
{  

	private View mLayout;
	private static TextView mTv_Deadline;
	private static String mItemID;
	private static boolean mIsChoosed;
	private TextView mTv_Email;
	private TextView mTv_Telephone;
	private TextView mTv_Name;
	private RadioButton mRadioButton_1;
	private RadioButton mRadioButton_2;
	private TextView mTv_Summit;
	private String mMember_Id;
	private String mMember_Type;
	private String mMember_Tel;
	private String mMember_Name;
	private String mMember_Email;
	private String paythod;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frame_danger4, null);
                             initUI();
		return mLayout ; 
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//判斷會員是否登錄
        boolean logined = Bean.isLogined();
		 if (logined) {
				
					settext();
					
			}
	}
	private void settext() {
		// TODO Auto-generated method stub
		
		mMember_Name = Bean.getMember_Name();
		mMember_Email = Bean.getMember_Email();
		mMember_Tel = Bean.getMember_Tel();
		mMember_Type = Bean.getMember_Type();
		mMember_Id = Bean.getMember_Id();
		 String  tel= mMember_Tel.replace("&nbsp;", " ");
		mTv_Telephone.setText(tel);
		mTv_Email.setText(mMember_Email);
		mTv_Name.setText(mMember_Name);
		
	}
	private void initUI() {
		
		         mTv_Name = (TextView) mLayout.findViewById(R.id.tv_danger4_name);
		         mTv_Telephone = (TextView) mLayout.findViewById(R.id.tv_danger4_telephone);
		         mTv_Email = (TextView) mLayout.findViewById(R.id.tv_danger4_email);
		         mRadioButton_1 = (RadioButton) mLayout.findViewById(R.id.radio_danger4_1);
		         mRadioButton_2 = (RadioButton) mLayout.findViewById(R.id.radio_danger4_2);
		      //   tv_danger4_summit
		         
		         mTv_Summit = (TextView) mLayout.findViewById(R.id.tv_danger4_summit);
		         mTv_Summit.setOnClickListener(this);
		
               mTv_Deadline = (TextView) mLayout.findViewById(R.id.tv_danger4_deadline);		
		      View view = mLayout.findViewById(R.id.rela_danger4_deadline);
		    view.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rela_danger4_deadline:
			startActivity(new Intent(getActivity(),DeadLineActivity.class));
			break;
		case R.id.tv_danger4_summit:
			summit();
			break;

		default:
			break;
		}
	}

	private  void summit() {
		// TODO Auto-generated method stub
		String text = mTv_Deadline.getText().toString();
	   if (!mIsChoosed) {
		new CustomerAlertDialog(getActivity(), "請選擇期限").show();
	} else {
           if (mRadioButton_1.isChecked()) {
			paythod ="1";
		   }
           else {
            paythod ="2";
		}
           if (mMember_Name==null||"".equals(mMember_Name)) {
        		CustomerAlertDialog customerAlertDialog = new CustomerAlertDialog(getActivity(), "請先登錄");
				customerAlertDialog.show();
				customerAlertDialog.setOnclickListener_on(this);
		}
           else {
        	   postData(text,paythod);
		}
	}  
	}   

	private void postData(String text, String paythod2) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 RequestParams params = new RequestParams();
		         List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
		         nameValuePairs.add(new BasicNameValuePair("MemberType", mMember_Type));
		         nameValuePairs.add(new BasicNameValuePair("MemberID", mMember_Id));
		         nameValuePairs.add(new BasicNameValuePair("MemberName", mMember_Name));
		         nameValuePairs.add(new BasicNameValuePair("MemberTel", mMember_Tel));
		         nameValuePairs.add(new BasicNameValuePair("MemberEmail",mMember_Email));
		         nameValuePairs.add(new BasicNameValuePair("SubsDays",mItemID ));
		         nameValuePairs.add(new BasicNameValuePair("PayMent", paythod2));
					
		         params.addBodyParameter(nameValuePairs);
		         HttpUtils http = new HttpUtils();
		         http.send(HttpRequest.HttpMethod.POST,
		                 Content.URL_BADFILES_SCRITPT,
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
										  
										  Toast.makeText(getActivity(), "確認書已經發送，請注意查收", Toast.LENGTH_SHORT).show();
										  startActivity(new Intent(getActivity(),XiongZhaiActivity.class));
										  
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
		                    	 Log.e("error:", msg);
		                    	 new Dialog_noInternet(getActivity()).show();
		                     }
		                 });
	}

	public static void setText(String string) {
		// TODO Auto-generated method stub
		mTv_Deadline.setText(string);
	}

	public static void setID(String itemID) {
		// TODO Auto-generated method stub
		mItemID=itemID;
	}

	@Override
	public void onBtnClicklistener() {
		// TODO Auto-generated method stub
		startActivity(new Intent(getActivity(),Login_Regiester.class));
	}

	public static void setTag(boolean b) {
		// TODO Auto-generated method stub
		mIsChoosed=b;
	}
}
