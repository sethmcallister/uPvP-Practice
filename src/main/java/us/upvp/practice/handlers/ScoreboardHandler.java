package us.upvp.practice.handlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import us.upvp.api.API;
import us.upvp.practice.scoreboard.PracticeScoreboard;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class ScoreboardHandler implements Listener
{
    private final ConcurrentHashMap<Player, PracticeScoreboard> scoreboards;

    public ScoreboardHandler()
    {
        this.scoreboards = new ConcurrentHashMap<>();
        Bukkit.getServer().getPluginManager().registerEvents(this, API.getPlugin());
    }

    public PracticeScoreboard getScoreboard(Player player)
    {
        return scoreboards.get(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        event.getPlayer().setScoreboard(scoreboard);

        PracticeScoreboard practiceScoreboard = new PracticeScoreboard(scoreboard, "&auPvP &7| &ePractice");
        scoreboards.put(event.getPlayer(), practiceScoreboard);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        scoreboards.remove(event.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event)
    {
        scoreboards.remove(event.getPlayer());
    }
}
