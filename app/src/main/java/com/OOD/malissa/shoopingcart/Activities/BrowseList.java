package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.app.DialogFragment;
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
import android.widget.Toast;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.CartObserver;
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

/**
 * BrowseList Activity controller
 */
public class BrowseList extends Activity implements CartObserver{

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
    private int currentCartCount;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _currentUser = (User) getIntent().getSerializableExtra("User");
        BrowseList.context = getApplicationContext();
        currentCartCount = bClerk.getCartCount();
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

        //if logout was clicked...
        if (id == R.id.logout) {
            // restart storeclerks
            bClerk.reset();
            sClerk.reset();
            StoreClerk.getInstance().reset();

            // redirect user to login screen
            Intent i = new Intent(getApplicationContext(), Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(i);

            return true;
        }
        //If account information is pressed...
        if (id == R.id.accinfo) {
            StoreClerk.getInstance().showAccountInfo(getAppContext(), _currentUser);
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

    @Override
    public void update(int count, boolean isEmpty) {
        // update cart count
        currentCartCount += count;
        if(_checkoutBtn != null)
        {
            _checkoutBtn.setText("view Cart("+ currentCartCount+")");
        }
        if(count == 0 && isEmpty)
        {
            // post toast
            Toast.makeText(getApplicationContext(), "no more products to add.",
                    Toast.LENGTH_LONG).show();
        }
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
            //Added an if statement so only products with non-zero
            //Quantity is displayed.
            do {
                x = bClerk.getAStoreProd();
                if(x != null)
                {
                    if(x.get_quantity() != 0) {
                        _products.add(x);

                    }
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
            //set browselist as cart observer
            bClerk.setCartObserver(this);
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
        final BrowseListAdapter cus = new BrowseListAdapter(BrowseList.context,_products, _currentUser);
        _listview.setAdapter(cus);
        _listview.setLongClickable(true);
        _listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
             @Override
             public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id)
             {
                 // get related item from browseListAdapter
                Product item = (Product) cus.getItem(pos);
                 //get the product details
                 if(_currentUser == User.BUYER)
                 {
                     bClerk.getProductDets(item);
                 }
                 else if (_currentUser == User.SELLER)
                 {
                     sClerk.getProductDets(item);
                 }

                 return true;
             }
         }
        );
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
                    //call clerk to add product
                    sClerk.addProduct();


                }
            });

            _financialBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // add function you want to call here
                    // call financial dialog box
                    DialogFragment dialog = sClerk.getFinanceDialog();
                    //dialog.setTargetFragment(this);
                    //reference: http://developer.android.com/reference/android/support/v4/app/DialogFragment.html#show(android.support.v4.app.FragmentManager, java.lang.String)
                    dialog.show(getFragmentManager(), "finance");
                }
            });
        }

        // update cart count
        this.update(0,false);

    }


}

