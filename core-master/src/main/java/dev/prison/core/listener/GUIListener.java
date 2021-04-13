package dev.prison.core.listener;

import dev.prison.core.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;

        Player player = (Player) event.getWhoClicked();

        GUI openGUI = GUI.getOpenGUI(player);

        if (openGUI != null && event.getCurrentItem() != null) {
            event.getCurrentItem().getType();
            openGUI.getGUIItems().values().stream()
                    .filter(i -> i.getItemStack().isSimilar(event.getCurrentItem()))
                    .findFirst().ifPresent(item -> item.getClickConsumer().accept(event));

            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player))
            return;

        GUI openGUI = GUI.getOpenGUI((Player) event.getPlayer());

        if (openGUI != null)
            openGUI.close();
    }

    @EventHandler
    public void onInteract(InventoryInteractEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;

        GUI openGUI = GUI.getOpenGUI((Player) event.getWhoClicked());

        if (openGUI != null) {
            event.setResult(Event.Result.DENY);
            event.setCancelled(true);
        }
    }
}
