package us.upvp.practice.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.upvp.practice.Practice;

/**
 * Created by Seth on 09/04/2017.
 */
public class KitEditorListener implements Listener
{
    @EventHandler
    public void onItemClick(PlayerInteractEvent event)
    {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR))
        {
            final ItemStack hand = event.getPlayer().getItemInHand();
            if (hand.equals(Practice.getInstance().getLobbyInventory().getKitEditTool()))
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCurrently not available."));
        }
    }

//    @EventHandler
//    public void onKitClick(InventoryClickEvent event)
//    {
//        if (event.getClickedInventory() == null)
//            return;
//
//        if (event.getClickedInventory().getTitle() == null)
//            return;
//
//        if (event.getClickedInventory().getTitle().equals(KitEditGUI.getInstance().giveKitEditInventory().getTitle()))
//        {
//            if (event.getRawSlot() == 0)
//            {
//                KitEditing kitEditing = new KitEditing((Player) event.getWhoClicked(), Ladder.NO_DEBUFF, 1);
//                KadePractice.getInstance().getKitEditings().add(kitEditing);
//            }
//            if (event.getRawSlot() == 1)
//            {
//                KitEditing kitEditing = new KitEditing((Player) event.getWhoClicked(), Ladder.DEBUFF, 1);
//                KadePractice.getInstance().getKitEditings().add(kitEditing);
//            }
//            if (event.getRawSlot() == 2)
//            {
//                KitEditing kitEditing = new KitEditing((Player) event.getWhoClicked(), Ladder.SOUP, 1);
//                KadePractice.getInstance().getKitEditings().add(kitEditing);
//            }
//            if (event.getRawSlot() == 3)
//            {
//                ((Player) event.getWhoClicked()).sendMessage(ChatColor.GOLD + "You cannot edit the " + ChatColor.AQUA.toString() + ChatColor.ITALIC + Ladder.GAPPLE.getDisplayName() + ChatColor.GOLD + " Kit.");
//            }
//            if (event.getRawSlot() == 4)
//            {
//                KitEditing kitEditing = new KitEditing((Player) event.getWhoClicked(), Ladder.VANILLA, 1);
//                KadePractice.getInstance().getKitEditings().add(kitEditing);
//            }
//            if (event.getRawSlot() == 5)
//            {
//                ((Player) event.getWhoClicked()).sendMessage(ChatColor.GOLD + "You cannot edit the " + ChatColor.AQUA.toString() + ChatColor.ITALIC + Ladder.ARCHER.getDisplayName() + ChatColor.GOLD + " Kit.");
//            }
//            if (event.getRawSlot() == 6)
//            {
//                ((Player) event.getWhoClicked()).sendMessage(ChatColor.GOLD + "You cannot edit the " + ChatColor.AQUA.toString() + ChatColor.ITALIC + Ladder.AXE.getDisplayName() + ChatColor.GOLD + " Kit.");
//            }
//            if (event.getRawSlot() == 7)
//            {
//                KitEditing kitEditing = new KitEditing((Player) event.getWhoClicked(), Ladder.NO_ENCHANTS, 1);
//                KadePractice.getInstance().getKitEditings().add(kitEditing);
//            }
//            event.getWhoClicked().closeInventory();
//        }
//    }
}
