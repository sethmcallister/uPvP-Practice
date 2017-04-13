package us.upvp.practice.match.duel;

import mkremins.fanciful.FancyMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.upvp.api.framework.server.practice.Ladder;
import us.upvp.practice.Practice;
import us.upvp.practice.match.Match;
import us.upvp.practice.match.party.Party;

/**
 * Created by Seth on 09/04/2017.
 */
public class Duel
{
    private Long expire;
    private Player sender;
    private Player target;
    private Ladder ladder;
    private Boolean accpet;
    private Party party;
    private Party targetParty;

    public Duel(Player sender, Player target, Ladder ladder)
    {
        this.expire = System.currentTimeMillis() + 30000;
        this.sender = sender;
        this.target = target;
        this.ladder = ladder;
        this.accpet = false;

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eDuel request sent to &a" + target.getName() + "&e with &a" + ladder.getDisplayName() + "&e."));
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2" + sender.getName() + "&e has requested to duel you with &2" + ladder.getDisplayName()));
        new FancyMessage().text("[").color(ChatColor.YELLOW)
                .then("Click here to accept").color(ChatColor.GREEN).command("/accept " + sender.getName()).tooltip("Click to accept " + sender.getName() + "'s duel").then("]").color(ChatColor.YELLOW).send(target);
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e or type &a/accept " + sender.getName() + "&e in the next 30 seconds to accept."));

        Practice.getInstance().getDuelHandler().getDuels().add(this);
    }

    public Duel(Player sender, Party party, Ladder ladder)
    {
        this.expire = System.currentTimeMillis() + 30000;
        this.ladder = ladder;
        this.accpet = false;
        this.party = Practice.getInstance().getPartyHandler().findByLeader(sender);
        this.targetParty = party;

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eDuel request sent to &a" + target.getName() + "&e with &a" + ladder.getDisplayName() + "&e."));
        targetParty.getLeader().sendMessage(ChatColor.translateAlternateColorCodes('&', "&2" + sender.getName() + "&e has requested to duel you with &2" + ladder.getDisplayName()));
        new FancyMessage().text("[").color(ChatColor.YELLOW)
                .then("Click here to accept").color(ChatColor.GREEN).command("/accept " + sender.getName()).tooltip("Click to accept " + sender.getName() + "'s duel").then("]").color(ChatColor.YELLOW).send(targetParty.getLeader());
        targetParty.getLeader().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e or type &a/accept " + sender.getName() + "&e in the next 30 seconds to accept."));

        Practice.getInstance().getDuelHandler().getDuels().add(this);
    }


    public static Duel getDuel(Player player)
    {
        for (Duel duel : Practice.getInstance().getDuelHandler().getDuels())
        {
            if (duel.getTarget().equals(player))
            {
                return duel;
            }
        }
        return null;
    }

    public Player getSender()
    {
        return sender;
    }

    public Player getTarget()
    {
        return target;
    }

    public Ladder getLadder()
    {
        return ladder;
    }

    public Boolean getAccpet()
    {
        return accpet;
    }

    public void acceptDuel()
    {
        final Duel duel = this;
        if (this.sender != null)
        {
            this.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eStarting duel against &a" + target.getName() + "&e."));
            this.target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eStarting duel against &a" + sender.getName() + "&e."));
            this.accpet = true;

            new Match(sender, target, ladder, false);
            Practice.getInstance().getDuelHandler().getDuels().remove(this);
            return;
        }
        Player sender = this.getParty().getLeader();
        Player target = this.getTargetParty().getLeader();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eStarting duel against &a" + target.getName() + "&e."));
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eStarting duel against &a" + sender.getName() + "&e."));
        this.accpet = true;

        new us.upvp.practice.match.Match(getParty(), getTargetParty(), duel.ladder);

        Practice.getInstance().getDuelHandler().getDuels().remove(this);
    }

    public void denyDuel()
    {
        this.sender.sendMessage(ChatColor.AQUA + this.target.getName() + ChatColor.GOLD + " has denied your duel request.");
        this.target.sendMessage(ChatColor.GOLD + "You have denied " + ChatColor.AQUA + this.sender.getName() + ChatColor.GOLD + "'s duel request.");
        Practice.getInstance().getDuelHandler().getDuels().remove(this);
    }

    public Long getExpire()
    {
        return expire;
    }

    public Party getParty()
    {
        return party;
    }

    public Party getTargetParty()
    {
        return targetParty;
    }
}
