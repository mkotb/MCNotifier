package io.mazenmc.notifier.packets;

/**
 * Packet sent when player ran a suspicious command
 */
public class PacketCommandWarning extends Packet{

    private static final int id = 4;
    private String command;
    private String playerName;

    public PacketCommandWarning(String[] args) {
        this.command = args[1];
        this.playerName = args[0];
    }

    @Override
    public void handle() {
        //Handling is all done client side
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + playerName + " " + command;
    }
}
