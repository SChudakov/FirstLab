package com.sschudakov.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Semen Chudakov on 25.09.2017.
 */
public class UserTextInput {
    public static String inputUserText(Component parentComponent, String text) {
        return JOptionPane.showInputDialog(parentComponent, "input");
    }
}
