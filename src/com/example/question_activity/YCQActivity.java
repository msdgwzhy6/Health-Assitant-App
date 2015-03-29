package com.example.question_activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assistant.R;

public class YCQActivity extends Activity {
	//定义控件对象
	private EditText et_year;
	private EditText et_month;
	private EditText et_day;
	private Button bt_submit;
	private Button bt_reset;
	TextView tv_result1;
	TextView tv_result2;
	
	//定义对象存储结果,1为起始日期，2为预产日期
	private int year1 = 0;
	private int month1 = 0;
	private int day1 = 0;
	private int year2 = 0;
	private int month2 = 0;
	private int day2 = 0;
	private int day0 = 0;
	
	//标志位判断是否重置
	int flag = 1;
	int flag2 = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ycq);
		
		//初始化控件
		init();
	
		//设置按钮监听
		set_listener();	
	}
	
	//初始化控件
	private void init() {		
		
		et_year = (EditText)findViewById(R.id.ycq_et_year);
		et_month = (EditText)findViewById(R.id.ycq_et_month);
		et_day = (EditText)findViewById(R.id.ycq_et_day);
		bt_submit = (Button)findViewById(R.id.ycq_bt_submit);
		bt_reset = (Button)findViewById(R.id.ycq_bt_reset);
		tv_result1 = (TextView)findViewById(R.id.ycq_tv_result1);
		tv_result2 = (TextView)findViewById(R.id.ycq_tv_result2);
	}
	
	//设置按钮监听器
	private void set_listener() {
		//提交按钮的监听
		bt_submit.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				//获取数据
				get_message();
				//计算数据
				comput();
			}
		});
		
		//重置按钮的监听
		bt_reset.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				flag = 1;
				et_year.setText("");
				et_month.setText("");
				et_day.setText("");
				year1 = 0;
				month1 = 0;
				day1 = 0;
				year2 = 0;
				month2 = 0;
				day2 = 0;
				//tv_result1.setText("身高： " + 0 + "\n" + "  体重： " + 0 + "\n" + "  BMI指数： " + 0);
				tv_result1.setVisibility(View.INVISIBLE);
				tv_result2.setVisibility(View.INVISIBLE);
			}
		});
	}	
	
	//获取控件结果
	private void get_message() {
		//年，月，日				
		String s1 = et_year.getText().toString();
		String s2 = et_month.getText().toString();
		String s3 = et_day.getText().toString();

		if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请保证信息填写完整！",
				     Toast.LENGTH_SHORT).show();
			return;
		}
		else {
			year1 = Integer.parseInt(s1);
			month1 = Integer.parseInt(s2);
			day1 = Integer.parseInt(s3);
			flag = 0;
			flag2 = 0;
		}
			
		if(year1<2000 || year1>2050 || month1<1 || month1>12 || day1<1 || day1>31) {
			
			Toast.makeText(getApplicationContext(), "请在规定范围内填写！",
					    Toast.LENGTH_SHORT).show();
			flag2 = 1;
		}		
	}
	
	//预产期计算
	private void comput() {
		/*//先根据月区分计算方法，再根据日区分计算公式
		if(month1 < 3 && month1 > 0) {
			year2 = year1;
			switch(month1) {
			case 1:
				if(day1<=24) {
					month2 = month1;
					day2 = day1 + 7;
					break;
				}
				else {
					month2 = month1 + 1;
					day2 = day1 + 7 - 31;
					break;
				}
			case 2:
				if(day1<=21) {
					month2 = month1;
					day2 = day1 + 7;
					break;
				}
				
			}
		}
		else if(month1 == 3) {
			
		}
		else if (month1 >3 && month1 < 12) {
			
		}
		else if(month1 == 12){
			
		}
		else {
			
		}*/
		
		//先判断出大概的月，年
		if(month1>3) {
			month2 = month1 - 3;
			year2 = year1 + 1;
		}
		else {
			month2 = month1 + 9;
			year2 = year1;
		}
		//再判断出这个月有多少天
		switch(month2) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day0 = 31;
			break;
		case 2:
			day0 = 28;
			break;
		default:
			day0 = 30;		
		}
		//计算出天数，再更新下月份和年份
		if(day1+7>day0) {
			day2 = day1 + 7 - day0;
			month2 = month2 + 1;
			if(month2 == 13) {
				month2 = 1;
				year2 = year2 + 1;
			}
		}
		else {
			day2 = day1 + 7;
		}
		
		//进行判断
		if(flag == 0 && flag2 == 0) {			
			//显示出控件
			tv_result1.setText("您的预产期为： \n");
			tv_result2.setText(year2 + "年" + month2 + "月" + day2 + "日");
			tv_result1.setVisibility(View.VISIBLE);
			tv_result2.setVisibility(View.VISIBLE);
		}
		else {
			tv_result1.setVisibility(View.INVISIBLE);
			tv_result2.setVisibility(View.INVISIBLE);
		}		
	}
	
}
