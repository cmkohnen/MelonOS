package me.meloni.melonos.Modules.Messaging;

import me.meloni.melonos.MelonOS;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.Collections;
import java.util.List;

public class StartMessage {
    public static String startMessage(MelonOS instance) {
        PluginDescriptionFile pdf = instance.getPdf();
        StringBuilder message = new StringBuilder();
        message.append(instance.getTranslationMap().get("welcome-message"));
        message.append("\n|\n|\n|        __  __          _                    ____     _____ \n|       |  \\/  |        | |                  / __ \\   / ____|\n|       | |\\/| |  / _ \\ | |  / _ \\  | '_ \\  | |  | |  \\___ \\ \n|       | |  | | |  __/ | | | (_) | | | | | | |__| |  ____) |\n|       |_|  |_|  \\___| |_|  \\___/  |_| |_|  \\____/  |_____/ \n|\n|");
        message.append("\n|\n|\n|          by ");
        for (List<String> strings : Collections.singletonList(pdf.getAuthors())) {
            for (String string : strings) {
                message.append(string).append(" ");
            }
        }
        message.append("\n|          version ").append(pdf.getVersion());
        message.append("\n|          ").append(pdf.getCommands().size()).append(" commands loaded");
        message.append("\n|          ").append(instance.getListenerManager().listeners().size()).append(" listeners loaded");
        message.append("\n|          ").append(instance.getPluginManager().getPlugins().length).append(" plugins loaded");
        message.append("\n|\n|");

        return message.toString();
    }
}
