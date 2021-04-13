package dev.prison.core.gui.impl;

import dev.prison.core.gui.GUI;
import dev.prison.core.gui.GUIItem;
import dev.prison.core.util.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GameModeGUI extends GUI {

    private final Player target;

    public GameModeGUI(Player player, Player target) {
        super(player);
        this.target = target;
    }

    @Override
    public String getTitle() {
        return "Set " + target.getName() + "'s GameMode";
    }

    @Override
    public Map<Integer, GUIItem> getGUIItems() {
        Map<Integer, GUIItem> guiItemMap = new HashMap<>();

        guiItemMap.put(3, new GUIItem(new ItemBuilder(Material.GRASS_BLOCK)
                .setDisplayName("&a&lCreative")
                .setLore("&7Click to set gamemode to creative")
                .build(), click -> {
            click.setCancelled(true);
            target.setGameMode(GameMode.CREATIVE);
            player.closeInventory();
        }));
        guiItemMap.put(4, new GUIItem(new ItemBuilder(Material.DIAMOND_SWORD)
                .setDisplayName("&b&lSurvival")
                .setLore("&7Click to set gamemode to survival")
                .build(), click -> {
            click.setCancelled(true);
            target.setGameMode(GameMode.SURVIVAL);
            player.closeInventory();
        }));
        guiItemMap.put(5, new GUIItem(new ItemBuilder(Material.GRASS_BLOCK)
                .setDisplayName("&2&lAdventure")
                .setLore("&7Click to set gamemode to adventure")
                .build(), click -> {
            click.setCancelled(true);
            target.setGameMode(GameMode.ADVENTURE);
            player.closeInventory();
        }));

        return guiItemMap;
    }
}
