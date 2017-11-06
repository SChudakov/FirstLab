package com.sschudakov.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semen Chudakov on 06.11.2017.
 */
public class MeshNameParser {

    private static final String REGULAR_EXPRESSION_FOR_PARSING_COLUMN = "[A-Z]+";
    private static final String REGULAR_EXPRESSION_FOR_PARSING_ROW = "[0-9]+";
    private static final int NUM_OF_LETTERS = 26;

    public static int parseRow(String meshName) {
        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_FOR_PARSING_ROW);
        Matcher matcher = pattern.matcher(meshName);

        String row;

        if (matcher.find()) {
            row = matcher.group();
            return Integer.valueOf(row);
        } else {
            throw new IllegalArgumentException("mesh name " + meshName + " contains no row");
        }
    }

    public static int parseColumn(String meshName) {
        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_FOR_PARSING_COLUMN);
        Matcher matcher = pattern.matcher(meshName);

        String column;

        if (matcher.find()) {
            column = matcher.group();
            System.out.println("parsed column: " + column);
            return convertToInt(column);
        } else {
            throw new IllegalArgumentException("mesh name " + meshName + " contains no row");
        }
    }

    private static int convertToInt(String column) {

        int result = 0;

        for (int i = 0; i < column.length(); i++) {
            result += (((int) column.charAt(i)) - 64) * Math.pow(NUM_OF_LETTERS, i);
        }
        return result - 1;
    }
}
