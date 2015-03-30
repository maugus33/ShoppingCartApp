package com.OOD.malissa.shoopingcart.Models.Interfaces;

/**
 * Created by Malissa on 3/29/2015.
 * Interface for classes that need to have their data initialized by the storage controller
 */
public interface Initializable {
    void initialized(Object object, String key);
}
