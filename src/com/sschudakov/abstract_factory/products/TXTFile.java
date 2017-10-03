package com.sschudakov.abstract_factory.products;

/**
 * Created by Semen Chudakov on 03.10.2017.
 */
public class TXTFile implements File{

    private String path;

    public TXTFile(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

}
