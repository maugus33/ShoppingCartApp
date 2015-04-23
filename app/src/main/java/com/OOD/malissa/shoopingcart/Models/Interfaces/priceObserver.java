package com.OOD.malissa.shoopingcart.Models.Interfaces;

/**
 * Observer used to observe any changes to inventory/price changes
 */
public interface priceObserver {
    public void update(double sellingPrice, double cost);
}
