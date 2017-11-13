package com.sschudakov.abstract_factory.factories.views;

import com.sschudakov.abstract_factory.factories.closers.TableFileCloser;
import com.sschudakov.abstract_factory.factories.openers.TableFileOpener;

import java.io.IOException;

/**
 * Created by Semen Chudakov on 12.11.2017.
 */
public class TableFileView implements FileView {

    private TableFileOpener opener;
    private TableFileCloser closer;


    public TableFileOpener getOpener() {
        return opener;
    }

    public TableFileCloser getCloser() {
        return closer;
    }

    public TableFileView(TableFileOpener opener, TableFileCloser closer) {
        this.opener = opener;
        this.closer = closer;
    }

    @Override
    public void open() throws IOException, ClassNotFoundException {
        this.opener.openFile();
        this.opener.getViewManager().setView(this);
        this.closer.getSaver().setTable(opener.getTable());
    }

    @Override
    public void save() {
        this.closer.getSaver().save();
    }

    @Override
    public boolean close() {
        return this.closer.close();
    }
}
