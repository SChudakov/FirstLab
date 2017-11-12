package com.sschudakov.abstract_factory.factory_producer;

import com.sschudakov.abstract_factory.factories.FileOpener;
import com.sschudakov.abstract_factory.factories.HTMLFileOpener;
import com.sschudakov.abstract_factory.factories.SerializedTableFileOpener;
import com.sschudakov.abstract_factory.factories.TXTFileOpener;
import com.sschudakov.abstract_factory.products.HTMLFile;
import com.sschudakov.abstract_factory.products.SerializedTableFile;
import com.sschudakov.abstract_factory.products.TXTFile;
import com.sschudakov.utils.FileExtensionDeterminer;

/**
 * Created by Semen Chudakov on 03.10.2017.
 */
public class FileOpenerProducer {
    public static FileOpener produceFactory(String path){

        if(FileExtensionDeterminer.isHTNLFile(path)){
            return new HTMLFileOpener(new HTMLFile(path));
        }
        if(FileExtensionDeterminer.isTXTFile(path)){
            return new TXTFileOpener(new TXTFile(path));
        }
        if (FileExtensionDeterminer.isExcelTableFile(path)) {
            return new SerializedTableFileOpener(new SerializedTableFile(path));
        }
        throw new IllegalArgumentException("File: " + path + " has unsupported extension (supported are only txt and html)");
    }

}
