package us.upvp.practice.handlers;

import lombok.Getter;
import org.bukkit.entity.Player;
import us.upvp.practice.match.Match;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Seth on 09/04/2017.
 */
public class MatchHandler
{
    @Getter
    private final List<Match> matches;

    public MatchHandler()
    {
        this.matches = new LinkedList<>();
    }

    public void addMatch(Match match)
    {
        this.matches.add(match);
    }

    public void removeMatch(Match match)
    {
        this.matches.remove(match);
    }

    public boolean isSpectatingMatch(Player player)
    {
        for (Match match : this.matches)
            if (match.getSpectators().contains(player))
                return true;

        return false;
    }

    public Match findMatchByPlayer(Player player)
    {
        for (Match match : this.matches)
            for (Player player1 : match.getFighters())
                if (player1 == player)
                    return match;

        return null;
    }
}
