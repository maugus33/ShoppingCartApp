package com.OOD.malissa.shoopingcart.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.OOD.malissa.shoopingcart.Activities.HelperClasses.User;
import com.OOD.malissa.shoopingcart.Controllers.BuyerClerk;
import com.OOD.malissa.shoopingcart.Models.Product;
import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;

/**
 * This is an Adapter that is used to fill the ListView in the BrowseList
 * with the products and buttons. This implements the Strategy pattern.
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

    /**
     * Creates the view to be placed in the listview for display on the screen.
     * @param position the int that represents the position of the view in the ListView
     * @param convertView
     * @param parent
     * @return
     * @author Malissa Augustin
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView ==null)
        {
            //Set up the specific list of items for BUYER or SELLER
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

        //Set up the listeners of the list based on BUYER or SELLER
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
