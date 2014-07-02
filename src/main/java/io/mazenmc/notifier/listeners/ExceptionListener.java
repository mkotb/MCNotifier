package io.mazenmc.notifier.listeners;

import io.mazenmc.notifier.Notifier;
import io.mazenmc.notifier.client.NotifierClient;
import io.mazenmc.notifier.packets.PacketExceptionThrown;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;

import java.lang.reflect.Field;
import java.util.logging.LogRecord;

public class ExceptionListener extends PluginLogger{

    private String pluginN;

    public ExceptionListener(Plugin context) {
        super(context);

        try{
            Field field = PluginLogger.class.getDeclaredField("pluginName");
            field.setAccessible(true);

            pluginN = (String) field.get(this);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void log(LogRecord logRecord) {
        handleLog(logRecord);
        super.log(logRecord);
    }

    private void handleLog(LogRecord record) {
        if(record.getThrown() != null) {
            for(NotifierClient client : NotifierClient.getClients()) {
                client.write(new PacketExceptionThrown(Notifier.generatePacketArgs(record.getThrown().getClass().getName(), record.getThrown().getMessage(), pluginN)));
            }
        }
    }

    public static void register() throws Exception{
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            ExceptionListener logger = new ExceptionListener(plugin);
            Field field = plugin.getClass().getDeclaredField("logger");

            field.setAccessible(true);
            field.set(plugin, logger);
        }
    }
}
