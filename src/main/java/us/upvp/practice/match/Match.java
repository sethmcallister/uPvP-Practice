package us.upvp.practice.match;

import lombok.Getter;
import mkremins.fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import us.upvp.api.API;
import us.upvp.api.framework.server.practice.Ladder;
import us.upvp.api.framework.user.User;
import us.upvp.practice.entities.EntityHider;
import us.upvp.practice.events.SendToSpawnEvent;
import us.upvp.practice.inventories.DeathInventory;
import us.upvp.practice.match.arena.Arena;
import us.upvp.practice.match.party.Party;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;

import static us.upvp.practice.Practice.getInstance;
import static us.upvp.practice.match.MatchType.DUEL;

/**
 * Created by Seth on 09/04/2017.
 */
public class Match implements Listener
{
    @Getter
    private final MatchType matchType;
    @Getter
    private final AtomicBoolean ranked = new AtomicBoolean(false);
    @Getter
    private final AtomicBoolean started = new AtomicBoolean(false);
    @Getter
    private final List<Player> fighters = new LinkedList<>();
    @Getter
    private final List<Player> spectators = new LinkedList<>();
    @Getter
    private final List<Party> parties = new LinkedList<>();
    @Getter
    private final Arena arena;
    @Getter
    private final Ladder ladder;
    @Getter
    private final long timeStarted;

    public Match(Player player1, Player player2, Ladder ladder, boolean ranked)
    {
        this.matchType = DUEL;
        this.ranked.set(ranked);
        this.started.set(false);
        this.fighters.add(player1);
        this.fighters.add(player2);
        this.arena = getInstance().getArenaHandler().getRandomArena();
        this.ladder = ladder;
        this.timeStarted = System.currentTimeMillis();
        this.start();
        getInstance().getMatchHandler().addMatch(this);
        Bukkit.getPluginManager().registerEvents(this, (Plugin) API.getPlugin());
    }

    public Match(Party party1, Party party2, Ladder ladder)
    {
        this.matchType = MatchType.TEAM;
        this.ranked.set(false);
        this.started.set(false);
        this.parties.add(party1);
        this.parties.add(party2);
        for (Party party : this.parties)
            for (Player member : party.getMembers())
                this.fighters.add(member);

        this.ladder = ladder;
        this.arena = getInstance().getArenaHandler().getRandomArena();
        this.timeStarted = System.currentTimeMillis();
        startParty();
        getInstance().getMatchHandler().addMatch(this);
        Bukkit.getPluginManager().registerEvents(this, (Plugin) API.getPlugin());
    }

    private void start()
    {
        int i = 0;
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta im = book.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + this.ladder.getDisplayName() + " Default Kit");
        book.setItemMeta(im);

        ItemStack custombook1 = new ItemStack(Material.BOOK);
        ItemMeta custombook1m = book.getItemMeta();
        custombook1m.setDisplayName(ChatColor.GOLD + this.ladder.getDisplayName() + " Custom Kit 1");
        custombook1.setItemMeta(custombook1m);

        ItemStack custombook2 = new ItemStack(Material.BOOK);
        ItemMeta custombook2m = book.getItemMeta();
        custombook2m.setDisplayName(ChatColor.GOLD + this.ladder.getDisplayName() + " Custom Kit 2");
        custombook2.setItemMeta(custombook2m);

        for (Player player : this.fighters)
        {
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);

            for (Player player1 : Bukkit.getOnlinePlayers())
            {

                if (player1 == player) continue;
                player1.hidePlayer(player);
                player.hidePlayer(player1);
            }

            for (Player player1 : this.fighters)
            {
                if (player == player1) continue;
                player.showPlayer(player1);
                player1.showPlayer(player);
            }
            player.getInventory().clear();
            player.getInventory().setItem(4, book);

            player.updateInventory();

            if (i == 0)
            {
                player.teleport(this.arena.getLocation1());
            }
            else if (i == 1)
            {
                player.teleport(this.arena.getLocation2());
                break;
            }
            i++;
        }

        new BukkitRunnable()
        {
            int i = 5;

            @Override
            public void run()
            {
                if (i == 0)
                {
                    sendMessage("&aThe duel has started.");
                    started.set(true);
                    cancel();
                    return;
                }
                sendMessage("&aStarting in &e" + i + "&a.");
                i--;
            }
        }.runTaskTimerAsynchronously((Plugin) API.getPlugin(), 0L, 20L);
    }

    private void startParty()
    {
        int i = 0;
        ItemStack book = new ItemStack(Material.BOOK);
        ItemMeta im = book.getItemMeta();
        im.setDisplayName(ChatColor.GOLD + this.ladder.getDisplayName() + " Default Kit");
        book.setItemMeta(im);

        for (Party party : this.parties)
        {
            for (Player player : party.getMembers())
            {
                for (Player player1 : Bukkit.getOnlinePlayers())
                {
                    if (player1 == player) continue;
                    player1.hidePlayer(player);
                    player.hidePlayer(player1);
                }

                for (Player player1 : this.fighters)
                {
                    if (player == player1) continue;
                    player.showPlayer(player1);
                    player1.showPlayer(player);
                }

                player.getInventory().clear();
                player.getInventory().setItem(4, book);
                player.updateInventory();

                if (i == 0)
                {
                    player.teleport(this.arena.getLocation1());
                }
                if (i == 1)
                {
                    player.teleport(this.arena.getLocation2());
                }
            }
            i++;
        }

        new BukkitRunnable()
        {
            int i = 5;

            @Override
            public void run()
            {
                if (i == 0)
                {
                    sendMessage("&aThe duel has started.");
                    started.set(true);
                    cancel();
                    return;
                }
                sendMessage("&aStarting in &e" + i + "&a.");
                i--;
            }
        }.runTaskTimerAsynchronously((Plugin) API.getPlugin(), 0L, 20L);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        if (this.fighters.contains(event.getEntity()))
        {
            if (this.matchType.equals(DUEL))
            {
                Player killed = event.getEntity();
                killed.setHealth(20.0);
                if (event.getEntity().getKiller() == null)
                {
                    for (Player player : this.fighters)
                    {
                        if (killed != player)
                        {
                            PlayerInventory iKilled = killed.getInventory();
                            PlayerInventory iKiller = player.getInventory();
                            Double health = player.getHealth();
                            stop(killed, player);
                            getInstance().getDeathInventoryHandler().addInventory(new DeathInventory(killed, 0.D, iKilled.getContents(), iKilled.getArmorContents()));
                            getInstance().getDeathInventoryHandler().addInventory(new DeathInventory(player, health, iKiller.getContents(), iKiller.getArmorContents()));
                            break;
                        }
                    }
                    return;
                }
                Player killer = event.getEntity().getKiller();
                getInstance().getDeathInventoryHandler().addInventory(new DeathInventory(killer, killer.getHealth(), killer.getInventory().getContents(), killer.getInventory().getArmorContents()));
                stop(killed, killer);
            }
            else
            {
                Player killed = event.getEntity();
                killed.setHealth(20.0);
                if (this.fighters.size() == 2)
                {
                    PlayerInventory iKilled = killed.getInventory();
                    PlayerInventory iKiller = killed.getKiller().getInventory();
                    Double health = killed.getKiller().getHealth();
                    stop(event.getEntity(), event.getEntity().getKiller());
                    getInstance().getDeathInventoryHandler().addInventory(new DeathInventory(killed, 0.0D, iKilled.getContents(), iKilled.getArmorContents()));
                    getInstance().getDeathInventoryHandler().addInventory(new DeathInventory(killed.getKiller(), health, iKiller.getContents(), iKiller.getArmorContents()));
                }
                else
                {
                    this.fighters.remove(killed);
                    this.addSpectator(killed, event.getEntity().getKiller(), true);
                }
            }
        }
        event.getDrops().clear();
        event.setDeathMessage(null);
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event)
    {
        if (this.fighters.contains(event.getPlayer()))
        {
            if (this.matchType.equals(DUEL))
            {
                for(Player player : fighters)
                {
                    if(player != null && player.getUniqueId() != event.getPlayer().getUniqueId())
                    {
                        stop(event.getPlayer(), player);
                    }
                }
            }
            else
            {
                if (this.fighters.size() == 2)
                {
                    for (Player figher : this.fighters)
                    {
                        if (figher.getUniqueId() != event.getPlayer().getUniqueId())
                        {
                            stop(event.getPlayer(), figher);
                            getInstance().getDeathInventoryHandler().addInventory(new DeathInventory(event.getPlayer(), event.getPlayer().getHealth(), event.getPlayer().getInventory().getContents(), event.getPlayer().getInventory().getArmorContents()));
                            getInstance().getDeathInventoryHandler().addInventory(new DeathInventory(figher.getKiller(), figher.getKiller().getHealth(), figher.getKiller().getInventory().getContents(), figher.getKiller().getInventory().getArmorContents()));
                            break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event)
    {
        EntityHider hider = getInstance().getEntityHider();
        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (!this.fighters.contains(player))
            {
                hider.hideEntity(player, event.getItemDrop());
            }
        }

        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin) API.getPlugin(), () -> event.getItemDrop().remove(), 4 * 20L);
    }

    @EventHandler
    public void onThrowItem(ProjectileLaunchEvent event)
    {
        EntityHider hider = getInstance().getEntityHider();
        if (event.getEntity().getShooter() instanceof Player)
        {
            if (event.getEntityType() == EntityType.SPLASH_POTION)
            {
                if (this.fighters.contains(event.getEntity().getShooter()))
                {
                    for (Player player : Bukkit.getOnlinePlayers())
                    {
                        if (!this.fighters.contains(player))
                        {
                            hider.hideEntity(player, event.getEntity());
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPotionSplashEvent(PotionSplashEvent event)
    {
        ThrownPotion potion = event.getPotion();
        if(potion.getShooter() instanceof Player)
        {
            if(this.fighters.contains(potion.getShooter()))
            {
                for(LivingEntity entity : event.getAffectedEntities())
                {
                    if(entity instanceof Player && !this.fighters.contains(entity))
                    {
                        event.setIntensity(entity, 0.0D);
                    }
                }
            }
        }
    }

    private void stop(Player looser, Player winner)
    {
        winner.getInventory().setHelmet(null);
        winner.getInventory().setChestplate(null);
        winner.getInventory().setLeggings(null);
        winner.getInventory().setBoots(null);
        winner.getInventory().clear();

        if(looser == null)
        {
            winner.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThere was an error while ending your match. Staff have been alerted and this will be fixed."));
            User uWinner = API.getUserManager().findByUniqueId(winner.getUniqueId());
            uWinner.getPractice().getWins().incrementAndGet();
            API.getLogger().log(Level.SEVERE, "THERE OCCURRED IN MATCH, LOOSER WAS NULL");
            Bukkit.getPluginManager().callEvent(new SendToSpawnEvent(winner));
            getInstance().getMatchHandler().removeMatch(this);
            return;
        }

        User uLoser = API.getUserManager().findByUniqueId(looser.getUniqueId());
        if(uLoser == null)
            uLoser = API.getUserManager().findOfflineByUniqueId(looser.getUniqueId());

        User uWinner = API.getUserManager().findByUniqueId(winner.getUniqueId());
        if (this.matchType.equals(DUEL))
        {
            sendMessage("&eWinner: " + winner.getName());

            /*
             * Winners messages
             */
            new FancyMessage().text("Inventories (click to view): ").color(ChatColor.GOLD)
                    .then(winner.getName()).color(ChatColor.YELLOW).command("/matchinv " + (winner.getName())).tooltip("Click to view " + winner.getName() + "'s inventory.").color(ChatColor.YELLOW).then(", ").color(ChatColor.YELLOW)
                    .then(looser.getName()).color(ChatColor.YELLOW).command("/matchinv " + (looser.getName())).tooltip("Click to view " + looser.getName() + "'s inventory.").send(winner);
            for (PotionEffect potion : winner.getActivePotionEffects())
            {
                winner.removePotionEffect(potion.getType());
            }
            winner.getInventory().clear();
            winner.getInventory().setHelmet(null);
            winner.getInventory().setChestplate(null);
            winner.getInventory().setLeggings(null);
            winner.getInventory().setBoots(null);
            uWinner.getPractice().getWins().incrementAndGet();


            /*
              Loosers messages
             */
            new FancyMessage().text("Inventories (click to view): ").color(ChatColor.GOLD).then(winner.getName()).color(ChatColor.YELLOW).command("/matchinv " + (winner.getName())).tooltip("Click to view " + winner.getName() + "'s inventory.").color(ChatColor.YELLOW).then(", ").color(ChatColor.YELLOW).then(looser.getName()).color(ChatColor.YELLOW).command("/matchinv " + (looser.getName())).tooltip("Click to view " + looser.getName() + "'s inventory.").send(looser);

            for (PotionEffect potion : looser.getActivePotionEffects())
            {
                looser.removePotionEffect(potion.getType());
            }
            looser.spigot().respawn();
            uLoser.getPractice().getLoses().incrementAndGet();

            if (this.ranked.get())
            {

                int p1 = uLoser.getPractice().getEloByLadder(this.ladder).get();
                int p2 = uWinner.getPractice().getEloByLadder(this.ladder).get();

                int scoreChange = 0;
                double expectedp1 = 1.0 / (1.0 + Math.pow(10.0, (p1 - p2) / 400.0));
                scoreChange = (int) (expectedp1 * 32.0);
                scoreChange = (scoreChange > 25) ? 25 : scoreChange;
                uLoser.getPractice().setLadderElo(this.ladder, p1 - scoreChange);
                uWinner.getPractice().setLadderElo(this.ladder, p2 + scoreChange);
                sendMessage("&eELO Changes: &a" + winner.getName() + " " + p2 + " +" + scoreChange + "(" + uWinner.getPractice().getEloByLadder(this.ladder).get() + ") &c" + looser.getName() + " " + p1 + " -" + scoreChange + "(" + uLoser.getPractice().getEloByLadder(this.ladder).get() + ")");
            }

            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    for (Player fighter : fighters)
                    {
                        Bukkit.getServer().getPluginManager().callEvent(new SendToSpawnEvent(fighter));
                        for (Player player : Bukkit.getOnlinePlayers())
                        {
                            if (player == fighter) continue;
                            if (!player.canSee(fighter))
                                player.showPlayer(fighter);
                            if (!fighter.canSee(player))
                                fighter.showPlayer(fighter);
                        }
                        getInstance().getEntityHider().showAllPlayers(fighter);
                    }

                    for (Player spectator : spectators)
                    {
                        Bukkit.getServer().getPluginManager().callEvent(new SendToSpawnEvent(spectator));
                        for (Player player : Bukkit.getOnlinePlayers())
                        {
                            if (player == spectator) continue;
                            if (!player.canSee(spectator))
                                player.showPlayer(spectator);
                            if (!spectator.canSee(player))
                                spectator.showPlayer(spectator);

                            spectator.setFlying(false);
                            spectator.setAllowFlight(false);
                        }
                        for (Player fighter : fighters)
                        {
                            if (fighter == spectator) continue;
                            if (!fighter.canSee(spectator))
                                fighter.showPlayer(spectator);
                            if (!spectator.canSee(fighter))
                                spectator.showPlayer(spectator);
                        }
                    }
                }
            }.runTaskLater((Plugin) API.getPlugin(), 2 * 20L);
            getInstance().getMatchHandler().removeMatch(this);
            HandlerList.unregisterAll(this);
        }
        else
        {
            Party won = null;
            Party lost = null;

            for (Party party : this.parties)
            {
                for (Player member : party.getMembers())
                {
                    if (member == winner)
                    {
                        won = party;
                    }
                    if (member == looser)
                    {
                        lost = party;
                    }
                    if (member.isDead())
                    {
                        member.spigot().respawn();
                    }
                    for (PotionEffect potion : member.getActivePotionEffects())
                    {
                        member.removePotionEffect(potion.getType());
                    }
                }
            }

            assert won != null;
            sendPartyMessage("&aWinning Team&7: &f" + won.getLeader().getName() + "'s Team.");
            assert lost != null;
            sendPartyMessage("&cLoosing Team&7: &f" + lost.getLeader().getName() + "'s Team.");

            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    for (Player fighter : fighters)
                    {
                        Bukkit.getServer().getPluginManager().callEvent(new SendToSpawnEvent(fighter));
                        for (Player player : Bukkit.getOnlinePlayers())
                        {
                            if (player == fighter) continue;
                            if (!player.canSee(fighter))
                                player.showPlayer(fighter);
                            if (!fighter.canSee(player))
                                fighter.showPlayer(fighter);
                        }
                    }

                    for (Player spectator : spectators)
                    {
                        Bukkit.getServer().getPluginManager().callEvent(new SendToSpawnEvent(spectator));
                        for (Player player : Bukkit.getOnlinePlayers())
                        {
                            if (player == spectator) continue;

                            if (!player.canSee(spectator))
                                player.showPlayer(spectator);
                            if (!spectator.canSee(player))
                                spectator.showPlayer(spectator);

                            spectator.setFlying(false);
                            spectator.setAllowFlight(false);
                        }
                        for (Party party : parties)
                        {
                            for (Player player : party.getMembers())
                            {
                                if (player == spectator) continue;

                                if (!player.canSee(spectator))
                                    player.showPlayer(spectator);
                                if (!spectator.canSee(player))
                                    spectator.showPlayer(spectator);
                            }
                        }
                    }
                }
            }.runTaskLater((Plugin) API.getPlugin(), 2 * 20L);
        }
        getInstance().getMatchHandler().removeMatch(this);
    }

    public void addSpectator(Player player, Player target)
    {
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(true);
        player.setFlying(true);
        this.spectators.add(player);
        player.teleport(target);
        for (Player fighter : this.fighters)
        {
            if (fighter.canSee(player))
                fighter.hidePlayer(player);
            player.showPlayer(fighter);
        }

        for (Player player1 : getFighters())
        {
            getInstance().getEntityHider().hideEntity(player1, player);
            getInstance().getEntityHider().showEntity(player, player1);
        }

        player.getInventory().setItem(8, getInstance().getLobbyInventory().getLeaveSpectatorTool());
        sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + player.getName() + "&e has started spectating your match."));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            if (!this.spectators.contains(event.getPlayer()))
                return;

            final ItemStack hand = event.getPlayer().getItemInHand();
            if (hand.equals(getInstance().getLobbyInventory().getLeaveSpectatorTool()))
            {
                this.spectators.remove(event.getPlayer());
                Bukkit.getPluginManager().callEvent(new SendToSpawnEvent(event.getPlayer()));
                event.getPlayer().setAllowFlight(false);
                event.getPlayer().setFlying(false);
                sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + event.getPlayer().getName() + "&e is no longer spectating your match."));
            }
        }
    }

    public void addSpectator(Player player, Player target, boolean silent)
    {
        this.spectators.add(player);
        player.teleport(target.getLocation());
        for (Player fighter : this.fighters)
        {
            if (fighter.canSee(player))
                fighter.hidePlayer(player);
            player.showPlayer(fighter);
        }
        if (!silent)
            sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + player.getName() + "&e has started spectating your match."));
    }

    private void sendMessage(String message)
    {
        for (Player player : this.fighters)
        {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    private void sendPartyMessage(String message)
    {
        for (Party party : this.parties)
        {
            for (Player player : party.getMembers())
            {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }
}
