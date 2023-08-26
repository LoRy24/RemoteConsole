package com.github.lory24.remoteconsole.velocity.server;

import com.github.lory24.remoteconsole.velocity.server.contexts.CommandHandler;
import com.github.lory24.remoteconsole.velocity.server.contexts.InfosHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

@Getter
public class HttpBackendServer {
    private final HttpServer server;
    private final String host;
    private final int port;

    @SneakyThrows
    public HttpBackendServer(String host, int port) {
        this.host = host;
        this.port = port;

        this.server = HttpServer.create(new InetSocketAddress(port), 0);

        // Contexts
        this.server.createContext("/", new InfosHandler());
        this.server.createContext("/execute", new CommandHandler());

        this.server.setExecutor(null);
    }

    public void start() {
        this.server.start();
    }

    public void stop() {
        this.server.stop(0);
    }

    @SneakyThrows
    public static void sendResponse(@NotNull HttpExchange exchange, @NotNull String response) {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
    }
}
