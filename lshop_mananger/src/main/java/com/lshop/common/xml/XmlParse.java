package com.lshop.common.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlParse {
	
	public Document doc;
	public Element root;
	
	public XmlParse(String xml) {
		try {
//			xml = xml.replaceAll("\r", "").replace("\n", "");
			doc = DocumentHelper.parseText(xml);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取xml中某个元素的值
	 * @param elementPath	元素路径，如：root.user.name（元素层次之间使用英文点号分隔）
	 * @return	返回元素的值，如果元素不存在返回null
	 */
	public String getElementText(String elementPath) {
		Element e = getElement(elementPath);
		return e == null ? null : e.getText();
	}
	
	/**
	 * 获取某个元素中的某个元素的值
	 * @param element		从这个元素里面查找
	 * @param elementPath	元素路径，如：user.name（元素层次之间使用英文点号分隔）
	 * @return	返回元素的值，如果元素不存在返回null
	 */
	public String getElementText(Element element, String elementPath) {
		Element e = getElement(element, elementPath);
		return e == null ? null : e.getText();
	}
	
	/**
	 * 获取xml中某个元素
	 * @param elementPath	元素路径，如：root.user.name（元素层次之间使用英文点号分隔）
	 * @return
	 */
	public Element getElement(String elementPath) {
		String[] nodeNames = elementPath.split("\\.");
		Element e = null;
		String name = null;
		
		for (int i = 0; i < nodeNames.length; i++) {
			name = nodeNames[i];
			if (i == 0 && name.equals(root.getName())) {
				if (nodeNames.length == 1) {
					return root;
				}
				continue;
			}
			if (e == null) {
				e = root.element(name);
			} else {
				e = e.element(name);
			}
			if (e == null) {
				return null;
			}
		}
		return e;
	}
	
	/**
	 * 获取某个元素中的某个元素
	 * @param element		从这个元素里面查找
	 * @param elementPath	元素路径，如：user.name（元素层次之间使用英文点号分隔）
	 * @return
	 */
	public Element getElement(Element element, String elementPath) {
		String[] nodeNames = elementPath.split("\\.");
		Element e = null;
		String name = null;
		
		for (int i = 0; i < nodeNames.length; i++) {
			name = nodeNames[i];
			if (e == null) {
				e = element.element(name);
			} else {
				e = e.element(name);
			}
			if (e == null) {
				return null;
			}
		}
		return e;
	}
	
//	public void getList() {
//		List list = root.selectNodes("result");
//		Element e = (Element) list.get(0);
//		Iterator<Element> it = e.elementIterator();
//		while(it.hasNext()) {
//			Element e2 = it.next();
//			//System.out.println(e2.getName()+" = "+e2.getText());
//		}
//	}
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
						"<return>"+
							"<result>"+
								"<status>100</status>"+
								"<message></message>"+
							"</result>"+
							"<result>"+
							"<status>200</status>"+
							"<message></message>"+
							"</result>"+
						    "<vbaccount>"+
								"<code>DP7BBET9B13855174649215QCH3UU8C1</code>"+
								"<usercode>6C820AA8929741BFBD6269F7AB18E767</usercode>"+
								"<account>aaa4</account>"+
								"<createtime>2013-11-27 09:57:44.937</createtime>"+
								"<updatetime>1385517464937</updatetime>"+
						    "</vbaccount>"+
						"</return>";
		
		XmlParse parse = new XmlParse(xml);
		String s = parse.getElementText("return.vbaccount.code");
		String s2 = parse.getElementText("vbaccount.code");
		//System.out.println(s);
		//System.out.println(s2);
		
//		parse.getList();
	}
	
}
