package io.mazenmc.notifier.packets;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Packet which the server will respond to on the call of PacketWorldDataRequest
 */
public class PacketWorldDataResponse extends Packet{

    private static final int id = 9;
    private World world;
    private int entityCount;
    private int entityMax;
    private Difficulty difficulty;
    private Time time;
    private WorldType worldType;
    private List<Player> players;

    public PacketWorldDataResponse(String[] args) {
        world = Bukkit.getWorld(args[0]);
        entityCount = world.getLivingEntities().size();
        entityMax = world.getAnimalSpawnLimit();
        difficulty = world.getDifficulty();
        time = (world.getFullTime() > 12000) ? Time.NIGHT : Time.DAY;
        worldType = world.getWorldType();
        players = world.getPlayers();
    }

    public int getEntityCount() {
        return entityCount;
    }

    public int getEntityMax() {
        return entityMax;
    }

    public World getWorld() {
        return world;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Time getTime() {
        return time;
    }

    public WorldType getWorldType() {
        return worldType;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void handle() {
        //No handling.
    }

    public static int getID() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        sb.append(id);
        sb.append(" ");
        sb.append(entityCount);
        sb.append(" ");
        sb.append(entityMax);
        sb.append(" ");
        sb.append(difficulty);
        sb.append(" ");
        sb.append(time);
        sb.append(" ");
        sb.append(worldType);
        sb.append(" ");

        StringBuilder playerBuilder = new StringBuilder("");
        for(Player player : players) {
            playerBuilder.append(player.getName());
            playerBuilder.append(',');
        }

        sb.append(playerBuilder.toString());

        return sb.toString();
    }

    public enum Time {
        DAY, NIGHT
    }
}
