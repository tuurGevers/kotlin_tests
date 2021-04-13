package dev.prison.core.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemBuilder implements Cloneable {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private String headValue = "";

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, short subID) {
        this.itemStack = new ItemStack(material, 1, subID);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int subID) {
        this.itemStack = new ItemStack(material, 1, (short) subID);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String name) {
        this.itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.itemMeta.setLore(Arrays.stream(lore)
                .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                .collect(Collectors.toList()));
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.itemMeta.setLore(lore.stream()
                .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                .collect(Collectors.toList()));
        return this;
    }

    public ItemBuilder addToLore(String... entries) {
        List<String> lore = this.itemMeta.hasLore() ? this.itemMeta.getLore() : new ArrayList();
        lore.addAll(Arrays.stream(entries)
                .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                .collect(Collectors.toList()));
        this.itemMeta.setLore(lore);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder addStored(Enchantment enchantment, int level) {
        EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) this.itemMeta;
        enchantmentStorageMeta.addStoredEnchant(enchantment, level, true);
        this.itemStack.setItemMeta(enchantmentStorageMeta);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setUnbreakable(Boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        if (this.itemMeta instanceof SkullMeta) {
            ((SkullMeta) this.itemMeta).setOwner(owner);
        }

        return this;
    }

    public ItemBuilder setArmorColor(Color color) {
        if (this.itemMeta instanceof LeatherArmorMeta) {
            ((LeatherArmorMeta) this.itemMeta).setColor(color);
        }

        return this;
    }

    public ItemBuilder setData(short data) {
        this.itemStack.setDurability(data);
        return this;
    }

    public ItemBuilder setData(int data) {
        this.itemStack.setDurability((short) data);
        return this;
    }

    public ItemBuilder setBookAuthor(String author) {
        if (this.itemMeta instanceof BookMeta) {
            ((BookMeta) this.itemMeta).setAuthor(author);
        }

        return this;
    }

    public ItemBuilder setBookTitle(String title) {
        if (this.itemMeta instanceof BookMeta) {
            ((BookMeta) this.itemMeta).setTitle(title);
        }

        return this;
    }

    public ItemBuilder setHeadValue(String value) {
        this.headValue = value;
        return this;
    }

    public static void setHeadFromValue(String value, ItemStack itemStack) {
        UUID hashAsId = new UUID(value.hashCode(), value.hashCode());
        Bukkit.getUnsafe().modifyItemStack(itemStack,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + value + "\"}]}}}"
        );
    }

    public ItemStack build() {
        if (headValue.isEmpty())
            this.itemStack.setItemMeta(this.itemMeta);
        else {
            ItemBuilder.setHeadFromValue(headValue, itemStack);
            SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
            itemMeta.setDisplayName(this.itemMeta.getDisplayName());

            if (this.itemMeta.getLore() != null)
                itemMeta.setLore(this.itemMeta.getLore());

            itemStack.setItemMeta(itemMeta);
        }

        return this.itemStack;
    }

    public ItemStack buildClone() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack.clone();
    }

    public ItemBuilder clone() {
        return new ItemBuilder(this.buildClone());
    }
}