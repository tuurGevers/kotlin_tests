package dev.prison.core.command;

import dev.prison.core.CorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Nick implements Listener, CommandExecutor {

    private final CorePlugin plugin;

    public Nick(CorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;

        if (!commandSender.hasPermission("nick.use")) {
            p.sendMessage(ChatColor.RED + "You do not have permissions.");
            return true;
        }

        if(args.length == 0){
            p.sendMessage("no nickname given");
            return true;
        }

        if(args[0].equalsIgnoreCase("off")){
            plugin.getConfig().set(p.getName(),null);
            plugin.saveConfig();
            p.sendMessage(ChatColor.GOLD + "nickname uit");
            return true;
        }

        if(args[0].equalsIgnoreCase("milan")){
            plugin.getConfig().set(p.getName(), "Ilhan is pedo badlion client exposed");
            plugin.saveConfig();
            p.sendMessage(ChatColor.GOLD + "nickname veranderd");
            return true;
        }

        plugin.getConfig().set(p.getName(), args[0]);
        plugin.saveConfig();
        p.sendMessage(ChatColor.GOLD + "Nickname veranderd!");
        return true;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (plugin.getConfig().contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("<" + plugin.getConfig().getString(event.getPlayer().getName(), event.getPlayer().getName()) + "> " + event.getMessage());
            }

        }
    }
}

