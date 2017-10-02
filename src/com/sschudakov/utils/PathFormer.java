package com.sschudakov.utils;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Semen Chudakov on 16.09.2017.
 */
public class PathFormer {

    public static String formPath(DefaultMutableTreeNode node){

        StringBuilder path = new StringBuilder("");

        DefaultMutableTreeNode currentNode = node;

        path.append((String) node.getUserObject());

        currentNode = (DefaultMutableTreeNode) currentNode.getParent();

        if (currentNode != null) {
            while (!currentNode.getUserObject().equals("files")) {
                path.insert(0, currentNode.getUserObject() + "\\");
                currentNode = (DefaultMutableTreeNode) currentNode.getParent();
            }
        }

        return path.toString();
    }
}
