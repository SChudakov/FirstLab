package com.sschudakov;

import com.sschudakov.gui.GUIManager;
import com.sschudakov.operations.FileMerger;
import com.sschudakov.operations.HTMLParser;
import com.sschudakov.utils.SiteDownloader;
import com.sschudakov.utils.SubstringsFinder;


import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String FIRST_TXT = "D:\\Workspace.java\\FirstLab\\first.txt";
    private static final String SECOND_TXT = "D:\\Workspace.java\\FirstLab\\second.txt";
    private static final String RESULT_TXT = "D:\\Workspace.java\\FirstLab\\result.txt";
    private static final String FIRST_HTML = "D:\\Workspace.java\\FirstLab\\first.html";
    private static final String SECOND_HTML = "D:\\Workspace.java\\FirstLab\\second.html";
    private static final String RESULT_HTML = "D:\\Workspace.java\\FirstLab\\result.html";

    public static void main(String[] args) {

        GUIManager manager = new GUIManager();
        manager.buildGUI();




//        try {
//            System.out.println(new Scanner(new File("D:\\Workspace.java\\FirstLab\\first.html")).useDelimiter("\\Z").next());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        JFrame frame = new JFrame();
//        int result = JOptionPane.showConfirmDialog(frame, "Text");
//        frame.setVisible(true);
//
//        System.out.println(result);


//        SiteDownloader.downloadHTMLText("https://mail.google.com/mail/u/0/?tab=wm#inbox/15eb41ef2564c8d5?projector=1",
//                "D:\\Workspace.java\\FirstLab\\downloaded_site.txt");


//        testHTMLParser();

//        System.out.println(HTMLParser.isHTMLFileReference("D:\\wiki/Text_editor.html"));

//        String regularExpression = "href=\"[A-Za-z0-9 _/#$.?=:&;]+\"|href=[A-Za-z0-9 _/#$.c?=:&;]+[\\x00-\\x7F]";
//        String test = "t\" title=\"Edit this page\" href=\"D:w/index.php?title=Regular_expression&amp;action=edit.html\"/><link rel=\"apple-tou";
//        checkRegularExpression(test, regularExpression);

    }

    private static void testScannerFileReading(){
        //        Scanner scanner1 = null;
//        try {
//            scanner1 = new Scanner(new File("D:\\Workspace.java\\FirstLab\\first.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String file1content = scanner1.useDelimiter("\\Z").next();
//
//
//        Scanner scanner2 = null;
//        try {
//            scanner2 = new Scanner(new File("D:\\Workspace.java\\FirstLab\\first.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String file2content = scanner2.useDelimiter("\\Z").next();
//
//        System.out.println(file1content.equals(file2content));
    }


    private static void testSubstringsFinding() {

        System.out.println("\ntestSubstringsFinding\n");

        System.out.println("test 1");

        System.out.println(SubstringsFinder.findSubstrings("summer holidays", "hol").toString());

    }

    private static void testJTreeBuilderFormPath() {
//        DefaultMutableTreeNode files = new DefaultMutableTreeNode("files");
//        DefaultMutableTreeNode node = new DefaultMutableTreeNode("C:");
//        DefaultMutableTreeNode child = new DefaultMutableTreeNode("file1");
//        DefaultMutableTreeNode childChild = new DefaultMutableTreeNode("file2");
//
//        files.add(node);
//        node.add(child);
//        child.add(childChild);
//
//        System.out.println(formPath(childChild));
    }

    private static void testHTMLParser() {

        final String url = "https://en.wikipedia.org/wiki/Regular_expression";
        final String path = "D:\\Workspace.java\\FirstLab\\downloaded_site.txt";

//        String test = "<a dir=\"ltr\" href=\"https://en.wikipedia.org/w/index.php?title=Regular_expression&amp;oldid=798406284\">https://en.wikipedia.org/w/index.php?title=Regular_expression&amp;oldid=798406284</a>:";

        SiteDownloader.downloadHTMLText(url, path);
        for (String reference : HTMLParser.parseFile("https://en.wikipedia.org/wiki/Regular_expression")) {
            System.out.println(reference);
        }
//        checkRegularExpression(test, "href=\"[A-Za-z0-9 _/.?=:&;]*\"");

    }

    private static void testFileManagerFilesCompounding() {

        System.out.println("\ntestFileManagerFilesCompounding\n");

        FileMerger.mergeFiles(FIRST_TXT, SECOND_TXT, RESULT_TXT);
        FileMerger.mergeFiles(FIRST_HTML, SECOND_HTML, RESULT_HTML);

    }

    private static void checkRegularExpression(String string, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
