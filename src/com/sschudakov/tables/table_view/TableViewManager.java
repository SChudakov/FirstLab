package com.sschudakov.tables.table_view;

import com.sschudakov.gui.GBC;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableViewManager {
    private JFrame frame = new JFrame("Table Viewer");
    private JPanel panel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();

    private JTable table;
    private JScrollPane tableScrollPane;

    public TableViewManager(JTable table) {
        this.table = table;
        this.tableScrollPane = new JScrollPane(table);
    }

    public void builTableView() {

        setupFrame();

        setupPanels();

        setupScrollPane();

        setUpTable();

        this.frame.setVisible(true);
    }

    private void setupFrame() {


        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationByPlatform(true);
        this.frame.setLayout(new GridBagLayout());
        this.frame.setSize(screenWidth * 8 / 10, screenHeight * 8 / 10);

        this.frame.add(this.panel, new GBC(0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH));
    }

    private void setupPanels() {

        GridBagLayout leftPanelLayout = new GridBagLayout();

        this.panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY, 1, true), "Panel"));
        this.panel.setLayout(leftPanelLayout);
        this.panel.add(this.tableScrollPane,
                new GBC(0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH));
    }


    private void setupScrollPane() {
        this.tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void setUpTable() {
        this.table.setAutoCreateRowSorter(true);
        this.table.setCellSelectionEnabled(true);
    }
}
