package io.mazenmc.notifier.util;

import java.util.HashMap;

public class SettingsManager {

    private final YMLFile config = new YMLFile("config", null);
    private final HashMap<String, char[]> userData = new HashMap<>();

    public SettingsManager() {
        for(String s : config.getConfig().getStringList("users")) {
            String[] data = s.split(":");

            userData.put(data[0], data[1].toCharArray());
        }
    }

    /**
     * Gets all the user data defined in config loaded at startup
     * @return The User data
     */
    public HashMap<String, char[]> getUserData() {
        return userData;
    }

    /**
     * Gets if the command provided is suspicious through the config file
     * @param command The command you wish to check
     * @return If the command is suspicious or not
     */
    public boolean isSuspiciousCommand(String command) {
        return config.getConfig().getStringList("suspicious-commands").contains(command);
    }
}
