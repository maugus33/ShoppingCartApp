package com.OOD.malissa.shoopingcart.Activities.Interfaces;

import android.view.View;

/**
 * Created by Malissa on 3/29/2015.
 * The Editable interface is used to make activity views editable
 */
public interface Editable {
    void makeTextEditable(View viewObject);
    void convertBack(View viewObject);
    boolean callConfirmDialogBox(); // confirmation box for saving confirmations
}
