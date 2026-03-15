package org.keirobm.myhome.mediamanager.boot;

import org.keirobm.myhome.mediamanager.domain.clients.config.AmuleConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmuleConfigBean {

    @Bean
    @ConfigurationProperties(prefix = "myhome.amule")
    public AmuleConfig amuleConfig() {
        return AmuleConfig.builder().build();
    }


}
