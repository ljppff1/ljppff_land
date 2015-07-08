package com.compass.hk.compass;

import com.compass.hk.MainActivity;
import com.webdesign688.compass.R;
import com.compass.hk.badfile.XiongZhaiActivity;
import com.compass.hk.dialog.CustomerAlertDialog;
import com.compass.hk.dialog.CustomerAlertDialog.mOnClickListener;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.login_register.Login_Regiester;
import com.compass.hk.util.Bean;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
/**
 * �ҵ�ָ����
 * 
 * @author liujun
 *
 */
public class OwnerActivity extends FragmentActivity  implements OnClickListener, mOnClickListener {

	private TextView mTv_Name;
	private boolean mIsLogined;
	private ImageButton btn_login;
	private Button imageButton_login_regiest1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owner);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("�ҵ�ָ���");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		initUI();
		//�Д��Ƿ��䛣��е�䛄t�@ʾ���֣�
		isLogin();
		
	}

	private void isLogin() {
		mIsLogined = Bean.isLogined();
		if (mIsLogined) {
			btn_login.setVisibility(View.GONE);
			mTv_Name.setVisibility(View.VISIBLE);
			mTv_Name.setText("���ã� "+Bean.getMember_Name()+"���gӭ����");
			findViewById(R.id.textView1).setVisibility(View.GONE);
			imageButton_login_regiest1.setVisibility(View.VISIBLE);
		}else{
			
		}
	}

	private void initUI() {
		// TODO Auto-generated method stub
		findViewById(R.id.rela_owner_mydoc).setOnClickListener(this);
		findViewById(R.id.rela_owner_xiongzai).setOnClickListener(this);
		findViewById(R.id.rela_owner_myheart).setOnClickListener(this);
		
		findViewById(R.id.rela_owner_tip).setOnClickListener(this);
		findViewById(R.id.rela_owner_floormanager).setOnClickListener(this);
		btn_login = (ImageButton) findViewById(R.id.imageButton_login_regiest);
		mTv_Name = (TextView) findViewById(R.id.tv_owner_name);
		imageButton_login_regiest1 =(Button)findViewById(R.id.imageButton_login_regiest1);
	}
	public  void btn_login_register1(View v) {
		CustomerAlertDialog customerAlertDialog = new CustomerAlertDialog(OwnerActivity.this, "���_���]�N��ǰ�Ñ�?");
		customerAlertDialog.show();
		customerAlertDialog.setOnclickListener_on(this);
    }
    //ע���½
	public  void btn_login_register(View v) {
                       startActivity(new Intent(this,Login_Regiester.class));
                       finish();
	}

	@Override
	public void onClick(View v) {
          switch (v.getId()) {
		case R.id.rela_owner_xiongzai:
				
				  if (mIsLogined) {
						
						startActivity(new Intent(OwnerActivity.this,XiongZhaiActivity.class));
					}
					else {
						new CustomerAlertDialog(OwnerActivity.this, "Ո�ȵ��").show();
					}
			break;
			//�ҵĵ��� 
		case R.id.rela_owner_mydoc:
       if (mIsLogined) {
				
				startActivity(new Intent(OwnerActivity.this,MyInfoEditActivity.class));
			}
			else {
				new CustomerAlertDialog(OwnerActivity.this, "Ո�ȵ��").show();
			}
			break;
		case R.id.rela_owner_myheart:
             if (mIsLogined) {
				
				startActivity(new Intent(OwnerActivity.this,MyFavorActivity.class));
			}
			else {
				new CustomerAlertDialog(OwnerActivity.this, "Ո�ȵ��").show();
			}
			break;
		case R.id.rela_owner_tip:
			
			
           if (mIsLogined) {
				
				startActivity(new Intent(OwnerActivity.this,FloorTipActivity.class));
			}
			else {
				new CustomerAlertDialog(OwnerActivity.this, "Ո�ȵ��").show();
			}
			break;
		case R.id.rela_owner_floormanager:
              if (mIsLogined) {
				
				startActivity(new Intent(OwnerActivity.this,MyFloorActivity.class));
			}
			else {
				new CustomerAlertDialog(OwnerActivity.this, "Ո�ȵ��").show();
			}
			break;
		default:
			break;
		}		
	}

	@Override
	public void onBtnClicklistener() {
		btn_login.setVisibility(View.VISIBLE);
		mTv_Name.setVisibility(View.GONE);
		imageButton_login_regiest1.setVisibility(View.GONE);
		Bean.setLogined(false);
	}
}
