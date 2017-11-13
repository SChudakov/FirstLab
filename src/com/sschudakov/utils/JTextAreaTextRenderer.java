package com.sschudakov.utils;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class JTextAreaTextRenderer {
    public static void renderText(String text, JTextArea area) {
        JTextAreaCleaner.cleanJTextArea(area);
        area.setText(text);
    }
}
