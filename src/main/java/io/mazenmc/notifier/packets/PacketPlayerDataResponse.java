package io.mazenmc.notifier.packets;

import io.mazenmc.notifier.Notifier;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.net.InetSocketAddress;

public class PacketPlayerDataResponse extends Packet{

    private static final int id = 11;
    private Player player;
    private double health;
    private double foodLevel;
    private ItemStack itemInHand;
    private World world;
    private InetSocketAddress address;
    
    public PacketPlayerDataResponse(String[] args) {
        player = Bukkit.getPlayer(args[0]);

        health = player.getHealth();
        itemInHand = player.getItemInHand();
        foodLevel = player.getFoodLevel();
        world = player.getWorld();
        address = player.getAddress();
    }

    public Player getPlayer() {
        return player;
    }

    public double getHealth() {
        return health;
    }

    public double getFoodLevel() {
        return foodLevel;
    }

    public ItemStack getItemInHand() {
        return itemInHand;
    }

    public World getWorld() {
        return world;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public String getIP() {
        return address.getAddress().getHostAddress();
    }

    @Override
    public void handle() {
        //What handling?
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        return Notifier.buildString(id, SPLITTER, health, SPLITTER, StringUtils.capitalize(itemInHand.getType().toString()), SPLITTER, foodLevel, SPLITTER, world.getName(), SPLITTER, getIP());
    }
}
