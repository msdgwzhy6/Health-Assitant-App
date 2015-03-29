package com.example.question_activity;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assistant.R;

public class EHRActivity extends Activity {
	//定义控件对象
	private RadioGroup rg_sex;
	private EditText et_age;
	private Button bt_submit;
	private Button bt_reset;
	TextView tv_result1;
	TextView tv_result2;
	
	//定义对象存储结果
	private boolean b_sex = true;
	private int i_age = 0;
	private double max_rate = 0.0;
	private double male_rate1 = 0.0;
	private double male_rate2 = 0.0;
	private double female_rate1 = 0.0;
	private double female_rate2 = 0.0;
	
	//标志位判断是否重置
	int flag = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ehr);
		
		//初始化控件
		init();
		
		//设置按钮监听
		set_listener();		
	}

	//初始化控件
	private void init() {		
		rg_sex = (RadioGroup)findViewById(R.id.ehr_rg_sex);
		et_age = (EditText)findViewById(R.id.ehr_et_age);
		bt_submit = (Button)findViewById(R.id.ehr_bt_submit);
		bt_reset = (Button)findViewById(R.id.ehr_bt_reset);
		tv_result1 = (TextView)findViewById(R.id.ehr_tv_result1);
		tv_result2 = (TextView)findViewById(R.id.ehr_tv_result2);
	}
	
	//设置按钮监听器
	private void set_listener() {
		//提交按钮的监听
		bt_submit.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				//获取数据
				get_message();
				//性别是男
				if(b_sex == true) {
					comput_male();
				}
				//性别是女
				else if(b_sex == false) {
					comput_female();
				}
			}
		});
		
		//重置按钮的监听
		bt_reset.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				flag = 1;
				et_age.setText("");
				i_age = 0;		
				tv_result1.setVisibility(View.INVISIBLE);
				tv_result2.setVisibility(View.INVISIBLE);
			}
		});
	}	
		
	//获取控件结果
	private void get_message() {
		//性别
		rg_sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {		
			@Override
			public void onCheckedChanged(RadioGroup arg0, int checkedId) {
				//选中男时b_sex为true，选中女时b_sex为false
				b_sex = (checkedId == R.id.ehr_rb_male) ? true : false;
			}
		});
		
		//年龄				
		String s3 = et_age.getText().toString();
		if(s3.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请保证信息填写完整",
				     Toast.LENGTH_SHORT).show();
			return;
		}
		else {
			i_age = Integer.parseInt(s3);
			flag = 0;
		}
	}
	
	//男性心率计算
	private void comput_male() {
		max_rate = 205 - i_age;
		male_rate1 = max_rate * 0.6;
		male_rate2 = max_rate * 0.85;
		if(flag == 0) {
			DecimalFormat df=new DecimalFormat("0.0");
			tv_result1.setText("您的正常最高心率为：" + df.format(max_rate));
			tv_result2.setText("您的目标运动心率范围为：" + df.format(male_rate1) + "--" + df.format(male_rate2));
			//显示出控件
			tv_result1.setVisibility(View.VISIBLE);
			tv_result2.setVisibility(View.VISIBLE);
		}
		else {
			tv_result1.setVisibility(View.INVISIBLE);
			tv_result2.setVisibility(View.INVISIBLE);
		}
		
	}
	
	//女性体脂率计算
	private void comput_female() {
		max_rate = 220 - i_age;
		female_rate1 = max_rate * 0.6;
		female_rate2 = max_rate * 0.85;
		if(flag == 0) {
			DecimalFormat df=new DecimalFormat("0.0");
			tv_result1.setText("您的正常最高心率为：" + df.format(max_rate));
			tv_result2.setText("您的目标运动心率范围为：" + df.format(female_rate1) + "--" + df.format(female_rate2));
			//显示出控件
			tv_result1.setVisibility(View.VISIBLE);
			tv_result2.setVisibility(View.VISIBLE);
		}
		else {
			tv_result1.setVisibility(View.INVISIBLE);
			tv_result2.setVisibility(View.INVISIBLE);
		}
		
	}
}
