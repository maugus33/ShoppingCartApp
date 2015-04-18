package com.OOD.malissa.shoopingcart.Models;

/**
 * Created by Malissa on 3/29/2015.
 * Class for the Products in store.
 */
public class Product {

    // Note: in Android Studios, there is a way for the IDE to create getters/setters for you
    //by hovering over the name of the private variable and clicking on the light bulb that shows
    // up. It will give you the option to create setters/getters for it.
    private String _name;
    private String _ID;
    private String _description;
    private String _type;
    private int _quantity;
    private double _invoiceP;
    private double _sellingP;
    private String _SellerID;

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
    public String get_name() { return _name;}
    public String get_ID() {return _ID; }
    public String get_description() {return _description;}
    public String get_type() {return _type;}
    public int get_quantity() {return _quantity; }
    public double get_invoiceP() {return _invoiceP;}
    public double get_sellingP() {return _sellingP;}
    public String get_SellerID() {return _SellerID;}

    //Added a toString to print receipt easier. 4/17/15
    public String toString() {return _name + "\t\t\t\t" + _sellingP;}
    //endregion

    //region Mutators
    public void set_name(String _name) {this._name = _name; }
    public void set_ID(String _ID) {this._ID = _ID;}
    public void set_description(String _description) {this._description = _description;}
    public void set_type(String _type) {this._type = _type;}
    public void set_quantity(int _quantity) {this._quantity = _quantity;}
    public void set_invoiceP(double _invoiceP) {this._invoiceP = _invoiceP;}
    public void set_sellingP(double _sellingP) {this._sellingP = _sellingP;}
    public void set_SellerID(String _SellerID) {this._SellerID = _SellerID;}
    //endregion

    /**
     * A method used to compare two Product names.
     * @param other a different Product to be compared with.
     * @return a boolean that determines whether the two Products have
     * the same name.
     */
    @Override
    public boolean equals(Object other) {

        if(this == other) return true;
        if(other == null) return false;
        if (getClass() != other.getClass()) return false;

        Product otherProd = (Product) other;

        return (this.get_name().equals(otherProd.get_name())
                        && this.get_ID().equals(otherProd.get_ID())
                        && this.get_SellerID().equals(otherProd.get_SellerID()));
    }

}
