package com.example.question_activity;

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

public class WeightActivity extends Activity {
	//定义控件对象
	private RadioGroup rg_sex;
	private EditText et_height;
	private EditText et_weight;
	private Button bt_submit;
	private Button bt_reset;
	private TextView tv_result1;
	private TextView tv_result2;
	
	//定义变量存储空间结果
	private boolean b_sex = true;
	private int i_height = 0;
	private double d_weight = 0.0;
	
	//定义标志位判断是否重置
	private int flag = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight);
		
		//初始化控件
		init();

		//设置按钮监听
		set_listener();
	}
	
	//初始化控件
	private void init() {
		rg_sex = (RadioGroup)findViewById(R.id.weight_rg_sex);
		et_height = (EditText)findViewById(R.id.weight_et_height);
		et_weight = (EditText)findViewById(R.id.weight_et_weight);
		bt_submit = (Button)findViewById(R.id.weight_bt_submit);
		bt_reset = (Button)findViewById(R.id.weight_bt_reset);
		tv_result1 = (TextView)findViewById(R.id.weight_tv_result1);
		tv_result2 = (TextView)findViewById(R.id.weight_tv_result2);
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
				et_height.setText("");
				et_weight.setText("");
				flag = 1;
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
				b_sex = (checkedId == R.id.weight_rb_male) ? true : false;
			}
		});
		
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
	
	//男性体重计算
	private void comput_male() {
		double normal_weight = (i_height - 100) * 0.9;
		double rate = d_weight / normal_weight - 1;
		weight_test(rate);
	}
	
	//女性体重计算
	private void comput_female() {
		double normal_weight = (i_height - 100) * 0.9 - 2.5;
		double rate = d_weight / normal_weight - 1;
		weight_test(rate);	
	}
	
	//体重判断
	private void weight_test(double rate) {	

		if(flag == 0) {
			if(rate>=-0.1 && rate<=0.1) {
				/*Toast.makeText(getApplicationContext(), "恭喜你，体重正常！",
					     Toast.LENGTH_SHORT).show();*/
				tv_result2.setText("身高： " + i_height + "  " + "  体重： " + d_weight + "\n" + "恭喜你，体重正常！");
			}
			else if(rate>0.1 && rate<=0.3) {
				tv_result2.setText("身高： " + i_height + "  " + "  体重： " + d_weight + "\n" + "轻度肥胖");
			}
			else if(rate>0.3 && rate<=0.5) {
				tv_result2.setText("身高： " + i_height + "  " + "  体重： " + d_weight + "\n" + "重度肥胖");
			}
			else if(rate>0.5) {
				tv_result2.setText("身高： " + i_height + "  " + "  体重： " + d_weight + "\n" + "胖到突破天际");
			}
			else {
				tv_result2.setText("身高： " + i_height + "  " + "  体重： " + d_weight + "\n" + "瘦！不要减肥了！");
			}
			tv_result1.setVisibility(View.VISIBLE);
			tv_result2.setVisibility(View.VISIBLE);
		}
		else {
			tv_result1.setVisibility(View.INVISIBLE);
			tv_result2.setVisibility(View.INVISIBLE);
		}
	}
}
