package me.cocolennon.townyresourcepacks.listeners;

import com.palmergames.bukkit.towny.event.player.PlayerExitsFromTownBorderEvent;
import me.cocolennon.townyresourcepacks.Main;
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

        String defaultPack = Main.getInstance().getConfig().getString("default-resource-pack");
        player.setResourcePack(defaultPack);
        container.set(key, PersistentDataType.STRING, "true");

    }
}
