package com.mudroid.hellomudroid;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockMapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class HelloMudroidActivity extends SherlockMapActivity {
    private MapView mapView;
	private MyLocationOverlay myLocationOverlay;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		// Grab a hook to the mapView
		mapView = (MapView) findViewById(R.id.map);
		// Turn on zoom controls
		mapView.setBuiltInZoomControls(true);
		
		// Instantiate MyLocationOverlay
		myLocationOverlay = new MyLocationOverlay(this, mapView);
		
		// Turn on myLocation
		myLocationOverlay.enableMyLocation();
		
		// Add it to the mapView
		mapView.getOverlays().add(myLocationOverlay);
		
		// redraw the mapView with all the new stuff
		mapView.invalidate();
    }
    
	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}

	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}