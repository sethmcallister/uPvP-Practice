package us.upvp.practice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import us.upvp.practice.Practice;

/**
 * Created by Seth on 09/04/2017.
 */
public class HungerLossListener implements Listener
{
    @EventHandler
    public void onHungerLoss(FoodLevelChangeEvent event)
    {
        if (Practice.getInstance().getMatchHandler().findMatchByPlayer((Player) event.getEntity()) == null)
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        if (player.getLocation().getBlockY() <= 10)
            player.teleport(new Location(Bukkit.getWorld("world"), 8.5, 108.5, -0.5, 87, 0));
    }

    @EventHandler
    public void onRain(WeatherChangeEvent event)
    {
        event.setCancelled(true);
    }
}
