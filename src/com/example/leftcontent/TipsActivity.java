package com.example.leftcontent;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.Tips_activity.ChangJianActivity;
import com.example.Tips_activity.JijiuActivity;
import com.example.Tips_activity.XiaoerActiviy;
import com.example.Tips_activity.YiwaiActivity;
import com.example.assistant.R;

public class TipsActivity extends Activity implements OnClickListener{
	private Button bt_label_jijiu;
	private Button bt_label_yiwai;
	private Button bt_label_changjian;
	private Button bt_label_xiaoer;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tips);
		bt_label_jijiu = (Button) this.findViewById(R.id.lable_jijiu);
		bt_label_yiwai = (Button) this.findViewById(R.id.lable_yiwai);
		bt_label_changjian = (Button) this.findViewById(R.id.lable_changjian);
		bt_label_xiaoer = (Button) this.findViewById(R.id.lable_xiaoer);
		
		
	    bt_label_jijiu.setOnClickListener(new View.OnClickListener() {  	    	   
	           @Override  
	           public void onClick(View v) {
	        	   //点击急救宝典进行跳转
	        	   Intent jijiuintent = new Intent(TipsActivity.this, JijiuActivity.class);
	   				startActivity(jijiuintent);
	           }  
	    }); 
	    bt_label_yiwai.setOnClickListener(new View.OnClickListener() {  	    	   
	           @Override  
	           public void onClick(View v) {
	        	   //点击户外意外进行跳转
	        	   Intent yiwaiintent = new Intent(TipsActivity.this, YiwaiActivity.class);
	   				startActivity(yiwaiintent);
	           }  
	    }); 
	    bt_label_changjian.setOnClickListener(new View.OnClickListener() {  	    	   
	           @Override  
	           public void onClick(View v) {
	        	   //点击生活常见进行跳转
	        	   Intent yiwaiintent = new Intent(TipsActivity.this, ChangJianActivity.class);
	   				startActivity(yiwaiintent);
	           }  
	    }); 
	    bt_label_xiaoer.setOnClickListener(new View.OnClickListener() {  	    	   
	           @Override  
	           public void onClick(View v) {
	        	   //点击小儿宝典进行跳转
	        	   Intent yiwaiintent = new Intent(TipsActivity.this, XiaoerActiviy.class);
	   				startActivity(yiwaiintent);
	           }  
	    }); 	    
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
}
