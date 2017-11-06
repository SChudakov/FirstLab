package com.sschudakov.tables.expression_parsing;

/**
 * Created by Semen Chudakov on 01.11.2017.
 */
public enum TokenType {

    NUMBER(),
    MESH_NAME(),

    PLUS(),
    MINUS(),
    MULTIPLICATION(),
    DIVISION(),
    MODULUS(),
    INTEGER_DIVISION(),
    EXPONENT(),

    MMAX(),
    MMIN(),

    LEFT_PARENTHESIS(),
    RIGHT_PARENTHESIS(),

    EQUATION_SIGN(),
    GREATER_THAN(),
    LESS_THAN(),
    GREATER_THAN_OR_EQUAL_TO(),
    LESS_THAN_OR_EQUAL_TO(),
    NOT_EQUAL(),

    COMMA(),

    FINAL_TOKEN()
}
