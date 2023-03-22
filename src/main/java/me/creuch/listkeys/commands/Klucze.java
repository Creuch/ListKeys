package me.creuch.listkeys.commands;

import me.creuch.listkeys.functions.connect;
import me.creuch.listkeys.Messages;
import me.creuch.listkeys.inventory.MainGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Klucze implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(command.getName().equalsIgnoreCase("klucze")) {
            if(commandSender instanceof Player) {
                MainGUI.buildInventory().show((HumanEntity) commandSender);
            } else {
                Messages.send(commandSender, "&cTej komendy mooże użyć tylko gracz!");
            }
        }
        return true;
    }
}
