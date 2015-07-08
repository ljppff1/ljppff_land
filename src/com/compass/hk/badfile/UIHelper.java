package com.compass.hk.badfile;

import android.content.Context;

public class UIHelper {

	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;

		return (int) (dipValue * scale + 0.5 * (dipValue >= 0 ? 1 : -1));
	}
}