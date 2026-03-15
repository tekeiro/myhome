package org.keirobm.myhome.mediamanager.domain.clients.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class AmuleConfig {
    private String host;
    private int port;
    private String password;
}
