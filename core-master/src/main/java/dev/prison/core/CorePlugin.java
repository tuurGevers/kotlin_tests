package dev.prison.core;

import dev.prison.core.command.*;
import dev.prison.core.listener.FreezeListener;
import dev.prison.core.listener.GUIListener;
import dev.prison.core.listener.WelcomeListener;
import dev.prison.core.listener.ice;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CorePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCommand earrape = getCommand("earrape");

        if (earrape != null) {
            earrape.setExecutor(new EarRapeCommand(this));
        }

        PluginCommand clearItems = getCommand("clearitems");

        if (clearItems != null) {
            clearItems.setExecutor(new ClearItems());
        }

        PluginCommand vanish = getCommand("vanish");

        if (vanish != null) {
            vanish.setExecutor(new Vanish(this));
        }

        PluginCommand fly = getCommand("fly");

        if (fly != null) {
            fly.setExecutor(new Fly());
        }

        PluginCommand n = getCommand("nick");

        if (n != null) {
            Nick nick = new Nick(this);

            n.setExecutor(nick);
            Bukkit.getPluginManager().registerEvents(nick, this);
        }

        PluginCommand s = getCommand("spawn");

        if (s != null) {
            Spawn spawn = new Spawn(this);

            s.setExecutor(spawn);
            Bukkit.getPluginManager().registerEvents(spawn, this);
        }

        PluginCommand freeze = getCommand("freeze");

        if (freeze != null) {
            freeze.setExecutor(new FreezeCommand());
        }

        PluginCommand manageplayers = getCommand("manageplayers");

        if (manageplayers != null) {
            manageplayers.setExecutor(new ManagePlayersCommand());
        }

        Arrays.asList(new ice(), new FreezeListener(), new GUIListener(), new WelcomeListener())
                .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }
}
