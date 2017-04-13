package us.upvp.practice.queue;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import us.upvp.api.API;
import us.upvp.api.framework.practice.Ladder;
import us.upvp.practice.Practice;
import us.upvp.practice.match.Match;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class Queue implements Listener
{
    private Ladder ladder;
    private int tid;
    private boolean ranked;
    @Getter
    private final ConcurrentHashMap<Player, Integer> queue;
    private boolean vip;

    public Queue(Ladder ladder, boolean ranked)
    {
        this.ladder = ladder;
        this.ranked = ranked;
        this.queue = new ConcurrentHashMap<>();
    }

    public Queue(Ladder ladder, boolean ranked, boolean vip)
    {
        this.ladder = ladder;
        this.ranked = ranked;
        this.queue = new ConcurrentHashMap<>();
        this.vip = vip;
    }

    public void addToQueue(final Player player, final int rating)
    {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou joined &a" + ladder.getDisplayName() + "&e " + (ranked ? "ranked queue with &a" + rating + " elo&e." : " queue, please wait for another player.")));

        this.queue.put(player, rating);
        if (this.ranked)
        {
            int tRating = API.getUserManager().findByUniqueId(player.getUniqueId()).getPractice().getEloByLadder(ladder).get();
            player.sendMessage(ChatColor.YELLOW + "Your elo is " + ChatColor.GREEN + tRating + ChatColor.YELLOW + ", searching in elo range " + ChatColor.GREEN + "[" + (tRating - 100) + " -> " + (tRating + 100) + "]");
            new BukkitRunnable()
            {
                int range;
                int i;
                boolean maxRange;

                @Override
                public void run()
                {
                    for (Player player1 : queue.keySet())
                    {
                        if (rating - this.range > queue.get(player) || rating + this.range < queue.get(player) || (player1 == player))
                            continue;
                        queue.remove(player);
                        queue.remove(player1);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eStarting duel against &a" + player1.getName()));
                        new Match(player, player1, ladder, true);
                        this.cancel();
                        return;
                    }

                    this.i++;
                    if (!this.maxRange)
                    {
                        if (!queue.containsKey(player))
                        {
                            this.cancel();
                            return;
                        }

                        if (this.i == 5)
                        {
                            this.range += 50;
                            if (rating - this.range <= 0)
                            {
                                this.maxRange = true;
                            }
                            player.sendMessage(ChatColor.YELLOW + "Your elo is " + ChatColor.GREEN + tRating + ChatColor.YELLOW + ", searching in elo range " + ChatColor.GREEN + "[" + (tRating - this.range) + " -> " + (tRating + this.range) + "]");
                            this.i = 0;
                            return;
                        }

                        if (this.i % 5 == 0)
                        {
                            queue.remove(player);
                            this.cancel();
                            Practice.getInstance().getLobbyInventory().giveSpawnItems(player);
                            player.sendMessage(ChatColor.RED + "Couldn't find You a match, removing You from the queue!");
                            return;
                        }
                    }

                    if (!queue.containsKey(player))
                        this.cancel();
                }
            }.runTaskTimer(API.getPlugin(), 0L, 20L);
        }
        else
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    if (!queue.containsKey(player))
                    {
                        this.cancel();
                        return;
                    }
                    for (Player player1 : queue.keySet())
                    {
                        if (player1 == player) continue;

                        queue.remove(player);
                        queue.remove(player1);
                        new Match(player, player1, ladder, false);
                        this.cancel();
                        return;
                    }
                }
            }.runTaskTimer(API.getPlugin(), 0L, 20L);
        }
    }

    @EventHandler
    public void onRightClickEvent(PlayerInteractEvent event)
    {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            final ItemStack hand = event.getPlayer().getItemInHand();
            if (hand.equals(Practice.getInstance().getLobbyInventory().getLeaveQueueTool()))
            {
                if (this.queue.containsKey(event.getPlayer()))
                {
                    this.queue.remove(event.getPlayer());
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eRemoved from the " + (ranked ? "ranked " : "unranked ") + "&a" + ladder.getDisplayName() + "&e queue."));
                    Practice.getInstance().getLobbyInventory().giveSpawnItems(event.getPlayer());
                    event.setCancelled(true);
                }
                else if (Practice.getInstance().getQueueManager().findByPlayer(event.getPlayer()) == null)
                {
                    Practice.getInstance().getLobbyInventory().giveSpawnItems(event.getPlayer());
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        if (this.queue.containsKey(event.getPlayer()))
            this.queue.remove(event.getPlayer());
    }

    public Ladder getLadder()
    {
        return ladder;
    }

    public boolean isRanked()
    {
        return ranked;
    }

    public boolean isVip()
    {
        return vip;
    }
}
