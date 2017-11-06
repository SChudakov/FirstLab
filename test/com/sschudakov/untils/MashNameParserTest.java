package com.sschudakov.untils;

import com.sschudakov.utils.MeshNameParser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 06.11.2017.
 */
public class MashNameParserTest {

    @Test
    public void parseCorrectRowTest() {
        String meshName = "A14513531";
        Assert.assertEquals(14513531, MeshNameParser.parseRow(meshName));

    }

    @Test
    public void parseNoRowTest() {
        String meshName = "A";

        try {
            MeshNameParser.parseRow(meshName);
            throw new AssertionError("an exception should have been thrown");
        } catch (Exception e) {
            Assert.assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    public void parseCorrectColumnTest() {
        String meshName = "A14513531";
        Assert.assertEquals(1, MeshNameParser.parseColumn(meshName));
    }
}
