package me.senkoco.townyresourcepacks.commands;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static me.senkoco.townyresourcepacks.utils.metadata.MetaData.setResourcePackLink;

public class CommandResourcePack implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        Player player = (Player)sender;
        Resident res = TownyAPI.getInstance().getResident(player);
        if(res == null) {
            sender.sendMessage("§6[Towny Resource Packs] §cCouldn't find resident!");
            return false;
        }
        if(!res.hasTown()) {
            sender.sendMessage("§6[Towny Resource Packs] §cYou aren't in a town!");
            return false;
        }
        if(!res.isMayor()){
            sender.sendMessage("§6[Towny Resource Packs] §cYou aren't the mayor of your town!");
            return false;
        }
        if(args.length == 0) {
            sender.sendMessage("§6[Towny Resource Packs] §cPlease provide a URL!");
            return false;
        }
        if(!isURLValid(args[0])){
            sender.sendMessage("§6[Towny Resource Packs] §cPlease provide a valid URL!");
            return false;
        }
        Town town = res.getTownOrNull();

        assert town != null;
        if(args[0].equalsIgnoreCase("clear")){
            setResourcePackLink(town, "clear");
            sender.sendMessage("§6[Towny Resource Packs] §eSuccessfully cleared your town's resource pack!");
            return true;
        }

        setResourcePackLink(town, args[0]);
        sender.sendMessage("§6[Towny Resource Packs] §eYou have successfully set your town's resource pack!\n§eYou may have to exit and re-enter your claims for the resource pack to set!");
        return true;
    }

    private static boolean isURLValid(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        }catch (IOException e) {
            return false;
        }
    }
}
