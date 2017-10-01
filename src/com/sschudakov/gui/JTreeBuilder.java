package com.sschudakov.gui;

import com.sschudakov.utils.PathFormer;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.io.File;

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
        this.top.add(new DefaultMutableTreeNode("C:"));
        this.top.add(new DefaultMutableTreeNode("D:"));
        this.tree.expandPath(new TreePath(this.tree.getModel().getRoot()));
        this.tree.setRootVisible(false);
    }

    class SelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            File nodeFile;

            if (node.getUserObject().equals("D:")) {
                nodeFile = new File("D:\\");
            } else {
                nodeFile = new File(PathFormer.formPath(node));
            }

            if (nodeFile.isDirectory()) {

                File[] listFiles = nodeFile.listFiles();

                if (listFiles != null) {
                    for (File file : listFiles) {
                        node.add(new DefaultMutableTreeNode(file.getName()));
                    }
                }
            }
        }

    }
}
