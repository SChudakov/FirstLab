package com.sschudakov.expression_parsing;

import com.sschudakov.tables.expression_parsing.Expression;
import com.sschudakov.tables.expression_parsing.ExpressionTree;
import com.sschudakov.tables.expression_parsing.SyntaxAnalyzer;
import com.sschudakov.tables.expression_parsing.Token;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 03.11.2017.
 */
public class SyntaxAnalyzerTest {

    @Test(timeout = 200)
    public void expressionTest(){
        Expression expression = new Expression("1>1!=2>=3=4>(1+1)");
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(expression);
        Token token = syntaxAnalyzer.expression();
        ExpressionTree.normalize(token);
        ExpressionTree.outputTree(token);
    }

}
