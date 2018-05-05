package com.lshop.common.https;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import com.lshop.common.util.Constants;





public class HTTPSSecureProtocolSocketFactory implements ProtocolSocketFactory {// SecureProtocolSocketFactory

    /**
     * 
     */
    static {
        // //System.out.println(">>>>in MySecureProtocolSocketFactory>>");
    }
    private SSLContext sslcontext = null;

    private SSLContext createSSLContext() {
//		//System.out.println("ctx--sdfasfd");
        SSLContext sslcontext = getSslContext();
        return sslcontext;
    }
    
    public SSLContext getSslContext() 
    {
    	String key_dir=Constants.HTTPS_SERVER_KEY_DIR;
    	String tru_dir=Constants.HTTPS_TRUST_DIR;
    	  String PWD=Constants.HTTPS_SERVER_KEY_PWD;
    	  String CLIENT_PWD=Constants.HTTPS_TRUST_PWD;
    	  
    	  
    	  SSLContext ctx = null;  
    	  try{
				ctx = SSLContext.getInstance("SSL");
		        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");  
		        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");  
		        KeyStore ks = KeyStore.getInstance("JKS");
		        KeyStore tks = KeyStore.getInstance("JKS");  
				ks.load(new FileInputStream(key_dir),CLIENT_PWD.toCharArray());
		        tks.load(new FileInputStream(tru_dir),PWD.toCharArray());  
				kmf.init(ks, CLIENT_PWD.toCharArray());
		        tmf.init(tks);  
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
    	  }
    	  catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}  catch (KeyStoreException e) {
				e.printStackTrace();
			}  catch (CertificateException e) {
				e.printStackTrace();
			}catch (KeyManagementException e) {
				e.printStackTrace();
			}  catch (UnrecoverableKeyException e) {
				e.printStackTrace();
			} 
			catch(IOException e){
				e.printStackTrace();
    	  }

        return ctx;
    }
    
    

    private SSLContext getSSLContext() {
        if (null == this.sslcontext) {
            this.sslcontext = createSSLContext();
        }
        return this.sslcontext;
    }

	@Override
	public Socket createSocket(String host, int port, InetAddress clientHost,
            int clientPort, HttpConnectionParams arg4) throws IOException,
			UnknownHostException, ConnectTimeoutException {
        return getSSLContext().getSocketFactory().createSocket(host, port,
                clientHost, clientPort);
	}
    
    public Socket createSocket(Socket socket, String host, int port,
            boolean autoClose) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(socket, host,
                port, autoClose);
    }

    public Socket createSocket(String host, int port) throws IOException,
            UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port);
    }

    public Socket createSocket(String host, int port, InetAddress clientHost,
            int clientPort) throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port,
                clientHost, clientPort);
    }



    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }
    public static void main(String[] args) throws HttpException, IOException {
    	
    	Protocol https = new Protocol("https",
    			new HTTPSSecureProtocolSocketFactory(), 443);
    	Protocol.registerProtocol("https", https);
    	for (int i = 0; i < 10; i++) {
			String url="https://10.0.2.148:8459/api/userupdatestaus?usercode=1&status=2";
	        GetMethod get = new GetMethod(url);
	        HttpClient client = new HttpClient();
	        int statusCode =client.executeMethod(get);
	        //获取访问状态值
	        // 状态码200表示请求成功
	        if (HttpStatus.SC_OK == statusCode) {
	        	//System.out.println(get.getResponseBodyAsString());
	        }
//	        Protocol.unregisterProtocol("https");
    	}
    }


}

