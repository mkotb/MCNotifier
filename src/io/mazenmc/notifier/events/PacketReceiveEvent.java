package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEvent;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketReader;

/**
 * Created by mazen on 6/17/14.
 */
public class PacketReceiveEvent extends NotifierEvent{

    private Packet packet;
    private NotifierClient client;

    public PacketReceiveEvent(String[] data, NotifierClient client) {
        packet = PacketReader.getInstance().readPacket(data);
        this.client = client;
    }

    public Packet getPacket() {
        return packet;
    }

    public NotifierClient getClient() {return client;}
}
