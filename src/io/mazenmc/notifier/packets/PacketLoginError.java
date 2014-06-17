package io.mazenmc.notifier.packets;

/**
 * Created by mazen on 6/16/14.
 */
public class PacketLoginError extends Packet{

    private String reason;

    public PacketLoginError(String reason) {
        this.reason = reason;
    }

    private PacketLoginError() {
        //Constructor for serialization
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void run() {
        //Empty method, all running of this method should be done on mobile.
    }
}
