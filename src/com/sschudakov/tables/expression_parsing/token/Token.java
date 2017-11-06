package com.sschudakov.tables.expression_parsing.token;

import com.sschudakov.tables.expression_parsing.TokenType;

/**
 * Created by Semen Chudakov on 06.11.2017.
 */
interface Token {
    boolean isPlus();

    boolean isMinus();

    boolean isPlusOrMinus();

    boolean isMultiplication();

    boolean isDivision();

    boolean isModulus();


    boolean isMultiplicationDivisionModulus();

    boolean isLRB();

    boolean isRRB();

    boolean isMeshName();

    boolean isExponent();

    boolean isNumber();


    boolean isFinalToken();

    boolean isOperator();


    boolean isEquationSign();

    boolean isGreaterThanOrEqualTo();

    boolean isGreaterThan();

    boolean isLessThanOrEqualTo();

    boolean isLessThan();

    boolean isNotEqual();


    boolean isLogicalOperator();


    int size();
}
