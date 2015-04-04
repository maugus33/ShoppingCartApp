package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class Checkout extends Activity {

    //region INSTANCE VARIABLES
    private ArrayList<EditText> _checkoutTextFields;
    private Button _purchaseBtn;
    private Button _cancelBtn;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Checkout.context = getApplicationContext();
        setupView();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checkout, menu);
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

    /**
     * Function used to get the application's context. Only use if the application exists!
     * @return The context of this activity
     */
    public static Context getAppContext() {
        return Checkout.context;
    }

    private void calculateTotal(){

    }

    private void setupView(){
        setupListeners();
        setContentView(R.layout.checkout);

    }

    private void setupListeners(){

        //LINK UI OBJECTS TO XML HERE
        // _purchaseBtn = (Button)findViewById(R.id.mybutton);
        // _cancelBtn =(Button)findViewById(R.id.mybutton);

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });

        _purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add function you want to call here
            }
        });


    }
}
