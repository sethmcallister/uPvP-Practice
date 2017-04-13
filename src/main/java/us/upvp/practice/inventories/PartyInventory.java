package us.upvp.practice.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seth on 09/04/2017.
 */
public class PartyInventory
{
    private static PartyInventory instance;
    private ItemStack normalParty;
    private ItemStack factionParty;
    private Inventory inventory;

    public PartyInventory()
    {
        instance = this;
        ItemStack tempNormal = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta imN = tempNormal.getItemMeta();
        imN.setDisplayName(ChatColor.GOLD + "Normal " + ChatColor.GRAY + "Party");
        List<String> loreN = new ArrayList<>();
        loreN.add(ChatColor.translateAlternateColorCodes('&', "&6Click me to create a Normal Party."));
        imN.setLore(loreN);
        tempNormal.setItemMeta(imN);
        this.normalParty = tempNormal;

        ItemStack tempFaction = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta imF = tempNormal.getItemMeta();
        imF.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6New &b&oParty Type Coming Soon"));
        List<String> loreF = new ArrayList<>();
        loreF.add(ChatColor.translateAlternateColorCodes('&', "&6New &b&oParty Type Coming Soon."));
        imF.setLore(loreF);
        tempFaction.setItemMeta(imF);
        this.factionParty = tempFaction;

        Inventory tempInv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6&lChoose a Party Type"));
        tempInv.setItem(3, tempNormal);
        tempInv.setItem(5, tempFaction);
        this.inventory = tempInv;
    }

    public static PartyInventory getInstance()
    {
        return instance;
    }

    public ItemStack getNormalParty()
    {
        return normalParty;
    }

    public ItemStack getFactionParty()
    {
        return factionParty;
    }

    public Inventory getInventory()
    {
        return inventory;
    }
}
