package me.meloni.melonos;

import me.meloni.melonos.Modules.Commands.CommandManager;
import me.meloni.melonos.Modules.Config.ConfigurationManager;
import me.meloni.melonos.Modules.Language.TranslationMap;
import me.meloni.melonos.Modules.Listeners.ListenerManager;
import me.meloni.melonos.Modules.Messaging.Messenger;
import me.meloni.melonos.Modules.Messaging.StartMessage;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MelonOS extends JavaPlugin {
    private final PluginDescriptionFile pdf = this.getDescription();
    private final PluginManager pluginManager = getServer().getPluginManager();
    private final CommandManager commandManager = new CommandManager(this);
    private final ListenerManager listenerManager = new ListenerManager(this);
    private final TranslationMap translationMap = new TranslationMap(this);
    private final Messenger messenger = new Messenger(this);
    private final ConfigurationManager configurationManager = new ConfigurationManager(this);

    @Override
    public void onEnable() {
        commandManager.registerCommands();
        listenerManager.registerEvents();
        Messenger.log(StartMessage.startMessage(this));
    }

    @Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    }

    public PluginDescriptionFile getPdf() {
        return pdf;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ListenerManager getListenerManager() {
        return listenerManager;
    }

    public TranslationMap getTranslationMap() {return translationMap; }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public Messenger getMessenger() {return messenger;}
}
