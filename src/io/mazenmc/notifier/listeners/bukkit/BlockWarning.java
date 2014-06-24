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
