package com.misterjeffbrown.playwise;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;


public class DiceActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        setTitle("Single Result");

        EditText edit = (EditText)findViewById(R.id.dice_numDice);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);
        edit = (EditText)findViewById(R.id.dice_numFaces);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);
        edit = (EditText)findViewById(R.id.dice_desiredOutcome);
        edit.setInputType(InputType.TYPE_CLASS_PHONE);

        Intent intent = getIntent();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dice, menu);
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

        if (id == R.id.multiple) {
            Intent intent = new Intent();
            intent.setClass(DiceActivity.this, RangeActivity.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }


    public void diceRoll(View view) {
        EditText editText_n = (EditText)findViewById(R.id.dice_numDice);
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
            editText_n.setText("1");
        }

        EditText editText_r = (EditText)findViewById(R.id.dice_numFaces);
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

        EditText editText_k = (EditText)findViewById(R.id.dice_desiredOutcome);
        Integer k = 4;
        try { k = Integer.parseInt(editText_k.getText().toString()); }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please enter an integer value.", Toast.LENGTH_SHORT).show();
            editText_k.setText("4");
        }
        TextView result = (TextView)findViewById(R.id.dice_results);

        Dice dice = new Dice(r, n, k);
        Double res = dice.calcProbability(dice);
        NumberFormat format = new DecimalFormat("0.000");
        result.setText(format.format(res * 100) + "%");
    }


    public static double P(int dice, int faces, int r) {
        if (dice == 0) {
            // Zero dice: probabiliy 1 to get 0
            if (r == 0) {
                return 1.0;
            } else {
                return 0.0;
            }
        } else {
            // Multiple dice: recursion
            double sum = 0.0;
            for (int i = r - faces; i < r; i++) {
                sum += P(dice - 1, faces, i) / faces;
            }
            return sum;
        }
    }


    public void dice_calculate(View view) {
        EditText editText_n = (EditText)findViewById(R.id.dice_numDice);
        Integer n = Integer.parseInt(editText_n.getText().toString());
        EditText editText_r = (EditText)findViewById(R.id.dice_numFaces);
        Integer r = Integer.parseInt(editText_r.getText().toString());
        EditText editText_k = (EditText)findViewById(R.id.dice_desiredOutcome);
        Integer k = Integer.parseInt(editText_k.getText().toString());
        TextView result = (TextView)findViewById(R.id.dice_results);

        Integer t = 1; //Placeholder for number of rolls
        Double tno = Math.pow(r, n);
        Integer totalNumOutcomes = tno.intValue();
        Integer match = 0;
        Double res;
        Vector<Integer> faceV = new Vector(), valV = new Vector();
        valV.addElement(1);


        for (int i = 0; i < r; i++) {
            faceV.addElement(i + 1);
        }

        for (int x = 0; x < n; x++) {
            Vector<Integer> tempV = new Vector();
            for (int y = 0; y < valV.size(); y++) {     //for every item in array, multiply that item by every face value and push to tempArray
                for (int z = 0; z < faceV.size(); z++) {
                    tempV.addElement(valV.elementAt(y) * faceV.elementAt(z));
                }
            }
            valV = tempV;
        }

        for (int i = 0; i < valV.size(); i++) {
            if (valV.elementAt(i) == k) { match++; }
        }

        res = ((match)/(tno)) * 100;
        String output = String.format("%.2f", res);
        result.setText(output + "%");

    }


}
