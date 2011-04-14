package com.coryleach.legends.lair;

import org.bukkit.*;
import org.bukkit.entity.Player;
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

        Player player = event.getPlayer();
        Location fromLocation = event.getFrom();
        Location toLocation = event.getTo();

        Dungeon fromDungeon = plugin.getDungeonForLocation(fromLocation);

        //Check to see if dungeons will be the same
        if ( fromDungeon != null && fromDungeon.containsLocation(toLocation) ) {
            //We haven't gone anywhere new
            return;
        }

        Dungeon toDungeon = plugin.getDungeonForLocation(toLocation);

        if ( fromDungeon != null ) {
            player.sendMessage("Leaving Dungeon" + fromDungeon.getName());
            return;
        }

        if ( toDungeon != null ) {
            player.sendMessage("Entering Dungeon" + toDungeon.getName());
        }

    }
    
    @Override
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        String[] split = event.getMessage().split(" ");

        if ( plugin.handleCommand(event.getPlayer(), split) ) {
            event.setCancelled(true);
        }
    	
    }



}

