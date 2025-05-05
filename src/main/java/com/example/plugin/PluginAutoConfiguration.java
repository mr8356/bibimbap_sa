package com.example.plugin;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import java.util.List;

@AutoConfiguration
@ConditionalOnProperty(name = "plugin.enabled", havingValue = "true", matchIfMissing = true)
public class PluginAutoConfiguration {
    @Bean
    public PluginManager pluginManager(List<Plugin> plugins) {
        return new PluginManager(plugins);
    }
} 