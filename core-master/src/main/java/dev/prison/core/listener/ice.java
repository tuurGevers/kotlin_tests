package dev.prison.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPhysicsEvent;

public class ice implements Listener {

    @EventHandler
    public void onMelt(BlockFadeEvent event) {
        if (event.getBlock().getType() == Material.ICE)
            event.setCancelled(true);
    }
}
