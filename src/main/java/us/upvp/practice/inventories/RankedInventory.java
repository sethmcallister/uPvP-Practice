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
public class RankedInventory
{
    public ItemStack getNoDebuff(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(373, 1, (byte) 16421);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "No Debuff " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(373, 1, (byte) 16421);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "No Debuff " + ChatColor.GOLD + "ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getDebuff(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(373, 1, (byte) 16388);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Debuff " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(373, 1, (byte) 16388);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Debuff " + ChatColor.GOLD + "ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getSoup(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(Material.MUSHROOM_SOUP);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Soup " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(Material.MUSHROOM_SOUP);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Soup " + ChatColor.GOLD + "ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getGapple(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(322, 1, (byte) 1);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Gapple " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(322, 1, (byte) 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Gapple " + ChatColor.GOLD + "ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getVanilla(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(373, 1, (byte) 8233);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Vanilla " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(373, 1, (byte) 8233);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Vanilla " + ChatColor.GOLD + "ladder.");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getArcher(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(Material.BOW);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Archer " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            im.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
            im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(Material.BOW);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Archer " + ChatColor.GOLD + "ladder.");
        im.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
        im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getAxe(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(Material.IRON_AXE);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Axe PvP " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(Material.IRON_AXE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "Axe PvP " + ChatColor.GOLD + "ladder.");
        im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getNoEnchants(boolean vip)
    {
        if (vip)
        {
            ItemStack is = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "No Enchants " + ChatColor.GOLD + "Queue." + ChatColor.GRAY + "(VIP)");
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Click to join the " + ChatColor.AQUA + "No Enchants " + ChatColor.GOLD + "ladder.");
        is.setItemMeta(im);
        return is;
    }

    public Inventory getRankedInventory()
    {
        final Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&6Join a &7Ranked &6ladder."));
        inventory.setItem(0, getNoDebuff(false));
        inventory.setItem(1, getDebuff(false));
        inventory.setItem(2, getSoup(false));
        inventory.setItem(3, getGapple(false));
        inventory.setItem(4, getVanilla(false));
//        inventory.setItem(5, getArcher(false));
        inventory.setItem(5, getAxe(false));
        inventory.setItem(6, getNoEnchants(false));
        return inventory;
    }
}
