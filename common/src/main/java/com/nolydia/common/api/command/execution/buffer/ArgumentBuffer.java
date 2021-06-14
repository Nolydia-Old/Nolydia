package com.nolydia.common.api.command.execution.buffer;

import java.util.Optional;

/**
 * This interface represents a buffer of {@link com.nolydia.common.api.command.argument.Argument}s which is filled and
 * can be accessed after the command execution.
 * <p>
 * A required argument can be read using {@link ArgumentBuffer#readArgument()}.
 * An optional argument can be read using {@link ArgumentBuffer#readOptionalArgument()}.
 * <p>
 * Attempting to read extra arguments will throw a {@link java.util.NoSuchElementException}.
 */
public interface ArgumentBuffer {

    /**
     * Reads the next argument from the buffer.
     *
     * @param <T> The argument type.
     * @return The next argument read.
     */
    <T> T readArgument();

    /**
     * Reads the next optional argument from the buffer.
     *
     * @param <T> The argument type.
     * @return An {@link Optional} filled with the argument value if the argument was given by the command sender,
     * an empty {@link Optional} otherwise.
     */
    <T> Optional<T> readOptionalArgument();
}
