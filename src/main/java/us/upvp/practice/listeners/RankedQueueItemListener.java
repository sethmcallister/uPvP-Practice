package us.upvp.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.upvp.api.framework.listeners.UPvPListener;
import us.upvp.api.framework.practice.Ladder;
import us.upvp.practice.Practice;

/**
 * Created by Seth on 09/04/2017.
 */
public class RankedQueueItemListener extends UPvPListener
{
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event)
    {
        Player who = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase((Practice.getInstance().getRankedInventory().getRankedInventory().getName())))
        {
            if (event.getRawSlot() == 0)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.NO_DEBUFF, true);
            }
            if (event.getRawSlot() == 1)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.DEBUFF, true);
            }
            if (event.getRawSlot() == 2)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.SOUP, true);
            }
            if (event.getRawSlot() == 3)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.GAPPLE, true);
            }
            if (event.getRawSlot() == 4)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.VANILLA, true);
            }
//            if (event.getRawSlot() == 5)
//            {
//                QueueManager.getInstance().addToQueue(who, Ladder.ARCHER, true);
//            }
            if (event.getRawSlot() == 5)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.AXE, true);
            }
            if (event.getRawSlot() == 6)
            {
                Practice.getInstance().getQueueManager().addToQueue(who, Ladder.NO_ENCHANTS, true);
            }

            who.closeInventory();
            Practice.getInstance().getLobbyInventory().giveQueueItems(who);
            event.setCancelled(true);
        }
    }
}
