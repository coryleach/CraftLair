/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coryleach.legends.lair;

import org.bukkit.*;
import com.sk89q.worldedit.bukkit.selections.*;

/**
 *
 * @author cory
 */
public class DungeonSubzone {

    public String enterMessage;
    protected CuboidSelection cuboid;
    //Treasure table
    //Monster table

    public DungeonSubzone(CuboidSelection cuboid) {

        this.cuboid = cuboid;

    }

    public boolean containsLocation(Location location) {
        return cuboid.contains(location);
    }

    public Location minimumLocation() {
        return cuboid.getMinimumPoint();
    }

    public Location maximumLocation() {
        return cuboid.getMaximumPoint();
    }

}
