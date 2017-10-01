package com.sschudakov.operations;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Semen Chudakov on 09.09.2017.
 */
public class HTMLParser {

    private static final String REGULAR_EXPRESSION_FOR_PARSING_TEXT = "href=\"[A-Za-z0-9 _/#$.?=:&;]+\"|href=[A-Za-z0-9 _/#$.?=:&;]+[\\x00-\\x7F]";
    private static final String REGULAR_EXPRESSION_FOR_PARSING_REFERENCE = "\"[A-Za-z0-9 _/.?=:&;]+\"|=[A-Za-z0-9 _/.?=:&;]+[\\x00-\\x7F]";

    public static List<String> parseFile(String path) {

        List<String> result = new LinkedList<>();

        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {
                result.addAll(findReferences(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static List<String> findReferences(String line) {

        List<String> references = new LinkedList();

        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_FOR_PARSING_TEXT);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {

            String processedReference = processPath(matcher.group());
            System.out.println("processed string: " + processedReference);

            if (isHTMLFileReference(processedReference)) {
                references.add(processedReference);
            }
        }

        return references;
    }

    private static String processPath(String path) {

        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_FOR_PARSING_REFERENCE);
        Matcher matcher = pattern.matcher(path);

        if (matcher.find()) {
            return matcher.group().substring(1, matcher.group().length() - 1);
        }

        throw new IllegalArgumentException("string: " + path + " contain no reference");
    }

    public static boolean isHTMLFileReference(String path) {
//        String substring = reference.substring(reference.length() - 5, reference.length());
//        System.out.println(substring);
//        return substring.equals(".html") && containsDiskSpecification(reference);
        //TODO: find a way to put \ to regular expression
        return true;
    }

    public static boolean containsDiskSpecification(String reference) {
        String substring = reference.substring(0, 3);
        System.out.println(substring);
        return substring.equals("C:\\") || substring.equals("D:\\")
                || substring.equals("c:\\") || substring.equals("d:\\");
    }
}
