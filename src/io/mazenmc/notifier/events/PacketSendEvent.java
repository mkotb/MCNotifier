package io.mazenmc.notifier.events;

import io.mazenmc.notifier.event.NotifierEvent;
import io.mazenmc.notifier.packets.Packet;

public class PacketSendEvent extends NotifierEvent{

    private Packet packet;

    public PacketSendEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
}
