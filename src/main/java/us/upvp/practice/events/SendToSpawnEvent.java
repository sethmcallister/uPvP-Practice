package us.upvp.practice.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import us.upvp.practice.Practice;
import us.upvp.practice.match.party.Party;

/**
 * Created by Seth on 09/04/2017.
 */
public class SendToSpawnEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    private final Player player;

    public SendToSpawnEvent(Player player)
    {
        this.player = player;

        player.teleport(new Location(Bukkit.getWorld("world"), 8.5, 106.5, -0.5, 87, 0));
        Party party = Practice.getInstance().getPartyHandler().findByMember(player);
        if (party != null)
        {
            if (party.getLeader().equals(player))
                Practice.getInstance().getLobbyInventory().givePartyItems(player, true);
            else
                Practice.getInstance().getLobbyInventory().givePartyItems(player, false);
        }
        else
            Practice.getInstance().getLobbyInventory().giveSpawnItems(player);

        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getActivePotionEffects().clear();
        player.updateInventory();
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public Player getPlayer()
    {
        return player;
    }
}
