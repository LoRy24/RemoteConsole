package com.github.lory24.remoteconsole.velocity.config;

import com.github.lory24.remoteconsole.velocity.RemC;
import com.github.lory24.remoteconsole.velocity.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@AllArgsConstructor
public enum ConfigValues {
    PREFIX("settings.messages.prefix"),

    SERVERS("settings.servers"),

    KEY("settings.key"),
    CURRENT_PORT("settings.current.port"),
    ALLOWED_ADDRESSES("settings.allowedAddresses")
    ;

    private final String path;

    // region Normal Datatypes Getters

    public Object get() {
        return this.get(this.path);
    }

    private Object get(String path) {
        return RemC.INSTANCE.getConfig().get(path);
    }

    public String getString() {
        return (String) this.get();
    }

    private String getString(String path) {
        return (String) this.get(path);
    }

    @SuppressWarnings("unused")
    public int getInteger() {
        return (int) this.get();
    }

    private int getInteger(String path) {
        return (int) this.get(path);
    }

    @SuppressWarnings("unused")
    public boolean getBool() {
        return (boolean) this.get();
    }

    private boolean getBool(String path) {
        return (boolean) this.get(path);
    }

    @SuppressWarnings({"unchecked", "unused"})
    public List<String> getStringList() {
        return (List<String>) this.get();
    }

    @SuppressWarnings("unchecked")
    private List<String> getStringList(String path) {
        return (List<String>) this.get(path);
    }

    public TextComponent getColoredString() {
        return StringUtils.color(this.getString());
    }

    private TextComponent getColoredString(String path) {
        return StringUtils.color(this.getString(path));
    }

    // endregion

    @Contract(" -> new")
    public @NotNull Message getMessage() {
        return new Message(this.getString());
    }
}
