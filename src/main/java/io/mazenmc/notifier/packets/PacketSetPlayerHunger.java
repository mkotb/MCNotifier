package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by mazen on 7/3/14.
 */
public class PacketSetPlayerHunger extends Packet{

    private static final int id = 14;
    private String name;
    private int hunger;

    public PacketSetPlayerHunger(String[] args) {
        name = args[0];
        hunger = Integer.parseInt(args[1]);
    }

    public String getName() {
        return name;
    }

    public double getHunger() {
        return hunger;
    }

    @Override
    public String toString() {
        return Notifier.buildString(id, SPLITTER, name, SPLITTER, hunger);
    }

    @Override
    public void handle() {
        Player player = Bukkit.getPlayer(name);

        if(player != null) {
            player.setFoodLevel(hunger);
        }
    }

    public static int getID() {
        return id;
    }
}
