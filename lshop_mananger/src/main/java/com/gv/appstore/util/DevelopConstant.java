package com.gv.appstore.util;

public class DevelopConstant {

	public static int emailDevReviewYes = 1;//开发者通过
	public static int emailDevReviewNo = 2;//开发者不通过
	public static int emailAppReviewYes = 3;//应用通过
	public static int emailAppReviewNo = 4;//应用不通过
	
	public static String emailMallFlag_BMCN = "bmcn";
	public static String emailMallFlag_BMEN = "bmen";
	public static String emailMallFlag_TVPADCN = "tvpadcn";
	public static String emailMallFlag_TVPADEN = "tvpaden";
	
	public static String bananaResPath = "http://res.bananatv.com/private/0007/";//banana资源文件根目录
	public static String tvpadResPath = "http://res.mtvpad.com/private/0008/";//tvpad资源文件根目录
	
	public static String appPath = "app/";//安装包目录
	public static String appImgPath = "appimg/";//软件截图 目录
	public static String idPath = "id/";//身份认证目录
	
	public static String getSuitFlag(String f){
		if(f.equals("bmcn") || f.equals("bscn")){
			f = emailMallFlag_BMCN;
		}
		if(f.equals("bmen") || f.equals("bsen")){
			f = emailMallFlag_BMEN;
		}
		if(f.equals("www") || f.equals("tvpadcn")){
			f = emailMallFlag_TVPADCN;
		}
		if(f.equals("en") || f.equals("tvpaden")){
			f = emailMallFlag_TVPADEN;
		}
		return f;
	}
	
	public static String getResDomain(String bizline){
		String domain = "";
		if(bizline.equals("0007")){
			domain = bananaResPath;
		}
		if(bizline.equals("0008")){
			domain = tvpadResPath;
		}
		return domain;
	}
}
