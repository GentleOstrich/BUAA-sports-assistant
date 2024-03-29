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
import java.util.Collection;
import java.util.Collections;
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
    protected static int iii = 3;
    protected static ArrayList<Boolean> ables = new ArrayList<>();
    protected static int mini = 30;
    protected static int maxi = 0;

    LatLng latlng0 = new LatLng(39.981370491320185,116.35171651840211); //北航新主楼
    LatLng latlng1 = new LatLng(39.981399264839105,116.35070800781251); //北航博物馆
    LatLng latlng2 = new LatLng(39.981236899823635,116.34618848562242); //北航田径场
    LatLng latlng3 = new LatLng(39.98316059217425,116.34608119726182); //北航绿园
    LatLng latlng4 = new LatLng(39.98320786171047,116.34791851043703); //北航绿园池塘
    LatLng latlng5 = new LatLng(39.983298290297334,116.34878754615785); //北航图书馆
    LatLng latlng6 = new LatLng(39.98334144935341,116.35185062885286); //中间点
    LatLng latlng7 = new LatLng(39.984087480157,116.35180234909059); //北航静园与老主楼
    LatLng latlng80 = new LatLng(39.904084310153955,116.39768689870836); //天安门广场
    LatLng latlng81 = new LatLng(39.9041131161928,116.39920502901079); //天安门广场
    LatLng latlng82 = new LatLng(39.9010143400229,116.39930158853532); //天安门广场
    LatLng latlng83 = new LatLng(39.90102668602277,116.39967441558838); //天安门广场
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
    ArrayList<LatLng> curlatLngs = new ArrayList<>();
    ArrayList<LatLng> mlatLngs = new ArrayList<>();
    ArrayList<LatLng> allatLngs = new ArrayList<>();
    String[] strs = new String[]{
            "北航新主楼", "北航博物馆", "北航田径场", "北航绿园", "北航绿园池塘", "北航图书馆", "北航静园", "北航老主楼", "天安门广场", "东交民巷", "中国法院博物馆", "日本公使馆旧址", "法国使馆旧址", "比利时使馆旧址", "意大利使馆旧址", "王府井大街", "清学部遗址", "参政胡同", "参政胡同中", "东铁匠胡同", "参政西巷", "察院胡同", "察院胡同中", "文昌胡同", "文昌胡同中", "文华胡同", "李大钊故居"
    };
    int[] pids = {R.drawable.pic0, R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10, R.drawable.pic11, R.drawable.pic12, R.drawable.pic13, R.drawable.pic14, R.drawable.pic15, R.drawable.pic16, R.drawable.pic17, R.drawable.pic18, R.drawable.pic19, R.drawable.pic20, R.drawable.pic21, R.drawable.pic22, R.drawable.pic23, R.drawable.pic24, R.drawable.pic25, R.drawable.pic26};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_map);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back); //修改actionbar左上角返回按钮的图标

        //页面初始化
        initView();

        //初始化定位
        initLocation();

        doit();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:   //返回键的id
//                this.finish();
//                return false;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                ables.clear();
                maxi = 0;
                mini = 30;
                iii = 3;
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private static ArrayList<Marker> markers = new ArrayList<>();
    private List<LatLng> makeMarkers() {
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, strs);

        allatLngs.add(latlng0);
        allatLngs.add(latlng1);
        allatLngs.add(latlng2);
        allatLngs.add(latlng3);
        allatLngs.add(latlng4);
        allatLngs.add(latlng5);
        allatLngs.add(latlng6);
        allatLngs.add(latlng7);
        allatLngs.add(latlng80);
        allatLngs.add(latlng9);
        allatLngs.add(latlng10);
        allatLngs.add(latlng11);
        allatLngs.add(latlng12);
        allatLngs.add(latlng13);
        allatLngs.add(latlng14);
        allatLngs.add(latlng15);
        allatLngs.add(latlng16);
        allatLngs.add(latlng17);
        allatLngs.add(latlng18);
        allatLngs.add(latlng19);
        allatLngs.add(latlng20);
        allatLngs.add(latlng21);
        allatLngs.add(latlng22);
        allatLngs.add(latlng23);
        allatLngs.add(latlng24);
        allatLngs.add(latlng25);
        allatLngs.add(latlng26);

        mlatLngs.add(latlng0);
        mlatLngs.add(latlng1);
        mlatLngs.add(latlng2);
        mlatLngs.add(latlng3);
        mlatLngs.add(latlng4);
        mlatLngs.add(latlng5);
        mlatLngs.add(latlng6);
        mlatLngs.add(latlng7);
        mlatLngs.add(latlng80);
        mlatLngs.add(latlng81);
        mlatLngs.add(latlng82);
        mlatLngs.add(latlng83);
        mlatLngs.add(latlng9);
        mlatLngs.add(latlng10);
        mlatLngs.add(latlng11);
        mlatLngs.add(latlng12);
        mlatLngs.add(latlng13);
        mlatLngs.add(latlng14);
        mlatLngs.add(latlng15);
        mlatLngs.add(latlng16);
        mlatLngs.add(latlng17);
        mlatLngs.add(latlng18);
        mlatLngs.add(latlng19);
        mlatLngs.add(latlng20);
        mlatLngs.add(latlng21);
        mlatLngs.add(latlng22);
        mlatLngs.add(latlng23);
        mlatLngs.add(latlng24);
        mlatLngs.add(latlng25);
        mlatLngs.add(latlng26);

        for (int i = 0; i < 27; i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            //设置标记的位置
            markerOptions.position(allatLngs.get(i));
            markerOptions.title(strs[i]);
            //设置标记的可见性，true表示显示，false表示隐藏
            markerOptions.visible(ables.get(i));
            //在地图上添加标记，返回一个标记对象
            Marker marker = tencentMap.addMarker(markerOptions);
            marker.setClickable(true);
            markers.add(marker);
            tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    String title = marker.getTitle();
                    int idx = strings.indexOf(title);
                    //将信息显示在界面上
                    ImageView iv_img = findViewById(R.id.iv_img);
                    iv_img.setBackgroundResource(pids[idx]);
                    TextView tv_name = findViewById(R.id.tv_name);
                    tv_name.setText(strs[idx]);
//                TextView tv_description = findViewById(R.id.tv_description);
//                tv_description.setText("infoUtil.getDescription()");
                    //将布局显示出来
                    RelativeLayout relativeLayout = findViewById(R.id.rl_marker);
                    relativeLayout.setVisibility(View.VISIBLE);
                    return true;
                }
            });

        }

        tencentMap.setOnMapClickListener(new TencentMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {
                RelativeLayout relativeLayout = findViewById(R.id.rl_marker);
                relativeLayout.setVisibility(View.GONE);
            }
        });
        return null;
    }

    public static void reNewMarkers() {
        for (int i = 0; i < 27; i++) {
            Marker marker = markers.get(i);
//            marker.setVisible(ables.get(i));
        }
    }

    private void doit() {
        makeMarkers();
//        MarkerOptions markerOptions = new MarkerOptions();
//        //设置标记的位置
//        markerOptions.position(latlng0);
//        markerOptions.title("北航新主楼");
//        //设置标记的可见性，true表示显示，false表示隐藏
//        markerOptions.visible(true);
//        //在地图上添加标记，返回一个标记对象
//        Marker marker = tencentMap.addMarker(markerOptions);
//        marker.setClickable(true);
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
        curlatLngs.clear();
//        if ((iii == 0 || iii == 1) && mini == 0) {
//            curlatLngs.add(new LatLng(39.981462977587896, 116.35334730148317));
//        }
        for (int i = mini; i <= maxi; i++) {
            curlatLngs.add(mlatLngs.get(i));
        }
//        List<LatLng> latLngs1 = new ArrayList<LatLng>();
//        List<LatLng> latLngs2 = new ArrayList<LatLng>();
//        List<LatLng> latLngs3 = new ArrayList<LatLng>();
//        List<LatLng> latLngs4 = new ArrayList<LatLng>();
//
//        latLngs1.add(new LatLng(39.981462977587896, 116.35334730148317));
//        latLngs1.add(latlng0);
//        latLngs1.add(latlng1);
//        latLngs1.add(latlng2);
//        latLngs1.add(latlng3);
//        latLngs1.add(latlng4);
//        latLngs1.add(latlng5);
//        latLngs1.add(latlng6);
//        latLngs1.add(latlng7);
//
//        latLngs2.add(new LatLng(39.981462977587896, 116.35334730148317));
//        latLngs2.add(latlng0);
//        latLngs2.add(latlng1);
//        latLngs2.add(latlng2);
//        latLngs2.add(latlng3);
//        latLngs2.add(latlng4);
//
//        latLngs3.add(latlng80);
//        latLngs3.add(latlng81);
//        latLngs3.add(latlng82);
//        latLngs3.add(latlng83);
//        latLngs3.add(latlng9);
//        latLngs3.add(latlng10);
//        latLngs3.add(latlng11);
//        latLngs3.add(latlng12);
//        latLngs3.add(latlng13);
//        latLngs3.add(latlng14);
//        latLngs3.add(latlng15);
//
//        latLngs4.add(latlng16);
//        latLngs4.add(latlng17);
//        latLngs4.add(latlng18);
//        latLngs4.add(latlng19);
//        latLngs4.add(latlng20);
//        latLngs4.add(latlng21);
//        latLngs4.add(latlng22);
//        latLngs4.add(latlng23);
//        latLngs4.add(latlng24);
//        latLngs4.add(latlng25);
//        latLngs4.add(latlng26);
//
//        latLngs.add(latLngs2);
//        latLngs.add(latLngs1);
//        latLngs.add(latLngs3);
//        latLngs.add(latLngs4);

        // 构造 PolylineOpitons
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(curlatLngs)
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
                new LatLngBounds.Builder().include(curlatLngs).build(),
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
