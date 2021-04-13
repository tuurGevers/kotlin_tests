package dev.prison.core.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.WeakHashMap;

public abstract class GUI {

    private static final Map<Player, GUI> GUI_MAP = new WeakHashMap<>();

    public static GUI getOpenGUI(Player player) {
        return GUI_MAP.get(player);
    }

    public final Player player;
    private Inventory inventory;
    private Map<Integer, GUIItem> items;

    public GUI(Player player) {
        this.player = player;
    }

    public void open() {
        this.items = getGUIItems();
        this.inventory = Bukkit.createInventory(null, getSize(), getTitle());
        items.forEach((slot, item) -> inventory.setItem(slot, item.getItemStack()));

        player.openInventory(inventory);
        GUI_MAP.put(player, this);
    }

    public void close() {
        GUI_MAP.remove(player);
    }

    public abstract String getTitle();

    public abstract Map<Integer, GUIItem> getGUIItems();

    public int getSize() {
        return calcSize();
    }

    public int calcSize() {
        int highest = 0;

        for (Integer integer : items.keySet())
            if (integer > highest)
                highest = integer;

        return (int)(Math.ceil((double)(highest + 1) / 9D) * 9D);
    }

    public Map<Integer, GUIItem> getItems() {
        return items;
    }
}

