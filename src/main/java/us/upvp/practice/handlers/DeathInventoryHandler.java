package us.upvp.practice.handlers;

import us.upvp.practice.inventories.DeathInventory;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Seth on 09/04/2017.
 */
public class DeathInventoryHandler
{
    private final ConcurrentHashMap<UUID, DeathInventory> inventories;

    public DeathInventoryHandler()
    {
        this.inventories = new ConcurrentHashMap<>();
    }

    public void addInventory(DeathInventory deathInventory)
    {
        this.inventories.put(deathInventory.getUuid(), deathInventory);
    }

    public DeathInventory findByUnqiueId(UUID uuid)
    {
        return inventories.get(uuid);
    }
}
