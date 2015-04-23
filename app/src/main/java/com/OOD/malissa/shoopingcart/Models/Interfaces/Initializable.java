package com.OOD.malissa.shoopingcart.Models.Interfaces;

/**
 * Interface for classes that need to have their data initialized by the storage controller
 */
public interface Initializable {
    void initialized(Object object, String key);
}
