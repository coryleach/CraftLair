package com.coryleach.legends.lair;

import org.bukkit.event.player.*;

/**
 * Handle events for all Player related events
 * @author Dinnerbone
 */
public class LairPlayerListener extends PlayerListener {
    private final Lair plugin;

    public LairPlayerListener(Lair instance) {
        plugin = instance;
    }

    @Override
    public void onPlayerJoin(PlayerEvent event) {
    }

    @Override
    public void onPlayerQuit(PlayerEvent event) {
    }

    @Override
    public void onPlayerMove(PlayerMoveEvent event) {    	
    }
    
    @Override
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	
    	//Leave the processing to the command map
    	if ( plugin.commandMap.dispatch(event.getPlayer(), event.getMessage()) ) {
    		//dispatch returned true which means we used up the event
    		event.setCancelled(true);
    	}
    	
    }
    
}

