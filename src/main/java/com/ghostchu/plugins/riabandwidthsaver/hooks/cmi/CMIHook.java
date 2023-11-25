package com.ghostchu.plugins.riabandwidthsaver.hooks.cmi;

import com.Zrips.CMI.events.CMIAfkEnterEvent;
import com.Zrips.CMI.events.CMIAfkLeaveEvent;
import com.ghostchu.plugins.riabandwidthsaver.AFKHook;
import com.ghostchu.plugins.riabandwidthsaver.RIABandwidthSaver;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class CMIHook extends AFKHook implements Listener {

    public CMIHook(RIABandwidthSaver plugin) {
        super(plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerAfk(CMIAfkEnterEvent event) {
        getPlugin().playerEcoEnable(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerLeaveAfk(CMIAfkLeaveEvent event) {
        getPlugin().playerEcoDisable(event.getPlayer());
    }
}
