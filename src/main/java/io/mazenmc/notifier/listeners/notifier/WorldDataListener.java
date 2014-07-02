package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.event.NotifierListener;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;
import io.mazenmc.notifier.packets.PacketWorldDataRequest;

/**
 * Created by mazen on 7/2/14.
 */
public class WorldDataListener implements NotifierListener{

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        if(event.getPacket() instanceof PacketWorldDataRequest) {
            PacketWorldDataRequest packet = (PacketWorldDataRequest) event.getPacket();

            //TODO: Send PacketWorldDataResponse
        }
    }
}
