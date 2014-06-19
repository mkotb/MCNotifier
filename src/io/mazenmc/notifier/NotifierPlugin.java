package io.mazenmc.notifier;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEventHandler;
import io.mazenmc.notifier.packets.Packet;
import io.mazenmc.notifier.packets.PacketServerShutdown;
import io.mazenmc.notifier.server.NotifierServer;
import io.mazenmc.notifier.util.ClassFinder;
import io.mazenmc.notifier.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public class NotifierPlugin extends JavaPlugin {

    private static NotifierPlugin plugin;
    private static SettingsManager settingsManager;
    private static NotifierEventHandler notifierEventHandler;
    private static ClassFinder classFinder;

    @Override
    public void onEnable() {
        //Create new instances
        plugin = this;
        saveDefaultConfig();
        settingsManager = new SettingsManager();
        notifierEventHandler = new NotifierEventHandler();
        classFinder = new ClassFinder();
        Packet.registerAll();

        //Start the NotifierServer
        try {
            new NotifierServer().start();
        }catch(IOException ex) {
            getLogger().log(Level.SEVERE, "Unable to start NotifierServer!", ex);
            getLogger().info("Disabling plugin..");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        for(NotifierClient client : NotifierClient.getClients()) {
            client.write(new PacketServerShutdown(null));
        }

        plugin = null;
        settingsManager = null;
    }

    public static NotifierPlugin getPlugin() {
        return plugin;
    }

    public static SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public static NotifierEventHandler getEventHandler() {
        return notifierEventHandler;
    }

    public static ClassFinder getClassFinder() {
        return classFinder;
    }
}
