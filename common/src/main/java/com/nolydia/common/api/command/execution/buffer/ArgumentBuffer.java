package com.nolydia.common.api.command.execution.buffer;

import java.util.Optional;

public interface ArgumentBuffer {

    <T> T readArgument();

    <T> Optional<T> readOptionalArgument();
}
