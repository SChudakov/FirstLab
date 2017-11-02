package com.sschudakov.expression_parsing;

import com.sschudakov.tables.expression_parsing.Expression;
import com.sschudakov.tables.expression_parsing.LexicalAnalyzer;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 02.11.2017.
 */
public class LexicalAnalyzerTest {
    @Test
    public void getTokenTest() {

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(
                new Expression("1+1-2*3/3^542*A3*(1+1)"));
        for (int i = 0; i < 20; i++) {
            System.out.println("\n");
//            System.out.println("last token: " + lexicalAnalyzer.getLastToken());
//            System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
            System.out.println(lexicalAnalyzer.getToken().toString());
            System.out.println("\n");
        }

        lexicalAnalyzer.giveBackToken();
        System.out.println("\n");
        System.out.println("last token: " + lexicalAnalyzer.getLastToken());
        System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
        System.out.println("\n");

        System.out.println("\n");
        System.out.println(lexicalAnalyzer.getToken().toString());
        System.out.println("last token: " + lexicalAnalyzer.getLastToken());
        System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
        System.out.println("\n");
    }

}
