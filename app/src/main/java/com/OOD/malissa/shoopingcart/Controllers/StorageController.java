package com.OOD.malissa.shoopingcart.Controllers;

import android.content.Context;

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
     *
     * @param context
     * @param key
     * @param object
     * @throws IOException
     */
    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    /**
     *
     * @param context
     * @param key
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }
}
