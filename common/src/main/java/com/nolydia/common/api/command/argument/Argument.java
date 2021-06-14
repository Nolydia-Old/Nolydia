package com.nolydia.common.api.command.argument;

import com.nolydia.common.api.command.execution.buffer.ArgumentBuffer;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.internalization.InternalizationMessage;

/**
 * The {@code Argument} interface represents an argument in a minecraft command, as bellows :
 * <p>
 * <code>/coins add &lt;amount&gt; [player]</code>, where :
 * <ul>
 *     <li><code>amount</code> is a required {@code Argument}</li>
 *     <li><code>player</code> is a facultative {@code Argument}</li>
 * </ul>
 * <p>
 * An {@code Argument} can be required or facultative. All {@code Arguments} that follow a facultative argument must be
 * facultative too.
 * <p>
 * If the {@code Argument} is a string potentially containing whitespaces, then {@link Argument#isString()} must return
 * {@code true}.
 * <p>
 * The implementation must define the way the argument will be parsed in {@link Argument#get(CommandSender, String)}.
 * Once parsed, {@code Arguments} are stored in an {@link ArgumentBuffer} and can
 * be retrieved in the {@link com.nolydia.common.api.command.execution.CommandExecutor}.
 *
 * @param <T> The {@code Argument} parsed value type.
 */
public interface Argument<T> extends TabCompletable<T> {

    /**
     * Returns the argument name, as it will appear in the minecraft generated documentation of the command.
     *
     * @return The name of the parameter.
     */
    InternalizationMessage getName();

    /**
     * Checks if the argument is required.
     *
     * @return {@code true} if it the argument is required, {@code false} otherwise.
     */
    default boolean isRequired() {
        return true;
    }

    /**
     * Checks if the argument is a string potentially containing whitespaces.
     *
     * @return {@code true} if the argument a string potentially containing whitespaces, {@code false} otherwise.
     */
    default boolean isString() {
        return false;
    }

    /**
     * Returns the parsed value of the argument.
     *
     * @param sender   The command sender.
     * @param argument The string argument as given by the command sender.
     * @return The parsed value of the argument.
     */
    T get(CommandSender sender, String argument);
}
