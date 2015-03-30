package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class ShoppingCart extends Activity {

    //region INSTANCE VARIABLES

    // remember that all instances of data from the model are copies. You can't modify
    // the actual model data from here.
    private ArrayList<Product> _selectedProducts;
    private ListView _listview;
    private Button _removeItemBtn;
    private Spinner _quantitySpin;
    private Button _checkoutBtn;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // how to setup Spinners can be seen below. Use xml to fill in _quantitySpin
        // reference: http://developer.android.com/guide/topics/ui/controls/spinner.html
        // reference: http://www.mysamplecode.com/2012/10/android-spinner-programmatically.html
        setupView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
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

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        // calls the Async class to execute.
        //new AsyncCaller().execute();

    }

    /**
     * Class used to grab products from the model without holding up the UI
     * MIGHT NOT USE FOR THIS CLASS IDK.
     * Useful if we have a large database
     * reference: http://stackoverflow.com/questions/14250989/how-to-use-asynctask-correctly-android
     * reference: http://www.compiletimeerror.com/2013/01/why-and-how-to-use-asynctask.html#.VRgtM_nF8WA
     * reference: https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/
     */
    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        // Progress Dialog allows you to give users feedback when loading
        ProgressDialog pdLoading = new ProgressDialog(ShoppingCart.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            pdLoading.dismiss();
        }

    }


    public void getProducts(){

    }


    /**
     * Private method used to identify what view to show.
     */
    private void setupView(){

        // set up button listeners first.
        setUpListeners();


        setContentView(R.layout.shopping_cart);
    }

    /**
     * Private method used to setUp UI listeners based on User that logged in
     */
    private void setUpListeners(){
        //LINK UI OBJECTS TO XML HERE
        //_quantitySpin = (Spinner) findViewById(R.id.mybutton);
        //_removeItemBtn = (Button) findViewById(R.id.mybutton);
        //_checkoutBtn = (Button) findViewById(R.id.mybutton);


            _removeItemBtn.setOnClickListener(new View.OnClickListener() {
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

        _quantitySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
