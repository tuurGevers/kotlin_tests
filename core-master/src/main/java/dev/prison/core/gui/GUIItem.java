package dev.prison.core.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class GUIItem {

    private final ItemStack itemStack;
    private final Consumer<InventoryClickEvent> clickConsumer;

    public GUIItem(ItemStack itemStack, Consumer<InventoryClickEvent> clickConsumer) {
        this.itemStack = itemStack;
        this.clickConsumer = clickConsumer;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Consumer<InventoryClickEvent> getClickConsumer() {
        return clickConsumer;
    }
}

