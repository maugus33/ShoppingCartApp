package com.OOD.malissa.shoopingcart.Controllers;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Malissa on 3/29/2015.
 * reference: https://androidresearch.wordpress.com/2013/04/07/caching-objects-in-android-internal-storage/
 */
public final class StorageController {

    private StorageController() {
    }


    /**
     *Used to write objects to Internal Storage.
     * @param context (not used)
     * @param key the file to write to
     * @param object the object to write
     * @throws IOException
     */
    public static void writeObject(Context context, File key, Object object) throws IOException {

        FileOutputStream fileout = new FileOutputStream(key);
        ObjectOutputStream oos = new ObjectOutputStream(fileout);
        oos.writeObject(object);
        oos.close();
        fileout.close();
    }

    /**
     * Used to read objects from Internal Storage.
     * @param context (not used)
     * @param key the file to read from
     * @return an Object class that is read from the file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObject(Context context, File key) throws IOException,
            ClassNotFoundException {
        //FileInputStream fis = context.openFileInput(key);
        FileInputStream fis = new FileInputStream(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
}
