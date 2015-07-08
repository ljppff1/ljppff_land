package com.compass.hk.util;

import android.graphics.drawable.Drawable;

public class Bean {

	private static String mString_Rent_ID;
	private static Drawable mDrawable_pic;
	private static boolean  isLogined;
	//會員的登錄狀態和信息
	private static  String  member_Id;
	private static  String  member_Email;
	private static  String  member_Name;
	private static  String  member_Type;
	private static  String  member_Tel;
	
	public static boolean isLogined() {
		return isLogined;
	}
	
	public static void setLogined(boolean isLogined) {
		Bean.isLogined = isLogined;
	}
	
	public static void setMember_Id(String member_Id) {
		Bean.member_Id = member_Id;
	}
	
	public static String getMember_Email() {
		return member_Email;
	}

	public static void setMember_Email(String member_Email) {
		Bean.member_Email = member_Email;
	}

	public static String getMember_Name() {
		return member_Name;
	}

	public static void setMember_Name(String member_Name) {
		Bean.member_Name = member_Name;
	}

	public static String getMember_Type() {
		return member_Type;
	}

	public static void setMember_Type(String member_Type) {
		Bean.member_Type = member_Type;
	}

	public static String getMember_Tel() {
		return member_Tel;
	}

	public static void setMember_Tel(String member_Tel) {
		Bean.member_Tel = member_Tel;
	}

	public static String getMember_Id() {
		return member_Id;
	}

	public static void setRentID(String id) {
		// TODO Auto-generated method stub
		mString_Rent_ID=id;
	}
	
	public String  getRentID(){
		return mString_Rent_ID;
		
	}

	public static void setDrawable(Drawable mDrawable) {
		// TODO Auto-generated method stub
		mDrawable_pic=mDrawable;
	}
	public static Drawable getmDrawable_pic() {
		return mDrawable_pic;
	}

}
