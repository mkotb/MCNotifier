package io.mazenmc.notifier.listeners.bukkit;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.PacketBlockPlaceWarning;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockWarning implements Listener{

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(Notifier.getSettingsManager().isBlockSuspicious(event.getBlockPlaced().getType())) {
            PacketBlockPlaceWarning packet = new PacketBlockPlaceWarning(Notifier.generatePacketArgs(event.getPlayer().getName(), event.getBlockPlaced().getType().toString()));
            for(NotifierClient client : NotifierClient.getClients()) {
                client.write(packet);
            }
        }
    }
}
