package dev.prison.core.command;

import dev.prison.core.gui.impl.FreezeGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.UUID;

public class FreezeCommand implements CommandExecutor {

    public static final Collection<UUID> FROZEN = new ArrayDeque<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /freeze <player>");
            return true;
        }

        Player player = Bukkit.getPlayer(strings[0]);

        if (player == null) {
            commandSender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        if (FROZEN.remove(player.getUniqueId())) {
            player.sendMessage(ChatColor.AQUA + "You have been unfrozen.");
            player.closeInventory();
            commandSender.sendMessage(ChatColor.GREEN + "Successfully unfroze " + player.getDisplayName());
            return true;
        }

        FROZEN.add(player.getUniqueId());
        player.sendMessage(ChatColor.AQUA + "You have been frozen.");
        new FreezeGUI(player).open();
        commandSender.sendMessage(ChatColor.GREEN + "Successfully froze " + player.getDisplayName());
        return true;
    }
}
