package io.mazenmc.notifier.packets;

/**
 * Packet notifying the client that they have been forced logged out by the server
 */
public class PacketForceLogout extends Packet{

    private String reason;
    private static final int id = 5;

    public PacketForceLogout(String[] args) {
        StringBuilder sb = new StringBuilder("");

        for(String s : args) {
            sb.append(s);
            sb.append(' ');
        }

        this.reason = sb.toString();
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void handle() {
        //Empty method, all running of this method should be done on mobile.
    }

    @Override
    public String toString() {
        return id + " " + reason;
    }

    public static int getID() {
        return id;
    }
}
