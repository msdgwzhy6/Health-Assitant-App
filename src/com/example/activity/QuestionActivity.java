package com.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.assistant.R;
import com.example.question_activity.BMIActivity;
import com.example.question_activity.BMRActivity;
import com.example.question_activity.EHRActivity;
import com.example.question_activity.FatActivity;
import com.example.question_activity.WHRActivity;
import com.example.question_activity.WeightActivity;
import com.example.question_activity.YCQActivity;


public class QuestionActivity extends Activity {
	
	//定义listView所需要的数组
	private String[] nums = new String[]
			{"1.", "2.", "3.", "4.", "5.", "6.", "7."};
	private String[] names = new String[]
			{"标准体重测试", "身体质量指数", "体脂肪率", "腰臀比", "基础代谢计算", "目标运动心率", "预产期计算"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);
		
		//创建listView
		initListView();		
	}
	
	private void initListView() {
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for(int i=0; i<names.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>(); 
			listItem.put("nums", nums[i]);
			listItem.put("items", names[i]);
			listItems.add(listItem);
		}
		
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.activityquestion_simple_item, 
				new String[] {"nums", "items"}, new int[] {R.id.tv_question_nums, R.id.tv_question_items});
		ListView list = (ListView)findViewById(R.id.lv_question_items);
		list.setAdapter(simpleAdapter);
	
		//设置listView的各个item的调用
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch(position) {
				case 0 :
					//点击item的第一项，标准体重测试
					Intent weightIntent = new Intent(QuestionActivity.this, WeightActivity.class);
					startActivity(weightIntent);
					break;
				case 1 :
					//点击item的第二项，身体质量指数
					Intent bmiIntent = new Intent(QuestionActivity.this, BMIActivity.class);
					startActivity(bmiIntent);
					break;
				case 2 :
					//点击item的第三项，体脂肪率
					Intent fatIntent = new Intent(QuestionActivity.this, FatActivity.class);
					startActivity(fatIntent);
					break;
				case 3:
					//点击item的第四项，腰臀比
					Intent whrIntent = new Intent(QuestionActivity.this, WHRActivity.class);
					startActivity(whrIntent);
					break;
				case 4:
					//点击item的第五项，基础代谢计算
					Intent bmrIntent = new Intent(QuestionActivity.this, BMRActivity.class);
					startActivity(bmrIntent);
					break;
				case 5:
					//点击item的第六项，目标运动心率计算
					Intent ehrIntent = new Intent(QuestionActivity.this, EHRActivity.class);
					startActivity(ehrIntent);
					break;
				case 6:
					//点击item的第七项，预产期计算
					Intent ycqIntent = new Intent(QuestionActivity.this, YCQActivity.class);
					startActivity(ycqIntent);
				
				}				
			}		
		}); 	
	}
	
}
