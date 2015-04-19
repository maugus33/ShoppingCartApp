package com.OOD.malissa.shoopingcart.Models.Interfaces;

import android.content.Context;

import java.io.File;

/**
 * Created by Malissa on 4/18/2015.
 * Used one objects that can be saved to the internal storage
 */
public interface Saveable {
    void save( String key,File file,Context context);
}
