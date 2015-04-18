package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

/**
 * BrowseList Activity controller
 */
public class BrowseList extends Activity {

    //region INSTANCE VARIABLES

    // remember that all instances of data from the model are copies. You can't modify
    // the actual model data from here.
    private ArrayList<Product> _products;
    private ListView _listview;
    private Button _financialBtn;
    private Button _addProdBtn;
    private Button _checkoutBtn;
    private User _currentUser;
    private BuyerClerk bClerk = BuyerClerk.getInstance();
    private SellerClerk sClerk = SellerClerk.getInstance();
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _currentUser = (User) getIntent().getSerializableExtra("User");
        BrowseList.context = getApplicationContext();
        _products = new ArrayList<>();
        getProducts();
        setupView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse_list, menu);
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
     * Useful if we have a large database
     * reference: http://stackoverflow.com/questions/14250989/how-to-use-asynctask-correctly-android
     * reference: http://www.compiletimeerror.com/2013/01/why-and-how-to-use-asynctask.html#.VRgtM_nF8WAs
     * reference: https://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/
     */
    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        // Progress Dialog allows you to give users feedback when loading
        ProgressDialog pdLoading = new ProgressDialog(BrowseList.this);

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
        return BrowseList.context;
    }

    public void getProducts(){

        if(_currentUser == User.BUYER)
        {
            Product x = null;
            // get all the inventory items from each users in
            // get a store product from the buyer clerk
            do {
                x = bClerk.getAStoreProd();
                if(x != null)
                {
                    _products.add(x);
                }
            }while(x != null);
        }
        else if (_currentUser == User.SELLER)
        {
            // get the seller's inventory objects
            Product x = null;
            // get all the inventory items from each users in
            // get a store product from the buyer clerk
            do {
                x = sClerk.getInventoryProd();
                if(x != null)
                {
                    _products.add(x);
                }
            }while(x != null);

        }

    }

    public User checkUser(){
      return null;
    }

    /**
     * Private method used to identify what view to show.
     */
    private void setupView(){



        // set up which view to show
        if(_currentUser == User.BUYER){
            setContentView(R.layout.browse_list_buyer);
        }
        else if(_currentUser == User.SELLER) {
            setContentView(R.layout.browse_list_seller);
        }

        // set up button listeners first.
        setUpListeners();
    }

    /**
     * Private method used to setUp UI listeners based on User that logged in
     */
    private void setUpListeners(){

        _listview=(ListView)findViewById(R.id.list);
        BrowseListAdapter cus = new BrowseListAdapter(BrowseList.context,_products, _currentUser);
        _listview.setAdapter(cus);
        if(_currentUser == User.BUYER){
            // add view id of button when available
            _checkoutBtn = (Button) findViewById(R.id.check_out);

            _checkoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BuyerClerk.getInstance().showShoppingCart();
                }
            });
        }
        else if(_currentUser == User.SELLER) {
            _addProdBtn = (Button) findViewById(R.id.new_prod);
            _financialBtn = (Button) findViewById(R.id.fin_sum);

            _addProdBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // add function you want to call here
                }
            });

            _financialBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // add function you want to call here
                }
            });
        }

    }


}

