package com.sschudakov.tables.utils;

import java.util.Arrays;

/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class ToDimensionalArrayOutputer {

    public static void ouputArray(Object[][] array) {
        for (Object[] objects : array) {
            System.out.println(Arrays.toString(objects));
        }
    }
}
