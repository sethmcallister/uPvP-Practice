package us.upvp.practice.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import us.upvp.practice.Practice;
import us.upvp.practice.match.Match;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class EnderpearlListener implements Listener
{
    private static ConcurrentHashMap<String, Long> enderpearlcooldown = new ConcurrentHashMap<>();

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event)
    {
        Player p = event.getPlayer();
        if (p.getPlayer().getItemInHand().getType().equals(Material.ENDER_PEARL))
        {
            if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK))
            {
                Match match = Practice.getInstance().getMatchHandler().findMatchByPlayer(event.getPlayer());
                if (match == null)
                {
                    event.setCancelled(true);
                    return;
                }
                if ((enderpearlcooldown.containsKey(p.getName())) && ((enderpearlcooldown.get(p.getName())) > System.currentTimeMillis()))
                {
                    long millisLeft = enderpearlcooldown.get(event.getPlayer().getName()) - System.currentTimeMillis();
                    double value = millisLeft / 1000.0D;
                    double sec = Math.round(10.0D * value) / 10.0D;
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "You cannot use this for another " + ChatColor.BOLD + sec + ChatColor.RED + " seconds!");
                    event.getPlayer().updateInventory();
                    return;
                }
                enderpearlcooldown.put(event.getPlayer().getName(), System.currentTimeMillis() + 16000);
            }
        }
    }
}
