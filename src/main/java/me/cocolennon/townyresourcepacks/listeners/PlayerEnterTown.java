package me.cocolennon.townyresourcepacks.listeners;

import com.palmergames.bukkit.towny.event.player.PlayerEntersIntoTownBorderEvent;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.metadata.StringDataField;
import com.palmergames.bukkit.towny.utils.MetaDataUtil;
import me.cocolennon.townyresourcepacks.utils.metadata.MetaData;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import me.cocolennon.townyresourcepacks.Main;
import org.bukkit.persistence.PersistentDataType;

public class PlayerEnterTown implements Listener {
    StringDataField resourcePackLink = MetaData.getInstance().resourcePackLink;

    @EventHandler
    public void onEnterTown(PlayerEntersIntoTownBorderEvent event){
        Player player = event.getPlayer();
        Town town = event.getEnteredTown();
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "has-resource-pack");
        NamespacedKey toggle = new NamespacedKey(Main.getInstance(), "toggle-resource-packs");
        PersistentDataContainer container = player.getPersistentDataContainer();

        if(container.has(toggle, PersistentDataType.BOOLEAN) && !container.get(toggle, PersistentDataType.BOOLEAN)) return;

        String resourcePackURL = "clear";
        if(MetaDataUtil.hasMeta(town, resourcePackLink)){
            resourcePackURL = MetaData.getInstance().getResourcePackLink(town);
        }

        if(resourcePackURL.equals("clear")){
            String defaultPack = Main.getInstance().getConfig().getString("default-resource-pack");
            player.setResourcePack(defaultPack);
        }

        player.setResourcePack(resourcePackURL);
        container.set(key, PersistentDataType.STRING, "true");
    }
}
