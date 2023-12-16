package me.cocolennon.townyresourcepacks.listeners;

import com.palmergames.bukkit.towny.event.player.PlayerEntersIntoTownBorderEvent;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.utils.MetaDataUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import me.cocolennon.townyresourcepacks.Main;
import org.bukkit.persistence.PersistentDataType;

import static me.cocolennon.townyresourcepacks.utils.metadata.MetaData.getResourcePackLink;
import static me.cocolennon.townyresourcepacks.utils.metadata.MetaData.resourcePackLink;

public class PlayerEnterTown implements Listener {
    @EventHandler
    public void onEnterTown(PlayerEntersIntoTownBorderEvent event){
        Player player = event.getPlayer();
        Town town = event.getEnteredTown();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "has-resource-pack");
        PersistentDataContainer container = player.getPersistentDataContainer();

        String resourcePackURL = "clear";
        if(MetaDataUtil.hasMeta(town, resourcePackLink)){
            resourcePackURL = getResourcePackLink(town);
        }

        if(resourcePackURL.equals("clear")){
            player.setResourcePack("https://www.curseforge.com/api/v1/mods/457153/files/4572162/download"); // This is the default Minecraft 1.20 resource pack
        }

        player.setResourcePack(resourcePackURL);
        container.set(key, PersistentDataType.STRING, "true");
    }
}
