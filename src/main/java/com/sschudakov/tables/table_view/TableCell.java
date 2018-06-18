package com.sschudakov.tables.table_view;

import com.sschudakov.tables.expression_parsing.tokens.DefaultToken;
import com.sschudakov.tables.expression_parsing.tokens.Token;

import java.io.Serializable;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableCell implements Serializable {

    private String value;
    private String expression;
    private Token parsedExpression;

    public TableCell() {
        this.value = "";
    }

    public TableCell(String value) {
        this.value = value;
    }

    public TableCell(String value, DefaultToken expression) {
        this.value = value;
        this.parsedExpression = expression;
    }

    // getters and setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Token getParsedExpression() {
        return parsedExpression;
    }

    public void setParsedExpression(Token parsedExpression) {
        this.parsedExpression = parsedExpression;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
