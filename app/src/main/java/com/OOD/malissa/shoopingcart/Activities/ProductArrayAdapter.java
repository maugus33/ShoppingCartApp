package com.OOD.malissa.shoopingcart.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.OOD.malissa.shoopingcart.R;

import java.util.ArrayList;
import java.util.HashMap;

/***
 * This is the
 * reference: http://stackoverflow.com/questions/20586319/set-button-onclick-event-for-every-row-of-listview
 */
public class ProductArrayAdapter extends BaseAdapter
{
    LayoutInflater mInlfater;
    ArrayList<HashMap<String,String>> list;
    public ProductArrayAdapter(Context context,ArrayList<HashMap<String,String>> list)
    {
        mInlfater = LayoutInflater.from(context);
        this.list =list;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if(convertView ==null)
        {
            convertView = mInlfater.inflate(R.layout.browse_list_buyer,parent,false);


        }
        else
        {
            //holder =(ViewHolder) convertView.getTag();
        }
        HashMap<String,String> map = list.get(position);
       // set up the buttons here?
        return convertView;
    }

    static class ViewHolder
    {
        // add buttons and text views of each layout item here. see reference for details
        // should be from product_item.xml
    }

}
