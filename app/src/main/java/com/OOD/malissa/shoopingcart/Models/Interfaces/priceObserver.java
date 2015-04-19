package com.OOD.malissa.shoopingcart.Models.Interfaces;

/**
 * Created by Malissa on 4/19/2015.
 * Observer used to observe any changes to inventory/price changes
 */
public interface priceObserver {
    public void update(double sellingPrice, double cost);
}
