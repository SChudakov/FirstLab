package com.sschudakov.gui;

import com.sschudakov.utils.PathFormer;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.NoSuchElementException;

/**
 * Created by Semen Chudakov on 15.09.2017.
 */
public class JTreeBuilder {

    private JTree tree;
    private DefaultMutableTreeNode top;


    public JTreeBuilder(JTree tree, DefaultMutableTreeNode top) {
        this.tree = tree;
        this.top = top;
    }

    public void setupTree() {

        DefaultMutableTreeNode cNode = new DefaultMutableTreeNode("C:\\");
        DefaultMutableTreeNode dNode = new DefaultMutableTreeNode("D:\\");
        addFiles(cNode);
        addFiles(dNode);

        this.top.add(cNode);
        this.top.add(dNode);
        this.tree.expandPath(new TreePath(this.tree.getModel().getRoot()));
        this.tree.setRootVisible(false);
    }

    private void addFiles(DefaultMutableTreeNode node) {

        String path = PathFormer.formPath(node);
        File pathFile = new File(path);

        if (pathFile.isDirectory()) {
            File[] files = pathFile.listFiles();

            if (files != null) {
                for (File file : files) {
                    node.add(new DefaultMutableTreeNode(file.getName()));
                }
            }
        }
    }

    class SelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            File nodeFile = new File(PathFormer.formPath(node));

            if (nodeFile.isDirectory()) {

                try {
                    DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getFirstChild();

                    while (child != null) {
                        addFiles(child);
                        child = (DefaultMutableTreeNode) node.getChildAfter(child);
                    }
                } catch (NoSuchElementException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
