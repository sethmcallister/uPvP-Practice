package us.upvp.practice.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.upvp.api.framework.permission.Group;
import us.upvp.api.internal.command.CommandCaller;
import us.upvp.api.internal.command.CommandCallerType;
import us.upvp.api.internal.command.CommandListener;
import us.upvp.practice.Practice;
import us.upvp.practice.match.duel.Duel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Seth on 09/04/2017.
 */
public class AcceptCommand extends CommandListener
{
    public AcceptCommand()
    {
        super("accept", new LinkedList<String>(), Group.MEMBER, true);
    }


    @Override
    public void execute(CommandCaller sender, CommandCallerType type, String[] args)
    {
        if (args.length != 1)
        {
            sender.sendMessage(ChatColor.RED + "Usage: /accept <Player>");
            return;
        }
        List<Duel> duels = new LinkedList<>();

        for (Duel duel : Practice.getInstance().getDuelHandler().getDuels())
        {
            if (duel.getTarget() != null && duel.getTarget().equals(sender))
                duels.add(duel);

            if (duel.getTargetParty() != null && duel.getTargetParty().getLeader().equals(sender))
                duels.add(duel);
        }
        if (duels.isEmpty())
        {
            sender.sendMessage(ChatColor.RED + "You have no pending duels.");
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null)
        {
            sender.sendMessage(ChatColor.RED + "That player is not online.");
            return;
        }
        if (Practice.getInstance().getMatchHandler().isSpectatingMatch((Player) sender))
        {
            sender.sendMessage(ChatColor.RED + "You cannot accept a duel while spectating a match.");
            return;
        }
        if (Practice.getInstance().getMatchHandler().findMatchByPlayer((Player) sender) != null)
        {
            sender.sendMessage(ChatColor.RED + "You cannot accept a duel while in a match.");
            return;
        }

        Duel duel = Practice.getInstance().getDuelHandler().findByDulerandDeulee(target, (Player) sender);
        if(duel == null)
        {
            sender.sendMessage(ChatColor.RED + "You have no pending duels from this person.");
            return;
        }
        duel.acceptDuel();
    }
}
