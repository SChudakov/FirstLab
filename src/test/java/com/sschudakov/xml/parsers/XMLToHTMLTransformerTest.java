package test.java.com.sschudakov.xml.parsers;

import org.junit.Test;

import javax.xml.transform.TransformerException;

/**
 * Created by Semen Chudakov on 01.12.2017.
 */
public class XMLToHTMLTransformerTest {

    private static final String XML_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";
    private static final String XSL_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xsl";
    private static final String HTML_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.html";

    @Test
    public void transformTest() {
        try {
            XMLToHTMLTransformer.transform(XML_FILE, XSL_FILE, HTML_FILE);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
