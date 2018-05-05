package com.lshop.web.userCenter.bbs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.core.util.cryption.MD5;

public class SynDB {
	
	private static final Log logger = LogFactory.getLog(SynDB.class);
	String pwd = MD5.convert32(MD5.convert32("gv123456") + "qweasdzxc");
	public String discuzDBPre;
	
	public static SynDB db;
	
	private SynDB(){
		try{
			discuzDBPre = BBSPropertiesHelper.getProperty("discuz_pre");
		}catch(Exception e){
			discuzDBPre = "gv_";
		}
		
		if(null == discuzDBPre || discuzDBPre.equals("")){
			discuzDBPre = "gv_";
		}
	}
	
	public static SynDB getSynDB(){
		if(db == null){
			db = new SynDB();
		}
		return db;
	}
	
	public DBHelper getDiscuzDB(){
		String dis_db_url = BBSPropertiesHelper.getProperty("discuz.jdbc.url");
		String dis_db_user = BBSPropertiesHelper.getProperty("discuz.jdbc.username");
		String dis_db_pwd = BBSPropertiesHelper.getProperty("discuz.jdbc.password");
		return new DBHelper(dis_db_url,dis_db_user,dis_db_pwd);	
	}
	
	public void synDiscuzDB(int uid,String name,String email,String ip){
		
		int regdate = Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10)); 
		String regip = ip;
		DBHelper db = getDiscuzDB();
		String sql1 = "insert into " + discuzDBPre + "common_member(uid,username,password,email,adminid,groupid,regdate,credits,timeoffset) " +
				"values(" + uid + ",'" + name +"','" + pwd + "','" + email +"','0','10'," + regdate + ",'2','9999')";
		
		String sql2 = "insert into " + discuzDBPre + "common_member_status(uid,regip,lastip,port,lastvisit,lastactivity,lastpost,lastsendmail,invisible,buyercredit,sellercredit,favtimes,sharetimes,profileprogress) " +
		"values(" + uid + ",'" + regip + "','" + regip + "',6666," + regdate + "," + regdate + ",0,0,0,0,0,0,0,0)";

		String sql3 = "insert into " + discuzDBPre + "common_member_profile(uid,bio,interest,field1,field2,field3,field4,field5,field6,field7,field8) " +
				"values(" + uid + ",'','','','','','','','','','')";
		
		String sql4 = "insert into " + discuzDBPre + "common_member_field_forum(uid,medals,sightml,groupterms,groups) " +
				"values(" + uid + ",'','','','')";
		
		String sql5 = "insert into " + discuzDBPre + "common_member_field_home(uid,spacecss,blockposition,recentnote,spacenote,privacy,feedfriend,acceptemail,magicgift,stickblogs) " +
				"values(" + uid + ",'','','','','','','','','')";
		String sql6 = "insert into " + discuzDBPre + "common_member_count(uid) " +
		"values(" + uid + ")";
		
		try{
			db.getDBConn().setAutoCommit(false);
			
			Statement stat = db.getDBConn().createStatement();
			stat.addBatch(sql1);

			stat.addBatch(sql2);
			stat.addBatch(sql3);
			stat.addBatch(sql4);
			stat.addBatch(sql5);
			stat.addBatch(sql6);
			
			stat.executeBatch();
			
		}catch (SQLException e) {
			try {
				db.getDBConn().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			logger.info("========账号" + name + "同步到discuz数据库失败");
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
		
	}
	
	public int getMemmberId(Statement stat,String name) throws SQLException{
		String sql = "select uid from " + discuzDBPre + "common_member where username='" + name + "'";
		ResultSet res = stat.executeQuery(sql);
		while(res.next()){
			return res.getInt(1);
		}
		return 0;
	}

	public void synDiscuzAndWordpressDB(int uid,String name,String email,String ip){
		logger.info("========将用户" + name + "信息同步到discuz和wordpress数据库。。。");
		this.synDiscuzDB(uid,name, email,ip);
	}
	
	public void reSetUCPwd(String email,String newPwd){
		DBHelper db = getDiscuzDB();
		try{
			Statement stat = db.getDBConn().createStatement();
			String salt = getSaltFromUcDB(stat,email);
			String pwd = MD5.convert32(MD5.convert32(newPwd) + salt);
			
			String sql = "UPDATE " + discuzDBPre + "ucenter_members SET password='" + pwd + "' where email='" + email + "'";
			stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			logger.info("========账号" + email + "：重置UC密码失败");
			e.printStackTrace();
		}finally{
			db.closeConnection();
		}
	}
	
	public String getSaltFromUcDB(Statement stat,String email) throws SQLException{
		String sql = "select salt from " + discuzDBPre + "ucenter_members where email='" + email + "'";
		ResultSet res = stat.executeQuery(sql);
		while(res.next()){
			return res.getString(1);
		}
		return "";
	}
	
	public static void main(String[] args) {
//		new SynDB().synDiscuzAndWordpressDB(100, "test891@test.com", "test891@test.com", "");
//		System.out.println(MD5.convert32(MD5.convert32("123456")+"18d24d"));
		String url = "http://www.bananatv.com/web/bmcn/noLoginInfo.jsp?jumpurl=http://bbs.bananatv.com&tt=111&";
		if(url.contains("?") && url.contains("jumpurl=")){
			url = url.substring(url.indexOf("?")+1);
			url = url.substring(url.indexOf("jumpurl=")+8);
			if(url.contains("&")){
				url = url.substring(0, url.indexOf("&"));
			}
		}
		System.out.println(url);
	}

}
