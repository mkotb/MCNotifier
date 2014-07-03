package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PacketSetPlayerHealth extends Packet{

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
        Player player = Bukkit.getPlayer(name);

        if(player != null) {
            player.setHealth(health);
        }
    }

    public static int getID() {
        return id;
    }
}
