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

import io.mazenmc.notifier.Notifier;

public class PacketPlayerSwear extends Packet {

    private static final int id = 19;
    private String swear;
    private String message;

    public PacketPlayerSwear(String[] args) {
        this.swear = args[0];

        StringBuilder sb = new StringBuilder("");

        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(' ');
        }

        this.message = sb.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getSwear() {
        return swear;
    }

    @Override
    public void handle() {
        //Handling is all client side
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return Notifier.buildString(id, SPLITTER, swear, SPLITTER, message);
    }
}
