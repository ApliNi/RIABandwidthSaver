package com.ghostchu.plugins.riabandwidthsaver;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class AFKHook implements Listener {
    private final RIABandwidthSaver plugin;

    public AFKHook(RIABandwidthSaver plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public RIABandwidthSaver getPlugin() {
        return plugin;
    }
}
