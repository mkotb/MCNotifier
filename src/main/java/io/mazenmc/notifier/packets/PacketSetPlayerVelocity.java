package io.mazenmc.notifier.packets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static java.lang.Double.parseDouble;

public class PacketSetPlayerVelocity extends Packet{

    private static final int id = 15;
    private String name;
    private Vector vector;

    public PacketSetPlayerVelocity(String[] args) {
        name = args[0];
        vector = new Vector(parseDouble(args[1]), parseDouble(args[2]), parseDouble(args[3]));
    }

    public String getName() {
        return name;
    }


    public Vector getVector() {
        return vector;
    }

    @Override
    public String toString() {
        return id + SPLITTER + name + SPLITTER + vector.getX() + SPLITTER + vector.getY() + SPLITTER + vector.getZ();
    }

    @Override
    public void handle() {
        Player player = Bukkit.getPlayer(name);

        if(player != null) {
            player.setVelocity(vector);
        }
    }

    public static int getID() {
        return id;
    }
}
