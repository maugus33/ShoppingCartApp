package com.OOD.malissa.shoopingcart.Controllers;


import com.OOD.malissa.shoopingcart.Models.Product;

/**
 * Created by Malissa on 3/29/2015.
 */
public class SellerClerk extends StoreClerk {
    //region SINGLETON SETUP
    private static SellerClerk ourInstance = new SellerClerk();

    public static SellerClerk getInstance() {
        return ourInstance;
    }

    private SellerClerk() {
        super();
    }
    //endregion

    public void removeProduct(Product item){

    }

    public void addProduct(){

    }

    public void getFinance(){

    }

    public void saveProductChanges(Product item){

    }

    /**
     * used to assign id to new products
     */
    private String assignID(){
        return null;
    }

    public Product getInventoryProd(){
        return null;
    }


}
