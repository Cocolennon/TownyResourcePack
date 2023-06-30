package me.senkoco.townyresourcepacks.listeners;

import com.palmergames.bukkit.towny.event.player.PlayerExitsFromTownBorderEvent;
import me.senkoco.townyresourcepacks.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerExitTown implements Listener {
    @EventHandler
    public void onLeaveTown(PlayerExitsFromTownBorderEvent event){
        Player player = event.getPlayer();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "has-resource-pack");
        PersistentDataContainer container = player.getPersistentDataContainer();
        if(!container.has(key, PersistentDataType.STRING)) container.set(key, PersistentDataType.STRING, "false");
        if(container.get(key, PersistentDataType.STRING).equals("false")) return;

        player.setResourcePack("https://www.curseforge.com/api/v1/mods/457153/files/4572162/download"); // This is the default Minecraft 1.20 resource pack
        container.set(key, PersistentDataType.STRING, "true");

    }
}
