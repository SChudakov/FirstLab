package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.table_view.TableCell;
import com.sschudakov.utils.MeshNameParser;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class ExpressionTree {

    private Token head;
    private List<Node> variables;
    private DefaultTableModel model;

    //getters and setters
    public Token getHead() {
        return head;
    }

    public void setHead(Token head) {
        this.head = head;
    }

    //constructors
    public ExpressionTree() {
        this.variables = new LinkedList<>();
    }

    public ExpressionTree(DefaultTableModel model) {
        this.model = model;
    }

    public ExpressionTree(Token head) {
        this.head = head;
        this.variables = new LinkedList<>();
    }

    public ExpressionTree(Token head, List<Node> variables) {
        this.head = head;
        this.variables = variables;
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

    private void normalize() {
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

    public Object evaluate() {
        normalize();
//        make_initializations();//obligatory
        return evaluate(head);
    }

    private Object evaluate(Token token) {

        if (token.isFinalToken()) {
            throw new IllegalArgumentException("cannot evaluate finalToken");
        }

        if (token.isOperator()) {
            return evaluateOperations(token);
        }
        if (token.isLRB()) {
            return evaluateBraces(token);
        }
        if (token.isNumber()) {
            return evaluateNumbers(token);
        }
        if (token.isLogicalOperator()) {
            return evaluateLogicalOperator(token);
        }
        if (token.isMeshName()) {
            return evaluateNames(token);
        }


        throw new IllegalArgumentException("Lexem has no matches");
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
                    findName(name).setValue(token.getRightToken());
                }
            } else {
                findName(name).setValue(token.getRightToken());
            }
        }

        if (token.isMeshName()) {
            if (findName((String) token.getToken()) != null) {
                variables.add(new Node((String) token.getToken(), Token.getFinalToken()));
                System.out.println("variable " + token.getToken() + " has been initialized");
            }
        }
        make_initializations(token.getLeftToken());
        make_initializations(token.getRightToken());
    }


    private Double evaluateOperations(Token token) {

        Token leftToken = token.getLeftToken();
        Token rightToken = token.getRightToken();

        double leftTokenValue = (double) evaluate(leftToken);
        double rightTokenValue = (double) evaluate(rightToken);

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
            return (double) ((int) leftTokenValue % (int) rightTokenValue);
        }
        if (token.isExponent()) {
            return Math.pow(leftTokenValue, rightTokenValue);
        }
        throw new IllegalArgumentException("token " + token + " is not an operations token");
    }

    private Object evaluateBraces(Token token) {
        return evaluate(token.getLeftToken());
    }

    private Double evaluateNumbers(Token token) {
        return (double) (int) (token.getToken());
    }

    private Boolean evaluateLogicalOperator(Token token) {

        Token leftToken = token.getLeftToken();
        Token rightToken = token.getRightToken();

        double leftTokenValue = (double) evaluate(leftToken);
        double rightTokenValue = (double) evaluate(rightToken);

        if (token.isEquationSign()) {
            return leftTokenValue == rightTokenValue;
        }
        if (token.isGreaterThanOrEqualTo()) {
            return leftTokenValue >= rightTokenValue;
        }
        if (token.isGreaterThan()) {
            return leftTokenValue > rightTokenValue;
        }
        if (token.isLessThanOrEqualTo()) {
            return leftTokenValue <= rightTokenValue;
        }
        if (token.isLessThan()) {
            return leftTokenValue < rightTokenValue;
        }
        if (token.isNotEqual()) {
            return leftTokenValue != rightTokenValue;
        }


        throw new IllegalArgumentException("token: " + token + " is not a logical operator");
    }

    private double evaluateNames(Token token) {

        double result;

        if (token.isMeshName()) {
            String meshName = (String) token.getToken();

            result = readValue(meshName);

        } else {
            throw new IllegalArgumentException("token " + token + " is not a mesh name token");
        }
        return result;
    }

    private double readValue(String meshName) {

        int row = MeshNameParser.parseRow(meshName);
        int column = MeshNameParser.parseColumn(meshName);

        Double result = 0.0;

        Object meshContent = this.model.getValueAt(row, column);

        if (meshContent != null) {

            if (meshContent instanceof TableCell) {
                TableCell mesh = (TableCell) meshContent;
                result = Double.valueOf(mesh.getValue());
            }
        } else {
            throw new RuntimeException("mesh " + meshName + " has no value");
        }
        return result;
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

    private Node findName(String name) {

        for (Node variable : this.variables) {
            if (variable.getName().equals(name)) {
                return variable;
            }
        }
        return null;
    }


}
