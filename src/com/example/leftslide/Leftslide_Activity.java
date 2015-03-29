package com.example.leftslide;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activity.MainActivity;
import com.example.assistant.R;
import com.example.leftcontent.AboutActivity;
import com.example.leftcontent.ShakeActivity;

import com.example.leftcontent.TipsActivity;
import com.sitech.oncon.barcode.core.CaptureActivity;

public class Leftslide_Activity extends ActivityGroup implements OnClickListener 

{
	public static SlidingMenuView slidingMenuView;
	public static int num1 = 1;
	private ViewGroup tabcontent;
	
	private TextView textViewShowName;
	//定义数组，来写入ListView中每个TextView的内容
	private	String[] leftmenuNames;
	private List<Map<String, Object>> leftmenuItems;
	private ListView listView;
	private SimpleAdapter leftmenuAdapter;
			
	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_left);

		slidingMenuView = (SlidingMenuView) findViewById(R.id.main_menu_view);
		tabcontent = (ViewGroup) slidingMenuView.findViewById(R.id.main_body);
		
		preferences = getSharedPreferences("appmatch",MODE_PRIVATE);
		editor = preferences.edit();
		
		textViewShowName = (TextView) findViewById(R.id.tv_username);
		listView = (ListView)findViewById(R.id.list1);	
		//textViewShowName.setText(text);
		initView();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		// 加载主页
		Intent i = new Intent(this, MainActivity.class);
		View v = getLocalActivityManager().startActivity(
				MainActivity.class.getName(), i).getDecorView();
		
		tabcontent.removeAllViews();
		tabcontent.addView(v);
		tabcontent.setOnClickListener(this);
		
	}
	

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		leftmenuNames = new String[] {"扫一扫", "摇一摇", "小知识", "关于", "退出"};
		//textViewShowName.setText("未登录");
			
		//创建一个List集合，List集合的元素是Map
		leftmenuItems = new ArrayList<Map<String, Object>>();
		for(int j=0; j<leftmenuNames.length; j++) {			
			Map<String, Object> leftmenuItem = new HashMap<String, Object>();
			leftmenuItem.put("everyitemnames", leftmenuNames[j]);
			leftmenuItems.add(leftmenuItem);
		}
		//创建一个SimpleAdapter
		leftmenuAdapter = new SimpleAdapter
				(this, leftmenuItems, R.layout.leftmenuitem, 
						new String[] { "everyitemnames"},
						new int[] { R.id.leftmenu_content} );

		listView.setAdapter(leftmenuAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view,
							int position, long id)
			{
				// 登录
				if(position == 0) {
					Intent sweep_intent = new Intent(Leftslide_Activity.this, CaptureActivity.class);
	   				startActivity(sweep_intent);
				}
					/*String user = preferences.getString("username", null);
					if(user == null)
					{
						Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
						startActivity(loginIntent);
					}
					else
					{
						editor.clear();
						editor.commit();
						textViewShowName.setText("未登录");
						leftmenuItems.get(0).put("everyitemnames", "登录");
						leftmenuAdapter.notifyDataSetChanged();
					}
					 */						
				//摇一摇
				else if(position == 1) {
					 Intent classfyIntent = new Intent(Leftslide_Activity.this, ShakeActivity.class);
			         startActivity(classfyIntent);			         
				}
				//小知识
				else if(position == 2) {
					Intent tipsIntent = new Intent(Leftslide_Activity.this, TipsActivity.class);
					startActivity(tipsIntent);
				}

				//关于
				else if(position == 3) {
					Intent aboutIntent = new Intent(Leftslide_Activity.this, AboutActivity.class);
					startActivity(aboutIntent);
				}
				//退出
				else {
					finish();

				}
			}
		});
	}

		

	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {			
		case R.id.main_body:
			slidingMenuView.snapToScreen(1);
			break;
			
		default:
			break;
		}
	}


	private long exitTime = 0;

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (slidingMenuView.getCurrentScreen() == 1) {

				if ((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(getApplicationContext(), "再按一次退出程序",
							Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				} else {
					finish();
					System.out.println(" APP is finish");
				}
			} else {
				slidingMenuView.snapToScreen(1);
			}

			return true;

		} else if (keyCode == KeyEvent.KEYCODE_MENU
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (slidingMenuView.getCurrentScreen() == 1) {
				slidingMenuView.snapToScreen(0);
			} else {
				slidingMenuView.snapToScreen(1);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

/*	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println(" APP is Destroyed");
		Intent download_intent = new Intent();
		download_intent.setClass(MainActivity.this, DownloadService.class);
		stopService(download_intent);
	}*/
	
	
}

