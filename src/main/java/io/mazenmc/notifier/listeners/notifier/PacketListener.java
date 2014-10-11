package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.event.NotifierListener;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;

public class PacketListener implements NotifierListener {

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        event.getPacket().handle();
    }
}
