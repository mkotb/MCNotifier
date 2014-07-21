package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.event.NotifierListener;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;
import io.mazenmc.notifier.packets.PacketAllPlayerDataRequest;
import io.mazenmc.notifier.packets.PacketAllPlayerDataResponse;

public class AllPlayerDataListener implements NotifierListener{

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        if(event.getPacket() instanceof PacketAllPlayerDataRequest) {
            event.getClient().write(new PacketAllPlayerDataResponse(Notifier.emptyPacketArgs()));
        }
    }
}
