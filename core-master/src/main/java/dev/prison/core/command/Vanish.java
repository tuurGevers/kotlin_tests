package dev.prison.core.command;

import dev.prison.core.CorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Vanish implements CommandExecutor {
    private final CorePlugin plugin;

    public Vanish(CorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        ArrayList<Player> vList = new ArrayList();
        Player p = (Player) commandSender;

        if (p instanceof Player) {
            if (p.hasPermission("vanish.use")) {
                if (!vList.contains(p)) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(plugin, p);
                        vList.add(p);
                    }
                } else if(vList.contains(p)){
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(plugin, p);
                        vList.remove(p);
                    }
                }
            }
        }
        return true;
    }
}
