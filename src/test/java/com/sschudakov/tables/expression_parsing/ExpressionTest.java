package test.java.com.sschudakov.tables.expression_parsing;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 02.11.2017.
 */
public class ExpressionTest {

    @Test
    public void readCharacterTest() {
        String string = "1+1";
        Expression expression = new Expression(string);
        StringBuffer readCharacters = new StringBuffer("");

        char character = expression.readCharacter();
        while (character != Expression.EXPRESSION_END) {
            System.out.println("read character: " + character);
            readCharacters.append(character);
            character = expression.readCharacter();
        }

        Assert.assertEquals(string, readCharacters.toString());
    }

    @Test
    public void giveBackCharacterTest() {
        String string = "1+1";
        Expression expression = new Expression(string);
        StringBuffer readCharacters = new StringBuffer("");
        StringBuffer stringBuffer = new StringBuffer(string);

        for (int i = 0; i < string.length(); i++) {
            System.out.println(expression.readCharacter());
        }
        char character = expression.readCharacter();
        System.out.println("character:" + character);

        expression.giveBackCharacter();
        System.out.println("after giving back: " + expression.readCharacter());

        System.out.println(character);

//        Assert.assertEquals(stringBuffer.reverse().toString(), readCharacters.toString());
    }

    @Test
    public void isEmptyTest() {
        String string = "1+1";
        Expression expression = new Expression(string);
        for (int i = 0; i < 4; i++) {
            expression.readCharacter();
        }
        Assert.assertEquals(true, expression.isEmpty());
    }

}
