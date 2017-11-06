package com.sschudakov.tables.table_view;

import com.sschudakov.tables.expression_parsing.token.DefaultToken;

import java.io.Serializable;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableCell implements Serializable {

    private String value;
    private DefaultToken expression;

    public TableCell() {
        this.value = "";
    }

    public TableCell(String value) {
        this.value = value;
    }

    public TableCell(String value, DefaultToken expression) {
        this.value = value;
        this.expression = expression;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DefaultToken getExpression() {
        return expression;
    }

    public void setExpression(DefaultToken expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
