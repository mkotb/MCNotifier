package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.event.NotifierListener;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;
import io.mazenmc.notifier.packets.PacketPlayerDataRequest;
import io.mazenmc.notifier.packets.PacketPlayerDataResponse;
import io.mazenmc.notifier.packets.PacketReceiveError;

public class PlayerDataListener implements NotifierListener {

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        if (event.getPacket() instanceof PacketPlayerDataRequest) {
            PacketPlayerDataRequest packet = (PacketPlayerDataRequest) event.getPacket();

            if (packet.getPlayer() == null) {
                event.getClient().write(new PacketReceiveError("Player is not online!".split(" ")));
                return;
            }

            event.getClient().write(new PacketPlayerDataResponse(Notifier.generatePacketArgs(packet.getPlayer().getName())));
        }
    }
}
