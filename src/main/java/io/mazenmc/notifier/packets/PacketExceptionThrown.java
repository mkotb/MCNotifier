package io.mazenmc.notifier.packets;

public class PacketExceptionThrown extends Packet{

    private static final int id = 10;
    private String exceptionName;
    private String exceptionMessage;
    private String pluginName;

    public PacketExceptionThrown(String[] args) {
        exceptionName = args[0];
        exceptionMessage = args[1];
        pluginName = args[3];
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    @Override
    public void handle() {

    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + Packet.SPLITTER + pluginName + Packet.SPLITTER + exceptionName + Packet.SPLITTER + exceptionMessage.replaceAll(" ", "_");
    }
}
