package com.compass.hk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode.Mode;
import android.os.AsyncTask;

import com.compass.hk.cash.Zone;
import com.compass.hk.util.getJson;

public class DefaultLogin {
	private Context mContext;
	private SharedPreferences sp;
	public  DefaultLogin(Context context){
		  this.mContext=context;
		      downloadZone();
	}

	private void downloadZone() {
		sp = mContext.getSharedPreferences("compass", 0);
		new DownLoadAsyTask_hongkog().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=1");
		new DownLoadAsyTask_jiulong().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=2");
		new DownLoadAsyTask_xinjie().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=3");
		new DownLoadAsyTask_lidao().execute("http://www.hk-compass.com/json/districtlist.php?LocationID=4");
		new DownLoadAsyTask_all().execute("http://www.hk-compass.com/json/districtlist.php");
	}
	class DownLoadAsyTask_all extends AsyncTask<String, Void, String>{  
		@Override
protected void onPostExecute(String result) {
	super.onPostExecute(result);
	JSONObject jsonObject;
	try {
		jsonObject = new JSONObject(result);
		String string_code = jsonObject.getString("code");
		 int  num_code=Integer.valueOf(string_code);
		 if (num_code==1) {
			 sp.edit().putString("data_all", result).commit();
			 Zone.setZone_all(result);
			}
	} catch (JSONException e) {
		e.printStackTrace();
	}
}
	@Override
	protected String doInBackground(String... params) {
		String str=params[0];
		return getJson.getData(str);
	}
	}
class DownLoadAsyTask_hongkog extends AsyncTask<String, Void, String>{  
@Override
protected void onPostExecute(String result) {
	super.onPostExecute(result);
	JSONObject jsonObject;
	try {
		jsonObject = new JSONObject(result);
		String string_code = jsonObject.getString("code");
		int  num_code=Integer.valueOf(string_code);
		if (num_code==1) {
			 sp.edit().putString("data_hongkong", result).commit();
			 Zone.setZone_hongkong(result);
			}
	} catch (JSONException e) {
		e.printStackTrace();
	}
}
@Override
protected String doInBackground(String... params) {
	String str=params[0];
	return getJson.getData(str);
}
}
class DownLoadAsyTask_jiulong extends AsyncTask<String, Void, String>{  
@Override
protected void onPostExecute(String result) {
	super.onPostExecute(result);
	JSONObject jsonObject;
	try {
		jsonObject = new JSONObject(result);
		String string_code = jsonObject.getString("code");
		int  num_code=Integer.valueOf(string_code);
		if (num_code==1) {
			 sp.edit().putString("data_jiulong", result).commit();
			 Zone.setZone_jiulong(result);
		 }
	} catch (JSONException e) {
		e.printStackTrace();
	}
}
@Override
protected String doInBackground(String... params) {
	String str=params[0];
	return getJson.getData(str);
}
}
class DownLoadAsyTask_xinjie extends AsyncTask<String, Void, String>{  
@Override
protected void onPostExecute(String result) {
	super.onPostExecute(result);
	JSONObject jsonObject;
	try {
		jsonObject = new JSONObject(result);
		String string_code = jsonObject.getString("code");
		int  num_code=Integer.valueOf(string_code);
		if (num_code==1) {
			 sp.edit().putString("data_xinjie", result).commit();
			 Zone.setZone_xinjie(result);
		 }
	} catch (JSONException e) {
		e.printStackTrace();
	}
}
@Override
protected String doInBackground(String... params) {
	String str=params[0];
	return getJson.getData(str);
}
}
class Data{
String   DistrictID;
String   DistrictNameHK;
String   DistrictNameEN;
@Override
public String toString() {
	return "Data [DistrictID=" + DistrictID + ", DistrictNameHK="
			+ DistrictNameHK + ", DistrictNameEN=" + DistrictNameEN
			+ "]";
}
}
class DownLoadAsyTask_lidao extends AsyncTask<String, Void, String>{  
@Override
protected void onPostExecute(String result) {
	super.onPostExecute(result);
	JSONObject jsonObject;
	try {
		jsonObject = new JSONObject(result);
		String string_code = jsonObject.getString("code");
		int  num_code=Integer.valueOf(string_code);
		if (num_code==1) {
			 sp.edit().putString("data_lidao", result).commit();
			 Zone.setZone_lidao(result);
		 }
	} catch (JSONException e) {
		e.printStackTrace();
	}
}
@Override
protected String doInBackground(String... params) {
	String str=params[0];
	return getJson.getData(str);
}
}
}
