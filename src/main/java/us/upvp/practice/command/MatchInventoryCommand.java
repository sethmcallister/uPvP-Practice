package us.upvp.practice.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import us.upvp.api.framework.permission.Group;
import us.upvp.api.internal.command.CommandCaller;
import us.upvp.api.internal.command.CommandCallerType;
import us.upvp.api.internal.command.CommandListener;
import us.upvp.practice.Practice;
import us.upvp.practice.inventories.DeathInventory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by Seth on 09/04/2017.
 */
public class MatchInventoryCommand extends CommandListener implements Listener
{
    private final LinkedList<UUID> viewingInv;

    public MatchInventoryCommand()
    {
        super("matchinv", Arrays.asList("_", "viewinventory"), Group.MEMBER, true);
        this.viewingInv = new LinkedList<>();
        Bukkit.getPluginManager().registerEvents(this, null);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        if (viewingInv.contains(event.getWhoClicked().getUniqueId()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event)
    {
        Player who = (Player) event.getPlayer();
        if (viewingInv.contains(who.getUniqueId()))
            viewingInv.remove(who.getUniqueId());
    }

    @Override
    public void execute(CommandCaller sender, CommandCallerType type, String[] args)
    {
        if (args.length != 1)
        {
            sender.sendMessage(ChatColor.RED + "Usage: /matchinv <player>");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        DeathInventory deathGUI = Practice.getInstance().getDeathInventoryHandler().findByUnqiueId(target.getUniqueId());
        if (deathGUI == null)
        {
            sender.sendMessage(ChatColor.GOLD + "That player does not have a match inventory.");
            return;
        }
        ((Player) sender).openInventory(deathGUI.openInventoryUUID());
        viewingInv.add(((Player) sender).getUniqueId());
    }
}
