package com.nolydia.common.api.command.execution.buffer;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueArgumentBuffer implements ArgumentBuffer {

    private final LinkedBlockingQueue<Object> arguments;

    public QueueArgumentBuffer() {
        this.arguments = new LinkedBlockingQueue<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T readArgument() {
        if (this.arguments.isEmpty()) {
            throw new NoSuchElementException();
        }

        return (T) this.arguments.poll();
    }

    @Override
    public <T> Optional<T> readOptionalArgument() {
        Optional<T> optionalArg = Optional.empty();

        if (!this.arguments.isEmpty()) {
            optionalArg = Optional.of(this.readArgument());
        }

        return optionalArg;
    }

    public <T> void addArgument(T argument) {
        this.arguments.offer(argument);
    }
}
