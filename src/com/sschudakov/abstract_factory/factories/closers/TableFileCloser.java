package com.sschudakov.abstract_factory.factories.closers;

import com.sschudakov.abstract_factory.factories.savers.TableSaver;
import com.sschudakov.tables.utils.FileContentReader;
import com.sschudakov.utils.OptionConfirmer;

import javax.swing.*;
import java.io.*;

/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class TableFileCloser implements Closer {

    private static final String TMP_FILE_PATH = "D:\\Workspace.java\\FirstLab\\serialized_tables\\tmp_file.ser";

    private TableSaver saver;

    public TableSaver getSaver() {
        return saver;
    }

    public TableFileCloser(TableSaver saver) {
        this.saver = saver;
    }

    @Override
    public boolean close() {
//        if (hasChanges(this.saver.getFile(), this.saver.getTable())) {
        int option = OptionConfirmer.confirmOption(this.saver.getTable().getParent(), "Do you want to save changes");

        if (option == JOptionPane.YES_OPTION) {
            return this.saver.save();
        }

        if (option == JOptionPane.NO_OPTION) {
            return true;
        }

        if (option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION) {
            return false;
        }
//        }
        return true;
    }

    private static boolean hasChanges(File file, JTable table) {
        if (file == null) {
            return true;
        }

        File tmpFile = new File(TMP_FILE_PATH);
        try {
            boolean fileCreated = file.createNewFile();
            System.out.println("\n\n\nfile created: " + fileCreated + "\n\n\n");
            System.out.println("\n\n\nfile exists: " + tmpFile.exists() + "\n\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            outputStream.writeObject(table);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean result = hasChanges(file, tmpFile);

        tmpFile.delete();

        return result;
    }

    private static boolean hasChanges(File file, File tmpFile) {

        String fileContent = null;
        String tmpFileContent = null;
        try {
            fileContent = FileContentReader.readFile(file);
            tmpFileContent = FileContentReader.readFile(tmpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("\n\n\nresult = " + !fileContent.equals(tmpFileContent) + "\n\n\n");
        System.out.println("file content: " + fileContent);
        System.out.println("\n\n\n");
        System.out.println("tmp file content: " + tmpFileContent);
        return !fileContent.equals(tmpFileContent);
    }
}
