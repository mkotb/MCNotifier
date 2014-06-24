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

/**
 * Packet notifying the client that there was an error logging in
 */
public class PacketLoginError extends Packet{

    private String reason;
    private static final int id = 0;

    public PacketLoginError(String[] args) {
        StringBuilder sb = new StringBuilder("");

        for(String s : args) {
            sb.append(s);
            sb.append(' ');
        }

        this.reason = sb.toString();
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void handle() {
        //Empty method, all running of this method should be done on mobile.
    }

    @Override
    public String toString() {
        return id + " " + reason;
    }

    public static int getID() {
        return id;
    }
}
