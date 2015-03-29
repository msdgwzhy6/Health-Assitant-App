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
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

import com.example.assistant.R;

public class HuiZhiJia_Activity37 extends TabActivity{
	private String[] names = new String []
			{"美卿亮甲膜根治治疗灰指甲专用特效液药","芊茗草亮甲灰指甲专用特3件套 灰指甲根治特效","灰指甲根治软甲膏治疗灰指甲专用特效液药","治疗灰指甲专用特效液 "};
	private String[] descs = new String[]
			{"参考价格：¥39.9","参考价格：¥59.0","参考价格：¥35.5","参考价格：¥9.9"};
	private  int[] imageIds = new int[]
			{ R.drawable.huizhijia1,  R.drawable.huizhijia2,  R.drawable.huizhijia3,  R.drawable.huizjijia4};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huizhijia);
		//获取Activity中的TabHost组件
		TabHost tabHost = getTabHost();
		//创建第一个Tab页
		TabSpec tab1 = tabHost.newTabSpec("tab1")
				.setIndicator("症状及措施")    //设置标题
				.setContent(R.id.tab01);  //设置内容
		//添加第二个标签页
		tabHost.addTab(tab1);
		
		TabSpec tab2 = tabHost.newTabSpec("tab2")
				.setIndicator("推荐药品")    //设置标题
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
        ListView list = (ListView) findViewById(R.id.lv_huizhijia_items);
//        为ListView设置Adapter
        list.setAdapter(simpleAdapter);   
	
	}

}
