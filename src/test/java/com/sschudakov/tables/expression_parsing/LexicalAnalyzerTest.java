package test.java.com.sschudakov.tables.expression_parsing;

import com.sschudakov.exceptions.IllegalTokenException;
import com.sschudakov.tables.expression_parsing.tokens.DefaultToken;
import com.sschudakov.tables.expression_parsing.tokens.FinalToken;
import com.sschudakov.tables.expression_parsing.tokens.Token;
import com.sschudakov.tables.expression_parsing.tokens.TokenType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 02.11.2017.
 */
public class LexicalAnalyzerTest {

    private static final String NUM_OF_TOKENS_TEST = "+-/*^><>=<=!=moddivmminmmaxminmax(,)";
    private static final int NUM_OF_TOKENS = 19;
    private static final String FULL_TOKENS_SET_TEST = "+-/*^><>=<=!=moddivmminmmaxminmax(,)";
    private static final String RESTRICTED_TOKENS_SET_TEST = "+-/*><>=<=!=moddivmminmmax(,)";
    private static final String TOKENS_SET_TEST = "+-/*";
    private static final String TOKENS_ARE_GIVEN_BACK_CORRECTLY_TEST = "+-/*";
    private static final String GIVE_BACK_THREE_TOKENS_TEST = "+-/*";
    private static final String CURRENT_TOKEN_WHILE_READING_TEST = "+";
    private static final String LAST_TOKEN_WHILE_READING_TEST = "+";
    private static final String CURRENT_TOKEN_WHILE_GIVING_BACK = "+-/*";
    private static final String LAST_TOKEN_WHILE_GIVING_BACK = "+-";


    private static final String EXPONENT_TOKEN_TEST = "^";
    private static final String MAX_TOKEN_TEST = "min";
    private static final String MIN_TOKEN_TEST = "max";


    @Test(timeout = 100)
    public void allTokensAreReadCorrectTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(NUM_OF_TOKENS_TEST),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }
    }

    @Test(timeout = 100)
    public void numOfTokensTest() {

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(NUM_OF_TOKENS_TEST),
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
    public void tokensSetTest() {
        ArrayList<Token> readTokens = new ArrayList<>();
        ArrayList<Token> expected = new ArrayList<>();
        expected.add(new DefaultToken("+", TokenType.PLUS));
        expected.add(new DefaultToken("-", TokenType.MINUS));
        expected.add(new DefaultToken("/", TokenType.DIVISION));
        expected.add(new DefaultToken("*", TokenType.MULTIPLICATION));

        LexicalAnalyzer analyzer = new LexicalAnalyzer(new Expression(TOKENS_SET_TEST),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = analyzer.readToken();

        while (!currentToken.equals(FinalToken.getInstance())) {
            readTokens.add(currentToken);
            currentToken = analyzer.readToken();
        }

        Assert.assertEquals(expected, readTokens);
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

    @Test(timeout = 100)
    public void tokensAreGivenBackCorrectlyTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(TOKENS_ARE_GIVEN_BACK_CORRECTLY_TEST),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }

        lexicalAnalyzer.giveBackToken();
        lexicalAnalyzer.giveBackToken();
    }

    @Test(timeout = 100)
    public void giveBackThreeTokensTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(GIVE_BACK_THREE_TOKENS_TEST),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }

        try {
            lexicalAnalyzer.giveBackToken();
            lexicalAnalyzer.giveBackToken();
            lexicalAnalyzer.giveBackToken();
            throw new AssertionError("lexical analyzer cannot give back more than 2 tokens");
        } catch (RuntimeException e) {
            /*NOP*/
        }
    }

    @Test(timeout = 100)
    public void currentTokenWhileReadingTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(CURRENT_TOKEN_WHILE_READING_TEST),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token firstToken = new DefaultToken("+", TokenType.PLUS);
        Token secondToken = FinalToken.getInstance();

        Assert.assertEquals(lexicalAnalyzer.getCurrentToken() == null, true);
        lexicalAnalyzer.readToken();
        Assert.assertEquals(lexicalAnalyzer.getCurrentToken().equals(firstToken), true);
        lexicalAnalyzer.readToken();
        Assert.assertEquals(lexicalAnalyzer.getCurrentToken().equals(secondToken), true);
    }

    @Test(timeout = 100)
    public void lastTokenWhileReadingTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(LAST_TOKEN_WHILE_READING_TEST),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token firstToken = new DefaultToken("+", TokenType.PLUS);

        Assert.assertEquals(lexicalAnalyzer.getLastToken() == null, true);
        lexicalAnalyzer.readToken();
        Assert.assertEquals(lexicalAnalyzer.getLastToken() == null, true);
        lexicalAnalyzer.readToken();
        Assert.assertEquals(lexicalAnalyzer.getLastToken().equals(firstToken), true);
        lexicalAnalyzer.readToken();
        Assert.assertEquals(lexicalAnalyzer.getLastToken().equals(FinalToken.getInstance()), true);
    }

    @Test(timeout = 100)
    public void currentTokenWhileGivingBackTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(CURRENT_TOKEN_WHILE_GIVING_BACK),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }

        Token firstToken = new DefaultToken("*", TokenType.MULTIPLICATION);

        lexicalAnalyzer.giveBackToken();
        Assert.assertEquals(lexicalAnalyzer.getCurrentToken().equals(firstToken), true);
        lexicalAnalyzer.giveBackToken();
        Assert.assertEquals(lexicalAnalyzer.getLastToken() == null, true);
    }

    @Test(timeout = 100)
    public void lastTokenWhileGivingBackTest() {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new Expression(LAST_TOKEN_WHILE_GIVING_BACK),
                LexicalAnalyzerMode.FULL_OPERATIONS_SET);

        Token currentToken = lexicalAnalyzer.readToken();
        while (!currentToken.equals(FinalToken.getInstance())) {
            currentToken = lexicalAnalyzer.readToken();
        }

        lexicalAnalyzer.giveBackToken();
        Assert.assertEquals(lexicalAnalyzer.getLastToken() == null, true);
    }
}
