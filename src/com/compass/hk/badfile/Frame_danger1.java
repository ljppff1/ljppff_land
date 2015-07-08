package com.compass.hk.badfile;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.compass.hk.dialog.AlertInfoDialog;
import com.compass.hk.dialog.Dialog_noInternet;
import com.compass.hk.rent.RentActivity;
import com.compass.hk.util.Bean;
import com.compass.hk.util.Content;
import com.compass.hk.util.getJson;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;




import com.webdesign688.compass.R;


public class Frame_danger1 extends Fragment implements OnClickListener  
{  
	private View mLayout;
	private ListView mListView;
	private ArrayList<Data> mDataList=new ArrayList<Frame_danger1.Data>();
	private OnFragmentListener mListener;
	private RelativeLayout rela1;
	private LinearLayout rela2;
	private EditText mEtframe_danger;
	private Button mBtSearch_danger;
	private ProgressBar rela3;
	@Override
	public void onAttach(Activity activity) {
		mListener = (OnFragmentListener) activity;
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             mLayout = inflater.inflate(R.layout.frame_danger_sort, null);
                             mLayout.findViewById(R.id.tv_danger_sort_book).setOnClickListener(this);
                             mEtframe_danger = (EditText)mLayout.findViewById(R.id.mEtframe_danger);
                             mBtSearch_danger =(Button)mLayout.findViewById(R.id.mBtSearch_danger);
                             mBtSearch_danger.setOnClickListener(this);
                             rela1 =(RelativeLayout)mLayout.findViewById(R.id.rela1);
                             rela2 =(LinearLayout)mLayout.findViewById(R.id.rela2);
                             rela3 =(ProgressBar)mLayout.findViewById(R.id.rela3);
                             download1();
                             downLoad();
                            
		return mLayout ; 
	}

	private void download1() {
		 boolean logined = Bean.isLogined();
		 if (logined) {
			new DownLoadAsyTask1().execute(Content.URL_BADFILES_STATUS+"?MemberType="+Bean.getMember_Type()+"&&MemberID="+Bean.getMember_Id());
		 }else{
			 rela2.setVisibility(View.GONE);
			 rela1.setVisibility(View.VISIBLE);
			 rela3.setVisibility(View.GONE);
		 }
	
	}
	public void frame_danger_pop(){
		
	}
	class DownLoadAsyTask1 extends AsyncTask<String, Void, String>{  
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(result);
				String string_code = jsonObject.getString("code");
				 int  num_code=Integer.valueOf(string_code);
				 //已订阅
				 if (num_code==1) {
					 rela1.setVisibility(View.GONE);
					 rela2.setVisibility(View.VISIBLE);
					 rela3.setVisibility(View.GONE);
					}
				 else if(num_code==0){
					 rela2.setVisibility(View.GONE);
					 rela1.setVisibility(View.VISIBLE);
					 rela3.setVisibility(View.GONE);
				 }
				 else {
					 mLayout.findViewById(R.id.progressBar_danger_sort).setVisibility(View.GONE);
					new AlertInfoDialog(getActivity()).show();
				}
			} catch (JSONException e) {
				mLayout.findViewById(R.id.progressBar_danger_sort).setVisibility(View.GONE);
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

	private void downLoad() {
		// TODO Auto-generated method stub
		new DownLoadAsyTask().execute(Content.URL_BADFILE);
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
						  
						  Data  data=new Data();
						  
						 JSONObject jsonObject2 = array.getJSONObject(i);
						 data.ID= jsonObject2.getString("ID");
						 data.Area= jsonObject2.getString("Area");
						 data.Num = jsonObject2.getString("Num");
						 mDataList.add(data);
					}
					  initListView();
				}
				 else {
					 mLayout.findViewById(R.id.progressBar_danger_sort).setVisibility(View.GONE);
					new AlertInfoDialog(getActivity()).show();
				}
			} catch (JSONException e) {
				mLayout.findViewById(R.id.progressBar_danger_sort).setVisibility(View.GONE);
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
	
	class Data{
		String ID;
		String Area;
		String Num;
		@Override
		public String toString() {
			return "Data [ID=" + ID + ", Area=" + Area + ", Num=" + Num + "]";
		}
	}
	private void initListView() {
                    mLayout.findViewById(R.id.progressBar_danger_sort).setVisibility(View.GONE);
                    mListView = (ListView) mLayout.findViewById(R.id.listView_danger_sort);
                    mListView.setVisibility(View.VISIBLE);
                    mListView.setAdapter(new Myadapter());
	}
	
	class Myadapter extends  BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHoler viewHoler=null;
			if (convertView==null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.item_listview_dangersort, null);
				     viewHoler=new ViewHoler();
				     viewHoler.tv_ID= (TextView) convertView.findViewById(R.id.tv_listview_badfle_id);
				     viewHoler.tv_Area= (TextView) convertView.findViewById(R.id.tv_listview_badfle_area);
				     viewHoler.tv_Num= (TextView) convertView.findViewById(R.id.tv_listview_badfle_num);
				     convertView.setTag(viewHoler);
			}
			else {
				viewHoler = (ViewHoler) convertView.getTag();
			}
			
			viewHoler.tv_ID.setText(mDataList.get(position).ID);
			viewHoler.tv_Area.setText(mDataList.get(position).Area);
			viewHoler.tv_Num.setText(mDataList.get(position).Num);
			return convertView;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataList.size();
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
	class  ViewHoler{
		TextView tv_ID;
		TextView tv_Area;
		TextView tv_Num;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_danger_sort_book:
/*			Intent intent = new Intent(getActivity(),XiongZhaiActivity.class);
			intent.putExtra("isBooked", true);
			startActivity(intent);
*/			mListener.onFragmentAction(true);
			break;
		case R.id.mBtSearch_danger:
			if(mEtframe_danger.getText().toString().length()>2){
			Intent intent =new Intent(getActivity(), XiongZhaiListActivity.class);
			intent.putExtra("SEARCH", mEtframe_danger.getText().toString());
			startActivity(intent);
			}else if(mEtframe_danger.getText().toString().length()==0){
				Intent intent =new Intent(getActivity(), XiongZhaiListActivity.class);
				intent.putExtra("SEARCH","荃威花園");
				startActivity(intent);
			}else{
				Toast.makeText(getActivity(), "請輸入最少3個字", 0).show();
			}
			break;
		default:
			break;
		}
	}
	
	public interface OnFragmentListener {

		public void onFragmentAction(boolean intflag);

		}
}
