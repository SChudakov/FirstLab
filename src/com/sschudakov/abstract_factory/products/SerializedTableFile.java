package com.sschudakov.abstract_factory.products;


/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class SerializedTableFile implements File {

    private String path;

    public SerializedTableFile(String path) {
        this.path = path;
    }

    @Override
    public String getPath() {
        return this.path;
    }
}
