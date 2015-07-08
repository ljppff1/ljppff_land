package com.compass.hk.compass;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.compass.MyTipActivity.DownLoadAsyTask;
import com.compass.hk.compass.MyTipActivity.MyTip;
import com.compass.hk.compass.MyTipActivity.Mydapter;
import com.compass.hk.compass.MyTipActivity.ViewHolder;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 楼盘管理
 * @author liujun
 *
 */
public class MyFloorActivity extends FragmentActivity {

	private ListView mListView;
	private ArrayList<MyFloor>  mMyTipList=new ArrayList<MyFloorActivity.MyFloor>();
	private Mydapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_favor);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("樓盤管理");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		//下載信息
		downLoad();
	}

	private void downLoad() {
		Log.e("downLoad", Bean.getMember_Type()+"type"+Bean.getMember_Id()+"id");
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_FLOORLIST+"?MemberType="+Bean.getMember_Type()
				+"&MemberID="+Bean.getMember_Id());
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
					 JSONArray array = jsonObject.getJSONArray("data");
					  for (int i = 0; i < array.length(); i++) {
						  
						  MyFloor  data=new MyFloor();

						 JSONObject jsonObject2 = array.getJSONObject(i);
						 data.ID= jsonObject2.getString("ID");
						 data.RentSale= jsonObject2.getString("RentSale");
						 data.PropertyType= jsonObject2.getString("PropertyType");
						 data.Region=jsonObject2.getString("Region");
						 data.District=jsonObject2.getString("District");
						 data.Name=jsonObject2.getString("Name");
						 data.StreetName= jsonObject2.getString("StreetName");
						 data.AreaGross= jsonObject2.getString("AreaGross");
						 data.Years = jsonObject2.getString("Years");
						 data.Floor=jsonObject2.getString("Floor");
						 data.Bedroom=jsonObject2.getString("Bedroom");
						 data.Livingroom=jsonObject2.getString("Livingroom");
						 data.SellingPrice=jsonObject2.getString("SellingPrice");
						 data.RentPrice=jsonObject2.getString("RentPrice");
						 data.isVacant=jsonObject2.getString("isVacant");
						 data.PropertyStatus=jsonObject2.getString("PropertyStatus");
						 mMyTipList.add(data);
					}
					  mMyTipList.toString();
					  initListView();
				}
				 else {
					 findViewById(R.id.progressBar_myfav).setVisibility(View.GONE);
					new AlertInfoDialog(MyFloorActivity.this).show();
				}
			} catch (JSONException e) {
				findViewById(R.id.progressBar_myfav).setVisibility(View.GONE);
				if(mMyTipList==null)
				new Dialog_noInternet(MyFloorActivity.this).show();
			}
				
		}
			@Override
			protected String doInBackground(String... params) {
				String str=params[0];
				return getJson.getData(str);
			}
			}
	private void initListView() {
		 findViewById(R.id.progressBar_myfav).setVisibility(View.GONE);
		mListView = (ListView) findViewById(R.id.listView_myfav);
		mListView.setVisibility(View.VISIBLE);
		adapter = new Mydapter();
		mListView.setAdapter(adapter);
	}
class  Mydapter extends  BaseAdapter{
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView==null) {
			convertView = getLayoutInflater().inflate(R.layout.item_listview_myfloor,null);
			viewHolder=new ViewHolder();
			viewHolder.Name= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_name);
			viewHolder.RentSale= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_rentsale);
			viewHolder.Area1= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_area1);
			viewHolder.Area2= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_area2);
			viewHolder.Housetype1= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_housetype1);
			viewHolder.HousePrice= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_houseprice);
			viewHolder.Delete= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_deletetip);

			convertView.setTag(viewHolder);
		}
		else {
			  viewHolder = (ViewHolder) convertView.getTag();
			  
		}
		
	  
		if(TextUtils.isEmpty(mMyTipList.get(position).RentSale)){
			viewHolder.Name.setText("/"+mMyTipList.get(position).PropertyType);
		}else{
			if(mMyTipList.get(position).RentSale.equals("0")){
				viewHolder.Name.setText("可租可售/"+mMyTipList.get(position).PropertyType);
			}else if(mMyTipList.get(position).RentSale.equals("1")){
				viewHolder.Name.setText("出售/"+mMyTipList.get(position).PropertyType);
			}else if(mMyTipList.get(position).RentSale.equals("2")){
				viewHolder.Name.setText("出租/"+mMyTipList.get(position).PropertyType);
			}else{
				viewHolder.Name.setText("/"+mMyTipList.get(position).PropertyType);
			}
		}
		viewHolder.RentSale.setText(mMyTipList.get(position).Region+"/"+mMyTipList.get(position).District);
		viewHolder.Area1.setText(mMyTipList.get(position).Name+"/"+mMyTipList.get(position).StreetName);
		viewHolder.Area2.setText(mMyTipList.get(position).AreaGross+"/"+mMyTipList.get(position).Years);
		viewHolder.Housetype1.setText(mMyTipList.get(position).SellingPrice+"/"+mMyTipList.get(position).RentPrice+"/"+mMyTipList.get(position).isVacant);
		if(mMyTipList.get(position).PropertyStatus.equals("0")){
			viewHolder.HousePrice.setText("已審核");
		}else{
			viewHolder.HousePrice.setText("未審核");
		}

		viewHolder.Delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				delete(position);
			}
		});
		return convertView;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMyTipList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
}
private void delete(final int position) {
	// TODO Auto-generated method stub
	String string_FavorId = mMyTipList.get(position).ID.toString();
	 RequestParams params = new RequestParams();
     List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
     nameValuePairs.add(new BasicNameValuePair("MemberType", Bean.getMember_Type()));
     nameValuePairs.add(new BasicNameValuePair("MemberID", Bean.getMember_Id()));
     nameValuePairs.add(new BasicNameValuePair("PropertyID", string_FavorId));
		
     params.addBodyParameter(nameValuePairs);
     HttpUtils http = new HttpUtils();
     http.send(HttpRequest.HttpMethod.POST,
             Content.URL_FLOORDEL,
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
							Toast.makeText(MyFloorActivity.this, "成功刪除", Toast.LENGTH_SHORT).show();
							mMyTipList.remove(position);
							adapter.notifyDataSetChanged();
						}
						  else {
							  Toast.makeText(MyFloorActivity.this, "未成功刪除", Toast.LENGTH_SHORT).show();
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                 }
                 @Override
                 public void onFailure(HttpException error, String msg) {
                	 Log.e("error:", msg);
                 }
             });
	
}
class MyFloor{
	String   ID;
	String   RentSale;
	String   PropertyType;
	String   Region;
	String   District;
	String   Name;
	String   StreetName;
	String   AreaGross;
	String   Years;
	String   Floor;
	String   Bedroom;
	String   Livingroom;
	String SellingPrice;
	String   RentPrice ;
	String   isVacant;
	String PropertyStatus;
	@Override
	public String toString() {
		return "MyFloor [ID=" + ID + ", RentSale=" + RentSale + ", PropertyType="
				+ PropertyType + ", Region=" + Region + ", District="
				+ District + ", Name=" + Name + ", StreetName="
				+ StreetName + ", AreaGross=" + AreaGross + ", Years="
				+ Years + ", Floor=" + Floor + ", Bedroom=" + Bedroom
				+ ", Livingroom =" + Livingroom  + ",SellingPrice="+SellingPrice+", RentPrice="
				+ RentPrice + ", isVacant=" + isVacant
				+ ", PropertyStatus=" + PropertyStatus + "]";
	}

	
	
	
}
class ViewHolder{
	TextView   RentSale;
	TextView   Area1;
	TextView   Area2;
	TextView   HouseAdress;
	TextView   HouseDistrict;
	TextView   Housetype1;
	TextView   Telephone;
	TextView   HousePrice;
	TextView   RoomNum;
	TextView   HouseFloor;
	TextView   Name;
	TextView   Contacter;
	TextView   Delete;
}
}
