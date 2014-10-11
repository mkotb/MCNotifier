package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * Packet which will be informing the server that a client wishes to receive information about a certain world
 */
public class PacketWorldDataRequest extends Packet {

    private static final int id = 8;
    private World world;

    public PacketWorldDataRequest(String[] args) {
        world = Bukkit.getWorld(args[0]);
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void handle() {
        //No handling...
    }

    public static int getID() {
        return id;
    }

    public String toString() {
        return Notifier.buildString(id, SPLITTER, world.getName());
    }
}
