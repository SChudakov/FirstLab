package com.sschudakov.tables.expression_parsing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class LexicalAnalyzer {

    private Expression expression;

    private Token lastToken;
    private Token currentToken;

    private List<Character> tokenSymbols;

    private boolean tokenGivenBack;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Token getLastToken() {
        return lastToken;
    }

    public void setLastToken(Token lastToken) {
        this.lastToken = lastToken;
    }

    public Token getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Token currentToken) {
        this.currentToken = currentToken;
    }

    public LexicalAnalyzer(Expression expression) {

        this.expression = expression;

        this.tokenSymbols = new ArrayList<>();
        this.tokenSymbols.add('+');
        this.tokenSymbols.add('-');
        this.tokenSymbols.add('*');
        this.tokenSymbols.add('/');
        this.tokenSymbols.add('%');
        this.tokenSymbols.add('^');
        this.tokenSymbols.add('(');
        this.tokenSymbols.add(')');
        this.tokenSymbols.add('=');
        this.tokenSymbols.add('>');
        this.tokenSymbols.add('<');
        this.tokenSymbols.add('!');

    }

    public Token getToken() {

        if (this.expression.isEmpty()) {
            return this.currentToken;
        }

        Token result;

        char currentCharacter = this.expression.readCharacter();

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
            result = handleNames();
            currentToken = result;
            this.lastToken = this.currentToken;
            return result;
        }

        if (!isTokenSymbol(currentCharacter)) {
            System.out.println("is not a token symbol");
            this.expression.giveBackCharacter();
            handleNotTokenSymbols();
            return getToken();
        }
        //when coming here it can be only atomic Token
        this.expression.giveBackCharacter();
        result = handleAtomicToken();
        this.lastToken = this.currentToken;
        this.currentToken = result;
        return result;
    }

    private Token handleNumbers() {
        System.out.println("handle numbers");
        Token result = new Token();
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

    private Token handleNames() {

        System.out.println("handle names");
        Token result = new Token();
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

    private void handleNotTokenSymbols() {
        System.out.println("handleNotTokenSymbols");
        StringBuilder mistake = new StringBuilder("");
        char character = this.expression.readCharacter();
        while (!isTokenSymbol(character)) {
            mistake.append(character);
            character = this.expression.readCharacter();
        }
        this.expression.giveBackCharacter();

        System.out.println(mistake.toString() + " is a mistake");
    }

    private Token handleAtomicToken() {
        System.out.println("handleAtomicToken");
        char character = this.expression.readCharacter();
        Token result = new Token();
        if (character == '+') {
            result.setTokenType(TokenType.PLUS);
            result.setToken('+');
            return result;
        }
        if (character == '-') {
            result.setTokenType(TokenType.MINUS);
            result.setToken('-');
            return result;
        }
        if (character == '*') {
            result.setTokenType(TokenType.MULTIPLICATION);
            result.setToken('*');
            return result;
        }
        if (character == '/') {
            result.setTokenType(TokenType.DIVISION);
            result.setToken('/');
            return result;
        }
        if (character == '%') {
            result.setTokenType(TokenType.MODULUS);
            result.setToken('%');
            return result;
        }
        if (character == '^') {
            result.setTokenType(TokenType.EXPONENT);
            result.setToken('^');
            return result;
        }
        if (character == '(') {
            result.setTokenType(TokenType.LEFT_PARENTHESIS);
            result.setToken('(');
            return result;
        }
        if (character == ')') {
            result.setTokenType(TokenType.RIGHT_PARENTHESIS);
            result.setToken(')');
            return result;
        }
        if (character == '=') {
            result.setTokenType(TokenType.EQUATION_SIGN);
            result.setToken('=');
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

        throw new IllegalArgumentException("character " + character + " fits no token");
    }


    public void giveBackToken() {

        if (this.currentToken != null) {
            if (!this.tokenGivenBack) {
                for (int i = 0; i < this.currentToken.size(); i++) {
                    this.expression.giveBackCharacter();
                }
                this.currentToken = this.lastToken;
                this.lastToken = null;
                this.tokenGivenBack = true;
            }else {
                throw new RuntimeException("token can not be given back twice");
            }
        }
    }

    public Token lastToken() {
        return lastToken;
    }

    public Token currentToken() {
        return this.lastToken;
    }


    public boolean isDigit(char s) {
        return (int) s >= 48 && (int) s <= 57;
    }

    public boolean isLetter(char s) {
        return (int) s >= 65 && (int) s <= 90 || (int) s >= 97 && (int) s <= 122;
    }

    public boolean isTokenSymbol(char character) {
        boolean result = false;
        for (Character tokenSymbol : this.tokenSymbols) {
            if (tokenSymbol.equals(character)) {
                result = true;
            }
        }
        return result || isLetter(character) || isDigit(character);
    }


}
