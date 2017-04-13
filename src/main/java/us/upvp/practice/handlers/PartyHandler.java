package us.upvp.practice.handlers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.upvp.practice.match.party.Party;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class PartyHandler
{
    @Getter
    private List<Party> parties;
    private Map<ItemStack, Party> items;

    public PartyHandler()
    {
        this.parties = new LinkedList<>();
        this.items = new ConcurrentHashMap<>();
    }

    public void addParty(Party party)
    {
        this.parties.add(party);
    }

    public Party findByLeader(Player player)
    {
        for (Party party : parties)
        {
            if (party.getLeader().equals(player))
                return party;
        }
        return null;
    }

    public Party findByLeader(String string)
    {
        for (Party party : parties)
        {
            if (party.getLeader().getName().equals(string))
                return party;
        }
        return null;
    }

    public Party findByMember(Player player)
    {
        for (Party party : parties)
        {
            for (Player member : party.getMembers())
            {
                if (member.equals(player))
                    return party;
            }
        }
        return null;
    }

    public Party findByItemStack(ItemStack itemStack)
    {
        for (Map.Entry<ItemStack, Party> entry : items.entrySet())
        {
            if (itemStack.equals(entry.getKey()))
                return entry.getValue();
        }
        return null;
    }


    private int getInventorySize(int max)
    {
        if (max <= 0) return 9;
        int quotient = (int) Math.ceil(max / 9.0);
        return quotient > 5 ? 54 : quotient * 9;
    }

    public Inventory getDuelInventory(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, getInventorySize(parties.size()), ChatColor.translateAlternateColorCodes('&', "&eClick to duel a party."));
        for (Party party1 : parties)
        {
            if (!party1.getMembers().contains(player))
            {
                ItemStack item = new ItemStack(Material.SKULL_ITEM);
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a" + party1.getLeader().getName() + "&e's party."));
                List<String> lore = new ArrayList<>();
                for (Player player1 : party1.getMembers())
                {
                    lore.add(ChatColor.translateAlternateColorCodes('&', " &a" + player1.getName()));
                }
                im.setLore(lore);
                item.setItemMeta(im);
                inventory.addItem(item);
                items.put(item, party1);
                continue;
            }
            ItemStack item = new ItemStack(397, 1, (byte) 5);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eYour party."));
            List<String> lore = new ArrayList<>();
            for (Player player1 : party1.getMembers())
            {
                lore.add(ChatColor.translateAlternateColorCodes('&', "&a" + player1.getName()));
            }
            im.setLore(lore);
            item.setItemMeta(im);
            inventory.addItem(item);
            items.put(item, party1);
        }
        return inventory;
    }

    public Inventory getPartiesInventory(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, getInventorySize(parties.size()), ChatColor.translateAlternateColorCodes('&', "&6All Parties."));
        for (Party party1 : parties)
        {
            if (!party1.getMembers().contains(player))
            {
                ItemStack item = new ItemStack(Material.SKULL_ITEM);
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a" + party1.getLeader().getName() + "&e's party."));
                List<String> lore = new ArrayList<>();
                for (Player player1 : party1.getMembers())
                {
                    lore.add(ChatColor.translateAlternateColorCodes('&', " &a" + player1.getName()));
                }
                im.setLore(lore);
                item.setItemMeta(im);
                inventory.addItem(item);
            }
            else
            {
                ItemStack item = new ItemStack(397, 1, (byte) 5);
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eYour party."));
                List<String> lore = new ArrayList<>();
                for (Player player1 : party1.getMembers())
                {
                    lore.add(ChatColor.translateAlternateColorCodes('&', "&a" + player1.getName()));
                }
                im.setLore(lore);
                item.setItemMeta(im);
                inventory.addItem(item);
            }
        }
        return inventory;
    }
}
