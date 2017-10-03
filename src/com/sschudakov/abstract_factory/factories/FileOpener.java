package com.sschudakov.abstract_factory.factories;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Created by Semen Chudakov on 20.09.2017.
 */
public interface FileOpener {
    void openFile(JTextArea area) throws FileNotFoundException;
}
