package com.sschudakov.tables.expression_parsing.token;

import com.sschudakov.tables.expression_parsing.TokenType;

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
    public boolean isMultiplicationDivisionModulus() {
        return false;
    }

    @Override
    public boolean isLRB() {
        return false;
    }

    @Override
    public boolean isRRB() {
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
