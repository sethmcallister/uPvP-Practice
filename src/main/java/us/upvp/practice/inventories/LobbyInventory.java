package us.upvp.practice.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Seth on 09/04/2017.
 */
public class LobbyInventory
{
    public ItemStack getUnrankedTool()
    {
        ItemStack is = new ItemStack(Material.IRON_SWORD);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Unranked Ladders ");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getRankedTool()
    {
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Ranked Ladders ");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getOptions()
    {
        ItemStack is = new ItemStack(Material.NETHER_STAR);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Options Menu" + ChatColor.GRAY + "(Right Click)");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getKitEditTool()
    {
        ItemStack is = new ItemStack(Material.BOOK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Kit Editor");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getVanishPlayersTool(Boolean bo)
    {
        if (bo)
        {
            ItemStack is = new ItemStack(351, 1, (byte) 11);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GOLD + "Show players");
            is.setItemMeta(im);
            return is;
        }
        ItemStack is = new ItemStack(351, 1, (byte) 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.RED + "Hide players");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getLeaveSpectatorTool()
    {
        ItemStack is = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Leave Spectator");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack createTeamTool()
    {
        ItemStack is = new ItemStack(Material.NAME_TAG);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Create a team");
        is.setItemMeta(im);
        return is;
    }

    public void giveSpawnItems(Player player)
    {
        player.getInventory().clear();
        player.getInventory().setItem(0, getRankedTool());
        player.getInventory().setItem(1, getUnrankedTool());
        player.getInventory().setItem(4, getKitEditTool());
        player.getInventory().setItem(7, createTeamTool());
        player.getInventory().setItem(8, getVanishPlayersTool(false));

        player.updateInventory();
    }

    public ItemStack getQueueInfoTool()
    {
        ItemStack is = new ItemStack(Material.BOOK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "View Queue Information");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getLeaveQueueTool()
    {
        ItemStack is = new ItemStack(351, 1, (byte) 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.RED + "Leave Queue");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getPartyDuelTool()
    {
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Duel Party");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getPartyDisbandTool()
    {
        ItemStack is = new ItemStack(Material.REDSTONE);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Disband Party");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getPartyLeaveTool()
    {
        ItemStack is = new ItemStack(351, 1, (byte) 1);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Leave Party");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getPartyInfoTool()
    {
        ItemStack is = new ItemStack(Material.NETHER_STAR);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "Party Information");
        is.setItemMeta(im);
        return is;
    }

    public ItemStack getPartyList()
    {
        ItemStack is = new ItemStack(Material.SKULL_ITEM);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + "All Other Parties");
        is.setItemMeta(im);
        return is;
    }

    public void giveQueueItems(Player player)
    {
        player.getInventory().clear();
        player.getInventory().setItem(0, getQueueInfoTool());
        player.getInventory().setItem(8, getLeaveQueueTool());
        player.updateInventory();
    }

    public void givePartyItems(Player player, boolean leader)
    {
        player.getInventory().clear();
        if (leader)
        {
            player.getInventory().setItem(0, getPartyInfoTool());
            player.getInventory().setItem(4, getPartyDuelTool());
            player.getInventory().setItem(8, getPartyDisbandTool());
        }
        else
        {
            player.getInventory().setItem(0, getPartyInfoTool());
            player.getInventory().setItem(4, getPartyList());
            player.getInventory().setItem(8, getPartyLeaveTool());
        }
        player.updateInventory();
    }
}
