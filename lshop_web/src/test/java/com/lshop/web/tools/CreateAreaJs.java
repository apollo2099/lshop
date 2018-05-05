package com.lshop.web.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.test.base.BaseSpringTestCase;
import com.lshop.common.pojo.logic.LvArea;

/**
 * 生成区域js文件
 * @author jinjian 2014-11-03
 *
 */
public class CreateAreaJs extends BaseSpringTestCase {
	private final static int area_level_1 = 1;
	private final static int area_level_2 = 2;
	private final static int area_level_3 = 3;
	private String[] langList = {"cn","en","tw","kn"};
	private final static String area_prev = "area_";
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Test
	public void testGetProductPrice() throws Exception {
		String areaFileName = "e:\\area\\";
		File f = new File(areaFileName);
		if (f.exists()) {
			deleteDir(areaFileName);
		} else {
			f.mkdir();
		}
		for (int i=0;i<langList.length;i++) {
			String langDirPath = areaFileName+area_prev+langList[i]+"\\";
			File f1 = new File(langDirPath);
			f1.mkdir();	
		}
		try {
			Map<Integer,List<LvArea>> countryProvinceMap = new HashMap<Integer,List<LvArea>>();
			List<LvArea> countryList = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel=" + area_level_1, null);
			List<LvArea> provinceList = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel=" + area_level_2, null);
			for (int i=0;i<provinceList.size();i++) {
				LvArea area = provinceList.get(i);
				List<LvArea> list = (List<LvArea>)countryProvinceMap.get(area.getParentid());
				if (list == null) {
					list = new ArrayList<LvArea>();
				}
				list.add(area);
				countryProvinceMap.put(area.getParentid(), list);
			}
			List<LvArea> cityList = lvlogicWriteDao.find("select a from LvArea a WHERE areaLevel=" + area_level_3, null);
			Map<Integer,List<LvArea>> provinceCityMap = new HashMap<Integer,List<LvArea>>();
			for (int i=0;i<cityList.size();i++) {
				LvArea area = cityList.get(i);
				List<LvArea> list = (List<LvArea>)provinceCityMap.get(area.getParentid());
				if (list == null) {
					list = new ArrayList<LvArea>();
				}
				list.add(area);
				provinceCityMap.put(area.getParentid(), list);
			}			
					
			for (int i=0;i<langList.length;i++) {
	    		JSONArray countryJa = new JSONArray();
	    		for (int j=0;j<countryList.size();j++) {
	    			LvArea areaCountry = countryList.get(j);
	    			//生成国家目录
	    			String langDirPath = areaFileName+area_prev+langList[i]+"\\";
	    			String countryDirPath = langDirPath+areaCountry.getId()+"\\";
					File fCountry = new File(countryDirPath);
					fCountry.mkdir();
					
	    			JSONObject joCountry = new JSONObject();
	    			joCountry.put("id", areaCountry.getId());
	    			if (i==0) {
	    				joCountry.put("name", areaCountry.getNamecn());
	    				joCountry.put("nameCn", areaCountry.getNamecn());
	    				joCountry.put("nameTw", areaCountry.getNametw());
	    			} else if (i==1) {
	    				joCountry.put("name", areaCountry.getNameen());
	    				joCountry.put("nameCn", areaCountry.getNamecn());
	    				joCountry.put("nameTw", areaCountry.getNametw());
	    			} else if (i==2) {
	    				joCountry.put("name", areaCountry.getNametw());
	    				joCountry.put("nameCn", areaCountry.getNamecn());
	    				joCountry.put("nameTw", areaCountry.getNametw());
	    			} else if (i==3) {
	    				joCountry.put("name", areaCountry.getNamekn());
	    				joCountry.put("nameCn", areaCountry.getNamecn());
	    				joCountry.put("nameTw", areaCountry.getNametw());
	    			}
	    			joCountry.put("icon", areaCountry.getIcon());
	    			joCountry.put("code", areaCountry.getCode());
	    			countryJa.add(joCountry);
	    			//生成省数据
	    			JSONArray provinceJa = new JSONArray();
	    			List<LvArea> areaProvinceList = countryProvinceMap.get(areaCountry.getId());
	    			if (areaProvinceList == null) {
						File f1 = new File(areaFileName+area_prev+langList[i]+"\\"+areaCountry.getId()+".js");
						try {
							f1.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}    		
						Writer writer = new OutputStreamWriter(new FileOutputStream(f1), "UTF-8");
						writer.write("var provinceData=[]");
						writer.close();		    				
	    				continue;
	    			}
	    			for (int k=0;k<areaProvinceList.size();k++) {
		    			LvArea areaProvince = areaProvinceList.get(k);
		    			JSONObject joProvince = new JSONObject();
		    			joProvince.put("id", areaProvince.getId());
		    			if (i==0) {
		    				joProvince.put("name", areaProvince.getNamecn());
		    				joProvince.put("nameCn", areaProvince.getNamecn());
		    				joProvince.put("nameTw", areaProvince.getNametw());
		    			} else if (i==1) {
		    				joProvince.put("name", areaProvince.getNameen());
		    				joProvince.put("nameCn", areaProvince.getNamecn());
		    				joProvince.put("nameTw", areaProvince.getNametw());
		    			} else if (i==2) {
		    				joProvince.put("name", areaProvince.getNametw());
		    				joProvince.put("nameCn", areaProvince.getNamecn());
		    				joProvince.put("nameTw", areaProvince.getNametw());
		    			} else if (i==3) {
		    				joProvince.put("name", areaProvince.getNamekn());
		    				joProvince.put("nameCn", areaProvince.getNamecn());
		    				joProvince.put("nameTw", areaProvince.getNametw());
		    			}
		    			joProvince.put("icon", areaProvince.getIcon());
		    			joProvince.put("code", areaProvince.getCode());
		    			provinceJa.add(joProvince);	   
		    			//生成城市数据
		    			JSONArray cityJa = new JSONArray();
		    			List<LvArea> areaCityList = provinceCityMap.get(areaProvince.getId());
		    			if (areaCityList == null) {
							File fProvince = new File(countryDirPath+"\\"+areaProvince.getId()+".js");
							try {
								fProvince.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}    		
							Writer writer = new OutputStreamWriter(new FileOutputStream(fProvince), "UTF-8");
							writer.write("var cityData=[];");
							writer.close();		    				
		    				continue;
		    			}
		    			for (int m=0;m<areaCityList.size();m++) {
			    			LvArea areaCity = areaCityList.get(m);
			    			JSONObject joCity = new JSONObject();
			    			joCity.put("id", areaCity.getId());
			    			if (i==0) {
			    				joCity.put("name", areaCity.getNamecn());
			    				joCity.put("nameCn", areaCity.getNamecn());
			    				joCity.put("nameTw", areaCity.getNametw());
			    			} else if (i==1) {
			    				joCity.put("name", areaCity.getNameen());
			    				joCity.put("nameCn", areaCity.getNamecn());
			    				joCity.put("nameTw", areaCity.getNametw());
			    			} else if (i==2) {
			    				joCity.put("name", areaCity.getNametw());
			    				joCity.put("nameCn", areaCity.getNamecn());
			    				joCity.put("nameTw", areaCity.getNametw());
			    			} else if (i==3) {
			    				joCity.put("name", areaCity.getNamekn());
			    				joCity.put("nameCn", areaCity.getNamecn());
			    				joCity.put("nameTw", areaCity.getNametw());
			    			}
			    			joCity.put("icon", areaCity.getIcon());
			    			joCity.put("code", areaCity.getCode());
			    			cityJa.add(joCity);
		    			}
						File fProvince = new File(countryDirPath+"\\"+areaProvince.getId()+".js");
						try {
							fProvince.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}    		
						Writer writer = new OutputStreamWriter(new FileOutputStream(fProvince), "UTF-8");
						writer.write("var cityData="+cityJa.toString()+";");
						writer.close();			    			
	    			}
					File f1 = new File(areaFileName+area_prev+langList[i]+"\\"+areaCountry.getId()+".js");
					try {
						f1.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}    		
					Writer writer = new OutputStreamWriter(new FileOutputStream(f1), "UTF-8");
					writer.write("var provinceData="+provinceJa.toString()+";");
					writer.close();		    			
	    		}
				File f1 = new File(areaFileName+area_prev+langList[i]+"\\country.js");
				try {
					f1.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}    		
				Writer writer = new OutputStreamWriter(new FileOutputStream(f1), "UTF-8");
				writer.write("var countryData="+countryJa.toString()+";");
				writer.close();
			}
			System.out.println("生成区域js文件结束============================");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void deleteDir(String filepath) throws IOException {
		File f = new File(filepath);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0) {
				f.delete();
			} else {
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteDir(delFile[j].getAbsolutePath());
					}
					delFile[j].delete();
				}
			}
		}
	}	
}
