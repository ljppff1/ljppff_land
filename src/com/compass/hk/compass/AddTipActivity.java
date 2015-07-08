package com.compass.hk.compass;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.CustomerAlertDialog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AddTipActivity extends Activity implements OnClickListener {

	private static String mString_Resource;
	private   static String mString_Area;
	private  static String mString_AreaId;
	private static  String mString_District;
	private static   String mString_houseType1;
	private  static String mString_houseType2;
	private  static String mString_RoomNum;
	private  static String mString_HouseFloor;
	private   static  TextView mTv_RoomNum;
	private   static TextView mTv_HouseType2;
	private static     TextView mTv_HouseType1;
	private  static TextView mTv_HouseResource;
	private  static  TextView mTv_HouseFloor;
	private  static   TextView mTv_District;
	private   static TextView mTv_Area;
	private Spinner mSpinner_Area1,mSpinner_Area2,mSpinner_Price1,mSpinner_Price2;
	private TextView mTv_Summit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tip);
		initUI();
		initSpinner();
	}

	private void initUI() {
		// TODO Auto-generated method stub
		View rela_area = findViewById(R.id.rela_addtip_area);
		View rela_district = findViewById(R.id.rela_addtip_district);
		View rela_housefloor= findViewById(R.id.rela_addtip_housefloor);
		View rela_houserource= findViewById(R.id.rela_addtip_houserource);
		View rela_housetype1= findViewById(R.id.rela_addtip_housetype1);
		View rela_housetype2= findViewById(R.id.rela_addtip_housetype2);
		View rela_roomnum= findViewById(R.id.rela_addtip_roomnum);
		
		rela_area.setOnClickListener(this);
		rela_district.setOnClickListener(this);
		rela_housefloor.setOnClickListener(this);
		rela_houserource.setOnClickListener(this);
		rela_housetype1.setOnClickListener(this);  
		rela_housetype2.setOnClickListener(this);
		rela_roomnum.setOnClickListener(this);
		
		mTv_Area = (TextView) findViewById(R.id.tv_addtip_area);
		mTv_District = (TextView) findViewById(R.id.tv_addtip_district);
		mTv_HouseFloor = (TextView) findViewById(R.id.tv_addtip_housefloor);
		mTv_HouseResource = (TextView) findViewById(R.id.tv_addtip_houseresource);
		mTv_HouseType1 = (TextView) findViewById(R.id.tv_addtip_housetype1);
		mTv_HouseType2 = (TextView) findViewById(R.id.tv_addtip_housetype2);
		mTv_RoomNum = (TextView) findViewById(R.id.tv_addtip_roomnum);
		mTv_Summit= (TextView) findViewById(R.id.tv_addtip_summit);
		mTv_Summit.setOnClickListener(this);
		
		mSpinner_Area1= (Spinner) findViewById(R.id.spinner_adtip_area1);
		mSpinner_Area2 = (Spinner) findViewById(R.id.spinner_adtip_area2);
		mSpinner_Price1 = (Spinner) findViewById(R.id.spinner_addtip_price1);
		mSpinner_Price2 = (Spinner) findViewById(R.id.spinner_addtip_price2);
		
	}
	private void initSpinner() {
		
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.addtip_size));
		ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.addtip_size));
		ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.addtip_price1));
		ArrayAdapter<String> adapter4=new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.addtip_price2));
		
		mSpinner_Area1.setAdapter(adapter1);
		mSpinner_Area2.setAdapter(adapter2);
		mSpinner_Price1.setAdapter(adapter3);
		mSpinner_Price2.setAdapter(adapter4);
		mSpinner_Area1.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//mString_area1 = mAreaList1[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mSpinner_Area2.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (arg2==10) {
					//mSting_area2="1000000";
				}
				else {
					
					//mSting_area2 = mAreaList2[arg2];
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,Listview_Tip_Activity.class);
		switch (v.getId()) {
		case R.id.rela_addtip_houserource:
			intent.putExtra("id", 1);
			intent.putExtra("title", "盤源");
			startActivity(intent);
			break;
        case R.id.rela_addtip_area:
        	intent.putExtra("id", 2);
        	intent.putExtra("title", "區域");
        	startActivity(intent);
			break;
         case R.id.rela_addtip_district:
        	 if (mString_AreaId==null) {
				new CustomerAlertDialog(this, "請選擇地區").show();
			}
        	 else if("0".equals(mString_AreaId)) {
				
			}
        	 else {
        		 intent.putExtra("id", 3);
        		 intent.putExtra("area_id", mString_AreaId);
        		 intent.putExtra("title", "地區");
        		 startActivity(intent);
			}
			break;
         case R.id.rela_addtip_housetype1:
        	 intent.putExtra("id", 4);
        	 intent.putExtra("title", "屋苑");
        	 startActivity(intent);
 			break;
         case R.id.rela_addtip_housetype2:
        	 intent.putExtra("id", 5);
        	 intent.putExtra("title", "物業種類");
        	 startActivity(intent);
 			break;
         case R.id.rela_addtip_roomnum:
        	 intent.putExtra("id", 6);
        	 intent.putExtra("title", "房間數目");
        	 startActivity(intent);
 			break;
         case R.id.rela_addtip_housefloor:
        	 intent.putExtra("id", 7);
        	 intent.putExtra("title", "樓層");
        	 startActivity(intent);
  			break;
         case R.id.tv_addtip_summit:
        	Toast.makeText(AddTipActivity.this, "已成功儲存樓盤提示", Toast.LENGTH_SHORT).show();
        	finish();
  			break;
  			
		default:
			break;
		}
	}

	public static void setResource(String string) {
		// TODO Auto-generated method stub
		mString_Resource = string;
		mTv_HouseResource.setText(mString_Resource+"  ");
	}

	public static void setArea(String string) {
		// TODO Auto-generated method stub
		mString_Area= string;
		mTv_Area.setText(mString_Area+"  ");
	}

	public static void setDistrict(String string) {
		// TODO Auto-generated method stub
		mString_District = string;
		mTv_District.setText(mString_District+"  ");
	}

	public static void setType1(String string) {
		// TODO Auto-generated method stub
		mString_houseType1= string;
		mTv_HouseType1.setText(mString_houseType1+"  ");
	}

	public static void setType2(String string) {
		// TODO Auto-generated method stub
		mString_houseType2= string;
		mTv_HouseType2.setText(mString_houseType2+"  ");
	}

	public static void setRoomNum(String string) {
		// TODO Auto-generated method stub
		mString_RoomNum = string;
		mTv_RoomNum.setText(mString_RoomNum+"  ");
	}

	public static void setHouseFloor(String string) {
		// TODO Auto-generated method stub
		mString_HouseFloor= string;
		mTv_HouseFloor.setText(mString_HouseFloor+"  ");
	}

	public static void setAreaId(String string) {
		// TODO Auto-generated method stub
		mString_AreaId= string;
	}

}
