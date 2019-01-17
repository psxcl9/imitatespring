package org.imitatespring.core.io;

import org.imitatespring.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liaocx
 */
public class FileSystemResource implements Resource {

    private final String path;

    private final File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must be NOT NULL");
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File file) {
        this.path = file.getPath();
        this.file = file;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }
}
