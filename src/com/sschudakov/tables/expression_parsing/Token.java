package com.sschudakov.tables.expression_parsing;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class Token {

    private static Token finalToken;

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

    public static Token getFinalToken() {
        return finalToken;
    }

    public Token() {
        this.token = new char[10];
    }

    static {
        finalToken = new Token();
        finalToken.setToken("L");
        finalToken.setTokenType(TokenType.FINAL_TOKEN);
        finalToken.setLeftToken(finalToken);
        finalToken.setRightToken(finalToken);
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

    public boolean isPlus() {
        return this.tokenType.equals(TokenType.PLUS);
    }

    public boolean isMinus() {
        return this.tokenType.equals(TokenType.MINUS);
    }

    public boolean isPlusOrMinus() {
        return this.tokenType.equals(TokenType.PLUS) || this.tokenType.equals(TokenType.MINUS);
    }

    public boolean isMultiplication() {
        return this.tokenType.equals(TokenType.MULTIPLICATION);
    }

    public boolean isDivision() {
        return this.tokenType.equals(TokenType.DIVISION);
    }

    public boolean isModulus() {
        return this.tokenType.equals(TokenType.MODULUS);
    }


    public boolean isMultiplicationDivisionModulus() {
        return isMultiplication()
                || isDivision()
                || isModulus();
    }

    public boolean isLRB() {
        return this.tokenType.equals(TokenType.LEFT_PARENTHESIS);
    }

    public boolean isRRB() {
        return this.tokenType.equals(TokenType.RIGHT_PARENTHESIS);
    }

    public boolean isMeshName() {
        return this.tokenType.equals(TokenType.MESH_NAME);
    }

    public boolean isExponent() {
        return this.tokenType.equals(TokenType.EXPONENT);
    }

    public boolean isNumber() {
        return this.tokenType.equals(TokenType.NUMBER);
    }

    public boolean isEquationSign() {
        return this.tokenType.equals(TokenType.EQUATION_SIGN);
    }

    public boolean isFinalToken() {
        return this.tokenType.equals(TokenType.FINAL_TOKEN);
    }

    public boolean isOperator() {
        return isPlusOrMinus()
                || isMultiplicationDivisionModulus()
                || this.tokenType.equals(TokenType.EXPONENT);
    }

    public boolean isLogicalOperator() {
        return this.tokenType.equals(TokenType.GREATER_THAN)
                || this.tokenType.equals(TokenType.GREATER_THAN_OR_EQUAL_TO)
                || this.tokenType.equals(TokenType.LESS_THAN)
                || this.tokenType.equals(TokenType.LESS_THAN_OR_EQUAL_TO)
                || this.tokenType.equals(TokenType.NOT_EQUAL)
                || this.tokenType.equals(TokenType.EQUATION_SIGN);
    }


    public int size() {
        return this.token.toString().length();
    }


    @Override
    public String toString() {
        return "token: " + this.token + " token type: " + this.tokenType;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Token) {
            Token otherToken = (Token) obj;

            return this.token.equals(otherToken.getToken()) && this.tokenType.equals(otherToken.getTokenType());
        }
        return false;
    }
}
