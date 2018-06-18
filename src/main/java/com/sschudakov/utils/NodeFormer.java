package com.sschudakov.utils;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Semen Chudakov on 08.10.2017.
 */
public class NodeFormer {
    public static DefaultMutableTreeNode formNode(String path) {
        String[] pathParts = path.split("\\\\");
        if (pathParts.length == 0) {
            System.out.println("illegal path: " + path);
        }
        if (pathParts.length == 1) {
            return new DefaultMutableTreeNode(path);
        }

        DefaultMutableTreeNode parent = new DefaultMutableTreeNode("files");
        DefaultMutableTreeNode child;
        for (int i = 0; i < pathParts.length; i++) {
            if(i == 0) {
                child = new DefaultMutableTreeNode(pathParts[i] + "\\");
            }else {
                child = new DefaultMutableTreeNode(pathParts[i]);
            }
            parent.add(child);
            parent = child;
        }
        return parent;
    }
}
