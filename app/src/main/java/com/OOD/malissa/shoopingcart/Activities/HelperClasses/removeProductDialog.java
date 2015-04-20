package com.OOD.malissa.shoopingcart.Activities.HelperClasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.OOD.malissa.shoopingcart.Activities.BrowseList;
import com.OOD.malissa.shoopingcart.Controllers.SellerClerk;

import java.util.ArrayList;

/**
 * Created by Malissa on 4/20/2015.
 */
public class removeProductDialog extends DialogFragment
{
    public removeProductDialog()
    {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        final ArrayList<String>  _productInfo = args.getStringArrayList("product");
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SellerClerk.getInstance().removeProduct(_productInfo);

                        // post toast
                        Toast.makeText(BrowseList.getAppContext(), "Product was removed",
                                Toast.LENGTH_LONG).show();

                        // bring user back to browselist and update the list
                        //todo: add toast to let user know that item was removed
                        Intent i = new Intent(BrowseList.getAppContext(), BrowseList.class);
                        i.putExtra("User", SellerClerk.getInstance().currentUserType());
                        SellerClerk.getInstance().goToActivity(BrowseList.getAppContext(), i);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create();
    }
}
