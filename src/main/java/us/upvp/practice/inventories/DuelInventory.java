package us.upvp.practice.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Seth on 09/04/2017.
 */
public class DuelInventory
{
    public ItemStack getNoDebuff()
    {
        ItemStack is = new ItemStack(373, 1, (byte) 16421);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "No Debuff " + ChatColor.GOLD + "Ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getDebuff()
    {
        ItemStack is = new ItemStack(373, 1, (byte) 16388);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "Debuff " + ChatColor.GOLD + "Ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getSoup()
    {
        ItemStack is = new ItemStack(Material.MUSHROOM_SOUP);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "Soup " + ChatColor.GOLD + "Ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getGapple()
    {
        ItemStack is = new ItemStack(322, 1, (byte) 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "Gapple " + ChatColor.GOLD + "Ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getVanilla()
    {
        ItemStack is = new ItemStack(373, 1, (byte) 8233);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "Vanilla " + ChatColor.GOLD + "Ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getArcher()
    {
        ItemStack is = new ItemStack(Material.BOW);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "Archer " + ChatColor.GOLD + "Ladder.");
        im.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getAxe()
    {
        ItemStack is = new ItemStack(Material.IRON_AXE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "Axe PvP " + ChatColor.GOLD + "Ladder.");
        im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getNoEnchants()
    {
        ItemStack is = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to duel with the " + ChatColor.AQUA + "No Enchants " + ChatColor.GOLD + "Ladder.");
        is.setItemMeta(im);
        return is;
    }

    public Inventory getDuelInventory()
    {
        final Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Click a Ladder to duel."));
        inventory.setItem(0, getNoDebuff());
        inventory.setItem(1, getDebuff());
        inventory.setItem(2, getSoup());
        inventory.setItem(3, getGapple());
        inventory.setItem(4, getVanilla());
//        inventory.setItem(5, getArcher());
        inventory.setItem(5, getAxe());
        inventory.setItem(6, getNoEnchants());
        return inventory;
    }
}
