package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.expression_parsing.tokens.Token;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class ExpressionTreeTest {

    @Test
    public void evaluateTest() {

        String string = "mmax(1,2^10,3,4,5)";
        Expression expression = new Expression(string);
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(expression);
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(lexicalAnalyzer);
        Token tree = syntaxAnalyzer.expression();
        ExpressionTree expressionTree = new ExpressionTree(tree);
        ExpressionTree.normalize(tree);
        expressionTree.outputTree();
        System.out.println(expressionTree.evaluate());
    }

}
