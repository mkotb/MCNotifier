package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.event.NotifierListener;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;
import io.mazenmc.notifier.packets.PacketWorldDataRequest;
import io.mazenmc.notifier.packets.PacketWorldDataResponse;

public class WorldDataListener implements NotifierListener{

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        if(event.getPacket() instanceof PacketWorldDataRequest) {
            PacketWorldDataRequest packet = (PacketWorldDataRequest) event.getPacket();

            event.getClient().write(new PacketWorldDataResponse(Notifier.generatePacketArgs(packet.getWorld().getName())));
        }
    }
}
