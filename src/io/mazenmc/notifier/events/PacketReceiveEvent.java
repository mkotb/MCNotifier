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

package io.mazenmc.notifier.events;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEvent;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketReader;

/**
 * Event which will be called any time a packet has been received by a client
 */
public class PacketReceiveEvent extends NotifierEvent{

    private Packet packet;
    private NotifierClient client;

    public PacketReceiveEvent(String[] data, NotifierClient client) {
        packet = PacketReader.getInstance().readPacket(data);
        this.client = client;
    }

    public Packet getPacket() {
        return packet;
    }

    public NotifierClient getClient() {return client;}
}
