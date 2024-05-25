package com.istiqomah.if2210_tb2_iq9.plugin;

import java.util.*;


public class PluginRegistry {

    private static final Map<String, SaveLoadPlugin> plugins = new HashMap<>();

    public static void registerPlugin(SaveLoadPlugin plugin) {
        for (String format : plugin.getSupportedFormats()) {
            plugins.put(format.toLowerCase(), plugin);
        }
    }

    public static List<String> getSupportedFormats() {
        return new ArrayList<>(plugins.keySet());
    }

    public static SaveLoadPlugin getPluginForFormat(String format) {
        return plugins.get(format);
    }
}
