package dev.prison.core.listener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WelcomeListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    Player player= event.getPlayer();

    if(player.getDisplayName().equals("mathijs777")){
        event.setJoinMessage(ChatColor.LIGHT_PURPLE + "milan outranked u in overwatch" + player.getDisplayName());
    }
    else{
        event.setJoinMessage(ChatColor.AQUA + "fakaaaaaaa " + player.getDisplayName());

    }
    }
}
