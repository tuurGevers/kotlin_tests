package dev.prison.core.command;

import dev.prison.core.CorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EarRapeCommand implements CommandExecutor {

    private final CorePlugin plugin;

    public EarRapeCommand(CorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("earrape.use")) {
            commandSender.sendMessage(ChatColor.RED + "You do not have sufficient permissions.");
            return true;
        }

        if (strings.length != 2) {
            commandSender.sendMessage(ChatColor.RED + "Usage: /earrape <player> <sound>");
            return true;
        }

        Player player = Bukkit.getPlayer(strings[0]);

        if (player == null) {
            commandSender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        Sound sound;

        try {
            sound = Sound.valueOf(strings[1]);
        } catch (Exception ex) {
            commandSender.sendMessage(ChatColor.RED + "Sound not found");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "Have fun!");
        new BukkitRunnable() {
            int attempts = 0;

            @Override
            public void run() {
                if (!player.isOnline())
                    cancel();

                if (attempts++ > 50000)
                    cancel();

                player.openInventory(player.getEnderChest());
                player.playSound(player.getLocation(), sound, 100f, 1f);
                /*player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 100f, 1f);
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 100f, 1f);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 100f, 1f);
                player.playSound(player.getLocation(), Sound.ENTITY_CAT_DEATH, 100f, 1f);
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 100f, 1f);*/

            }
        }.runTaskTimer(plugin, 1L, 1L); // Elke 5 ticks
        return true;
    }
}
