package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.client.NotifierClient;

/**
 * Packet sent to the server informing that the client has logged out
 */
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
        for(NotifierClient client : NotifierClient.getClients()) {
            if(!client.getUsername().equals(client.getUsername())) {
                client.write(this);
            }
        }

        client.logout();
    }

    @Override
    public String toString() {
        return id + " " + client.getUsername();
    }
}
