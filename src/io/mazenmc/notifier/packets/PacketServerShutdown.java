package io.mazenmc.notifier.packets;

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
