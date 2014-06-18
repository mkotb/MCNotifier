package io.mazenmc.notifier.packets;

public class PacketLoginSuccess extends Packet{

    private static final int id = 1;

    public PacketLoginSuccess(String[] args) {}

    @Override
    public void handle() {
        //Empty method
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + " ";
    }
}
