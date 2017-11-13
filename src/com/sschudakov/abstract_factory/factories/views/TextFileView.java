package com.sschudakov.abstract_factory.factories.views;

import com.sschudakov.abstract_factory.factories.closers.TextFileCloser;
import com.sschudakov.abstract_factory.factories.openers.TextFileOpener;

import java.io.IOException;

/**
 * Created by Semen Chudakov on 12.11.2017.
 */
public class TextFileView implements FileView {

    private TextFileOpener opener;
    private TextFileCloser closer;

    public TextFileOpener getOpener() {
        return opener;
    }

    public TextFileCloser getCloser() {
        return closer;
    }

    public TextFileView(TextFileOpener opener, TextFileCloser closer) {
        this.opener = opener;
        this.closer = closer;
    }

    public void open() throws IOException, ClassNotFoundException {
        this.opener.openFile();
    }

    public void save() {
        this.closer.getSaver().save();
    }

    public boolean close() {
        return this.closer.close();
    }
}
