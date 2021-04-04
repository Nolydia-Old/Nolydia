package com.nolydia.common.api.io.directory.exceptions;

import com.nolydia.common.api.io.directory.FileDirectory;

/**
 * Wraps an exception occurred during the creation of a {@link FileDirectory}
 */
public class FileDirectoryException extends Exception {

    public FileDirectoryException(Throwable cause) {
        super("Exception occurred during the creation of a file directory", cause);
    }
}
