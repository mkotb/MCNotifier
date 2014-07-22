package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.NotifierPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PacketPlayerKick extends Packet{

    private static final int ID = 23;

    private String playerName;
    private String message;

    public PacketPlayerKick(String[] args) {
        playerName = args[0];
        message = args[1];
    }

    public String getName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void handle() {
        final Player p = Bukkit.getPlayer(getName());

        if(p != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.kickPlayer(getMessage());
                }
            }.runTask(NotifierPlugin.getPlugin());
        }
    }

    public static int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return Notifier.buildString(ID, SPLITTER, playerName, SPLITTER, message);
    }
}
