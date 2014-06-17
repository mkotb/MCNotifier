package io.mazenmc.notifier.packets;

import java.io.Serializable;

public abstract class Packet implements Serializable{

    public abstract void run();
}
