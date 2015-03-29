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

public class FatActivity extends Activity {
	//定义控件对象
	private RadioGroup rg_sex;
	private EditText et_height;
	private EditText et_weight;
	private EditText et_age;
	private Button bt_submit;
	private Button bt_reset;
	TextView tv_result1;
	TextView tv_result2;
	
	//定义对象存储结果
	private boolean b_sex = true;
	private int i_height = 0;
	private double d_weight = 0.0;
	private int i_age = 0;
	private double male_fat = 0.0;
	private double female_fat = 0.0;
	
	//标志位判断是否重置
	int flag = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fat);
		
		//初始化控件
		init();
		
		//设置按钮监听
		set_listener();		
	}

	//初始化控件
	private void init() {		
		rg_sex = (RadioGroup)findViewById(R.id.fat_rg_sex);
		et_height = (EditText)findViewById(R.id.fat_et_height);
		et_weight = (EditText)findViewById(R.id.fat_et_weight);
		et_age = (EditText)findViewById(R.id.fat_et_age);
		bt_submit = (Button)findViewById(R.id.fat_bt_submit);
		bt_reset = (Button)findViewById(R.id.fat_bt_reset);
		tv_result1 = (TextView)findViewById(R.id.fat_tv_result1);
		tv_result2 = (TextView)findViewById(R.id.fat_tv_result2);
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
					//System.out.println("信息：" + b_sex + " " + i_height + " " + d_weight);
					comput_male();
				}
				//性别是女
				else if(b_sex == false) {
					//System.out.println("信息：" + b_sex + " " + i_height + " " + d_weight);
					comput_female();
				}
			}
		});
		
		//重置按钮的监听
		bt_reset.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				flag = 1;
				et_height.setText("");
				et_weight.setText("");
				et_age.setText("");
				i_height = 0;
				d_weight = 0;
				i_age = 0;
				//tv_result1.setText("身高： " + 0 + "\n" + "  体重： " + 0 + "\n" + "  BMI指数： " + 0);
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
				b_sex = (checkedId == R.id.fat_rb_male) ? true : false;
			}
		});
		
		//身高，体重，年龄				
		String s1 = et_height.getText().toString();
		String s2 = et_weight.getText().toString();
		String s3 = et_age.getText().toString();
		if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请保证信息填写完整",
				     Toast.LENGTH_SHORT).show();
			return;
		}
		else {
			i_height = Integer.parseInt(s1);
			d_weight = Double.parseDouble(s2);
			i_age = Integer.parseInt(s3);
			flag = 0;
		}
	}
	
	//男性体脂率计算
	private void comput_male() {
		//先计算bmi，再计算体脂率
		double d_height = (double)i_height / 100;
		double bmi = d_weight / Math.pow(d_height, 2);	
		male_fat = 1.2*bmi + 0.23*i_age - 16.2;
		
		//根据体脂率进行判断
		if(flag == 0) {
			if(male_fat<10 && male_fat >=7) {
				result1(male_fat);
				tv_result2.setText("体脂率过低！");
			}
			else if(male_fat>=10 && male_fat<13) {
				result1(male_fat);
				tv_result2.setText("体脂率较低！");
			}
			else if(male_fat>=14 && male_fat<17) {
				result1(male_fat);
				tv_result2.setText("体脂率正常！");
			}
			else if(male_fat>=17 && male_fat<25) {
				result1(male_fat);
				tv_result2.setText("体脂率较高！");
			}
			else if(male_fat>=25) {
				result1(male_fat);
				tv_result2.setText("体脂率过高！");
			}
			else {
				result1(male_fat);
				tv_result2.setText("建议您去看医生！");
			}
			
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
		double d_height = (double)i_height / 100;
		double bmi = d_weight / Math.pow(d_height, 2);	
		female_fat = 1.2*bmi + 0.23*i_age - 5.4;
		
		//根据体脂率进行判断
		if(flag == 0) {
			if(female_fat<17 && female_fat >=14) {
				result1(female_fat);
				tv_result2.setText("体脂率过低！");
			}
			else if(female_fat>=17 && female_fat<20) {
				result1(female_fat);
				tv_result2.setText("体脂率较低！");
			}
			else if(female_fat>=20 && female_fat<27) {
				result1(female_fat);
				tv_result2.setText("体脂率正常！");
			}
			else if(female_fat>=27 && female_fat<31) {
				result1(female_fat);
				tv_result2.setText("体脂率较高！");
			}
			else if(female_fat>=31) {
				result1(female_fat);
				tv_result2.setText("体脂率过高！");
			}
			else {
				result1(female_fat);
				tv_result2.setText("建议您去看医生！");
			}
			//显示出控件
			tv_result1.setVisibility(View.VISIBLE);
			tv_result2.setVisibility(View.VISIBLE);
		}
		else {
			tv_result1.setVisibility(View.INVISIBLE);
			tv_result2.setVisibility(View.INVISIBLE);
		}
		
	}

	//result1的结果显示
	private void result1(double fat) {
		DecimalFormat df=new DecimalFormat("#.0");
		tv_result1.setText("您的体脂肪率为： " + df.format(fat));
	}
}
