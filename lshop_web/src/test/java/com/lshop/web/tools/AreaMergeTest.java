package com.lshop.web.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.test.base.BaseSpringTestCase;
import com.lshop.common.pojo.logic.LvArea;

/**
 * 区域数据合并
 * @author jinjian 2014-10-28
 *
 */
public class AreaMergeTest extends BaseSpringTestCase {
	private final static int area_level_1 = 1;
	private final static int area_level_2 = 2;
	private final static int area_level_3 = 3;
	
	private final static String src_geo = "gv_geo";
	/**
	 * 国家信息表
	 */
	private final static String table_country = "gv_country";
	/**
	 * 省份信息表
	 */
	private final static String table_subdivision = "gv_subdivision";
	/**
	 * 城市信息表
	 */
	private final static String table_city = "gv_city";	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Test
	public void testGetProductPrice() throws Exception {
		//String url="jdbc:mysql://localhost:3306/test?user=root&password=123456";
		String url="jdbc:mysql://10.0.1.214:3306/gv_geo?user=develop&password=123456";
		String areaFileName = "e:\\areaResult.txt";
		Calendar beginCal = Calendar.getInstance();
		File f = new File(areaFileName);
		if (f.exists()) {
			f.delete();
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
            FileOutputStream out=new FileOutputStream(areaFileName);
            PrintStream ps=new PrintStream(out);	
    		//1、合并国家信息数据
    		Map<String,LvArea> areaCountryCodeMap = new HashMap<String,LvArea>(); 
    		List<LvArea> areaCountryList = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel=" + area_level_1, null);
    		for (int i=0;i<areaCountryList.size();i++) {
    			LvArea area = areaCountryList.get(i);
    			if(area.getCode().length()>0){
    				areaCountryCodeMap.put(area.getCode().toLowerCase().trim(), area);	
    			}
    		}
    		
    		Connection con = DriverManager.getConnection(url);
    		Statement stmt = con.createStatement();
    		//将 geo 数据库国家表信息插入到 lv_area 表
    		String query = "select id,code_iso,i18n_names from " + table_country;
    		ResultSet rs=stmt.executeQuery(query);
    		int countrySrcTotal = 0;
    		int countryInsertSuccess = 0;
    		while (rs.next()) {
    			countrySrcTotal++;
    			String codeIso = rs.getString(2);
    			if (areaCountryCodeMap.get(codeIso.toLowerCase().trim()) == null) {
    				Integer id = rs.getInt(1);
    				String i18nNames = rs.getString(3);
    				JSONObject joI18nNames = JSONObject.fromObject(i18nNames);
    				String enName = joI18nNames.getString("en");
    				String cnName = joI18nNames.getString("zh-CN");
    				String twName = joI18nNames.getString("zh-TW");
    				LvArea newArea = new LvArea();
    				newArea.setNamecn(cnName);
    				newArea.setNameen(enName);
    				newArea.setNametw(twName);
    				newArea.setNamekn(enName);
    				newArea.setCode(codeIso);
    				newArea.setStoreId("tvpadcn");
    				Calendar cal = Calendar.getInstance();
    				newArea.setCreateTime(cal.getTime());
    				newArea.setAreaLevel(area_level_1);
    				newArea.setSrc(src_geo);
    				String remark = table_country + ":id=" + id;
    				newArea.setRemark(remark);
    				lvlogicWriteDao.save(newArea);
    				countryInsertSuccess++;
    			}
    		}
    		//2、合并省份信息
    		//获取最新的国家信息
    		Map<String,LvArea> areaCountryCodeMapNew = new HashMap<String,LvArea>();
    		Map<Integer,LvArea> areaCountryIdMapNew = new HashMap<Integer,LvArea>(); 
    		List<LvArea> areaCountryListNew = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel=" + area_level_1, null);
    		for (int i=0;i<areaCountryListNew.size();i++) {
    			LvArea area = areaCountryListNew.get(i);
    			if(area.getCode().length()>0){
    				areaCountryCodeMapNew.put(area.getCode().toLowerCase().trim(), area);
    				areaCountryIdMapNew.put(area.getId(), area);
    			}
    		}
    		//组装省份信息
    		Map<String,LvArea> areaSubdivisionCodeMap = new HashMap<String,LvArea>(); 
    		List<LvArea> areaSubdivisionList = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel="+area_level_2, null);
    		for (int i=0;i<areaSubdivisionList.size();i++) {
    			LvArea area = areaSubdivisionList.get(i);
    			LvArea countryArea = areaCountryIdMapNew.get(area.getParentid());
    			if (countryArea == null) {
    				ps.println("[组装省份时未找到国家信息]===id:"+area.getId()+",namecn:"+area.getNamecn()+",parentid:"+area.getParentid());
    				continue;
    			}
    			if(area.getCode().length()>0){
    				areaSubdivisionCodeMap.put(countryArea.getCode().toLowerCase().trim() + "-" + area.getCode().toLowerCase().trim(), area);
    			}
    		}
    		//将 geo 数据库省份表信息插入到 lv_area 表
    		query = "select id,code_iso,name,code from " + table_subdivision;
    		rs=stmt.executeQuery(query);
    		int subdivisionSrcTotal = 0;
    		int subdivisionInsertSuccess = 0;
    		int subdivisionCnTotal = 0;
    		while (rs.next()) {
    			subdivisionSrcTotal++;
    			Integer id = rs.getInt(1);
    			String codeIso = rs.getString(2);
    			String name = rs.getString(3);			
    			String code = rs.getString(4);
    			String[] codeArray = code.split("-");
    			//如果是中国的省份信息，则不用处理，以华扬数据库中为准
    			if ("CN".equals(codeArray[0])) {
    				subdivisionCnTotal++;
    				continue;
    			}
    			if (areaSubdivisionCodeMap.get(code.toLowerCase().trim()) == null) {
    				LvArea newArea = new LvArea();
    				newArea.setNamecn(name);
    				newArea.setNameen(name);
    				newArea.setNametw(name);
    				newArea.setNamekn(name);
    				LvArea parentArea = areaCountryCodeMapNew.get(codeArray[0].toLowerCase().trim());
    				if (parentArea==null) {
    					System.out.println("==========");
    				}
    				newArea.setParentid(parentArea.getId());
    				newArea.setCode(codeIso);
    				newArea.setStoreId("tvpadcn");
    				Calendar cal = Calendar.getInstance();
    				newArea.setCreateTime(cal.getTime());
    				newArea.setAreaLevel(area_level_2);
    				newArea.setSrc(src_geo);
    				String remark = table_subdivision + ":id=" + id;
    				newArea.setRemark(remark);
    				lvlogicWriteDao.save(newArea);
    				subdivisionInsertSuccess++;
    			}
    		}
    		//3、合并城市信息
    		//获取最新的省份信息
    		Map<String,LvArea> areaSubdivisionCodeMapNew = new HashMap<String,LvArea>();
    		Map<Integer,LvArea> areaSubdivisionIdMapNew = new HashMap<Integer,LvArea>();
    		List<LvArea> areaSubdivisionListNew = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel="+area_level_2, null);
    		for (int i=0;i<areaSubdivisionListNew.size();i++) {
    			LvArea area = areaSubdivisionListNew.get(i);
    			LvArea countryArea = areaCountryIdMapNew.get(area.getParentid());
    			if (countryArea == null) {
    				ps.println("[组装最新省份时未找到国家信息]===id:"+area.getId()+",namecn:"+area.getNamecn()+",parentid:"+area.getParentid());
    				continue;
    			}			
    			if(area.getCode().length()>0){
    				areaSubdivisionCodeMapNew.put(countryArea.getCode().toLowerCase().trim() + "-" + area.getCode().toLowerCase().trim(), area);
    				areaSubdivisionIdMapNew.put(area.getId(), area);
    			}
    		}
    		//组装城市信息
    		Map<String,LvArea> areaCityMap = new HashMap<String,LvArea>();
    		List<LvArea> areaCityList = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel="+area_level_3, null);
    		for (int i=0;i<areaCityList.size();i++) {
    			LvArea area = areaCityList.get(i);
    			LvArea subdivisionArea = areaSubdivisionIdMapNew.get(area.getParentid());
    			if (subdivisionArea == null) {
    				ps.println("[组装城市时未找到省份信息]===id:"+area.getId()+",namecn:"+area.getNamecn()+",parentid:"+area.getParentid());
    				continue;
    			}
    			LvArea countryArea = areaCountryIdMapNew.get(subdivisionArea.getParentid());
    			if (countryArea == null) {
    				ps.println("[组装城市时未找到国家信息]===id:"+area.getId()+",namecn:"+area.getNamecn()+",parentid:"+area.getParentid());
    				continue;
    			}			
    			if(area.getCode().length()>0){
    				areaCityMap.put(countryArea.getCode().toLowerCase().trim()+"-"+subdivisionArea.getCode().toLowerCase().trim() + "-" + area.getCode().toLowerCase().trim(), area);	
    			}
    		}
    		//将 geo 数据库城市表信息插入到 lv_area 表
    		query = "select id,name,i18n_names,subd_code from " + table_city;
    		rs=stmt.executeQuery(query);
    		int citySrcTotal = 0;
    		int cityInsertSuccess = 0;
    		int cityCnTotal = 0;
    		while (rs.next()) {
    			citySrcTotal++;
    			Integer id = rs.getInt(1);
    			String name = rs.getString(2);
    			String i18nNames = rs.getString(3);			
    			String subdCode = rs.getString(4);
    			String[] subdCodeArray = subdCode.split("-");
    			//如果是中国的省份信息，则不用处理，以华扬数据库中为准
    			if ("CN".equals(subdCodeArray[0])) {
    				cityCnTotal++;
    				continue;
    			}
    			String codeIso = subdCode + "-" + name;		
    			if (name.length() < 1) {
					ps.println("[插入城市数据时名称为空]===id:"+id+",name:"+name+",i18nNames:"+i18nNames+",subdCode:"+subdCode);
					continue;    				
    			}    			
    			if (areaCityMap.get(codeIso.toLowerCase().trim()) == null) {
    				JSONObject joI18nNames = JSONObject.fromObject(i18nNames);
    				String enName = joI18nNames.getString("en");
    				LvArea newArea = new LvArea();
    				newArea.setNamecn(enName);
    				newArea.setNameen(enName);
    				newArea.setNametw(enName);
    				newArea.setNamekn(enName);
    				LvArea parentArea = areaSubdivisionCodeMapNew.get(subdCode.toLowerCase().trim());
    				if (parentArea == null) {
    					ps.println("[插入城市数据时未找到省份信息]===id:"+id+",name:"+name+",i18nNames:"+i18nNames+",subdCode:"+subdCode);
    					continue;
    				}
    				newArea.setParentid(parentArea.getId());
    				newArea.setCode(name);
    				newArea.setStoreId("tvpadcn");
    				Calendar cal = Calendar.getInstance();
    				newArea.setCreateTime(cal.getTime());
    				newArea.setAreaLevel(area_level_3);
    				newArea.setSrc(src_geo);
    				String remark = table_subdivision + ":id=" + id;
    				newArea.setRemark(remark);
    				lvlogicWriteDao.save(newArea);
    				cityInsertSuccess++;
    			}
    		}
    		ps.println("===== 区域信息合并结果  =================================");
    		ps.println("===== countrySrcTotal:"+countrySrcTotal+",countryInsertSuccess:"+countryInsertSuccess+" ====");
    		ps.println("===== subdivisionSrcTotal:"+subdivisionSrcTotal+",subdivisionInsertSuccess:"+subdivisionInsertSuccess+",subdivisionCnTotal:"+subdivisionCnTotal+" ====");
    		ps.println("===== citySrcTotal:"+citySrcTotal+",cityInsertSuccess:"+cityInsertSuccess+",cityCnTotal:"+cityCnTotal+" ====");
    		Calendar endCal = Calendar.getInstance();
    		Long diffTime =endCal.getTimeInMillis()-beginCal.getTimeInMillis();
    		ps.println("===== 耗时:"+diffTime+"毫秒 ====");
            ps.close();
            System.out.println("===== 区域信息合并完成  =================================");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
