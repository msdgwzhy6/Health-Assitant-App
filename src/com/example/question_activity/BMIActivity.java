package com.example.question_activity;
import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assistant.R;

public class BMIActivity extends Activity {
	//定义控件对象
	private EditText et_height;
	private EditText et_weight;
	private Button bt_submit;
	private Button bt_reset;
	TextView tv_result1;
	TextView tv_result2;
	
	//定义对象存储结果
	private int i_height = 0;
	private double d_weight = 0.0;
	private double bmi= 0.0;
	
	//标志位判断是否重置
	int flag = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmi);
		
		//初始化控件
		init();
		
		//设置按钮监听
		set_listener();		
	}

	//初始化控件
	private void init() {		
		et_height = (EditText)findViewById(R.id.bmi_et_height);
		et_weight = (EditText)findViewById(R.id.bmi_et_weight);
		bt_submit = (Button)findViewById(R.id.bmi_bt_submit);
		bt_reset = (Button)findViewById(R.id.bmi_bt_reset);
		tv_result1 = (TextView)findViewById(R.id.bmi_tv_result1);
		tv_result2 = (TextView)findViewById(R.id.bmi_tv_result2);
	}
	
	//设置按钮监听器
	private void set_listener() {
		//提交按钮的监听
		bt_submit.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				//获取数据
				get_message();
				//计算结果
				comput();
			}
		});
		
		//重置按钮的监听
		bt_reset.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				flag = 1;
				et_height.setText("");
				et_weight.setText("");
				i_height = 0;
				d_weight = 0;
				//tv_result1.setText("身高： " + 0 + "\n" + "  体重： " + 0 + "\n" + "  BMI指数： " + 0);
				tv_result1.setVisibility(View.INVISIBLE);
				tv_result2.setVisibility(View.INVISIBLE);
			}
		});
	}	
		
	//获取控件结果
	private void get_message() {
		//身高，体重				
		String s1 = et_height.getText().toString();
		String s2 = et_weight.getText().toString();
		if(s1.isEmpty() || s2.isEmpty()) {
			Toast.makeText(getApplicationContext(), "请保证信息填写完整",
				     Toast.LENGTH_SHORT).show();
			return;
		}
		else {
			i_height = Integer.parseInt(s1);
			d_weight = Double.parseDouble(s2);
			flag = 0;
		}
	}
	

	//计算测试结果
	private void comput() {		
		
		double d_height = (double)i_height / 100;
		bmi = d_weight / Math.pow(d_height, 2);		
		
		//根据不同的范围得到不同的结果
		if(flag == 0) {
			if(bmi<18.5) {
				result1(bmi);
				tv_result2.setText("您的体重过低！");
			}
			else if(bmi>=18.5 && bmi<24) {
				result1(bmi);
				tv_result2.setText("体重正常！");
			}
			else if(bmi>=24 && bmi<27) {
				result1(bmi);
				tv_result2.setText("肥胖前期！");
			}
			else if(bmi>=27 && bmi<30) {
				result1(bmi);
				tv_result2.setText("Ⅰ级肥胖Ⅲ！");
			}
			else if(bmi>=30 && bmi<40) {
				result1(bmi);
				tv_result2.setText("Ⅱ级肥胖！");
			}
			else if(bmi>=40) {
				result1(bmi);
				tv_result2.setText("Ⅲ级肥胖！");
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
	private void result1(double bmi) {
		DecimalFormat df=new DecimalFormat("#.0");
		tv_result1.setText("身高： " + i_height + "\n" + "  体重： " + d_weight + "\n" + "  BMI指数： " + df.format(bmi));
	}
}
