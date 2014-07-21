package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;

public class PacketAllPlayerDataRequest extends Packet{

    private static final int ID = 20;

    public PacketAllPlayerDataRequest(String[] args) {}

    public static int getID() {
        return ID;
    }

    @Override
    public void handle() {
        //No handling..
    }

    @Override
    public String toString() {
        return Notifier.buildString(ID, SPLITTER);
    }
}
