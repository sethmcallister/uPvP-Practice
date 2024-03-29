package us.upvp.practice.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import us.upvp.practice.Practice;

/**
 * Created by Seth on 09/04/2017.
 */
public class ChestAccessListner implements Listener
{
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event)
    {
        if (Practice.getInstance().getMatchHandler().findMatchByPlayer(event.getPlayer()) == null)
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (event.getClickedBlock() != null && isChest(event.getClickedBlock()))
            event.setCancelled(true);
    }

    private boolean isChest(Block block)
    {
        return block.getType().equals(Material.CHEST);
    }
}
