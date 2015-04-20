package com.OOD.malissa.shoopingcart.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;
import com.OOD.malissa.shoopingcart.Controllers.StoreClerk;
import com.OOD.malissa.shoopingcart.Models.Cart;
import com.OOD.malissa.shoopingcart.Models.CreditCard;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;
import java.util.Calendar;

import static android.widget.RadioGroup.OnCheckedChangeListener;

public class Payment extends Activity {

    //region INSTANCE VARIABLES
    private Cart _shoppingCart = new Cart();

    private ArrayList<String> _creditList;
    private RadioGroup _existingCCList;
    private CheckBox _addNewCard;
    private EditText _cCName;
    private EditText _cCNum;
    private TextView _expiration;
    private Spinner _spinMonth;
    ArrayAdapter<String> spinMonthAdapter;
    private Spinner _spinYear;
    ArrayAdapter<String> spinYearAdapter; // used to add years calculated by calculateSpinYear() function
    private CheckBox _saveCard;
    private Button _purchaseBtn;
    private Button _cancelBtn;
    private static Context context; // used to get the context of this activity. only use when onCreate of Activity has been called!

    private boolean _cardSelectedRadio = false;
    private boolean _cardSelectedAdd = false;
    private RadioButton[] creditCard;
    private String _cardHolderName;
    private String _cardAccountNum;
    private String _cardExpirationM;
    private String _cardExpirationY;
    private boolean _savingCard = false;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Payment.context = getApplicationContext();
        setContentView(R.layout.payment);

        getCreditCards();

        setUpListeners();
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
        return Payment.context;
    }

    public void getCreditCards(){
      _creditList = BuyerClerk.getInstance().getCreditInfo();
    }

    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        _existingCCList = (RadioGroup) findViewById(R.id.cc_select_list);

        for(int i = 0; i < _creditList.size(); i++) {
            creditCard = new RadioButton[_creditList.size()];
            creditCard[i] = new RadioButton(this.getAppContext());
            creditCard[i].setText("Card Ending in "
                    + _creditList.get(i).substring(_creditList.get(i).length()-4));
            creditCard[i].setTextColor(0xff282828);
            creditCard[i].setId(i + 100);
            _existingCCList.addView(creditCard[i]);
        }
        _existingCCList.clearCheck();

        _addNewCard = (CheckBox)findViewById(R.id.add_cc_check);

        _cCName = (EditText)findViewById(R.id.cardNameText);
        _cCNum = (EditText)findViewById(R.id.cardNumberText);

        _expiration = (TextView) findViewById(R.id.expiration_title);
        _spinMonth =(Spinner)findViewById(R.id.month_spin);
        String[] months = {"01","02","03","04","05","06",
                "07","08","09","10","11","12"};
        spinMonthAdapter = new ArrayAdapter<String>(this.getAppContext(),
                R.layout.custom_spinner_item, months);
        spinMonthAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        _spinMonth.setAdapter(spinMonthAdapter);

        _spinYear =(Spinner)findViewById(R.id.year_spin);
        spinYearAdapter = new ArrayAdapter<String>(this.getAppContext(),
                R.layout.custom_spinner_item, calculateSpinYear());
        spinYearAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        _spinYear.setAdapter(spinYearAdapter);

        _saveCard = (CheckBox) findViewById(R.id.saveCheck);
        _cancelBtn = (Button)findViewById(R.id.cancelButton);
        _purchaseBtn = (Button)findViewById(R.id.purchaseButton);

        //Set Listeners here.
        _existingCCList.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                _cardSelectedRadio = true;
            }
        });

        _addNewCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    _cardSelectedAdd = true;

                    _cCName.setVisibility(View.VISIBLE);
                    _cCNum.setVisibility(View.VISIBLE);
                    _expiration.setVisibility(View.VISIBLE);
                    _spinMonth.setVisibility(View.VISIBLE);
                    _spinYear.setVisibility(View.VISIBLE);
                    _saveCard.setVisibility(View.VISIBLE);
                }
                else {
                    _cardSelectedAdd = false;

                    _cCName.setVisibility(View.GONE);
                    _cCNum.setVisibility(View.GONE);
                    _expiration.setVisibility(View.GONE);
                    _spinMonth.setVisibility(View.GONE);
                    _spinYear.setVisibility(View.GONE);
                    _saveCard.setVisibility(View.GONE);
                }
            }

        });

        _cCName.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {
                   _cardHolderName = _cCName.getText().toString();
               }

               @Override
               public void afterTextChanged(Editable s) {

               }
           }

        );

        _cCNum.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

              }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {
                  _cardAccountNum = _cCNum.getText().toString();
              }

              @Override
              public void afterTextChanged(Editable s) {

              }
          }

        );


        _spinMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _cardExpirationM = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });

        _spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _cardExpirationY = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });

        _saveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()){
                    _savingCard = true;
                }
                else {
                    _savingCard = false;
                }
            }
        });

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        _purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (_cardSelectedRadio || _cardSelectedAdd) {
                    if(_cardSelectedAdd) {

                        if(_cardAccountNum == null
                                || _cardHolderName == null
                                || _cardExpirationM == null
                                || _cardExpirationY == null) {
                            Toast.makeText(getApplicationContext(), "Please fill all fields correctly.",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        if(_cardAccountNum.length() != 16) {
                            Toast.makeText(getApplicationContext(), "Please make sure Credit Card Number is 16 digits.",
                                    Toast.LENGTH_LONG).show();
                            return;
                        }

                        if (_savingCard) {
                            String expiration = _cardExpirationM + "/" + _cardExpirationY;
                            BuyerClerk.getInstance().addNewCredit(_cardAccountNum, expiration);
                        }
                    }
                        BuyerClerk.getInstance().finalCheckout();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Card Selected.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public String[] calculateSpinYear(){
        String[] years = new String[15];
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 15; i++) {
            years[i] = year.toString();
            year = year + 1;
        }

        return years;
    }

}

