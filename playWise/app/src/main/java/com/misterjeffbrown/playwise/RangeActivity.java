package com.misterjeffbrown.playwise;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class RangeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);

        setTitle("Ranged Result");

        EditText edit = (EditText)findViewById(R.id.range_numDice);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);
        edit = (EditText)findViewById(R.id.range_numFaces);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);
        edit = (EditText)findViewById(R.id.range_desiredStart);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);
        edit = (EditText)findViewById(R.id.range_desiredEnd);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);

        Intent intent = getIntent();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_range, menu);
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


        if (id == R.id.single) {
            Intent intent = new Intent();
            intent.setClass(RangeActivity.this, DiceActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void diceRoll(View view) {
        EditText editText_n = (EditText)findViewById(R.id.range_numDice);
        Integer n = 1;
        try {
            n = Integer.parseInt(editText_n.getText().toString());
            if(n > 6) {
                n = 1;
                editText_n.setText("1");
                Toast.makeText(getApplicationContext(), "Please enter a smaller value.", Toast.LENGTH_SHORT).show();
            }
            else if(n < 0) {
                n = 1;
                editText_n.setText("1");
                Toast.makeText(getApplicationContext(), "Please enter a non-negative value.", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter an integer value.", Toast.LENGTH_SHORT).show();
            editText_n.setText("2");
        }

        EditText editText_r = (EditText)findViewById(R.id.range_numFaces);
        Integer r = 6;
        try {
            r = Integer.parseInt(editText_r.getText().toString());
            if(r > 20) {
                r = 6;
                editText_r.setText("6");
                Toast.makeText(getApplicationContext(), "Please enter a smaller value.", Toast.LENGTH_SHORT).show();
            }
            else if(r < 0) {
                r = 6;
                editText_r.setText("6");
                Toast.makeText(getApplicationContext(), "Please enter a non-negative value.", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter an integer value.", Toast.LENGTH_SHORT).show();
            editText_r.setText("6");
        }

        EditText editText_k = (EditText)findViewById(R.id.range_desiredStart);
        Integer k = 4;
        try{ k = Integer.parseInt(editText_k.getText().toString()); }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter an integer value.", Toast.LENGTH_SHORT).show();
            editText_k.setText("4");
        }

        EditText editText_l = (EditText)findViewById(R.id.range_desiredEnd);
        Integer l = 7;
        try{ l = Integer.parseInt(editText_l.getText().toString()); }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter an integer value.", Toast.LENGTH_SHORT).show();
            editText_l.setText("8");
        }

        TextView result = (TextView)findViewById(R.id.range_results);
        Double res = 0.0;
        for(int x = k; x < l+1; x++){
            Dice dice = new Dice(r, n, x);
            res += dice.calcProbability(dice);
        }

        //Dice dice = new Dice(r, n, k);
        NumberFormat format = new DecimalFormat("0.000");
        result.setText(format.format(res * 100) + "%");
    }
}
