package dev.prison.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Fly implements CommandExecutor {
    ArrayList<Player> flyList = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;

        if (commandSender instanceof Player) {
            if (p.hasPermission("fly.use")) {
                if (args.length == 0) {
                    if (flyList.contains(p)) {
                        p.setAllowFlight(false);
                        p.sendMessage("you can no longer fly");
                        return true;
                    }
                    p.setAllowFlight(true);
                    p.sendMessage("you can now fly");
                } else {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(p.hasPermission("fly_others.use"))
                    if (target instanceof Player) {
                        if (flyList.contains(target)) {
                            target.setAllowFlight(false);
                            target.sendMessage("you can no longer fly");
                            return true;
                        }
                        target.setAllowFlight(true);
                        target.sendMessage("you can now fly");
                        return true;

                    }
                }
                return true;

            }

        }
        return true;
    }
}
