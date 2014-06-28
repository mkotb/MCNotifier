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

import java.util.UUID;

/**
 * Packet informing the client of their Encryption key
 */
public class PacketEncryptKey extends Packet{

    private UUID encryptionKey;
    private static final int id = 8;

    public PacketEncryptKey(UUID encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    @Override
    public void handle() {
        //
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + encryptionKey.toString();
    }
}
