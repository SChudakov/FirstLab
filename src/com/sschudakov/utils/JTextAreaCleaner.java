package com.sschudakov.utils;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 28.09.2017.
 */
public class JTextAreaCleaner {

    private static final String EMTY_STRING = "";

    public static void cleanJTextArea(JTextArea area){
        if(area != null){
            area.setText(EMTY_STRING);
        }
    }

}
