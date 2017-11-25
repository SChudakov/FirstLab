package com.sschudakov.xml;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sschudakov.xml.parsers.TestXmlParser;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class MyErrorHandler implements ErrorHandler {

	private Logger logger;

	public MyErrorHandler(String log) throws IOException {
		// создание регистратора ошибок
		logger = Logger.getLogger(TestXmlParser.class.getName());
		// установка файла и формата вывода ошибок
		FileHandler fh = new FileHandler(log);
		logger.addHandler(fh);
	}

	public void warning(SAXParseException e) {
		logger.warning(getLineAddress(e) + "-" + e.getMessage());
	}

	public void error(SAXParseException e) {
		logger.log(Level.SEVERE, getLineAddress(e) + " - " + e.getMessage());
	}

	public void fatalError(SAXParseException e) {
		logger.log(Level.SEVERE, getLineAddress(e) + " - " + e.getMessage());
	}

	private String getLineAddress(SAXParseException e) {
		return e.getLineNumber() + " : " + e.getColumnNumber();
	}

}
