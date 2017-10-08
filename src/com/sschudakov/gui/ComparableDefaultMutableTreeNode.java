package com.sschudakov.gui;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Semen Chudakov on 08.10.2017.
 */
public class ComparableDefaultMutableTreeNode extends DefaultMutableTreeNode {

    public ComparableDefaultMutableTreeNode() {
    }

    public ComparableDefaultMutableTreeNode(Object userObject) {
        super(userObject);
    }

    public ComparableDefaultMutableTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    @Override
    public boolean equals(Object other) {

        if (!this.getClass().equals(other.getClass())) {
            return false;
        }
        ComparableDefaultMutableTreeNode castedOther = (ComparableDefaultMutableTreeNode) other;

        return super.getUserObject().equals(castedOther.getUserObject());
    }

}
