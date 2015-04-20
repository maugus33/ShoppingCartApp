package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;


public class AddProduct extends Activity {

    //region INSTANCE VARIABLES
    // 0 - name, 1 - description, 2 - type, 3 - quantity, 4 - invoiceP, 5 - sellingP
    private ArrayList<String> _productInfo;
    private ArrayList<EditText> _productTextFields;
    private EditText _prodName;
    private EditText _prodDesc;
    private EditText _prodIPrice;
    private EditText _prodSPrice;
    private EditText _prodQuant;
    private EditText _prodType;
    private Button _cancelBtn;
    private Button _saveBtn;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    private SellerClerk sClerk = SellerClerk.getInstance();
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AddProduct.context = getApplicationContext();

        // initialize product info array and fill it with strings
        _productInfo = new ArrayList<>();
        _productTextFields = new ArrayList<>();

        setupView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
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

    /**
     * Function used to get the application's context. Only use if the application exists!
     * @return The context of this activity
     */
    public static Context getAppContext() {
        return AddProduct.context;
    }

    private void setupView(){

        setContentView(R.layout.add_product);
        setupListeners();
    }

    private void setupListeners(){

        //LINK UI OBJECTS TO XML HERE
        _prodName = (EditText)findViewById(R.id.addName);
         _prodDesc = (EditText)findViewById(R.id.addDeets);
         _prodIPrice = (EditText)findViewById(R.id.addcost);
         _prodSPrice = (EditText)findViewById(R.id.addprice);
        _prodQuant =(EditText)findViewById(R.id.addquant);
         _prodType =(EditText)findViewById(R.id.addType);

        // setup each EditText item in array here
        _productTextFields.add(_prodName);
        _productTextFields.add(_prodDesc);
        _productTextFields.add(_prodSPrice);
        _productTextFields.add(_prodQuant);
        _productTextFields.add(_prodIPrice);
        _productTextFields.add(_prodType);

        _cancelBtn = (Button) findViewById(R.id.addCancelbtn);
        _saveBtn = (Button) findViewById(R.id.addProdBtn);



        //region change test listeners
        //productInfo organization: 0 - name, 1 - description, 2 - type, 3 - quantity, 4 - invoiceP, 5 - sellingP
        /*_prodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _productInfo.set(0, _prodName.getText().toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });

        _prodDesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _productInfo.set(1, _prodDesc.getText().toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });

        _prodIPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _productInfo.set(4, _prodIPrice.getText().toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });

        _prodSPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _productInfo.set(5, _prodSPrice.getText().toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });

        _prodQuant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _productInfo.set(3, _prodQuant.getText().toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });

        _prodType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _productInfo.set(2, _prodType.getText().toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });
*/
        //endregion


        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send user back to browselist
                Intent i = new Intent(getApplicationContext(), BrowseList.class);
                i.putExtra("User", sClerk.currentUserType() );
                sClerk.goToActivity(getApplicationContext(),i);
            }
        });

        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check to make sure that all fields are not empty
                boolean canSave = true;
                for(EditText text : _productTextFields)
                {

                    if(text.getText().toString().equals(""))
                    {
                        // set flag and break out of loop
                        canSave = false;
                        break;
                    }
                }
                if(canSave) {
                    // update the productInfo stuff
                    _productInfo.add(_prodName.getText().toString());
                    _productInfo.add(_prodDesc.getText().toString());
                    _productInfo.add(_prodType.getText().toString());
                    _productInfo.add(_prodQuant.getText().toString());
                    _productInfo.add(_prodIPrice.getText().toString());
                    _productInfo.add(_prodSPrice.getText().toString());

                    //and send info to clerk to update model
                    sClerk.saveNewProduct(_productInfo);

                    // post toast
                    Toast.makeText(getAppContext(), "new Product added.",
                            Toast.LENGTH_LONG).show();

                }
                else
                {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Please fill all fields correctly.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
