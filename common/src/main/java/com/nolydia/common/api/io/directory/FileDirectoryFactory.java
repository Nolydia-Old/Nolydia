package com.nolydia.common.api.io.directory;

import com.nolydia.common.api.io.directory.exceptions.FileDirectoryException;

import java.nio.file.Path;

public interface FileDirectoryFactory {

    FileDirectory createFileDirectory(Path path) throws FileDirectoryException;
}
