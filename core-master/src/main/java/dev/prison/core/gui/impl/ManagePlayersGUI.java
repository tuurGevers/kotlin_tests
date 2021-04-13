package dev.prison.core.gui.impl;

import dev.prison.core.gui.GUI;
import dev.prison.core.gui.GUIItem;
import dev.prison.core.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ManagePlayersGUI extends GUI {

    public ManagePlayersGUI(Player player) {
        super(player);
    }

    @Override
    public String getTitle() {
        return "Manage Online Players";
    }

    @Override
    public Map<Integer, GUIItem> getGUIItems() {
        Map<Integer, GUIItem> guiItemMap = new HashMap<>();

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            guiItemMap.put(guiItemMap.size(), new GUIItem(new ItemBuilder(Material.PLAYER_HEAD)
                    .setSkullOwner(onlinePlayer.getName())
                    .setDisplayName("&bManage " + onlinePlayer.getName())
                    .setLore("&7Click to manage player.")
                    .build(), clickEvent -> {
                clickEvent.setCancelled(true);
                new ManagePlayerSubGUI(player, onlinePlayer).open();
            }));
        }

        return guiItemMap;
    }
}
