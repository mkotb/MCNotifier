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

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

public abstract class Packet {

    private static HashMap<Integer, Class<?>> packets = new HashMap<>();
    public static final String SPLITTER = "//";

    public static void registerAll() {
        Set<Class<? extends Packet>> scs = new Reflections("io.mazenmc.notifier.packets").getSubTypesOf(Packet.class);
        Class<?>[] lcs = scs.toArray(new Class[scs.size()]);

        for(int i = 0; i < lcs.length; i++) {
            Class<?> cls = lcs[i];
            if(!cls.getName().equals("io.mazenmc.notifier.packets.Packet") && !cls.getName().equals("io.mazenmc.notifier.packets.PacketReader")) {
                try{
                    packets.put((int) cls.getDeclaredMethod("getID").invoke(null), cls);
                }catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
                    packets.put(i, cls);
                }
            }
        }
    }

    public abstract void handle();

    @Override
    public abstract String toString();

    public static Class<? extends Packet> getPacket(int id) {
        return packets.get(id).asSubclass(Packet.class);
    }
}
