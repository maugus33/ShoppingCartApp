package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Activities.Interfaces.Editable;
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class ProductDetails extends Activity implements Editable {

    //region INSTANCE VARIABLES
    // 0 - name, 1 - id, 2 - description, 3 - type, 4 - quantity, 5 - invoiceP, 6 - sellingP, 7 - sellerID
    private ArrayList<String> _productInfo;
    // might want to spell out which textview is which instead of having it in an array
    private ArrayList<EditText> _productTextFields;
    private User _currentUser;
    private Button _editBtn;
    private Button _addCart;
    private Button _saveBtn;
    private Button _cancelBtn;
    private Button _removeBtn;
    private EditText _productName;
    private EditText _productDes;
    private EditText _productCost;
    private EditText _productPrice;
    private EditText _productQuant;
    private EditText _productType;

    private BuyerClerk bClerk = BuyerClerk.getInstance();
    private SellerClerk sClerk = SellerClerk.getInstance();
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductDetails.context = getApplicationContext();
        _currentUser = (User) getIntent().getSerializableExtra("User");
        _productInfo = (ArrayList<String>) getIntent().getSerializableExtra("Product");
        setupView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
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
        return ProductDetails.context;
    }

    /**
     * Private method used to identify what view to show.
     */
    private void setupView(){
        // set up which view to show
        if(_currentUser == User.BUYER){
            setContentView(R.layout.product_details_buyer);
        }
        else if(_currentUser == User.SELLER) {
            setContentView(R.layout.product_details_seller);
        }

        // set up button listeners first.
        setUpListeners();
    }

    /**
     * Private method used to setUp UI listeners based on User that logged in
     */
    private void setUpListeners(){

        if(_currentUser == User.BUYER){
            //LINK UI OBJECTS TO XML HERE
            _productName = (EditText)  findViewById(R.id.bProductName);
            _productDes = (EditText)  findViewById(R.id.bprodDescrp);
            _productPrice = (EditText)  findViewById(R.id.bprice);
            _productQuant = (EditText)  findViewById(R.id.bquantity);
            _productType = (EditText)  findViewById(R.id.productType);
            _addCart = (Button) findViewById(R.id.addToCart);

            _addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // add this item to cart and bring user to shopping cart screen
                    //add selected item to cart
                    BuyerClerk.getInstance().addToCart(_productInfo.get(1),_productInfo.get(7));
                    // and bring user to the shopping cart screen
                    BuyerClerk.getInstance().showShoppingCart();
                }
            });
        }
        else if(_currentUser == User.SELLER) {

            //LINK UI OBJECTS TO XML HERE
            _productName = (EditText)  findViewById(R.id.ProductName);
            _productDes = (EditText)  findViewById(R.id.prodDescrp);
            _productPrice = (EditText)  findViewById(R.id.price);
            _productQuant = (EditText)  findViewById(R.id.quantity);
            _productCost = (EditText)  findViewById(R.id.cost);
            _productType = (EditText)  findViewById(R.id.sProductType);
            _saveBtn = (Button) findViewById(R.id.SaveBtn);
            _editBtn = (Button) findViewById(R.id.editProd);
            _removeBtn = (Button) findViewById(R.id.removeProd);
            _cancelBtn = (Button) findViewById(R.id.cancelbtn);

            //set text for cost
            _productCost.setText(_productInfo.get(5));
            _productCost.setText(_productInfo.get(5));

            _productTextFields = new ArrayList<>();
            // setup each EditText item in array here
            _productTextFields.add(_productName);
            _productTextFields.add(_productDes);
            _productTextFields.add(_productPrice);
            _productTextFields.add(_productQuant);
            _productTextFields.add(_productCost);
            _productTextFields.add(_productType);

            //region change test listeners
            /*
            _productName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _productInfo.set(0, _productName.getText().toString());
                }

                @Override
                public void afterTextChanged(android.text.Editable s) {
                }
            });

            _productDes.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _productInfo.set(2,_productDes.getText().toString());

                }
                @Override
                public void afterTextChanged(android.text.Editable s) {}});

            _productPrice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _productInfo.set(6,_productPrice.getText().toString());

                }
                @Override
                public void afterTextChanged(android.text.Editable s) {}});

            _productQuant.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _productInfo.set(0,_productQuant.getText().toString());

                }
                @Override
                public void afterTextChanged(android.text.Editable s) {}});

            _productCost.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _productInfo.set(5,_productCost.getText().toString());

                }
                @Override
                public void afterTextChanged(android.text.Editable s) {}});

            _productType.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _productInfo.set(3,_productType.getText().toString());

                }
                @Override
                public void afterTextChanged(android.text.Editable s) {}});
                */
            //endregion

            //setting visibility of saveBtn to initially hide it
            //reference: http://stackoverflow.com/questions/6173400/how-to-programmatically-hide-a-button-in-android-sdk
            _saveBtn.setVisibility(View.GONE);
            _cancelBtn.setVisibility(View.GONE);

            _editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // hide the edit button and show the save button
                    _editBtn.setVisibility(View.GONE);
                    _removeBtn.setVisibility(View.GONE);
                    _saveBtn.setVisibility(View.VISIBLE);
                    _cancelBtn.setVisibility(View.VISIBLE);

                    // iterate through textfields and make them editable
                    for(EditText text : _productTextFields)
                    {
                        text.setEnabled(true);
                        text.setFocusableInTouchMode(true);
                        text.setBackgroundColor(Color.LTGRAY);

                    }
                }
            });
            _saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // hide the save button and show the edit button
                    _editBtn.setVisibility(View.VISIBLE);
                    _removeBtn.setVisibility(View.VISIBLE);
                    _saveBtn.setVisibility(View.GONE);
                    _cancelBtn.setVisibility(View.GONE);

                    // iterate through textfields and make them not editable
                    for(EditText text : _productTextFields)
                    {
                        text.setEnabled(false);
                        text.setFocusable(false);
                        text.setBackgroundColor(Color.TRANSPARENT);
                    }


                    //grab data from each text field and
                    _productInfo.set(3,_productType.getText().toString());
                    _productInfo.set(5,_productCost.getText().toString());
                    _productInfo.set(0,_productQuant.getText().toString());
                    _productInfo.set(6,_productPrice.getText().toString());
                    _productInfo.set(2,_productDes.getText().toString());
                    _productInfo.set(0, _productName.getText().toString());

                    // update correct product from seller's inventory.
                    sClerk.saveProductChanges(_productInfo);

                }
            });

            _cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // hide the save button and show the edit button
                    _editBtn.setVisibility(View.VISIBLE);
                    _removeBtn.setVisibility(View.VISIBLE);
                    _saveBtn.setVisibility(View.GONE);
                    _cancelBtn.setVisibility(View.GONE);

                    // iterate through textfields and make them editable
                    for(EditText text : _productTextFields)
                    {
                        text.setEnabled(false);
                        text.setFocusable(false);
                        text.setBackgroundColor(Color.TRANSPARENT);
                    }

                    //set product info on screen
                    _productName.setText(_productInfo.get(0));
                    _productDes.setText( _productInfo.get(2) );
                    _productType.setText(_productInfo.get(3) );
                    _productPrice.setText(_productInfo.get(6));
                    _productQuant.setText(_productInfo.get(4));
                    _productCost.setText(_productInfo.get(5));
                }
            });

            _removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //remove item
                    sClerk.removeProduct(_productInfo);

                    // bring user back to browselist and update the list
                    //todo: add toast to let user know that item was removed
                    sClerk.returnToBrowseList(getApplicationContext());
                }
            });

        }
        //set product info on screen
        //
        _productName.setText(_productInfo.get(0));
        _productDes.setText( _productInfo.get(2) );
        _productType.setText(_productInfo.get(3) );
        _productPrice.setText(_productInfo.get(6));
        _productQuant.setText(_productInfo.get(4));




    }

    /**
     * Function used to show user confirmationDialogBox when saving changes
     * @return if the users submitted a yes or no response to saving changes
     */
    @Override
    public boolean callConfirmDialogBox(){
        return false;
    }

    @Override
    public void makeTextEditable(View viewObject) {

    }

    @Override
    public void convertBack(View viewObject) {

    }
}
