package com.coryleach.legends.lair;

import java.util.*;
import java.util.logging.*;
//import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
//import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import com.sk89q.worldedit.bukkit.*;
import com.sk89q.worldedit.bukkit.selections.*;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.UnhandledCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;
import com.sk89q.minecraft.util.commands.CommandsManager;
//import com.sk89q.worldedit.*;
//import com.sk89q.worldedit.commands.InsufficientArgumentsException;
//import org.bukkit.command.*;


/**
 * Legends Lair plugin for Bukkit
 *
 * @author coryleach
 */
public class Lair extends JavaPlugin {

    //Private Members
    private final LairPlayerListener playerListener = new LairPlayerListener(this);
    //private final LairBlockListener blockListener = new LairBlockListener(this);
    private final LairEntityListener entityListener = new LairEntityListener(this);
    //private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    private HashMap<String,Dungeon> dungeons;
    private HashMap<Player,EditSession> editSessions;
    private CommandsManager<Player> commandMap;

    //Public Members
    public Server server;
    public Logger log;
    //public LairCommandMap commandMap;

    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
    	
    	//Say Goodbye
        System.out.println("Legends.Lair Goodbye!");
        
    }

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events
    	
    	//Setup Plugin Member Variables
    	server = getServer();
    	log = server.getLogger();

        commandMap = new CommandsManager<Player>() {
            @Override
            public boolean hasPermission(Player player, String perm) {
                //TODO: Implement Permissions
                return true;
            }
        };

        dungeons = new HashMap<String,Dungeon>();
        editSessions = new HashMap<Player,EditSession>();

	PluginManager pm = getServer().getPluginManager();

    	try {
            // Register our events
            pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Normal, this);
            pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Priority.Normal, this);
            pm.registerEvent(Event.Type.ENTITY_COMBUST, entityListener, Priority.Normal, this);
            pm.registerEvent(Event.Type.ENTITY_EXPLODE, entityListener, Priority.Normal, this);
            pm.registerEvent(Event.Type.ENTITY_TARGET, entityListener, Priority.Normal, this);

    	} catch ( Exception e ) {

            log.info("Exception while registering events.");
            log.info(e.getMessage());

        }
        
    	try {
            
            //Setup PlayerListener class to preprocess command events
            pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Normal, this);
            pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);

            //Register Commands to the command map
            commandMap.register(LairDungeonCommand.class);

    	} catch ( Exception e ) {

            log.info("Exception Registering Commands: ");
            log.info(e.getMessage());

    	}
    	
        //Say Hello
        log.info("Legends.Lair Hello!");

    }

    public void addDungeon(Dungeon dungeon) {

        String name = dungeon.getName();
        this.dungeons.put(name, dungeon);

    }

    public Dungeon getDungeonWithName(String name) {

        Dungeon dungeon = this.dungeons.get(name);
        return dungeon;

    }

    public EditSession getSessionForPlayer(Player player) {

        return this.editSessions.get(player);

    }

    public EditSession createSessionForPlayer(Player player, Dungeon dungeon) {

        EditSession session = new EditSession(player,dungeon);
        this.editSessions.put(player, session);

        return session;

    }

    public String[] getDungeonList() {

        Set<String> set = this.dungeons.keySet();

        return set.toArray(new String[set.size()]);

    }

    public Dungeon getDungeonForLocation(Location loc) {

        String[] keys = getDungeonList();

        for ( int i = 0; i < keys.length; i++ ) {

            String name = keys[i];

            Dungeon dungeon = this.dungeons.get(name);

            if ( dungeon == null ) {
                continue;
            }

            if ( dungeon.containsLocation(loc) ) {
                return dungeon;
            }
            
        }

        return null;
        
    }

    //This method can be removed after testing
    public static void main(String[] args) {
        //Do Nothing
    }

    public CuboidSelection getSelection(Player player) {
        
        PluginManager pm = getServer().getPluginManager();

        if ( !pm.isPluginEnabled("WorldEdit") ) {
            player.sendMessage("This plugin requres the WorldEdit plugin");
            return null;
        }

        WorldEditPlugin worldEdit = (WorldEditPlugin)pm.getPlugin("WorldEdit");

        Selection selection = worldEdit.getSelection(player);

        //We only work with Cuboid selections
        if ( !(selection instanceof CuboidSelection) ) {
            return null;
        }

        return (CuboidSelection)selection;

    }

    public boolean handleCommand(Player player, String[] split) {

        try {
            
            split[0] = split[0].substring(1);
            
            // Quick script shortcut
            if (split[0].matches("^[^/].*\\.js$")) {
                String[] newSplit = new String[split.length + 1];
                System.arraycopy(split, 0, newSplit, 1, split.length);
                newSplit[0] = "cs";
                newSplit[1] = newSplit[1];
                split = newSplit;
            }

            // No command found!
            if (!commandMap.hasCommand(split[0])) {
                return false;
            }

            try {
                commandMap.execute(split, player, this, player);
            } catch (CommandPermissionsException e) {
                player.sendMessage("You don't have permission to do this.");
            } catch (MissingNestedCommandException e) {
                player.sendMessage(e.getUsage());
            } catch (CommandUsageException e) {
                player.sendMessage(e.getMessage());
                player.sendMessage(e.getUsage());
            } catch (WrappedCommandException e) {
                throw e.getCause();
            } catch (UnhandledCommandException e) {
                return false;
            } finally {

            }
            
        } catch (Throwable excp) {

            player.sendMessage("Please report this error:");
            player.sendMessage(excp.getMessage());

        }

        return true;
        
    }
    
}
