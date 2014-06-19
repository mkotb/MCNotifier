package io.mazenmc.notifier.listeners.bukkit;

import io.mazenmc.notifier.NotifierPlugin;
import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.PacketCommandWarning;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandWarning implements Listener{

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String[] data = event.getMessage().split(" ");
        String command = data[0];

        if(NotifierPlugin.getSettingsManager().isSuspiciousCommand(command)) {
            for(NotifierClient client : NotifierClient.getClients()) {
                client.write(new PacketCommandWarning(new String[] {event.getPlayer().getName(), command}));
            }
        }
    }
}
