package com.compass.hk.badfile;

import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.badfile.Frame_danger2.DownLoadAsyTask;
import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.webdesign688.compass.R;






public class Frame_danger5 extends Fragment 
{  

	private View mLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frame_danger2, null);	
                             downLoad();
                             
		return mLayout ; 
	}

	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_BADFILE_HOW);
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
    	    		 String string_Title= data.getString("Title");
    	    		 String string_Content= data.getString("Content");
    	    		 //settext
    	    		 TextView tv_Title= (TextView) mLayout.findViewById(R.id.tv_danger2_title);
    	    		 TextView tv_Content= (TextView) mLayout.findViewById(R.id.tv_danger2_content);
    	    		 
    	    		 tv_Title.setText(string_Title);
    	    		 tv_Content.setText(Html.fromHtml(string_Content));
    	    		 
    	    		 
				}
				 else {
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
	
}
