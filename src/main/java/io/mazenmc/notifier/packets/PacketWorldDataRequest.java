package io.mazenmc.notifier.packets;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class PacketWorldDataRequest extends Packet{

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
        return id + " " + world;
    }
}
