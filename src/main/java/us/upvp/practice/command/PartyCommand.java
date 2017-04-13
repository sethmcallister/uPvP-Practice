package us.upvp.practice.command;

import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.upvp.api.framework.permission.Group;
import us.upvp.api.framework.user.User;
import us.upvp.api.internal.command.CommandCaller;
import us.upvp.api.internal.command.CommandCallerType;
import us.upvp.api.internal.command.CommandListener;
import us.upvp.practice.Practice;
import us.upvp.practice.match.party.Party;

import java.util.LinkedList;

/**
 * Created by Seth on 09/04/2017.
 */
public class PartyCommand extends CommandListener
{
    public PartyCommand()
    {
        super("party", new LinkedList<String>(), Group.MEMBER, true);
    }

    private void handleInvite(Player player, String[] args)
    {
        Party party = Practice.getInstance().getPartyHandler().findByMember(player);
        if (party == null)
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not in a party."));
            return;
        }
        if (!party.getLeader().equals(player))
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not the leader of Your current Party."));
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null)
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is not online."));
            return;
        }

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have invited &b" + target.getName() + "&6 to your party."));
        for (Player player1 : party.getMembers())
        {
            player1.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + target.getName() + "&6 has been invited to Your party."));

        }

        new FancyMessage().text("You have been invited to join ").color(ChatColor.GOLD)
                .then(player.getName()).color(ChatColor.AQUA)
                .then("'s Party.").color(ChatColor.GOLD).send(target);

        new FancyMessage().text("Click to ").color(ChatColor.GOLD)
                .then("ACCEPT").command("/party join " + player.getName()).color(ChatColor.GREEN).style(ChatColor.BOLD).tooltip("Click here to join " + player.getName() + "'s Party")
                .then(" their invite.").color(ChatColor.GOLD).send(target);
        party.getInvites().add(target);
    }

    private void handleJoin(Player player, String[] args)
    {
        Player targetLeader = Bukkit.getPlayer(args[1]);
        if (targetLeader == null)
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is not online."));
            return;
        }
        Party target = Practice.getInstance().getPartyHandler().findByLeader(targetLeader);
        if (!target.getInvites().contains(player))
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have not been invited to that Party."));
            return;
        }
        target.getInvites().remove(player);
        for (Player member : target.getMembers())
        {
            member.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + player.getName() + "&6 has joined Your Party."));
        }
        target.getMembers().add(player);
        Practice.getInstance().getLobbyInventory().givePartyItems(player, false);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have joined &b" + target.getLeader().getName() + "&6's Party."));
    }

    private void handleKick(Player player, String[] args)
    {
        Party party = Practice.getInstance().getPartyHandler().findByMember(player);
        if (party == null)
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not in a Party."));
            return;
        }
        if (party.inMatch())
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not allowed to kick players while in a match."));
            return;
        }
        if (!party.getLeader().equals(player))
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not the leader of Your current Party."));
            return;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null)
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is not online."));
            return;
        }
        if (!party.getMembers().contains(target))
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player is not in Your Party."));
            return;
        }
        party.getMembers().remove(target);
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You have been kicked from &b" + player.getName() + "&6's Party."));
        for (Player member : party.getMembers())
        {
            member.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + player.getName() + " &6has been kicked from the Party."));
        }
        Practice.getInstance().getLobbyInventory().giveSpawnItems(target);
    }

    private void handleUsage(Player player)
    {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /party <invite|kick> [player]"));
    }

    @Override
    public void execute(CommandCaller sender, CommandCallerType type, String[] args)
    {
        Player player = Bukkit.getPlayer(((User) sender).getUniqueId());
        if (args.length < 1)
        {
            handleUsage(player);
            return;
        }
        switch (args[0].toLowerCase())
        {
            case "invite":
                handleInvite(player, args);
                break;
            case "kick":
                handleKick(player, args);
                break;
            case "join":
                handleJoin(player, args);
                break;
            default:
                handleUsage(player);
                break;
        }
    }
}
