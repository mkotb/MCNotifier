package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.event.NotifierListener;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;
import io.mazenmc.notifier.packets.PacketPlayerDataRequest;
import io.mazenmc.notifier.packets.PacketPlayerDataResponse;

public class AllPlayerDataListener implements NotifierListener{

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        if(event.getPacket() instanceof PacketPlayerDataRequest) {
            event.getClient().write(new PacketPlayerDataResponse(Notifier.emptyPacketArgs()));
        }
    }
}
