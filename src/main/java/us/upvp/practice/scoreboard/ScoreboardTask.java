package us.upvp.practice.scoreboard;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.upvp.practice.Practice;
import us.upvp.practice.match.Match;
import us.upvp.practice.match.MatchType;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;

/**
 * Created by Seth on 09/04/2017.
 */
public class ScoreboardTask extends BukkitRunnable
{
    private final DecimalFormat FORMAT = new DecimalFormat("0.0");

    private String format(long millisecond)
    {
        return FORMAT.format(millisecond / 1000.0D);
    }

    private ConcurrentSkipListSet<String> splitEqually(final String text, final int size)
    {
        ConcurrentSkipListSet<String> ret = new ConcurrentSkipListSet<>();

        for (int start = 0; start < text.length(); start += size)
        {
            if (!ret.isEmpty())
            {
                ret.add(text.substring(start, Math.min(text.length(), start + size)));
            }
            else
                ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    }

    private String formatTime(long time)
    {
        if (time > 60000L)
        {
            return setLongFormat(time);
        }
        else
        {
            return format(time);
        }
    }

    private String setLongFormat(long paramMilliseconds)
    {
        if (paramMilliseconds < TimeUnit.MINUTES.toMillis(1L))
        {
            return FORMAT.format(paramMilliseconds);
        }
        return DurationFormatUtils.formatDuration(paramMilliseconds, (paramMilliseconds >= TimeUnit.HOURS.toMillis(1L) ? "HH:" : "") + "mm:ss");
    }

    private String translateString(String string)
    {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public void run()
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            PracticeScoreboard scoreboard = Practice.getInstance().getScoreboardHandler().getScoreboard(player);
            if (scoreboard == null)
                continue;

            scoreboard.clear();
            Match match = Practice.getInstance().getMatchHandler().findMatchByPlayer(player);
            if (match != null)
            {
                scoreboard.add(translateString("&7&m-----------"), translateString("&7&m-----------"));
                if (match.getMatchType().equals(MatchType.DUEL))
                {
                    for (Player player1 : match.getFighters())
                        if (!player1.getUniqueId().equals(player.getUniqueId()))
                            scoreboard.add(translateString("&eOpponent: "), player1.getName());
                }
                else if (match.getMatchType().equals(MatchType.TEAM))
                    scoreboard.add(translateString("&eRemaining: "), String.valueOf(match.getFighters().size()));

                scoreboard.add(translateString("&eDuration: "), formatTime(System.currentTimeMillis() - match.getTimeStarted()));
                scoreboard.add(translateString("&eSpectators: "), String.valueOf(match.getSpectators().size()));
                scoreboard.add("", "");
                scoreboard.add(translateString("&astore."), translateString("&auPvP.us"));
                scoreboard.add(translateString("&7&m-----------"), translateString("&7&m-----------"));
            }
            scoreboard.update();
        }
    }
}
