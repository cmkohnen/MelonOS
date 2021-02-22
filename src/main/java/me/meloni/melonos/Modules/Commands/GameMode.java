package me.meloni.melonos.Modules.Commands;

import me.meloni.melonos.MelonOS;
import me.meloni.melonos.Modules.Messaging.Messenger;
import me.meloni.melonos.Modules.Moderator.ModeratorLevel;
import me.meloni.melonos.Modules.Player.Online;
import me.meloni.melonos.Tools.TabAssistant;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class GameMode extends Command{
    Messenger messenger;
    public GameMode(MelonOS instance) {
        super(instance);
        this.messenger = instance.getMessenger();
    }

    @Override
    public List<String> getCommands() {
        return Collections.singletonList("gamemode");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length >= 1) {
            org.bukkit.GameMode gameMode;
            if (strings[0].equalsIgnoreCase("creative") || strings[0].equalsIgnoreCase("1")) {
                gameMode = org.bukkit.GameMode.CREATIVE;
            } else if (strings[0].equalsIgnoreCase("survival") || strings[0].equalsIgnoreCase("0")) {
                gameMode = org.bukkit.GameMode.SURVIVAL;
            } else if (strings[0].equalsIgnoreCase("spectator") || strings[0].equalsIgnoreCase("3")) {
                gameMode = org.bukkit.GameMode.SPECTATOR;
            } else if (strings[0].equalsIgnoreCase("adventure") || strings[0].equalsIgnoreCase("2")) {
                gameMode = org.bukkit.GameMode.ADVENTURE;
            } else {messenger.replyMessage("Diesen Modus gibt es nicht",commandSender); return true;}

            if (strings.length == 1) {
                if (commandSender instanceof Player) {
                    if(commandSender.hasPermission("melonos.command.gamemode.self." + gameMode.toString().toLowerCase())) {
                        ((Player) commandSender).setGameMode(gameMode);
                    } else {
                        messenger.noPermission("melonos.command.gamemode." + gameMode.toString().toLowerCase(), commandSender);
                    }
                } else {
                    messenger.noPlayer();
                }
            } else {
                if(commandSender.hasPermission("melonos.command.gamemode.other")) {

                    if (Online.isOnline(strings[1])) {

                        Player target = Bukkit.getPlayer(strings[1]);
                        if (ModeratorLevel.isAllowed(commandSender, target)) {
                            Objects.requireNonNull(target).setGameMode(gameMode);

                        } else {
                            messenger.replyMessage("Du darfst diesen Spielers Spielmodus nicht verändern.", commandSender);
                        }
                    } else {
                        messenger.playerOffline(strings[1],commandSender);
                    }
                } else {messenger.noPermission("melonos.command.gamemode.other", commandSender);}
            }


        } else messenger.notEnoughArgs(commandSender);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        TabAssistant tabAssistant = new TabAssistant(commandSender, strings[0]);
        if(strings.length == 1) {
            tabAssistant.addMultipleIfHasPermissions(Arrays.asList("survival","0"),Arrays.asList("melonos.command.gamemode.self.survival","melonos.command.gamemode.other.survival"));
            tabAssistant.addMultipleIfHasPermissions(Arrays.asList("creative","1"),Arrays.asList("melonos.command.gamemode.self.creative","melonos.command.gamemode.other.creative"));
            tabAssistant.addMultipleIfHasPermissions(Arrays.asList("adventure","2"),Arrays.asList("melonos.command.gamemode.self.adventure","melonos.command.gamemode.other.adventure"));
            tabAssistant.addMultipleIfHasPermissions(Arrays.asList("spectator","3"),Arrays.asList("melonos.command.gamemode.self.spectator","melonos.command.gamemode.other.spectator"));
        } else if(strings.length == 2){
            tabAssistant.addAllowedTargetsIfHasPermission("melonos.command.gamemode.self.other");
        }
        return tabAssistant.getArguments();
    }
}
