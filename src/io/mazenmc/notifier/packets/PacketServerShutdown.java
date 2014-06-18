package io.mazenmc.notifier.packets;

public class PacketServerShutdown extends Packet {

    private static final int id = 3;

    public PacketServerShutdown(String[] args) {
        //Pretty much..
    }

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
