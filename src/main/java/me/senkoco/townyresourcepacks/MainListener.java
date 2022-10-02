package me.senkoco.townyresourcepacks;

import com.palmergames.bukkit.towny.event.PlayerEnterTownEvent;
import com.palmergames.bukkit.towny.event.PlayerLeaveTownEvent;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.utils.MetaDataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.senkoco.townyresourcepacks.utils.metadata.MetaData.getResourcePackLink;
import static me.senkoco.townyresourcepacks.utils.metadata.MetaData.resourcePackLink;

public class MainListener implements Listener {

    @EventHandler
    public void onEnterTown(PlayerEnterTownEvent event){
        Player player = event.getPlayer();
        Town town = event.getEnteredtown();
        String resourcePackURL = "clear";
        if(MetaDataUtil.hasMeta(town, resourcePackLink)){
            resourcePackURL = getResourcePackLink(town);
        }

        if(resourcePackURL == "clear"){
            player.setResourcePack("");
        }

        player.setResourcePack(resourcePackURL);
    }

    @EventHandler
    public void onLeaveTown(PlayerLeaveTownEvent event){
        Player player = event.getPlayer();

        player.setResourcePack("");
    }
}
