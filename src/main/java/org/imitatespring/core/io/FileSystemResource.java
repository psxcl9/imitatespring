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

    private String path;

    private File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must be NOT NULL");
        this.path = path;
        this.file = new File(path);
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
