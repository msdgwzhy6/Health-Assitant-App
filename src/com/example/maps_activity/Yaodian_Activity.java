package com.example.maps_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.PoiOverlay;
import com.example.assistant.R;


public class Yaodian_Activity extends MapActivity {
	//添加百度地图的相关控件  
    private MapView mapView;  
    //加载百度地图的引起  
    private BMapManager bMapManager;  
    //定义百度地图的KEY  
    private String key = "6747334F8FE3431E41F7DD495E956582E5D3D376";
    //在百度地图上添加相应的控件  
    private MapController mapController; 
    //定位监听器
    LocationListener mLocationListener = null;
    //定位图层
    MyLocationOverlay mLocationOverlay = null;
    private EditText editCity;
	private EditText editGeoCodeKey;
	private EditText areaText;
	private GetOverlay getOverlay;
	private GeoPoint geoPoint = new GeoPoint(122084095, 37422006);
	private MKSearch mSearch = null; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		 //首先实例化mapView  
        mapView = (MapView) this.findViewById(R.id.bmapView);  
        bMapManager = new BMapManager(Yaodian_Activity.this);  
        //调用百度地图加载KEY  
        bMapManager.init(key, new MKGeneralListener() {
			
			@Override
			public void onGetPermissionState(int arg0) {
				// TODO Auto-generated method stub
				 if(arg0 == 300){  
	                    Toast.makeText(Yaodian_Activity.this, "您输入的KEY有问题,请核实", 2).show();  
	                }  
				
			}
			
			@Override
			public void onGetNetworkState(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        this.initMapActivity(bMapManager);
        //表示可以设置缩放功能  
        mapView.setBuiltInZoomControls(true);  
        mapController = mapView.getController();  
       
        //设置地图的缩放级别  
        mapController.setZoom(15);
        //设置模式为交通地图
        mapView.setTraffic(true);
        //设置启用内置的缩放控件
        mapView.setBuiltInZoomControls(true);
        Button yiyuan = (Button) findViewById(R.id.button2);  
        yiyuan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Yaodian_Activity.this, Yiyuan_Activity.class);
				startActivity(intent);
				
			}
		});
        //添加定位图层
        mLocationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(mLocationOverlay);
        Button buttonSearchArea = (Button) findViewById(R.id.button1);  
        buttonSearchArea.setOnClickListener(new View.OnClickListener() {  
  
            @Override  
            public void onClick(View v) {  
  
                  
                mSearch.poiSearchNearBy(areaText.getText().toString(),// 搜索XX附近5000米范围的XXX  
                        geoPoint, 5000);  
  
            }  
        });  
        //注册定位事件
        mLocationListener = new LocationListener() {
			
			@Override
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				if(arg0!=null){
					GeoPoint geoPoint = new GeoPoint((int)(arg0.getLatitude()*1e6),
							(int)(arg0.getLongitude()*1e6));
					//显示用户经纬度
					String log = String.format("当前的位置："+"经度：%f "+"纬度：%f ",
							arg0.getLongitude(), arg0.getLatitude());
					TextView locationtext = (TextView)findViewById(R.id.textview);
					locationtext.setText(log);
					
					//获取点动画显示
					mapView.getController().animateTo(geoPoint);
					mapController = mapView.getController();
					//设置地图的中心
					mapController.setCenter(geoPoint);
					//设置缩放级别
					mapController.setZoom(15);
					//初始化
					MKSearch mMKSearch = new MKSearch();
					mMKSearch.init(bMapManager, new MySearchListener());
					//执行搜索
					mMKSearch.poiSearchNearBy("药店", geoPoint, 2000);
					
				}
				
			}
		};
        
        
	}
	
	  @Override  
	    protected void onDestroy() {  
		    super.onDestroy(); 
	        if(bMapManager != null){  
	            bMapManager.destroy();  
	            bMapManager = null;  
	        }  
	        
	    }  
	    @Override  
	    protected void onResume() {  
	    	super.onResume();
	        if(bMapManager != null){  
	        	//开启地图注册定位事件，定位后将地图移到定位点
	        	bMapManager.getLocationManager().requestLocationUpdates(mLocationListener);
	        	mLocationOverlay.enableMyLocation();//关闭更新
	        	mLocationOverlay.enableCompass();   //打开指针
	            bMapManager.start();  
	        } 
	       
	    }  
	    @Override  
	    protected void onPause() {  
	    	super.onPause(); 
	        if(bMapManager != null){  
	        	//终止百度地图
	        	bMapManager.getLocationManager().removeUpdates(mLocationListener);
	        	mLocationOverlay.disableMyLocation();
	        	mLocationOverlay.disableCompass();   //关闭指针
	            bMapManager.stop();  
	        } 
	       
	    }  
	   
	    
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	 /**
	 * * 实现MKSearchListener接口,用于实现异步搜索服务 * @author SXF
	 */
	public class MySearchListener implements MKGeneralListener, MKSearchListener {
		/** * 根据经纬度搜索地址信息结果 * * @param result 搜索结果 * @param iError 错误号 （0表示正确返回） */
		@Override
		public void onGetNetworkState(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetPermissionState(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		/** * 驾车路线搜索结果 * * @param result 搜索结果 * @param iError 错误号 */
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onGetPoiDetailSearchResult(int arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * * POI搜索结果（范围检索、城市POI检索、周边检索） * * @param result 搜索结果 * @param type
		 * 返回结果类型（11,12,21:poi列表 7:城市列表） * @param iError 错误号（0表示正确返回）
		 */
		@Override
		public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub
			if(arg0  ==null){
				return;
			}
			// PoiOverlay是baidu map api提供的用于显示POI的Overlay
			PoiOverlay poioverlay = new PoiOverlay(Yaodian_Activity.this, mapView);
			// 设置搜索到的POI数据
			poioverlay.setData(arg0.getAllPoi());
			// 在地图上显示PoiOverlay（将搜索到的兴趣点标注在地图上）
			mapView.getOverlays().add(poioverlay);
			
		}

		@Override
		public void onGetRGCShareUrlResult(String arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		/** * 公交换乘路线搜索结果 * * @param result 搜索结果 * @param iError 错误号（0表示正确返回） */
		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		/** * 步行路线搜索结果 * * @param result 搜索结果 * @param iError 错误号（0表示正确返回） */
		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
	private void gotoLocate() {// 获取所在位置
		Drawable marker = getResources().getDrawable(R.drawable.iconmarka); // 得到需要标在地图上的资源
		marker.setBounds(0, 0, marker.getIntrinsicWidth(),
				marker.getIntrinsicHeight()); // 为maker定义位置和边界
		mapView.getOverlays().clear();
		mapView.getOverlays().add(getOverlay);
		//mapView.getOverlays().add(
			//	new OverItemT(marker, Yaodian_Activity.this, geoPoint, ""));

		mapView.getController().animateTo(geoPoint);
		mapController = mapView.getController();
		// 设置地图的中心
		mapController.setCenter(geoPoint);
		// 设置地图默认的缩放级别
		mapController.setZoom(16);
	}

	private void getInitLoc() {// 初始化时候获取坐标
		try {

			LocationManager locationManager;
			String context = Context.LOCATION_SERVICE;
			locationManager = (LocationManager) getSystemService(context);
			// String provider = LocationManager.GPS_PROVIDER;

			Criteria criteria = new Criteria();
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			criteria.setAltitudeRequired(false);
			criteria.setBearingRequired(false);
			criteria.setCostAllowed(true);
			criteria.setPowerRequirement(Criteria.POWER_LOW);
			String provider = locationManager.getBestProvider(criteria, true);
			Location location = locationManager.getLastKnownLocation(provider);
			geoPoint = new GeoPoint((int) (location.getLatitude() * 1e6),
					(int) (location.getLongitude() * 1e6));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	class GetOverlay extends Overlay {
		GeoPoint geo;

		@Override
		public void draw(Canvas canvas, MapView gmapView, boolean arg2) {
			super.draw(canvas, mapView, arg2);
			if (geo == null) {
				return;
			}
			Log.i("11111111111111111111", arg2 + "-------draw--");
		}

		@Override
		public boolean onTap(GeoPoint geo, MapView arg1) {
			geoPoint = geo;
			Drawable marker = getResources().getDrawable(R.drawable.iconmarka); // 得到需要标在地图上的资源
			marker.setBounds(0, 0, marker.getIntrinsicWidth(),
					marker.getIntrinsicHeight()); // 为maker定义位置和边界
			mapView.getOverlays().clear();
			mapView.getOverlays().add(getOverlay);
			/*mapView.getOverlays()
					.add(new OverItemT(marker, Yaodian_Activity.this,geoPoint, ""));*/
			Log.i("11111111111111111111", geo.getLongitudeE6() / 1E6
					+ "----------" + geo.getLatitudeE6() / 1E6);
			return super.onTap(geo, arg1);
		}
		

	}

}
