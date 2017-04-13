package us.upvp.practice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.upvp.api.framework.listeners.UPvPListener;
import us.upvp.practice.Practice;

/**
 * Created by Seth on 09/04/2017.
 */
public class QueueItemListener extends UPvPListener
{
    @EventHandler
    public void onItemClick(PlayerInteractEvent event)
    {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            final ItemStack hand = event.getPlayer().getItemInHand();
            if (hand.equals(Practice.getInstance().getLobbyInventory().getUnrankedTool()))
            {
                event.getPlayer().openInventory(Practice.getInstance().getUnrankedInventory().getUnrankedInventory());
                event.setCancelled(true);
                return;
            }
            if (hand.equals(Practice.getInstance().getLobbyInventory().getRankedTool()))
            {
                event.getPlayer().openInventory(Practice.getInstance().getRankedInventory().getRankedInventory());
                event.setCancelled(true);
                return;
            }
            if (hand.equals(Practice.getInstance().getLobbyInventory().createTeamTool()))
            {
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cComing Soon."));
//                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou have created a new party."));
//                Practice.getInstance().getLobbyInventory().givePartyItems(event.getPlayer(), true);
//                Practice.getInstance().getPartyHandler().addParty(new Party(event.getPlayer()));
                event.setCancelled(true);
                return;
            }
            if (hand.equals(Practice.getInstance().getLobbyInventory().getVanishPlayersTool(false)))
            {
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    event.getPlayer().hidePlayer(player);
                }
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&ePlayers hidden"));
                event.getPlayer().setItemInHand(Practice.getInstance().getLobbyInventory().getVanishPlayersTool(true));
                event.setCancelled(true);
                return;
            }
            if (hand.equals(Practice.getInstance().getLobbyInventory().getVanishPlayersTool(true)))
            {
                for (Player player : Bukkit.getOnlinePlayers())
                {
                    event.getPlayer().showPlayer(player);
                }
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&ePlayers shown"));
                event.getPlayer().setItemInHand(Practice.getInstance().getLobbyInventory().getVanishPlayersTool(false));
                event.setCancelled(true);
                return;
            }
            if (hand.equals(Practice.getInstance().getLobbyInventory().getPartyDuelTool()))
            {
                event.getPlayer().openInventory(Practice.getInstance().getPartyHandler().getDuelInventory(event.getPlayer()));
                event.setCancelled(true);
            }
        }
    }
}
