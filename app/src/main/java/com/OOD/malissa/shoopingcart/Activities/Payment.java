package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class Payment extends Activity {

    //region INSTANCE VARIABLES
    private ArrayList<String> _creditList;
    private EditText _cCName;
    private EditText _cCNum;
    private Spinner _spinMonth;// months don't need an adapter since they are static while years change
    private Spinner _spinYear;
    ArrayAdapter<String> spinYearAdapter; // used to add years calculated by calculateSpinYear() function
    private Button _addProdBtn;
    private Button _checkoutBtn;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);


        // how to setup Spinners can be seen below. Uses ArrayAdapter for fill SpinYear, Use xml to fill in SpinMonth
        // reference: http://developer.android.com/guide/topics/ui/controls/spinner.html
        // reference: http://www.mysamplecode.com/2012/10/android-spinner-programmatically.html
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
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

    public ArrayList<String> getCreditCards(){
      return null;
    }

    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        //_cCName = (EditText)findViewById(R.id.mybutton);
       // _cCNum = (EditText)findViewById(R.id.mybutton);
       // _addProdBtn = (Button)findViewById(R.id.mybutton);
       // _checkoutBtn = (Button)findViewById(R.id.mybutton);
       // _spinMonth =(Spinner)findViewById(R.id.mybutton);
       // _spinYear =(Spinner)findViewById(R.id.mybutton);

        _addProdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });

        _checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });

        _spinMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void calculateSpinYear(){

    }

}
