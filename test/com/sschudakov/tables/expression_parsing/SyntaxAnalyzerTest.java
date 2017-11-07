package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.expression_parsing.tokens.DefaultToken;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 03.11.2017.
 */
public class SyntaxAnalyzerTest {

    @Test(timeout = 200)
    public void expressionTest(){
        Expression expression = new Expression("1>1!=2>=3=4>(1+1)");
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(expression);
        DefaultToken token = syntaxAnalyzer.expression();
        ExpressionTree.normalize(token);
        ExpressionTree.outputTree(token);
    }

}
