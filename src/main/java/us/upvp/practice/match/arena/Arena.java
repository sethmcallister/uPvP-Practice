package us.upvp.practice.match.arena;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Seth on 09/04/2017.
 */
public class Arena
{
    @Getter
    public final String name;
    @Getter
    public final Integer id;
    @Getter
    public final Location location1;
    @Getter
    public final Location location2;

    public Arena(String name, Integer id, Location location1, Location location2)
    {
        this.name = name;
        this.id = id;
        this.location1 = location1;
        this.location2 = location2;

        Bukkit.getWorld(location1.getWorld().getName()).loadChunk(location1.getChunk());
        Bukkit.getWorld(location2.getWorld().getName()).loadChunk(location2.getChunk());
    }
}
