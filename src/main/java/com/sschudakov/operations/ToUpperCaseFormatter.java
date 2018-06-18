package com.sschudakov.operations;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 03.10.2017.
 */
public class ToUpperCaseFormatter {
    public static void selectedTextToUpperCase(JTextArea area){
        if (!area.getSelectedText().equals("")) {
            area.replaceRange(area.getSelectedText().toUpperCase(), area.getSelectionStart(), area.getSelectionEnd());
        } else {
            throw new IllegalArgumentException("Nothing has been selected");
        }

    }

}
