package com.OOD.malissa.shoopingcart.Models.Interfaces;

import java.util.Iterator;

/**
 * Created by Malissa on 4/3/2015.
 */
public interface NewIterable extends Iterable {

    @Override
    public NewIterator iterator();
}
