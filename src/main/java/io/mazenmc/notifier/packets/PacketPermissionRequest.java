package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.NotifierPlugin;
import org.bukkit.entity.Player;

public class PacketPermissionRequest extends Packet{

    private static final int id = 15;
    private String permission;
    private Player permRequester;

    public PacketPermissionRequest(String permission, Player permRequester) {
        if(!Notifier.vaultDetected()) {
            throw new IllegalStateException("Vault was not detected. Make sure Vault is enabled to utilize this feature.");
        }
        this.permission = permission;
        this.permRequester = permRequester;
    }

    @Override
    public void handle() {
        NotifierPlugin.getPermission().playerAdd(permRequester, permission);
    }

    @Override
    public String toString() {
        return Notifier.buildString(id, SPLITTER, permRequester.getName(), SPLITTER, permission);
    }

    public static int getID() {
        return id;
    }
}
