package io.mazenmc.notifier.packets;

/**
 * Created by Nafi on 3/07/2014.
 */
public class PacketPermissionResponse extends Packet {

    private static final int id = 15;
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
        return id + Packet.SPLITTER + player + Packet.SPLITTER + permission;
    }

    public static int getID() {
        return id;
    }
}
