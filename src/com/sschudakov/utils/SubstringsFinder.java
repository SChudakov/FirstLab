package com.sschudakov.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semen Chudakov on 24.09.2017.
 */
public class SubstringsFinder {

    public static List<Substring> findSubstrings(String line, String pattern) {

        LinkedList<Substring> result = new LinkedList<>();

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == pattern.charAt(0)) {
                if (matchesSubstringOf(line, i, pattern)) {
                    result.add(new Substring(i, i + pattern.length()));
                }
            }
        }


        return result;
    }

    private static boolean matchesSubstringOf(String line, int from, String patter) {
        if(from + patter.length()  > line.length()){
            return false;
        }
        return line.substring(from, from + patter.length()).equals(patter);
    }
}
