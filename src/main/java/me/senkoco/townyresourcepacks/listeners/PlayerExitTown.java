package me.senkoco.townyresourcepacks.listeners;

import com.palmergames.bukkit.towny.event.player.PlayerExitsFromTownBorderEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerExitTown implements Listener {
    @EventHandler
    public void onLeaveTown(PlayerExitsFromTownBorderEvent event){
        Player player = event.getPlayer();

        player.setResourcePack("https://www.curseforge.com/api/v1/mods/457153/files/4572162/download"); // This is the default Minecraft 1.20 resource pack
    }
}
