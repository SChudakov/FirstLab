package com.sschudakov.tables.expression_parsing;

import com.sschudakov.exceptions.IllegalSyntaxException;
import com.sschudakov.tables.expression_parsing.tokens.DefaultToken;
import com.sschudakov.tables.expression_parsing.tokens.MultipleOperandsToken;
import com.sschudakov.tables.expression_parsing.tokens.Token;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Semen Chudakov on31.10.2017.
 */

public class SyntaxAnalyzer {

    LexicalAnalyzer lexicalAnalyzer;
    Set<Expression> setOfExpressions;

    public SyntaxAnalyzer(Expression expression, LexicalAnalyzerMode mode) {
        this.lexicalAnalyzer = new LexicalAnalyzer(expression, mode);
        this.setOfExpressions = new HashSet<>();
    }

    public SyntaxAnalyzer(LexicalAnalyzer lexicalAnalyzer) {
        this.lexicalAnalyzer = lexicalAnalyzer;
        this.setOfExpressions = new HashSet<>();
    }

    public Token expression() {

        Token firstArgument;
        Token operation;

        firstArgument = inequationOperand();
        System.out.println("expression inequation: ");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.readToken();
        System.out.println("expression operation:");
        System.out.println(operation);

        while (operation.isLogicalOperator()) {
            firstArgument = ExpressionTree.makeTree((DefaultToken) operation, firstArgument, inequationOperand());
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
        Token operation;

        firstArgument = addendum();
        System.out.println("inequation addendum:");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.readToken();
        System.out.println("expression operation:");
        System.out.println(operation);

        while (operation.isPlusOrMinus()) {
            firstArgument = ExpressionTree.makeTree((DefaultToken) operation, firstArgument, addendum());
            System.out.println("expression addendum:");
            System.out.println(firstArgument);
            operation = this.lexicalAnalyzer.readToken();
            System.out.println("expression operation:");
            System.out.println(operation);
        }
        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private Token addendum() {

        Token firstArgument;
        Token operation;

        firstArgument = factor();
        System.out.println("addendum factor:");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.readToken();
        System.out.println("addendum operation:");
        System.out.println(operation);

        while (operation.isMultiplicationDivisionModulusIntegerDivision()) {
            firstArgument = ExpressionTree.makeTree((DefaultToken) operation, firstArgument, factor());
            System.out.println("adendum factor:");
            System.out.println(firstArgument);
            operation = this.lexicalAnalyzer.readToken();
            System.out.println("addendum operation:");
            System.out.println(operation);
        }

        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    private Token factor() {

        Token firstArgument;
        Token operation;

        firstArgument = atom();
        System.out.println("factor atom:");
        System.out.println(firstArgument);
        operation = this.lexicalAnalyzer.readToken();
        System.out.println("factor operation:");
        System.out.println(operation);

        while (operation.isExponent()) {
            firstArgument = ExpressionTree.makeTree((DefaultToken) operation, firstArgument, factor());
            System.out.println("factor atom:");
            System.out.println(firstArgument);
            operation = this.lexicalAnalyzer.readToken();
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
            return ExpressionTree.makeTree((DefaultToken) token);
        }

        if (token.isLeftParenthesis()) {

            DefaultToken expression = (DefaultToken) expression();
            System.out.println("atom expression: ");
            System.out.println(expression);
            nextToken = this.lexicalAnalyzer.readToken();
            System.out.println("atomic next token:");
            System.out.println(nextToken);

            if (nextToken.isRightParenthesis()) {
                return ExpressionTree.makeTree((DefaultToken) token, expression, nextToken);
            } else {

                System.out.println("\nnext token: " + nextToken + "\n");
                throw new IllegalArgumentException("exception in atom: no right parenthesis found");
            }
        }

        if(token.isMultipleOperandsToken()){
            System.out.println("\nis multiple operands token\n");
            MultipleOperandsToken castedToken = (MultipleOperandsToken) token;
            nextToken = this.lexicalAnalyzer.readToken();

            if (nextToken.isLeftParenthesis()) {

                while (!nextToken.isRightParenthesis()) {
                    castedToken.addOperand(expression());
                    nextToken = this.lexicalAnalyzer.readToken();
                }
                //there is anyway one extra token
                return castedToken;

            } else {
                throw new IllegalSyntaxException("illegal syntax: mmax and mmin operations should be followed by a left parenthesis");
            }
        }

        if (token.isMaxMin()) {
            System.out.println("\nis min max\n");

            MultipleOperandsToken castedToken = (MultipleOperandsToken) token;
            nextToken = this.lexicalAnalyzer.readToken();

            if (nextToken.isLeftParenthesis()) {
                int numOfArguments = 0;
                while (!nextToken.isRightParenthesis()) {
                    castedToken.addOperand(expression());
                    nextToken = this.lexicalAnalyzer.readToken();
                    numOfArguments++;
                }

                if(numOfArguments != 2){
                    throw new IllegalSyntaxException("operations min and max can only have 2 parameters");
                }

                //there is anyway one extra token
                return castedToken;

            } else {
                throw new IllegalSyntaxException("illegal syntax: max and min operations should be followed by a left parenthesis");
            }
        }


        throw new IllegalSyntaxException("token: " + token + " is used wrongly");
    }
}
