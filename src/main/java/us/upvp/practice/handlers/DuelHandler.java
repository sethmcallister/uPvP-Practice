package us.upvp.practice.handlers;

import org.bukkit.entity.Player;
import us.upvp.practice.match.duel.Duel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class DuelHandler
{
    private static DuelHandler duelHandler;
    private List<Duel> duels;
    private ConcurrentHashMap<Player, Long> lastAccepted;

    public DuelHandler()
    {
        duelHandler = this;
        this.duels = new LinkedList<>();
        this.lastAccepted = new ConcurrentHashMap<>();
    }

    public static DuelHandler getInstance()
    {
        return duelHandler;
    }

    public void justAccepted(Player player)
    {
        this.lastAccepted.put(player, 10000 + System.currentTimeMillis());
    }

    public boolean canAcceptAgain(Player player)
    {
        long last = lastAccepted.get(player);
        return last > System.currentTimeMillis();
    }

    public Duel findByPlayer(Player player)
    {
        for (Duel duel : duels)
        {
            if (duel.getSender().equals(player) || duel.getTarget().equals(player))
            {
                return duel;
            }
        }
        return null;
    }

    public Duel findByDulerandDeulee(Player dueler, Player duelee)
    {
        List<Duel> temp = duels;
        Collections.reverse(temp);

        return temp.stream().filter(duel -> duel.getSender().getUniqueId().equals(dueler.getUniqueId()) && duel.getTarget().getUniqueId().equals(duelee.getUniqueId())).findFirst().orElse(null);
    }


    public List<Duel> getDuels()
    {
        return duels;
    }
}
