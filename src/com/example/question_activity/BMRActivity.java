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

public class BMRActivity extends Activity {
	//定义控件对象
	private RadioGroup rg_sex;
	private EditText et_height;
	private EditText et_weight;
	private EditText et_age;
	private Button bt_submit;
	private Button bt_reset;
	TextView tv_result1;
	
	//定义对象存储结果
	private boolean b_sex = true;
	private int i_height = 0;
	private double d_weight = 0.0;
	private int i_age = 0;
	private double male_bmr = 0.0;
	private double female_bmr = 0.0;
	
	//标志位判断是否重置
	int flag = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmr);
		
		//初始化控件
		init();
		
		//设置按钮监听
		set_listener();		
	}

	//初始化控件
	private void init() {		
		rg_sex = (RadioGroup)findViewById(R.id.bmr_rg_sex);
		et_height = (EditText)findViewById(R.id.bmr_et_height);
		et_weight = (EditText)findViewById(R.id.bmr_et_weight);
		et_age = (EditText)findViewById(R.id.bmr_et_age);
		bt_submit = (Button)findViewById(R.id.bmr_bt_submit);
		bt_reset = (Button)findViewById(R.id.bmr_bt_reset);
		tv_result1 = (TextView)findViewById(R.id.bmr_tv_result1);
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
					comput_male();
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
				b_sex = (checkedId == R.id.bmr_rb_male) ? true : false;
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
	
	//男性基础代谢率计算
	private void comput_male() {
		//男性基础代谢率计算公式
		male_bmr = 10*d_weight + 6.25*i_height - 5*i_age + 5;
		
		//进行判断
		if(flag == 0) {			
			//显示出控件
			result1(male_bmr);
			tv_result1.setVisibility(View.VISIBLE);
		}
		else {
			tv_result1.setVisibility(View.INVISIBLE);
		}		
	}
	
	//女性体脂率计算
	private void comput_female() {
		//女性基础代谢率计算公式
		female_bmr = 10*d_weight + 6.25*i_height - 5*i_age - 161;
		
		//进行判断
		if(flag == 0) {			
			//显示出控件
			result1(female_bmr);
			tv_result1.setVisibility(View.VISIBLE);
		}
		else {
			tv_result1.setVisibility(View.INVISIBLE);
		}		
	}

	//result1的结果显示
	private void result1(double fat) {
		DecimalFormat df=new DecimalFormat("#.0");
		tv_result1.setText("您的基础代谢率为： \n" + df.format(fat) + " 大卡！");
	}
}
