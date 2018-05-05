package com.lshop.manage.lvOrder.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.Constants;
import com.lshop.emailsend.service.EmailSendService;
import com.lshop.manage.lvAccount.service.LvAccountService;
import com.lshop.manage.lvOrder.service.LvEmailService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.message.service.impl.OrderMsgServiceImpl;

@Service("LvEmailService")
public class LvEmailServiceImpl implements LvEmailService {
	private static final Log logger = LogFactory.getLog(LvEmailServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService lvOrderService;
	@Resource
	private EmailSendService emailSendService;
	@Resource
	private LvAccountService lvAccountService;

	@Override
	public LvEmailTpl get(Dto dto) throws ServiceException {
		LvEmailTpl tpl=null;
		String key=dto.getAsString("tplKey");
		String storeFlag=dto.getAsString("flag");
		String hql="FROM LvEmailTpl WHERE tplKey=:key and storeId=:storeId";
		Map param=new HashMap();
		param.put("key", key);
		param.put("storeId", storeFlag);
		List<LvEmailTpl> list=dao.find(hql, param);
		if(list!=null&&!list.isEmpty()){
			tpl=list.get(0);
			
		}
		return tpl;
	}
	public Integer doNoticeFa(Dto dto) throws Exception{
		logger.info("*****LvEmailServiceImpl.doNoticeFa() method begin*****");
		String key="FA_HUO_NOTICE";
		dto.put("tplKey", key);
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		LvOrder orderTmp=lvOrderService.getOrder(dto);
		dto.put("flag", orderTmp.getStoreId());
		LvEmailTpl lvEmailTpl=get(dto);
		
		
		
		//紧急处理策略，第三方店铺不发送邮件
		if(!orderTmp.getStoreId().equals("third")){
			dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(orderTmp.getStoreId()));
			dto.put("title", lvEmailTpl.getEnTitle().replace("{oid}", lvOrder.getOid()));// 邮件标题
			dto.put("userEmail", orderTmp.getUserEmail());// 用户email
			LvAccount lvAccount=lvAccountService.getAccountByEmail(dto);
			
			String content=(lvEmailTpl.getEn()+lvEmailTpl.getZh())
			.replace("{sendtime}", StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"))
			.replace("{oid}", lvOrder.getOid()!=null?lvOrder.getOid():"")
			.replace("{currency}", orderTmp.getCurrency()!=null?orderTmp.getCurrency():"")
			.replace("{totalPrice}", orderTmp.getTotalPrice()!=null?orderTmp.getTotalPrice()+"":"")
			.replace("{expressCompany}", lvOrder.getExpressCompany()!=null?lvOrder.getExpressCompany():"")
			.replace("{expressNum}", lvOrder.getExpressNum()!=null?lvOrder.getExpressNum():"");
			if(lvAccount!=null){
				content=content.replace("{relName}", lvAccount.getNickname()!=null?lvAccount.getNickname():"");
			}
			
			dto.put("content", content);// 邮件内容
			emailSendService.sendEmailNotice(dto);
			
			logger.info("*****发货邮件发送成功,订单号="+orderTmp.getOid());
		}else{
			
			logger.info("*****第三方店铺不发送发货邮件通知,订单号="+orderTmp.getOid());
		}
		logger.info("*****LvEmailServiceImpl.doNoticeFa() method end*****");
		return 1;
	}

}

