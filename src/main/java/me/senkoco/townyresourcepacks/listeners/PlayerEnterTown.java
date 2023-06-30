package me.senkoco.townyresourcepacks.listeners;

import com.palmergames.bukkit.towny.event.player.PlayerEntersIntoTownBorderEvent;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.utils.MetaDataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.senkoco.townyresourcepacks.utils.metadata.MetaData.getResourcePackLink;
import static me.senkoco.townyresourcepacks.utils.metadata.MetaData.resourcePackLink;

public class PlayerEnterTown implements Listener {
    @EventHandler
    public void onEnterTown(PlayerEntersIntoTownBorderEvent event){
        Player player = event.getPlayer();
        Town town = event.getEnteredTown();
        String resourcePackURL = "clear";
        if(MetaDataUtil.hasMeta(town, resourcePackLink)){
            resourcePackURL = getResourcePackLink(town);
        }

        if(resourcePackURL.equals("clear")){
            player.setResourcePack("https://www.curseforge.com/api/v1/mods/457153/files/4572162/download"); // This is the default Minecraft 1.20 resource pack
        }

        player.setResourcePack(resourcePackURL);
    }
}
