package com.compass.hk.frame;

import com.webdesign688.compass.R;
import com.compass.hk.hot.HotDetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;






public class MenuFrame1 extends Fragment 
{  

	private View layout;
	private ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
                             layout = inflater.inflate(R.layout.frame_menu1, null);	
                             initListView();
		return layout ; 
	}

	private void initListView() {
                  		layout.findViewById(R.id.progressBar_tab_rent).setVisibility(View.GONE);
                  		mListView = (ListView) layout.findViewById(R.id.listView_tab_rent);
                  		mListView.setVisibility(View.VISIBLE);
                  		mListView.setAdapter(new Myadapter());
                  		//µã»÷Listview
                  		listViewClick();
                  		
                  		
	}
	private void listViewClick() {
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			startActivity(new Intent(getActivity(),HotDetailActivity.class));	
			}
		});
		
		
	}
	class  Myadapter extends   BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			           View laout = getActivity().getLayoutInflater().inflate(R.layout.item_listview_rent, null);
			return laout;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
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
}
