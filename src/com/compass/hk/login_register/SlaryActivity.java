package com.compass.hk.login_register;

import com.webdesign688.compass.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SlaryActivity extends Activity {

	private String[] mSlaryLists;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_age);
		TextView textView= (TextView) findViewById(R.id.tv_age_title);
		textView.setText("每月個人入息");
     mSlaryLists = new  String[]{
    		 "15,000HDK以下   ","15,000-25,000HDK   ","25,000-40,000HDK   ","40,000-60,000HDK   ",
    		 "60,000-80,000HDK   ","80,000HDK以上   "
     };		
     ListView mListView_Age= (ListView) findViewById(R.id.listView_age);
     mListView_Age.setAdapter(new Myadapter());
     mListView_Age.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
		//	RegisterActivity.setSlary(mSlaryLists[arg2]);
			// TODO Auto-generated method stub
			Intent data=new Intent();
			data.putExtra("slary", mSlaryLists[arg2]);
			data.putExtra("slary1",  (arg2+1)+"");
			setResult(1, data);
			// TODO Auto-generated method stub
			finish();

		}
	});
	}
   class Myadapter  extends  BaseAdapter{
	   @Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
		     View layout= getLayoutInflater().inflate(R.layout.item_listview_age, null);
		     TextView textView= (TextView) layout.findViewById(R.id.tv_listview_age);
		     textView.setText(mSlaryLists[position]);
		   
			return layout;
		}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSlaryLists.length;
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.age, menu);
		return true;
	}

}
