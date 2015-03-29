package com.example.medicine_activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.TabActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.assistant.R;

public class Jingzhui_Activity00 extends TabActivity {
	private String[] names = new String []
			{ "万通 万通筋骨贴 7cm*10贴","明仁 颈痛颗粒 4g×6袋","999 正天丸 6克×10袋","中国灸 镇痛灸II型 2贴"};
	private String[] descs = new String[]
			{"参考价格：¥14.90","参考价格：¥31.70","参考价格：¥11.70","参考价格：¥38.00"};
	private  int[] imageIds = new int[]
			{ R.drawable.jz1,  R.drawable.jz2,  R.drawable.jz3,  R.drawable.jz4};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinzhui);
		//获取Activity中的TabHost组件
		TabHost tabHost = getTabHost();
		//创建第一个Tab页
		TabSpec tab1 = tabHost.newTabSpec("tab1")
				.setIndicator("症状及措施")    //设置标题
				.setContent(R.id.tab01);  //设置内容
		//添加第二个标签页
		tabHost.addTab(tab1);
		
		TabSpec tab2 = tabHost.newTabSpec("tab2")
				.setIndicator("推荐的常用药品")    //设置标题
				.setContent(R.id.tab02);  //设置内容
		//添加第二个标签页
		tabHost.addTab(tab2);
		//修改选项卡标签格式
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++)  
		{  
		    TextView textView = (TextView)tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);  
		    textView.setTextSize(25);  
		    textView.setGravity(Gravity.CENTER);  
		    textView.getLayoutParams().height = LayoutParams.MATCH_PARENT;  
		    textView.getLayoutParams().width = LayoutParams.MATCH_PARENT; 
		}
		
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0; i<names.length;i++){
        	Map<String,Object> listItem = new HashMap<String,Object>();
        	listItem.put("header",imageIds[i]);
        	listItem.put("personName",names[i]);
        	listItem.put("desc",descs[i]);
        	listItems.add(listItem);
        }
//        创建一个simpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.medicine_item,
        		new String[] {"personName","header","desc"},
        		//new String[] {"personName","header"},
        		new int[] {R.id.name,R.id.header,R.id.desc});
        		//new int[] {R.id.name,R.id.header});
        ListView list = (ListView) findViewById(R.id.lv_bone_items);
//        为ListView设置Adapter
        list.setAdapter(simpleAdapter);   
	
	}

}
