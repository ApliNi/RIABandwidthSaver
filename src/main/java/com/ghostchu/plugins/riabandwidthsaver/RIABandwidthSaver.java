package com.ghostchu.plugins.riabandwidthsaver;

import com.Zrips.CMI.events.CMIAfkEnterEvent;
import com.Zrips.CMI.events.CMIAfkLeaveEvent;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class RIABandwidthSaver extends JavaPlugin implements Listener {

    private Set<UUID> AFK_PLAYERS = new HashSet<>();
    private long pktCancelled = 0;
    private long pktBytesSaved = 0;

    private final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        initProtocolLib();
    }

    private void initProtocolLib() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(
                this,
                ListenerPriority.HIGHEST,
                PacketType.Play.Server.ANIMATION,
                PacketType.Play.Server.BLOCK_BREAK_ANIMATION,
                PacketType.Play.Server.ENTITY_SOUND,
                PacketType.Play.Server.NAMED_SOUND_EFFECT,
                PacketType.Play.Server.WORLD_PARTICLES,
                PacketType.Play.Server.EXPLOSION,
                PacketType.Play.Server.UPDATE_TIME,
                PacketType.Play.Server.ENTITY_HEAD_ROTATION,
                PacketType.Play.Server.HURT_ANIMATION,
                PacketType.Play.Server.DAMAGE_EVENT,
                PacketType.Play.Server.ENTITY_LOOK,
                PacketType.Play.Server.REL_ENTITY_MOVE,
                PacketType.Play.Server.REL_ENTITY_MOVE_LOOK,
                PacketType.Play.Server.SPAWN_ENTITY_EXPERIENCE_ORB) {
            @Override
            public void onPacketSending(PacketEvent event) {
                UUID uuid = event.getPlayer().getUniqueId();
                if (!AFK_PLAYERS.contains(uuid)) {
                    return;
                }
                PacketType type = event.getPacketType();
                if (type == PacketType.Play.Server.REL_ENTITY_MOVE || type == PacketType.Play.Server.REL_ENTITY_MOVE_LOOK || type == PacketType.Play.Server.SPAWN_ENTITY_EXPERIENCE_ORB) {
                    if(RANDOM.nextInt(3) == 0){
                        return;
                    }
                }
                event.setCancelled(true);
                pktCancelled++;
                pktBytesSaved += event.getPacket().getBytes().size();
            }
        });
    }

    private void playerEcoEnable(Player player) {
        player.sendMessage(ChatColor.GREEN + "🍃 ECO 节能模式已对您启用，游戏世界更新可能会延迟。");
        player.setSendViewDistance(2);
        AFK_PLAYERS.add(player.getUniqueId());
    }

    private void playerEcoDisable(Player player) {
        player.sendMessage(ChatColor.DARK_GRAY + "🍃 ECO 节能模式已关闭。如果出现游戏世界不同步问题，切换服务器或重新登陆即可解决。");
        player.setSendViewDistance(-1);
        player.resetPlayerTime();
        AFK_PLAYERS.remove(player.getUniqueId());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ProtocolLibrary.getProtocolManager().removePacketListeners(this);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerAfk(CMIAfkEnterEvent event) {
        playerEcoEnable(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerLeaveAfk(CMIAfkLeaveEvent event) {
        playerEcoDisable(event.getPlayer());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(ChatColor.GREEN + "🍃 ECO 节能模式 - 统计信息：");
        sender.sendMessage(ChatColor.YELLOW + "共减少发送数据包：" + ChatColor.AQUA + pktCancelled + " 个");
        sender.sendMessage(ChatColor.YELLOW + "共减少发送数据包：" + ChatColor.AQUA + humanReadableByteCount(pktBytesSaved, false) + " （不包含视距优化的增益数据）");
        return true;
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
}
