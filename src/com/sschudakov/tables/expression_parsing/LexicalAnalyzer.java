package com.sschudakov.tables.expression_parsing;

import com.sschudakov.exceptions.IllegalSyntaxException;
import com.sschudakov.exceptions.IllegalTokenException;
import com.sschudakov.tables.expression_parsing.tokens.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class LexicalAnalyzer {

    private static final String DIV = "div";
    private static final String MOD = "mod";
    private static final String MAX = "max";
    private static final String MIN = "min";
    private static final String MMAX = "mmax";
    private static final String MMIN = "mmin";

    private Expression expression;

    private Token lastToken;
    private Token currentToken;

    private List<Character> tokenSymbols;

    private LexicalAnalyzerMode mode;


    //getters and setters
    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public LexicalAnalyzerMode getMode() {
        return mode;
    }

    public void setMode(LexicalAnalyzerMode mode) {
        this.mode = mode;
    }

    public Token getLastToken() {
        return lastToken;
    }

    public Token getCurrentToken() {
        return currentToken;
    }


    public LexicalAnalyzer(LexicalAnalyzerMode mode) {
        this(null, mode);
    }

    public LexicalAnalyzer(Expression expression, LexicalAnalyzerMode mode) {

        this.expression = expression;
        this.mode = mode;
        this.tokenSymbols = new ArrayList<>();
        initializeTokenSymbols();

    }

    private void initializeTokenSymbols() {
        this.tokenSymbols.add('+');
        this.tokenSymbols.add('-');
        this.tokenSymbols.add('*');
        this.tokenSymbols.add('/');
        this.tokenSymbols.add('(');
        this.tokenSymbols.add(')');
        this.tokenSymbols.add('=');
        this.tokenSymbols.add('>');
        this.tokenSymbols.add('<');
        this.tokenSymbols.add('!');
        this.tokenSymbols.add(',');
        this.tokenSymbols.add('^');
    }


    public Token readToken() {

        Token result;
        char currentCharacter = this.expression.readCharacter();

        if (isExpressionEndSymbol(currentCharacter)) {
            this.lastToken = this.currentToken;
            this.currentToken = FinalToken.getInstance();
            return FinalToken.getInstance();
        }

        if (isDigit(currentCharacter)) {
            System.out.println("is digit");
            this.expression.giveBackCharacter();
            result = handleNumbers();
            this.lastToken = this.currentToken;
            this.currentToken = result;
            return result;
        }

        if (isLetter(currentCharacter)) {
            System.out.println("is letter");
            this.expression.giveBackCharacter();
            result = handleLetters();
            currentToken = result;
            this.lastToken = this.currentToken;
            return result;
        }

        if (!isTokenSymbol(currentCharacter)) {
            System.out.println("is not a token symbol");
            this.expression.giveBackCharacter();
            handleNotTokenSymbols();
            return readToken();
        }
        //when coming here it can be only atomic DefaultToken
        this.expression.giveBackCharacter();
        result = handleAtomicToken();
        this.lastToken = this.currentToken;
        this.currentToken = result;
        return result;
    }

    private DefaultToken handleNumbers() {
        System.out.println("handle numbers");
        DefaultToken result = new DefaultToken();
        StringBuilder number = new StringBuilder("");

        char character = this.expression.readCharacter();
        while (isDigit(character)) {
            number.append(character);
            character = this.expression.readCharacter();
        }
        //there is anyway one extra read sign
        this.expression.giveBackCharacter();

        result.setToken(Integer.valueOf(number.toString()));
        result.setTokenType(TokenType.NUMBER);
        return result;
    }

    private Token handleLetters() {

        char currentCharacter = this.expression.readCharacter();

        if (isUppercaseLetter(currentCharacter)) {
            this.expression.giveBackCharacter();
            return handleNames();
        }

        if (isLowercaseLetter(currentCharacter)) {
            this.expression.giveBackCharacter();
            return handleLiteralOperation();
        }
        throw new IllegalArgumentException("character " + currentCharacter + " is not a letter");
    }

    private Token handleLiteralOperation() {

        StringBuilder operation = new StringBuilder("");
        DefaultToken defaultToken = new DefaultToken();
        MultipleOperandsToken multipleOperandsTokenResult = new MultipleOperandsToken();


        // first case - mod or div
        for (int i = 0; i < 3; i++) {
            operation.append(this.expression.readCharacter());
        }

        if (operation.toString().equals(DIV)) {
            defaultToken.setTokenType(TokenType.INTEGER_DIVISION);
            defaultToken.setToken(DIV);
            return defaultToken;
        }

        if (operation.toString().equals(MOD)) {
            defaultToken.setTokenType(TokenType.MODULUS);
            defaultToken.setToken(MOD);
            return defaultToken;
        }
        if (operation.toString().equals(MAX)) {
            if (this.mode.equals(LexicalAnalyzerMode.FULL_OPERATIONS_SET)) {
                multipleOperandsTokenResult.setTokenType(TokenType.MAX);
                multipleOperandsTokenResult.setToken(MAX);
                return multipleOperandsTokenResult;
            } else {
                throw new IllegalTokenException("operation max is not supported in restricted operations set mode");
            }
        }
        if (operation.toString().equals(MIN)) {
            if (this.mode.equals(LexicalAnalyzerMode.FULL_OPERATIONS_SET)) {
                multipleOperandsTokenResult.setTokenType(TokenType.MIN);
                multipleOperandsTokenResult.setToken(MIN);
                return multipleOperandsTokenResult;
            } else {
                throw new IllegalTokenException("operation min is not supported in restricted operations set mode");
            }
        }

        // second case - mmax or mmin
        operation.append(this.expression.readCharacter());

        if (operation.toString().equals(MMAX)) {
            multipleOperandsTokenResult.setTokenType(TokenType.MMAX);
            multipleOperandsTokenResult.setToken(MMAX);
            return multipleOperandsTokenResult;

        }

        if (operation.toString().equals(MMIN)) {
            multipleOperandsTokenResult.setTokenType(TokenType.MMIN);
            multipleOperandsTokenResult.setToken(MMIN);
            return multipleOperandsTokenResult;

        }
        throw new IllegalArgumentException("operation " + operation.toString() + " is not supported");
    }

    private DefaultToken handleNames() {

        System.out.println("handle names");
        DefaultToken result = new DefaultToken();
        char character = this.expression.readCharacter();

        if (!isLetter(character)) {
            throw new IllegalArgumentException("handle names is called wrong, cause " + character + " is not a letter");
        }

        StringBuffer cellName = new StringBuffer("");

        while (isLetter(character)) {
            cellName.append(character);
            character = this.expression.readCharacter();
        }
        //there is anyway one extra read character that must be a digit
        if (!isDigit(character)) {
            throw new IllegalArgumentException("a cell name must contain at least one digit in name");
        }

        while (isDigit(character)) {
            cellName.append(character);
            character = this.expression.readCharacter();
        }
        //there is anyway one extra read character
        this.expression.giveBackCharacter();

        result.setTokenType(TokenType.MESH_NAME);
        result.setToken(cellName.toString());
        return result;
    }

    private DefaultToken handleAtomicToken() {


        System.out.println("handleAtomicToken");
        char character = this.expression.readCharacter();
        DefaultToken result = new DefaultToken();
        if (character == '+') {
            result.setTokenType(TokenType.PLUS);
            result.setToken("+");
            return result;
        }
        if (character == '-') {
            result.setTokenType(TokenType.MINUS);
            result.setToken("-");
            return result;
        }
        if (character == '*') {
            result.setTokenType(TokenType.MULTIPLICATION);
            result.setToken("*");
            return result;
        }
        if (character == '/') {
            result.setTokenType(TokenType.DIVISION);
            result.setToken("/");
            return result;
        }
        if (character == '^') {
            if (this.mode.equals(LexicalAnalyzerMode.FULL_OPERATIONS_SET)) {
                result.setTokenType(TokenType.EXPONENT);
                result.setToken("^");
                return result;
            } else {
                throw new IllegalTokenException("token ^ is not supported in restricted operations set mode");
            }
        }
        if (character == '(') {
            result.setTokenType(TokenType.LEFT_PARENTHESIS);
            result.setToken("(");
            return result;
        }
        if (character == ')') {
            result.setTokenType(TokenType.RIGHT_PARENTHESIS);
            result.setToken(")");
            return result;
        }
        if (character == '=') {
            result.setTokenType(TokenType.EQUATION_SIGN);
            result.setToken("=");
            return result;
        }
        if (character == ',') {
            result.setTokenType(TokenType.COMMA);
            result.setToken(",");
            return result;
        }
        if (character == '>') {
            if (this.expression.readCharacter() == '=') {
                result.setTokenType(TokenType.GREATER_THAN_OR_EQUAL_TO);
                result.setToken(">=");
                return result;
            } else {
                this.expression.giveBackCharacter();
                result.setTokenType(TokenType.GREATER_THAN);
                result.setToken(">");
                return result;
            }
        }
        if (character == '<') {
            if (this.expression.readCharacter() == '=') {
                result.setTokenType(TokenType.LESS_THAN_OR_EQUAL_TO);
                result.setToken("<=");
                return result;
            } else {
                this.expression.giveBackCharacter();
                result.setTokenType(TokenType.LESS_THAN);
                result.setToken("<");
                return result;
            }
        }
        if (character == '!') {
            if (this.expression.readCharacter() == '=') {
                result.setTokenType(TokenType.NOT_EQUAL);
                result.setToken("!=");
                return result;
            } else {
                this.expression.giveBackCharacter();
                throw new IllegalArgumentException("character ! can only be used together with = (!= means not equal)");
            }
        }

        throw new IllegalTokenException("character " + character + " fits no token");
    }

    private void handleNotTokenSymbols() {
        System.out.println("handleNotTokenSymbols");
        StringBuilder mistake = new StringBuilder("");
        char character = this.expression.readCharacter();
        while (!isTokenSymbol(character) && character != Expression.EXPRESSION_END) {
            mistake.append(character);
            character = this.expression.readCharacter();
        }
        this.expression.giveBackCharacter();

        throw new IllegalSyntaxException(mistake.toString() + " - is a mistake");
    }


    public void giveBackToken() {

        if (this.currentToken != null) {
                for (int i = 0; i < this.currentToken.size(); i++) {
                    this.expression.giveBackCharacter();
                }
                this.currentToken = this.lastToken;
                this.lastToken = null;

        } else {
            throw new RuntimeException("there is no token to be give back");
        }
    }


    private boolean isDigit(char s) {
        return (int) s >= 48 && (int) s <= 57;
    }

    private boolean isLetter(char s) {
        return isUppercaseLetter(s) || isLowercaseLetter(s);
    }

    private boolean isUppercaseLetter(char s) {
        return (int) s >= 65 && (int) s <= 90;
    }

    private boolean isLowercaseLetter(char s) {
        return (int) s >= 97 && (int) s <= 122;
    }


    private boolean isTokenSymbol(char character) {
        boolean result = false;
        for (Character tokenSymbol : this.tokenSymbols) {
            if (tokenSymbol.equals(character)) {
                result = true;
            }
        }
        return result || isLetter(character) || isDigit(character);
    }

    private boolean isExpressionEndSymbol(char s){
        return s == Expression.EXPRESSION_END;
    }
}
