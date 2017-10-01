package com.sschudakov.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Semen Chudakov on 28.09.2017.
 */
public class OptionConfirmer {
    public static int confirmOption(Component parentComponent, String option){
        return JOptionPane.showConfirmDialog(parentComponent, option);
    }
}
