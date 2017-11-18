package com.sschudakov.tables.expression_parsing;

import com.sschudakov.exceptions.IllegalTokenException;
import com.sschudakov.tables.expression_parsing.tokens.FinalToken;
import com.sschudakov.tables.expression_parsing.tokens.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 02.11.2017.
 */
public class LexicalAnalyzerTest {

    private static final String NUM_OF_TOKENS_TEST_EXPRESSION = "+-/*^><>=<=!=moddivmminmmaxminmax(,)";
    private static final int NUM_OF_TOKENS = 19;
    private static final String FULL_TOKENS_SET_TEST = "+-/*^><>=<=!=moddivmminmmaxminmax(,)";
    private static final String RESTRICTED_TOKENS_SET_TEST = "+-/*><>=<=!=moddivmminmmax(,)";


    private static final String EXPONENT_TOKEN_TEST = "^";
    private static final String MAX_TOKEN_TEST = "min";
    private static final String MIN_TOKEN_TEST = "max";


    @Test(timeout = 100)

    public void allTokensAreReadCorrectTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(NUM_OF_TOKENS_TEST_EXPRESSION),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }
    }

    @Test(timeout = 100)
    public void numOfTokensTest() {

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(NUM_OF_TOKENS_TEST_EXPRESSION),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        List<Token> tokens = new ArrayList<>();
        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            tokens.add(currentToken);
            currentToken = lexicalAnalyzer.readToken();
        }
        Assert.assertEquals(NUM_OF_TOKENS, tokens.size());
    }

    @Test(timeout = 100)
    public void fullOperationsSetTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(FULL_TOKENS_SET_TEST),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }
    }

    @Test(timeout = 100)
    public void restrictedOperationsSetTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(RESTRICTED_TOKENS_SET_TEST),
                LexicalAnalyzerMode.RESTRICTION_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }
    }

    @Test(timeout = 100)
    public void exponentIsNotSupportedTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(EXPONENT_TOKEN_TEST),
                LexicalAnalyzerMode.RESTRICTION_OPERATIONS_SET);

        try {
            Token token = lexicalAnalyzer.readToken();
            throw new AssertionError("exponent should not be supported in restriction operations set mode");
        } catch (IllegalTokenException e) {
            /*NOP*/
        }
    }

    @Test(timeout = 100)
    public void manIsNotSupportedTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(MAX_TOKEN_TEST),
                LexicalAnalyzerMode.RESTRICTION_OPERATIONS_SET);

        try {
            Token token = lexicalAnalyzer.readToken();
            throw new AssertionError("min operation should not be supported in restriction operations set mode");
        } catch (IllegalTokenException e) {
            /*NOP*/
        }
    }

    @Test(timeout = 100)
    public void minIsNotSupportedTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(MIN_TOKEN_TEST),
                LexicalAnalyzerMode.RESTRICTION_OPERATIONS_SET);

        try {
            Token token = lexicalAnalyzer.readToken();
            throw new AssertionError("min operation should not be supported in restriction operations set mode");
        } catch (IllegalTokenException e) {
            /*NOP*/
        }
    }

}
