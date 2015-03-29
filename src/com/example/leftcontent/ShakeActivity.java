package com.example.leftcontent;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assistant.R;
import com.example.medicine_activity.BaiDianFeng_Activity32;
import com.example.medicine_activity.CuoChuang_Activity30;
import com.example.medicine_activity.ErYan_Activity55;
import com.example.medicine_activity.GanYan_Activity52;
import com.example.medicine_activity.GuGuanJieYan_Activity02;
import com.example.medicine_activity.HuiZhiJia_Activity37;
import com.example.medicine_activity.Jingzhui_Activity00;
import com.example.medicine_activity.LiJi_Activity24;
import com.example.medicine_activity.PiFu_Activity36;
import com.example.medicine_activity.QianLie_Activity62;
import com.example.medicine_activity.RuXian_Activity73;
import com.example.medicine_activity.TangNiaoBing_Activity43;
import com.example.medicine_activity.TuoFa_Activity61;
import com.example.medicine_activity.WeiYan_Activity20;
import com.example.medicine_activity.YanHouYan_Activity13;

public class ShakeActivity extends Activity {

	private SensorManager sensorManager;
	private Vibrator vibrator;
	ImageView iv;
	MediaPlayer mp;
	boolean isPlay=false;
	private static final String TAG = "TestSensorActivity";
	private static final int SENSOR_SHAKE = 10;
	
	
	private TextView tv_shake1;
	private TextView tv_shake2;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake);
		mp=MediaPlayer.create(this, R.raw.rolldice);
		iv=(ImageView) findViewById(R.id.imageview);
		//iv.setImageResource(R.drawable.point6);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		tv_shake1 = (TextView)findViewById(R.id.tv_shake1);
		tv_shake2 = (TextView)findViewById(R.id.tv_shake2);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sensorManager != null) {// 注册监听器
			sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
			// 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (sensorManager != null) {// 取消监听器
			sensorManager.unregisterListener(sensorEventListener);
		}
//		if(isPlay){
//			mp.stop();
//			mp.release();
//		}
	}

	/**
	 * 重力感应监听
	 */
	private SensorEventListener sensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			// 传感器信息改变时执行该方法
			float[] values = event.values;
			float x = values[0]; // x轴方向的重力加速度，向右为正
			float y = values[1]; // y轴方向的重力加速度，向前为正
			float z = values[2]; // z轴方向的重力加速度，向上为正
			//Log.i(TAG, "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
			//int medumValue = 10;
			
			if (Math.abs(x) > 10 || Math.abs(y) > 9 || Math.abs(z) > 14) {
//				mp.seekTo(0);
//				mp.start();加这个会死机
				isPlay=true;
				vibrator.vibrate(200);
				Message msg = new Message();
				msg.what = SENSOR_SHAKE;
				handler.sendMessage(msg);
				Log.i(TAG, "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
	};

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:				
				shake_action();
				break;
			}
		}

	};
	
	//摇一摇后的动作执行
	void shake_action() {
		
		tv_shake1.setVisibility(View.VISIBLE);
		int id=(int) (Math.random()*15+1);//生成色子随机值
		switch(id)
		{
		case 1:
			iv.setImageResource(R.drawable.baidianfeng1);
			tv_shake2.setText("白癜风(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this, BaiDianFeng_Activity32.class);
					startActivity(intent);
				}
			});
			break;
		case 2:
			iv.setImageResource(R.drawable.cuochuang1);
			tv_shake2.setText("痤疮(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this, CuoChuang_Activity30.class);
					startActivity(intent);
				}
			});
			break;
		case 3:
			iv.setImageResource( R.drawable.ganyan1);
			tv_shake2.setText("干眼症(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,GanYan_Activity52.class);
					startActivity(intent);
				}
			});
			break;
		case 4:
			iv.setImageResource(R.drawable.guguanjieyan1);
			tv_shake2.setText("关节炎(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,GuGuanJieYan_Activity02.class);
					startActivity(intent);
				}
			});
			break;
		case 5:
			iv.setImageResource(R.drawable.huizhijia1);
			tv_shake2.setText("灰指甲(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,HuiZhiJia_Activity37.class);
					startActivity(intent);
				}
			});
			break;
		case 6:
			iv.setImageResource(R.drawable.jz1);
			tv_shake2.setText("颈椎病(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,Jingzhui_Activity00.class);
					startActivity(intent);
				}
			});
			break;
		case 7:
			iv.setImageResource(R.drawable.liji1);
			tv_shake2.setText("痢疾拉肚(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,LiJi_Activity24.class);
					startActivity(intent);
				}
			});
			break;
		case 8:
			iv.setImageResource(R.drawable.pifuguoming1);
			tv_shake2.setText("皮肤过敏(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,PiFu_Activity36.class);
					startActivity(intent);
				}
			});
			break;
		case 9:
			iv.setImageResource( R.drawable.qian1);
			tv_shake2.setText("前列腺疾病(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,QianLie_Activity62.class);
					startActivity(intent);
				}
			});
			break;
		case 10:
			iv.setImageResource( R.drawable.eryan1);
			tv_shake2.setText("耳炎(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,ErYan_Activity55.class);
					startActivity(intent);
				}
			});
			break;
		case 11:
			iv.setImageResource( R.drawable.ruxian1);
			tv_shake2.setText("乳腺增生(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,RuXian_Activity73.class);
					startActivity(intent);
				}
			});
			break;
		case 12:
			iv.setImageResource(R.drawable.baidianfeng1);
			tv_shake2.setText("糖尿病(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,TangNiaoBing_Activity43.class);
					startActivity(intent);
				}
			});
			break;
		case 13:
			iv.setImageResource(R.drawable.tuofa1);
			tv_shake2.setText("脱发(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,TuoFa_Activity61.class);
					startActivity(intent);
				}
			});
			break;
		case 14:
			iv.setImageResource(R.drawable.weiyan1);
			tv_shake2.setText("胃炎(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,WeiYan_Activity20.class);
					startActivity(intent);
				}
			});
			break;
		case 15:
			iv.setImageResource(R.drawable.yanhouyan1);
			tv_shake2.setText("咽喉炎(点我了解详情)");
			tv_shake2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(ShakeActivity.this,YanHouYan_Activity13.class);
					startActivity(intent);
				}
			});
			break;
		
		}
	
		
		tv_shake2.setVisibility(View.VISIBLE);
	}
}
