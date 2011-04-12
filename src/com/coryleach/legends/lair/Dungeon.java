/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coryleach.legends.lair;

import java.util.ArrayList;
import org.bukkit.*;
import com.sk89q.worldedit.bukkit.selections.*;

/**
 *
 * @author cory
 */
public class Dungeon {

    protected String name;
    protected ArrayList<DungeonSubzone> subzones;
    
    protected String enterMessage;
    protected String exitMessage;

    protected Location minimumLocation;
    protected Location maximumLocation;

    //Treasure Table Needed
    //Monster Table Needed

    public Dungeon(String name) {

        this.name = name;
        this.subzones = new ArrayList<DungeonSubzone>();

    }

    public DungeonSubzone getSubzoneForLocation(Location location) {

        //Search for a zone containing this point
        for (int i = 0; i < subzones.size(); i++) {

            DungeonSubzone subzone = subzones.get(i);

            if ( subzone.containsLocation(location) ) {
                return subzone;
            }

        }

        return null;
        
    }

    public boolean containsLocation(Location location) {

        //check to see if
        if ( !(location.getX() <= maximumLocation.getX()) ) {
            return false;
        }

        if ( !(location.getY() <= maximumLocation.getY()) ) {
            return false;
        }

        if ( !(location.getZ() <= maximumLocation.getZ()) ) {
            return false;
        }

        if ( !(location.getX() >= minimumLocation.getX()) ) {
            return false;
        }

        if ( !(location.getY() >= minimumLocation.getY()) ) {
            return false;
        }

        if ( !(location.getZ() >= minimumLocation.getZ()) ) {
            return false;
        }
        
        return true;

    }

    public Location getMinimumLocation() {
        return minimumLocation;
    }

    public Location getMaximumLocation() {
        return maximumLocation;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDefined() {

        if ( subzones.size() <= 0 ) {
            return false;
        }

        return true;
        
    }

    public void addSubzone(DungeonSubzone subzone) {

        subzones.add(subzone);

        updateMinimumLocation(subzone.minimumLocation());
        updateMaximumLocation(subzone.maximumLocation());
        
    }

    protected void updateMinimumLocation(Location location) {

        //Check that minimum location is already defined
        if ( minimumLocation == null ) {
            minimumLocation = location;
            return;
        }

        //Find points smaller than current minimum
        if ( minimumLocation.getX() > location.getX() ) {
            minimumLocation.setX(location.getX());
        }

        if ( minimumLocation.getY() > location.getY() ) {
            minimumLocation.setY(location.getY());
        }

        if ( minimumLocation.getZ() > location.getZ() ) {
            minimumLocation.setZ(location.getZ());
        }
        
    }

    protected void updateMaximumLocation(Location location) {

        //Check that minimum location is already defined
        if ( maximumLocation == null ) {
            maximumLocation = location;
            return;
        }

        //Find points smaller than current minimum
        if ( maximumLocation.getX() < location.getX() ) {
            maximumLocation.setX(location.getX());
        }

        if ( maximumLocation.getY() < location.getY() ) {
            maximumLocation.setY(location.getY());
        }

        if ( maximumLocation.getZ() < location.getZ() ) {
            maximumLocation.setZ(location.getZ());
        }

    }


}
