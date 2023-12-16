package me.cocolennon.townyresourcepacks.listeners;

import me.cocolennon.townyresourcepacks.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoined implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Main.getInstance().checkVersion();
        Player player = event.getPlayer();
        if(!player.isOp()) return;
        if(Main.getInstance().getUsingOldVersion()) {
            player.sendMessage("§6[Towny Resource Packs] §eYou are using an older version of Towny Resource Packs, please update to version " + Main.getInstance().getLatestVersion());
        }
    }
}
