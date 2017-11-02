package com.sschudakov.expression_parsing;

import com.sschudakov.tables.expression_parsing.Expression;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 02.11.2017.
 */
public class ExpressionTest {

    @Test
    public void readCharacterTest() {
        String string = "abcdefghijklmnoprstuxyz><+-=!";
        Expression expression = new Expression(string);
        StringBuffer readCharacters = new StringBuffer("");

        char character = expression.readCharacter();

        while (character != Expression.EXPRESSION_END) {
            readCharacters.append(character);
            character = expression.readCharacter();
        }

        Assert.assertEquals(string, readCharacters.toString());
    }

    @Test
    public void givebackCharacterTest() {
        String string = "abc";
        Expression expression = new Expression(string);
        StringBuffer readCharacters = new StringBuffer("");
        StringBuffer stringBuffer = new StringBuffer(string);

        for (int i = 0; i < string.length(); i++) {
            expression.readCharacter();
        }
        char character = expression.readCharacter();
        System.out.println(character);

        expression.giveBackCharacter();
        expression.giveBackCharacter();
        character = expression.readCharacter();

//        while (character != string.charAt(0)) {
//            System.out.println(character);
//            expression.giveBackCharacter();
//        }
        System.out.println(character);

//        Assert.assertEquals(stringBuffer.reverse().toString(), readCharacters.toString());
    }

}
