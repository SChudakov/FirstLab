package com.sschudakov.tables.table_view;

import com.sschudakov.tables.expression_parsing.Token;

import java.io.Serializable;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableCell implements Serializable {

    private String value;
    private Token expression;

    public TableCell() {
        this.value = "";
    }

    public TableCell(String value) {
        this.value = value;
    }

    public TableCell(String value, Token expression) {
        this.value = value;
        this.expression = expression;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Token getExpression() {
        return expression;
    }

    public void setExpression(Token expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
