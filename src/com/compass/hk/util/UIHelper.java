package com.compass.hk.util;

import java.util.Comparator;
import java.util.UUID;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.webdesign688.compass.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;



public class UIHelper {
	public static int getScreenWidth(Context context) {
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(localDisplayMetrics);
		return localDisplayMetrics.widthPixels;
	}
	class MyComparator_down implements Comparator<Integer>{
		public int compare(Integer i1, Integer i2) {
			if (i1 < i2) return -1;
			else if (i1 == i2) return 0;
			else return 1;
		}
	}
	
	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;

		return (int) (dipValue * scale + 0.5 * (dipValue >= 0 ? 1 : -1));
	}
	/*public static DisplayImageOptions getNormalImageOptions() {
		int backgroundId;
		return new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.icon_applogo)
				.showImageForEmptyUri(R.drawable.icon_applogo)
				.showImageOnFail(R.drawable.icon_applogo)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				.cacheInMemory(true).cacheOnDisk(true).build();
	}*/
	/*public static void sendEmail(Activity activity, String target) {
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL, new String[] { target });
		try {
			activity.startActivity(Intent.createChooser(i, activity
					.getResources().getString(R.string.title_select_app)));
		} catch (android.content.ActivityNotFoundException ex) {
			// 无电子邮箱程序的提示
			Toast.makeText(activity, R.string.tips_no_find_email_app,
					Toast.LENGTH_SHORT).show();
		}
	}*/
	 public static String getUUID(){ 
	        String s = UUID.randomUUID().toString(); 
	        //去掉�?”符�?
	        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	    } 
	 public static  int getStatusBarHeight(Context context){
		   Rect rect = new Rect();
		   ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
		   return rect.top;
		}
}
