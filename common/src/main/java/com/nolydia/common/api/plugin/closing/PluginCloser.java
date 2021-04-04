package com.nolydia.common.api.plugin.closing;

import com.google.inject.Inject;
import com.nolydia.common.api.persistence.sql.access.SQLAccess;

public class PluginCloser {

    private final SQLAccess sqlAccess;

    @Inject
    public PluginCloser(SQLAccess sqlAccess) {
        this.sqlAccess = sqlAccess;
    }

    public void close() {
        sqlAccess.closePool();
    }
}
