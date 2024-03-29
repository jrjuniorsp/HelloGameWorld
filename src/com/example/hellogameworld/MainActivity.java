package com.example.hellogameworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.hellogameworld.view.MonkeyIslandGamePanel;

public class MainActivity extends Activity {

	 private static final String TAG = MainActivity.class.getSimpleName();

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // requesting to turn the title OFF
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        // making it full screen
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        // set our MainGamePanel as the View
	        setContentView(new MonkeyIslandGamePanel(this));
	        Log.d(TAG, "View added");
	    }

	 @Override
	 protected void onDestroy() {
	  Log.d(TAG, "Destroying...");
	  super.onDestroy();
	 }

	 @Override
	 protected void onStop() {
	  Log.d(TAG, "Stopping...");
	  super.onStop();
	 }
}
