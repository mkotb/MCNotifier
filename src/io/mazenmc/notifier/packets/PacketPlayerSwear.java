package io.mazenmc.notifier.packets;

public class PacketPlayerSwear extends Packet{

    private static final int id = 7;
    private String swear;
    private String message;

    public PacketPlayerSwear(String[] args) {
        this.swear = args[0];

        StringBuilder sb = new StringBuilder("");

        for(int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            sb.append(' ');
        }

        this.message = sb.toString();
    }

    public String getMessage() {
        return message;
    }

    public String getSwear() {
        return swear;
    }

    @Override
    public void handle() {
        //Handling is all client side
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + swear + " " + message;
    }
}
