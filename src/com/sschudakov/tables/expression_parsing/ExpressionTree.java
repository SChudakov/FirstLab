package com.sschudakov.tables.expression_parsing;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class ExpressionTree {

    private Token head;
    private List<Node> variables;

    ExpressionTree() {
        this.variables = new LinkedList<>();
    }

    public ExpressionTree(Token head) {
        this.head = head;
    }

    Node findName(String name) {

        for (Node variable : this.variables) {
            if (variable.getName().equals(name)) {
                return variable;
            }
        }
        return null;
    }

    public static Token makeTree(Token father, Token left, Token right) {

        father.setLeftToken(left);
        father.setRightToken(right);

        return father;
    }

    public static Token makeTree(Token slip) {

        slip.setLeftToken(Token.getFinalToken());
        slip.setRightToken(Token.getFinalToken());

        return slip;
    }

    public void outputTree() {
        outputTree(head);
    }

    public static void outputTree(Token token) {

        if (token == null) {
            System.out.println("outputTree: token is null");
            return;
        }
        if (token.isFinalToken()) {
            System.out.println("final token");
            return;
        }

        Token currentLeft = token.getLeftToken();
        Token currentRight = token.getRightToken();
        if (currentLeft.isFinalToken() && currentRight.isFinalToken()) {
            System.out.println(token.getToken());
        } else {

            if (token.getTokenType().equals(TokenType.LEFT_PARENTHESIS)) {
                System.out.println(token.getToken());
                outputTree(token.getLeftToken());
                outputTree(token.getRightToken());
            } else {
                outputTree(token.getLeftToken());
                System.out.println(token.getToken());
                outputTree(token.getRightToken());
            }

        }

    }

    public void normalize() {
        normalize(head);
    }

    public static void normalize(Token token) {

        if (token == null) {
            return;
        }

        Token currentLeft = token.getLeftToken();
        Token currentRight = token.getRightToken();

        if (currentLeft == null) {
            token.setLeftToken(Token.getFinalToken());
        } else {
            if (!currentLeft.isFinalToken()) {
                normalize(currentLeft);
            }
        }
        if (currentRight == null) {
            token.setRightToken(Token.getFinalToken());
        } else {
            if (!currentRight.isFinalToken()) {
                normalize(currentRight);
            }
        }
    }

    public double evaluate() {
        normalize();
        make_initializations();//obligatory
        return evaluate(head);
    }

    private double evaluate(Token lex) {

        if (lex.isFinalToken()) {
            throw new IllegalArgumentException("cannot evaluate finalToken");
        }

        Token currentLeft = lex.getLeftToken();
        Token currentRight = lex.getRightToken();

        if (lex.isOperator()) {
            return handle_operations(lex);
        }
        if (lex.isLRB()) {
            return handle_braces(lex);
        }
        if (lex.isNumber()) {
            return handle_numbers(lex);
        }
        if (lex.isEquationSign()) {
            return handle_equations(lex);
        }
        if (lex.isMeshName()) {
            return handle_names(lex);
        }


        throw new IllegalArgumentException("Lexem has no matches");
    }


    private double handle_operations(Token token) {

        Token leftToken = token.getLeftToken();
        Token rightToken = token.getRightToken();

        double leftTokenValue = evaluate(leftToken);
        double rightTokenValue = evaluate(rightToken);

        if (token.isPlus()) {
            return leftTokenValue + rightTokenValue;
        }
        if (token.isMinus()) {
            return leftTokenValue - rightTokenValue;
        }
        if (token.isMultiplication()) {
            return leftTokenValue * rightTokenValue;
        }
        if (token.isDivision()) {
            return leftTokenValue / rightTokenValue;
        }
        if (token.isModulus()) {
            return (int) leftTokenValue % (int) rightTokenValue;
        }
        if (token.isExponent()) {
            return Math.pow(leftTokenValue, rightTokenValue);
        }
        throw new IllegalArgumentException("token " + token.toString() + " is not an operations token");
    }

    private double handle_braces(Token lex) {
        return evaluate(lex.getLeftToken());
    }

    private double handle_numbers(Token lex) {
        return (Double) lex.getToken();
    }

    private double handle_equations(Token token) {

        String nameOfVariable = (String) token.getLeftToken().getToken();
        initializeVariable(nameOfVariable, token.getRightToken());
        return evaluate(token.getRightToken());
    }


    private double handle_names(Token token) {
        String name = (String) token.getToken();
        outputTree(findName(name).getValue());
        if (findName(name).getValue().isFinalToken()) {
            throw new IllegalArgumentException("A variable " + name + " has no getValue");
        }
        return evaluate(findName(name).getValue());
    }


    private void make_initializations() {
        make_initializations(head);
    }

    private void make_initializations(Token token) {

        if (token.isFinalToken()) {
            return;
        }
        if (token.isEquationSign()) {

            if (!token.getLeftToken().isMeshName()) {
                throw new IllegalArgumentException("making initializations: illegal usage of = operation: there is no variable name");
            }


            // variables should be anyway initialized
            make_initializations(token.getLeftToken());
            make_initializations(token.getRightToken());

            String name = (String) token.getLeftToken().getToken();

            if (hasTheSame(token.getRightToken(), name)) {
                if (findName(name).getValue().isFinalToken()) {
                    throw new IllegalArgumentException("illegal initialization: a variable cannot be initialized " +
                            "recursively without having a getValue");
                } else {
                    fixGetValue(token.getRightToken(), name, findName(name).getValue());
                    // lex.getRightToken() has been fixed
                    findName(name).setValue(token.getRightToken());
                }
            } else {
                findName(name).setValue(token.getRightToken());
            }
        }

        if (token.isMeshName()) {

//            if (token.getToken() == (String) "v") {
//                outputVariables();
//                throw new IllegalArgumentException("token v cannot be used as a variable name");
//            }

            if (findName((String) token.getToken()) != null) {
                variables.add(new Node((String) token.getToken(), Token.getFinalToken()));
                System.out.println("variable " + token.getToken() + " has been initialized");
            }
        }
        make_initializations(token.getLeftToken());
        make_initializations(token.getRightToken());
    }

    private boolean hasTheSame(Token token, String name) {
        if (token.isFinalToken()) {
            return false;
        }
        if (token.isMeshName()) {
            String currentName = (String) token.getToken();
            if (name == currentName) {
                return true;
            } else {
                return hasTheSame(findName(currentName).getValue(), name);
            }
        }
        return hasTheSame(token.getLeftToken(), name) || hasTheSame(token.getRightToken(), name);
    }

    private void fixGetValue(Token token, String name, Token previousValue) {
        if (token.isFinalToken()) {
            return;
        }

        Token currentLeft = token.getLeftToken();
        Token currentRight = token.getRightToken();

        if (currentLeft.isMeshName()) {
            String currentName = (String) currentLeft.getToken();
            if (name.equals(currentName)) {
                token.setLeftToken(previousValue);
            } else {
                fixGetValue(findName(currentName).getValue(), name, previousValue);
            }

        }
        if (currentRight.isMeshName()) {
            String currentName = (String) currentRight.getToken();
            if (name.equals(currentName)) {
                token.setRightToken(previousValue);
            } else {
                fixGetValue(findName(currentName).getValue(), name, previousValue);
            }
        }
        fixGetValue(token.getLeftToken(), name, previousValue);
        fixGetValue(token.getRightToken(), name, previousValue);
    }

    private void outputVariables() {

        System.out.println(this.variables.toString());
    }

    private void initializeVariable(String name, Token value) {
        if (findName(name) != null) {
            variables.add(new Node(name, value));
        } else {
            findName(name).setValue(value);
        }
    }

}
