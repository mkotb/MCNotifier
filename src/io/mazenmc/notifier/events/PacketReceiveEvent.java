package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEvent;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketReader;

/**
 * Event which will be called any time a packet has been received by a client
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
