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

public class PacketReader {

    private static final PacketReader instance = new PacketReader();

    /**
     * Get instance of PacketReader
     * @return instance of PacketReader
     */
    public static PacketReader getInstance() {
        return instance;
    }

    /**
     * Read the packet through string data provided by client
     * @param data The String data provided by the client
     * @return Found client
     * @throws java.lang.IllegalArgumentException If ID found in the data is not associated with a packet
     * @throws java.lang.NumberFormatException If ID placeholder contains something other than a number
     */
    public Packet readPacket(String[] data) {
        int id = Integer.parseInt(data[0]);

        String[] args = new String[data.length-1];

        for(int i = 1; i < data.length; i++) {
            args[i-1] = data[i];
        }

        try{
            return Packet.getPacket(id).getConstructor(String[].class).newInstance(new Object[] {args} );
        }catch(NullPointerException ex) {
            throw new IllegalArgumentException("ID " + id + " is not associated with a packet!");
        }catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
