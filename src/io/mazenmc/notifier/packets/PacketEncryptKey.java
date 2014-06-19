package io.mazenmc.notifier.packets;

import java.util.UUID;

/**
 * Packet informing the client of their Encryption key
 */
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
        return id + " " + encryptionKey.toString();
    }
}
