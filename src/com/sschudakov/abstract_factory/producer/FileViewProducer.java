package com.sschudakov.abstract_factory.producer;

import com.sschudakov.abstract_factory.factories.closers.TableFileCloser;
import com.sschudakov.abstract_factory.factories.closers.TextFileCloser;
import com.sschudakov.abstract_factory.factories.openers.TableFileOpener;
import com.sschudakov.abstract_factory.factories.openers.TextFileOpener;
import com.sschudakov.abstract_factory.factories.savers.TableSaver;
import com.sschudakov.abstract_factory.factories.savers.TextFileSaver;
import com.sschudakov.abstract_factory.factories.views.FileView;
import com.sschudakov.abstract_factory.factories.views.TableFileView;
import com.sschudakov.abstract_factory.factories.views.TextFileView;
import com.sschudakov.utils.FileExtensionDeterminer;

import javax.swing.*;
import java.io.File;

/**
 * Created by Semen Chudakov on 03.10.2017.
 */
public class FileViewProducer {

    private static JTextArea area;

    public static void setArea(JTextArea area) {
        FileViewProducer.area = area;
    }

    public static FileView produceView(File file) {

        if (FileExtensionDeterminer.isTextFile(file.getPath())) {
            return new TextFileView(produceTextFileOpener(file), produceTextFileCloser(file));
        }

        if (FileExtensionDeterminer.isTableFile(file.getPath())) {
            return new TableFileView(produceTableOpener(file), produceTableCloser(file));
        }
        throw new IllegalArgumentException("File: " + file + " has unsupported extension (supported are only txt html and ser)");
    }


    private static TextFileOpener produceTextFileOpener(File file) {
        return new TextFileOpener(file, area);
    }

    private static TextFileCloser produceTextFileCloser(File file) {
        return new TextFileCloser(new TextFileSaver(area, file));
    }

    private static TableFileOpener produceTableOpener(File file) {
        return new TableFileOpener(file);
    }

    private static TableFileCloser produceTableCloser(File file) {
        return new TableFileCloser(new TableSaver(file));
    }
}
