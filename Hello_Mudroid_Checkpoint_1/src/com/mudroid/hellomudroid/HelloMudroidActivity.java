package com.mudroid.hellomudroid;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockMapActivity;

public class HelloMudroidActivity extends SherlockMapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}