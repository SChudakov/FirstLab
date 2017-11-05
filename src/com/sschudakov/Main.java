package com.sschudakov;

import com.sschudakov.gui.GUIManager;
import com.sschudakov.operations.FileMerger;
import com.sschudakov.operations.HTMLParser;
import com.sschudakov.tables.expression_parsing.Expression;
import com.sschudakov.tables.expression_parsing.ExpressionTree;
import com.sschudakov.tables.expression_parsing.SyntaxAnalyzer;
import com.sschudakov.tables.expression_parsing.Token;
import com.sschudakov.tables.table_view.TableViewManager;
import com.sschudakov.utils.SiteDownloader;
import com.sschudakov.utils.SimilarWordsFinder;


import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

//        GUIManager manager = new GUIManager();
//        manager.buildGUI();

        String[] columnsNames = new String[]{"Name1", "Name2", "Name3"};
        Object[][] cells = new Object[][]{
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
        };


        JTable table = new JTable(cells, columnsNames);
        TableViewManager tableViewManager = new TableViewManager(table);
        tableViewManager.builTableView();
    }
}
