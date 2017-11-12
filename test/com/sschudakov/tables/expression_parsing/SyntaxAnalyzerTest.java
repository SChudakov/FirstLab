package com.sschudakov.tables.expression_parsing;

import com.sschudakov.tables.expression_parsing.tokens.DefaultToken;
import com.sschudakov.tables.expression_parsing.tokens.Token;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 03.11.2017.
 */
public class SyntaxAnalyzerTest {

    //1>1*(2+1^3-4div1)

    @Test(timeout = 200)
    public void expressionTest(){
        Expression expression = new Expression("min(1,2^3)");
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(expression);
        Token token = syntaxAnalyzer.expression();
        ExpressionTree.normalize(token);
        ExpressionTree.outputTree(token);
    }

}
