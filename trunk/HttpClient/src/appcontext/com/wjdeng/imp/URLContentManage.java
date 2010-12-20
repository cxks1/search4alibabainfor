/******************************************************************************** 
 * Create Author   : JoveDeng
 * Create Date     : Apr 22, 2010
 * File Name       : URLContentUtils.java
 *
 *
 ********************************************************************************/
package com.wjdeng.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.wjdeng.URLContent;
import com.wjdeng.client.util.LogUtil;
import com.wjdeng.client.util.SysUtils;

public class URLContentManage implements URLContent {

	private HttpClient client = new DefaultHttpClient();

	public static String KEY_CONTENT = "content";

	public static String KEY_CHARSET = "CharSet";

	public Map<String, Object> getContentByURL(String url, boolean get)
			throws ClientProtocolException, IOException ,Exception{
		if (!get) {
			return this.getContentByURL(url);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(url==null)return map;
		HttpGet httget = new HttpGet(url);
		HttpResponse response;
		response = client.execute(httget);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			map.put(KEY_CONTENT, EntityUtils.toString(entity));
			map.put(KEY_CHARSET, EntityUtils.getContentCharSet(entity));
		}
		this.clearMuCookie();
		LogUtil.getLogger(this.getClass().getSimpleName()).warn("获取" + url + "内容成功！");
		return map;
	}
	
	/**
	 * 
	 * 清理混淆cooke
	 */
	private void clearMuCookie(){
		List<Cookie> list  = ((AbstractHttpClient) client).getCookieStore().getCookies();
		StringBuilder sb  = new StringBuilder();
		String sid = "";
		String name  ="";
		BasicCookieStore bcook = new BasicCookieStore();
		((AbstractHttpClient) client).setCookieStore(bcook);
		for(int i=0 ;i<list.size();i++){
			Cookie  cook = list.get(i);
			name  = cook.getName();
			if("JSESSIONID".equals(name)){
				if("".equals(sid)){
					sid = cook.getValue();
				}else{
					continue;
				}
			}
			bcook.addCookie(cook);
			sb.append(cook.getName()).append("=").append(cook.getValue()).append(";");
		}
		System.out.println(sb.toString());
	}

	public Map<String, Object> getContentByURL(String url)
			throws ClientProtocolException, IOException,Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(url==null)return map;
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		String urltem = this.setPairByUrl(url, nvps);
		HttpPost httpost = new HttpPost(urltem);
		HttpResponse response;
		httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		response = client.execute(httpost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			map.put(KEY_CONTENT, EntityUtils.toString(entity));
			map.put(KEY_CHARSET, EntityUtils.getContentCharSet(entity));
		}
		clearMuCookie();
		//LogUtil.getLogger(this.getClass().getSimpleName()).warn(url);
		return map;
	}

	private String setPairByUrl(String url, List<NameValuePair> list) {
		String[] sta = url.split("&");
		for (int i = 1; i < sta.length; i++) {
			String[] params = sta[i].split("=");
			String name = params[0];
			String value = "";
			if (params.length > 1)
				value = params[1];
			list.add(new BasicNameValuePair(name, value));

		}
		return sta[0];
	}

	public static void main(String[] arg) {
		List<String> list = new ArrayList<String>();
		list.add("http://www.alibaba.com/");
		/*
		list.add("http://192.168.0.126:8080/MainFrame");
		list.add("http://192.168.0.126:8080/MainFrame");
		list.add("http://192.168.0.126:8080/MainFrame");
		list.add("http://192.168.0.126:8080/MainFrame");
		list.add("http://192.168.0.126:8080/MainFrame");
		list.add("http://192.168.0.126:8080/MainFrame");*/
		URLContentManage um = new URLContentManage();
		for (String url : list) {
			try {
				Map<String, Object> map = um.getContentByURL(url, true);
				Source s = new Source(map.get(KEY_CONTENT).toString());
				Element el =s.getFirstElement("body").getFirstElementByClass("homeL");
				SysUtils.wirtfile(el.getTextExtractor().toString());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}