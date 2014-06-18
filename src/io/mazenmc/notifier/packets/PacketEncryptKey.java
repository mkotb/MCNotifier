package io.mazenmc.notifier.packets;

import java.util.UUID;

public class PacketEncryptKey extends Packet{

    private UUID encryptionKey;
    private static final int id = 4;

    public PacketEncryptKey(UUID encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    @Override
    public void handle() {
        //
    }

    @Override
    public String toString() {
        return 4 + " " + encryptionKey.toString();
    }
}
