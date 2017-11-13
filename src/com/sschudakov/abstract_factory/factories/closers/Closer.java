package com.sschudakov.abstract_factory.factories.closers;

import com.sschudakov.abstract_factory.factories.savers.Saver;

/**
 * Created by Semen Chudakov on 12.11.2017.
 */
public interface Closer {
    Saver getSaver();
    boolean close();
}
