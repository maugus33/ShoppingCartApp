package com.OOD.malissa.shoopingcart.Models.Interfaces;

import java.io.Serializable;

/**
 * Created by Malissa on 4/19/2015.
 * Implements the strategy pattern. used to identify which alogorithm to use to get an id
 */
public interface IDAlgorithm extends Serializable {
    //key is a string the algorithm can use as a base for the calculations
    public String calculate(String key);
}
