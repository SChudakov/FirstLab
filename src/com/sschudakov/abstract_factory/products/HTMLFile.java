package com.sschudakov.abstract_factory.products;

/**
 * Created by Semen Chudakov on 03.10.2017.
 */
public class HTMLFile implements File{

    private String path;

    public HTMLFile(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
