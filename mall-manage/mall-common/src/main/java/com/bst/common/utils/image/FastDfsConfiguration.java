package com.bst.common.utils.image;

import org.csource.fastdfs.ClientGlobal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class FastDfsConfiguration {

    @ConfigurationProperties(prefix = "fast-dfs.config")
    @Bean
    public FastDfsProperties fastDfsProperties() {
        return new FastDfsProperties();
    }

    @Bean("dfsProperties")
    public Properties dfsProperties(FastDfsProperties fastDfsProperties) {
        Properties properties = new Properties();
        if (fastDfsProperties.getTracker_server() != null) {
            properties.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, fastDfsProperties.getTracker_server());
        }
        if (fastDfsProperties.getConnect_timeout() != null) {
            properties.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, fastDfsProperties.getConnect_timeout());
        }
        if (fastDfsProperties.getNetwork_timeout() != null) {
            properties.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, fastDfsProperties.getNetwork_timeout());
        }
        if (fastDfsProperties.getCharset() != null) {
            properties.put(ClientGlobal.PROP_KEY_CHARSET, fastDfsProperties.getCharset());
        }
        properties.put(ClientGlobal.DEFAULT_HTTP_ANTI_STEAL_TOKEN, fastDfsProperties.getHttp().isAnti_steal_token());
        if (fastDfsProperties.getHttp().getSecret_key() != null) {
            properties.put(ClientGlobal.DEFAULT_HTTP_SECRET_KEY, fastDfsProperties.getHttp().getSecret_key());
        }
        if (fastDfsProperties.getHttp().getTracker_http_port() != null) {
            properties.put(ClientGlobal.DEFAULT_HTTP_TRACKER_HTTP_PORT, fastDfsProperties.getHttp().getTracker_http_port());
        }
        return properties;
    }
}
