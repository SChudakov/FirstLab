package com.sschudakov.tables.table_view;

import com.sschudakov.abstract_factory.factories.views.TableFileView;
import com.sschudakov.exceptions.MeshHasNoValueException;
import com.sschudakov.gui.GBC;
import com.sschudakov.tables.expression_parsing.Expression;
import com.sschudakov.tables.expression_parsing.ExpressionTree;
import com.sschudakov.tables.expression_parsing.LexicalAnalyzer;
import com.sschudakov.tables.expression_parsing.SyntaxAnalyzer;
import com.sschudakov.tables.expression_parsing.tokens.Token;
import com.sschudakov.utils.ExceptionRenderer;
import com.sschudakov.utils.MessageRenderer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableViewManager {
    private JFrame frame = new JFrame("Table Viewer");
    private JPanel tablePanel = new JPanel();
    private JPanel labelsPanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();


    private JMenu fileMenu = new JMenu("File");
    private JMenu operationsMenu = new JMenu("Operation");

    private JMenuItem saveItem = new JMenuItem("save");
    private JMenuItem addRowItem = new JMenuItem("add row");
    private JMenuItem addColumnItem = new JMenuItem("add column");
    private JMenuItem removeRowItem = new JMenuItem("remove row");
    private JMenuItem removeColumnItem = new JMenuItem("remove column");


    private JTextField expressionTextFiled = new JTextField();
    private JTextField rowTextFiled = new JTextField();
    private JTextField columnTextField = new JTextField();

    private JTable table;
    private TableModel tableModel;
    private JScrollPane tableScrollPane;


    private TableFileView view;

    public TableViewManager(JTable table) {
        this.table = table;
        this.tableModel = (TableModel) table.getModel();
        this.tableScrollPane = new JScrollPane(table);
    }

    public void setView(TableFileView view) {
        this.view = view;
    }


    public void buildTableView() {

        setupFrame();

        setupPanels();

        setupMenuBar();

        addListeners();

        setupScrollPane();

        setupTextFields();

        setupTable();

        this.frame.setVisible(true);
    }

    private void setupFrame() {


        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        this.frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.frame.setLocationByPlatform(true);
        this.frame.setLayout(new GridBagLayout());
        this.frame.setSize(screenWidth * 8 / 10, screenHeight * 8 / 10);

        this.frame.add(this.tablePanel, new GBC(0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH));
        this.frame.add(this.labelsPanel, new GBC(0, 1, 1, 1, 0.2, 0.2, GridBagConstraints.BOTH));

        this.frame.setJMenuBar(this.menuBar);
    }

    private void setupPanels() {

        GridBagLayout tablePanelLayout = new GridBagLayout();

        this.tablePanel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.BLACK, 1, true), "Table"));
        this.tablePanel.setLayout(tablePanelLayout);
        this.tablePanel.add(this.tableScrollPane,
                new GBC(0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH));

        GridBagLayout labelsPanelLayout = new GridBagLayout();

        this.labelsPanel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.BLACK, 1, true), "Text Fields"));
        this.labelsPanel.setLayout(labelsPanelLayout);
        this.labelsPanel.add(this.rowTextFiled,
                new GBC(0, 0, 1, 1, 0.2, 0.2, GridBagConstraints.BOTH));
        this.labelsPanel.add(this.columnTextField,
                new GBC(1, 0, 1, 1, 0.2, 0.2, GridBagConstraints.BOTH));
        this.labelsPanel.add(this.expressionTextFiled,
                new GBC(2, 0, 1, 1, 0.8, 0.8, GridBagConstraints.BOTH));
    }

    private void setupMenuBar() {

        this.fileMenu.add(this.saveItem);

        this.operationsMenu.add(this.addRowItem);
        this.operationsMenu.add(this.addColumnItem);
        this.operationsMenu.add(this.removeRowItem);
        this.operationsMenu.add(this.removeColumnItem);

        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.operationsMenu);
    }

    private void addListeners() {

        this.frame.addWindowListener(new CloseOperationListener());

        this.saveItem.addActionListener(new SaveListener());

        this.addRowItem.addActionListener(new AddRowListener());
        this.addColumnItem.addActionListener(new AddColumnListener());
        this.removeRowItem.addActionListener(new RemoveRowListener());
        this.removeColumnItem.addActionListener(new RemoveColumnListener());

        this.table.addMouseListener(new MouseClickListener());
    }

    private void setupScrollPane() {
        this.tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void setupTextFields() {
        this.expressionTextFiled.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.BLACK, 1, true), "Expression"));
        this.rowTextFiled.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.BLACK, 1, true), "Row"));
        this.columnTextField.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.BLACK, 1, true), "Column"));
    }

    private void setupTable() {
        this.table.setAutoCreateRowSorter(true);
        this.table.setCellSelectionEnabled(true);
        this.tableModel.addTableModelListener(new TableListener());
        System.out.println(Arrays.toString(this.tableModel.getListeners(TableModelListener.class)));
    }


    public class TableListener implements TableModelListener {

        private LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        private SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(lexicalAnalyzer);
        private ExpressionTree expressionTree;

        public TableListener() {
            this.expressionTree = new ExpressionTree(tableModel);
        }

        @Override
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (e.getType() == TableModelEvent.UPDATE && row >= 0 && column >= 0) {

                System.out.println("row: " + row);
                System.out.println("column: " + column);
                System.out.println("type: " + e.getType());

                Object renewedValue = tableModel.getValueAt(row, column);

                if (renewedValue instanceof String) {
                    String expression = (String) tableModel.getValueAt(row, column);
                    if (!expression.equals("")) {
                        System.out.println("\nexpression: " + expression + "\n");
                        TableCell cell = new TableCell();
                        try {

                            this.lexicalAnalyzer.setExpression(new Expression(expression));
                            Token parsedExpression = this.syntaxAnalyzer.expression();

                            if (!this.expressionTree.wouldCreateCycle(
                                    table.getColumnName(column) + String.valueOf(row),
                                    parsedExpression)) {
                                this.expressionTree.setHead(parsedExpression);

                                Object value;
                                try {
                                    value = this.expressionTree.evaluate();
                                } catch (MeshHasNoValueException e1) {
                                    value = "";
                                }
                                cell.setValue(value.toString());
                                cell.setParsedExpression(parsedExpression);
                                cell.setExpression(expression);
                                table.setValueAt(cell, row, column);
                                renewValue();
                            } else {
                                MessageRenderer.renderMessage(frame, "This expression creates a cycle in table " +
                                        "cells expressions");
                            }

                        } catch (IllegalArgumentException e1) {
                            e1.printStackTrace();
                            ExceptionRenderer.renderException(frame, e1);
                        }
                    }
                }
            }
        }

        private void renewValue() {
            TableCell currentCell;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    if (tableModel.getValueAt(i, j) instanceof TableCell) {
                        currentCell = (TableCell) tableModel.getValueAt(i, j);
                        this.expressionTree.setHead(currentCell.getParsedExpression());
                        try {
                            currentCell.setValue(this.expressionTree.evaluate().toString());
                        } catch (Exception e) {
                            currentCell.setValue("");
                        }
                        tableModel.setValueAt(currentCell, i, j);
                    }
                }
            }
        }
    }


    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.save();
        }
    }

    class CloseOperationListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {

            System.out.println("Closer operation");

            if (view.close()) {
                System.out.println("table closed");
//                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                frame.setVisible(false);
            }
        }
    }

    class AddRowListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("action performed");
            tableModel.addRow(formEmptyRowData());
        }

        private Object[] formEmptyRowData() {
            Object[] result = new Object[tableModel.getColumnCount()];
            for (int i = 0; i < result.length; i++) {
                result[i] = new TableCell();
            }
            return result;
        }
    }

    class AddColumnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.addColumn(tableModel.getColumnName(tableModel.getColumnCount()));
        }
    }

    class RemoveRowListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] rows = table.getSelectedRows();
            for (int i = 0; i < rows.length; i++) {
                //to let removeRow work correctly
                tableModel.removeRow(rows[i] - i);
            }
        }
    }

    class RemoveColumnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] columns = table.getSelectedColumns();
            for (int i = 0; i < columns.length; i++) {
                //to let removeColumn work correctly
                tableModel.removeColumn(columns[i] - i);
            }
        }
    }


    class MouseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            int row = table.rowAtPoint(e.getPoint());
            int column = table.columnAtPoint(e.getPoint());

            rowTextFiled.setText(String.valueOf(row));
            columnTextField.setText(tableModel.getColumnName(column));
            if (tableModel.getValueAt(row, column) != null) {
                if (tableModel.getValueAt(row, column) instanceof TableCell) {
                    expressionTextFiled.setText(((TableCell) tableModel.getValueAt(row, column)).getExpression());
                }
                if (tableModel.getValueAt(row, column) instanceof String) {
                    expressionTextFiled.setText((tableModel.getValueAt(row, column)).toString());
                }
            } else {
                expressionTextFiled.setText("");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
