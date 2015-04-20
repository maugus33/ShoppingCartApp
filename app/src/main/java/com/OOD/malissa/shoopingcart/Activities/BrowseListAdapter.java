package com.OOD.malissa.shoopingcart.Activities;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

/***
 * This is the
 * reference: http://stackoverflow.com/questions/20586319/set-button-onclick-event-for-every-row-of-listview
 */
public class BrowseListAdapter extends BaseAdapter
{
    LayoutInflater mInlfater;
    ArrayList<Product> list;
    User _cUser;

    public BrowseListAdapter(Context context, ArrayList<Product> list, User currentUser)
    {
        mInlfater = LayoutInflater.from(context);
        this.list =list;
        this._cUser = currentUser;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    // NOT IMPLEMENTED
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView ==null)
        {
            if (_cUser == User.BUYER) {
                convertView = mInlfater.inflate(R.layout.custom_list_buyer_items, parent, false);
                holder = new ViewHolder();
                holder._tv = (TextView) convertView.findViewById(R.id.product_name);
                holder._addCart = (Button) convertView.findViewById(R.id.add_cart);
            } else if (_cUser == User.SELLER) {
                convertView = mInlfater.inflate(R.layout.custom_list_seller_items, parent, false);
                holder = new ViewHolder();
                holder._tv = (TextView) convertView.findViewById(R.id.product_sname);
            }

            convertView.setTag(holder);

        }
        else
        {
            holder =(ViewHolder) convertView.getTag();
        }
        Product item = list.get(position);
       // set up the buttons here?

        if (_cUser == User.BUYER) {
            holder._tv.setText(item.get_name());
            holder._addCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //add selected item to cart
                    BuyerClerk.getInstance().addToCart(list.get(position));
                    // update the

                }
            });
        } else if (_cUser == User.SELLER) {
            //set the text for the item
            holder._tv.setText(item.get_name());


        }

        return convertView;
    }

    /**
     * holder Class to contain inflated xml file elements
     */
    static class ViewHolder
    {
        // add buttons and text views of each layout item here. see reference for details
        // should be from product_item.xml
         TextView _tv;
        Button _addCart;
    }

}
