package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShoppingCart extends Activity {

    //region INSTANCE VARIABLES

    // remember that all instances of data from the model are copies. You can't modify
    // the actual model data from here.
    private ArrayList<Product> _selectedProducts;
    private ListView _listview;
    private Button _payBtn;

    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShoppingCart.context = getApplicationContext();

        getProducts();

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

        //if logout was clicked...
        if (id == R.id.logout) {
            // restart storeclerks
            BuyerClerk.getInstance().reset();
            SellerClerk.getInstance().reset();
            StoreClerk.getInstance().reset();

            // redirect user to login screen
            Intent i = new Intent(getApplicationContext(), Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(i);

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

    /**
     * Function used to get the application's context. Only use if the application exists!
     * @return The context of this activity
     */
    public static Context getAppContext() {
        return ShoppingCart.context;
    }


    public void getProducts(){
        BuyerClerk Clerk = BuyerClerk.getInstance();

        _selectedProducts = Clerk.getCartItems();


    }


    /**
     * Private method used to identify what view to show.
     */
    private void setupView(){

        setContentView(R.layout.shopping_cart);

        // set up button listeners.
        setUpListeners();

    }

    /**
     * Private method used to setUp UI listeners based on User that logged in
     */
    private void setUpListeners(){
        //LINK UI OBJECTS TO XML HERE
        _listview=(ListView)findViewById(R.id.cartProds);
        ShoppingCartAdapter cus = new ShoppingCartAdapter(ShoppingCart.context,_selectedProducts);
        _listview.setAdapter(cus);

        _payBtn = (Button) findViewById(R.id.paybtn);

        //Modified pay button so that if the cart is empty, it does nothing and shows a message. 4/19/15
            _payBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // add function you want to call here
                    if(_selectedProducts.isEmpty()) {
                        // post toast
                        Toast.makeText(getAppContext(), "Shopping Cart is empty. Nothing to purchase.",
                                Toast.LENGTH_LONG).show();
                        //((TextView) findViewById(R.id.shoppingcart_empty)).setVisibility(View.VISIBLE);
                    }
                    else {
                        BuyerClerk.getInstance().getVerifyPurchase();
                    }
                }
            });



    }
}
