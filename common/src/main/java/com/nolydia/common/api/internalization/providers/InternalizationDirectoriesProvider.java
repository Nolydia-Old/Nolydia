package com.nolydia.common.api.internalization.providers;

import com.google.inject.throwingproviders.CheckedProvider;
import com.nolydia.common.api.io.directory.FileDirectory;
import com.nolydia.common.api.io.directory.exceptions.FileDirectoryException;

import java.util.List;

public interface InternalizationDirectoriesProvider extends CheckedProvider<List<FileDirectory>> {

    @Override
    List<FileDirectory> get() throws FileDirectoryException;
}
