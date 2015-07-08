package com.compass.hk.compass;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.webdesign688.compass.R;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.frame.Frame_Title;
import com.compass.hk.rent.RentDetailActivity;
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
import android.app.AlertDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 楼盘提示
 * @author liujun
 *
 */
public class MyTipActivity extends Activity {

	private ListView mListView;
	private ArrayList<MyTip>  mMyTipList=new ArrayList<MyTipActivity.MyTip>();
	private Mydapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tip);
		//下載信息
		downLoad();
	}

	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_PROMPTLIST+"?MemberType="+Bean.getMember_Type()
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
						  
						  MyTip  data=new MyTip();
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 data.PrompID= jsonObject2.getString("PromptID");
						 data.RentSale= jsonObject2.getString("RentSale");
						 data.HouseSource= jsonObject2.getString("HouseSource");
						 data.HouseDistrict=jsonObject2.getString("HouseDistrict");
						 data.Housetype1=jsonObject2.getString("HouseType1");
						 data.HouseType2=jsonObject2.getString("HouseType2");
						 data.HouseSize1= jsonObject2.getString("HouseSize1");
						 data.HouseSize2= jsonObject2.getString("HouseSize2");
						 data.HousePrice1 = jsonObject2.getString("HousePrice1");
						 data.HousePrice2=jsonObject2.getString("HousePrice2");
						 data.RoomNum=jsonObject2.getString("RoomNum");
						 data.HouseFloor=jsonObject2.getString("HouseFloor");
						 data.UseLang=jsonObject2.getString("UseLang");
						 data.AdvPrice=jsonObject2.getString("AdvPrice");
						 mMyTipList.add(data);
					}
					  initListView();
				}
				 else {
					 findViewById(R.id.progressBar_mytip).setVisibility(View.GONE);
					new AlertInfoDialog(MyTipActivity.this).show();
				}
			} catch (JSONException e) {
				findViewById(R.id.progressBar_mytip).setVisibility(View.GONE);
				 if(mMyTipList.isEmpty())
				new Dialog_noInternet(MyTipActivity.this).show();
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
		findViewById(R.id.progressBar_mytip).setVisibility(View.GONE);
		mListView = (ListView) findViewById(R.id.listView_mytip);
		mListView.setVisibility(View.VISIBLE);
		adapter = new Mydapter();
		mListView.setAdapter(adapter);
	}
class  Mydapter extends  BaseAdapter{
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView==null) {
			convertView = getLayoutInflater().inflate(R.layout.item_listview_mytip,null);
			viewHolder=new ViewHolder();
			viewHolder.AdvPrice= (TextView) convertView.findViewById(R.id.tv_mytip_listview_advprice);
			viewHolder.Delete= (TextView) convertView.findViewById(R.id.tv_mytip_listview_deletetip);
			viewHolder.HouseDistrict= (TextView) convertView.findViewById(R.id.tv_mytip_listview_district);
			viewHolder.UseLang= (TextView) convertView.findViewById(R.id.tv_mytip_listview_email);
			viewHolder.HouseFloor= (TextView) convertView.findViewById(R.id.tv_mytip_listview_housefloor);
			viewHolder.RoomNum= (TextView) convertView.findViewById(R.id.tv_mytip_listview_housenum);
			viewHolder.HousePrice= (TextView) convertView.findViewById(R.id.tv_mytip_listview_houseprice);
			viewHolder.HouseSize= (TextView) convertView.findViewById(R.id.tv_mytip_listview_housesize);
			viewHolder.HouseSource= (TextView) convertView.findViewById(R.id.tv_mytip_listview_housesource);
			viewHolder.Housetype1= (TextView) convertView.findViewById(R.id.tv_mytip_listview_housetype1);
			viewHolder.HouseType2= (TextView) convertView.findViewById(R.id.tv_mytip_listview_housetype2);
			viewHolder.RentSale= (TextView) convertView.findViewById(R.id.tv_mytip_listview_rentsale);
			viewHolder.HouseArea= (TextView) convertView.findViewById(R.id.tv_mytip_listview_area);
			convertView.setTag(viewHolder);
		}
		else {
			  viewHolder = (ViewHolder) convertView.getTag();
			  
		}
		viewHolder.AdvPrice.setText(mMyTipList.get(position).AdvPrice);
		viewHolder.HouseArea.setText(mMyTipList.get(position).HouseArea);
		viewHolder.HouseDistrict.setText(mMyTipList.get(position).HouseDistrict);
		viewHolder.HouseFloor.setText(mMyTipList.get(position).HouseFloor);
		viewHolder.AdvPrice.setText(mMyTipList.get(position).HousePrice1+"到"+mMyTipList.get(position).HousePrice2);
		viewHolder.HouseSize.setText(mMyTipList.get(position).HouseSize1+"到"+mMyTipList.get(position).HouseSize2);
		viewHolder.HouseSource.setText(mMyTipList.get(position).HouseSource);
		viewHolder.Housetype1.setText(mMyTipList.get(position).Housetype1);
		viewHolder.HouseType2.setText(mMyTipList.get(position).HouseType2);
		viewHolder.RentSale.setText(mMyTipList.get(position).RentSale);
		viewHolder.RoomNum.setText(mMyTipList.get(position).RoomNum);
		viewHolder.UseLang.setText(mMyTipList.get(position).UseLang);
		viewHolder.Delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//delete
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
	String string_PrompId = mMyTipList.get(position).PrompID.toString();
	 RequestParams params = new RequestParams();
     List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>(10);
     nameValuePairs.add(new BasicNameValuePair("MemberType", Bean.getMember_Type()));
     nameValuePairs.add(new BasicNameValuePair("MemberID", Bean.getMember_Id()));
     nameValuePairs.add(new BasicNameValuePair("PromptID", string_PrompId));
		
     params.addBodyParameter(nameValuePairs);
     HttpUtils http = new HttpUtils();
     http.send(HttpRequest.HttpMethod.POST,
             Content.URL_PROMTTDEL,
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
							Toast.makeText(MyTipActivity.this, "成功刪除", Toast.LENGTH_SHORT).show();
							mMyTipList.remove(position);
							adapter.notifyDataSetChanged();
						}
						  else {
							  Toast.makeText(MyTipActivity.this, "未成功刪除", Toast.LENGTH_SHORT).show();
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
class MyTip{
	String   PrompID;
	String   RentSale;
	String   HouseSource;
	String   HouseArea;
	String   HouseDistrict;
	String   Housetype1;
	String   HouseType2;
	String   HouseSize1;
	String   HouseSize2;
	String   HousePrice1;
	String   HousePrice2;
	String   RoomNum;
	String   HouseFloor;
	String   UseLang;
	String   AdvPrice;
	@Override
	public String toString() {
		return "MyTip [PrompID=" + PrompID + ", RentSale=" + RentSale
				+ ", HouseSource=" + HouseSource + ", HouseArea=" + HouseArea
				+ ", HouseDistrict=" + HouseDistrict + ", Housetype1="
				+ Housetype1 + ", HouseType2=" + HouseType2 + ", HouseSize1="
				+ HouseSize1 + ", HouseSize2=" + HouseSize2 + ", HousePrice1="
				+ HousePrice1 + ", HousePrice2=" + HousePrice2 + ", RoomNum="
				+ RoomNum + ", HouseFloor=" + HouseFloor + ", UseLang="
				+ UseLang + ", AdvPrice=" + AdvPrice + "]";
	}
	
}
class ViewHolder{
	TextView   RentSale;
	TextView   HouseSource;
	TextView   HouseArea;
	TextView   HouseDistrict;
	TextView   Housetype1;
	TextView   HouseType2;
	TextView   HouseSize;
	TextView   HousePrice;
	TextView   RoomNum;
	TextView   HouseFloor;
	TextView   UseLang;
	TextView   AdvPrice;
	TextView   Delete;
}
}