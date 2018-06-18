package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.expression_parsing.tokens.DefaultToken;
import com.sschudakov.tables.expression_parsing.tokens.Token;

/**
 * Created by Semen Chudakov on 01.11.2017.
 */
public class Node {
    private String name;
    private Token value;

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

    public Token getValue() {
        return value;
    }

    public void setValue(Token value) {
        this.value = value;
    }
}
