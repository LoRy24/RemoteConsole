package com.github.lory24.remoteconsole.velocity;

import com.github.lory24.remoteconsole.velocity.config.ConfigValues;
import com.github.lory24.remoteconsole.velocity.config.PluginConfiguration;
import com.github.lory24.remoteconsole.velocity.config.YamlConfiguration;
import com.github.lory24.remoteconsole.velocity.server.HttpBackendServer;
import com.google.gson.Gson;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Getter
public enum RemC {
    INSTANCE,
    ;

    // Gson utility
    public static final Gson gson = new Gson();

    // Entry class
    private Entry entry;
    private Logger logger;
    private ProxyServer server;

    // Config
    private PluginConfiguration pluginConfiguration;
    private YamlConfiguration config;

    // Core
    private HttpBackendServer httpBackendServer;
    private CommandsHandler cHandler;

    public boolean enable(final @NotNull Entry entry) {
        this.entry = entry;
        this.logger = entry.getLogger();
        this.server = entry.getProxyServer();

        this.pluginConfiguration = new PluginConfiguration().loadConfiguration();
        this.config = this.pluginConfiguration.getYamlConfig();

        this.httpBackendServer = new HttpBackendServer("127.0.0.1", ConfigValues.CURRENT_PORT.getInteger());
        this.httpBackendServer.start();

        this.cHandler = new CommandsHandler();

        return true;
    }

    public void disable() {
        this.httpBackendServer.stop();
        this.logger.info("Plugin disabled!");
    }
}
