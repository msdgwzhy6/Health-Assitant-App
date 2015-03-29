package com.example.push;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.assistant.R;
import com.example.http.HttpUtil;

public class PushActivity extends Activity {
	String url;
	private static final String URL = "http://192.168.1.13:8080";
	TextView push_tv;
	
	private Handler mHandler = new Handler() {  
        public void handleMessage (Message msg) {//此方法在ui线程运行  
            Bundle b = msg.getData();  
            url = b.getString("url");  
            String content = b.getString("content");  
            System.out.println("url->" + url +",content-->" + content);  
            push_tv.setText(content);
        }  
    }; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push);
		
		push_tv = (TextView)findViewById(R.id.push_tv);
		
		push_tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {   
			    Intent intent = new Intent(Intent.ACTION_VIEW);
			    if(url != null) {
				    intent.setData(Uri.parse(url));
				    startActivity(intent);
			    }
			}
		});
		
		init_push();
	}

	private void init_push() {
		PushThread p = new PushThread();
		Thread thread = new Thread(p);
		thread.start();
	}
    
    class PushThread implements Runnable {
		public void run() {
			String pushContent = HttpUtil.sendGet(URL, null);
			System.out.println(pushContent);
			//tv.setText(pushContent);
			if(pushContent == "error") {
				Message msg = mHandler.obtainMessage(); 
				Bundle b = new Bundle();  
			    b.putString("content", "请确保网络连接正确！\n请确保服务器开启！");  			 
			    msg.setData(b);  
			    msg.sendToTarget(); 
			}
			else {
				//提取抓取到的<p>. </p>之间的内容
				String regex="<p.*?>(.*?)</p> ";
				Pattern p =Pattern.compile(regex);
				Matcher m=p.matcher(pushContent);
				String s = null;
				while(m.find()){
					s = m.group(1);
				}   	
				//System.out.println(s);
				String s1=s.substring(s.indexOf("[")+1, s.lastIndexOf("]"));
				String [] a=s1.split(",");
				String s2 = "http://www.54590.cn" + a[0];
				String s3 = a[1];
				//System.out.println(s2);
				//System.out.println(s3);
				
				Message msg = mHandler.obtainMessage(); 
				Bundle b = new Bundle();  
			    b.putString("url", s2);  
			    b.putString("content", s3);  
			    msg.setData(b);  
			    msg.sendToTarget(); 		
			}
		}
    }
	
}
