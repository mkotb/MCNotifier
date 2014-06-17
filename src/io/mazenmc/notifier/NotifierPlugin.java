package io.mazenmc.notifier;

import io.mazenmc.notifier.event.NotifierEventHandler;
import io.mazenmc.notifier.server.NotifierServer;
import io.mazenmc.notifier.util.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public class NotifierPlugin extends JavaPlugin{

    private static NotifierPlugin plugin;
    private static SettingsManager settingsManager;
    private static NotifierEventHandler notifierEventHandler;

    @Override
    public void onEnable() {
        //Create new instances
        plugin = this;
        saveDefaultConfig();
        settingsManager = new SettingsManager();
        notifierEventHandler = new NotifierEventHandler();

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
}
