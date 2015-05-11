package com.misterjeffbrown.playwise;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("playWise");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void single(View view) {
        Intent intent_dice = new Intent(this, DiceActivity.class);
        startActivity(intent_dice);
    }

    public void range(View view) {
        Intent intent_range = new Intent(this, RangeActivity.class);
        startActivity(intent_range);
    }

    public void ask(View view) {
        Intent intent_ask = new Intent(this, AskActivity.class);
        startActivity(intent_ask);
    }

    public void main_exit(View view) {
        finish();
        System.exit(0);
    }
}
