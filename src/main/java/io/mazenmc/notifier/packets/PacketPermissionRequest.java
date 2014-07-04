package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import org.bukkit.entity.Player;

/**
 * Created by Nafi on 3/07/2014.
 */
public class PacketPermissionRequest extends Packet{

    private static final int id = 15;
    private String permission;
    private Player permRequestor;

    public PacketPermissionRequest(String permission, Player permRequestor) throws Exception {
        if(!Notifier.vaultDetected()) {
            throw new Exception("Vault was not detected. Make sure Vault is enabled to utilize this feature.");
        }
        this.permission = permission;
        this.permRequestor = permRequestor;
    }

    @Override
    public void handle() {

    }

    @Override
    public String toString() {
        return id + Packet.SPLITTER + permRequestor.getName() + Packet.SPLITTER + permission;
    }

    public static int getID() {
        return id;
    }
}
