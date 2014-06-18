package io.mazenmc.notifier.packets;

public class PacketReceiveError extends Packet{

    private static final int id = 4;
    private String reason;

    public PacketReceiveError(String[] args) {
        StringBuilder sb = new StringBuilder("");

        for(String s : args) {
            sb.append(s);
            sb.append(' ');
        }

        reason = sb.toString();
    }

    @Override
    public void handle() {
        //
    }

    @Override
    public String toString() {
        return id + " " + reason;
    }
}
