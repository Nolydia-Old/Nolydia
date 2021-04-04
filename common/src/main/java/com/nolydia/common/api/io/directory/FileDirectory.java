package com.nolydia.common.api.io.directory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.io.directory.exceptions.FileDirectoryException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileDirectory {

    private final List<Path> files;

    @Inject
    public FileDirectory(@Assisted Path rootPath) throws FileDirectoryException {
        this.files = new ArrayList<>();

        try (Stream<Path> walk = Files.walk(rootPath)) {
            walk
                    .filter(path ->
                            Files.isReadable(path) && Files.isRegularFile(path) && !path.toFile().isHidden())
                    .forEach(this.files::add);
        } catch (Exception e) {
            throw new FileDirectoryException(e);
        }
    }

    public List<Path> listDirectory() {
        return this.files;
    }
}
