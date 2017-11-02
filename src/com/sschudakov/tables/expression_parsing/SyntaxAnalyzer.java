package com.sschudakov.tables.expression_parsing;

import java.util.HashSet;

/**
 * Created by Semen Chudakov on31.10.2017.
 */

public class SyntaxAnalyzer {

    LexicalAnalyzer lexicalAnalyzer;
    ExpressionTree tree;
    HashSet<Expression> setOfExpressions;

    public SyntaxAnalyzer(Expression expression) {
        this.lexicalAnalyzer = new LexicalAnalyzer(expression);
        this.tree = new ExpressionTree();
        this.setOfExpressions = new HashSet<>();
    }


    public Token expression() {

        Token firstArgument;
        Token operation;

        firstArgument = addendum();

        operation = lexicalAnalyzer.getToken();

        while (operation.isPlusOrMinus()) {
            firstArgument = tree.makeTree(operation, firstArgument, addendum());
            operation = lexicalAnalyzer.getToken();
        }
        this.lexicalAnalyzer.giveBackToken();
        return firstArgument;
    }

    public Token addendum() {

        Token firstArgument;
        Token operation;

        firstArgument = factor();
        operation = lexicalAnalyzer.getToken();

        while (operation.isMultiplicationDivisionModulus()) {
            firstArgument = tree.makeTree(operation, firstArgument, factor());
            operation = lexicalAnalyzer.getToken();
        }

        lexicalAnalyzer.giveBackToken();

        return firstArgument;
    }

    public Token factor() {

        Token arg1;
        Token op;

        arg1 = atom();
        op = lexicalAnalyzer.getToken();

        while (op.isExponent()) {
            arg1 = this.tree.makeTree(op, arg1, factor());
            op = this.lexicalAnalyzer.getToken();
        }

        lexicalAnalyzer.giveBackToken();

        return arg1;

    }

    public Token atom() {

        Token token;
        Token nextToken;

        //comments are not accepted
        //while ((token = lexicalAnalyzer->getToken())->isComment());

        token = lexicalAnalyzer.getToken();

        if (token.isNumber()) {
            return tree.makeTree(token);
        }

        if (token.isMeshName()) {
            //comments are not accepted
            //while ((nextToken = lexicalAnalyzer->getToken())->isComment());
            nextToken = lexicalAnalyzer.getToken();
            if (nextToken.isEquationSign()) {
                return tree.makeTree(nextToken, tree.makeTree(token), expression());
            } else {
                lexicalAnalyzer.giveBackToken();
                return tree.makeTree(token);
            }

        }
        if (token.isLRB()) {
            Token exp = expression();
            //comments are not accepted
            //while ((nextToken = lexicalAnalyzer->getToken())->isComment());
            nextToken = lexicalAnalyzer.getToken();
            if (nextToken.isRRB()) {
                return tree.makeTree(token, exp, nextToken);
            } else {
                throw new IllegalArgumentException("exeption in atom: no RRB found");
            }
        }
        throw new IllegalArgumentException("exeption in atom: no matches for the lexem - cannot build a tree");
    }

//    double calculateExpression() {
//
//        lexicalAnalyzer.emplace(tree.get_head());
//        lexicalAnalyzer.get_buffer()->fillBuffer();
//
//        if (lexicalAnalyzer.get_buffer()->isEmpty()){
//            throw new IllegalArgumentException("end of work");
//        }
//
//        lexicalAnalyzer.set_currentLexem(NULL);
//        lexicalAnalyzer.set_lastLexem(NULL);
//
//        tree.set_head(expression());
//        double result;
//        try {
//            result = tree.evaluate();
//        } catch (invalid_argument ia) {
//            string exceptionMassage = ia.what();
//            if (exceptionMassage != "token v cannot be used as a variable name") {
//                throw ia;
//            } else {
//                throw new IllegalArgumentException();
//            }
//        }
//        tree.set_previousValue(result);
//        return result;
//    }
}
