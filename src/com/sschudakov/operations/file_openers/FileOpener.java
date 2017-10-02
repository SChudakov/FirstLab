package com.sschudakov.operations.file_openers;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 20.09.2017.
 */
public interface FileOpener {
    void openFile(String path, JTextArea area);
}
