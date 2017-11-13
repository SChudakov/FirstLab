package com.sschudakov.abstract_factory.factories.openers;

import java.io.IOException;

/**
 * Created by Semen Chudakov on 20.09.2017.
 */
public interface Opener {
    void openFile() throws IOException, ClassNotFoundException;
}
