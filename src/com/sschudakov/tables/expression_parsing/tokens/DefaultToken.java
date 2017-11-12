package com.sschudakov.tables.expression_parsing.tokens;

import com.sschudakov.tables.expression_parsing.TokenType;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */

public class DefaultToken implements Token {

    private static DefaultToken finalToken;

    private Object token;
    private TokenType tokenType;

    private Token leftToken;
    private Token rightToken;

    //getters and setters
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

    public static DefaultToken getFinalToken() {
        return finalToken;
    }

    public DefaultToken() {

    }

    static {
        finalToken = new DefaultToken();
        finalToken.setToken("F");
        finalToken.setTokenType(TokenType.FINAL_TOKEN);
        finalToken.setLeftToken(finalToken);
        finalToken.setRightToken(finalToken);
    }

    public DefaultToken(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public DefaultToken(Object token, TokenType tokenType, DefaultToken leftToken, DefaultToken rightToken) {
        this.token = token;
        this.tokenType = tokenType;
        this.leftToken = leftToken;
        this.rightToken = rightToken;
    }

    @Override
    public boolean isPlus() {
        return this.tokenType.equals(TokenType.PLUS);
    }

    @Override
    public boolean isMinus() {
        return this.tokenType.equals(TokenType.MINUS);
    }

    @Override
    public boolean isPlusOrMinus() {
        return this.tokenType.equals(TokenType.PLUS) || this.tokenType.equals(TokenType.MINUS);
    }

    @Override
    public boolean isMultiplication() {
        return this.tokenType.equals(TokenType.MULTIPLICATION);
    }

    @Override
    public boolean isDivision() {
        return this.tokenType.equals(TokenType.DIVISION);
    }

    @Override
    public boolean isModulus() {
        return this.tokenType.equals(TokenType.MODULUS);
    }

    @Override
    public boolean isIntegerDivision() {
        return this.tokenType.equals(TokenType.INTEGER_DIVISION);
    }

    @Override
    public boolean isMultiplicationDivisionModulusIntegerDivision() {
        return isMultiplication() ||
                isDivision() ||
                isModulus() ||
                isIntegerDivision();
    }

    @Override
    public boolean isLeftParenthesis() {
        return this.tokenType.equals(TokenType.LEFT_PARENTHESIS);
    }

    @Override
    public boolean isRightParenthesis() {
        return this.tokenType.equals(TokenType.RIGHT_PARENTHESIS);
    }

    @Override
    public boolean isMeshName() {
        return this.tokenType.equals(TokenType.MESH_NAME);
    }

    @Override
    public boolean isExponent() {
        return this.tokenType.equals(TokenType.EXPONENT);
    }

    @Override
    public boolean isNumber() {
        return this.tokenType.equals(TokenType.NUMBER);
    }

    @Override
    public boolean isFinalToken() {
        return this.tokenType.equals(TokenType.FINAL_TOKEN);
    }

    @Override
    public boolean isOperator() {
        return isPlusOrMinus()
                || isMultiplicationDivisionModulusIntegerDivision()
                || this.tokenType.equals(TokenType.EXPONENT);
    }

    @Override
    public boolean isEquationSign() {
        return this.tokenType.equals(TokenType.EQUATION_SIGN);
    }

    @Override
    public boolean isGreaterThanOrEqualTo() {
        return this.tokenType.equals(TokenType.GREATER_THAN_OR_EQUAL_TO);
    }

    @Override
    public boolean isGreaterThan() {
        return this.tokenType.equals(TokenType.GREATER_THAN);
    }

    @Override
    public boolean isLessThanOrEqualTo() {
        return this.tokenType.equals(TokenType.LESS_THAN_OR_EQUAL_TO);
    }

    @Override
    public boolean isLessThan() {
        return this.tokenType.equals(TokenType.LESS_THAN);
    }

    @Override
    public boolean isNotEqual() {
        return this.tokenType.equals(TokenType.NOT_EQUAL);
    }

    @Override
    public boolean isLogicalOperator() {
        return this.tokenType.equals(TokenType.GREATER_THAN)
                || this.tokenType.equals(TokenType.GREATER_THAN_OR_EQUAL_TO)
                || this.tokenType.equals(TokenType.LESS_THAN)
                || this.tokenType.equals(TokenType.LESS_THAN_OR_EQUAL_TO)
                || this.tokenType.equals(TokenType.NOT_EQUAL)
                || this.tokenType.equals(TokenType.EQUATION_SIGN);
    }

    @Override
    public boolean isMax() {
        return false;
    }

    @Override
    public boolean isMin() {
        return false;
    }

    @Override
    public boolean isMaxMin() {
        return false;
    }

    @Override
    public boolean isMMax() {
        return false;
    }

    @Override
    public boolean isMMin() {
        return false;
    }

    @Override
    public boolean isMultipleOperandsToken() {
        return false;
    }

    @Override
    public boolean isComma() {
        return this.tokenType.equals(TokenType.COMMA);
    }


    @Override
    public int size() {
        return this.token.toString().length();
    }


    @Override
    public String toString() {
        return "token: " + this.token + " token type: " + this.tokenType;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof DefaultToken) {
            DefaultToken otherToken = (DefaultToken) obj;

            return this.token.equals(otherToken.getToken()) && this.tokenType.equals(otherToken.getTokenType());
        }
        return false;
    }
}
