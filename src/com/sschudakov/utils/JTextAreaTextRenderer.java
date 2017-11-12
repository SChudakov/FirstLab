package com.sschudakov.utils;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class JTextAreaTextRenderer {

    private static JTextArea textArea;

    public static void setTextArea(JTextArea textArea) {
        JTextAreaTextRenderer.textArea = textArea;
    }

    public static void renderText(String text) {
        JTextAreaCleaner.cleanJTextArea(textArea);
        textArea.setText(text);
    }
}
