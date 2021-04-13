package dev.prison.core.listener;

import dev.prison.core.command.FreezeCommand;
import dev.prison.core.gui.impl.FreezeGUI;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16);

        if (FreezeCommand.FROZEN.contains(event.getPlayer().getUniqueId())) {
            event.getPlayer().teleport(event.getFrom());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (FreezeCommand.FROZEN.contains(event.getPlayer().getUniqueId())) {
            new FreezeGUI(event.getPlayer()).open();
        }
    }
}
