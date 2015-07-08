package com.compass.hk.login_register;

import com.webdesign688.compass.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AgeActivity extends Activity {

	private String[] mAgeLists;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_age);
     mAgeLists = new  String[]{
    		 "20歲以下   ","20-24歲   ","25-29歲   ","30-34歲   ",
    		 "35-39歲   ","40-44歲   ","45-49歲   ","50-54   歲","55-59歲   ","60歲以上   "
     };		
     ListView mListView_Age= (ListView) findViewById(R.id.listView_age);
     mListView_Age.setAdapter(new Myadapter());
     mListView_Age.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			//RegisterActivity.setAge(mAgeLists[arg2]);
			Intent data=new Intent();
			data.putExtra("age", mAgeLists[arg2]);
			data.putExtra("age1", (arg2+1)+"");
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
		     textView.setText(mAgeLists[position]);
		   
			return layout;
		}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mAgeLists.length;
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
