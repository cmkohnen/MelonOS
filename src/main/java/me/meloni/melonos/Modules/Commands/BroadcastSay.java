package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import me.meloni.melonos.Modules.Messaging.Messenger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class BroadcastSay extends Command{
    Messenger messenger;
    public BroadcastSay(MelonOS instance) {
        super(instance);
        this.messenger = instance.getMessenger();
    }

    @Override
    public List<String> getCommands() {
        return Arrays.asList("say", "broadcast");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(strings.length > 0) {
            if(!(commandSender instanceof Player)) {
                StringBuilder broadcast = new StringBuilder();
                for (String arg : strings) {
                    broadcast.append(arg).append(" ");
                }
                Messenger.broadcast(broadcast.toString());
            } else {
                if(command.getName().equalsIgnoreCase("say")) {
                    Player target = (Player) commandSender;
                    StringBuilder say = new StringBuilder();
                    for (String arg : strings) {
                        say.append(arg).append(" ");
                    }
                    target.chat(say.toString());
                    return true;
                } else if(command.getName().equalsIgnoreCase("broadcast")) {
                    StringBuilder broadcast = new StringBuilder();
                    for (String arg : strings) {
                        broadcast.append(arg).append(" ");
                    }
                    Messenger.broadcast(broadcast.toString());
                    return true;
                } else messenger.error(commandSender);
            }
            return true;
        }else {
            return false;
        }
    }
}
