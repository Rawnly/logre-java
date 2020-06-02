package com.fedevitaledev.LogreClient.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProxyConfig {
    private String host;
    private int port;

    private String username;
    private String password;

    public ProxyConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static ProxyConfig getProxy(String host, int port) {
        return new ProxyConfig(host, port);
    }

    public static ProxyConfig getProxy(String host, int port, String username, String password) {
        return new ProxyConfig(host, port, username, password);
    }
}
