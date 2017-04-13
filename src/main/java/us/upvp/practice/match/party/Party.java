package us.upvp.practice.match.party;

import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Seth on 09/04/2017.
 */
public class Party
{
    private final List<Player> members = new LinkedList<>();
    private final List<Player> invites = new LinkedList<>();
    private final Player leader;
    private boolean inMatch;

    public Party(Player leader)
    {
        this.leader = leader;
        this.members.add(leader);
        this.inMatch = false;
    }

    public List<Player> getMembers()
    {
        return members;
    }

    public Player getLeader()
    {
        return leader;
    }


    public boolean inMatch()
    {
        return inMatch;
    }

    public void setinMatch(boolean inMatch)
    {
        this.inMatch = inMatch;
    }

    public List<Player> getInvites()
    {
        return invites;
    }
}
