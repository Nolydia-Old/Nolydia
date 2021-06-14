package com.nolydia.common.api.plugin.shutdown;

public interface CloseableResource {

    void close() throws Exception;
}
