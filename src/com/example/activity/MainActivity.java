package com.example.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.assistant.R;
import com.example.chat.IniActivity;
import com.example.maps_activity.basemap_Activity;
import com.example.push.PushActivity;

public class MainActivity extends Activity {
	//各个模块先模拟成按钮
	RelativeLayout rl_map = null;
	RelativeLayout rl_chat = null;
	RelativeLayout rl_medicine = null;
	RelativeLayout rl_question = null;
	Button bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt = (Button)findViewById(R.id.main_tv);
		//初始化各个按钮
		init_modes();
	}

	private void init_modes() {
		//map_bt = (Button)findViewById(R.id.map_bt);
		rl_map = (RelativeLayout)findViewById(R.id.rl_map);
		rl_chat = (RelativeLayout)findViewById(R.id.rl_chat);
		rl_medicine = (RelativeLayout)findViewById(R.id.rl_medicine);
		rl_question = (RelativeLayout)findViewById(R.id.rl_question);

		rl_map.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//跳转到地图模块
				Intent intent = new Intent(MainActivity.this, basemap_Activity.class);

				/*//添加地图提示信息
				Toast toast = Toast.makeText(MainActivity.this,"滑动地图显示附近2000米内所有药店位置，点击显示具体药店名称",Toast.LENGTH_LONG);
				toast.show();*/
				startActivity(intent);
				//finish();
			}
		});

		rl_chat.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//跳转到聊天模块

            	String sdcard = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            	File file = new File(sdcard+"/A/");
            	deleteFile(file);

				Intent intent = new Intent(MainActivity.this, IniActivity.class);
				startActivity(intent);
				//finish();
			}
		});

		rl_medicine.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//跳转到药箱模块
				Intent intent = new Intent(MainActivity.this, MedicineActivity.class);
				startActivity(intent);
				//finish();
			}
		});

		rl_question.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				//跳转到问卷模块
				Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
				startActivity(intent);
				//finish();
			}
		});

        bt.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				//跳转到推送模块
				Intent intent = new Intent(MainActivity.this, PushActivity.class);
				startActivity(intent);
			}
		});
	}

    public void deleteFile(File file) {
    	if (file.exists()) {
            if (file.isFile()) {
            	file.delete();
            }
            // 如果它是一个目录
            else if (file.isDirectory()) {
            	// 声明目录下所有的文件 files[];
            	File files[] = file.listFiles();
            	for (int i = 0; i < files.length; i++) {
            		// 遍历目录下所有的文件
            		deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
            	}
            }
            file.delete();
    	}
    }
}
