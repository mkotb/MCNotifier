package io.mazenmc.notifier.packets;

public class PacketLoginError extends Packet{

    private String reason;
    private static final int id = 0;

    public PacketLoginError(String[] args) {
        StringBuilder sb = new StringBuilder("");

        for(String s : args) {
            sb.append(s);
            sb.append(' ');
        }

        this.reason = sb.toString();
        register(id);
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void run() {
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
