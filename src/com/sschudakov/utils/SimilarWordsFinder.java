package com.sschudakov.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semen Chudakov on 24.09.2017.
 */
public class SimilarWordsFinder {

    private static int substringLength = 0;

    public static List<String> findSimilarWords(String line, String pattern) {

        String[] words = line.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }

        LinkedList<String> result = new LinkedList<>();

        int currentSubstringLength = 0;

        for (int i = 0; i < words.length; i++) {
            currentSubstringLength = matchingSubstringLength(words[i], pattern);
            if (currentSubstringLength == substringLength) {
                result.add(words[i]);
            }
            if (currentSubstringLength > substringLength) {
                substringLength = currentSubstringLength;
                result.clear();
                result.add(words[i]);
            }
        }
        return result;
    }

    private static int matchingSubstringLength(String word, String patter) {
        int result = 0;

        int i = 0;
        int j = 0;
        for (; i < word.length() && j < patter.length(); i++, j++) {
            if (word.charAt(i) == patter.charAt(j)) {
                result++;
            }else {
                break;
            }
        }

        return result;
    }

    private static String formWord(String line, int from) {

        int resultLength = 0;
        while (line.charAt(from + resultLength) != ' ' && line.charAt(from + resultLength) != '\r'
                && line.charAt(from + resultLength) != '\n') {
            resultLength++;
        }
        return line.substring(from, from + resultLength);
    }

}
