package com.sschudakov.untils;

import com.sschudakov.utils.SimilarWordsFinder;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 09.10.2017.
 */
public class SimilarWordsFinderTest {
    @Test
    public void findSimilarWords() {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File("D:\\Workspace.java\\FirstLab\\test_operations\\gutenberg.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = scanner.useDelimiter("\\Z").next();
        String pattern = "onl";

        List result = SimilarWordsFinder.findSimilarWords(line, pattern);

        System.out.println("result:" + result.toString());
    }

    @Test
    public void matchingSubstringLengthTest() {

        String word = "Fo";
        String pattern = "Foundation";

        Assert.assertEquals(4, SimilarWordsFinder.matchingSubstringLength(word, pattern));
    }

}
