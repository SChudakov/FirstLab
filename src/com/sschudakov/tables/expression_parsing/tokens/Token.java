package com.sschudakov.tables.expression_parsing.tokens;

import java.io.Serializable;

/**
 * Created by Semen Chudakov on 06.11.2017.
 */
public interface Token{

    boolean isPlus();

    boolean isMinus();

    boolean isPlusOrMinus();


    boolean isMultiplication();

    boolean isDivision();

    boolean isModulus();

    boolean isIntegerDivision();


    boolean isMultiplicationDivisionModulusIntegerDivision();

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


    boolean isMax();

    boolean isMin();

    boolean isMaxMin();

    boolean isMMax();

    boolean isMMin();

    boolean isMultipleOperandsToken();


    boolean isComma();


    int size();
}
