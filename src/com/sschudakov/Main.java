package com.sschudakov;

import com.sschudakov.gui.GUIManager;
import com.sschudakov.tables.expression_parsing.tokens.FinalToken;

public class Main {

    public static void main(String[] args) {

        GUIManager manager = new GUIManager();
        manager.buildGUI();
    }

    private static void printCharacters() {
        for (int i = 0; i < 256; i++) {
            System.out.println(i + " " + (char) i);
        }
    }
}
