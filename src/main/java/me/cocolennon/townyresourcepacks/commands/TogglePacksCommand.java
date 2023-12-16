package me.cocolennon.townyresourcepacks.commands;

import me.cocolennon.townyresourcepacks.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class TogglePacksCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "toggle-resource-packs");
        PersistentDataContainer container = player.getPersistentDataContainer();

        if(!container.has(key, PersistentDataType.BOOLEAN)) {
            container.set(key, PersistentDataType.BOOLEAN, false);
            player.sendMessage("§6[Towny Resource Packs] §eYou will no longer receive resource packs");
            return true;
        }
        boolean toggle = container.get(key, PersistentDataType.BOOLEAN);
        if(toggle) {
            container.set(key, PersistentDataType.BOOLEAN, false);
            player.sendMessage("§6[Towny Resource Packs] §eYou will no longer receive resource packs");
        }else{
            container.set(key, PersistentDataType.BOOLEAN, true);
            player.sendMessage("§6[Towny Resource Packs] §eYou will receive resource packs again");
        }
        return true;
    }
}
