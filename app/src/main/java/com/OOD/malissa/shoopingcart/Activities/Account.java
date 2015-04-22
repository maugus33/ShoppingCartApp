package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class Account extends Activity {

    //region INSTANCE VARIABLES
    private ArrayList<EditText> _infoTextList = new ArrayList<>();
    private Button _returnBtn;
    private Button _editBtn;
    private Button _cancelBtn;
    private Button _saveBtn;
    private EditText _userName;
    private TextView _bill;
    private EditText _password;
    private TextView _cPassTitle;
    private EditText _cPassword;
    private boolean _uniqueUser = true;
    private boolean _passwordMatch = true;
    private User _currentUser;
    private ArrayList<String> _accountInfo;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Account.context = getApplicationContext();
        _currentUser = (User) getIntent().getSerializableExtra("User");
        _accountInfo = (ArrayList<String>) getIntent().getSerializableExtra("Account");

        setContentView(R.layout.account);
        setUpListeners();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
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
        return Account.context;
    }

    /**
     * Private method used to setUp UI listeners based on User that logged in
     */
    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        _returnBtn = (Button)  findViewById(R.id.accountReturn);
        _editBtn = (Button)  findViewById(R.id.accountEdit);
        _cancelBtn = (Button)  findViewById(R.id.accountCancel);
        _saveBtn = (Button)  findViewById(R.id.accountSave);
        _userName = (EditText) findViewById(R.id.accountUname);
        _password = (EditText) findViewById(R.id.accountPword);
        _cPassTitle = (TextView) findViewById(R.id.accountCPwordTitle);
        _cPassword = (EditText) findViewById(R.id.accountCPword);
        _bill = (TextView) findViewById(R.id.billText);
        if(_currentUser == User.BUYER) {
            _bill.setText("Bill: " + BuyerClerk.getInstance().getBuyerBill());
        }
        else
        {

            _bill.setVisibility(View.GONE);
        }



        _infoTextList.add(_userName);
        _infoTextList.add(_password);
        _infoTextList.add(_cPassword);

        _userName.setText(_accountInfo.get(0));
        _password.setText(_accountInfo.get(1));
        _cPassword.setText(_accountInfo.get(1));


        //Set up the object Listeners here.
        _userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            //After losing focus, check if the Username is already taken
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {
                    //if the username is the current username, set _uniqueUser boolean true
                    if(_userName.getText().toString().equals(_accountInfo.get(0))){
                        _uniqueUser = true;
                    }
                    else {
                        //if the username is unique, set _uniqueUser boolean true
                        if (StoreClerk.getInstance().checkUsername(_userName.getText().toString(), _currentUser)) {
                            _uniqueUser = true;
                        }
                        //if the username is taken, set _uniqueUser boolean false and show toast message
                        else {
                            Toast.makeText(getApplicationContext(), "Username has already been taken.",
                                    Toast.LENGTH_LONG).show();
                            _uniqueUser = false;
                        }
                    }
                }
            }
        });

        _password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //When the password field's focus is lost, check if confirm password is the same.
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {
                    //If the password and confirm password are the same, set _passwordMatch true.
                    if(_password.getText().toString().equals(_cPassword.getText().toString())){
                        _passwordMatch = true;
                    }
                    //If the password and confirm password are not the same, set _passwordMatch false
                    // and show a toast message.
                    else{
                        Toast.makeText(getApplicationContext(), "Password does not match.",
                                Toast.LENGTH_LONG).show();
                        _passwordMatch = false;

                    }
                }
            }
        });

        _cPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //When the confirm password field's focus is lost, check if password is the same.
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {
                    //If the password and confirm password are the same, set _passwordMatch true.
                    if(_password.getText().toString().equals(_cPassword.getText().toString())){
                        _passwordMatch = true;
                    }
                    //If the password and confirm password are not the same, set _passwordMatch false
                    // and show a toast message.
                    else {
                        Toast.makeText(getApplicationContext(), "Password does not match.",
                                Toast.LENGTH_LONG).show();
                        _passwordMatch = false;
                    }
                }
            }
        });

        _returnBtn.setOnClickListener(new View.OnClickListener(){
            //Returns the user to the BrowseList
            @Override
            public void onClick(View v) {
                if(_currentUser == User.BUYER) {
                    finish();
                    BuyerClerk.getInstance().openBrowseList(getAppContext());
                }
                if(_currentUser == User.SELLER) {
                    finish();
                    SellerClerk.getInstance().returnToBrowseList(getAppContext());
                }

            }
        });

        _editBtn.setOnClickListener(new View.OnClickListener() {
            //Sets the screen to be able to edit the account information
            @Override
            public void onClick(View v) {
                // hide the edit button and show the save button
                _returnBtn.setVisibility(View.GONE);
                _editBtn.setVisibility(View.GONE);
                _cancelBtn.setVisibility(View.VISIBLE);
                _saveBtn.setVisibility(View.VISIBLE);
                _cPassTitle.setVisibility(View.VISIBLE);
                _cPassword.setVisibility(View.VISIBLE);

                //Make the text fields editable
                for (EditText text : _infoTextList) {
                    text.setEnabled(true);
                    text.setFocusableInTouchMode(true);
                    text.setBackgroundColor(Color.LTGRAY);
                }
                if(_currentUser == User.BUYER) {_bill.setVisibility(View.INVISIBLE);}

            }
        });

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            //Returns the screen to just show the account information
            @Override
            public void onClick(View v) {
                _returnBtn.setVisibility(View.VISIBLE);
                _editBtn.setVisibility(View.VISIBLE);
                _cancelBtn.setVisibility(View.GONE);
                _saveBtn.setVisibility(View.GONE);
                _cPassTitle.setVisibility(View.GONE);
                _cPassword.setVisibility(View.GONE);


                for (EditText text : _infoTextList) {
                    text.setEnabled(false);
                    text.setFocusableInTouchMode(false);
                    text.setBackgroundColor(Color.TRANSPARENT);
                }

                _userName.setText(_accountInfo.get(0));
                _password.setText(_accountInfo.get(1));
                _cPassword.setText(_accountInfo.get(1));
                if(_currentUser == User.BUYER) {_bill.setVisibility(View.VISIBLE);}

            }
        });


        _saveBtn.setOnClickListener(new View.OnClickListener() {
            //Takes the data from the edit fields and saves only if the username is unique and
            //the password and confirm password are the same. Then returns screen to show account info.
            @Override
            public void onClick(View v) {

                if(_passwordMatch && _uniqueUser) {

                    _returnBtn.setVisibility(View.VISIBLE);
                    _editBtn.setVisibility(View.VISIBLE);
                    _cancelBtn.setVisibility(View.GONE);
                    _saveBtn.setVisibility(View.GONE);
                    _cPassTitle.setVisibility(View.GONE);
                    _cPassword.setVisibility(View.GONE);

                    for (EditText text : _infoTextList) {
                        text.setEnabled(false);
                        text.setFocusableInTouchMode(false);
                        text.setBackgroundColor(Color.TRANSPARENT);
                    }

                    _accountInfo.set(0, _userName.getText().toString());
                    _accountInfo.set(1, _password.getText().toString());

                    StoreClerk.getInstance().updateAccount(_accountInfo, _currentUser);
                }

                //Show toasts for error handling.
                else if(_passwordMatch && !_uniqueUser) {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Username has already been taken.",
                            Toast.LENGTH_LONG).show();
                }

                else if(!_passwordMatch && _uniqueUser) {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Password does not match.",
                            Toast.LENGTH_LONG).show();
                }

                else if(!_passwordMatch && !_uniqueUser) {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Username has already been taken and Password does not match.",
                            Toast.LENGTH_LONG).show();
                }

                if(_currentUser == User.BUYER) {_bill.setVisibility(View.VISIBLE);}

            }
        });
    }

}
