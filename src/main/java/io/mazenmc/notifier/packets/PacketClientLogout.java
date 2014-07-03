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

package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.client.NotifierClient;

/**
 * Packet sent to the server informing that the client has logged out
 */
public class PacketClientLogout extends Packet{

    private static final int id = 6;
    private NotifierClient client;

    public PacketClientLogout(String[] args) {
        //
    }

    public void setClient(NotifierClient client) {
        this.client = client;
    }

    @Override
    public void handle() {
        for(NotifierClient client : NotifierClient.getClients()) {
            if(!client.getUsername().equals(client.getUsername())) {
                client.write(this);
            }
        }

        client.logout();
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + Packet.SPLITTER + client.getUsername();
    }
}
