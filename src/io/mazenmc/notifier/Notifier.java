package io.mazenmc.notifier;

import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.event.NotifierEventHandler;
import io.mazenmc.notifier.util.SettingsManager;

public class Notifier {

    /**
     * Gets EventHandlers instance from NotifierPlugin
     * @return EventHandler instance
     */
    public static NotifierEventHandler getEventHandler() {
        return NotifierPlugin.getEventHandler();
    }

    /**
     * Gets a client by their ID
     * @param id The ID to get the client by
     * @return Found client
     */
    public static NotifierClient getClient(int id) {
        return NotifierClient.getClient(id);
    }

    /**
     * Get the client by their username
     * @param username The String that the user goes by
     * @return Found client
     */
    public static NotifierClient getClient(String username) {
        return NotifierClient.getClient(username);
    }

    /**
     * Allows for a more code-clean method of creating packet arguments
     * @param args The arguments you wish to create
     * @return Generated packet arguments
     */
    public static String[] generatePacketArgs(String... args) {
        return args;
    }

    /**
     * Generates an empty set of packet arguments
     * @return Packet arguments
     */
    public static String[] emptyPacketArgs() {
        return new String[] {};
    }

    /**
     * Gets SettingsManager instance from NotifierPlugin
     * @return SettingManagers instance
     */
    public static SettingsManager getSettingsManager() {
        return NotifierPlugin.getSettingsManager();
    }
}
