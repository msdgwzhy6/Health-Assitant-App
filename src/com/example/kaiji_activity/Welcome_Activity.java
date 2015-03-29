package com.example.kaiji_activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.assistant.R;

/**开场欢迎动画**/
public class Welcome_Activity  extends Activity{
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		Toast toast = Toast.makeText(Welcome_Activity.this,"记得联网哦(*^__^*) ",Toast.LENGTH_LONG);
		toast.show();
		//延迟俩秒执行run方法之后的页面跳转
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				/*Intent intent = new Intent(Welcome_Activity.this,Viewpage_Activity.class);
				startActivity(intent);
				
				Welcome_Activity.this.finish();*/
				SharedPreferences setting = getSharedPreferences("CitiGame.ini", 0);
				Boolean user_first = setting.getBoolean("FIRST",true);
				if(user_first){//第一次
				setting.edit().putBoolean("FIRST", false).commit();
				Intent intent = new Intent(Welcome_Activity.this,Viewpage_Activity.class);
				startActivity(intent);
				
				Welcome_Activity.this.finish();
				}else{
				Intent intent = new Intent(Welcome_Activity.this, Donghua_Activity.class);
				startActivity(intent);
				finish();
				}
				
			}
			
		},2000);
	}
	
	
		

}
