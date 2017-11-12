package com.sschudakov.tables.expression_parsing.tokens;

/**
 * Created by Semen Chudakov on 06.11.2017.
 */
public class FinalToken implements Token {

    private static FinalToken ourInstance = new FinalToken();

    public static FinalToken getInstance() {
        return ourInstance;
    }

    private FinalToken() {
    }


    @Override
    public boolean isPlus() {
        return false;
    }

    @Override
    public boolean isMinus() {
        return false;
    }

    @Override
    public boolean isPlusOrMinus() {
        return false;
    }

    @Override
    public boolean isMultiplication() {
        return false;
    }

    @Override
    public boolean isDivision() {
        return false;
    }

    @Override
    public boolean isModulus() {
        return false;
    }

    @Override
    public boolean isIntegerDivision() {
        return false;
    }

    @Override
    public boolean isMultiplicationDivisionModulusIntegerDivision() {
        return false;
    }

    @Override
    public boolean isLeftParenthesis() {
        return false;
    }

    @Override
    public boolean isRightParenthesis() {
        return false;
    }

    @Override
    public boolean isMeshName() {
        return false;
    }

    @Override
    public boolean isExponent() {
        return false;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public boolean isFinalToken() {
        return true;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public boolean isEquationSign() {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqualTo() {
        return false;
    }

    @Override
    public boolean isGreaterThan() {
        return false;
    }

    @Override
    public boolean isLessThanOrEqualTo() {
        return false;
    }

    @Override
    public boolean isLessThan() {
        return false;
    }

    @Override
    public boolean isNotEqual() {
        return false;
    }

    @Override
    public boolean isLogicalOperator() {
        return false;
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
        return false;
    }


    @Override
    public int size() {
        return 1;
    }


    @Override
    public String toString() {
        return "token type: FINAL_TOKEN";
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
