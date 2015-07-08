package com.compass.hk.util;

public interface Content {
  final  static String   URL="http://www.hk-compass.com/json/";
  // http://josiephp.imymedia.com/compass/json/map3.php?ID=1
   final  static String   URL_FIRST_HAND=URL+"firsthand.php?ShowType=";
  
  final  static String   URL_FIRST_HAND_DETAIL=URL+"firsthanddetail.php?ID=";
  
  final  static String   URL_FIRST_HAND_DETAIL_WUYETUPIAN=URL+"firsthandphotos.php?ID=";
  
  final  static String   URL_FIRST_HAND_DETAIL_GALLERY=URL+"firsthandgallery.php?ID=";
  
  final  static String   URL_FIRST_HAND_DETAIL_GALLERYVIEW=URL+"firsthandgalleryview.php?ID=";
  
  final  static String   URL_PROPERTYDETAIL=URL+"propertydetail.php?ID=";
  final  static String   URL_PROPERTYDETAIL1=URL+"propertydetail.php";

  
  final  static String   URL_PROPERTYLIST=URL+"propertylist.php?ShowType=";
  
  final  static String   URL_PROPERTYDETAIL_GALLERY=URL+"propertygallery.php?ID=";
  
  //租售物业图片
  final  static String   URL_PROPERTYPHOTOS=URL+"propertyphotos.php?ID=";
  
//地區列表
  final  static String   URL_DISTRICTLIST=URL+"districtlist.php?LocationID=";
//地區列表
  final  static String   URL_DISTRICTLIST_ALL=URL+"districtlist.php";
//search
  final  static String   URL_PROPERTYSEARCH=URL+"propertysearch.php";
  final  static String   URL_POSTBUILD=URL+"postbuild.php";
  //一手樓盤地圖街景
  final  static String   URL_YISHOU_JIEJING=URL+"map1.php?ID=";
  
  final  static String   URL_RENT_MAP=URL+"map3.php?ID=";
  
  //一手樓盤的新聞
  final  static String   URL_YISHOU_NEWLISTS=URL+"firstnewslist.php";
  
//一手樓盤內頁的新聞
  final  static String   URL_YISHOU_NEWLISTS_INNER=URL+"firstnewslist.php?PropertyID=";
  
  
  
  //一手樓盤的新聞詳情
  final  static String   URL_YISHOU_NEWDETAIL=URL+"firstnewsdetail.php?ID=";
  
  //二手樓盤的新聞
  final  static String   URL_PROPERTYNEWLISTS=URL+"propertynewslist.php";
  
  //二手樓盤的新聞詳情
  final  static String   URL_PROPERTYNEWDETAILS=URL+"propertynewsdetail.php?ID=";
  
 //凶宅的新聞
  final  static String   URL_BADFILE_NEWLISTS=URL+"badfilenewslist.php";
  
  //凶宅新聞詳情
  final  static String   URL_BADFILENEWDETAIL=URL+"badfilenewsdetail.php?ID=";
  
  //按揭的新聞
  final  static String   URL_AJNEWSLIST=URL+"ajnewslist.php";
  
  //按揭的新聞詳情
  final  static String   URL_AJNEWDETAIL=URL+"ajnewsdetail.php?ID=";
  
//公司的新聞
  final  static String   URL_COMPANYNEWSLIST=URL+"companynewslist.php";
  
  //公司的新聞詳情
  final  static String   URL_COMPANYNEWDETAILS=URL+"companynewsdetail.php?ID=";
  
  
  //聯絡我們
  final  static String   URL_ContactUs=URL+"contact.php";
  
  //計算利率
  
  final  static String   URL_Calculate=URL+"calculate.php";
  
/*
 * ********************  凶宅接口*********************************************
 */
  //排行榜
  static final  String URL_BADFILE=URL+"badfile.php";
  
  //何為凶宅
  static final String  URL_BADFILE_WHAT =URL+"info.php?InfoID=11";
  
 //怎麼樣可避免入凶宅
  static final String  URL_BADFILE_HOW=URL+"info.php?InfoID=12";
  
 //查詢訂閱狀態
  static final String  URL_BADFILES_STATUS=URL+"badfilestatus.php";
  
  //提交訂閱
  static final String  URL_BADFILES_SCRITPT=URL+"badfilescript.php";
  
  //搜尋結果
  static final String  URL_BADFILES_LIST=URL+"badfilelist.php";
  
  //期限列表
  static final String  URL_BADFILE_TYPE=URL+"badfiletype.php";
  
  
  /*
   * *******************凶宅接口***********************************
   */
  
  /*
   * *******************個人中心接口（開始）********************************
   */

  //註冊
  final  static String   URL_REGISTER=URL+"reg.php";
  
  //登錄
  final  static String   URL_LOGIN=URL+"login.php";
  
//樓盤提示列表
  final  static String   URL_PROMPTLIST="http://www.hk-compass.com/json/"+"promptlist.php";
  
//新增樓盤提示
  final  static String   URL_PROMPADD=URL+"promptadd.php";
  
//刪除樓盤
  final  static String   URL_PROMTTDEL=URL+"promptdel.php";
  
//心水樓盤列表
  final  static String   URL_FAVORLIST="http://www.hk-compass.com/json/"+"favorlist.php";
  
//加入心水樓盤
  final  static String   URL_FAVORADD=URL+"favoradd.php";
  final  static String   URL_SENDEMAIL=URL+"propertycontact.php";
//刪除心水樓盤
  final  static String   URL_FAVORDEL=URL+"favordel.php";
  
//楼盘列表
  final  static String   URL_FLOORLIST=URL+"mypropertylist.php";
//刪除樓盤
  final  static String   URL_FLOORDEL=URL+"mypropertydel.php";

  /*
   * ******************個人中心接口（結束）*********************************
   */
//代理接口
  final  static String   URL_Agence=URL+"agencylist.php?ShowType=1";
  final  static String   URL_Agence4=URL+"agencylist.php?ShowType=3";
//搜索
  final static String URL_Search =URL+"propertysearch.php";
 //用户接口
  final static String URL_user_edit =URL+"myinfo.php";
  
}

