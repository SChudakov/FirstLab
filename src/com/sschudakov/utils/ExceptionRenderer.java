package com.sschudakov.utils;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Semen Chudakov on 16.09.2017.
 */
public class ExceptionRenderer {
    public static void renderException(Component parentComponent, Exception exception) {
        JOptionPane.showMessageDialog(parentComponent, exception.getMessage());
    }
}
