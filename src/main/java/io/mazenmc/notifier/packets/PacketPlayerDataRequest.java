package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Packet which will be informing the server that a client wishes to receive information about a certain player
 */
public class PacketPlayerDataRequest extends Packet {

    private static final int id = 10;
    private Player player;

    public PacketPlayerDataRequest(String[] args) {
        player = Bukkit.getPlayer(args[0]);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void handle() {
        //No handling...
    }

    public static int getID() {
        return id;
    }

    public String toString() {
        return Notifier.buildString(id, SPLITTER, player.getName());
    }
}
