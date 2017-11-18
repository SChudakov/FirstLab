package com.sschudakov.tables.expression_parsing;

import com.sschudakov.exceptions.MeshHasNoValueException;
import com.sschudakov.tables.expression_parsing.tokens.*;
import com.sschudakov.tables.table_view.TableCell;
import com.sschudakov.tables.utils.MeshNameParser;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Semen Chudakov on 31.10.2017.
 */
public class ExpressionTree {

    private Token head;
    private HashSet<Node> variables;
    private DefaultTableModel model;

    //getters and setters
    public Token getHead() {
        return head;
    }

    public void setHead(Token head) {
        this.head = head;
    }

    //constructors

    public ExpressionTree(DefaultTableModel model) {
        this.variables = new HashSet<>();
        this.model = model;
    }

    public ExpressionTree(Token head) {
        this.head = head;
        this.variables = new HashSet<>();
    }


    public static DefaultToken makeTree(DefaultToken father, Token left, Token right) {

        father.setLeftToken(left);
        father.setRightToken(right);

        return father;
    }

    public static DefaultToken makeTree(DefaultToken slip) {

        slip.setLeftToken(FinalToken.getInstance());
        slip.setRightToken(FinalToken.getInstance());

        return slip;
    }


    public void outputTree() {
        outputTree(head);
    }

    public static void outputTree(Token token) {

        if (token instanceof DefaultToken) {
            outputDefaultToken((DefaultToken) token);
        }
        if (token instanceof MultipleOperandsToken) {
            outputMultipleOperandsToken((MultipleOperandsToken) token);
        }

    }

    private static void outputDefaultToken(DefaultToken token) {
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

    private static void outputMultipleOperandsToken(MultipleOperandsToken token) {
        if (token == null) {
            System.out.println("outputTree: token is null");
            return;
        }
        if (token.isFinalToken()) {
            System.out.println("final token");
            return;
        }

        System.out.println(token.getToken());
        for (int i = 0; i < token.getOperands().size(); i++) {
            outputTree(token.getOperands().get(i));
            if (i != token.getOperands().size() - 1) {
                System.out.println(",");
            }
        }
    }


    private void normalize(DefaultToken head) {
        normalize(this.head);
    }

    public static void normalize(Token token) {

        if (token == null) {
            return;
        }
        if (token instanceof DefaultToken) {
            normalizeDefaultToken((DefaultToken) token);
        }
        if (token instanceof MultipleOperandsToken) {
            normalizeMultipleOperandsToken((MultipleOperandsToken) token);
        }
    }

    private static void normalizeDefaultToken(DefaultToken token) {

        Token currentLeft = token.getLeftToken();
        Token currentRight = token.getRightToken();

        if (currentLeft == null) {
            token.setLeftToken(FinalToken.getInstance());
        } else {
            if (!currentLeft.isFinalToken()) {
                normalize(currentLeft);
            }
        }
        if (currentRight == null) {
            token.setRightToken(FinalToken.getInstance());
        } else {
            if (!currentRight.isFinalToken()) {
                normalize(currentRight);
            }
        }
    }

    private static void normalizeMultipleOperandsToken(MultipleOperandsToken multipleOperandsToken) {
        for (Token token : multipleOperandsToken.getOperands()) {
            normalize(token);
        }
    }


    public Object evaluate() {
//        normalize();
//        make_initializations();//obligatory
        return evaluate(head);
    }

    private Object evaluate(Token token) {

        if (token.isFinalToken()) {
            throw new IllegalArgumentException("cannot evaluate finalToken");
        }

        if (token instanceof DefaultToken) {
            return evaluateDefaultToken((DefaultToken) token);
        }

        if (token instanceof MultipleOperandsToken) {
            return evaluateMultipleOperandsToken((MultipleOperandsToken) token);
        }
        throw new IllegalArgumentException("token of type " + token.getClass() + " is not acceptable");
    }

    private Object evaluateDefaultToken(DefaultToken token) {
        if (token.isOperator()) {
            return evaluateOperations(token);
        }
        if (token.isLeftParenthesis()) {
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

        throw new IllegalArgumentException("Token:" + token + " has no matches");
    }

    private Double evaluateMultipleOperandsToken(MultipleOperandsToken token) {
        List<Double> operandsValues = buildOperandValueList(token);

        if (token.isMMax()) {
            return Collections.max(operandsValues);
        }

        if (token.isMMin()) {
            return Collections.min(operandsValues);
        }

        if (token.isMax()) {
            return Collections.max(operandsValues);
        }

        if (token.isMin()) {
            return Collections.min(operandsValues);
        }

        throw new IllegalArgumentException("multiple operation " + token + " is not supported");
    }

    private List<Double> buildOperandValueList(MultipleOperandsToken multipleOperandsToken) {
        List<Double> result = new ArrayList<>();
        for (Token token : multipleOperandsToken.getOperands()) {
            Object value = evaluate(token);
            if (value instanceof Double) {
                result.add((Double) value);
            }
            if (value instanceof Boolean) {
                throw new IllegalArgumentException("expressions with boolean value cannot be used in MMAX or MMIN operation");
            }
        }
        return result;
    }

    private Double evaluateOperations(DefaultToken token) {

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
            return leftTokenValue % rightTokenValue;
        }
        if (token.isIntegerDivision()) {
            return (double) ((int) leftTokenValue / (int) rightTokenValue);
        }
        if (token.isExponent()) {
            return Math.pow(leftTokenValue, rightTokenValue);
        }
        throw new IllegalArgumentException("token " + token + " is not an operations token");
    }

    private Object evaluateBraces(DefaultToken token) {
        return evaluate(token.getLeftToken());
    }

    private Double evaluateNumbers(DefaultToken token) {
        return (double) (int) (token.getToken());
    }

    private Boolean evaluateLogicalOperator(DefaultToken token) {

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

    private Object evaluateNames(DefaultToken token) {

        Object result;

        if (token.isMeshName()) {
            String meshName = (String) token.getToken();

            result = readValue(meshName);

        } else {
            throw new IllegalArgumentException("token " + token + " is not a mesh name token");
        }
        return result;
    }

    private Object readValue(String meshName) {

        int row = MeshNameParser.parseRow(meshName);
        int column = MeshNameParser.parseColumn(meshName);

        Token result = null;
        Object meshContent = this.model.getValueAt(row, column);

        if (meshContent != null) {
            if (meshContent instanceof TableCell) {
                TableCell mesh = (TableCell) meshContent;
                result = mesh.getParsedExpression();
            }
        } else {
            throw new MeshHasNoValueException("mesh " + meshName + " has no value");
        }
        return evaluate(result);
    }


    public boolean wouldCreateCycle(String meshName, Token value) {

        System.out.println("\nmesh name: " + meshName + "\n");
        System.out.println("\ntoken: " + value.toString() + "\n");

        if (value == null) {
            System.out.println("return false");
            return false;
        }
        if (value.isFinalToken()) {
            System.out.println("return false");
            return false;
        }
        if (value instanceof DefaultToken) {
            DefaultToken token = (DefaultToken) value;
            if (token.isMeshName()) {
                int row = MeshNameParser.parseRow((String) token.getToken());
                int column = MeshNameParser.parseColumn((String) token.getToken());


                boolean meshNameEquals = token.getToken().equals(meshName);
                boolean meshNameIsEmpty = (this.model.getValueAt(row, column) == null);
                boolean result;


                if (meshNameIsEmpty) {
                    result = meshNameEquals;
                } else {
                    result = token.getToken().equals(meshName) || wouldCreateCycle(meshName,
                            (((TableCell) this.model.getValueAt(row, column))).getParsedExpression());
                }

                System.out.println("return: " + result);
                return result;
            }
            boolean result = wouldCreateCycle(meshName, token.getLeftToken())
                    || wouldCreateCycle(meshName, token.getRightToken());

            System.out.println("return: " + result);
            return result;
        }

        if (value instanceof MultipleOperandsToken) {
            MultipleOperandsToken token = (MultipleOperandsToken) value;

            for (Token operand : token.getOperands()) {
                if (wouldCreateCycle(meshName, operand)) {
                    System.out.println("return: true");
                    return true;
                }

            }
            System.out.println("return: false");
            return false;
        }
        throw new RuntimeException("token " + value.toString() + " has illegal type");
    }
}
