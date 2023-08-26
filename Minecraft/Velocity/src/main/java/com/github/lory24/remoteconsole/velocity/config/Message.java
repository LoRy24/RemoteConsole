package com.github.lory24.remoteconsole.velocity.config;

import com.github.lory24.remoteconsole.velocity.utils.StringUtils;
import lombok.AllArgsConstructor;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public class Message {
    private String message;

    public Message applyPrefix() {
        return this.replaceParameter("prefix", ConfigValues.PREFIX.getString());
    }

    public Message replaceParameter(final String key, final String value) {
        this.message = message.replace("%" + key + "%", value);
        return this;
    }

    public void send(@NotNull final Audience audience) {
        audience.sendMessage(StringUtils.color(this.message));
    }

    public TextComponent getTextComponent() {
        return StringUtils.color(this.message);
    }
}
