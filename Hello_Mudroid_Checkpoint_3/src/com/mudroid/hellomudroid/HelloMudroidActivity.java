package com.mudroid.hellomudroid;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockMapActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class HelloMudroidActivity extends SherlockMapActivity {
	private final int REASONABLE_ZOOM_LEVEL = 18;
	private MapView mapView;
	private MyLocationOverlay myLocationOverlay;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// This has to be called before we call super.onCreate
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Load a partially transparent black background
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.black_semi_transparent));

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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.action_items, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.actionbar_mylocation:
			// Make sure we avoid the NPE if mylocation is not ready yet
			if (myLocationOverlay.getMyLocation() != null) {	
				mapView.getController().animateTo(
						myLocationOverlay.getMyLocation(), new Runnable() {

							@Override
							public void run() {
								if (mapView.getZoomLevel() < REASONABLE_ZOOM_LEVEL)
									for (int i = mapView.getZoomLevel(); i < REASONABLE_ZOOM_LEVEL; i++) {
										mapView.getController().zoomIn();
									}
							}
						});
			} else {
				// If the location is not ready yet, tell the user
				Toast.makeText(this, R.string.waiting_for_location, Toast.LENGTH_LONG);
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
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
		mapView.invalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}