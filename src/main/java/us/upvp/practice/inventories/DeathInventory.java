package us.upvp.practice.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

/**
 * Created by Seth on 09/04/2017.
 */
public class DeathInventory
{
    private UUID uuid;
    private String name;
    private Double health;
    private ItemStack[] inventory;
    private ItemStack[] armor;


    public DeathInventory(Player player, Double health, ItemStack[] inventory, ItemStack[] armor)
    {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.health = health;
        this.inventory = inventory;

        this.armor = armor;
    }

    public Inventory openInventoryUUID()
    {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&a" + name + "&e's latest inventory."));
        for (ItemStack itemStack : armor)
        {
            inventory.addItem(itemStack);
        }
        int place = 9;
        for (ItemStack itemStack : inventory)
        {
            inventory.setItem(place, itemStack);
            place++;
        }
        ItemStack is = new ItemStack(Material.SPECKLED_MELON);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Health: " + ChatColor.RED + Math.round(health));
        is.setItemMeta(im);
        inventory.setItem(50, new ItemStack(160, 1, (byte) 8));
        inventory.setItem(49, is);
        inventory.setItem(48, new ItemStack(160, 1, (byte) 8));
        return inventory;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public Double getHealth()
    {
        return health;
    }

    public ItemStack[] getInventory()
    {
        return inventory;
    }

    public ItemStack[] getArmor()
    {
        return armor;
    }
}
