package dev.prison.core.command;

import dev.prison.core.CorePlugin;
import dev.prison.core.util.cuboid.Cuboid;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class Spawn implements CommandExecutor, Listener {

    private final CorePlugin plugin;
    private Location spawnLocation = null;
    private Cuboid spawnCuboid;

    public Spawn(CorePlugin plugin) {
        this.plugin = plugin;

        if (plugin.getConfig().contains("spawn.location")) {
            this.spawnLocation = plugin.getConfig().getLocation("spawn.location");
        }

        if (plugin.getConfig().contains("spawn.cuboid")) {
            //this.spawnCuboid = plugin.getConfig().getSerializable("spawn.cuboid", Cuboid.class);
        }
    }

    private Location tempPosOne, tempPosTwo;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Console can not use this command.");
            return true;
        }

        Player p = (Player) commandSender;

        if (spawnLocation == null && args.length == 0) {
            p.sendMessage(ChatColor.RED + "Spawn is not set. Set it using /spawn setlocation");
            return true;
        }

        if(args.length == 0 || !p.hasPermission("spawn.admin")){
            p.teleport(spawnLocation);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "setlocation": {
                spawnLocation = p.getLocation();
                plugin.getConfig().set("spawn.location", spawnLocation);
                plugin.saveConfig();
                p.sendMessage(ChatColor.GREEN + "Successfully set location");
                return true;
            }

            case "setpos1": {
                tempPosOne = p.getLocation();
                p.sendMessage(ChatColor.GREEN + "Successfully set position one of the spawn selection.");
                return true;
            }

            case "setpos2": {
                tempPosTwo = p.getLocation();
                p.sendMessage(ChatColor.GREEN + "Successfully set position two of the spawn selection.");
                return true;
            }

            case "claim": {
                if (tempPosOne == null || tempPosTwo == null) {
                    p.sendMessage(ChatColor.RED + "Corners not set, please set them using" +
                            " /spawn setpos1 & /spawn setpos2.");
                    return true;
                }


                spawnCuboid = new Cuboid(tempPosOne, tempPosTwo);
                plugin.getConfig().set("spawn.cuboid", spawnCuboid);
                plugin.saveConfig();
                p.sendMessage(ChatColor.GREEN + "Successfully claimed the spawn region.");
                return true;
            }

            default: {
                p.sendMessage(ChatColor.RED + "/spawn setlocation");
                p.sendMessage(ChatColor.RED + "/spawn setpos1");
                p.sendMessage(ChatColor.RED + "/spawn setpos2");
                p.sendMessage(ChatColor.RED + "/spawn claim");
                return true;
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (spawnCuboid != null && spawnCuboid.contains(event.getBlock())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot break blocks in spawn.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (spawnCuboid != null && spawnCuboid.contains(event.getBlock())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot place blocks in spawn.");
            event.setCancelled(true);
        }
    }
}
