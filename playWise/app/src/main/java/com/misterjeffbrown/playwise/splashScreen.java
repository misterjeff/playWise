package com.misterjeffbrown.playwise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Jeffrey on 5/7/2015.
 */



public class splashScreen extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer = new Thread() {
          public void run() {
              try {
                  sleep(3000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } finally {
                  Intent openMainActivity = new Intent("com.misterjeffbrown.playwise.MainActivity");
                  startActivity(openMainActivity);
              }
          }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
