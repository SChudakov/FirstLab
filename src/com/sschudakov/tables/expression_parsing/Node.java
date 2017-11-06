package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.expression_parsing.token.DefaultToken;

/**
 * Created by Semen Chudakov on 01.11.2017.
 */
public class Node {
    private String name;
    private DefaultToken value;

    public Node(String name, DefaultToken value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DefaultToken getValue() {
        return value;
    }

    public void setValue(DefaultToken value) {
        this.value = value;
    }
}
