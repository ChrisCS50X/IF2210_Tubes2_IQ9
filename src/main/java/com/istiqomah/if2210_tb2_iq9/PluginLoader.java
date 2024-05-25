package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.plugin.SaveLoadPlugin;
import com.istiqomah.if2210_tb2_iq9.plugin.PluginRegistry;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

public class PluginLoader {
    public static void loadPlugins(String pluginDir) {
        File dir = new File(pluginDir);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Plugin directory not found: " + pluginDir);
            return;
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".jar"));
        if (files == null || files.length == 0) {
            System.out.println("No JAR files found in plugin directory: " + pluginDir);
            return;
        }

        for (File file : files) {
            try {
                URL[] urls = { file.toURI().toURL() };
                URLClassLoader urlClassLoader = new URLClassLoader(urls, PluginLoader.class.getClassLoader());
                ServiceLoader<SaveLoadPlugin> serviceLoader = ServiceLoader.load(SaveLoadPlugin.class, urlClassLoader);

                for (SaveLoadPlugin plugin : serviceLoader) {
                    PluginRegistry.registerPlugin(plugin);
                    System.out.println("Loaded plugin: " + plugin.getClass().getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load plugin: " + file.getName());
            }
        }
    }
}
