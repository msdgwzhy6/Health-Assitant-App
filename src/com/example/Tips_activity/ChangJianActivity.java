package com.example.Tips_activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.assistant.R;

public class ChangJianActivity extends Activity{
	private String[] names = new String []
			{"烫伤","牙疼","鱼骨卡吼","异物入眼","中暑"};
	
	private  int[] imageIds = new int[]
			{ R.drawable.jijiu,  R.drawable.jijiu,  R.drawable.jijiu,  R.drawable.jijiu, R.drawable.jijiu };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.activity_changjian);
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0; i<names.length;i++){
        	Map<String,Object> listItem = new HashMap<String,Object>();
        	listItem.put("header",imageIds[i]);
        	listItem.put("personName",names[i]);
        	
        	listItems.add(listItem);
        }
//        创建一个simpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.celan_item,
        		new String[] {"personName","header"},
        		//new String[] {"personName","header"},
        		new int[] {R.id.name,R.id.header});
        		//new int[] {R.id.name,R.id.header});
        ListView list = (ListView) findViewById(R.id.listietem);
//        为ListView设置Adapter
        list.setAdapter(simpleAdapter);
        list.setCacheColorHint(Color.TRANSPARENT); //消除listview滑动缓存
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				if(position == 0){
					Intent classfyIntent = new Intent(ChangJianActivity.this, TangActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 1){
					Intent classfyIntent = new Intent(ChangJianActivity.this, YaActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 2){
					Intent classfyIntent = new Intent(ChangJianActivity.this,YuguActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 3){
					Intent classfyIntent = new Intent(ChangJianActivity.this,YiwuActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 4){
					Intent classfyIntent = new Intent(ChangJianActivity.this, ZhongshuActivity.class);
			         startActivity(classfyIntent);
				}
				
			}
		});
	}
	
	
}
