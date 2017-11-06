package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.expression_parsing.token.DefaultToken;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Semen Chudakov on31.10.2017.
 */

public class SyntaxAnalyzer {

    LexicalAnalyzer lexicalAnalyzer;
    Set<Expression> setOfExpressions;

    public SyntaxAnalyzer(Expression expression) {
        this.lexicalAnalyzer = new LexicalAnalyzer(expression);
        this.setOfExpressions = new HashSet<>();
    }

    public SyntaxAnalyzer(LexicalAnalyzer lexicalAnalyzer) {
        this.lexicalAnalyzer = lexicalAnalyzer;
        this.setOfExpressions = new HashSet<>();
    }

    public DefaultToken expression() {

        DefaultToken firstArgument;
        DefaultToken operation;

        firstArgument = inequationOperand();
        System.out.println("expression inequation: ");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.getToken();
        System.out.println("expression operation:");
        System.out.println(operation);

        while (operation.isLogicalOperator()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, inequationOperand());
            System.out.println("expression addendum:");
            System.out.println(firstArgument);
            operation = this.lexicalAnalyzer.getToken();
            System.out.println("expression operation:");
            System.out.println(operation);
        }
        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private DefaultToken inequationOperand() {

        DefaultToken firstArgument;
        DefaultToken operation;

        firstArgument = addendum();
        System.out.println("expression addendum:");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.getToken();
        System.out.println("expression operation:");
        System.out.println(operation);

        while (operation.isPlusOrMinus()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, addendum());
            System.out.println("expression addendum:");
            System.out.println(firstArgument);
            operation = this.lexicalAnalyzer.getToken();
            System.out.println("expression operation:");
            System.out.println(operation);
        }
        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private DefaultToken addendum() {

        DefaultToken firstArgument;
        DefaultToken operation;

        firstArgument = factor();
        System.out.println("addendum factor:");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.getToken();
        System.out.println("addendum operation:");
        System.out.println(operation);

        while (operation.isMultiplicationDivisionModulus()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, factor());
            System.out.println("adendum factor:");
            System.out.println(firstArgument);
            operation = this.lexicalAnalyzer.getToken();
            System.out.println("addendum operation:");
            System.out.println(operation);
        }

        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private DefaultToken factor() {

        DefaultToken firstArgument;
        DefaultToken operation;

        firstArgument = atom();
        System.out.println("factor atom:");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.getToken();
        System.out.println("factor operation:");
        System.out.println(operation);

        while (operation.isExponent()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, factor());
            System.out.println("factor atom:");
            System.out.println(firstArgument);
            operation = this.lexicalAnalyzer.getToken();
            System.out.println("factor operation:");
            System.out.println(operation);
        }

        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private DefaultToken atom() {

        DefaultToken token;
        DefaultToken nextToken;

        token = this.lexicalAnalyzer.getToken();
        System.out.println("atomic token:");
        System.out.println(token);

        if (token.isNumber()) {
            return ExpressionTree.makeTree(token);
        }

        if (token.isMeshName()) {
//            nextToken = this.lexicalAnalyzer.getToken();
//            if (nextToken.isEquationSign()) {
//                return ExpressionTree.makeTree(nextToken, ExpressionTree.makeTree(token), expression());
//            } else {
//                this.lexicalAnalyzer.giveBackToken();
            return ExpressionTree.makeTree(token);
//            }
        }
        if (token.isLRB()) {

            DefaultToken expression = expression();
            System.out.println("atom expression: ");
            System.out.println(expression);
            nextToken = this.lexicalAnalyzer.getToken();
            System.out.println("atomic next token:");
            System.out.println(nextToken);

            if (nextToken.isRRB()) {
                return ExpressionTree.makeTree(token, expression, nextToken);
            } else {
                throw new IllegalArgumentException("exception in atom: no RRB found");
            }
        }
        throw new IllegalArgumentException("exception in atom: no matches for the token:" + token + " cannot build a tree");
    }
}
