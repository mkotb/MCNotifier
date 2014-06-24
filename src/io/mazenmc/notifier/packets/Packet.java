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

import io.mazenmc.notifier.NotifierPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public abstract class Packet {

    private static HashMap<Integer, Class<?>> packets = new HashMap<>();
    public static final char SPLITTER = ' ';

    public static void registerAll() {
        List<Class<?>> lcs = NotifierPlugin.getClassFinder().find("io.mazenmc.notifier.packets");

        for(int i = 0; i < lcs.size(); i++) {
            Class<?> cls = lcs.get(i);
            if(!cls.getName().equals("Packet") && !cls.getName().equals("PacketReader")) {
                try{
                    Field field = cls.getField("id");
                    field.setAccessible(true);

                    packets.put((int) field.get(null), cls);
                }catch(NoSuchFieldException | IllegalAccessException ignored) {
                    packets.put(i, cls);
                }
            }
        }
    }

    public abstract void handle();

    public static Class<? extends Packet> getPacket(int id) {
        return packets.get(id).asSubclass(Packet.class);
    }
}
