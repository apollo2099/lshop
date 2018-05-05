package com.lshop.manage.lvArea.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvOrder.action.LvOrderAction;

@Controller("LvAreaAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvAreaAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvAreaAction.class);
	private Integer areaId;
	/**
	 * 
	 * @Method: toArea 
	 * @Description:  [根据区域编号查询区域信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-28 上午11:06:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-28 上午11:06:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String toArea(){
		PrintWriter out = null;
		if(ObjectUtils.isNotEmpty(areaId)){
			try {
				List<LvArea> areaList= LvAreaCache.list;
				for (int i = 0; i < areaList.size(); i++) {
					LvArea area=areaList.get(i);
					if(areaId.equals(area.getId())){
						response.setContentType("text/html;charset=UTF-8");   
						response.setCharacterEncoding("UTF-8");
					    out=this.getResponse().getWriter();
					    String jsonTmp = JSONObject.fromObject(area).toString();
					    logger.info("json_message"+jsonTmp);
						out.print(jsonTmp );
						out.close();
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return null;
	}
	
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	

}
