package io.mazenmc.notifier.packets;

/**
 * Packet which will be notifying the client that the server has been shutdown
 */
public class PacketServerShutdown extends Packet {

    private static final int id = 2;

    public PacketServerShutdown(String[] args) {}

    @Override
    public void handle() {
        //No handling here..
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + " ";
    }
}
