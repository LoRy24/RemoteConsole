package com.github.lory24.remoteconsole.velocity.utils;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * This class contains some utils used to work with strings, for example functions to convert a string to a colored text
 * component.
 *
 * @author LoRy24
 */
@UtilityClass
public class StringUtils {

    /**
     * This function convert a string to a text component with parsed colors.
     *
     * @param input The input string
     * @return The text component that is created after parsing the colors to the input string
     */
    public TextComponent color(String input) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(input);
    }
}
