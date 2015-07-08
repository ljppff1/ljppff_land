package com.compass.hk.more;

import com.webdesign688.compass.R;
import com.compass.hk.frame.Frame_Title;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MoreActivity extends FragmentActivity {

	private String[] mStringLists;
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		FragmentManager sfm = getSupportFragmentManager();
		FragmentTransaction ft = sfm.beginTransaction();
		Frame_Title    frame_Title= new Frame_Title();
		frame_Title.setTitle("����");
		ft.add(R.id.frame_title, frame_Title);
		ft.commit();
		initListView();
	}

	private void initListView() {
		
		mStringLists = new String[]{"���]�o����","���՗l��","˽������","�z�����","�j�҂�","�ØIָ��ᘾW"};
		mListView = (ListView) findViewById(R.id.listView_more);
		mListView.setAdapter(new Myadapter());
		itemClick();
	}
   private void itemClick() {
		// TODO Auto-generated method stub
	   mListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			switch (position) {
			case 0:
				
				 Intent intent=new Intent(Intent.ACTION_SEND);
	                intent.setType("text/plain");
	                intent.putExtra(Intent.EXTRA_SUBJECT, "����");
	                intent.putExtra(Intent.EXTRA_TEXT, "�ØIָ���-��۷��خa����I�űP���ǱP�ь�����լ�YӍ���������ʣ�סլ���ۡ����ó�ʽ���d��ַ��http://www.hk-compass.com");
	                startActivity(Intent.createChooser(intent, "����"));
		     break;
			case 1:
				startActivity(new Intent(MoreActivity.this,TermsActivity.class));
				break;
			case 2:
				startActivity(new Intent(MoreActivity.this,PoliceActivity.class));
				break;
			case 3:
				Toast.makeText(MoreActivity.this, "��ǰ�����°汾", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				startActivity(new Intent(MoreActivity.this,ContactActivity.class));
				break;
			case 5:
				Intent it = new Intent( Intent.ACTION_VIEW );

				it.setData( Uri.parse("http://www.hk-compass.com") ); //����������Ҫ��ת��rul

				it = Intent.createChooser( it, null );

				startActivity( it );
				break;
			case 6:
				
				break;
				
			default:
				break;
			}
		}
	});
	}
class Myadapter extends BaseAdapter{
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View layout = getLayoutInflater().inflate(R.layout.item_listview_more, null);
			TextView tv_Title= (TextView) layout.findViewById(R.id.tv_listview_more_title);
			tv_Title.setText(mStringLists[position]);
			return layout;
			
		}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mStringLists.length;
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
