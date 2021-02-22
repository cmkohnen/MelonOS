package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import me.meloni.melonos.Modules.Messaging.Messenger;
import me.meloni.melonos.Modules.Moderator.ModeratorLevel;
import me.meloni.melonos.Tools.TabAssistant;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class Kill extends Command{
    Messenger messenger;
    public Kill(MelonOS instance) {
        super(instance);
        this.messenger = instance.getMessenger();
    }

    @Override
    public List<String> getCommands() {
        return Collections.singletonList("kill");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if(strings.length == 0) {
                p.setHealth(0);
                messenger.replyMessage("Du hast Selbstmord begangen.", commandSender);
                return true;
            }
        }
        if (strings.length > 0) {
            if(!commandSender.hasPermission("melonos.command.kill.other")) {
                messenger.noPermission("melonos.command.kill.other", commandSender);
                return true;
            }


            if(strings[0].equalsIgnoreCase("everyone")) {
                if(commandSender.hasPermission("melonos.command.kill.everyone")) {
                    Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                    Bukkit.getServer().getOnlinePlayers().toArray(players);
                    for (Player player : players) {
                        if (!(commandSender instanceof Player)) {
                            player.setHealth(0);
                        } else if (ModeratorLevel.isAllowed(commandSender, player)) {
                            player.setHealth(0);
                        }
                    }
                    if(!(commandSender instanceof Player) ) {
                        messenger.replyMessage("Du hast jeden Spieler umgebracht.", commandSender);
                    } else if(ModeratorLevel.hasModeratorLevel((Player) commandSender)){
                        messenger.replyMessage("Du hast jeden Spieler umgebracht, der unter deinem Moderatorlevel war.", commandSender);
                    } else {
                        messenger.replyMessage("Du hast jeden Spieler umgebracht.", commandSender);
                    }
                    return true;
                } else {
                    messenger.noPermission("melonos.command.kill.everyone", commandSender);
                }
            }





            Player target;
            target = Bukkit.getPlayer(strings[0]);
            if(target == null) {
                messenger.replyMessage(strings[0] + " ist nicht online!", commandSender);
                return true;
            }
            if(commandSender instanceof Player) {
                if(!ModeratorLevel.isAllowed(commandSender, target)) {
                    messenger.deny("Du kannst den Spieler " + target.getName() + " nicht töten!", commandSender);
                } else {
                    //execute as player
                    target.setHealth(0);
                    messenger.replyMessage(target.getName() + " wurde getötet!", commandSender);
                }
            } else {
                //execute as console
                target.setHealth(0);
                messenger.replyMessage(target.getName() + " wurde getötet!", commandSender);
            }
        } else {
            messenger.deny("Du kannst dich nicht selbst töten!", commandSender);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(strings.length == 1) {
            TabAssistant tabAssistant = new TabAssistant(commandSender, strings[0]);
            tabAssistant.addAllowedTargets();
            tabAssistant.addIfHasPermission("everyonoe","melonos.command.kill.everyone");
            return tabAssistant.getArguments();
        }else {
            return null;
        }
    }
}
