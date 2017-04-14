package us.upvp.practice.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import us.upvp.practice.Practice;
import us.upvp.practice.match.Match;
import us.upvp.practice.match.party.Party;

/**
 * Created by Seth on 09/04/2017.
 */
public class EntityDamageListener implements Listener
{
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event)
    {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player)
        {
            Player damager = (Player) event.getDamager();
            Player damaged = (Player) event.getEntity();
            Match drMatch = Practice.getInstance().getMatchHandler().findMatchByPlayer(damager);
            Match dMatch = Practice.getInstance().getMatchHandler().findMatchByPlayer(damaged);
            if (drMatch == null)
            {
                event.setCancelled(true);
                damager.updateInventory();
                return;
            }
            if (dMatch == null)
                event.setCancelled(true);

            if (dMatch != drMatch)
                event.setCancelled(true);

            Party party = Practice.getInstance().getPartyHandler().findByMember(damager);
            if (party != null && party.getMembers().contains(damaged))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAloneDamange(EntityDamageEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            if (Practice.getInstance().getMatchHandler().findMatchByPlayer((Player) event.getEntity()) == null)
                event.setCancelled(true);
        }
    }
}
