package com.sschudakov.tables.expression_parsing;

import org.junit.Test;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class ExpressionTreeTest {

    @Test
    public void evaluateTest() {

        String string = "(1+1)/21";
        Expression exception = new Expression(string);
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(exception);
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(lexicalAnalyzer);
        ExpressionTree expressionTree = new ExpressionTree(syntaxAnalyzer.expression());

//        expressionTree.outputTree();
        System.out.println(expressionTree.evaluate());
    }

}
