package us.upvp.practice.queue;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import us.upvp.api.API;
import us.upvp.api.framework.server.practice.Ladder;
import us.upvp.api.framework.user.User;

import java.util.HashSet;
import java.util.function.Predicate;

/**
 * Created by Seth on 09/04/2017.
 */
public class QueueManager
{
    private HashSet<Queue> queues;

    public QueueManager()
    {
        this.queues = new HashSet<>();
        for (Ladder ladder : Ladder.values())
        {
            this.queues.add(new Queue(ladder, false));
            this.queues.add(new Queue(ladder, true));
            this.queues.add(new Queue(ladder, true, true));
        }

        for (Queue queue : this.queues)
            Bukkit.getPluginManager().registerEvents(queue, (Plugin) API.getPlugin());
    }

    private Queue getQueue(Predicate<Queue> test)
    {
        for (Queue queue : this.queues)
        {
            if (!test.test(queue))
                continue;

            return queue;
        }
        return null;
    }

    public Queue findByPlayer(Player player)
    {
        for (Queue queue : queues)
        {
            if (queue.getQueue().containsKey(player))
                return queue;
        }
        return null;
    }

    private Queue getQueue(Ladder ladder, boolean ranked)
    {
        return this.getQueue(queue -> queue.isRanked() == ranked && queue.getLadder() == ladder);
    }

    private Queue getQueue(Ladder ladder, boolean ranked, boolean vip)
    {
        return this.getQueue(queue -> queue.isRanked() == ranked && queue.getLadder() == ladder && queue.isVip() == vip);
    }

    public void addToQueue(Player ply, Ladder ladder, boolean ranked)
    {
        User user = API.getUserManager().findByUniqueId(ply.getUniqueId());
        if (user == null)
        {
            this.getQueue(ladder, ranked).addToQueue(ply, 1000);
            return;
        }
        Queue queue = this.getQueue(ladder, ranked);
        if (queue == null)
            return;

        if (user.getPractice() == null)
        {
            queue.addToQueue(ply, 1000);
            return;
        }

        queue.addToQueue(ply, user.getPractice().getEloByLadder(ladder).get());
    }

    public void addToQueue(Player ply, Ladder ladder, boolean ranked, boolean vip)
    {
        User user = API.getUserManager().findByUniqueId(ply.getUniqueId());
        this.getQueue(ladder, ranked, vip).addToQueue(ply, user.getPractice().getEloByLadder(ladder).get());
    }
}
