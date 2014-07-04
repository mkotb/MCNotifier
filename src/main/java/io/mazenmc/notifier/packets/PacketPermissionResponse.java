package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;

public class PacketPermissionResponse extends Packet {

    private static final int id = 16;
    private String player;
    private String permission;
    private Long timeout;

    public PacketPermissionResponse(String player, String permission, Long timeout) {
        this.player = player;
        this.permission = permission;
        this.timeout = timeout;
    }

    @Override
    public void handle() {

    }

    @Override
    public String toString() {
        return Notifier.buildString(id, SPLITTER, player, SPLITTER, permission, SPLITTER, timeout);
    }

    public static int getID() {
        return id;
    }
}
