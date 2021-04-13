package dev.prison.core.gui.impl;

import dev.prison.core.command.FreezeCommand;
import dev.prison.core.gui.GUI;
import dev.prison.core.gui.GUIItem;
import dev.prison.core.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FreezeGUI extends GUI {

    public FreezeGUI(Player player) {
        super(player);
    }

    @Override
    public String getTitle() {
        return "You have been frozen";
    }

    @Override
    public Map<Integer, GUIItem> getGUIItems() {
        Map<Integer, GUIItem> guiItemMap = new HashMap<>();

        guiItemMap.put(4, new GUIItem(new ItemBuilder(Material.ICE)
                .setDisplayName("&4&lYOU HAVE BEEN FROZEN")
                .setLore("&7You are not allowed to move!")
                .build(), click -> click.setCancelled(true)));

        return guiItemMap;
    }

    @Override
    public void close() {
        super.close();

        if (player.isOnline() && FreezeCommand.FROZEN.contains(player.getUniqueId()))
            open();
    }
}
