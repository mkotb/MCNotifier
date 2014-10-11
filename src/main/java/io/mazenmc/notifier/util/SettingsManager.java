/*
* Copyright (C) 2014 Mazen K.
* This file is part of MCNotifier.
*
* MCNotifier for Bukkit is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, version 3 to be exact
*
* MCNotifier is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with MCNotifier. If not, see <http://www.gnu.org/licenses/>.
*/

package io.mazenmc.notifier.util;

import org.bukkit.Material;

import java.util.HashMap;

public class SettingsManager {

    private final YMLFile config = new YMLFile("config", null);
    private final HashMap<String, char[]> userData = new HashMap<>();

    public SettingsManager() {
        for (String s : config.getConfig().getStringList("users")) {
            String[] data = s.split(":");

            userData.put(data[0], data[1].toCharArray());
        }
    }

    /**
     * Gets all the user data defined in config loaded at startup
     *
     * @return The User data
     */
    public HashMap<String, char[]> getUserData() {
        return userData;
    }

    /**
     * Gets if the command provided is suspicious through the config file
     *
     * @param command The command you wish to check
     * @return If the command is suspicious or not
     */
    public boolean isSuspiciousCommand(String command) {
        return config.getConfig().getStringList("suspicious-commands").contains(command);
    }

    /**
     * Gets if the material provided is suspicious according to the configuration file
     *
     * @param material The material you wish to check
     * @return If the material is suspicious or not
     */
    public boolean isBlockSuspicious(Material material) {
        for (String s : config.getConfig().getStringList("suspicious-blocks")) {
            if (Material.matchMaterial(s) == material) {
                return true;
            }
        }

        return false;
    }
}
