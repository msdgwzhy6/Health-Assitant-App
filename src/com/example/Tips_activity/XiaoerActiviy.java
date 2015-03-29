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

public class XiaoerActiviy extends Activity{
	private String[] names = new String []
			{"小儿吐奶","小儿高热惊厥","小儿噎食","小儿麻疹","小儿肺炎"};
	
	private  int[] imageIds = new int[]
			{ R.drawable.jijiu,  R.drawable.jijiu,  R.drawable.jijiu,  R.drawable.jijiu, R.drawable.jijiu };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    setContentView(R.layout.activity_xiaoer);
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
					Intent classfyIntent = new Intent(XiaoerActiviy.this, TunaiActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 1){
					Intent classfyIntent = new Intent(XiaoerActiviy.this, GaoshaoActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 2){
					Intent classfyIntent = new Intent(XiaoerActiviy.this, YeshiActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 3){
					Intent classfyIntent = new Intent(XiaoerActiviy.this, MazhenActivity.class);
			         startActivity(classfyIntent);
				}
				else if(position == 4){
					Intent classfyIntent = new Intent(XiaoerActiviy.this, FeiyanActivity.class);
			         startActivity(classfyIntent);
				}
				
			}
		});
	}
	
	
}
