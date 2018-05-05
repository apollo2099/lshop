package com.lshop.web.userCenter.service.impl;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.pojo.logic.LvLogistics;
import com.lshop.common.util.DateUtils;

/**
 * 物流信息
 * @author zhengxue
 * @since 2.0 2012-08-25
 *
 */
@Service("DHLLogisticsTrackingService")
public class DHLLogisticsTrackingServiceImpl extends LogisticsTrackingServiceImpl {
   
	private static String  dataformString="yyyy-MM-dd'T'HH:mm:ss";
	@SuppressWarnings("unchecked")
	@Override
	public Dto doLogisticsTracking(Dto dto) {
		String key = dto.getAsString("key");
		LvLogistics lvLogisticsr = getLogisticsrInfoByKey(key);
		String expressNum = dto.getAsString("expressNum");
		dto.setDefaultPo(lvLogisticsr);
		if (lvLogisticsr != null) {
			try {
				String str = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><req:KnownTrackingRequest xmlns:req=\"http://www.dhl.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.dhl.com TrackingRequestKnown.xsd\"><Request><ServiceHeader><MessageTime>").append(DateUtils.formatDate(new Date(), dataformString)).append("+08:00</MessageTime><MessageReference>1234567890123456789012345678901</MessageReference><SiteID>").append(lvLogisticsr.getUid()).append("</SiteID><Password>").append(lvLogisticsr.getPwd()).append("</Password></ServiceHeader></Request><LanguageCode>ZH</LanguageCode><AWBNumber>").append(expressNum).append("</AWBNumber><LevelOfDetails>ALL_CHECK_POINTS</LevelOfDetails></req:KnownTrackingRequest>").toString();

				/* Preparing the URL and opening connection to the server */
				URL servletURL = null;
				servletURL = new URL(lvLogisticsr.getUrl());

				HttpURLConnection servletConnection = null;
				servletConnection = (HttpURLConnection) servletURL.openConnection();
				servletConnection.setDoOutput(true); // to allow us to write
														// to the URL
				servletConnection.setDoInput(true);
				servletConnection.setUseCaches(false);
				servletConnection.setRequestMethod("POST");

				servletConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				String len = Integer.toString(str.getBytes().length);
				servletConnection.setRequestProperty("Content-Length", len);
				servletConnection.setReadTimeout(10000);
				servletConnection.connect();
				OutputStreamWriter wr = new OutputStreamWriter(servletConnection.getOutputStream());
				wr.write(str);
				wr.flush();
				wr.close();

				InputStream inputStream = null;
				inputStream = servletConnection.getInputStream();
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = factory.newDocumentBuilder();
				Document doc = documentBuilder.parse(inputStream);

				inputStream.close();
				return parseDocument(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;

	}

	@SuppressWarnings("unchecked")
	private Dto parseDocument(Document doc) {
		Dto dto = new BaseDto();
		String status = "error";
		List<Map<String, Object>> events=null;
		if (doc != null) {
			status = doc.getElementsByTagName("ActionStatus").item(0).getTextContent();
			if ("success".equals(status.trim())) {
				Element element = doc.getDocumentElement(); // getting the root
				dto.put("AWBNumber", element.getElementsByTagName("AWBNumber").item(0).getTextContent());
				dto.put("consigneeName", element.getElementsByTagName("ConsigneeName").item(0).getTextContent());
				dto.put("shipmentDate", DateUtils.parseDateTime(element.getElementsByTagName("ShipmentDate").item(0).getTextContent(), dataformString));
				dto.put("originServiceArea", element.getElementsByTagName("OriginServiceArea").item(0).getTextContent());
				dto.put("destinationServiceArea", element.getElementsByTagName("DestinationServiceArea").item(0).getTextContent());
				dto.put("flag", element.getElementsByTagName("DlvyNotificationFlag").item(0).getTextContent());
				dto.put("pieces", element.getElementsByTagName("Pieces").item(0).getTextContent());
				NodeList shipmentEvent = doc.getElementsByTagName("ShipmentEvent");
				int shipmentEventSize = shipmentEvent.getLength();
				events = new ArrayList<Map<String, Object>>(shipmentEventSize);
				for (int i = 0; i < shipmentEventSize; i++) {
					Element e = (Element) shipmentEvent.item(i);
					Map<String, Object> event = new HashMap<String, Object>();
					event.put("date", DateUtils.convertToDate(e.getElementsByTagName("Date").item(0).getTextContent()));
					event.put("time",DateUtils.parseDateTime( e.getElementsByTagName("Time").item(0).getTextContent(), "HH:mm:ss"));
					event.put("serviceEvent", ((Element) e.getElementsByTagName("ServiceEvent").item(0)).getElementsByTagName("Description").item(0).getTextContent());
					event.put("serviceArea", ((Element) e.getElementsByTagName("ServiceArea").item(0)).getElementsByTagName("Description").item(0).getTextContent());
					events.add(event);
				}

			}
		}
		dto.put("status", status);
		dto.put("events", events);
		return dto;
	}

}
