package com.sschudakov.tables.expression_parsing;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class Token {

    private Object token;
    private TokenType tokenType;

    private Token leftToken;
    private Token rightToken;


    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Token getLeftToken() {
        return leftToken;
    }

    public void setLeftToken(Token leftToken) {
        this.leftToken = leftToken;
    }

    public Token getRightToken() {
        return rightToken;
    }

    public void setRightToken(Token rightToken) {
        this.rightToken = rightToken;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Token() {
        this.token = new char[10];
    }

    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Token(Object token, TokenType tokenType, Token leftToken, Token rightToken) {
        this.token = token;
        this.tokenType = tokenType;
        this.leftToken = leftToken;
        this.rightToken = rightToken;
    }

    boolean isPlus() {
        return this.tokenType.equals(TokenType.PLUS);
    }

    boolean isMinus() {
        return this.tokenType.equals(TokenType.MINUS);
    }

    boolean isPlusOrMinus() {
        return this.tokenType.equals(TokenType.PLUS) || this.tokenType.equals(TokenType.MINUS);
    }

    boolean isMultiplication() {
        return this.tokenType.equals(TokenType.MULTIPLICATION);
    }

    boolean isDivision() {
        return this.tokenType.equals(TokenType.DIVISION);
    }

    boolean isModulus() {
        return this.tokenType.equals(TokenType.MODULUS);
    }


    boolean isMultiplicationDivisionModulus() {
        return isMultiplication()
                || isDivision()
                || isModulus();
    }

    boolean isLRB() {
        return this.tokenType.equals(TokenType.LEFT_PARENTHESIS);
    }

    boolean isRRB() {
        return this.tokenType.equals(TokenType.RIGHT_PARENTHESIS);
    }

    boolean isMeshName() {
        return this.tokenType.equals(TokenType.MESH_NAME);
    }

    boolean isExponent() {
        return this.tokenType.equals(TokenType.EXPONENT);
    }

    boolean isNumber() {
        return this.tokenType.equals(TokenType.NUMBER);
    }

    boolean isEquationSign() {
        return this.tokenType.equals(TokenType.EQUATION_SIGN);
    }

    boolean isFinalToken() {
        return this.tokenType.equals(TokenType.FINAL_TOKEN);
    }

    boolean isOperator() {
        return isPlusOrMinus()
                || isMultiplicationDivisionModulus()
                || this.tokenType.equals(TokenType.EXPONENT);
    }

    @Override
    public String toString() {
        return "token: " + this.token + " token type: " + this.tokenType;
    }

    public int size(){
        return this.token.toString().length();
    }

}
