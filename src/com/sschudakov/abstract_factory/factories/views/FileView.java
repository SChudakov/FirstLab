package com.sschudakov.abstract_factory.factories.views;

import java.io.IOException;

/**
 * Created by Semen Chudakov on 12.11.2017.
 */
public interface FileView {
    void open() throws IOException, ClassNotFoundException;

    void save();

    boolean close();
}
