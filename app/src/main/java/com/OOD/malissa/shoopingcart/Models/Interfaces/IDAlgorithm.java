package com.OOD.malissa.shoopingcart.Models.Interfaces;

import java.io.Serializable;

/**
 * Implements the strategy pattern. used to identify which alogorithm to use to get an id
 */
public interface IDAlgorithm extends Serializable {
    //key is a string the algorithm can use as a base for the calculations
    public String calculate(String key);
}
