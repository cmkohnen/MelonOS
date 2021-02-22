package me.meloni.melonos.Modules.Config;

import me.meloni.melonos.MelonOS;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationManager {
    private final MelonOS instance;
    private final FileConfiguration fileConfiguration;
    public ConfigurationManager(MelonOS instance) {
        this.instance = instance;
        this.fileConfiguration = instance.getConfig();
        fileConfiguration.options().copyDefaults(true);
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

}
