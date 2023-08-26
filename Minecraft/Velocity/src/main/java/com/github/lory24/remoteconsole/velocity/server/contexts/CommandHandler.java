package com.github.lory24.remoteconsole.velocity.server.contexts;

import com.github.lory24.remoteconsole.velocity.RemC;
import com.github.lory24.remoteconsole.velocity.config.ConfigValues;
import com.github.lory24.remoteconsole.velocity.server.HttpBackendServer;
import com.github.lory24.remoteconsole.velocity.server.data.ExecuteRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CommandHandler implements HttpHandler {

    @Override
    public void handle(@NotNull HttpExchange exchange) throws IOException {
        // Read data
        byte[] requestData = exchange.getRequestBody().readAllBytes();

        try {
            // Create the request
            ExecuteRequest executeRequest = RemC.gson.fromJson(new String(requestData), ExecuteRequest.class);

            // Authenticate the connection
            if (!executeRequest.getKey().equals(ConfigValues.KEY.getString()) || !ConfigValues.ALLOWED_ADDRESSES.getStringList().contains(exchange.getRemoteAddress().getHostString())) {
                this.sendStatus(exchange, -2);
                return;
            }

            // Execute it
            RemC.INSTANCE.getCHandler().handleRequest(executeRequest, exchange.getRemoteAddress());
        } catch (Exception ignored) {
            this.sendStatus(exchange, -1);
            return;
        }

        // Send response
        this.sendStatus(exchange, 0);
    }

    private void sendStatus(HttpExchange exchange, int code) {
        HttpBackendServer.sendResponse(exchange, "{\"status\":" + code + "}");
    }
}
