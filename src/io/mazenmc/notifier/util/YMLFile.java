package io.mazenmc.notifier.util;

import io.mazenmc.notifier.NotifierPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class YMLFile {

    String fileName;
    File file;
    private FileConfiguration customConfig = null;
    private File customConfigFile = null;

    public YMLFile(String Name, String directory) {
        fileName = Name + ".yml";
        file = new File(NotifierPlugin.getPlugin().getDataFolder(), fileName);

        if(directory != null) {
            fileName = directory + File.separator + fileName;
            file = new File(NotifierPlugin.getPlugin().getDataFolder() + File.separator + directory, fileName);
        }
    }

    public boolean create() {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete() {
        return file.delete();
    }

    public String getName() {
        return file.getName().replaceAll(".yml", "");
    }

    public boolean exists() {
        return file.exists();
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        if (customConfig == null) {
            reloadConfig();
        }

        return customConfig;
    }

    public void reloadConfig() {
        if (customConfigFile == null) {
            customConfigFile = file;
        }

        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
    }

    public void saveConfig() {
        if (customConfig == null || customConfigFile == null) {
            return;
        }

        try {
            getConfig().save(customConfigFile);
        } catch (IOException ex) {
            NotifierPlugin.getPlugin().getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }

    public void saveDefaultConfig() {
        try{
            getFile().createNewFile();
            NotifierPlugin.getPlugin().saveResource(getName(), false);
        }catch(IOException ignored) {}
    }
}