package us.upvp.practice.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.upvp.api.framework.server.practice.Ladder;
import us.upvp.practice.Practice;

/**
 * Created by Seth on 09/04/2017.
 */
public class KitSelectListener implements Listener
{
    @EventHandler
    public void onKitSelection(PlayerInteractEvent event)
    {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
        {
            final ItemStack hand = event.getPlayer().getItemInHand();
            if (hand != null && hand.getItemMeta() != null && hand.getItemMeta().getDisplayName() != null)
            {
                String name = hand.getItemMeta().getDisplayName();
                if (name.contains("Default Kit"))
                {
                    if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "No Debuff Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.NO_DEBUFF);
                    }
                    else if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Debuff Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.DEBUFF);
                    }
                    else if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Soup Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.SOUP);
                    }
                    else if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Gapple Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.GAPPLE);
                    }
                    else if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Vanilla Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.VANILLA);
                    }
                    else if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Archer Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.ARCHER);
                    }
                    else if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Axe Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.AXE);
                    }
                    else if (hand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "No Enchants Default Kit"))
                    {
                        Practice.getInstance().getKitHandler().givePlayerItems(event.getPlayer(), Ladder.NO_ENCHANTS);
                    }
                }
            }
        }
    }
}
