package us.upvp.practice.handlers;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.upvp.api.framework.practice.Ladder;

/**
 * Created by Seth on 09/04/2017.
 */
public class KitHandler
{
    private static ItemStack dsword()
    {
        ItemStack is = new ItemStack(Material.DIAMOND_SWORD, 1);

        ItemMeta im = is.getItemMeta();
        im.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        im.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        is.setItemMeta(im);
        return is;
    }

    private static ItemStack enderpearl()
    {
        ItemStack is = new ItemStack(Material.ENDER_PEARL, 16);
        return is;
    }

    private static ItemStack potato()
    {
        ItemStack is = new ItemStack(Material.COOKED_BEEF, 64);
        return is;
    }

    public void givePlayerItems(Player player, Ladder ladder)
    {
        if (ladder.equals(Ladder.NO_DEBUFF))
        {
            player.getInventory().clear();
            player.getInventory().setItem(0, dsword());
            player.getInventory().setItem(1, enderpearl());
            player.getInventory().setItem(8, potato());
            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            ItemStack fres = new ItemStack(373, 1, (short) 8259);
            player.getInventory().setItem(3, fres);
            player.getInventory().setItem(9, speed);
            player.getInventory().setItem(10, speed);
            player.getInventory().setItem(18, speed);
            player.getInventory().setItem(2, speed);

            int i = 36;

            while (i > 0)
            {
                i--;
                ItemStack item = new ItemStack(373, 1, (short) 16421);
                player.getInventory().addItem(item);
            }
            ItemStack helm = (new ItemStack(Material.DIAMOND_HELMET));
            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            helm.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setHelmet(helm);
            ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            chest.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setChestplate(chest);
            ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
            leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            leg.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setLeggings(leg);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            boots.addEnchantment(Enchantment.DURABILITY, 3);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            player.getInventory().setBoots(boots);
            player.updateInventory();
        }
        if (ladder.equals(Ladder.DEBUFF))
        {
            player.getInventory().clear();
            player.getInventory().setItem(0, dsword());
            player.getInventory().setItem(1, enderpearl());
            player.getInventory().setItem(8, potato());
            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            ItemStack fres = new ItemStack(373, 1, (short) 8259);
            player.getInventory().setItem(3, fres);
            player.getInventory().setItem(9, speed);
            player.getInventory().setItem(10, speed);
            player.getInventory().setItem(18, speed);
            player.getInventory().setItem(2, speed);
            ItemStack posion = new ItemStack(373, 1, ((short) 16452));
            player.getInventory().setItem(16, posion);
            player.getInventory().setItem(26, posion);
            ItemStack slowness = new ItemStack(373, 1, ((short) 16458));
            player.getInventory().setItem(17, slowness);
            player.getInventory().setItem(25, slowness);

            int i = 32;

            while (i > 0)
            {
                i--;
                ItemStack item = new ItemStack(373, 1, (short) 16421);
                player.getInventory().addItem(item);
            }

            ItemStack helm = (new ItemStack(Material.DIAMOND_HELMET));
            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            helm.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setHelmet(helm);
            ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            chest.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setChestplate(chest);
            ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
            leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            leg.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setLeggings(leg);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
            boots.addEnchantment(Enchantment.DURABILITY, 3);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            player.getInventory().setBoots(boots);

            player.updateInventory();
        }
        if (ladder.equals(Ladder.SOUP))
        {
            player.getInventory().clear();
            ItemStack is = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta im = is.getItemMeta();
            is.setItemMeta(im);
            player.getInventory().setItem(0, is);

            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            player.getInventory().setItem(1, speed);
            player.getInventory().setItem(35, speed);
            int i = 7;
            while (i > 0)
            {
                i--;
                ItemStack item = new ItemStack(Material.MUSHROOM_SOUP);
                player.getInventory().addItem(item);
            }

            ItemStack helm = (new ItemStack(Material.IRON_HELMET));
            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            helm.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setHelmet(helm);
            ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            chest.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setChestplate(chest);
            ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
            leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            leg.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setLeggings(leg);
            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            boots.addEnchantment(Enchantment.DURABILITY, 3);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            player.getInventory().setBoots(boots);
        }
        if (ladder.equals(Ladder.GAPPLE))
        {
            player.getInventory().clear();
            ItemStack is = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta im = is.getItemMeta();
            im.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
            im.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
            is.setItemMeta(im);
            player.getInventory().setItem(0, is);

            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            ItemStack str = new ItemStack(373, 1, (short) 8265);

            player.getInventory().setItem(1, speed);
            player.getInventory().setItem(2, str);

            player.getInventory().setItem(16, str);
            player.getInventory().setItem(17, speed);
            player.getInventory().setItem(25, str);
            player.getInventory().setItem(26, speed);
            player.getInventory().setItem(34, str);
            player.getInventory().setItem(35, speed);

            ItemStack fres = new ItemStack(373, 1, (short) 8259);
            player.getInventory().setItem(3, fres);

            ItemStack gapple = new ItemStack(322, 64, (byte) 1);
            player.getInventory().setItem(1, gapple);

            ItemStack helm = (new ItemStack(Material.DIAMOND_HELMET));
            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            helm.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setHelmet(helm);
            ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            chest.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setChestplate(chest);
            ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
            leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            leg.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setLeggings(leg);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            boots.addEnchantment(Enchantment.DURABILITY, 3);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            player.getInventory().setBoots(boots);

            player.getInventory().setItem(9, helm);
            player.getInventory().setItem(10, chest);
            player.getInventory().setItem(11, leg);
            player.getInventory().setItem(12, boots);
            player.updateInventory();
        }
        if (ladder.equals(Ladder.GAPPLE))
        {
            player.getInventory().clear();
            ItemStack is = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta im = is.getItemMeta();
            im.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
            im.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
            is.setItemMeta(im);
            player.getInventory().setItem(0, is);

            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            ItemStack str = new ItemStack(373, 1, (short) 8265);

            player.getInventory().setItem(4, speed);
            player.getInventory().setItem(2, str);

            player.getInventory().setItem(16, str);
            player.getInventory().setItem(17, speed);
            player.getInventory().setItem(25, str);
            player.getInventory().setItem(26, speed);
            player.getInventory().setItem(34, str);
            player.getInventory().setItem(35, speed);

            ItemStack fres = new ItemStack(373, 1, (short) 8259);
            player.getInventory().setItem(3, fres);

            ItemStack gapple = new ItemStack(322, 64, (byte) 1);
            player.getInventory().setItem(1, gapple);

            ItemStack helm = (new ItemStack(Material.DIAMOND_HELMET));
            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            helm.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setHelmet(helm);
            ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            chest.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setChestplate(chest);
            ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
            leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            leg.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setLeggings(leg);
            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            boots.addEnchantment(Enchantment.DURABILITY, 3);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            player.getInventory().setBoots(boots);

            player.getInventory().setItem(9, helm);
            player.getInventory().setItem(10, chest);
            player.getInventory().setItem(11, leg);
            player.getInventory().setItem(12, boots);
            player.updateInventory();
        }
        if (ladder.equals(Ladder.VANILLA))
        {
            player.getInventory().clear();
            ItemStack is = new ItemStack(Material.DIAMOND_SWORD, 1);
            ItemMeta im = is.getItemMeta();
            im.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
            im.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
            is.setItemMeta(im);

            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            ItemStack str = new ItemStack(373, 1, (short) 8233);
            ItemStack fres = new ItemStack(373, 1, (short) 8259);
            ItemStack weakness = new ItemStack(373, 1, (short) 16456);
            ItemStack slowness = new ItemStack(373, 1, (short) 16458);
            ItemStack posion = new ItemStack(373, 1, (short) 16388);
            ItemStack regen = new ItemStack(373, 1, (short) 16385);
            ItemStack speedDebuff = new ItemStack(373, 1, (short) 16418);

            player.getInventory().setItem(0, is);
            player.getInventory().setItem(1, enderpearl());
            player.getInventory().setItem(2, speed);
            player.getInventory().setItem(3, str);
            player.getInventory().setItem(4, fres);
            player.getInventory().setItem(8, potato());
            player.getInventory().setItem(9, weakness);
            player.getInventory().setItem(10, weakness);
            player.getInventory().setItem(15, str);
            player.getInventory().setItem(16, speed);
            player.getInventory().setItem(17, regen);
            player.getInventory().setItem(18, slowness);
            player.getInventory().setItem(19, slowness);
            player.getInventory().setItem(24, str);
            player.getInventory().setItem(25, speed);
            player.getInventory().setItem(26, regen);
            player.getInventory().setItem(27, posion);
            player.getInventory().setItem(28, posion);
            player.getInventory().setItem(33, str);
            player.getInventory().setItem(34, speed);
            player.getInventory().setItem(35, speedDebuff);

            int i = 32;

            while (i > 0)
            {
                i--;
                ItemStack item = new ItemStack(373, 1, (short) 16421);
                player.getInventory().addItem(item);
            }

            ItemStack helm = (new ItemStack(Material.DIAMOND_HELMET));
            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            helm.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setHelmet(helm);

            ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            chest.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setChestplate(chest);

            ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
            leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            leg.addEnchantment(Enchantment.DURABILITY, 3);
            player.getInventory().setLeggings(leg);

            ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            boots.addEnchantment(Enchantment.DURABILITY, 3);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            player.getInventory().setBoots(boots);

            player.updateInventory();
        }
        if (ladder.equals(Ladder.ARCHER))
        {
            player.getInventory().clear();
            ItemStack bow = new ItemStack(Material.BOW, 1);
            ItemMeta bowm = bow.getItemMeta();
            bowm.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
            bowm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
            bow.setItemMeta(bowm);

            player.getInventory().setItem(0, bow);
            player.getInventory().setItem(2, new ItemStack(Material.WOOD_PICKAXE));
            player.getInventory().setItem(8, potato());
            player.getInventory().setItem(26, new ItemStack(Material.ARROW, 64));

            player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
            player.updateInventory();
        }
        if (ladder.equals(Ladder.AXE))
        {
            player.getInventory().clear();
            ItemStack axe = new ItemStack(Material.IRON_AXE, 1);
            axe.addEnchantment(Enchantment.DAMAGE_ALL, 1);

            player.getInventory().setItem(0, axe);
            player.getInventory().setItem(1, enderpearl());
            player.getInventory().setItem(7, new ItemStack(Material.GOLDEN_APPLE));
            player.getInventory().setItem(8, potato());

            ItemStack item = new ItemStack(373, 1, (short) 16421);
            int i = 4;
            while (i > 0)
            {
                i--;
                player.getInventory().addItem(item);
            }

            player.getInventory().setItem(30, item);
            player.getInventory().setItem(31, item);
            player.getInventory().setItem(32, item);
            player.getInventory().setItem(33, item);

            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            player.getInventory().setItem(6, speed);
            player.getInventory().setItem(35, speed);
            player.getInventory().setItem(36, speed);

            ItemStack helm = (new ItemStack(Material.IRON_HELMET));
            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            player.getInventory().setHelmet(helm);
            ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
            chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            player.getInventory().setChestplate(chest);
            ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
            leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            player.getInventory().setLeggings(leg);
            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
            boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
            boots.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            player.getInventory().setBoots(boots);
            player.updateInventory();
        }
        if (ladder.equals(Ladder.NO_ENCHANTS))
        {
            player.getInventory().clear();
            player.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
            player.getInventory().setItem(1, enderpearl());
            player.getInventory().setItem(8, potato());
            ItemStack speed = new ItemStack(373, 1, (short) 8226);
            player.getInventory().setItem(17, speed);
            player.getInventory().setItem(26, speed);
            player.getInventory().setItem(35, speed);
            player.getInventory().setItem(7, speed);

            ItemStack item = new ItemStack(373, 1, (short) 16421);
            int i = 38;

            while (i > 0)
            {
                i--;
                player.getInventory().addItem(item);
            }

            player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

            player.updateInventory();
        }
    }
}
