package us.upvp.practice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.upvp.api.framework.listeners.UPvPListener;
import us.upvp.api.framework.practice.Ladder;
import us.upvp.practice.Practice;
import us.upvp.practice.events.SendToSpawnEvent;
import us.upvp.practice.match.duel.Duel;
import us.upvp.practice.match.party.Party;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class PartyInventoryListener extends UPvPListener
{
    private ConcurrentHashMap<Player, Party> toDuel;

    public PartyInventoryListener()
    {
        this.toDuel = new ConcurrentHashMap<>();
    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        Player who = (Player) event.getWhoClicked();
        int clicked = event.getRawSlot();
        if (event.getClickedInventory() == null)
            return;

        if (event.getClickedInventory().equals(Practice.getInstance().getPartyInventory().getInventory()))
        {
            if (clicked == 3)
            {
                who.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou have created a new party."));
                Practice.getInstance().getLobbyInventory().givePartyItems(who, true);
                new Party(who);
                who.closeInventory();
            }
            event.setCancelled(true);
        }
        else if (event.getClickedInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&eClick to duel a party.")))
        {
            if (event.getClickedInventory().getItem(clicked) == null)
                return;

            Party party = Practice.getInstance().getPartyHandler().findByItemStack(event.getClickedInventory().getItem(clicked));
            if (party == null)
            {
                event.setCancelled(true);
                return;
            }
            if (party.getLeader().equals(who))
            {
                who.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot duel you own Party."));
                event.setCancelled(true);
                who.closeInventory();
                return;
            }
            if (party.inMatch())
            {
                who.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat party is in a duel."));
                event.setCancelled(true);
                who.closeInventory();
                return;
            }
            toDuel.put(who, party);
            event.setCancelled(true);
            who.closeInventory();
            who.openInventory(Practice.getInstance().getPartyDuelInventory().getDuelInventory());
        }
        else if (event.getClickedInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', "&6All Parties.")))
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack hand = event.getPlayer().getItemInHand();
        Party party = Practice.getInstance().getPartyHandler().findByMember(player);
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
        {
            if (hand.equals(Practice.getInstance().getLobbyInventory().getPartyLeaveTool()))
            {
                if (!party.getMembers().isEmpty())
                {
                    Practice.getInstance().getPartyHandler().getParties().remove(party);
                    Bukkit.getPluginManager().callEvent(new SendToSpawnEvent(player));
                    return;
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou have left &a" + party.getLeader().getName() + "&e's party."));
                sendPartyMessage(party, "&a" + player.getName() + "&e has left the party.");
                party.getMembers().remove(player);
                Practice.getInstance().getLobbyInventory().giveSpawnItems(player);
            }
            else if (hand.equals(Practice.getInstance().getLobbyInventory().getPartyDisbandTool()))
            {
                for (Player player1 : party.getMembers())
                {
                    Practice.getInstance().getLobbyInventory().giveSpawnItems(player1);
                }
                sendPartyMessage(party, "&a" + party.getLeader().getName() + "&e's party was disbanded.");
                Practice.getInstance().getPartyHandler().getParties().remove(party);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have just disbanded your party."));
            }
            else if (hand.equals(Practice.getInstance().getLobbyInventory().getPartyInfoTool()))
            {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eParty members."));
                for (Player member : party.getMembers())
                {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + member.getName()));
                }
            }
            else if (hand.equals(Practice.getInstance().getLobbyInventory().getPartyList()))
            {
                event.getPlayer().openInventory(Practice.getInstance().getPartyHandler().getPartiesInventory(player));
                event.setCancelled(true);
            }
        }
    }

    private void sendPartyMessage(Party party, String message)
    {
        for (Player player : party.getMembers())
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    @EventHandler
    public void duelclick(InventoryClickEvent event)
    {
        Player who = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase(Practice.getInstance().getPartyDuelInventory().getDuelInventory().getName()))
        {
            if (event.getRawSlot() == 0)
            {
                new Duel(who, toDuel.get(who), Ladder.NO_DEBUFF);
            }
            if (event.getRawSlot() == 1)
            {
                new Duel(who, toDuel.get(who), Ladder.DEBUFF);
            }
            if (event.getRawSlot() == 2)
            {
                new Duel(who, toDuel.get(who), Ladder.SOUP);
            }
            if (event.getRawSlot() == 3)
            {
                new Duel(who, toDuel.get(who), Ladder.GAPPLE);
            }
            if (event.getRawSlot() == 4)
            {
                new Duel(who, toDuel.get(who), Ladder.VANILLA);
            }
            if (event.getRawSlot() == 5)
            {
                new Duel(who, toDuel.get(who), Ladder.AXE);
            }
            if (event.getRawSlot() == 6)
            {
                new Duel(who, toDuel.get(who), Ladder.NO_ENCHANTS);
            }
            toDuel.remove(who);
            who.closeInventory();
            event.setCancelled(true);
        }
    }
}
