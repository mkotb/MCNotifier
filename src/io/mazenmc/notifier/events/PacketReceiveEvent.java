package io.mazenmc.notifier.events;

import io.mazenmc.notifier.event.NotifierEvent;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketReader;

/**
 * Created by mazen on 6/17/14.
 */
public class PacketReceiveEvent extends NotifierEvent{

    private Packet packet;

    public PacketReceiveEvent(String[] data) {
        packet = PacketReader.getInstance().readPacket(data);
    }

    public Packet getPacket() {
        return packet;
    }
}
