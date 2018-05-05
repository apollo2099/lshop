package com.lshop.common.payutil.payDollar.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.SSLSocketFactory;

public class ServerPost {
	static public String post(String ip_postData, String ip_pageUrl) {

		java.security.Security
				.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		System.setProperty("java.protocol.handler.pkgs",
				"com.sun.net.ssl.internal.www.protocol");
		OutputStream outStream = null;
		InputStream inStream = null;
		URL url = null;
		try {
			String strResult = "";
			url = new URL(ip_pageUrl);

			// URLConnection con = (URLConnection) url.openConnection();

			URLConnection con = url.openConnection(); // from secure site
			if (con instanceof com.sun.net.ssl.HttpsURLConnection) {
				((com.sun.net.ssl.HttpsURLConnection) con)
						.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory
								.getDefault());
			}

			con.setDoOutput(true);
			con.setDoInput(true);

			// Set request headers for content type and length

			con.setRequestProperty("Content-type",
					"application/x-www-form-urlencoded");

			con.setRequestProperty("Content-length", String.valueOf(ip_postData
					.length()));

			//
			// Issue Post & Read the response
			//

			System.out.println("");
			System.out.println("Page Retrieve ...");

			// 鍙戝嚭Post璇锋眰
			outStream = con.getOutputStream();
			outStream.write(ip_postData.getBytes());
			outStream.flush();
			// 璇诲彇搴旂瓟
			inStream = con.getInputStream();
			while (true) {
				int c = inStream.read();
				if (c == -1)
					break;
				strResult = strResult + String.valueOf((char) c);
			}

			return strResult;
		} catch (Exception e) {
			System.out.print(e.toString());
			return "F" + e.toString();
		} finally {

			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {

				}
			}

			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {

				}
			}

		}
	}

}

