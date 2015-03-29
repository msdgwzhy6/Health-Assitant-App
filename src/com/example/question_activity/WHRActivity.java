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

public class WHRActivity extends Activity {
	//定义控件对象
	private RadioGroup rg_sex;
	private EditText et_waist;
	private EditText et_hip;
	private Button bt_submit;
	private Button bt_reset;
	TextView tv_result1;
	TextView tv_result2;
	
	//定义对象存储结果
	private boolean b_sex = true;
	private double d_waist = 0.0;
	private double d_hip = 0.0;
	private double whr= 0.0;
	
	//标志位判断是否重置
	int flag = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_whr);
		
		//初始化控件
		init();
		
		//设置按钮监听
		set_listener();		
	}

	//初始化控件
	private void init() {		
		rg_sex = (RadioGroup)findViewById(R.id.whr_rg_sex);
		et_waist = (EditText)findViewById(R.id.whr_et_waist);
		et_hip = (EditText)findViewById(R.id.whr_et_hip);
		bt_submit = (Button)findViewById(R.id.whr_bt_submit);
		bt_reset = (Button)findViewById(R.id.whr_bt_reset);
		tv_result1 = (TextView)findViewById(R.id.whr_tv_result1);
		tv_result2 = (TextView)findViewById(R.id.whr_tv_result2);
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
				et_waist.setText("");
				et_hip.setText("");
				d_waist = 0.0;
				d_hip = 0.0;
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
				b_sex = (checkedId == R.id.whr_rb_male) ? true : false;
			}
		});		
		
		//身高，体重				
		String s1 = et_waist.getText().toString();
		String s2 = et_hip.getText().toString();
		if(s1.isEmpty() || s2.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请保证信息填写完整",
				     Toast.LENGTH_SHORT).show();
			return;
		}
		else {
			d_waist = Integer.parseInt(s1);
			d_hip = Double.parseDouble(s2);
			flag = 0;
		}
	}
	

	//计算男性测试结果
	private void comput_male() {				
		whr = d_waist / d_hip;		
		
		//根据不同的范围得到不同的结果
		if(flag == 0) {
			if(whr<0.85) {
				result1(whr);
				tv_result2.setText("梨形身材（下身肥胖）！");
			}
			else if(whr>=0.85 && whr<=0.95) {
				result1(whr);
				tv_result2.setText("身材正常!");
			}
			else {
				result1(whr);
				tv_result2.setText("苹果型身材（上身肥胖）！");
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
	
	//计算女性测试结果
	private void comput_female() {				
		whr = d_waist / d_hip;		
		
		//根据不同的范围得到不同的结果
		if(flag == 0) {
			if(whr<0.67) {
				result1(whr);
				tv_result2.setText("梨形身材（下身肥胖）！");
			}
			else if(whr>=0.67 && whr<=0.80) {
				result1(whr);
				tv_result2.setText("身材正常!");
			}
			else {
				result1(whr);
				tv_result2.setText("苹果型身材（上身肥胖）！");
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
	private void result1(double whr) {
		DecimalFormat df=new DecimalFormat("0.0");
		tv_result1.setText("腰围： " + d_waist + "\n" + "  臀围： " + d_hip + "\n" + "  腰臀比指数： " + df.format(whr));
	}
}
