package com.ghostchu.plugins.riabandwidthsaver.hooks.essx;

import com.ghostchu.plugins.riabandwidthsaver.AFKHook;
import com.ghostchu.plugins.riabandwidthsaver.RIABandwidthSaver;
import net.ess3.api.events.AfkStatusChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class ESSXHook extends AFKHook {
    public ESSXHook(RIABandwidthSaver plugin) {
        super(plugin);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerAfk(AfkStatusChangeEvent event) {
        if (event.getValue()) {
            getPlugin().playerEcoEnable(event.getAffected().getBase());
        } else {
            getPlugin().playerEcoDisable(event.getAffected().getBase());
        }
    }

}
