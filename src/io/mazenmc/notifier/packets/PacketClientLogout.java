package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.client.NotifierClient;

public class PacketClientLogout extends Packet{

    private static final int id = 6;
    private NotifierClient client;

    public PacketClientLogout(String[] args) {
        //
    }

    public void setClient(NotifierClient client) {
        this.client = client;
    }

    @Override
    public void handle() {
        //
    }

    @Override
    public String toString() {
        return id + " ";
    }
}
