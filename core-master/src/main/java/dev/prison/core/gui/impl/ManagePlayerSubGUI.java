package dev.prison.core.gui.impl;

import dev.prison.core.gui.GUI;
import dev.prison.core.gui.GUIItem;
import dev.prison.core.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ManagePlayerSubGUI extends GUI {

    private final Player target;

    public ManagePlayerSubGUI(Player player, Player target) {
        super(player);
        this.target = target;
    }

    @Override
    public String getTitle() {
        return "Managing " + target.getName();
    }

    @Override
    public Map<Integer, GUIItem> getGUIItems() {
        Map<Integer, GUIItem> guiItemMap = new HashMap<>();

        guiItemMap.put(4, new GUIItem(new ItemBuilder(Material.BOOK)
                .setDisplayName("&bView inventory of " + target.getName())
                .setLore("&7Click to view " + target.getName() + "'s inventory")
                .build(), clickEvent -> {
            clickEvent.setCancelled(true);
            player.openInventory(target.getInventory());
        }));

        guiItemMap.put(11, new GUIItem(new ItemBuilder(Material.STICK)
                .setDisplayName("&bKick " + target.getName())
                .setLore("&7Click to kick " + target.getName())
                .build(), clickEvent -> {
            clickEvent.setCancelled(true);
            target.kickPlayer("Kicked by " + player.getName());
            player.closeInventory();
        }));

        guiItemMap.put(13, new GUIItem(new ItemBuilder(Material.APPLE)
                .setDisplayName("&bSet gamemode of " + target.getName())
                .setLore("&7Click to set gamemode of " + target.getName())
                .build(), clickEvent -> {
            clickEvent.setCancelled(true);
            new GameModeGUI(player, target).open();
        }));

        guiItemMap.put(15, new GUIItem(new ItemBuilder(Material.BEEF)
                .setDisplayName("&4Kill " + target.getName())
                .setLore("&7Click to kill " + target.getName())
                .build(), clickEvent -> {
            clickEvent.setCancelled(true);
            target.setHealth(0);
            player.closeInventory();
        }));

        return guiItemMap;
    }

    @Override
    public int getSize() {
        return 27;
    }
}
