package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.expression_parsing.tokens.DefaultToken;
import com.sschudakov.tables.expression_parsing.tokens.Token;

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

    public Token expression() {

        Token firstArgument;
        DefaultToken operation;

        firstArgument = inequationOperand();
        System.out.println("expression inequation: ");
        System.out.println(firstArgument);
        operation = (DefaultToken) this.lexicalAnalyzer.readToken();
        System.out.println("expression operation:");
        System.out.println(operation);

        while (operation.isLogicalOperator()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, inequationOperand());
            System.out.println("expression addendum:");
            System.out.println(firstArgument);
            operation = (DefaultToken) this.lexicalAnalyzer.readToken();
            System.out.println("expression operation:");
            System.out.println(operation);
        }

        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private Token inequationOperand() {

        Token firstArgument;
        DefaultToken operation;

        firstArgument = addendum();
        System.out.println("expression addendum:");
        System.out.println(firstArgument);
        operation = (DefaultToken) this.lexicalAnalyzer.readToken();
        System.out.println("expression operation:");
        System.out.println(operation);

        while (operation.isPlusOrMinus()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, addendum());
            System.out.println("expression addendum:");
            System.out.println(firstArgument);
            operation = (DefaultToken) this.lexicalAnalyzer.readToken();
            System.out.println("expression operation:");
            System.out.println(operation);
        }
        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private Token addendum() {

        Token firstArgument;
        DefaultToken operation;

        firstArgument = factor();
        System.out.println("addendum factor:");
        System.out.println(firstArgument);
        operation = (DefaultToken) this.lexicalAnalyzer.readToken();
        System.out.println("addendum operation:");
        System.out.println(operation);

        while (operation.isMultiplicationDivisionModulus()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, factor());
            System.out.println("adendum factor:");
            System.out.println(firstArgument);
            operation = (DefaultToken) this.lexicalAnalyzer.readToken();
            System.out.println("addendum operation:");
            System.out.println(operation);
        }

        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private Token factor() {

        Token firstArgument;
        DefaultToken operation;

        firstArgument = atom();
        System.out.println("factor atom:");
        System.out.println(firstArgument);
        operation = (DefaultToken) this.lexicalAnalyzer.readToken();
        System.out.println("factor operation:");
        System.out.println(operation);

        while (operation.isExponent()) {
            firstArgument = ExpressionTree.makeTree(operation, firstArgument, factor());
            System.out.println("factor atom:");
            System.out.println(firstArgument);
            operation = (DefaultToken) this.lexicalAnalyzer.readToken();
            System.out.println("factor operation:");
            System.out.println(operation);
        }

        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private Token atom() {

        Token token;
        Token nextToken;

        token = this.lexicalAnalyzer.readToken();
        System.out.println("atomic token:");
        System.out.println(token);

        if (token.isNumber()) {
            return ExpressionTree.makeTree((DefaultToken) token);
        }

        if (token.isMeshName()) {
//            nextToken = this.lexicalAnalyzer.readToken();
//            if (nextToken.isEquationSign()) {
//                return ExpressionTree.makeTree(nextToken, ExpressionTree.makeTree(token), expression());
//            } else {
//                this.lexicalAnalyzer.giveBackToken();
            return ExpressionTree.makeTree((DefaultToken) token);
//            }
        }
        if (token.isLeftParenthesis()) {

            Token expression = expression();
            System.out.println("atom expression: ");
            System.out.println(expression);
            nextToken = this.lexicalAnalyzer.readToken();
            System.out.println("atomic next token:");
            System.out.println(nextToken);

            if (nextToken.isRightParenthesis()) {
                return ExpressionTree.makeTree((DefaultToken) token, expression, nextToken);
            } else {
                throw new IllegalArgumentException("exception in atom: no RRB found");
            }
        }

        if(token.isMultipleOperandsToken()){

        }


        throw new IllegalArgumentException("exception in atom: no matches for the token:" + token + " cannot build a tree");
    }
}
