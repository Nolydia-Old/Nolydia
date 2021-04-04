package com.nolydia.common.api.command.argument;

import com.nolydia.common.api.command.execution.buffer.ArgumentBuffer;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.internalization.InternalizationMessage;

/**
 * An {@code Argument} can be required or facultative.
 * If the {@code Argument} is a string potentially containing whitespaces,
 * then {@link Argument#isString()} must return {@code true}.
 * The {@code Argument} must implement the way it is parsed in {@link Argument#get(CommandSender, String)}.
 * Once parsed, {@code Arguments} are stored in an {@link ArgumentBuffer} and can
 * be retrieved while executing the command.
 * <p>
 * Example :
 * /coins add <amount> [player] : amount is a required {@code Argument}, player is a facultative {@code Argument}
 * <p>
 *
 * @param <T> type of the {@code Argument} value
 */
public interface Argument<T> extends TabCompletable<T> {

    /**
     * Get the argument's name.
     *
     * @return the name of the parameter.
     */
    InternalizationMessage getName();

    /**
     * Check if the argument is required.
     *
     * @return {@code true} if it is required, {@code false} otherwise
     */
    default boolean isRequired() {
        return true;
    }

    /**
     * Check if the argument is a string potentially containing whitespaces.
     *
     * @return {@code true} if the argument a string potentially containing whitespaces, {@code false} otherwise
     */
    default boolean isString() {
        return false;
    }

    /**
     * Get the parsed value of the argument.
     *
     * @param sender the sender who executed the command
     * @param argument the string argument as given by the command sender
     * @return the parsed value of the parameter
     */
    T get(CommandSender sender, String argument);
}
