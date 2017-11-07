package com.sschudakov.tables.expression_parsing.tokens;

/**
 * Created by Semen Chudakov on 06.11.2017.
 */
public interface Token {
    boolean isPlus();

    boolean isMinus();

    boolean isPlusOrMinus();

    boolean isMultiplication();

    boolean isDivision();

    boolean isModulus();


    boolean isMultiplicationDivisionModulus();

    boolean isLeftParenthesis();

    boolean isRightParenthesis();

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

    boolean isMMax();

    boolean isMMin();

    boolean isMultipleOperandsToken();


    int size();
}
