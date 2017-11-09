package com.sschudakov.tables.expression_parsing.tokens;

import com.sschudakov.tables.expression_parsing.TokenType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semen Chudakov on 06.11.2017.
 */
public class MultipleOperandsToken implements Token {


    private Object token;
    private TokenType tokenType;

    private List<Token> operands;


    //constructors
    public MultipleOperandsToken() {
        this(null, null);
    }

    public MultipleOperandsToken(Object token, TokenType tokenType) {
        this.token = token;
        this.tokenType = tokenType;
        this.operands = new LinkedList<>();
    }

    //get operands
    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public List<Token> getOperands() {
        return operands;
    }




    public void addOperand(Token operand){
        this.operands.add(operand);
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
        return isMultiplication()
                || isDivision()
                || isModulus()
                || isIntegerDivision();
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
    public boolean isMMax() {
        return this.tokenType.equals(TokenType.MMAX);
    }

    @Override
    public boolean isMMin() {
        return this.tokenType.equals(TokenType.MMIN);
    }

    @Override
    public boolean isMultipleOperandsToken() {
        return isMMax() || isMMin();
    }

    @Override
    public boolean isComma() {
        return false;
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
