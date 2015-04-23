package com.OOD.malissa.shoopingcart.Models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class for the Products in store.
 * implements parcelable so product object can get its data for product details - implements Parcelable
 */
public class Product  implements Serializable {

    private String _name;
    private String _ID;
    private String _description;
    private String _type;
    private int _quantity;
    private double _invoiceP;
    private double _sellingP;
    private String _SellerID;
    // decimal format used to properly format the doubles
    private DecimalFormat df = new DecimalFormat("#.00");

    // default constructor
    public Product(){}
    // explicit value constructor
    public Product(String name, String id, String descrip, String type, int quantity,
                   double invoice, double selling, String sellerID){
        this._name = name;
        this._ID = id;
        this._description = descrip;
        this._type = type;
        this._quantity = quantity;
        this._invoiceP = invoice;
        this._sellingP = selling;
        this._SellerID = sellerID;
    }

    //region Accessors

    /**
     * Returns the _name of the Product.
     * @return a String that is the name of the Product
     */
    public String get_name() { return _name;}

    /**
     * An accessor for the Product's ID
     * @return a String that is the Product ID
     */
    public String get_ID() {return _ID; }

    /**
     * An accessor for the description of the product.
     * @return a String that is the Product description
     */
    public String get_description() {return _description;}

    /**
     * An accessor for the Product type.
     * @return a String that is the Product's type
     */
    public String get_type() {return _type;}

    /**
     * An accessor for the Product quantity
     * @return an int that is the quantity of the product
     */
    public int get_quantity() {return _quantity; }

    /**
     * An accessor for the Product invoice price.
     * @return a double that is the invoice price of the product
     */
    public double get_invoiceP() {return _invoiceP;}

    /**
     * An accessor for the Product selling price.
     * @return a double that is the selling price of the product
     */
    public double get_sellingP() {return _sellingP;}

    /**
     * An accessor for the seller ID of the Product.
     * @return a String that is the seller ID of the product
     */
    public String get_SellerID() {return _SellerID;}

    /**
     * A toString method for the Product which shows the product name and
     * selling price.
     * @return a String that has the Product name and selling price
     * @author Paul Benedict Reyes
     * @author Malissa Augustin
     */
    @Override
    public String toString() {return _name + " $" + df.format(_sellingP);}
    //endregion


    //region Mutators

    /**
     * Changes the _name of the Product.
     * @param _name  a String that is the name of the Product
     */
    public void set_name(String _name) {this._name = _name; }

    /**
     * A mutator for the Product's ID
     * @param _ID  a String that is the Product ID
     */
    public void set_ID(String _ID) {this._ID = _ID;}

    /**
     * A mutator for the description of the product.
     * @param _description a String that is the Product description
     */
    public void set_description(String _description) {this._description = _description;}

    /**
     * A mutator for the Product type.
     * @param _type a String that is the Product's type
     */
    public void set_type(String _type) {this._type = _type;}

    /**
     * A mutator for the Product quantity
     * @param _quantity an int that is the quantity of the product
     */
    public void set_quantity(int _quantity) {this._quantity = _quantity;}

    /**
     * A mutator for the Product invoice price.
     * @param _invoiceP  a double that is the invoice price of the product
     */
    public void set_invoiceP(double _invoiceP) {this._invoiceP = _invoiceP;}

    /**
     * A mutator for the Product selling price.
     * @param _sellingP  a double that is the selling price of the product
     */
    public void set_sellingP(double _sellingP) {this._sellingP = _sellingP;}

    /**
     * A mutator for the seller ID of the Product.
     * @param _SellerID  a String that is the seller ID of the product
     */
    public void set_SellerID(String _SellerID) {this._SellerID = _SellerID;}
    //endregion

    /**
     * A method used to compare two Product.
     * @param other a different Product to be compared with.
     * @return a boolean that determines whether the two Products have
     * the same id and seller id.
     * @author Paul Benedict Reyes
     */
    @Override
    public boolean equals(Object other) {

        if(this == other) return true;
        if(other == null) return false;
        if (getClass() != other.getClass()) return false;

        Product otherProd = (Product) other;

        //Changed so that only the product and seller id are compared 4/19/15
        return (this.get_ID().equals(otherProd.get_ID())
                        && this.get_SellerID().equals(otherProd.get_SellerID()));
    }

    /**
     * A method used to compare two Product names.
     * @param otherID a different Product's id to be compared with.
     * @param otherSellerID a different Product's id to be compared with.
     * @return a boolean that determines whether the two Products have
     * the same name.
     * @author Malissa Augustin
     */
    public boolean equals(String otherID,String otherSellerID) {

        if(otherID == null ||otherSellerID == null ) return false;

        return ( this.get_ID().equals(otherID)
                && this.get_SellerID().equals(otherSellerID));
    }


    /**
     * Turns product into an array of data.
     * @return an ArrayList of String that contains the information of the Product
     * @author Malissa Augustin
     */
    public ArrayList<String> toArrayList() {

        ArrayList<String> list = new ArrayList<>();
        list.add(this._name);
        list.add(this._ID);
        list.add(this._description);
        list.add(this._type);
        Integer quantity = this._quantity;
        list.add(quantity.toString());
        list.add(df.format(this._invoiceP));
        list.add(df.format(this._sellingP));
        list.add(this._SellerID);


        return list;}

    /**
     * Copies the data of a given item. everything but the productID and SellerID can be changed
     * @param item the Product to be copied
     * @author Malissa Augustin
     */
    public void copyData(Product item){

        this._sellingP = item.get_sellingP();
        this._invoiceP = item.get_invoiceP();
        this._description = item.get_description();
        this._name = item.get_name();
        this._quantity = item.get_quantity();
        this._type = item.get_type();

    }

}
