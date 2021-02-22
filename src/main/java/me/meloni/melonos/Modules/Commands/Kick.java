package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import me.meloni.melonos.Modules.Messaging.Messenger;
import me.meloni.melonos.Modules.Moderator.ModeratorLevel;
import me.meloni.melonos.Modules.Player.Disconnect;
import me.meloni.melonos.Tools.TabAssistant;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class Kick extends Command{
    Messenger messenger;
    public Kick(MelonOS instance) {
        super(instance);
        this.messenger = instance.getMessenger();
    }

    @Override
    public List<String> getCommands() {
        return Collections.singletonList("kick");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        if (strings.length > 0) {

            if(strings[0].equalsIgnoreCase("everyone")) {
                if(commandSender.hasPermission("melonos.command.kick.everyone")) {
                    StringBuilder kickReason = new StringBuilder();
                    if(strings.length > 1) {
                        for(int i = 1; i < strings.length; i++) {
                            kickReason.append(strings[i]).append(" ");
                        }
                    }


                    Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                    Bukkit.getServer().getOnlinePlayers().toArray(players);
                    for (Player player : players) {
                        if (!(commandSender instanceof Player)) {
                            Disconnect.kick(player, kickReason.toString());
                        } else if (ModeratorLevel.isAllowed(commandSender, player)) {
                            Disconnect.kick(player, kickReason.toString());
                        }
                    }
                    if(!(commandSender instanceof Player) ) {
                        messenger.replyMessage("Du hast jeden Spieler vom Server geworfen.", commandSender);
                    } else if(ModeratorLevel.hasModeratorLevel((Player) commandSender)){
                        messenger.replyMessage("Du hast jeden Spieler vom Server geworfen, der unter deinem Moderatorlevel war.", commandSender);
                    } else {
                        messenger.replyMessage("Du hast jeden Spieler vom Server geworfen.", commandSender);
                    }
                    return true;
                } else {
                    messenger.noPermission("melonos.command.kick.everyone", commandSender);
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
                    messenger.deny("Du kannst den Spieler " + target.getName() + " nicht vom Server werfen!", commandSender);
                } else {
                    //execute as player
                    kick(commandSender, strings, target);
                }
            } else {
                //execute as console
                kick(commandSender, strings, target);
            }
        } else {
            messenger.notEnoughArgs(commandSender);
        }
        return true;
    }

    private void kick(CommandSender commandSender, String[] strings, Player target) {
        StringBuilder kickReason = new StringBuilder();
        if(strings.length > 1) {
            for(int i = 1; i < strings.length; i++) {
                kickReason.append(strings[i]).append(" ");
            }
        }
        Disconnect.kick(target, kickReason.toString());
        messenger.replyMessage(target.getName() + " wurde vom Server geworfen!", commandSender);
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
