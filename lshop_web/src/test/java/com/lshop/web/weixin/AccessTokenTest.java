package com.lshop.web.weixin;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lshop.web.weixin.WxConstant;
import com.lshop.web.weixin.message.send.NewsArticlesItem;
import com.lshop.web.weixin.message.send.NewsMessage;
import com.lshop.web.weixin.util.HttpClientUtil;
import com.lshop.web.weixin.util.JAXBUtil;

/**
 * @author jinjian 2014-12-25
 *
 */
public class AccessTokenTest {
	//14:20
	//{"access_token":"Uz8DcC0tokogsOkUWS8G19z7HbpGoIqPhMFW1bDhcnSyGo6TvYT748Cx0RSUghzimOsQTvotaU5UFPpId-Xygr6CoecTf8cD8seSoZWOqD0","expires_in":7200}
	public static final String accessToken = "g3byD0YRwJ1x-bFzO0SvBoJ-Vr7gf-4SXfX2uYpiyMJyo3z2_zdYNEHr_nMXftVCzaYOZTPqakrFzSyneuFBpR3INcPioBWtpVQiq2-bE18";

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void convertToXml(){
/*		String appid = "wx71aae68b689d5a62";
		String secret = "44e614ee784a6ed2bcdc8d10eac49a3e";*/		
		//测试帐号jinjian
		String appid = "wx8aa69e3a9b5c6d66";
		String secret = "c96c85e18d4954f692d26467435b0146";
		//liudm
/*		String appid = "wx4a78e76c3cca70da";
		String secret = "c0d8ecb00ae161d006ab877ddfe40ca3";	*/	
		String url = MessageFormat.format(WxConstant.url_access_token, appid, secret);
		
		System.out.println(HttpClientUtil.get(url));
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
