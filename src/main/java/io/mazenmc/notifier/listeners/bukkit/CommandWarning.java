/*
* Copyright (C) 2014 Mazen K.
* This file is part of MCNotifier.
*
* MCNotifier for Bukkit is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, version 3 to be exact
*
* MCNotifier is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with MCNotifier. If not, see <http://www.gnu.org/licenses/>.
*/

package io.mazenmc.notifier.listeners.bukkit;

import io.mazenmc.notifier.NotifierPlugin;
import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.PacketCommandWarning;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandWarning implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String[] data = event.getMessage().split(" ");
        String command = data[0];

        if (NotifierPlugin.getSettingsManager().isSuspiciousCommand(command)) {
            for (NotifierClient client : NotifierClient.getClients()) {
                client.write(new PacketCommandWarning(new String[]{event.getPlayer().getName(), command}));
            }
        }
    }
}
