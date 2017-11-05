package com.sschudakov.tables.expression_parsing;

import org.junit.Test;

/**
 * Created by Semen Chudakov on 02.11.2017.
 */
public class LexicalAnalyzerTest {
    @Test
    public void getTokenLastCurrentTokenTest() {

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression("1+1<>!=<+>+>=<=="));

        for (int i = 0; i < 15; i++) {
            System.out.println("\n");
            System.out.println("read token: " + lexicalAnalyzer.getToken().toString());
            System.out.println("last token: " + lexicalAnalyzer.getLastToken());
            System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
            System.out.println("\n");
        }
    }

    @Test
    public void giveBackTokenTest() {

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression("1+1"));

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                System.out.println("\n token is given back\n");
                lexicalAnalyzer.giveBackToken();
                continue;
            }
            System.out.println("\n");
            System.out.println("read token: " + lexicalAnalyzer.getToken().toString());
            System.out.println("last token: " + lexicalAnalyzer.getLastToken());
            System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
            System.out.println("\n");
        }

    }

    @Test
    public void getTokenFinalTokenTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression("1+1"));
//        for (int i = 0; i < 3; i++) {
//            System.out.println("\n");
//            System.out.println("read token: " + lexicalAnalyzer.getToken().toString());
//            System.out.println("last token: " + lexicalAnalyzer.getLastToken());
//            System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
//            System.out.println("\n");
//        }
        System.out.println("\n");
        System.out.println("read token: " + lexicalAnalyzer.getToken().toString());
        System.out.println("last token: " + lexicalAnalyzer.getLastToken());
        System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("read token: " + lexicalAnalyzer.getToken().toString());
        System.out.println("last token: " + lexicalAnalyzer.getLastToken());
        System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("read token: " + lexicalAnalyzer.getToken().toString());
        System.out.println("last token: " + lexicalAnalyzer.getLastToken());
        System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("read token: " + lexicalAnalyzer.getToken().toString());
        System.out.println("last token: " + lexicalAnalyzer.getLastToken());
        System.out.println("current token: " + lexicalAnalyzer.getCurrentToken());
        System.out.println("\n");

        Token finalToken = lexicalAnalyzer.getToken();
        System.out.println("final token: " + finalToken);
//        Assert.assertEquals(Token.getFinalToken(), finalToken);
    }


}
