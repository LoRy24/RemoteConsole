package com.github.lory24.remoteconsole.velocity.server.contexts;

import com.github.lory24.remoteconsole.velocity.server.HttpBackendServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class InfosHandler implements HttpHandler {

    @Override
    public void handle(@NotNull HttpExchange exchange) throws IOException {
        HttpBackendServer.sendResponse(exchange, "{\"type\":\"velocity\"}");
    }
}
