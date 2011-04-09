package com.coryleach.legends.lair;

//import java.util.HashMap;
import java.util.logging.*;
//import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
//import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.*;
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
    
    //Public Members
    public Server server;
    public Logger log;
    public LairCommandMap commandMap;

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
    	commandMap = new LairCommandMap(server);
    	
		PluginManager pm = getServer().getPluginManager();

    	try {
    		// Register our events
    		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Priority.Normal, this);
    		pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Priority.Normal, this);
    		pm.registerEvent(Event.Type.ENTITY_COMBUST, entityListener, Priority.Normal, this);
    		pm.registerEvent(Event.Type.ENTITY_EXPLODE, entityListener, Priority.Normal, this);
    		pm.registerEvent(Event.Type.ENTITY_TARGET, entityListener, Priority.Normal, this);
    		
    	} catch ( Exception e ) {
        	System.out.println("Exception While Registering Events");
        	System.out.println(e.getMessage());
    	}
    	
        /*pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_PHYSICS, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_CANBUILD, blockListener, Priority.Normal, this);
         */
        
    	try {
            
    		//Setup PlayerListener class to preprocess command events
    		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Normal, this);
    		
    		//Register Commands to the command map
    		commandMap.register("/lairlist", "/lairlist", new LairListCommand(this,"/lairlist"));
    		commandMap.register("/lairspawn", "/lairspawn", new LairSpawnCommand(this,"/lairspawn"));
    		
    	} catch ( Exception e ) {
        	System.out.println("Exception While Registering Commands");
        	System.out.println(e.getMessage());
    	}
    	
        //Say Hello
    	System.out.println("Legends.Lair Hello!");

    }

    //This method can be removed after testing
    public static void main(String[] args) {
        //Do Nothing
    }
    
}
