package com.sschudakov.untils;

import com.sschudakov.utils.SimilarWordsFinder;
import org.junit.Test;

import java.util.List;

/**
 * Created by Semen Chudakov on 09.10.2017.
 */
public class SimilarWordsFinderTest {
    @Test
    public void test_1(){

        String line = "aple applehgfhfhf\r";
        String pattern = "apple";

        List result = SimilarWordsFinder.findSimilarWords(line, pattern);

        System.out.println(result.toString());
    }

}
