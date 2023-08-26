package com.github.lory24.remoteconsole.velocity.server.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("ClassCanBeRecord")
@Getter
@RequiredArgsConstructor
public class ExecuteRequest {
    private final String key;
    private final String server;
    private final String command;
}
