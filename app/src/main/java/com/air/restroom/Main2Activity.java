package com.air.restroom;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.content.Intent;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView.MapViewEventListener;
import net.daum.mf.map.api.MapView;

public class Main2Activity extends Activity {
    Intent it;
    double[] coords;
    private static final String API_KEYS = "6e83ed1e757e4911a78748828489f975";
    MapView mapView;
    MapPoint mapPoint_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        it = getIntent();

        coords = it.getDoubleArrayExtra("coord");

        mapView = new MapView(this);
        mapView.setDaumMapApiKey(API_KEYS);

        ViewGroup viewGroup = (ViewGroup)findViewById(R.id.mapView);
        viewGroup.addView(mapView);

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(coords[0], coords[1]), true);
        mapView.setMapViewEventListener(mapViewEventListener);
        System.out.println(coords[0] + " " + coords[1]);

    }

    MapViewEventListener mapViewEventListener = new MapViewEventListener() {
        @Override
        public void onMapViewInitialized(MapView mapView) {
            Log.d("Init", mapView.toString());
        }

        @Override
        public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
            Log.d("CenterPointMoved", mapPoint.getMapPointGeoCoord().toString());
        }

        @Override
        public void onMapViewZoomLevelChanged(MapView mapView, int i) {

        }

        @Override
        public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

        }

        @Override
        public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
            Log.d("SingleTapped", mapPoint.getMapPointGeoCoord().latitude + " " + mapPoint.getMapPointGeoCoord().longitude);

            mapPoint_ = mapPoint;
            Intent intent = new Intent(getApplicationContext(), Popup.class);
            startActivityForResult(intent, 1);

        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode== 1 && resultCode==RESULT_OK){

            if(data.getStringExtra("isEnd").equals("y")){//use data

                Intent intent_info = new Intent(getApplicationContext(), Add_info.class);

                startActivityForResult(intent_info, 2);

            }
            else if(data.getStringExtra("isEnd").equals("n")){//use data
            }
        }

        if (requestCode == 2 && resultCode==RESULT_OK) {

            if(data.getStringExtra("isEnd").equals("y")) {

                int cnt=0;

                MapPOIItem marker = new MapPOIItem();
                marker.setItemName("Default Marker");
                marker.setTag(0);
                marker.setMapPoint(MapPoint.mapPointWithGeoCoord(mapPoint_.getMapPointGeoCoord().latitude, mapPoint_.getMapPointGeoCoord().longitude));
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                mapView.addPOIItem(marker);

                SharedPreferences info = getSharedPreferences("info", MODE_PRIVATE);
                SharedPreferences.Editor editor = info.edit();

                editor.putString("mapPoint-X_"+String.valueOf(cnt), String.valueOf(mapPoint_.getMapPointGeoCoord().latitude));
                editor.putString("mapPoint-Y_"+String.valueOf(cnt), String.valueOf(mapPoint_.getMapPointGeoCoord().longitude));

                cnt++;

            }
        }
    }

}