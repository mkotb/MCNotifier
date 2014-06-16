package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.Packet;

/**
 * Created by mazen on 6/16/14.
 */
public class PacketReceiveEvent extends MessageReceiveEvent<Packet>{

    public PacketReceiveEvent(Packet received, NotifierClient client) {
        super(received, client);
    }
}
