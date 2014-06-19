package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.event.NotifierListener;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;
import io.mazenmc.notifier.packets.PacketClientLogout;

public class LogoutListener implements NotifierListener{

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        if(event.getPacket() instanceof PacketClientLogout) {
            PacketClientLogout packet = (PacketClientLogout) event.getPacket();

            packet.setClient(event.getClient());
            packet.handle();
        }
    }
}
