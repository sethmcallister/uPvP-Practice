package us.upvp.practice;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import us.upvp.api.API;
import us.upvp.api.internal.command.CommandListener;
import us.upvp.api.internal.event.EventListener;
import us.upvp.api.internal.module.PluginModule;
import us.upvp.practice.command.*;
import us.upvp.practice.entities.EntityHider;
import us.upvp.practice.handlers.*;
import us.upvp.practice.inventories.*;
import us.upvp.practice.listeners.*;
import us.upvp.practice.queue.QueueManager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Seth on 09/04/2017.
 */
public class Practice extends PluginModule
{
    private static Practice instance;
    private List<CommandListener> commands;
    private List<EventListener> listeners;

    @Getter
    private MatchHandler matchHandler;
    @Getter
    private ArenaHandler arenaHandler;
    @Getter
    private EntityHider entityHider;
    @Getter
    private PartyHandler partyHandler;
    @Getter
    private LobbyInventory lobbyInventory;
    @Getter
    private DeathInventoryHandler deathInventoryHandler;
    @Getter
    private DuelHandler duelHandler;
    @Getter
    private DuelInventory duelInventory;
    @Getter
    private KitHandler kitHandler;
    @Getter
    private PartyInventory partyInventory;
    @Getter
    private PartyDuelInventory partyDuelInventory;
    @Getter
    private UnrankedInventory unrankedInventory;
    @Getter
    private RankedInventory rankedInventory;
    @Getter
    private QueueManager queueManager;
    @Getter
    private ScoreboardHandler scoreboardHandler;


    @Override
    public void onEnable()
    {
        instance = this;
        this.commands = new LinkedList<>();
        this.listeners = new LinkedList<>();

        this.matchHandler = new MatchHandler();
        this.arenaHandler = new ArenaHandler();
        this.entityHider = new EntityHider((Plugin) API.getPlugin(), EntityHider.Policy.BLACKLIST);
        this.partyHandler = new PartyHandler();
        this.lobbyInventory = new LobbyInventory();
        this.deathInventoryHandler = new DeathInventoryHandler();
        this.duelHandler = new DuelHandler();
        this.duelInventory = new DuelInventory();
        this.kitHandler = new KitHandler();
        this.partyInventory = new PartyInventory();
        this.partyDuelInventory = new PartyDuelInventory();
        this.unrankedInventory = new UnrankedInventory();
        this.rankedInventory = new RankedInventory();
        this.queueManager = new QueueManager();
        this.scoreboardHandler = new ScoreboardHandler();

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kb 0.95 3.1E-7 0.95");
            }
        }.runTaskLater((Plugin) API.getPlugin(), 2 * 20L);

        getCommandListeners().addAll(setupCommands());
        getEventListeners().addAll(setupListeners());
    }

    //TODO Wout make the fucking api#getPlugin() methods ;)

    public static Practice getInstance()
    {
        return instance;
    }

    public List<CommandListener> setupCommands()
    {
        this.commands.add(new AcceptCommand());
        this.commands.add(new DuelCommand());
        this.commands.add(new MatchInventoryCommand());
        this.commands.add(new PartyCommand());
        this.commands.add(new SpectateCommand());
        return commands;
    }

    public List<EventListener> setupListeners()
    {
        this.listeners.add(new ChestAccessListner());
        this.listeners.add(new EnderpearlListener());
        this.listeners.add(new EntityDamageListener());
        this.listeners.add(new HungerLossListener());
        this.listeners.add(new KitEditorListener());
        this.listeners.add(new KitSelectListener());
        this.listeners.add(new PartyInventoryListener());
        this.listeners.add(new PlayerJoinListener());
        this.listeners.add(new QueueItemListener());
        this.listeners.add(new QuickSoupListener());
        this.listeners.add(new RankedQueueItemListener());
        this.listeners.add(new UnrankedQueueItemListener());
        return this.listeners;
    }
}
