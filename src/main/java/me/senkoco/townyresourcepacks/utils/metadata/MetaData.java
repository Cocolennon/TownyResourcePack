package me.senkoco.townyresourcepacks.utils.metadata;

import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.metadata.StringDataField;
import com.palmergames.bukkit.towny.utils.MetaDataUtil;

public class MetaData {
    public static StringDataField resourcePackLink = new StringDataField("townyresourcepack_link");

    public static String getResourcePackLink(Town town){
        return MetaDataUtil.getString(town, resourcePackLink);
    }

    public static void setResourcePackLink(Town town, String resourceLink){
        if(!town.hasMeta("townyresourcepack_link")){
            MetaDataUtil.addNewStringMeta(town, "townyresourcepack_link", resourceLink, true);
            return;
        }
        MetaDataUtil.setString(town, resourcePackLink, resourceLink, true);
    }
}
