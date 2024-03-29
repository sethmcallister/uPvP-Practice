package us.upvp.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.upvp.api.framework.server.practice.Ladder;
import us.upvp.practice.Practice;

/**
 * Created by Seth on 09/04/2017.
 */
public class UnrankedQueueItemListener implements Listener
{
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event)
    {
        Player who = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase((Practice.getInstance().getUnrankedInventory().getUnrankedInventory().getName())))
        {
            if (event.getRawSlot() == 0)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.NO_DEBUFF, false);
            }
            if (event.getRawSlot() == 1)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.DEBUFF, false);
            }
            if (event.getRawSlot() == 2)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.SOUP, false);
            }
            if (event.getRawSlot() == 3)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.GAPPLE, false);
            }
            if (event.getRawSlot() == 4)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.VANILLA, false);
            }
//            if (event.getRawSlot() == 5)
//            {
//                QueueManager.getInstance().addToQueue(who, Ladder.ARCHER, false);
//
//            }
            if (event.getRawSlot() == 5)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.AXE, false);
            }
            if (event.getRawSlot() == 6)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.NO_ENCHANTS, false);
            }
            who.closeInventory();
            Practice.getInstance().getLobbyInventory().giveQueueItems(who);
            event.setCancelled(true);
        }
    }
}
