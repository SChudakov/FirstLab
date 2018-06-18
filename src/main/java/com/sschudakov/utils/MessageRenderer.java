package com.sschudakov.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Semen Chudakov on 21.09.2017.
 */
public class MessageRenderer {
    public static void renderMessage(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }
}
