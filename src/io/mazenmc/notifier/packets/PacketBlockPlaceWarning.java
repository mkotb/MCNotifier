package io.mazenmc.notifier.packets;

public class PacketBlockPlaceWarning extends Packet{

    private static final int id = 7;
    private String username;
    private String block;

    public PacketBlockPlaceWarning(String[] args) {
        this.username = args[0];
        this.block = args[1];
    }

    public String getBlock() {
        return block;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void handle() {
        //No handling here..
    }

    @Override
    public String toString() {
        return id + " " + username + " " + block;
    }
}
