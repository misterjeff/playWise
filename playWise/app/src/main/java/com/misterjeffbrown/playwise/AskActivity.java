package com.misterjeffbrown.playwise;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class AskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ask, menu);
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


    public String prevQ = "blarb";

    public String getQ() {
        return prevQ;
    }

    public void setQ(String q) {
        prevQ = q;
    }

    public void askQuestion(View view) {
        EditText editText_q = (EditText)findViewById(R.id.ask_question);
        String q = editText_q.getText().toString();
        TextView textView_r = (TextView)findViewById(R.id.ask_results);

        if (prevQ.equals(q)) { }
        else {
            prevQ = q;
            Random rand = new Random();
            Integer r = rand.nextInt(100);

            if (r.equals(0)) textView_r.setText("Impossible.");
            else if (r < 10) textView_r.setText("Not gonna happen.");
            else if (r < 20) textView_r.setText("Extremely unlikely.");
            else if (r < 30) textView_r.setText("Don't count on it.");
            else if (r < 40) textView_r.setText("Not looking great.");
            else if (r < 50) textView_r.setText("Less than likely.");
            else if (r < 60) textView_r.setText("It's a crapshoot.");
            else if (r < 70) textView_r.setText("Somewhat likely.");
            else if (r < 80) textView_r.setText("Decently probable.");
            else if (r < 90) textView_r.setText("It's a safe bet.");
            else if (r < 100) textView_r.setText("All but guaranteed.");
            else if (r.equals(100)) textView_r.setText("It is fate.");

        }
    }
}
