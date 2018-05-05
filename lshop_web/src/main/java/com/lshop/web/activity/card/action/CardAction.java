package com.lshop.web.activity.card.action;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Map;





import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;






import com.gv.core.exception.ActionException;
import com.gv.core.util.FileUpload;
import com.gv.core.datastructure.Dto;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvMappCardsTmp;
import com.lshop.common.pojo.logic.LvMappCardsUser;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.PngFileWriter;
import com.lshop.common.util.PropertiesHelper;

@Controller("CardAction")
@Scope("prototype")
public class CardAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private File img;
	private String imgFileName;
	private LvMappCardsUser  cardsUser;
	private String code;
	private Integer type;
	
	public String card(){
//		String code = "096b230670e647cebcb0487c75110264";
		String code = this.getRequest().getParameter("code");
		code = code==null?"":code;
		dto.put("code", code);
		LvMappCardsTmp model = (LvMappCardsTmp)this.doService("CardService", "getCardByName", dto);
		if( null == model ){
			PrintWriter out = null;
			try {
				out = this.getResponse().getWriter();
				out.print("No matching template!");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		this.getRequest().setAttribute("resRoot", this.getResDomain()+"/"+this.getFlag()+"/res/card");
		this.getRequest().setAttribute("model", model);
		return "card";
	}
	
	public String docard() throws ActionException{
		type=1;
		dto.put("code", code);
		LvMappCardsTmp model = (LvMappCardsTmp)this.doService("CardService", "getCardByName", dto);
		String imgName=this.getRequest().getParameter("imgName");
		if(imgName!=null&&!"".equals(imgName.trim())){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	    int num=new Random().nextInt(10000);//产生3位随机数
	    while(num<1000){
	    	num=new Random().nextInt(10000);
	    }
		String fileName=sf.format(new Date())+num+".png";
		String rootPath=PropertiesHelper.getProperty("res.domain.path")+"/"+this.getFlag()+"/res/card";
		String path=rootPath+"/uploadimg/"+fileName ;
		PngFileWriter png=new PngFileWriter();
		List list=new ArrayList<File>();
		list.add(new File(rootPath+"/uploadimg/"+imgName));//获取上传的图片
	    String imgBPath=rootPath+"/"+model.getCssName()+"/images/aboveTop1.png";
	    list.add(new File(imgBPath));
	    String[] p=this.getRequest().getParameter("P").split(",");
	    String[] s=this.getRequest().getParameter("S").split(",");
	    int x=Integer.parseInt(p[0]);
	    int y=Integer.parseInt(p[1]);
	    int w=Integer.parseInt(s[0]);
	    int h=Integer.parseInt(s[1]);
	    int phoneW=Integer.parseInt(this.getRequest().getParameter("W"));
	    int imageD=Integer.parseInt(this.getRequest().getParameter("D"));
		png.append(list, path,x,y,w,h,phoneW,imageD,true);//输出合成图
		cardsUser.setImgUrl(fileName);
		}
		cardsUser.setCreateTime(new Date());
		cardsUser.setCode(CodeUtils.getCode());
		cardsUser.setTempCode(model.getCode());
		dto.put("model", cardsUser);
		this.doService("CardService", "save", dto);
		code=cardsUser.getCode();
		return "docard";  
	}
	
	public String getList(){
		List<LvMappCardsTmp> list = (List<LvMappCardsTmp>)this.doService("CardService", "getList", dto);
		this.getRequest().setAttribute("tempList", list);
		return "getList";
	}
	
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	public LvMappCardsUser getCardsUser() {
		return cardsUser;
	}
	public void setCardsUser(LvMappCardsUser cardsUser) {
		this.cardsUser = cardsUser;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String uploadImg() throws IOException{
		PrintWriter out= this.getResponse().getWriter();
		JSONObject json=new JSONObject();
		json.put("imgName", "");
		if(img!=null){
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		    int num=new Random().nextInt(10000);//产生3位随机数
		    while(num<1000){
		    	num=new Random().nextInt(10000);
		    }
			String fileName="I"+sf.format(new Date())+num+".png";
			String path=PropertiesHelper.getProperty("res.domain.path")+"/"+this.getFlag()+"/res/card/uploadimg/";
			FileUpload.upload(img, path, fileName);
			json.put("imgName", fileName);
		}
		out.print(json.toString());
		return null;
	}
	public String cardview(){
		String type="0";//默认不显示分享提示
		String code=this.getRequest().getParameter("code");
		 type=this.getRequest().getParameter("type");
		dto.put("code", code);
		Dto result = (Dto)this.doService("CardService", "getUserCard", dto);
		Map<String,Object> map=(Map<String,Object>)result.get("model");
		if(map==null){
			return "card";
		}
		map.put("type",type );
		this.getRequest().setAttribute("resRoot", this.getResDomain()+"/"+this.getFlag()+"/res/card/"+map.get("cssName"));
		this.getRequest().setAttribute("model", result.get("model"));
		return "view";
	}
	
}
