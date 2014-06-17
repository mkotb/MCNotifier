package io.mazenmc.notifier.packets;

import java.util.HashMap;

public abstract class Packet {

    private static HashMap<Integer, Packet> packets = new HashMap<>();

    public void register(int id) {
        packets.put(id, this);
    }

    public abstract void run();

    public static Packet getPacket(int id) {
        return packets.get(id);
    }
}
