package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
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
import com.OOD.malissa.shoopingcart.Activities.Interfaces.Editable;
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

public class Account extends Activity implements Editable {

    //region INSTANCE VARIABLES
    private ArrayList<EditText> _infoTextList = new ArrayList<>();
    private Button _returnBtn;
    private Button _editBtn;
    private Button _cancelBtn;
    private Button _saveBtn;
    private EditText _userName;
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

        _infoTextList.add(_userName);
        _infoTextList.add(_password);
        _infoTextList.add(_cPassword);

        _userName.setText(_accountInfo.get(0));
        _password.setText(_accountInfo.get(1));
        _cPassword.setText(_accountInfo.get(1));

/*
        if(_currentUser == User.BUYER) {
            _userName.setText(StoreClerk.getInstance().get_userAccountB().getUsername());
            _password.setText(StoreClerk.getInstance().get_userAccountB().getPassword());
        }

        if(_currentUser == User.SELLER){
            _userName.setText(StoreClerk.getInstance().get_userAccountS().getUsername());
            _password.setText(StoreClerk.getInstance().get_userAccountS().getPassword());
        }*/

        _userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {

                    if(_userName.getText().toString().equals(_accountInfo.get(0))){
                        _uniqueUser = true;
                    }
                    else {
                        if (StoreClerk.getInstance().checkUsername(_userName.getText().toString(), _currentUser)) {
                            _uniqueUser = true;
                        } else {
                            Toast.makeText(getApplicationContext(), "Username has already been taken.",
                                    Toast.LENGTH_LONG).show();
                            _uniqueUser = false;
                        }
                    }
                }
            }
        });

        _password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {

                    if(_password.getText().toString().equals(_cPassword.getText().toString())){
                        _passwordMatch = true;
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password does not match.",
                                Toast.LENGTH_LONG).show();
                        _passwordMatch = false;

                    }
                }
            }
        });

        _cPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {

                    if(_password.getText().toString().equals(_cPassword.getText().toString())){
                        _passwordMatch = true;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password does not match.",
                                Toast.LENGTH_LONG).show();
                        _passwordMatch = false;
                    }
                }
            }
        });

        _returnBtn.setOnClickListener(new View.OnClickListener(){
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
            @Override
            public void onClick(View v) {
                // hide the edit button and show the save button
                _returnBtn.setVisibility(View.GONE);
                _editBtn.setVisibility(View.GONE);
                _cancelBtn.setVisibility(View.VISIBLE);
                _saveBtn.setVisibility(View.VISIBLE);
                _cPassTitle.setVisibility(View.VISIBLE);
                _cPassword.setVisibility(View.VISIBLE);

                for (EditText text : _infoTextList) {
                    text.setEnabled(true);
                    text.setFocusableInTouchMode(true);
                    text.setBackgroundColor(Color.LTGRAY);
                }

            }
        });

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
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

            }
        });


        _saveBtn.setOnClickListener(new View.OnClickListener() {
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

            }
        });



        // set the user and password text??
        //_userName.setText();
        //_password.setText();

    }

    @Override
    public void makeTextEditable(View viewObject) {

    }

    @Override
    public void convertBack(View viewObject) {

    }

    @Override
    public boolean callConfirmDialogBox() {
        return false;
    }
}
