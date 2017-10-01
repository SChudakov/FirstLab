package com.sschudakov.gui;

import java.awt.*;

/**
 * Created by Semen Chudakov on 01.10.2017.
 */
public class GBC extends GridBagConstraints {
    public GBC(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int fill) {
        super();
        super.gridx = gridx;
        super.gridy = gridy;
        super.gridwidth = gridwidth;
        super.gridheight = gridheight;
        super.weightx = weightx;
        super.weighty = weighty;
        super.fill = fill;
    }
}
