import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MelonOS extends JavaPlugin {
    private final PluginDescriptionFile pdf = this.getDescription();
    private final PluginManager pluginManager = getServer().getPluginManager();
    private final CommandManager commandManager = new CommandManager(this);
    private final ListenerManager listenerManager = new ListenerManager(this);

    @Override
    public void onEnable() {
        commandManager.registerCommands();
        listenerManager.registerEvents();
        Bukkit.getLogger().info(startmsg());
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

    private String startmsg(){
        String message = "MelonOS erfolgreich geladen. ";
        message = message + "\n|\n|\n|        __  __          _                    ____     _____ \n|       |  \\/  |        | |                  / __ \\   / ____|\n|       | |\\/| |  / _ \\ | |  / _ \\  | '_ \\  | |  | |  \\___ \\ \n|       | |  | | |  __/ | | | (_) | | | | | | |__| |  ____) |\n|       |_|  |_|  \\___| |_|  \\___/  |_| |_|  \\____/  |_____/ \n|\n|";
        String author = pdf.getAuthors().toString();
        String version = pdf.getVersion();
        String commands = Integer.toString(commandManager.commands().size());
        String listeners = Integer.toString(listenerManager.listeners().size());
        message = message + "\n|\n|\n|          by " + author + "\n|          version " + version + "\n|          "+ commands + " commands loaded\n|          " + listeners + " listeners loaded\n|\n|";
        return message;
    }
}
