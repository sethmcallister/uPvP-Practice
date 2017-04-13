package us.upvp.practice.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.upvp.api.framework.permission.Group;
import us.upvp.api.framework.user.User;
import us.upvp.api.internal.command.CommandCaller;
import us.upvp.api.internal.command.CommandCallerType;
import us.upvp.api.internal.command.CommandListener;
import us.upvp.practice.Practice;
import us.upvp.practice.match.Match;

import java.util.Arrays;

/**
 * Created by Seth on 09/04/2017.
 */
public class SpectateCommand extends CommandListener
{
    public SpectateCommand()
    {
        super("spectate", Arrays.asList("spec", "viewmatch"), Group.MEMBER, true);
    }

    @Override
    public void execute(CommandCaller sender, CommandCallerType type, String[] args)
    {
        if (args.length != 1)
        {
            sender.sendMessage(ChatColor.RED + "Usage: /spec <player>");
            return;
        }
        if (Practice.getInstance().getMatchHandler().isSpectatingMatch((Player) sender))
        {
            sender.sendMessage(ChatColor.RED + "You cannot spectate while spectating another match.");
            return;
        }
        if (Practice.getInstance().getMatchHandler().findMatchByPlayer((Player) sender) != null)
        {
            sender.sendMessage(ChatColor.RED + "You cannot accept a spectate a match while in a match.");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null)
        {
            sender.sendMessage(ChatColor.RED + "That player is not online.");
            return;
        }
        Match match = Practice.getInstance().getMatchHandler().findMatchByPlayer(target);
        if (match == null)
        {
            sender.sendMessage(ChatColor.RED + "That player is not in a match.");
            return;
        }
        User user = (User)sender;
        if(user.getGroup().getLadder() >= Group.HELPER.getLadder())
        {
            match.addSpectator((Player) sender, target, true);
            user.sendMessage("&7[Silent]&eYou are now spectating &a" + target.getName() + "&e's match.");
            return;
        }
        match.addSpectator((Player) sender, target);
        user.sendMessage("&eYou are now spectating &a" + target.getName() + "&e's match.");
    }
}
