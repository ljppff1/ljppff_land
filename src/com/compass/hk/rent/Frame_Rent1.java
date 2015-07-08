package com.compass.hk.rent;

import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 物业资料详情
 * @author liujun
 *
 */
public class Frame_Rent1 extends Fragment 
{  

	private View mLayout;
	private TextView mTv_Areagross;
	private TextView mTv_Areanet;
	private TextView mTv_BuildingNum;
	private TextView mTv_Dashawuyuan;
	private TextView mTv_Developer;
	private TextView mTv_Discrict;
	private TextView mTv_Floor;
	private TextView mTv_Isinclude1;
	private TextView mTv_Isinclude2;
	private TextView mTv_Isvacant1;
	private TextView mTv_Isvancant2;
	private TextView mTv_Jiangge;
	private TextView mTv_Latestprice;
	private TextView mTv_Monthy;
	private TextView mTv_Periods;
	private TextView mTv_Propertytype;
	private TextView mTv_Region;
	private TextView mTv_Remark;
	private TextView mTv_Rentprice;
	private TextView mTv_Saleprice;
	private TextView mTv_Schoolnet;
	private TextView mTv_Seatno;
	private TextView mTv_Set;
	private TextView mTv_Sitto;
	private TextView mTv_Special;
	private TextView mTv_Streetname;
	private TextView mTv_Units;
	private TextView mTv_View;
	private TextView mTv_Years;
	private String mString_ID;
	private TextView mTv_rentsale;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frament_rent1, null);
                             getID();
                             downLoad();
                            
		return mLayout ; 
	}

	private void getID() {
            Bean   bean=new Bean();		
            mString_ID = bean.getRentID();
	}

	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_PROPERTYDETAIL+mString_ID);
	}
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
					 String SellingPrice = data.getString("SellingPrice");
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
					 
					 //initUI
					 initUI();
					 if(!TextUtils.isEmpty(RentSale)){
					 if(RentSale.equals("0")){
						 mTv_rentsale.setText(":  "+"可租可售");
					 }else if(RentSale.equals("1")){
						 mTv_rentsale.setText(":  "+"出售");
					 }else if(RentSale.equals("2")){
						 mTv_rentsale.setText(":  "+"出租");
					 }}
					 //settext
					 mTv_Areagross.setText(":  "+AreaGross+"呎");
					 mTv_Areanet.setText(":  "+AreaNet+"呎");
					 mTv_BuildingNum.setText(":  "+BuildingNumbers);
					 mTv_Developer.setText(":  "+Developer);
					 mTv_Discrict.setText(":  "+District);
					 mTv_Floor.setText(":  "+Floor);
					 mTv_Isinclude1.setText(":  "+isInclude1);
					 mTv_Isinclude2.setText(":  "+isInclude2);
					 mTv_Isvacant1.setText(":  "+isVacant1);
					 mTv_Isvancant2.setText(":  "+isVacant2);
					 
					 Spanned fromHtml_monthly = Html.fromHtml(Monthly);
					 mTv_Monthy.setText(":  "+fromHtml_monthly);
					 
					 Spanned fromHtml_district = Html.fromHtml(District);
					 mTv_Discrict.setText(":  "+fromHtml_district);
					 mTv_Special.setText(":  "+Special);
					 mTv_Latestprice.setText("");
					 mTv_Periods.setText(":  "+Periods);
					 mTv_Propertytype.setText(":  "+PropertyType);
					 mTv_Region.setText(":  "+Region);
					 mTv_Remark.setText(""+Remark);
					 mTv_Rentprice.setText(":  HK$"+RentPrice);
					 mTv_Schoolnet.setText(":  "+SchoolNet);
					 mTv_Seatno.setText(":  "+SeatNo);
					 mTv_Saleprice.setText(":  HK$"+SellingPrice+"萬");
					 
			           if (TextUtils.isEmpty(SellingPrice)||"0".equals(SellingPrice)) {
							 mTv_Saleprice.setText(":   HK$ -萬    ");
					}
			           else {
							 mTv_Saleprice.setText(":  HK$"+SellingPrice+"萬");
					}
			           if (TextUtils.isEmpty(RentPrice)||"0".equals(RentPrice)) {
			        	   mTv_Rentprice.setText(":   HK$ -萬    ");
					}
			           else {
							 mTv_Rentprice.setText(":  HK$"+RentPrice);
					}
					 
					 mTv_Set.setText(":  "+Sets);
					 mTv_Sitto.setText(":  "+Sitto);
					 mTv_Streetname.setText(":  "+StreetName);
					 mTv_Units.setText(":  "+Units);
					 mTv_View.setText(":  "+Views);
					 mTv_Years.setText(":  "+Years);
					 mTv_Dashawuyuan.setText(":  "+Name);
					 mTv_Jiangge.setText(":  "+Bedroom+"房"+LivingRoom+"廳"+Toilet+"廁");
					 
				 }
				 else {
					// layout.findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
					new AlertInfoDialog(getActivity()).show();
				}
			} catch (JSONException e) {
				new Dialog_noInternet(getActivity()).show();
				e.printStackTrace();
			}
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}

	private void initUI() {
		// TODO Auto-generated method stub
		mTv_Areagross = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_areagross);
		mTv_Areanet = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_areanet);
		mTv_BuildingNum = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_buildingnumbers);
		mTv_Dashawuyuan = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_dashawuyuan);
		mTv_Developer = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_developer);
		mTv_Discrict = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_disctrict);
		mTv_Floor = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_floor);
		mTv_Isinclude1 = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_isinclude1);
		mTv_Isinclude2 = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_isinclude2);
		mTv_Isvacant1 = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_isVacant1);
		mTv_Isvancant2 = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_isVacant2);
		mTv_Jiangge = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_jiangge);
		mTv_Latestprice = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_latestprice);
		mTv_Monthy = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_monthy);
		mTv_Periods = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_periods);
		mTv_Propertytype = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_propertytype);
		mTv_Region = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_region);
		mTv_Remark = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_remark);
		mTv_Rentprice = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_rentprice);
		mTv_Saleprice = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_saleprice);
		mTv_Schoolnet = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_schoolnet);
		mTv_Seatno = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_seatno);
		mTv_Set = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_sets);
		mTv_Sitto = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_sitto);
		mTv_Special = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_special);
		mTv_Streetname = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_streetname);
		mTv_Units = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_units);
		mTv_View = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_view);
		mTv_Years = (TextView) mLayout.findViewById(R.id.tv_rent1_detail_years);
		mTv_rentsale =(TextView)mLayout.findViewById(R.id.tv_rent1_detail_rentsale);
		
	}
	
}
