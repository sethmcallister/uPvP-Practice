package us.upvp.practice.handlers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import us.upvp.practice.match.arena.Arena;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Seth on 09/04/2017.
 */
public class ArenaHandler
{
    @Getter
    private final List<Arena> arenas;

    public ArenaHandler()
    {
        this.arenas = new LinkedList<>();
        this.arenas.add(new Arena("Taiga", 0, new Location(Bukkit.getWorld("world"), 637.5, 39.5, 509.5, 0, 0), new Location(Bukkit.getWorld("world"), 637.5, 39.5, 597.5, 180, 0)));
        this.arenas.add(new Arena("Sand", 1, new Location(Bukkit.getWorld("world"), 763.5, 77.5, 891.5, 0, 0), new Location(Bukkit.getWorld("world"), 763.5, 77.5, 802.5, 0, 0)));
        this.arenas.add(new Arena("Weird Thing", 2, new Location(Bukkit.getWorld("world"), -362.5, 76.5, 402.5, 90, 0), new Location(Bukkit.getWorld("world"), -362.5, 75.5, 491.5, 180, 0)));
    }

    public Arena getRandomArena()
    {
        Random random = new Random();
        return getArena(random.nextInt(arenas.size()));
    }

    private Arena getArena(Integer id)
    {
        for (Arena arena : arenas)
        {
            if (arena.getId().equals(id))
                return arena;
        }
        return null;
    }
}
