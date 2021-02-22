package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import me.meloni.melonos.Modules.Messaging.Messenger;
import me.meloni.melonos.Modules.Messaging.StartMessage;
import me.meloni.melonos.Modules.ServerControl.ServerController;
import me.meloni.melonos.Tools.TabAssistant;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class MainCommand extends Command {
    MelonOS instance;
    public MainCommand(MelonOS instance) {
        super(instance);
        this.instance = instance;
    }

    @Override
    public List<String> getCommands() {
        return Collections.singletonList("melonos");
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        Messenger messenger = getInstance().getMessenger();
        String usage ="/melonos [help/version/info";
        if(sender.hasPermission("melonos.admin.reload")) {
            usage = usage + "/reload";
        }
        if(sender.hasPermission("melonos.admin.shutdown")) {
            usage = usage + "/shutdown";
        }
        usage = usage + "]";


        if(args.length == 0) {
            messenger.replyMessage("/melonos help für weitere Hilfe.", sender);
            messenger.replyMessage(ChatColor.WHITE + usage, sender);
            return true;
        }
        else {
            if(args[0].equalsIgnoreCase("version")) {
                messenger.replyMessage("Version " + instance.getPdf().getVersion(), sender);
                return true;

            }else if(args[0].equalsIgnoreCase("help")){
                if(args.length == 1) {
                    StringBuilder help = new StringBuilder();
                    help.append("/melonos help §f| Zeigt dieses Menü");
                    help.append("/melonos version §f| Zeigt die aktuelle MelonOS Version");
                    if(sender.hasPermission("melonos.admin.reload")) {
                        help.append("\n").append(Messenger.prefix).append("/melonos reload §f| Lädt den Server neu");
                    }
                    if(sender.hasPermission("melonos.admin.shutdown")) {
                        help.append("\n").append(Messenger.prefix).append("/melonos shutdown §f| Fährt den Server herunter");
                    }
                    messenger.replyMessage(ChatColor.WHITE + usage, sender);
                    messenger.replyMessage(help.toString(), sender);
                    return true;
                } else {
                    return false;
                }
            }
            else if(args[0].equalsIgnoreCase("reload")) {
                if(sender.hasPermission("melonos.admin.reload")) {
                    messenger.replyMessage("Lade den Server neu...", sender);
                    ServerController.reload();
                    messenger.replyMessage("Server neu geladen!", sender);
                }else {
                    messenger.noPermission("melonos.admin.reload", sender);
                }
                return true;
            }
            else if(args[0].equalsIgnoreCase("shutdown")) {
                if(sender.hasPermission("melonos.admin.shutdown")) {
                    ServerController.shutdown();
                }else {
                    messenger.noPermission("melonos.admin.shutdown", sender);
                }
                return true;
            } else if(args[0].equalsIgnoreCase("info")) {
                messenger.replyMessage(StartMessage.startMessage(instance), sender);
                return true;
            } else {
                messenger.replyMessage("/melonos help für weitere Hilfe.", sender);
                messenger.replyMessage(ChatColor.WHITE + usage, sender);
                return true;
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {

        if(args.length == 1) {
            TabAssistant tabAssistant = new TabAssistant(sender, args[0]);
            tabAssistant.add("version");
            tabAssistant.add("help");
            tabAssistant.add("info");
            tabAssistant.addIfHasPermission("reload","melonos.admin.reload");
            tabAssistant.addIfHasPermission("shutdown", "melonos.admin.shutdown");
            return tabAssistant.getArguments();
        } else {
            return null;
        }
    }
}
