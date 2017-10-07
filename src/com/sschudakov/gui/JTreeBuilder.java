package com.sschudakov.gui;

import com.sschudakov.utils.PathFormer;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.NoSuchElementException;

/**
 * Created by Semen Chudakov on 15.09.2017.
 */
public class JTreeBuilder {

    private JTree tree;
    private DefaultMutableTreeNode top;
    private DefaultTreeModel model;

    private ImageIcon fileIcon = new ImageIcon("D:\\Workspace.java\\FirstLab\\icons\\file_icon.png");
    private ImageIcon directoryIcon = new ImageIcon("D:\\Workspace.java\\FirstLab\\icons\\directory_icon.ico");


    public JTreeBuilder(JTree tree, DefaultMutableTreeNode top) {
        this.tree = tree;
        this.top = top;
        this.model = (DefaultTreeModel) this.tree.getModel();
    }

    public void setupTree() {

        this.model.setAsksAllowsChildren(true);

        DefaultMutableTreeNode cNode = new DefaultMutableTreeNode("C:\\");
        DefaultMutableTreeNode dNode = new DefaultMutableTreeNode("D:\\");

        addFiles(cNode);
        addFiles(dNode);

        this.top.add(cNode);
        this.top.add(dNode);
        this.tree.expandPath(new TreePath(this.tree.getModel().getRoot()));
        this.tree.setRootVisible(false);
    }

    public void reload() {
        this.model.reload();
    }

    public void reload(DefaultMutableTreeNode node) {
        this.model.reload(node);
    }

    public void insertNodeInto(DefaultMutableTreeNode parent, DefaultMutableTreeNode son) {
        setAllowsChildren(parent, son);
        this.model.insertNodeInto(son, parent, 0);
    }

    public void removeNodeFromParent(DefaultMutableTreeNode node) {
        this.model.removeNodeFromParent(node);
    }


    private void addFiles(DefaultMutableTreeNode node) {

        String path = PathFormer.formPath(node);
        File pathFile = new File(path);

        if (pathFile.isDirectory()) {
            File[] files = pathFile.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (!hasChild(node, file.getName())) {
                        DefaultMutableTreeNode child = new DefaultMutableTreeNode(file.getName());
                        setAllowsChildren(node, child);
                        node.add(child);
                    }
                }
            }
        }
    }

    private boolean isFileNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode son) {
        String path = PathFormer.formPath(parent) + "\\" + son.getUserObject().toString();
        File file = new File(path);
        return file.isFile();
    }

    private boolean isDirectoryNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode son) {
        String path = PathFormer.formPath(parent) + "\\" + son.getUserObject().toString();
        File file = new File(path);
        return file.isDirectory();
    }

    private void setAllowsChildren(DefaultMutableTreeNode parent, DefaultMutableTreeNode son) {
        if (isFileNode(parent, son)) {
            son.setAllowsChildren(false);
        } else {
            if (isDirectoryNode(parent, son)) {
                son.setAllowsChildren(true);
            } else {
                System.out.println("There is no file or directory: " + PathFormer.formPath(son));
            }
        }
    }


    private boolean hasChild(DefaultMutableTreeNode parent, String userObject) {
        int numOfChildren = parent.getChildCount();
        for (int i = 0; i < numOfChildren; i++) {
            DefaultMutableTreeNode currentChild = (DefaultMutableTreeNode) parent.getChildAt(i);
            if (currentChild.getUserObject().equals(userObject)) {
                return true;
            }
        }
        return false;
    }


    class SelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            try {
                String path = PathFormer.formPath(node);

                File nodeFile = new File(path);

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
            } catch (Exception e1) {
                /*NOP*/
            }
        }
    }
}
