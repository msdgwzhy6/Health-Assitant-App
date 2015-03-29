package com.example.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	//Get
	public static String sendGet(String url, String params)
	{
		System.out.println("1");
		String result = "";
		BufferedReader reader = null;
		try {
			URL realUrl = new URL(url + "?" + params);
			
			if(realUrl != null)
			{
				HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
				System.out.println("2");	
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("	", "Keep-Alive");
				conn.setConnectTimeout(4000);
				
				conn.connect();
				System.out.println("3");
				int responseCode =  conn.getResponseCode();
				System.out.println("4");
				System.out.println(responseCode);
				if( responseCode == 200)
				{
					reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
					String line;
					while((line = reader.readLine()) != null)
					{
						result += line;
						
					}
					return result;
				}				
			}
		} catch ( Exception e) {
			System.out.println("发送Get请求出现异常！" + e);
			e.printStackTrace();
			return "error";	

		} finally
		{
			try {
				if(reader != null)
				{
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "error";
					
	}
	
	//Post	
	public static String sendPost(String url, String params)
	{
		System.out.println("1");
		PrintWriter out = null;
		BufferedReader reader = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			if(realUrl != null)
			{
				HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
				System.out.println("2");
				conn.setRequestProperty("accept", "*/*");
				conn.setRequestProperty("connectioln", "Keep-Alive");
				
				conn.setDoOutput(true);
				conn.setDoInput(true);
				
				out = new PrintWriter(conn.getOutputStream());				
				out.print(params);
				out.flush();
				System.out.println("3");
				int responseCode =  conn.getResponseCode();
				System.out.println(responseCode);
				if( responseCode == 200)
				{
					reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
					String line;
					while((line = reader.readLine()) != null)
					{
						result += line;
						
					}
				}
				
			}
		} catch ( Exception e) {
			System.out.println("发送Post请求出现异常！" + e);
			e.printStackTrace();

		} finally
		{
			try {
				if(reader != null)
				{
					reader.close();
				}
				if(out != null)
				{
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
