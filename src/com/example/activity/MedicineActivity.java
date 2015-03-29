package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assistant.R;
import com.example.medicine_activity.BaiDianFeng_Activity32;
import com.example.medicine_activity.BaiNeiZhang_Activity54;
import com.example.medicine_activity.BiYan_Activity50;
import com.example.medicine_activity.BuTiao_Activity72;
import com.example.medicine_activity.CuoChuang_Activity30;
import com.example.medicine_activity.ErYan_Activity55;
import com.example.medicine_activity.FeiJieHe_Activity12;
import com.example.medicine_activity.FeiQiZhong_Activity14;
import com.example.medicine_activity.GanYan_Activity52;
import com.example.medicine_activity.GaoXueYa_Acyivity40;
import com.example.medicine_activity.GaoXueZhi_Activity42;
import com.example.medicine_activity.GuGuHuaiSi_Activity04;
import com.example.medicine_activity.GuGuanJieYan_Activity02;
import com.example.medicine_activity.GuZhiShuSong_Activity03;
import com.example.medicine_activity.GuZhiZengSheng_Activity01;
import com.example.medicine_activity.GuanXinBing_Activity44;
import com.example.medicine_activity.HuiZhiJia_Activity37;
import com.example.medicine_activity.JiaoQi_Activity31;
import com.example.medicine_activity.JieChangYan_Activity22;
import com.example.medicine_activity.Jingzhui_Activity00;
import com.example.medicine_activity.KuiYang_Activity51;
import com.example.medicine_activity.LeiFengShi_Activity08;
import com.example.medicine_activity.LiJi_Activity24;
import com.example.medicine_activity.NaoXueShuan_Activity41;
import com.example.medicine_activity.NiuPiXuan_Activity33;
import com.example.medicine_activity.PiFu_Activity36;
import com.example.medicine_activity.PiYan_Activity34;
import com.example.medicine_activity.QianLie_Activity62;
import com.example.medicine_activity.QinGuanYan_Activity53;
import com.example.medicine_activity.RuXian_Activity73;
import com.example.medicine_activity.ShenYan_Activity63;
import com.example.medicine_activity.TangNiaoBing_Activity43;
import com.example.medicine_activity.TongFeng_Activity07;
import com.example.medicine_activity.TongJing_Activity70;
import com.example.medicine_activity.TuoFa_Activity61;
import com.example.medicine_activity.WeiYan_Activity20;
import com.example.medicine_activity.XiJun_Activity35;
import com.example.medicine_activity.XiaoChuan_Activity10;
import com.example.medicine_activity.XiaoHua_Activity21;
import com.example.medicine_activity.YaZhouYan_Activity56;
import com.example.medicine_activity.YanHouYan_Activity13;
import com.example.medicine_activity.YangWei_Activity60;
import com.example.medicine_activity.YaoJiLaoSun_Activity06;
import com.example.medicine_activity.YinDaoYan_Activity71;
import com.example.medicine_activity.ZhiChuang_Activity23;
import com.example.medicine_activity.ZhiQiGuanYan_Activity11;
import com.example.medicine_activity.ZhuiJianPan_Activity05;


public class MedicineActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicine);
		//创建BaseExpandableListAdapter对象
		final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
			 //设置组视图的图片
            int[] logos = new int[] { R.drawable.bone, R.drawable.breath,R.drawable.digest,R.drawable.skin,R.drawable.heart,R.drawable.eye,R.drawable.man,R.drawable.woman};
            //设置组视图的显示文字
            private String[] diseaseTypes = new String[] { "风湿骨科", "呼吸系统", "消化系统","皮肤科","心脑血管","五官科","男性健康","女性健康" };
            //子视图显示文字
            private String[][] disease= new String[][] {
                    { "颈椎病", "骨质增生", "骨关节炎", "骨质疏松症", "股骨坏死","椎间盘突出","腰肌劳损" ,"痛风","类风湿"},
                    { "哮喘", "支气管炎","肺结核","咽喉炎","肺气肿"},
                    { "胃炎", "消化不良", "结肠炎","痔疮便秘","痢疾" },
                    {"痤疮(青春痘)","癣症/脚气","白癜风","银屑病(牛皮癣)","皮炎湿疹","真菌感染","皮肤过敏","灰指甲"},
                    {"高血压","脑血栓","高血脂","糖尿病","冠心病"},
                    {"过敏性鼻炎","口腔溃疡","干眼症","青光眼","白内障","耳炎","牙周炎"},
                    {"阳痿早泄","脱发少发","前列腺炎","肾炎"},
                    {"痛经","阴道炎","月经不调","乳腺疾病"}

            };
           
          //自己定义一个获得文字信息的方法
            TextView getTextView() {
            	   LinearLayout lp = new LinearLayout(
						 MedicineActivity.this);
                TextView textView = new TextView(
                		 MedicineActivity.this);
                lp.setOrientation(LinearLayout.HORIZONTAL);
                //textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(180, 15, 0, 15);
                textView.setTextSize(20);
                textView.setTextColor(Color.BLACK);
                return textView;
            }
            
		//重写ExpandableListAdapter中的各个方法
			@Override
			public boolean isChildSelectable(int groupPostion, int childPostion) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}
			
			//该方法决定每个组选项的外观
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
                    View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				 LinearLayout ll = new LinearLayout(
						 MedicineActivity.this);
	                ll.setOrientation(LinearLayout.HORIZONTAL);
	                ImageView logo = new ImageView(MedicineActivity.this);
	                logo.setImageResource(logos[groupPosition]);
	                logo.setPadding(60, 5, 0, 5);
	                
	                ll.addView(logo);
	                TextView textView = getTextView();
	                //textView.setTextColor(Color.RED);
	                textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
	                //方向为左上右下
	                textView.setPadding(60, 30, 0, 30);
	                textView.setText(getGroup(groupPosition).toString());
	                textView.setTextSize(22);
	                ll.addView(textView);

	                return ll;
			}
			
			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				 return groupPosition;
			}
			
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				 return diseaseTypes.length;
			}
			
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return diseaseTypes[groupPosition];
			}
			
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return disease[groupPosition].length;
			}
			//该方法决定每个子选项的外观
			@Override
			public View getChildView(int groupPosition, int childPosition,
                    boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				 LinearLayout ll = new LinearLayout(
						 MedicineActivity.this);
	                ll.setOrientation(0);
	                TextView textView = getTextView();
	                textView.setText(getChild(groupPosition, childPosition)
	                        .toString());
	                ll.addView(textView);
	                return ll;
			}
			
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				 return childPosition;
			}
			
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return disease[groupPosition][childPosition];
			}
		};
		 ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.lv_medicine_items);
	     expandableListView.setAdapter(adapter);
	     
	     //设置Item监听器
	     expandableListView.setOnChildClickListener(new OnChildClickListener() {

	            @Override
	            public boolean onChildClick(ExpandableListView parent, View v,
	                    int groupPosition, int childPosition, long id) {

	                Toast.makeText(
	                		MedicineActivity.this,
	                        "您将选择查看" + adapter.getChild(groupPosition, childPosition),
	                        Toast.LENGTH_SHORT).show();

	                return false;
	            }
	        });
	   //设置ExpandableListView的Ttem跳转
	     //设置item点击的监听器
         expandableListView.setOnChildClickListener(new OnChildClickListener() {
        	 @Override
             public boolean onChildClick(ExpandableListView parent, View v,
                     int groupPosition, int childPosition, long id) {
        		 if(groupPosition==0&&childPosition == 0){
                 Intent intent00=new Intent(MedicineActivity.this, Jingzhui_Activity00.class);
                 startActivity(intent00);
        		 }
        		 else if(groupPosition==0&&childPosition == 1){
        			 Intent intent01=new Intent(MedicineActivity.this,GuZhiZengSheng_Activity01.class);
                     startActivity(intent01);
            		 }
        		 else if(groupPosition==0&&childPosition == 2){
                     Intent intent02=new Intent(MedicineActivity.this,GuGuanJieYan_Activity02.class);
                     startActivity(intent02);
            		 }
        		 else if(groupPosition==0&&childPosition == 3){
                     Intent intent03 = new Intent(MedicineActivity.this,GuZhiShuSong_Activity03.class);
                     startActivity(intent03);
            		 }
        		 else if(groupPosition==0&&childPosition == 4){
                     Intent intent04 = new Intent(MedicineActivity.this,GuGuHuaiSi_Activity04.class);
                     startActivity(intent04);
            		 }
        		 else if(groupPosition==0&&childPosition == 5){
                     Intent intent05 = new Intent(MedicineActivity.this,ZhuiJianPan_Activity05.class);
                     startActivity(intent05);
            		 }
        		 else if(groupPosition==0&&childPosition == 6){
                     Intent intent06 = new Intent(MedicineActivity.this,YaoJiLaoSun_Activity06.class);
                     startActivity(intent06);
            		 }
        		 else if(groupPosition==0&&childPosition == 7){
                     Intent intent07 = new Intent(MedicineActivity.this,TongFeng_Activity07.class);
                     startActivity(intent07);
            		 }
        		 else if(groupPosition==0&&childPosition == 8){
                     Intent intent08 = new Intent(MedicineActivity.this,LeiFengShi_Activity08.class);
                     startActivity(intent08);
            		 }
    
        	 if(groupPosition==1&&childPosition == 0){
                     Intent intent10=new Intent(MedicineActivity.this, XiaoChuan_Activity10.class);
                     startActivity(intent10);
            		 } 
        	 	else if(groupPosition==1&&childPosition == 1){
                 Intent intent11 = new Intent(MedicineActivity.this, ZhiQiGuanYan_Activity11.class);
                 startActivity(intent11);
        		 }
        	 	else if(groupPosition==1&&childPosition == 2){
                 Intent intent12 = new Intent(MedicineActivity.this, FeiJieHe_Activity12.class);
                 startActivity(intent12);
        		 }
        	 	else if(groupPosition==1&&childPosition == 3){
                 Intent intent13 = new Intent(MedicineActivity.this, YanHouYan_Activity13.class);
                 startActivity(intent13);
        		 }
        	 	else if(groupPosition==1&&childPosition == 4){
                 Intent intent14 = new Intent(MedicineActivity.this, FeiQiZhong_Activity14.class);
                 startActivity(intent14);
        		 }
        	 
        	 if(groupPosition==2&&childPosition == 0){
                 Intent intent20=new Intent(MedicineActivity.this, WeiYan_Activity20.class);
                 startActivity(intent20);
        		 } 
        	   else if(groupPosition==2&&childPosition == 1){
                 Intent intent21=new Intent(MedicineActivity.this,XiaoHua_Activity21.class);
                 startActivity(intent21);
        		 }
        	   else if(groupPosition==2&&childPosition == 2){
                   Intent intent22=new Intent(MedicineActivity.this,JieChangYan_Activity22.class);
                   startActivity(intent22);
          		 } 
        	   else if(groupPosition==2&&childPosition == 3){
                   Intent intent23=new Intent(MedicineActivity.this,ZhiChuang_Activity23.class);
                   startActivity(intent23);
          		 } 
        	   else if(groupPosition==2&&childPosition == 4){
                   Intent intent24=new Intent(MedicineActivity.this,LiJi_Activity24.class);
                   startActivity(intent24);
          		 }
        	 
        	 if(groupPosition==3&&childPosition == 0){
                 Intent intent30=new Intent(MedicineActivity.this, CuoChuang_Activity30.class);
                 startActivity(intent30);
        		 } 
        	  else if(groupPosition==3&&childPosition == 1){
                 Intent intent31=new Intent(MedicineActivity.this, JiaoQi_Activity31.class);
                 startActivity(intent31);
        		 } 
        	  else if(groupPosition==3&&childPosition == 2){
                  Intent intent32=new Intent(MedicineActivity.this, BaiDianFeng_Activity32.class);
                  startActivity(intent32);
         		 } 
        	  else if(groupPosition==3&&childPosition == 3){
                  Intent intent33=new Intent(MedicineActivity.this, NiuPiXuan_Activity33.class);
                  startActivity(intent33);
         		 }
        	  else if(groupPosition==3&&childPosition == 4){
                  Intent intent34=new Intent(MedicineActivity.this, PiYan_Activity34.class);
                  startActivity(intent34);
         		 }
        	  else if(groupPosition==3&&childPosition == 5){
                  Intent intent35=new Intent(MedicineActivity.this, XiJun_Activity35.class);
                  startActivity(intent35);
         		 }
        	  else if(groupPosition==3&&childPosition == 6){
                  Intent intent36=new Intent(MedicineActivity.this, PiFu_Activity36.class);
                  startActivity(intent36);
         		 }
        	  else if(groupPosition==3&&childPosition == 7){
                  Intent intent37=new Intent(MedicineActivity.this, HuiZhiJia_Activity37.class);
                  startActivity(intent37);
         		 }
        	if(groupPosition==4&&childPosition == 0){
                  Intent intent40=new Intent(MedicineActivity.this, GaoXueYa_Acyivity40.class);
                  startActivity(intent40);
         		 }
        	  else if(groupPosition==4&&childPosition == 1){
                Intent intent41=new Intent(MedicineActivity.this, NaoXueShuan_Activity41.class);
                startActivity(intent41);
       		 }
        	  else if(groupPosition==4&&childPosition == 2){
                  Intent intent42=new Intent(MedicineActivity.this, GaoXueZhi_Activity42.class);
                  startActivity(intent42);
         		 }
        	  else if(groupPosition==4&&childPosition == 3){
                  Intent intent43=new Intent(MedicineActivity.this, TangNiaoBing_Activity43.class);
                  startActivity(intent43);
         		 }
        	  else if(groupPosition==4&&childPosition == 4){
                  Intent intent44=new Intent(MedicineActivity.this, GuanXinBing_Activity44.class);
                  startActivity(intent44);
         		 }
         if(groupPosition==5&&childPosition == 0){
                Intent intent50=new Intent(MedicineActivity.this, BiYan_Activity50.class);
                startActivity(intent50);
       		 }
	         else if(groupPosition==5&&childPosition == 1){
	             Intent intent51=new Intent(MedicineActivity.this, KuiYang_Activity51.class);
	             startActivity(intent51);
	    		 } 
	         else if(groupPosition==5&&childPosition == 2){
	             Intent intent52=new Intent(MedicineActivity.this, GanYan_Activity52.class);
	             startActivity(intent52);
	    		 } 
	         else if(groupPosition==5&&childPosition == 3){
	             Intent intent53=new Intent(MedicineActivity.this, QinGuanYan_Activity53.class);
	             startActivity(intent53);
	    		 }
	         else if(groupPosition==5&&childPosition == 4){
	             Intent intent54=new Intent(MedicineActivity.this, BaiNeiZhang_Activity54.class);
	             startActivity(intent54);
	    		 }
	         else if(groupPosition==5&&childPosition == 5){
	             Intent intent55=new Intent(MedicineActivity.this, ErYan_Activity55.class);
	             startActivity(intent55);
	    		 }
	         else if(groupPosition==5&&childPosition == 6){
	             Intent intent56=new Intent(MedicineActivity.this, YaZhouYan_Activity56.class);
	             startActivity(intent56);
	    		 }
         
         if(groupPosition==6&&childPosition == 0){
             Intent intent60=new Intent(MedicineActivity.this, YangWei_Activity60.class);
             startActivity(intent60);
    		 }
         	else if(groupPosition==6&&childPosition == 1){
             Intent intent61=new Intent(MedicineActivity.this, TuoFa_Activity61.class);
             startActivity(intent61);
    		 }
         	else if(groupPosition==6&&childPosition == 2){
                Intent intent62=new Intent(MedicineActivity.this, QianLie_Activity62.class);
                startActivity(intent62);
       		 }
         	else if(groupPosition==6&&childPosition == 3){
                Intent intent63=new Intent(MedicineActivity.this, ShenYan_Activity63.class);
                startActivity(intent63);
       		 }
         
         if(groupPosition==7&&childPosition == 0){
             Intent intent70=new Intent(MedicineActivity.this, TongJing_Activity70.class);
             startActivity(intent70);
    		 }
           else if(groupPosition==7&&childPosition == 1){
             Intent intent71=new Intent(MedicineActivity.this, YinDaoYan_Activity71.class);
             startActivity(intent71);
    		 }
           else if(groupPosition==7&&childPosition == 2){
               Intent intent72=new Intent(MedicineActivity.this, BuTiao_Activity72.class);
               startActivity(intent72);
      		 }
           else if(groupPosition==7&&childPosition == 3){
               Intent intent73=new Intent(MedicineActivity.this, RuXian_Activity73.class);
               startActivity(intent73);
      		 }
         
         
         
         
         
         
         
				return true;
             }
        	
         });
	   	
	     

	
	}
	
}
