package com.sschudakov.tables.expression_parsing;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class Expression {

    private int index;
    private String expression;
    public static final Character EXPRESSION_BEGIN = '\0';
    public static final Character EXPRESSION_END = '\0';

    public Expression(String expression) {
        this.expression = expression;
    }

    public char readCharacter() {
        if (index == this.expression.length()) {
            return EXPRESSION_END;
        }
        return this.expression.charAt(index++);
    }

    public void giveBackCharacter() {
        if (index != 0) {
            index--;
        }
    }

    public char lastCharackter() {
        if (index == 0) {
            return EXPRESSION_BEGIN;
        }
        return this.expression.charAt(index - 1);
    }

    public char currentCharacter() {
        if(this.index == this.expression.length()){
            return EXPRESSION_END;
        }
        return this.expression.charAt(index);
    }

    public boolean isEmpty() {
        return index == this.expression.length();
    }

    @Override
    public String toString() {
        return this.expression;
    }
}
