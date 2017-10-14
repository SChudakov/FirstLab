package com.sschudakov.gui;

import com.sschudakov.utils.NodeFormer;
import com.sschudakov.utils.PathFormer;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Semen Chudakov on 15.09.2017.
 */
public class JTreeBuilder {

    private JTree tree;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;

    private ImageIcon fileIcon = new ImageIcon("D:\\Workspace.java\\FirstLab\\icons\\file_icon.png");
    private ImageIcon directoryIcon = new ImageIcon("D:\\Workspace.java\\FirstLab\\icons\\directory_icon.ico");


    public JTreeBuilder(JTree tree, DefaultMutableTreeNode root) {
        this.tree = tree;
        this.root = root;
        this.model = (DefaultTreeModel) this.tree.getModel();
    }

    public void setupTree() {

        this.model.setAsksAllowsChildren(true);

        DefaultMutableTreeNode cNode = new DefaultMutableTreeNode("C:\\");
        DefaultMutableTreeNode dNode = new DefaultMutableTreeNode("D:\\");

        addFiles(cNode);
        addFiles(dNode);

        this.root.add(cNode);
        this.root.add(dNode);
        this.tree.expandPath(new TreePath(this.tree.getModel().getRoot()));
        this.tree.setRootVisible(false);
    }


    public void reload() {
        this.model.reload();
    }

    public void reload(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode nodeInThisTree = findNode(node);
        if (nodeInThisTree != null) {
            this.model.reload(node);
        }
    }

    public void addNode(String node) {
        Path nodePath = Paths.get(node);
        if (nodePath.getNameCount() >= 2) {
            Path parentPath = nodePath.getParent();
            DefaultMutableTreeNode parentInThisTree = findNode(NodeFormer.formNode(parentPath.toString()));
            if (parentInThisTree != null) {
                System.out.println("found node: " + parentInThisTree.getUserObject());
                DefaultMutableTreeNode son = new DefaultMutableTreeNode(nodePath.getName(nodePath.getNameCount() - 1).toString());
                setAllowsChildren(parentInThisTree, son);
                if (!hasChild(parentInThisTree, (String) son.getUserObject())) {
                    this.model.insertNodeInto(son, parentInThisTree, 0);
                }
            } else {
                System.out.println("parent in this tree is null");
            }
        } else {
            System.out.println("node count  < 2");
        }
    }

    public void addNode(DefaultMutableTreeNode node) {
        Path nodePath = Paths.get((String) node.getUserObject());
        if (nodePath.getNameCount() >= 2) {
            Path parentPath = nodePath.getName(nodePath.getNameCount() - 2);
            DefaultMutableTreeNode parentInThisTree = findNode(new DefaultMutableTreeNode(parentPath.toString()));
            if (parentInThisTree != null) {
                System.out.println("found node: " + parentInThisTree.getUserObject());
                if (!hasChild(parentInThisTree, (String) node.getUserObject())) {
                    this.model.insertNodeInto(node, parentInThisTree, 0);
                }
            } else {
                System.out.println("parent in this tree is null");
            }
        } else {
            System.out.println("node count  < 2");
        }
    }

    public void insertNodeInto(DefaultMutableTreeNode parent, DefaultMutableTreeNode son) {
        setAllowsChildren(parent, son);
        DefaultMutableTreeNode parentInThisTree = findNode(parent);
        if (parentInThisTree != null) {
            System.out.println("found node: " + parentInThisTree.getUserObject());
            if (!hasChild(parentInThisTree, (String) son.getUserObject())) {
                System.out.println("has no");
                this.model.insertNodeInto(son, parentInThisTree, 0);
            } else {
                System.out.println("has already");
            }
        } else {
            System.out.println("parent in this tree is null");
        }
    }

    public void removeNodeFromParent(String path){
        removeNodeFromParent(NodeFormer.formNode(path));
    }

    public void removeNodeFromParent(DefaultMutableTreeNode node) {

        DefaultMutableTreeNode nodeIntThisTree = findNode(node);

        if (nodeIntThisTree != null) {
            System.out.println("found node: " + nodeIntThisTree.getUserObject().toString());
            this.model.removeNodeFromParent(nodeIntThisTree);
        } else {
            System.out.println("node in this tree is null");
        }
    }

    public void changeName(DefaultMutableTreeNode node, String newName) {
        DefaultMutableTreeNode nodeInThisTree = findNode(node);
        if (nodeInThisTree != null) {
            System.out.println("found node: " + nodeInThisTree.getUserObject().toString());
            this.model.valueForPathChanged(new TreePath(nodeInThisTree), newName);
        } else {
            System.out.println("node in this tree is null");
        }
    }


    public  DefaultMutableTreeNode findNode(DefaultMutableTreeNode node) {

        ArrayList<DefaultMutableTreeNode> nodePathParts = new ArrayList<>();

        DefaultMutableTreeNode currentNodePathPart = node;

        while (!currentNodePathPart.getUserObject().equals("files")) {
            nodePathParts.add(0, currentNodePathPart);
            currentNodePathPart = (DefaultMutableTreeNode) currentNodePathPart.getParent();
        }
        System.out.println(nodePathParts.toString());

        DefaultMutableTreeNode iterator = this.root;

        for (DefaultMutableTreeNode nodePathPart : nodePathParts) {
            System.out.println(iterator);
            iterator = findNode(iterator, (String) nodePathPart.getUserObject());
            if (iterator == null) {
                return null;
            }
        }

        return iterator;
    }

    private DefaultMutableTreeNode findNode(DefaultMutableTreeNode parent, String userObject) {
        int childCount = parent.getChildCount();

        for(int i = 0; i < childCount; i++){
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent.getChildAt(i);
            if(child.getUserObject().equals(userObject)){
                return child;
            }
        }

        return null;
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

            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            try {
                String path = PathFormer.formPath(selectedNode);

                File nodeFile = new File(path);

                if (nodeFile.isDirectory()) {

                    try {

                        int childCount = selectedNode.getChildCount();

                        for (int j = 0; j < childCount; j++) {
                            addFiles((DefaultMutableTreeNode) selectedNode.getChildAt(j));
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
