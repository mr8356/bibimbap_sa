package com.example.plugin;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PluginManager {
    private final List<Plugin> plugins;
    
    public PluginManager(List<Plugin> plugins) { 
        this.plugins = plugins;
        // 플러그인 초기화
        plugins.forEach(Plugin::initialize);
    }
    
    public List<String> runAll() {
        return plugins.stream()
                     .map(Plugin::execute)
                     .toList();
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }
}
