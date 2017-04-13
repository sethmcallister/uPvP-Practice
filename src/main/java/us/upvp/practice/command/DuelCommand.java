package us.upvp.practice.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import us.upvp.api.framework.permission.Group;
import us.upvp.api.framework.server.practice.Ladder;
import us.upvp.api.framework.user.User;
import us.upvp.api.internal.command.CommandCaller;
import us.upvp.api.internal.command.CommandCallerType;
import us.upvp.api.internal.command.CommandListener;
import us.upvp.practice.Practice;
import us.upvp.practice.match.Match;
import us.upvp.practice.match.duel.Duel;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class DuelCommand extends CommandListener implements Listener
{
    private final ConcurrentHashMap<Player, Player> toDuel;

    public DuelCommand()
    {
        super("duel", new LinkedList<String>(), Group.MEMBER, true);
        this.toDuel = new ConcurrentHashMap<>();
        Bukkit.getPluginManager().registerEvents(this, null);
    }

    @EventHandler
    public void duelclick(InventoryClickEvent event)
    {
        Player who = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase(Practice.getInstance().getDuelInventory().getDuelInventory().getName()))
        {
            if (!toDuel.containsKey(who))
            {
                who.closeInventory();
                event.setCancelled(true);
            }
            if (event.getRawSlot() == 0)
            {
                new Duel(who, toDuel.get(who), Ladder.NO_DEBUFF);
            }
            if (event.getRawSlot() == 1)
            {
                new Duel(who, toDuel.get(who), Ladder.DEBUFF);
            }
            if (event.getRawSlot() == 2)
            {
                new Duel(who, toDuel.get(who), Ladder.SOUP);
            }
            if (event.getRawSlot() == 3)
            {
                new Duel(who, toDuel.get(who), Ladder.GAPPLE);
            }
            if (event.getRawSlot() == 4)
            {
                new Duel(who, toDuel.get(who), Ladder.VANILLA);
            }
//            if (event.getRawSlot() == 5)
//            {
//                new Duel(who, toDuel.get(who), Ladder.ARCHER);
//            }
            if (event.getRawSlot() == 5)
            {
                new Duel(who, toDuel.get(who), Ladder.AXE);
            }
            if (event.getRawSlot() == 7)
            {
                new Duel(who, toDuel.get(who), Ladder.NO_ENCHANTS);
            }
            who.closeInventory();
            event.setCancelled(true);
        }
    }

    @Override
    public void execute(CommandCaller sender, CommandCallerType type, String[] args)
    {
        Player player = Bukkit.getPlayer(((User) sender).getUniqueId());
        if (args.length != 1)
        {
            sender.sendMessage(ChatColor.RED + "Usage: /duel <player>");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null)
        {
            sender.sendMessage(ChatColor.RED + "That player is not online.");
            return;
        }
        if (target == player)
        {
            sender.sendMessage(ChatColor.RED + "You cannot duel yourself.");
            return;
        }
        Match match = Practice.getInstance().getMatchHandler().findMatchByPlayer(target);
        if (match != null)
        {
            sender.sendMessage(ChatColor.RED + "That player is already in a match.");
            return;
        }
        toDuel.put(player, target);
        player.openInventory(Practice.getInstance().getDuelInventory().getDuelInventory());
    }
}
