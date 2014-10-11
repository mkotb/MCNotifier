package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.NotifierPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PacketSetPlayerHealth extends Packet {

    private static final int id = 13;
    private String name;
    private double health;

    public PacketSetPlayerHealth(String[] args) {
        name = args[0];
        health = Double.parseDouble(args[1]);
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return Notifier.buildString(id, SPLITTER, name, SPLITTER, health);
    }

    @Override
    public void handle() {
        final Player player = Bukkit.getPlayer(name);

        if (player != null) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setHealth(health);
                }
            }.runTask(NotifierPlugin.getPlugin());
        }
    }

    public static int getID() {
        return id;
    }
}
