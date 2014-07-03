package io.mazenmc.notifier.packets;

import org.bukkit.entity.Player;

public class PacketSimpleMessage extends Packet {

    private static final int id = 12;
    private String message;
    private Player sender;

    public PacketSimpleMessage(String message, Player sender) {
        this.message = message;
        this.sender = sender;
    }

    public static int getID() {
        return id;
    }

    @Override
    public void handle() {}

    @Override
    public String toString() {
        return id + " " + sender.getName() + " " + message;
    }
}
