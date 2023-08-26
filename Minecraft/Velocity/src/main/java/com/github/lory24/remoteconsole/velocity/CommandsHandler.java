package com.github.lory24.remoteconsole.velocity;

import com.github.lory24.remoteconsole.velocity.server.data.ExecuteRequest;
import com.velocitypowered.api.proxy.ProxyServer;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;

public class CommandsHandler {

    public void handleRequest(@NotNull ExecuteRequest request, InetSocketAddress address) {
        if (request.getServer().isEmpty()) { // Proxy action
            RemC.INSTANCE.getLogger().info("Received HTTP remote command request from " + address.getHostString() + "! Command: '" + request.getCommand() + "'");

            // Perform command
            ProxyServer server = RemC.INSTANCE.getServer();
            server.getCommandManager().executeImmediatelyAsync(server.getConsoleCommandSource(), request.getCommand());
        }
        else {
            RemC.INSTANCE.getLogger().warn("Received unsupported HTTP REMOTE operation from " + address.getHostString() + "!");
        }
    }
}
