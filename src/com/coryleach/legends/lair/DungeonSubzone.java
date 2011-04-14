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
        this.cuboid = new CuboidSelection(cuboid.getWorld(),cuboid.getMinimumPoint(),cuboid.getMaximumPoint());
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

    //X-axis
    public int width() {
       return cuboid.getHeight();
    }

    //Y-axis
    public int height() {
        return cuboid.getHeight();
    }

    //Z-axis
    public int length() {
        return cuboid.getLength();
    }

    public void print() {

        Location min = this.minimumLocation();

        int minX = (int)min.getX();
        int minX2 = (int)min.getX() + this.width();

        int minY = (int)min.getY();
        int minY2 = (int)min.getY() + this.height();

        int minZ = (int)min.getZ();
        int minZ2 = (int)min.getZ() + this.length();

        System.out.print("MinX: " + Integer.toString(minX) + " " + Integer.toString(minX2));
        System.out.print("MinY: " + Integer.toString(minY) + " " + Integer.toString(minY2));
        System.out.print("MinZ: " + Integer.toString(minZ) + " " + Integer.toString(minZ2));

    }

    public boolean intersects(DungeonSubzone intersectZone) {

        Location min = intersectZone.minimumLocation();
        Location localmin = this.minimumLocation();

        int minX = (int)min.getX();
        int minX2 = (int)min.getX() + intersectZone.width();

        int minY = (int)min.getY();
        int minY2 = (int)min.getY() + intersectZone.height();

        int minZ = (int)min.getZ();
        int minZ2 = (int)min.getZ() + intersectZone.length();

        int localMinX = (int)localmin.getX();
        int localMinX2 = (int)localmin.getX() + this.width();

        int localMinY = (int)localmin.getY();
        int localMinY2 = (int)localmin.getY() + this.height();

        int localMinZ = (int)localmin.getZ();
        int localMinZ2 = (int)localmin.getZ() + this.length();

        System.out.print("MinX: " + Integer.toString(minX) + " " + Integer.toString(minX2));
        System.out.print("LocalMinX: " + Integer.toString(localMinX) + " " + Integer.toString(localMinX2));

        System.out.print("MinY: " + Integer.toString(minY) + " " + Integer.toString(minY2));
        System.out.print("LocalMinY: " + Integer.toString(localMinY) + " " + Integer.toString(localMinY2));

        System.out.print("MinZ: " + Integer.toString(minZ) + " " + Integer.toString(minZ2));
        System.out.print("LocalMinZ: " + Integer.toString(localMinZ) + " " + Integer.toString(localMinZ2));

        boolean xIntersect = false;
        //Check x direction intersect
        if ( minX >= localMinX && minX <= localMinX2 ) {
            System.out.print("X Intersect");
            xIntersect = true;
        } else if ( localMinX >= minX && localMinX <= minX2 ) {
            System.out.print("X Intersect");
            xIntersect = true;
        } else {
            System.out.print("No X Intersect");
            return xIntersect;
        }

        boolean yIntersect = false;
        //Check y direction intersect
        if ( minY >= localMinY && minY <= localMinY2 ) {
            System.out.print("Y Intersect");
            yIntersect = true;
        } else if ( localMinY >= minY && localMinY <= minY2 ) {
            System.out.print("Y Intersect");
            yIntersect = true;
        } else {
            System.out.print("No Y Intersect");
            return yIntersect;
        }

        boolean zIntersect = false;
        //Check z direction intersect
        if ( minZ >= localMinZ && minZ <= localMinZ2 ) {
            System.out.print("Z Intersect");
            zIntersect = true;
        } else if ( localMinZ >= minZ && localMinZ <= minZ2 ) {
            System.out.print("Z Intersect");
            zIntersect = true;
        } else {
            System.out.print("No Z Intersect");
            return zIntersect;
        }

        System.out.print("Intersect on all axis");
        return true;
        
    }

}
