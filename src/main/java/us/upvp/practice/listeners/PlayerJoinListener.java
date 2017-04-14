package us.upvp.practice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import us.upvp.practice.events.SendToSpawnEvent;

/**
 * Created by Seth on 09/04/2017.
 */
public class PlayerJoinListener implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Bukkit.getPluginManager().callEvent(new SendToSpawnEvent(event.getPlayer()));
    }
}
