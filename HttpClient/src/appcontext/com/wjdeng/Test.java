/******************************************************************************** 
 * Create Author   : JoveDeng
 * Create Date     : Apr 22, 2010
 * File Name       : Test.java
 *
 ********************************************************************************/
package com.wjdeng;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test {

	private String test() throws Exception {
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("", ""));
		HttpGet get = new HttpGet(
				"http://www.alibaba.com/trade/search?SearchText=car&Country=&CatId=100001627&IndexArea=product_en&sq=y");
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String str = EntityUtils.toString(entity);
			System.out.println(str);
			System.out.println(EntityUtils.getContentCharSet(entity));
			return str;
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fn ="F:\\proset\\temp";
		File f = new File(fn);
		File[] fs =f.listFiles();
		for(File t :fs){
			t.renameTo(new File(fn+"\\"+t.getName()+".jpg"));
		}
		
//		Test test = new Test();
//		try {
//
//			test.connetctionIp();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	private void connetctionIp(){
        try {
            URI url  = new URI("http://wjdeng.gicp.net:25");
            URLConnection con = url.toURL().openConnection();
            InputStream input  = con.getInputStream();
            //Socket sk = new Socket("192.168.0.126",80);
            //InputStream input  = sk.getInputStream();
            Scanner sc = new Scanner(input);
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            //sk.connect(endpoint)
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //SocketAddress  sadd  = 
        //sk.connect(endpoint)
    }
    


}
