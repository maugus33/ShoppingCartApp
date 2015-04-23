package com.OOD.malissa.shoopingcart.Activities.HelperClasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class FinancialDialog extends DialogFragment
{
    /**
     * The default constructor.
     */
    public FinancialDialog()
    {

    }

    /**
     * Creates a Dialog box used to display the Financial Summary of the Seller.
     * @param savedInstanceState
     * @return a Dialog which has the Financial Summary
     * @author Malissa Augustin
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        String title = args.getString("title", "");
        String message = args.getString("message", "");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create();
    }
}
