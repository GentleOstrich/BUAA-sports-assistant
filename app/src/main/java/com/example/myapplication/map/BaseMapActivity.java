package com.example.myapplication.map;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.map.MapLifecycle;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.lib.models.MarkerInfo;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.LocationSource;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.UiSettings;
import com.tencent.tencentmap.mapsdk.maps.TencentMapNavi;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.object.param.DrivingParam;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;

import java.util.ArrayList;
import java.util.List;
//import com.tencent.lbssearch.object.result.DrivingResult;


/**
 * 基础地图
 *
 * @author llw
 */
public class BaseMapActivity extends AppCompatActivity implements LocationSource,
        TencentLocationListener {

    //基础地图
    private MapView mapView;
    //腾讯地图
    private TencentMap tencentMap;
    //地图生命周期
    private MapLifecycle mapLifecycle;

    //定位管理
    private TencentLocationManager locationManager;
    //定位请求
    private TencentLocationRequest locationRequest;
    //定位数据源监听
    private LocationSource.OnLocationChangedListener locationChangedListener;
    private int[] ids = {R.id.checkbox_0, R.id.checkbox_1};
    private int[] bids = {R.id.checkbox_back_0, R.id.checkbox_back_1};
    protected static int iii;

    LatLng latlng0 = new LatLng(39.981370491320185,116.35171651840211); //北航新主楼
    LatLng latlng1 = new LatLng(39.981399264839105,116.35070800781251); //北航博物馆
    LatLng latlng2 = new LatLng(39.9812595076337,116.34603023529054); //北航田径场
    LatLng latlng3 = new LatLng(39.983257186409034,116.34615898132326); //北航绿园
    LatLng latlng4 = new LatLng(39.98320786171047,116.34791851043703); //北航绿园池塘
    LatLng latlng5 = new LatLng(39.983298290297334,116.34878754615785); //北航图书馆
    LatLng latlng6 = new LatLng(39.98332295261845,116.35138392448427); //中间点
    LatLng latlng7 = new LatLng(39.984087480157,116.35180234909059); //北航静园与老主楼
    LatLng latlng8 = new LatLng(39.90102668602277,116.39967441558838); //天安门广场
    LatLng latlng9 = new LatLng(39.90201435880345,116.39963150024415); //东交民巷
    LatLng latlng10 = new LatLng(39.90247115264972,116.40726506710054); //中国法院博物馆
    LatLng latlng11 = new LatLng(39.902557572764536,116.40836477279663); //日本公使馆旧址
    LatLng latlng12 = new LatLng(39.902627531825296,116.41066610813141); //安亲王府、法国使馆旧址
    LatLng latlng13 = new LatLng(39.902611070876226,116.41171753406526); //比利时使馆旧址
    LatLng latlng14 = new LatLng(39.906405215031675,116.41151368618013); //意大利使馆旧址
    LatLng latlng15 = new LatLng(39.908174646424804,116.41149222850801); //王府井大街
    LatLng latlng16 = new LatLng(39.90512131992228,116.37350142002107); //清学部遗址
    LatLng latlng17 = new LatLng(39.90514601043976,116.3717418909073); //参政胡同
    LatLng latlng18 = new LatLng(39.905569862935394,116.37175261974336); //中间点
    LatLng latlng19 = new LatLng(39.90557397799534,116.36971414089204); //东铁匠胡同
    LatLng latlng20 = new LatLng(39.90701423378536,116.3696873188019); //参政西巷
    LatLng latlng21 = new LatLng(39.907080073326334,116.3659429550171); //察院胡同
    LatLng latlng22 = new LatLng(39.90548344661996,116.36593222618104); //中间点
    LatLng latlng23 = new LatLng(39.905495791814566,116.36541724205019); //文昌胡同
    LatLng latlng24 = new LatLng(39.90515835569514,116.36524558067323); //中间点
    LatLng latlng25 = new LatLng(39.904882644461395,116.36515974998476); //文华胡同
    LatLng latlng26 = new LatLng(39.90490733506487,116.36586785316469); //李大钊故居

    ArrayList<List<LatLng>> latLngs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_map);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        //页面初始化
        initView();

        //初始化定位
        initLocation();

        doit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void makeMarkInfo(int i) {
        MarkerOptions markerOptions = new MarkerOptions();
        //设置标记的位置
//        markerOptions.position(latlng);
//        markerOptions.title(name);
        //设置标记的可见性，true表示显示，false表示隐藏
        markerOptions.visible(true);
        //在地图上添加标记，返回一个标记对象
        Marker marker = tencentMap.addMarker(markerOptions);
        marker.setClickable(true);
    }

    private List<LatLng> makeMarkers() {

        MarkerOptions markerOptions = new MarkerOptions();
        //设置标记的位置
        markerOptions.position(latlng0);
        markerOptions.title("北航新主楼");
        //设置标记的可见性，true表示显示，false表示隐藏
        markerOptions.visible(true);
        //在地图上添加标记，返回一个标记对象
        Marker marker = tencentMap.addMarker(markerOptions);
        marker.setClickable(true);
//        tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                //将信息显示在界面上
//                ImageView iv_img = findViewById(R.id.iv_img);
//                iv_img.setBackgroundResource(R.drawable.buaa_lib);
//                TextView tv_name = findViewById(R.id.tv_name);
//                tv_name.setText("infoUtil.getName()");
//                TextView tv_description = findViewById(R.id.tv_description);
//                tv_description.setText("infoUtil.getDescription()");
//                //将布局显示出来
//                RelativeLayout relativeLayout = findViewById(R.id.rl_marker);
//                relativeLayout.setVisibility(View.VISIBLE);
//                return true;
//            }
//        });
        //地图点击事件
//        mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
//            @Override
//            public boolean onMapPoiClick(MapPoi arg0) {
//                return false;
//            }
//            @Override
//            public void onMapClick(LatLng arg0) {
//                rl_marker.setVisibility(View.GONE);
//            }
//        });
        return null;
    }

    private void doit() {
        MarkerOptions markerOptions = new MarkerOptions();
        //设置标记的位置
        markerOptions.position(latlng0);
        markerOptions.title("北航新主楼");
        //设置标记的可见性，true表示显示，false表示隐藏
        markerOptions.visible(true);
        //在地图上添加标记，返回一个标记对象
        Marker marker = tencentMap.addMarker(markerOptions);
        marker.setClickable(true);
//        tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                //将信息显示在界面上
//                ImageView iv_img = findViewById(R.id.iv_img);
//                iv_img.setBackgroundResource(R.drawable.buaa_lib);
//                TextView tv_name = findViewById(R.id.tv_name);
//                tv_name.setText("infoUtil.getName()");
//                TextView tv_description = findViewById(R.id.tv_description);
//                tv_description.setText("infoUtil.getDescription()");
//                //将布局显示出来
//                RelativeLayout relativeLayout = findViewById(R.id.rl_marker);
//                relativeLayout.setVisibility(View.VISIBLE);
//                return true;
//            }
//        });
//        marker.setOnClickListener(new TencentMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                //当用户点击Marker时，执行以下操作
//                //显示或隐藏InfoWindow
//                if (infoWindow.isVisible()) {
//                    //如果InfoWindow已经显示，就隐藏它
//                    tencentMap.hideInfoWindow();
//                } else {
//                    //如果InfoWindow没有显示，就显示它
//                    tencentMap.showInfoWindow(infoWindow);
//                }
//                //返回true，表示事件已经被消费，不会触发其他的监听器
//                return true;
//            }
//        });
        // 构造折线点串
        List<LatLng> latLngs1 = new ArrayList<LatLng>();
        List<LatLng> latLngs2 = new ArrayList<LatLng>();
        List<LatLng> latLngs3 = new ArrayList<LatLng>();
        List<LatLng> latLngs4 = new ArrayList<LatLng>();

        latLngs1.add(latlng0);
        latLngs1.add(latlng1);
        latLngs1.add(latlng2);
        latLngs1.add(latlng3);
        latLngs1.add(latlng4);
        latLngs1.add(latlng5);
        latLngs1.add(latlng6);
        latLngs1.add(latlng7);

        latLngs2.add(latlng0);
        latLngs2.add(latlng1);
        latLngs2.add(latlng2);
        latLngs2.add(latlng3);
        latLngs2.add(latlng4);

        latLngs3.add(latlng8);
        latLngs3.add(latlng9);
        latLngs3.add(latlng10);
        latLngs3.add(latlng11);
        latLngs3.add(latlng12);
        latLngs3.add(latlng13);
        latLngs3.add(latlng14);
        latLngs3.add(latlng15);

        latLngs4.add(latlng16);
        latLngs4.add(latlng17);
        latLngs4.add(latlng18);
        latLngs4.add(latlng19);
        latLngs4.add(latlng20);
        latLngs4.add(latlng21);
        latLngs4.add(latlng22);
        latLngs4.add(latlng23);
        latLngs4.add(latlng24);
        latLngs4.add(latlng25);
        latLngs4.add(latlng26);

        latLngs.add(latLngs1);
        latLngs.add(latLngs2);
        latLngs.add(latLngs3);
        latLngs.add(latLngs4);
        // 构造 PolylineOpitons
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(latLngs.get(iii))
                // 折线设置圆形线头
                .lineCap(true)
                // 折线的颜色为绿色
                .color(0xff00ff00)
                // 折线宽度为25像素
                .width(25)
                // 还可以添加描边颜色
                .borderColor(0xffff0000)
                // 描边颜色的宽度，线宽还是 25 像素，不过填充的部分宽度为 `width` - 2 * `borderWidth`
                .borderWidth(5);

        // 绘制折线
        Polyline polyline = tencentMap.addPolyline(polylineOptions);

        // 将地图视野移动到折线所在区域(指定西南坐标和东北坐标)，设置四周填充的像素
        tencentMap.moveCamera(CameraUpdateFactory.newLatLngBounds(
                new LatLngBounds.Builder().include(latLngs.get(iii)).build(),
                100));
    }

    /**
     * 页面初始化
     */
    private void initView() {
        //地图
        mapView = findViewById(R.id.mapView);
        //创建tencentMap地图对象
        tencentMap = mapView.getMap();
        //设置为经典样式
        tencentMap.setMapStyle(2);

        //设置最大缩放等级 最大值 20 最小值 1
        tencentMap.setMaxZoomLevel(20);
        //设置最小缩放等级 最大值 20 最小值 1
        tencentMap.setMinZoomLevel(12);
        //启用3D视图
        tencentMap.setBuilding3dEffectEnable(true);

        //获取地图UI设置对象
        UiSettings uiSettings = tencentMap.getUiSettings();
        //设置logo的大小   比例范围(0.7~1.3)
        uiSettings.setLogoScale(0.7f);

        //设置logo底部居中
        uiSettings.setLogoPosition(TencentMapOptions.LOGO_POSITION_BOTTOM_CENTER);
        //设置logo左下角显示同时设置与MapView的左边距和下边距为100
        //uiSettings.setLogoPosition(TencentMapOptions.LOGO_POSITION_BOTTOM_LEFT, new int[]{100, 100});

        //设置比例尺是否显示  true显示，false不显示，不设置则为默认值，默认为true
        uiSettings.setScaleViewEnabled(true);
        //设置比例尺显示在右下角
        uiSettings.setScaleViewPosition(TencentMapOptions.SCALEVIEW_POSITION_BOTTOM_RIGHT);

        //设置指南针是否显示 true显示， false不显示，不设置则为默认值，默认为false
        uiSettings.setCompassEnabled(true);
        //设置指南针的填充值。一个值 为设置指南针上边缘距离 MapView 上边缘的距离为100px
        // 两个值 为设置指南针距离 MapView 左边缘和上边缘的距离，第一个值为左，第二个值为上。
        uiSettings.setCompassExtraPadding(20, 20);

        //设置缩放控件是否显示，true显示，false不显示，不设置则为默认值，默认为false
        uiSettings.setZoomControlsEnabled(true);
        //设置缩放控件显示在左下角
        uiSettings.setZoomPosition(TencentMapOptions.ZOOM_POSITION_BOTTOM_LEFT);


        mapLifecycle = new MapLifecycle(mapView);
        //将观察者与被观察者绑定起来
        getLifecycle().addObserver(mapLifecycle);
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        //用于访问腾讯定位服务的类, 周期性向客户端提供位置更新
        locationManager = TencentLocationManager.getInstance(this);
        //设置坐标系
        locationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
        //创建定位请求
        locationRequest = TencentLocationRequest.create();
        //设置定位周期（位置监听器回调周期）为3s
        locationRequest.setInterval(3000);

        //地图上设置定位数据源
        tencentMap.setLocationSource(this);
        //设置当前位置可见
        tencentMap.setMyLocationEnabled(true);
    }

    /**
     * 接收定位结果
     */
    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int code, String reason) {

        if (code == TencentLocation.ERROR_OK && locationChangedListener != null) {
            //重新构建一个定位对象
            Location location = new Location(tencentLocation.getProvider());
            //设置经纬度以及精度
            location.setLatitude(tencentLocation.getLatitude());
            location.setLongitude(tencentLocation.getLongitude());
            location.setAccuracy(tencentLocation.getAccuracy());
            Log.d("location-->", tencentLocation.getLatitude() + "==" + tencentLocation.getLongitude());
            //更改位置定位，此时地图上就会显示当前定位到位置
            locationChangedListener.onLocationChanged(location);
        }
    }

    /**
     * 用于接收GPS、WiFi、Cell状态码
     */
    @Override
    public void onStatusUpdate(String name, int status, String desc) {
        //GPS, WiFi, Radio 等状态发生变化
        Log.v("State changed", name + "===" + desc);
    }

    /**
     * 启用
     *
     * @param onLocationChangedListener 数据源更改监听
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        locationChangedListener = onLocationChangedListener;

        int error = locationManager.requestLocationUpdates(locationRequest, this, Looper.myLooper());
        switch (error) {
            case 1:
                showMsg("设备缺少使用腾讯定位服务需要的基本条件");
                break;
            case 2:
                showMsg("AndroidManifest 中配置的 key 不正确");
                break;
            case 3:
                showMsg("自动加载libtencentloc.so失败");
                break;
            default:
                break;
        }
    }

    /**
     * 停用
     */
    @Override
    public void deactivate() {
        locationManager.removeUpdates(this);
        locationManager = null;
        locationRequest = null;
        locationChangedListener = null;
    }

    /**
     * Toast提示
     *
     * @param msg
     */
    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
