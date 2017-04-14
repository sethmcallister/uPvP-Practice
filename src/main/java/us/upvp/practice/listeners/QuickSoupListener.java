package us.upvp.practice.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Seth on 09/04/2017.
 */
public class QuickSoupListener implements Listener
{
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if ((!event.getAction().equals(Action.RIGHT_CLICK_AIR)) && (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;

        if (event.getItem() == null || !event.getItem().getType().equals(Material.MUSHROOM_SOUP))
            return;

        Player player = event.getPlayer();
        if ((player.getHealth() == 20.0D))
            return;

        float addHealth = (float) (9 + player.getHealth());
        float addFood = 9f + player.getFoodLevel();

        event.getPlayer().setHealth(addHealth > 20f ? player.getMaxHealth() : addHealth);
        event.getPlayer().setFoodLevel(addFood > 20 ? 20 : (int) addFood);

        if(event.getItem() != null)
            event.getItem().setType(Material.BOWL);
    }
}
