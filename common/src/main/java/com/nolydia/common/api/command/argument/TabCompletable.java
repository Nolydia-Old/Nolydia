package com.nolydia.common.api.command.argument;

import com.nolydia.common.api.command.sender.CommandSender;

import java.util.Collection;
import java.util.Collections;

/**
 * This interface can be implemented by an {@code Argument} that can be tab completable.
 * <p>
 * The implementation must define the collection of the authorized values for the argument
 * in {@link TabCompletable#tabComplete(CommandSender)}.
 *
 * @param <T>
 */
public interface TabCompletable<T> {

    /**
     * Returns the authorized values for the argument.
     *
     * @param sender The command sender.
     * @return A collection of the authorized values for the argument.
     */
    default Collection<T> tabComplete(CommandSender sender) {
        return Collections.emptyList();
    }
}
