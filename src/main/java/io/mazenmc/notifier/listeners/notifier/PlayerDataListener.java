package io.mazenmc.notifier.listeners.notifier;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.event.NotifierMethod;
import io.mazenmc.notifier.events.PacketReceiveEvent;
import io.mazenmc.notifier.packets.PacketPlayerDataRequest;
import io.mazenmc.notifier.packets.PacketPlayerDataResponse;
import org.bukkit.event.Listener;

public class PlayerDataListener implements Listener{

    @NotifierMethod
    public void onReceive(PacketReceiveEvent event) {
        if(event.getPacket() instanceof PacketPlayerDataRequest) {
            PacketPlayerDataRequest packet = (PacketPlayerDataRequest) event.getPacket();

            event.getClient().write(new PacketPlayerDataResponse(Notifier.generatePacketArgs(packet.getPlayer().getName())));
        }
    }
}
