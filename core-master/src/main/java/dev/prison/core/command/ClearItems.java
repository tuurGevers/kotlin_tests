package dev.prison.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import java.lang.String;

import java.util.List;

public class ClearItems implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = (Player) commandSender;

        World world = p.getWorld();
        int count = (int) world.getEntities().stream().filter(Item.class::isInstance).count();

        for(Entity e : world.getEntities()){
            if(e instanceof Item){
                e.remove();

            }

        }

        Bukkit.broadcastMessage(ChatColor.RED.toString() + count + " items verwijderd");


        return true;
    }

}
