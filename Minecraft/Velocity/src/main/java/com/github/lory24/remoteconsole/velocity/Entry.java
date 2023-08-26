package com.github.lory24.remoteconsole.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Plugin(
        id = "remcvelocity", name = "RemoteConsoleVelocity",
        version = "1.0-SNAPSHOT",
        authors = {"LoRy24"}
)
@Getter
public class Entry {

    @Inject
    private ProxyServer proxyServer;

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        // Try to enable the plugin
        enablingBlock: {
            try {
                if (!RemC.INSTANCE.enable(this))
                    break enablingBlock;

                this.logger.info("Plugin enabled at version 1.0-SNAPSHOT!");
                return;
            } catch (Exception e) {
                this.logger.error(e.getMessage());
            }
        }

        // Flag the error
        this.errorShutdownRoutine();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        RemC.INSTANCE.disable();
    }

    private void errorShutdownRoutine() {
        this.logger.warn("An error has happened while enabling the core!");
        RemC.INSTANCE.disable();
    }
}
