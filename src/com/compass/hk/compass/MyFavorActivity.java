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
 * 我的心水楼盘
 * @author liujun
 *
 */
public class MyFavorActivity extends FragmentActivity {

	private ListView mListView;
	private ArrayList<MyFavor>  mMyTipList=new ArrayList<MyFavorActivity.MyFavor>();
	private Mydapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_favor);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("我的心水樓盤");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		//下載信息
		downLoad();
	}

	private void downLoad() {
		Log.e("downLoad", Bean.getMember_Type()+"type"+Bean.getMember_Id()+"id");
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_FAVORLIST+"?MemberType="+Bean.getMember_Type()
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
						  
						  MyFavor  data=new MyFavor();
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 data.ContactNO= jsonObject2.getString("ContactNO");
						 data.ContactPerson= jsonObject2.getString("ContactPerson");
						 data.FavorID= jsonObject2.getString("FavorID");
						 data.HouseArea1=jsonObject2.getString("HouseArea1");
						 data.HouseArea2=jsonObject2.getString("HouseArea2");
						 data.HouseDistrict=jsonObject2.getString("HouseDistrict");
						 data.HouseFloor= jsonObject2.getString("HouseFloor");
						 data.HousePrice= jsonObject2.getString("HousePrice");
						 data.HouseType = jsonObject2.getString("HouseType");
						 data.LiveNum=jsonObject2.getString("LiveNum");
						 data.Name=jsonObject2.getString("HouseName");
						 data.RentSale=jsonObject2.getString("RentSale");
						 data.RoomNum=jsonObject2.getString("RoomNum");
						 data.ToiltNum=jsonObject2.getString("ToiltNum");
						 data.HouseAddress=jsonObject2.getString("HouseAddress");
						 mMyTipList.add(data);
					}
					  mMyTipList.toString();
					  initListView();
				}
				 else {
					 findViewById(R.id.progressBar_myfav).setVisibility(View.GONE);
					new AlertInfoDialog(MyFavorActivity.this).show();
				}
			} catch (JSONException e) {
				findViewById(R.id.progressBar_myfav).setVisibility(View.GONE);
				 if(mMyTipList.isEmpty())
				new Dialog_noInternet(MyFavorActivity.this).show();
				e.printStackTrace();
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
			convertView = getLayoutInflater().inflate(R.layout.item_listview_myfavor,null);
			viewHolder=new ViewHolder();
			viewHolder.Area1= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_area1);
			viewHolder.Area2= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_area2);
			viewHolder.Contacter= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_contacter);
			viewHolder.Delete= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_deletetip);
			viewHolder.HouseAdress= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_houseadress);
			viewHolder.HouseDistrict= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_housedistrict);
			viewHolder.HouseFloor= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_housefloor);
			viewHolder.RoomNum= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_housenum);
			viewHolder.HousePrice= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_houseprice);
			viewHolder.Housetype1= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_housetype1);
			viewHolder.Name= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_name);
			viewHolder.RentSale= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_rentsale);
			viewHolder.Telephone= (TextView) convertView.findViewById(R.id.tv_myfavor_listview_telephone);
			convertView.setTag(viewHolder);
		}
		else {
			  viewHolder = (ViewHolder) convertView.getTag();
			  
		}
		viewHolder.Telephone.setText(mMyTipList.get(position).ContactNO);
		viewHolder.Contacter.setText(mMyTipList.get(position).ContactPerson);
		viewHolder.HouseAdress.setText(mMyTipList.get(position).HouseAddress);
		viewHolder.Area1.setText(mMyTipList.get(position).HouseArea1+"呎");
		viewHolder.Area2.setText(mMyTipList.get(position).HouseArea2+"呎");
		viewHolder.HouseDistrict.setText(mMyTipList.get(position).HouseDistrict);
		viewHolder.HouseFloor.setText(mMyTipList.get(position).HouseFloor);
		viewHolder.HousePrice.setText(mMyTipList.get(position).HousePrice);
		viewHolder.Housetype1.setText(mMyTipList.get(position).HouseType);
		viewHolder.Name.setText(mMyTipList.get(position).Name);
		viewHolder.RentSale.setText(mMyTipList.get(position).RentSale);
		viewHolder.RoomNum.setText(mMyTipList.get(position).RoomNum+"房"+mMyTipList.get(position).LiveNum+"廳"+mMyTipList.get(position).ToiltNum+"廁");
		viewHolder.Delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				delete(position);
			}
		});
		// TODO Auto-generated method stub
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
	String string_FavorId = mMyTipList.get(position).FavorID.toString();
	 RequestParams params = new RequestParams();
     List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
     nameValuePairs.add(new BasicNameValuePair("MemberType", Bean.getMember_Type()));
     nameValuePairs.add(new BasicNameValuePair("MemberID", Bean.getMember_Id()));
     nameValuePairs.add(new BasicNameValuePair("FavorID", string_FavorId));
		
     params.addBodyParameter(nameValuePairs);
     HttpUtils http = new HttpUtils();
     http.send(HttpRequest.HttpMethod.POST,
             Content.URL_FAVORDEL,
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
							Toast.makeText(MyFavorActivity.this, "成功刪除", Toast.LENGTH_SHORT).show();
							mMyTipList.remove(position);
							adapter.notifyDataSetChanged();
						}
						  else {
							  Toast.makeText(MyFavorActivity.this, "未成功刪除", Toast.LENGTH_SHORT).show();
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
class MyFavor{
	String   FavorID;
	String   Name;
	String   RentSale;
	String   HouseArea1;
	String   HouseArea2;
	String   HouseType;
	String   HousePrice;
	String   HouseFloor;
	String   RoomNum;
	String   LiveNum;
	String   ToiltNum;
	String   HouseDistrict;
	String   ContactPerson ;
	String   ContactNO;
	String HouseAddress;
	@Override
	public String toString() {
		return "MyFavor [FavorID=" + FavorID + ", Name=" + Name + ", RentSale="
				+ RentSale + ", HouseArea1=" + HouseArea1 + ", HouseArea2="
				+ HouseArea2 + ", HouseType=" + HouseType + ", HousePrice="
				+ HousePrice + ", HouseFloor=" + HouseFloor + ", RoomNum="
				+ RoomNum + ", LiveNum=" + LiveNum + ", ToiltNum=" + ToiltNum
				+ ", HouseDistrict=" + HouseDistrict + ", ContactPerson="
				+ ContactPerson + ", ContactNO=" + ContactNO
				+ ", HouseAddress=" + HouseAddress + "]";
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
