package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {

    public HTMLFileFilter() {
        super();
    }

    @Override
    public boolean accept(File file) {
        int index = file.getName().indexOf('.');
        String endFile = file.getName().substring(index);
        if (file.isDirectory() || endFile.equalsIgnoreCase(".html") || endFile.equalsIgnoreCase(".htm")) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
