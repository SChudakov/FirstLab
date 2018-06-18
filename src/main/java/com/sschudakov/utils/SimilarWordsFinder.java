package com.sschudakov.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semen Chudakov on 24.09.2017.
 */
public class SimilarWordsFinder {

    private static int substringLength = 1;

    public static List<String> findSimilarWords(String line, String pattern) {

        substringLength = 1;

        LinkedList<String> result = new LinkedList<>();

        String[] words = line.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }

        int currentSubstringLength;

        for (int i = 0; i < words.length; i++) {
            currentSubstringLength = matchingSubstringLength(words[i], pattern);
            if (currentSubstringLength == substringLength) {
                result.add(words[i]);
            }
            if (currentSubstringLength > substringLength) {
                result.clear();
                result.add(words[i]);
                substringLength = currentSubstringLength;
            }
        }
        return result;
    }

    public static int matchingSubstringLength(String word, String patter) {
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
}
