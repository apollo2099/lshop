/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvExchangeRate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.util.Constants;
import com.lshop.manage.lvExchangeRate.service.LvExchangeRateService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvExchangeRateService")
public class LvExchangeRateServiceImpl extends BaseServiceImpl implements LvExchangeRateService {
	    @Resource 
	    private HibernateBaseDAO dao; 
		@Resource
		private JmsMessageQueueSender messageQueueSenderLshopManager;
	/**
	 * 获得所有
	 */
	
	public List<LvExchangeRate> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvExchangeRate lvExchangeRate = (LvExchangeRate)dto.get("model");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvExchangeRate t where 1=1 ");
          if(lvExchangeRate!=null){
	        	if(ObjectUtils.isNotEmpty(lvExchangeRate.getCurrency())) {
		        	sql.append(" and  t.currency=:currency ");
		        	params.put("currency", lvExchangeRate.getCurrency());
		        }
	        	if(ObjectUtils.isNotEmpty(lvExchangeRate.getMainCurrency())) {
		        	sql.append(" and  t.mainCurrency=:mainCurrency ");
		        	params.put("mainCurrency", lvExchangeRate.getMainCurrency());
		        }
          }
        sql.append(" order by t.createTime desc");
        Finder finder = Finder.create(sql.toString());
        finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvExchangeRate get(Dto dto) throws ServiceException {
		 LvExchangeRate lvExchangeRate = (LvExchangeRate)dto.get("model");
		 lvExchangeRate = (LvExchangeRate)dao.load(LvExchangeRate.class, lvExchangeRate.getId());
		return  lvExchangeRate;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvExchangeRate lvExchangeRate = get(dto);
		dao.delete(  lvExchangeRate);
	}

	/**
	 * 保存
	 */
	public LvExchangeRate save(Dto dto) throws ServiceException {
		LvExchangeRate lvExchangeRate = (LvExchangeRate) dto.get("model");
		dao.save(lvExchangeRate);
		
		//目前只是处理人民币与美元的兑换
		if(lvExchangeRate.getCurrency().equals("CNY")&&lvExchangeRate.getMainCurrency().equals("USD")){
			Constants.rateNum=lvExchangeRate.getExchangeRate();
		}
		//处理V币与美元的兑换
		if(lvExchangeRate.getCurrency().equals("CNY")&&lvExchangeRate.getMainCurrency().equals("VB")){
			Constants.rateVbNumCny=lvExchangeRate.getExchangeRate();
		}
		//处理V币与人民币的兑换
		if(lvExchangeRate.getCurrency().equals("USD")&&lvExchangeRate.getMainCurrency().equals("VB")){
			Constants.rateVbNum=lvExchangeRate.getExchangeRate();
		}
		//向前端用户发送mq数据
		this.sendJSONToWeb();
		return lvExchangeRate;
	}
	/**
	 * 更新
	 */
	public LvExchangeRate update(Dto dto) throws ServiceException {
		 LvExchangeRate lvExchangeRate = (LvExchangeRate)dto.get("model");
		dao.update(lvExchangeRate);
		
		if(lvExchangeRate.getCurrency().equals("CNY")&&lvExchangeRate.getMainCurrency().equals("USD")){
			Constants.rateNum=lvExchangeRate.getExchangeRate();
		}
		//处理V币与美元的兑换
		if(lvExchangeRate.getCurrency().equals("CNY")&&lvExchangeRate.getMainCurrency().equals("VB")){
			Constants.rateVbNumCny=lvExchangeRate.getExchangeRate();
		}
		//处理V币与人民币的兑换
		if(lvExchangeRate.getCurrency().equals("USD")&&lvExchangeRate.getMainCurrency().equals("VB")){
			Constants.rateVbNum=lvExchangeRate.getExchangeRate();
		}
		//向前端用户发送mq数据
		this.sendJSONToWeb();
		return  lvExchangeRate;
	}
	
	/**
     * 
     * @Method: isExistRate 
     * @Description:  [根据币种查询当前币种兑换率是否存在]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-12-18 上午10:25:21]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-12-18 上午10:25:21]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param dto
     * @throws ServiceException 
     * @return Boolean
     */
	public Boolean isExistRate(Dto dto)throws ServiceException{
		LvExchangeRate lvExchangeRate = (LvExchangeRate)dto.get("model");
		String hql="from LvExchangeRate where currency=:currency and mainCurrency=:mainCurrency";
		Map param=new HashMap();
		param.put("currency", lvExchangeRate.getCurrency());
		param.put("mainCurrency", lvExchangeRate.getMainCurrency());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Method: sendJSONToWeb 
	 * @Description:  [向前端系统发送汇率变更信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-22 上午10:36:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-22 上午10:36:53]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	protected void sendJSONToWeb() {
		Map param = new HashMap();
		param.put("rateNum", Constants.rateNum);//美元兑人民币汇率信息
		param.put("rateNumCNY", Constants.rateNumCNY);//人民币对美元汇率信息
		param.put("rateVbNum", Constants.rateVbNum);//V币对美元汇率信息
		param.put("rateVbNumCny", Constants.rateVbNumCny);//V币对人民币汇率信息
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());
	}
}
