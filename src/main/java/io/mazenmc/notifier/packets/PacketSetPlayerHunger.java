package io.mazenmc.notifier.packets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by mazen on 7/3/14.
 */
public class PacketSetPlayerHunger extends Packet{

    private static final int id = 14;
    private String name;
    private int health;

    public PacketSetPlayerHunger(String[] args) {
        name = args[0];
        health = Integer.parseInt(args[1]);
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return id + SPLITTER + name + SPLITTER + health;
    }

    @Override
    public void handle() {
        Player player = Bukkit.getPlayer(name);

        if(player != null) {
            player.setFoodLevel(health);
        }
    }

    public static int getID() {
        return id;
    }
}
